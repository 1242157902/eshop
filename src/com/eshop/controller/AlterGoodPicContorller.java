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

import com.eshop.entrity.Goods;
import com.eshop.entrity.GoodsRecord;
import com.eshop.entrity.Stock;
import com.eshop.service.CategoryServiceImpl;
import com.eshop.service.GoodRecordServiceImpl;
import com.eshop.service.GoodServiceImpl;
import com.eshop.service.StockServiceImpl;
import com.eshop.service.interf.CategoryService;
import com.eshop.service.interf.GoodRecordService;
import com.eshop.service.interf.GoodService;
import com.eshop.service.interf.StockService;
import com.eshop.utils.AdminSessionUtils;
import com.eshop.utils.ChineseToEnglishUtils;
import com.eshop.utils.NumStringAddUtil;
import com.eshop.utils.SubstringUtils;
import com.eshop.utils.UUIDUtil;

/**
 * 更改商品图片Contorller
 * @author xia
 *
 */
public class AlterGoodPicContorller extends HttpServlet{
	GoodService goodService=new GoodServiceImpl();
	CategoryService categoryService=new CategoryServiceImpl();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String company=AdminSessionUtils.getSessionAdminUserCommpany(request);
		int userID=AdminSessionUtils.getSessionAdminUserID(request);
	 	Goods good=new Goods();
	 	Goods good1=new Goods();
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
			for (FileItem item : items) {
				if (item.isFormField()) {
					// 普通字段
					String fieldName = item.getFieldName();
					String fieldValue = item.getString("UTF-8");
					System.out.println(fieldName + "=" + fieldValue);
					/*if(fieldName.equals("Goodname")){
						good.setGood_name(fieldValue);
					}else */if(fieldName.equals("Good_id")){
						good.setGood_id(fieldValue);
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
						// 构建输出流
						// 打散存储目录
						String newStorePath = makeStorePath(storePath, fileName);// 根据
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
						
						//判断存入哪个图片字段
						if(i==0){
							//截取需要的图片存储名
							good.setGood_img(SubstringUtils.subString(storeFile));
						}else if(i==1){
							good.setGood_small_img(SubstringUtils.subString(storeFile));
						}else if(i==2){
							good.setGood_detail_img(SubstringUtils.subString(storeFile));
						}else if(i==3){
							good.setGood_category_img(SubstringUtils.subString(storeFile));
						}
						i++;
						/*//截取需要的图片存储名
						good.setGood_img(SubstringUtils.subString(storeFile));*/
						System.out.println(SubstringUtils.subString(storeFile));
						
						in.close();
						item.delete();//删除临时文件
						
					}
				 }
				//根据good_id判断是否已经存在此商品；
				good1=goodService.findGoodById(good.getGood_id(), company);
				if(good1!=null){
					//说明用户没有上传图片
					if(good.getGood_img()==null){
						good.setGood_img(good1.getGood_img());
					}
					if(good.getGood_detail_img()==null){
						good.setGood_detail_img(good1.getGood_detail_img());
					}
					if(good.getGood_small_img()==null){
						good.setGood_small_img(good1.getGood_small_img());
					}
					if(good.getGood_category_img()==null){
						good.setGood_category_img(good1.getGood_category_img());
					}
				}
				goodService.updateGoodPics(good,company);
				request.setAttribute("message", "<script>alert('修改成功！')</script>");
				request.getRequestDispatcher("/managers/good/good.jsp").forward(request, response);
				
				
			//}
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

	// 根据 /WEB-INF/files和文件名，创建一个新的存储路径 /WEB-INF/files/1/12
	private String makeStorePath(String storePath, String fileName) {
		int hashCode = fileName.hashCode();
		int dir1 = hashCode & 0xf;// 0000~1111：整数0~15共16个
		int dir2 = (hashCode & 0xf0) >> 4;// 0000~1111：整数0~15共16个

		String path = storePath + "\\" + dir1 + "\\" + dir2; // WEB-INF/file/1/12
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
