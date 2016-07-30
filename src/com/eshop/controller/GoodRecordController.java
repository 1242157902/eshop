package com.eshop.controller;

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
import com.eshop.entrity.Goods;
import com.eshop.entrity.GoodsRecord;
import com.eshop.entrity.view.JsonEntity;
import com.eshop.entrity.view.VGoodRecordEntity;
import com.eshop.service.GoodRecordServiceImpl;
import com.eshop.service.GoodServiceImpl;
import com.eshop.service.interf.GoodRecordService;
import com.eshop.service.interf.GoodService;
import com.eshop.utils.AdminSessionUtils;
import com.eshop.utils.DateUtil;
import com.eshop.utils.SerialNumber;
import com.eshop.utils.SessionUtils;
import com.eshop.utils.UUIDUtil;


/**
 * 记录商品价格和积分变动的Controller类
 * @author Jane
 *
 */

@Controller
@RequestMapping("goodRecord")
public class GoodRecordController {
	
	GoodRecordService RecordService=new GoodRecordServiceImpl();
	GoodService goodService=new GoodServiceImpl();
	
	//查询商品记录
	@RequestMapping(value ="queryGoodRecord")
	public void queryGoodRecord(HttpServletRequest request,HttpServletResponse  response,VGoodRecordEntity vGoodRecord){
	List<GoodsRecord> RecordList = null;
	JsonEntity jsonEntity = new JsonEntity(); 
	Map<String, Object> m = new HashMap<String, Object>();
	/*HttpSession session=request.getSession();
	AdminUser user= (AdminUser) session.getAttribute("user");*/
	String company=AdminSessionUtils.getSessionAdminUserCommpany(request);
	try{
		if (vGoodRecord!=null) {
			if (vGoodRecord.getRecord_id()!=null) {
				m.put("record_id", vGoodRecord.getRecord_id());
			}
			if (vGoodRecord.getGoodname()!=null) {
				m.put("Good_name", vGoodRecord.getGoodname());
			}
			if (vGoodRecord.getOrder()!=null) {
				m.put("order", vGoodRecord.getOrder());
			}
			if (vGoodRecord.getSort()!=null) {
				m.put("sort", vGoodRecord.getSort());
			}
		}
		RecordList = RecordService.findgoodRecordByPagination(vGoodRecord.getPage(), vGoodRecord.getRows(), m,company);
		 jsonEntity.setTotal(RecordService.getTotal(m,company));
		 jsonEntity.setRows(JSONArray.fromObject(RecordList).toString());
		 String responseJson ="{\"total\":"+jsonEntity.getTotal()+" , \"rows\":"+JSONArray.fromObject(RecordList).toString()+"}";
		 response.setContentType("text/json;charset=utf-8");
		 response.getWriter().write(responseJson);
	}catch(Exception e )
	{
		e.printStackTrace();
	} 
 }
	
	//新增商品价格和积分变动记录
	@RequestMapping(value="addRecord")
	public void addRecord(HttpServletResponse response,HttpServletRequest request,VGoodRecordEntity vGoodRecord) throws Exception{
		String company = SessionUtils.getSessionUserCommpany(request);
		int userID=AdminSessionUtils.getSessionAdminUserID(request);
		GoodsRecord goodRecord=null;
		if(vGoodRecord!=null){
			
			 goodRecord=new GoodsRecord();
			 Goods good=new Goods();
			 goodRecord.setRecord_id(UUIDUtil.getuuid());
			 goodRecord.setGoodname(vGoodRecord.getGoodname());
			 goodRecord.setGood_id(vGoodRecord.getGood_id());
			 goodRecord.setGood_price(vGoodRecord.getGood_price());
			 goodRecord.setGood_integration(vGoodRecord.getGood_integration());
			 good.setGood_id(vGoodRecord.getGood_id());
			 good.setGood_price(vGoodRecord.getGood_price());
			 good.setGood_nowprice(vGoodRecord.getGood_integration());
			 RecordService.addGoodRecord(goodRecord,company,userID);
			 //更新商品表中的价格和积分信息；
			 goodService.updateGoodPrice(good, company);
		}
		}
	}



