package com.eshop.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.eshop.entrity.Order;
import com.eshop.entrity.PerScore;
import com.eshop.entrity.RewardScore;
import com.eshop.entrity.view.JsonEntity;
import com.eshop.entrity.view.VoRewardScoreEntity;
import com.eshop.service.PerScoreServiceImpl;
import com.eshop.service.RewardScoreServiceImpl;
import com.eshop.service.interf.PerScoreService;
import com.eshop.service.interf.RewardScoreService;
import com.eshop.utils.AdminSessionUtils;
import com.eshop.utils.DateUtil;
import com.eshop.utils.ImportExeclUtil;
import com.eshop.utils.StringUtil;

/**
 * 
 * <p>Title：        RewardScoreController<p>
 * <p>Description:  奖励积分Controller<p>
 * @date:           2015年12月4日下午9:05:00
 * @author:         ysl
 * @version         1.0
 */
@Controller
@RequestMapping("rewardscore")
public class RewardScoreController {

	RewardScoreService rs = new RewardScoreServiceImpl();
	PerScoreService perScoreService = new PerScoreServiceImpl();
	
	@RequestMapping(value = "importRewardScore")
	public  String  importRewardScore(HttpServletRequest request,  @RequestParam("file") CommonsMultipartFile mFile,HttpServletResponse response)  
	{
		boolean  flag = true ;			//默认插入
		Float percent = null;
		Order order = null;
		String company = AdminSessionUtils.getSessionAdminUserCommpany(request);
		if (company != null) {
			 
	
		try {
			if (mFile!=null)
			{
			       List<List<String>> list = ImportExeclUtil.getInstance().readExcelData(mFile.getInputStream(), mFile.getOriginalFilename());
			       
			        if (list != null)  
			        {  
			  
			            for (int i = 1; i < list.size(); i++)  
			            {  
			            	RewardScore oldRewardScore = null;
				    		RewardScore rewardScore = new RewardScore();
				    		PerScore ps = perScoreService.getImportPercent("1", company);
				    		if (ps !=null)
				    		{
				    			percent = ps.getPercentage();
							}else
							{
								percent = 1.0f;
							}
				    		//1、查找数据库中现有的工号有哪些
				    		//2、如果数据库中已经存在该工号，则将奖励加分相加
				    		//3、如果数据库中不存在该工号，则插入该工号对应的实体
						                List<String> cellList = list.get(i);  
						                for (int j = 0; j < cellList.size(); j++)  
						                {  
						                    // System.out.print("    第" + (j + 1) + "列值：");  
						                    //System.out.print("    " + cellList.get(j));  
						                    if (j==0)
							    			{
							    				String nick_name = StringUtil.truncateAndTrim0(StringUtil.jishuDeleteE(cellList.get(j)), ".");
							    				rewardScore.setNick_name(nick_name);
							    				oldRewardScore  =  rs.getRewardScoreBynickname(nick_name, company);
							    				order = rs.getOrderByNickName(nick_name, company);
							    				if (oldRewardScore != null)
							    				{
							    					flag = false ;
												} else
												{
													flag = true;
												}
											}
							    			else if(j == 1)
											{
							    				int  rewards = new Integer(StringUtil.truncateAndTrim0(cellList.get(j), "."));
							    				Float perReward = rewards*percent;
							    				rewards = perReward.intValue() ;
							    				rewardScore.setScore(rewards);
												rewardScore.setReward_score(rewards);
											}
						  
						                }  
						             
			                //System.out.println();  
			            	if (flag) //插入
			    			{
			            		rewardScore.setImport_time(DateUtil.formatDate1(new Date()));
			            		if (order!=null) 
				    			{
			            			rewardScore.setName(order.getConsignee_name());
				            		rewardScore.setDepartment(order.getConsignee_address());
				            		rewardScore.setDevice_number(order.getConsignee_phone());
								}
								rs.insertRewardScore(rewardScore, company);
							}
				    		else //更新
				    		{
				    			int oldMonth = DateUtil.strDateToDate(oldRewardScore.getImport_time()).getMonth();
				    			Date date = new Date();
				    			int newMonth = date.getMonth();
				    			//System.out.println("##"+oldRewardScore.getScore());
				    			if (oldMonth == newMonth&&oldRewardScore.getScore()!=0) 
				    			{
				    				 request.setAttribute("message", "这个月已经导入过一次了！");
									 return "/message";
								}
				    			if (oldRewardScore.getImport_time()!=null) {
				    				oldRewardScore.setLast_time(oldRewardScore.getImport_time());
								}
				    			oldRewardScore.setImport_time(DateUtil.formatDate1(date));
				    			oldRewardScore.setBefore_rscore(oldRewardScore.getReward_score());
				    			if (order!=null) 
				    			{
				    				oldRewardScore.setName(order.getConsignee_name());
				    				oldRewardScore.setDepartment(order.getConsignee_address());
				    				oldRewardScore.setDevice_number(order.getConsignee_phone());
								}
				    			int oldScore = oldRewardScore.getReward_score();
				    			int newScore = oldScore + rewardScore.getReward_score();
				    			oldRewardScore.setReward_score(newScore);
				    			oldRewardScore.setScore(rewardScore.getReward_score());
				    			
				    			rs.update(oldRewardScore, company);
				    		}
			            }  
			  
			        }  
			        else
			        {
			        	 request.setAttribute("message", "文件格式不正确！文件格式应为第一列为工号，第二列为奖励积分的Excel表格！");
						 request.setAttribute("flag", "1");
						 return "/message";
			        }
			}
		}  catch (Exception e) {
			 request.setAttribute("message", "文件格式不正确！文件格式应为第一列为工号，第二列为奖励积分的Excel表格！");
			 request.setAttribute("flag", "1");
			 return "/message";
		}
		}	
		return "/managers/score/importrewardinternal";
	}
	
