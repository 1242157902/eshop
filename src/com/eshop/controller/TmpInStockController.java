package com.eshop.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eshop.entrity.InStock;
import com.eshop.entrity.InStockDetail;
import com.eshop.entrity.TempInStock;
import com.eshop.entrity.view.VoTmpInStock;
import com.eshop.service.InStockDetailServiceImpl;
import com.eshop.service.InStockServiceImpl;
import com.eshop.service.TmpInStockDetailServiceImpl;
import com.eshop.service.interf.InStockDetailService;
import com.eshop.service.interf.InStockService;
import com.eshop.service.interf.TmpInStockService;
import com.eshop.utils.AdminSessionUtils;
import com.eshop.utils.DateUtil;
import com.eshop.utils.SequenceUtils;
import com.eshop.utils.SerialNumber;
import com.eshop.utils.SessionUtils;
import com.eshop.utils.UUIDUtil;

/**
 * 
 * <p>Title：        TmpInStockController<p>
 * <p>Description:临时表的处理  <p>
 * @date:           2015年11月10日下午7:02:04
 * @author:         ysl
 * @version         1.0
 */
@Controller
@RequestMapping("tmpInStock")
public class TmpInStockController {

	TmpInStockService tmpInStockService = new TmpInStockDetailServiceImpl();
	 InStockDetailService   inStockDetailService  = new  InStockDetailServiceImpl();
	 InStockService  inStockService = new InStockServiceImpl();
	
	@RequestMapping(value = "insertInStock")
	public void insertInStock( HttpServletRequest request,String  tmp_in_ids, HttpServletResponse response)
	{
		TempInStock tempInStock = null;
		int		inQuantity = 0;
		double total = 0;
		/*String company = SessionUtils.getSessionUserCommpany(request);*/
		String company = AdminSessionUtils.getSessionAdminUserCommpany(request);
		try {
			
			if (tmp_in_ids!=null&&!"".equals(tmp_in_ids))
			{
				//1、建立入库单，及入库单号
				String[]  purchaseids = tmp_in_ids.split(",");
				InStock inStock = new InStock();
				//String in_id = UUIDUtil.getuuid();		//入库单号
				String in_id =  "ES"+AdminSessionUtils.getSessionAdminPhone(request).toString()+SequenceUtils.getInstance().getNextId("ES", company) ;		//入库单号
				 for (String  purchase_id : purchaseids)
				 {
					 
					//2、根据id查询出该入库明细单,并计算总数及总价
					 tempInStock = tmpInStockService.findTmpInStockById(purchase_id, company);
					 if (tempInStock!=null) 
					 {
						 inQuantity =inQuantity +  tempInStock.getGood_quantity();
						 total = total + tempInStock.getSubtotal();
						 //3、生成入库明细，请将入库单号填写进去
						 InStockDetail  inStockDetail = new InStockDetail();
						 inStockDetail.setInstockitemid(tempInStock.getTmp_in_id());
						 inStockDetail.setGood_id(tempInStock.getGood_id());
						 inStockDetail.setGood_price(tempInStock.getGood_price());
						 inStockDetail.setGood_quantity(tempInStock.getGood_quantity());
						 inStockDetail.setSubtotal(tempInStock.getSubtotal());
						 inStockDetail.setInstock_id(in_id);
						 inStockDetailService.insertInStockDetail(inStockDetail,company);
					}
				}
				 inStock.setInstock_id(in_id);
				 inStock.setQuantity(inQuantity);
				 inStock.setInstock_total(total);
				 inStock.setInstocktime(DateUtil.formatDate1(new Date()));
				 inStock.setStatus(0);
				 inStock.setManager_id(tempInStock.getUser_id());
				 inStock.setManager_name(tempInStock.getManager_name());
				 //4、插入到入库表中
				 inStockService.insertPurchase(inStock, company);
				 //5、删除临时表中的数据
				 tmpInStockService.deleteTmpInStock(company);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "updateTmpInStock")
	public void updateTmpInStock(VoTmpInStock voTmpInStock,HttpServletResponse response,HttpServletRequest request)
	{
		TempInStock tempInStock=null;
		/*String company = SessionUtils.getSessionUserCommpany(request);*/
		String company = AdminSessionUtils.getSessionAdminUserCommpany(request);
		try
		{
			 if (voTmpInStock!=null&&voTmpInStock.getTmp_in_id()!=null) {
				 
				 //先通过id查询出该商品的信息
				 tempInStock = tmpInStockService.findTmpInStockById(voTmpInStock.getTmp_in_id(), company);
				 int quantity = voTmpInStock.getGood_quantity();
				 tempInStock.setGood_quantity(quantity);
				 double subtotal = quantity * tempInStock.getGood_price();
				 tempInStock.setSubtotal(subtotal);
				 //更新商品信息
				 tmpInStockService.updateTempInStock(tempInStock, company);
				 response.setContentType("text/html;charset=utf-8");
				String str = "{\"status\":\"ok\" , \"message\":\"操作成功!\"}";
				response.getWriter().write(str);
			}
		}catch(Exception e )
		{
			e.printStackTrace();
			 response.setContentType("text/html;charset=utf-8");
			String str = "{\"status\":\"fail\" , \"message\":\"操作失败!\"}";
			try {
				response.getWriter().write(str);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
	}
}
