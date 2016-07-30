package com.eshop.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eshop.entrity.Goods;
import com.eshop.entrity.InStock;
import com.eshop.entrity.InStockDetail;
import com.eshop.entrity.Stock;
import com.eshop.entrity.view.JsonEntity;
import com.eshop.entrity.view.VoInStockEntity;
import com.eshop.service.GoodServiceImpl;
import com.eshop.service.InStockDetailServiceImpl;
import com.eshop.service.InStockServiceImpl;
import com.eshop.service.StockServiceImpl;
import com.eshop.service.interf.GoodService;
import com.eshop.service.interf.InStockDetailService;
import com.eshop.service.interf.InStockService;
import com.eshop.service.interf.StockService;
import com.eshop.utils.AdminSessionUtils;
import com.eshop.utils.SessionUtils;

 /**
  * 
  * <p>Title：        InStockController<p>
  * <p>Description:  <p>
  * @date:           2015年11月11日下午3:39:57
  * @author:         ysl
  * @version         1.0
  */
@Controller
@RequestMapping("instock")
public class InStockController {

	
	InStockDetailService inStockDetailService = new  InStockDetailServiceImpl();
	InStockService inStockService = new InStockServiceImpl();
	GoodService  goodService = new GoodServiceImpl();
	StockService  stockService = new StockServiceImpl();
	