	@RequestMapping(value = "getImportData")
	public  void getImportDataByPanigation(HttpServletRequest request,  VoRewardScoreEntity voRewardScoreEntity,HttpServletResponse response)  
	{
		List<RewardScore> rewardScoreList = null;
		JsonEntity jsonEntity = new JsonEntity(); 
		Map<String, Object> m = new HashMap<String, Object>();
	/*	String company = SessionUtils.getSessionUserCommpany(request);*/
		String company = AdminSessionUtils.getSessionAdminUserCommpany(request);
		if (company == null) {
			return ;
		}
		try{
			if (voRewardScoreEntity!=null) {
				if (company!=null) {
					m.put("company", company);
				}
				 
				if (voRewardScoreEntity.getOrder()!=null) {
					m.put("order", voRewardScoreEntity.getOrder());
				}
				if (voRewardScoreEntity.getSort()!=null) {
					m.put("sort", voRewardScoreEntity.getSort());
				}
			}
			rewardScoreList =  rs .findStockByPagination(voRewardScoreEntity.getPage(), voRewardScoreEntity.getRows(), m);
			 jsonEntity.setTotal( rs .getTotal(m));
			 jsonEntity.setRows(JSONArray.fromObject(rewardScoreList).toString());
			String responseJson ="{\"total\":"+jsonEntity.getTotal()+" , \"rows\":"+JSONArray.fromObject(rewardScoreList).toString()+"}";
			 response.setContentType("text/json;charset=utf-8");
			 response.getWriter().write(responseJson);
		}catch(Exception e )
		{
			e.printStackTrace();
		} 
	}
	
	@RequestMapping(value = "scoreGoBack")
	public  void scoreGoBack(HttpServletRequest request,  String goBack,HttpServletResponse response)  
	{
		String company = AdminSessionUtils.getSessionAdminUserCommpany(request);
		//1、从数据库中查询出所有的记录
		try {
			List<RewardScore> rewardScoreList = rs.getAllImportScore(company);
			if(rewardScoreList!=null)
			{
				//2、再将所有记录中score不为空的，情况下， 将导前积分赋值给导后积分就行了
				for (RewardScore rewardScore : rewardScoreList) 
				{
					if (rewardScore!=null&&rewardScore.getBefore_rscore()!=0) 
					{
						int before_score = rewardScore.getBefore_rscore();
						String last_time	 = rewardScore.getLast_time();
						rewardScore.setScore(0);
						rewardScore.setReward_score(before_score);
						rewardScore.setImport_time(last_time);
						//3、更新记录表
						rs.update(rewardScore, company);
					}  
				}
			}
		
		} catch (SQLException e) {
		 
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
	
	@RequestMapping(value = "updateRewardScore")
	public void updateRewardScore( HttpServletRequest request,VoRewardScoreEntity voRewardScoreEntity,HttpServletResponse response)
	{
		RewardScore rewardScore = null;
		Integer oldQuantity = null;
		Integer updateQuantity = null;
		String company = AdminSessionUtils.getSessionAdminUserCommpany(request);
		try
		{
			 if (voRewardScoreEntity!=null&&voRewardScoreEntity.getNick_name()!=null) {
				 
				 //先通过id查询出该商品的库存信息
				 rewardScore = rs.getScoreBynickname(voRewardScoreEntity.getNick_name(), company);
				 rewardScore.setReward_score(voRewardScoreEntity.getReward_score());
				 //更新该商品
				 rs.update(rewardScore, company);
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
