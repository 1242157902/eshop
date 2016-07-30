package com.eshop.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eshop.entrity.AdminUser;
import com.eshop.entrity.Stock;
import com.eshop.entrity.StockRecord;
import com.eshop.entrity.User;
import com.eshop.entrity.view.JsonEntity;
import com.eshop.entrity.view.VoStockEntity;
import com.eshop.service.StockRecordServiceImpl;
import com.eshop.service.StockServiceImpl;
import com.eshop.service.interf.StockRecordService;
import com.eshop.service.interf.StockService;
import com.eshop.utils.AdminSessionUtils;
import com.eshop.utils.DateUtil;
import com.eshop.utils.SessionUtils;



/**
 * 
 * <p>Title：        StockController<p>
 * <p>Description:  库存Controller，处理库存数据信息<p>
 * @date:           2015年11月1日上午11:55:53
 * @author:         ysl
 * @version         1.0
 */
@Controller
@RequestMapping("stock")
public class StockController {

	StockService stockService = new StockServiceImpl();
	StockRecordService stockRecordService = new StockRecordServiceImpl();
	// 商品修改提交json信息，响应json信息
	@RequestMapping(value = "findAllStock")
	public  void findAllStock(HttpServletRequest request,VoStockEntity vStock,HttpServletResponse response)  {
		List<Stock> stockList = null;
		JsonEntity jsonEntity = new JsonEntity(); 
		Map<String, Object> m = new HashMap<String, Object>();
	/*	String company = SessionUtils.getSessionUserCommpany(request);*/
		String company = AdminSessionUtils.getSessionAdminUserCommpany(request);
		if (company == null) {
			return ;
		}
		try{
			if (vStock!=null) {
				if (company!=null) {
					m.put("company", company);
				}
				if (vStock.getGood_id()!=null) {
					m.put("Good_id", vStock.getGood_id());
				}
				if (vStock.getGood_name()!=null) {
					m.put("Good_name", vStock.getGood_name());
				}
				if (vStock.getOrder()!=null) {
					m.put("order", vStock.getOrder());
				}
				if (vStock.getSort()!=null) {
					m.put("sort", vStock.getSort());
				}
			}
			stockList = stockService.findStockByPagination(vStock.getPage(), vStock.getRows(), m);
			 jsonEntity.setTotal(stockService.getTotal(m));
			 jsonEntity.setRows(JSONArray.fromObject(stockList).toString());
			String responseJson ="{\"total\":"+jsonEntity.getTotal()+" , \"rows\":"+JSONArray.fromObject(stockList).toString()+"}";
			 response.setContentType("text/json;charset=utf-8");
			 response.getWriter().write(responseJson);
		}catch(Exception e )
		{
			e.printStackTrace();
		} 
	}
	
	@RequestMapping(value = "updateStock")
	public void updateStock( HttpServletRequest request,VoStockEntity vStock,HttpServletResponse response)
	{
		Stock stock = null;
		Integer oldQuantity = null;
		Integer updateQuantity = null;
	/*	String company = SessionUtils.getSessionUserCommpany(request);*/
		String company = AdminSessionUtils.getSessionAdminUserCommpany(request);
		try
		{
			 if (vStock!=null&&vStock.getGood_id()!=null) {
				 
				 //先通过id查询出该商品的库存信息
				 stock = stockService.findStockById(vStock.getGood_id(),company);
				 oldQuantity = stock.getQuantity();
				 stock.setGood_name(vStock.getGood_name());
				 updateQuantity = vStock.getQuantity();
				 stock.setQuantity(updateQuantity);
				 stock.setVquantity(vStock.getVquantity());
				 stock.setRemark(vStock.getRemark());
				 //更新该商品
				 stockService.updateStock(stock,company);
				 
				 //插入库存记录
				 StockRecord stockRecord = new StockRecord();
				 stockRecord.setGood_name(stock.getGood_name());
				 stockRecord.setGood_id(stock.getGood_id());
				 stockRecord.setCreatetime(DateUtil.formatDate1(new Date()));
				 stockRecord.setQuantity(oldQuantity);
				 stockRecord.setUpdate_quantity(updateQuantity);
					HttpSession session=request.getSession();
					if (session!=null)
					{
						AdminUser user= (AdminUser) session.getAttribute("adminuser");
						if (user!=null) {
							stockRecord.setManager_id(user.getId());
							stockRecord.setManager_name(user.getName());
						}
					}
				 stockRecordService.insertStockRecord(stockRecord, company);
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
	
	
	
	//这个页面不需要，其他需要得
	@RequestMapping(value = "deleteStock")
	public void deleteStock(HttpServletRequest request,String  good_ids)
	{
		String company = SessionUtils.getSessionUserCommpany(request);
		System.out.println("*************");
	}
	
	@RequestMapping(value = "addStock")
	public void addStock(HttpServletRequest request,VoStockEntity vStock,HttpServletResponse response)
	{
		String company = SessionUtils.getSessionUserCommpany(request);
		try {
			response.setContentType("text/html;charset=utf-8");
			String str = "{\"status\":\"ok\" , \"message\":\"操作成功!\"}";
			response.getWriter().write(str);
		} catch (Exception e) {
			response.setContentType("text/html;charset=utf-8");
			String str = "{\"status\":\"fail\" , \"message\":\"操作失败!\"}";
			try {
				response.getWriter().write(str);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
	
}
