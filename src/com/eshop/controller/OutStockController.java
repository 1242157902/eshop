package com.eshop.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eshop.entrity.Order;
import com.eshop.entrity.OutStock;
import com.eshop.entrity.Stock;
import com.eshop.entrity.view.JsonEntity;
import com.eshop.entrity.view.VoOutStockEntity;
import com.eshop.entrity.view.VoStockEntity;
import com.eshop.service.OrderDetailServiceImpl;
import com.eshop.service.OrderServiceImpl;
import com.eshop.service.OutStockServiceImpl;
import com.eshop.service.StockServiceImpl;
import com.eshop.service.interf.OrderDetailService;
import com.eshop.service.interf.OrderService;
import com.eshop.service.interf.OutStockService;
import com.eshop.service.interf.StockService;
import com.eshop.utils.AdminSessionUtils;
import com.eshop.utils.SessionUtils;


 /**
  * 
  * <p>Title：        OutStockController<p>
  * <p>Description: 出库Controller <p>
  * @date:           2015年11月4日下午4:22:59
  * @author:         ysl
  * @version         1.0
  */
@Controller
@RequestMapping("outStock")
public class OutStockController {

	OutStockService outStockService = new OutStockServiceImpl();
	StockService stockService = new StockServiceImpl();
	OrderService   orderService  = new OrderServiceImpl();
	OrderDetailService orderDetailService  = new OrderDetailServiceImpl();
	
	
	// 商品修改提交json信息，响应json信息
	@RequestMapping(value = "findAllOutStock")
	public  void findAllOutStock( HttpServletRequest request,VoOutStockEntity vOutStock,HttpServletResponse response)  {
		
		List<OutStock> outStockList = null;
		JsonEntity jsonEntity = new JsonEntity(); 
		Map<String, Object> m = new HashMap<String, Object>();
		/*String company = SessionUtils.getSessionUserCommpany(request);*/
		String company = AdminSessionUtils.getSessionAdminUserCommpany(request);
		try{
			if (vOutStock!=null) {
				if (company!=null) {
					m.put("company", company);
				}
				if (vOutStock.getOut_id()!=null) {
					m.put("Out_id", vOutStock.getOut_id());
				}
				if (vOutStock.getOrder_id()!=null) {
					m.put("Order_id", vOutStock.getOrder_id());
				}
				if (vOutStock.getOuttime()!=null) {
					m.put("outtime", vOutStock.getOuttime());
				}
				if (vOutStock.getEndtime()!=null) {
					m.put("endtime", vOutStock.getEndtime());
				}
				if (vOutStock.getManager_name()!=null) {
					m.put("manager_name", vOutStock.getManager_name());
				}
				if (vOutStock.getBackfill_id()!=null) {
					m.put("backfill_id", vOutStock.getBackfill_id());
				}
				if (vOutStock.getGood_id()!=null) {
					m.put("Good_id", vOutStock.getGood_id());
				}
				if (vOutStock.getGood_name()!=null) {
					m.put("Good_name", vOutStock.getGood_name());
				}
				if (vOutStock.getOrder()!=null) {
					m.put("order", vOutStock.getOrder());
				}
				if (vOutStock.getSort()!=null) {
					m.put("sort", vOutStock.getSort());
				}
			}
			outStockList = outStockService.findOutStockByPagination(vOutStock.getPage(), vOutStock.getRows(), m);
			 jsonEntity.setTotal(outStockService.getTotal(m));
			String responseJson ="{\"total\":"+jsonEntity.getTotal()+" , \"rows\":"+JSONArray.fromObject(outStockList).toString()+"}";
			 response.setContentType("text/json;charset=utf-8");
			 response.getWriter().write(responseJson);
		}catch(Exception e )
		{
			e.printStackTrace();
		} 
	}
	
	
	@RequestMapping(value = "updateOutStock")
	public void updateOutStock(HttpServletRequest request,  VoOutStockEntity vOutStock,HttpServletResponse response)
	{
		OutStock outStock = null;
		/*String company = SessionUtils.getSessionUserCommpany(request);*/
		String company = AdminSessionUtils.getSessionAdminUserCommpany(request);
		try
		{
			 if (vOutStock!=null&&vOutStock.getGood_id()!=null) {
				 
				 //先通过id查询出该商品的库存信息
				 outStock = outStockService.findOutStockById(vOutStock.getOut_id(),company);
				 outStock.setGood_id(vOutStock.getGood_id());
				 outStock.setGood_name(vOutStock.getGood_name());
				 outStock.setQuantity(vOutStock.getQuantity());		//最重要的是这个
				 outStock.setOuttime(vOutStock.getOuttime());
				 outStock.setManager_name(vOutStock.getManager_name());
				 outStock.setBackfill_id(vOutStock.getBackfill_id());
				 //更新该商品
				 outStockService.updateOutStock(outStock,company);
				 //回填以后更新订单的状态为出交易成功
				Order order =  orderService.findOrderById(outStock.getOrder_id(), company);
				 order.setStatus(4);
				 orderService.updateOrder(order, company);
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
	@RequestMapping(value = "stockOut")
	public void stockOut(HttpServletRequest request,String  out_id,HttpServletResponse response)
	{
		List<Stock> stockList = null;
		OutStock outStock = null; 
		int flag = 0;
		/*String company = SessionUtils.getSessionUserCommpany(request);*/
		String company = AdminSessionUtils.getSessionAdminUserCommpany(request);
		try{
			if (out_id !=null )
			{
				//1、查找出要出库的商品是什么？
				outStock =  outStockService.findOutStockById(out_id,company);
				//查找库中现存的商品有哪些
				stockList = stockService.findAllStock(company);
				for (Stock stock : stockList) 
				{
					if (stock.getGood_id().equals(outStock.getGood_id()))
					{
						int stockQuantity = stock.getQuantity();
						int outStockQuantity = outStock.getQuantity();
						if(stockQuantity>=outStockQuantity)
						{
							flag = 1;
							stockQuantity = stockQuantity - outStockQuantity;
							stock.setQuantity(stockQuantity);
							stockService.updateStock(stock,company);		//更新库存
							
							//5、修改出库单的状态
							outStock.setStatus(1);
							outStockService.updateOutStock(outStock,company);
						}else{
							flag = 2;
						}
					}
				}
			
				switch (flag) {
				case 0:
					response.setContentType("text/html;charset=utf-8");
					String str = "{\"status\":\"ok\" , \"message\":\"库中无该类商品，请采购!\"}";
					response.getWriter().write(str);
					break;
				case 1:
					response.setContentType("text/html;charset=utf-8");
					String str1 = "{\"status\":\"ok\" , \"message\":\"操作成功!\"}";
					response.getWriter().write(str1);
					break;
				case 2:
					response.setContentType("text/html;charset=utf-8");
					String str2 = "{\"status\":\"ok\" , \"message\":\"库存不够，请采购!\"}";
					response.getWriter().write(str2);
					break;

				default:
					break;
				}
			}
			
		}catch(Exception e )
		{
			response.setContentType("text/html;charset=utf-8");
			String str = "{\"status\":\"fail\" , \"message\":\"操作失败!\"}";
			try {
				response.getWriter().write(str);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		
		
		
		/*		List<Stock> stockList = null;
 	 OutStock outStock = null;
	 	 
	 	 try
	 	 {
	 		 
	 		if (out_ids!=null&&!"".equals(out_ids))
			{
				 //1、将in_ids分解成数组，
				String[]  outIds = out_ids.split(",");
				//2、查找库中现有商品有哪些
				stockList = stockService.findAllStock();
				for (String out_id : outIds) 
				{
					//3、通过out_id（主键）查找相应的出库单
					outStock =  outStockService.findOutStockById(out_id);
					boolean flag = true;
					//4、判断出库单中商品是否在库中，若在， 并且库中商品数量大于等于要出库的商品数量，则出库，否则，不出库
					for (Stock stock : stockList)
					{
						if (stock.getGood_id().equals(outStock.getGood_id())) 
						{
							
							int stockQuantity = stock.getQuantity();
							int outStockQuantity = outStock.getQuantity();
							if(stockQuantity>=outStockQuantity)
							{
								flag = false;
								stockQuantity = stockQuantity - outStockQuantity;
								stock.setQuantity(stockQuantity);
								stockService.updateStock(stock);		//更新库存
							}
						}
				 
					}
					if (flag)
					{
						Stock newStock = new Stock();
						newStock.setGood_id(outStock.getGood_id());
						newStock.setGood_name(outStock.getGood_name());
						newStock.setQuantity(outStock.getQuantity());
						newStock.setVquantity(0);
						newStock.setRemark("礼品");
						//插入到库
						stockService.insertStock(newStock);
					}
					//5、修改入库单的状态
					outStock.setStatus(1);
					outStockService.updateOutStock(outStock);
				}
			}
	 	 }
	 	 catch(Exception e )
	 	 {
	 		 e.printStackTrace();
	 	 }*/
	}
	 
	/*
	@RequestMapping(value = "addStock")
	public void addStock(VoStockEntity vStock,HttpServletResponse response)
	{
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
	}*/
	
}
