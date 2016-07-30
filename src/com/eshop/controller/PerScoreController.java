package com.eshop.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eshop.entrity.Goods;
import com.eshop.entrity.GoodsRecord;
import com.eshop.entrity.PerScore;
import com.eshop.entrity.view.JsonEntity;
import com.eshop.entrity.view.VPerScoreEntity;
import com.eshop.service.PerScoreServiceImpl;
import com.eshop.service.interf.PerScoreService;
import com.eshop.utils.AdminSessionUtils;
import com.eshop.utils.SessionUtils;
import com.eshop.utils.UUIDUtil;


import com.eshop.service.PerScoreServiceImpl;
import com.eshop.service.interf.PerScoreService;

/**
 * 管理积分奖励百分比Controller
 * @author  JYM
 * 2015-12-3 下午9:15:23
 */
@Controller
@RequestMapping("perscore")
public class PerScoreController {
	PerScoreService perService=new PerScoreServiceImpl();
	//增加比例信息
	@RequestMapping(value ="addPerscore")
	public void addPerscore(HttpServletResponse response,HttpServletRequest request,VPerScoreEntity vPerScore){
		String company = AdminSessionUtils.getSessionAdminUserCommpany(request);
		int userID=AdminSessionUtils.getSessionAdminUserID(request);
		PerScore perscore=null;
		if(vPerScore!=null){
			
			 perscore=new PerScore();
			 perscore.setPercentage(vPerScore.getPercentage());
			 perscore.setStatus(1);
			 perscore.setCreatetime(new Date());
			 //更新比例记录信息的状态为0；
			 perService.updatePerScore(company);
			 //新添信息
			 perService.addPerScore(perscore,company,userID);
		}
	}
	
	
	//查询比例信息
	@RequestMapping(value ="queryPerscore")
	public void queryPerscore(HttpServletRequest request,HttpServletResponse  response,VPerScoreEntity vPerScore){
		List<PerScore> RecordList = null;
		JsonEntity jsonEntity = new JsonEntity(); 
		Map<String, Object> m = new HashMap<String, Object>();
		String company=AdminSessionUtils.getSessionAdminUserCommpany(request);
		try{
			if (vPerScore!=null) {
				if (vPerScore.getCreatetime()!=null) {
					m.put("createtime", vPerScore.getCreatetime());
				}
				if (vPerScore.getOrder()!=null) {
					m.put("order", vPerScore.getOrder());
				}
				if (vPerScore.getSort()!=null) {
					m.put("sort", vPerScore.getSort());
				}
			}
			 RecordList = perService.findperScoreByPagination(vPerScore.getPage(), vPerScore.getRows(), m,company);
			 jsonEntity.setTotal(perService.getTotal(m,company));
			 jsonEntity.setRows(JSONArray.fromObject(RecordList).toString());
			 String responseJson ="{\"total\":"+jsonEntity.getTotal()+" , \"rows\":"+JSONArray.fromObject(RecordList).toString()+"}";
			 response.setContentType("text/json;charset=utf-8");
			 response.getWriter().write(responseJson);
		}catch(Exception e )
		{
			e.printStackTrace();
		} 
	}
}
