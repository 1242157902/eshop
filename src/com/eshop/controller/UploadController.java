package com.eshop.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.eshop.entrity.Category;
import com.eshop.entrity.Goods;
import com.eshop.entrity.GoodsRecord;
import com.eshop.entrity.Stock;
import com.eshop.entrity.User;
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
import com.eshop.utils.SessionUtils;
import com.eshop.utils.SubstringUtils;
import com.eshop.utils.UUIDUtil;

/**
 * 商品添加，首页图片上传控制类
 * @author Jane
 *
 */
public class UploadController extends HttpServlet {
	
	GoodService goodService=new GoodServiceImpl();
	CategoryService categoryService=new CategoryServiceImpl();
	StockService stockService = new StockServiceImpl();
	GoodRecordService RecordService=new GoodRecordServiceImpl();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String company=AdminSessionUtils.getSessionAdminUserCommpany(request);
		int userID=AdminSessionUtils.getSessionAdminUserID(request);
	 	Goods good=new Goods();
	 	Stock stock=new Stock();
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
					if(fieldName.equals("Goodname")){
						good.setGood_name(fieldValue);
					}else if(fieldName.equals("Brandname")){
						good.setBrand_name(fieldValue);
					}else if(fieldName.equals("category")){
						good.setC_id(categoryService.findCategoryByName(fieldValue,company));
					}else if(fieldName.equals("guige")){
						good.setGood_guige(fieldValue);
					}else if(fieldName.equals("description")){
						good.setGood_desc(fieldValue);
					}else if(fieldName.equals("isvisual")){
						if(fieldValue.equals("true")){
							good.setIsvisual(true);
						}else{
							good.setIsvisual(false);
						}
					}else if(fieldName.equals("price")){
						good.setGood_price(Double.parseDouble(fieldValue));
					}else if(fieldName.equals("Nowprice")){
						good.setGood_nowprice( Double.parseDouble(fieldValue));
					}else if(fieldName.equals("isSell")){
						if(fieldValue.equals("true")){
							good.setIs_sell(true);
						}else{
							good.setIs_sell(false);
						}
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
						fileName = ChineseToEnglishUtils.getUpEname(fileName);
						System.out.println("=============="+fileName);
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
				//通过C_id查询商品表中的相同类别中Good_id最大的记录；
				String good_id=goodService.findGoodIdByCId(good.getC_id(),company);
				//如果商品为该类别的第一个商品；
				if(good_id==null){
					good.setGood_id(NumStringAddUtil.StringAddString2(good.getC_id()));
				}else{
					good.setGood_id(NumStringAddUtil.numStringAdd2(good.getC_id(), good_id));
				}
				
				goodService.addGood(good,company);
				
				//添加库存中新商品信息
				stock.setGood_id(good.getGood_id());
				stock.setGood_name(good.getGood_name());
				stock.setQuantity(0);
				stock.setVquantity(0);
				stock.setRemark(good.getBrand_name());
				//向库存中加入新商品数据；
				stockService.insertStock(stock, company);
				
				//向商品价格、积分变化记录表中添加新商品原始价格、积分记录
				GoodsRecord goodRecord=new GoodsRecord();
				goodRecord.setRecord_id(UUIDUtil.getuuid());
				goodRecord.setGood_id(good.getGood_id());
				goodRecord.setGood_price(good.getGood_price());
				goodRecord.setGood_integration(good.getGood_nowprice());
				RecordService.addGoodRecord(goodRecord, company, userID);
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
