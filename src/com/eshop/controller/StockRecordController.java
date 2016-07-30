package com.eshop.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eshop.entrity.Goods;
import com.eshop.entrity.StockRecord;
import com.eshop.entrity.view.JsonEntity;
import com.eshop.entrity.view.VoStockRecordEntity;
import com.eshop.service.GoodServiceImpl;
import com.eshop.service.StockRecordServiceImpl;
import com.eshop.service.interf.GoodService;
import com.eshop.service.interf.StockRecordService;
import com.eshop.utils.AdminSessionUtils;
import com.eshop.utils.SessionUtils;

@Controller
@RequestMapping("stockrecord")
public class StockRecordController {
	
	StockRecordService  stockRecordService =new StockRecordServiceImpl();
	GoodService goodService = new GoodServiceImpl();
	// 商品修改提交json信息，响应json信息
	@RequestMapping(value = "findAllStockRecord")
	public  void findAllStock(HttpServletRequest request,VoStockRecordEntity  voStockRecordEntity,HttpServletResponse response)  {
		List<StockRecord> stockRecordList = null;
		JsonEntity jsonEntity = new JsonEntity(); 
		Map<String, Object> m = new HashMap<String, Object>();
		/*String company = SessionUtils.getSessionUserCommpany(request);*/
		String company = AdminSessionUtils.getSessionAdminUserCommpany(request);
		if (company == null) {
			return ;
		}
		try{
			if (voStockRecordEntity!=null) {
				if (company!=null) {
					m.put("company", company);
				}
				if (voStockRecordEntity.getGood_id()!=null) {
					m.put("Good_id", voStockRecordEntity.getGood_id());
				}
				if (voStockRecordEntity.getCreatetime()!=null) {
					m.put("createtime", voStockRecordEntity.getCreatetime());
				}
				if (voStockRecordEntity!=null) {
					m.put("endtime", voStockRecordEntity.getEndtime());
				}
				if (voStockRecordEntity.getGood_name()!=null) {
					m.put("Good_name", voStockRecordEntity.getGood_name());
				}
				if (voStockRecordEntity.getManager_name()!=null) {
					m.put("manager_name", voStockRecordEntity.getManager_name());
				}
				if (voStockRecordEntity.getOrder()!=null) {
					m.put("order", voStockRecordEntity.getOrder());
				}
				if (voStockRecordEntity.getSort()!=null) {
					m.put("sort", voStockRecordEntity.getSort());
				}
			}
			stockRecordList = stockRecordService.findStockRecordByPagination(voStockRecordEntity.getPage(), voStockRecordEntity.getRows(), m);
			for (StockRecord stockRecord : stockRecordList) {
				Goods good = goodService.findGoodById(stockRecord.getGood_id(), company);
				if (good!=null&&good.getGood_name()!=null) {
					stockRecord.setGood_name(good.getGood_name());
				}
			}
			jsonEntity.setTotal(stockRecordService.getTotal(m));
			 jsonEntity.setRows(JSONArray.fromObject(stockRecordList).toString());
			String responseJson ="{\"total\":"+jsonEntity.getTotal()+" , \"rows\":"+JSONArray.fromObject(stockRecordList).toString()+"}";
			 response.setContentType("text/json;charset=utf-8");
			 response.getWriter().write(responseJson);
		}catch(Exception e )
		{
			e.printStackTrace();
		} 
	}
}
