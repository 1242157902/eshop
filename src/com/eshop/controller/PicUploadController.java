package com.eshop.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.eshop.entrity.GoodPics;
import com.eshop.entrity.Goods;
import com.eshop.service.GoodPicServiceImpl;
import com.eshop.service.interf.GoodPicService;
import com.eshop.utils.AdminSessionUtils;
import com.eshop.utils.ChineseToEnglishUtils;
import com.eshop.utils.SessionUtils;
import com.eshop.utils.SubstringUtils;
import com.eshop.utils.UUIDUtil;


/**
 * 商品详细页多图片上传控制
 * @author Jane
 *
 */
public class PicUploadController extends HttpServlet {
	
	GoodPicService picService=new GoodPicServiceImpl();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String company=AdminSessionUtils.getSessionAdminUserCommpany(request);
	 	GoodPics goodpic=new GoodPics();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter pout = response.getWriter();
		try {
			// 得到存放上传文件的真实路径
			String storePath = getServletContext().getRealPath("/upload");

			// 设置环境
			DiskFileItemFactory factory = new DiskFileItemFactory();
		/*	factory.setRepository(new File(getServletContext().getRealPath("/temp")));//设置临时存放目录
*/			// 判断一下form是否是enctype=multipart/form-data类型的
			boolean isMultipart = ServletFileUpload.isMultipartContent(request);
			if (!isMultipart) {
				System.out.println("大傻鸟");
				return;
			}
			// ServletFileUpload核心类
			ServletFileUpload upload = new ServletFileUpload(factory);
//			upload.setProgressListener(new ProgressListener() {
//				//pBytesRead：当前以读取到的字节数
//				//pContentLength：文件的长度
//				//pItems:第几项
//				public void update(long pBytesRead, long pContentLength,
//						int pItems) {
//					System.out.println("已读取："+pBytesRead+",文件大小："+pContentLength+",第几项："+pItems);
//				}
//				
//			});
//			upload.setFileSizeMax(4 * 1024 * 1024);// 设置单个上传文件的大小
//			upload.setSizeMax(6 * 1024 * 1024);// 设置总文件大小
			// 解析
			List<FileItem> items = upload.parseRequest(request);
			int i=0;
			String newStorePath=null;
			for (FileItem item : items) {
				if (item.isFormField()) {
					// 普通字段
					String fieldName = item.getFieldName();
					String fieldValue = item.getString("UTF-8");
					System.out.println(fieldName + "=" + fieldValue);
					if(fieldName.equals("GoodID")){
						goodpic.setGood_id(fieldValue);
					}else if(fieldName.equals("Picname")){
						goodpic.setGood_pic(fieldValue);
					}else if(fieldName.equals("order")){
						goodpic.setOrder(Integer.parseInt(fieldValue));
					}
				} else {
					// 得到MIME类型
					String mimeType = item.getContentType();
					// 只允许上传图片
					// if(mimeType.startsWith("image")){
						// 上传字段
						InputStream in = item.getInputStream();
						// 上传的文件名
						String fileName = item.getName();// C:\Documents and
						if(fileName==null||"".equals(fileName.trim())){
							i++;
							continue;
						}
						// Settings\wzhting\妗岄潰\a.txt
						// a.txt
						fileName = fileName
								.substring(fileName.lastIndexOf("\\") + 1);// a.txt
						fileName =ChineseToEnglishUtils.getUpEname(fileName);
						System.out.println(request.getRemoteAddr()+"=============="+fileName);
						
						if(i<5){
							// 如果是中图片则调用以下方法；
							 newStorePath = makeStorePath(storePath, fileName);
						}else{
							//如果是小图片则调用以下方法；
							 newStorePath = makeStorePath1(storePath, fileName);
						}
						// /WEB-INF/files和文件名，创建一个新的存储路径
						// /WEB-INF/files/1/12
						String storeFile = newStorePath + "\\" + fileName;// WEB-INF/files/1/2/sldfdslf.txt
						OutputStream out = new FileOutputStream(storeFile);
						
						byte b[] = new byte[1024];
						int len = -1;
						while ((len = in.read(b)) != -1) {
							out.write(b, 0, len);
						}
						//JAVA中正则表达式,用"\\\\"表示"\";;;java 文件路径 反斜杠转为斜杠
						storeFile= storeFile.replaceAll("\\\\", "/");
						System.out.println(storeFile);
						//截取需要的图片存储名
						goodpic.setPic_url(SubstringUtils.subString(storeFile));
						System.out.println(SubstringUtils.subString(storeFile));
						
						in.close();
						item.delete();//删除临时文件
						
						//查询数据库中图片有没有记录；
						String good_id=picService.findGoodByPic(SubstringUtils.subString(storeFile),company);
						if(good_id==null||!good_id.equals(goodpic.getGood_id())){
						picService.addGoodPic(goodpic,company);
						}
						i++;
					}
				 }
			
			request.setAttribute("message", "<script>alert('添加成功！')</script>");
			request.getRequestDispatcher("/managers/good/good.jsp").forward(request, response);
			
		} catch (org.apache.commons.fileupload.FileUploadBase.FileSizeLimitExceededException e) {
			// 单个文件超出大小时的异常
			pout.write("单个文件大小不能超出4M");
		} catch (org.apache.commons.fileupload.FileUploadBase.SizeLimitExceededException e) {
			// 总文件超出大小时的异常
			pout.write("总文件大小不能超出6M");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// 根据 /WEB-INF/files和文件名，创建一个新的存储中图的文件路径 
	private String makeStorePath(String storePath, String fileName) {
		int hashCode = fileName.hashCode();
		String dir1="middle";

		String path = storePath + "\\" + dir1;
		File file = new File(path);
		if (!file.exists())
			file.mkdirs();

		return path;
	}
	// 产生存小图的文件夹路径；
		private String makeStorePath1(String storePath, String fileName) {
			int hashCode = fileName.hashCode();
			String dir1="small";

			String path = storePath + "\\" + dir1;
			File file = new File(path);
			if (!file.exists())
				file.mkdirs();

			return path;
		}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