	// 商品修改提交json信息，响应json信息
	@RequestMapping(value = "findAllInStock")
	public  void findAllStock(HttpServletRequest request, VoInStockEntity vInStock,HttpServletResponse response)  {
		
		List<InStock> inStockList = null;
		JsonEntity jsonEntity = new JsonEntity(); 
		String company = AdminSessionUtils.getSessionAdminUserCommpany(request);
		if (company == null) {
			return ;
		}
		Map<String, Object> m = new HashMap<String, Object>();
		try{
			if (vInStock!=null) {
				if (company!=null) {
					m.put("company", company);
				}
				m.put("status", "1");
				if (vInStock.getInstock_id()!=null) {
					m.put("instock_id", vInStock.getInstock_id());
				}
				if (vInStock.getInstocktime()!=null) {
					m.put("instocktime", vInStock.getInstocktime());
				}
				if (vInStock.getEndtime()!=null) {
					m.put("endtime", vInStock.getEndtime());
				}
				if (vInStock.getManager_name()!=null) {
					m.put("Manager_name", vInStock.getManager_name());
				}
		
				if (vInStock.getOrder()!=null) {
					m.put("order", vInStock.getOrder());
				}
				if (vInStock.getSort()!=null) {
					m.put("sort", vInStock.getSort());
				}
			}
			inStockList = inStockService.findInStockByPagination(vInStock.getPage(), vInStock.getRows(), m);
			 jsonEntity.setTotal(inStockService.getTotal(m));
			String responseJson ="{\"total\":"+jsonEntity.getTotal()+" , \"rows\":"+JSONArray.fromObject(inStockList).toString()+"}";
			 response.setContentType("text/json;charset=utf-8");
			 response.getWriter().write(responseJson);
		}catch(Exception e )
		{
			e.printStackTrace();
		} 
	}
	@RequestMapping(value = "findAllInStock1")
	public  void findAllStock1(HttpServletRequest request, VoInStockEntity vInStock,HttpServletResponse response)  {
		
		List<InStock> inStockList = null;
		JsonEntity jsonEntity = new JsonEntity(); 
		String company = AdminSessionUtils.getSessionAdminUserCommpany(request);
		if (company == null) {
			return ;
		}
		Map<String, Object> m = new HashMap<String, Object>();
		try{
			if (vInStock!=null) {
				if (company!=null) {
					m.put("company", company);
				}
				m.put("status", "0");
				if (vInStock.getInstock_id()!=null) {
					m.put("instock_id", vInStock.getInstock_id());
				}
				if (vInStock.getInstocktime()!=null) {
					m.put("instocktime", vInStock.getInstocktime());
				}
				if (vInStock.getEndtime()!=null) {
					m.put("endtime", vInStock.getEndtime());
				}
				if (vInStock.getManager_name()!=null) {
					m.put("Manager_name", vInStock.getManager_name());
				}
				
				if (vInStock.getOrder()!=null) {
					m.put("order", vInStock.getOrder());
				}
				if (vInStock.getSort()!=null) {
					m.put("sort", vInStock.getSort());
				}
			}
			inStockList = inStockService.findInStockByPagination(vInStock.getPage(), vInStock.getRows(), m);
			jsonEntity.setTotal(inStockService.getTotal(m));
			String responseJson ="{\"total\":"+jsonEntity.getTotal()+" , \"rows\":"+JSONArray.fromObject(inStockList).toString()+"}";
			response.setContentType("text/json;charset=utf-8");
			response.getWriter().write(responseJson);
		}catch(Exception e )
		{
			e.printStackTrace();
		} 
	}
	@RequestMapping(value = "searchInStockDetail")
	public  void searchInStockDetail(HttpServletRequest request, String  instock_ids,HttpServletResponse response)
	{
			List<List<InStockDetail>>  list =null;
			List<InStockDetail> inStockList = null;
			String company = AdminSessionUtils.getSessionAdminUserCommpany(request);
		 	 try
		 	 {
		 		 
		 		if (instock_ids!=null&&!"".equals(instock_ids))
				{
		 			list = new ArrayList<List<InStockDetail>>();
					inStockList = new ArrayList<InStockDetail>();
					 //1、将instock_ids分解成数组，
					String[]  instockIds = instock_ids.split(",");
					//2、根据instockIds查询该入库单下的所有入库详细
					for (String inStock_id : instockIds) 
					{
						
						List<InStockDetail>   inStockDetailList1 = inStockDetailService.findInStockDetailByInStockId(inStock_id, company);
						
						//加入商品名称
						for (InStockDetail inStockDetail : inStockDetailList1) 
						{
							Goods good = goodService.findGoodById(inStockDetail.getGood_id(), company);
							if (good!=null&&good.getGood_name()!=null) {
								inStockDetail.setGood_name(good.getGood_name());
							}
						}
						list.add(inStockDetailList1);
					}
					//3、将所有详细入库的单据重新加入到一个集合中，再将集合转化为json格式
					for (List<InStockDetail>  instockList : list) 
					{
						for (InStockDetail  inStockDetail : instockList) 
						{
							inStockList.add(inStockDetail);
						}
					}
				}
		 		
				String responseJson ="{\"total\":"+inStockList.size()+" , \"rows\":"+JSONArray.fromObject(inStockList).toString()+"}";
				 response.setContentType("text/json;charset=utf-8");
				 response.getWriter().write(responseJson);
		 	 }catch(Exception e )
		 	 {
		 		 e.printStackTrace();
		 	 }
	
	}
	
	
	@RequestMapping(value = "insertStock")
	public void insertStock(HttpServletRequest request,String  instock_ids,HttpServletResponse response)
	{
		List<Stock> stockList = null;
		List<InStockDetail> inStockDetailList = null;
		String company = AdminSessionUtils.getSessionAdminUserCommpany(request);
	 	 try
	 	 {
	 		 
	 		if (instock_ids!=null&&!"".equals(instock_ids))
			{
				 //1、将instock_ids分解成数组，
				String[]  inStockIds = instock_ids.split(",");
				//4、查找库中现有商品有哪些
				//stockList = stockService.findAllStock(company);
				for (String instock_id : inStockIds) 
				{
					//2、根据instock_id查询出该入库单中明细
					inStockDetailList = inStockDetailService.findInStockDetailByInStockId(instock_id, company);
					//3、判断入库单中商品是否在库中，若在，则将商品数量相加，否则插入库中
					for (InStockDetail  inStockDetail : inStockDetailList)
					{
						//4、查找库中现有商品有哪些
						stockList = stockService.findAllStock(company);
						boolean flag = true;	//标志位：表示库中是否已有该商品
						for (Stock stock : stockList)
						{
							if (stock.getGood_id().equals(inStockDetail.getGood_id())) 	//如果库中已有该商品，则数量相加
							{
								flag = false;		//已在库
								int stockQuantity = stock.getQuantity();
								int realQuantity = stockQuantity + inStockDetail.getGood_quantity();
								stock.setQuantity(realQuantity);
								stock.setVquantity(realQuantity);
								//更新库存
								stockService.updateStock(stock, company);
							}
						}
						if (flag)
						{
							Goods good = goodService.findGoodById(inStockDetail.getGood_id(), company);
							Stock newStock = new Stock();
							newStock.setGood_id(inStockDetail.getGood_id());
							newStock.setGood_name(good.getGood_name());
							newStock.setQuantity(inStockDetail.getGood_quantity());
							newStock.setVquantity(0);
							newStock.setRemark("礼品");
							//插入到库
							stockService.insertStock(newStock,company);
						}
					}
					//5、根据instock_id获取该入库单，修改入库单的状态
					InStock inStock = inStockService.findInStockById(instock_id, company);
					inStock.setStatus(1);
					inStockService.updateInStock(inStock,company);
				}
			}
	 	 }
	 	 catch(Exception e )
	 	 {
	 		 e.printStackTrace();
	 	 }
	}
	
}
