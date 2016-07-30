/**
 * 
 */
package com.eshop.controller;


import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.sf.json.JSONArray;

import org.apache.commons.io.FileUtils;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.eshop.entrity.AdminUser;
import com.eshop.entrity.Advertise;
import com.eshop.entrity.AdvertiseList;
import com.eshop.entrity.User;
import com.eshop.entrity.view.JsonEntity;
import com.eshop.entrity.view.VAdvertiseEntity;
import com.eshop.entrity.view.VTmpAdvListEntity;
import com.eshop.service.AdvertiseServiceImpl;


import com.eshop.service.interf.AdvertiseService;
import com.eshop.utils.AdminSessionUtils;
import com.eshop.utils.DateUtil;
import com.eshop.utils.SessionUtils;


/**
 * yy 2015 2015年11月12日 下午9:26:07
 *
 */

@Controller
@RequestMapping("advertise")
public class AdvertiseController{
	AdvertiseService  advertiseService=new AdvertiseServiceImpl();
	
	@RequestMapping(value ="UploadImg")
	public String UploadImg(@RequestParam MultipartFile[] img,HttpServletRequest request){
		HttpSession session=request.getSession();
		AdminUser user= (AdminUser) session.getAttribute("adminuser");
		String company=null;
		if(user != null)
		{
			company =user.getCompany();
		}
	 	Advertise advertise=new Advertise();
	 	advertise.setOperator(user.getId());
	 	advertise.setOperdate(DateUtil.formatDate1(new Date()));	 	
		
		for(MultipartFile myfile : img){   
            if(!myfile.isEmpty()){   
         
                String realPath = request.getSession().getServletContext().getRealPath("/images/advertise");   
                //这里不必处理IO流关闭的问题，因为FileUtils.copyInputStreamToFile()方法内部会自动把用到的IO流关掉，我是看它的源码才知道的   
                String fileName=myfile.getOriginalFilename();
                int rand=new Random().nextInt(10);
                String prefix=fileName.substring(fileName.lastIndexOf(".")+1);
        	    String savefilename ="A_"+DateUtil.formatDate2(new Date())+"_"+user.getId()+"_"+rand+"."+prefix;
        	 	
                advertise.setName(savefilename);
                try {
					FileUtils.copyInputStreamToFile(myfile.getInputStream(), new File(realPath, savefilename));
					advertiseService.addAdvertise(advertise,company);
                } catch (IOException e) {
					return "/managers/advertise/advermodify";
				}   
            }   
        }   
		return "/managers/advertise/contentList";
		
	}
	@RequestMapping(value ="queryPic")
	public void queryPic(HttpServletRequest request,HttpServletResponse response){
		List<Advertise> adList=null;
		JsonEntity jsonEntity = new JsonEntity(); 
		
		HttpSession session=request.getSession();
		AdminUser user= (AdminUser) session.getAttribute("adminuser");
		String company=null;
		if(user != null)
		{
			company =user.getCompany();
		}
		try{
			
			adList = advertiseService.queryPic(company);
			 jsonEntity.setTotal(advertiseService.getTotal(company));
			 jsonEntity.setRows(JSONArray.fromObject(adList).toString());
			String responseJson ="{\"total\":"+jsonEntity.getTotal()+" , \"rows\":"+JSONArray.fromObject(adList).toString()+"}";
			 response.setContentType("text/json;charset=utf-8");
			 response.getWriter().write(responseJson);
		}catch(Exception e )
		{
			e.printStackTrace();
		} 
	}
	
	
	
	@RequestMapping(value="insertAdvList")
	public void insertAdvList(String ids,HttpServletResponse response,HttpServletRequest request){
		HttpSession session=request.getSession();
		AdminUser user= (AdminUser) session.getAttribute("adminuser");
		String company = null;
		if(user != null)
		{
			company =user.getCompany();
		}
		try {
			advertiseService.insertAdvList(ids,company,user.getId());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="queryAdvList")
	public void  queryAdvList(HttpServletRequest request,HttpServletResponse response){
		List<AdvertiseList> adList=null;
		JsonEntity jsonEntity = new JsonEntity(); 
		Map<String, Object> m = new HashMap<String, Object>();
		HttpSession session=request.getSession();
		AdminUser user= (AdminUser) session.getAttribute("adminuser");
		String company=null;
		if(user != null)
		{
			company =user.getCompany();
		}
		try{
			
			adList = advertiseService.queryAdvList(user.getId(),company);
			 
			jsonEntity.setRows(JSONArray.fromObject(adList).toString());
			String responseJson ="{\"rows\":"+JSONArray.fromObject(adList).toString()+"}";
			 response.setContentType("text/json;charset=utf-8");
			 response.getWriter().write(responseJson);
		}catch(Exception e )
		{
			e.printStackTrace();
		} 
	}
	
	@RequestMapping(value="finishAdvList")
	public void finishAdvList(String id,HttpServletRequest request,HttpServletResponse response){
		HttpSession session=request.getSession();
		AdminUser user= (AdminUser) session.getAttribute("adminuser");
		String company = null;
		if(user != null)
		{
			company =user.getCompany();
		}
		AdvertiseList advertiseList=null;
		try {
			advertiseList=advertiseService.queryTmpAdvList(id,company);
			advertiseList.setCreatetime(DateUtil.formatDate1(new Date()));
			advertiseService.finishAdvList(advertiseList,company);
			advertiseService.deleteTmpAdvList(id,company);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="updateTmpAdvList")
	public void updateTmpAdvList(HttpServletRequest request,HttpServletResponse response){
		String company = AdminSessionUtils.getSessionAdminUserCommpany(request);
		String id=request.getParameter("id");
		String showtime=request.getParameter("showtime");
		try {
			advertiseService.updateTmpAdvList(id,showtime,company);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="queryQueue")
	public void queryQueue(HttpServletRequest request,HttpServletResponse response){
		List<AdvertiseList> adList=null;
		JsonEntity jsonEntity = new JsonEntity(); 
		HttpSession session=request.getSession();
		AdminUser user= (AdminUser) session.getAttribute("adminuser");
		String company=null;
		if(user != null)
		{
			company =user.getCompany();
		}
		try{
			
			adList = advertiseService.queryQueue(company);
			 
			jsonEntity.setRows(JSONArray.fromObject(adList).toString());
			String responseJson ="{\"rows\":"+JSONArray.fromObject(adList).toString()+"}";
			 response.setContentType("text/json;charset=utf-8");
			 response.getWriter().write(responseJson);
		}catch(Exception e )
		{
			e.printStackTrace();
		} 
	}
	
	@RequestMapping(value="deleteListById")
	public void deleteListById(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String id=request.getParameter("id");
		HttpSession session=request.getSession();
		AdminUser user= (AdminUser) session.getAttribute("adminuser");
		String company = null;
		if(user != null)
		{
			company =user.getCompany();
		}
	
		try {
			
			advertiseService.deleteAdvList(id,company);
			String msg="{\"status\":\"true\" , \"msg\":\"删除成功!\"}";
			 response.setContentType("text/json;charset=utf-8");
			 response.getWriter().write(msg);
		} catch (Exception e) {
			e.printStackTrace();
			String msg="{\"status\":\"false\" , \"msg\":\"删除失败!\"}";
			 response.setContentType("text/json;charset=utf-8");
			 response.getWriter().write(msg);
		}
	}
	
	
	@RequestMapping(value="getAdvList")
	public void getAdvList(HttpServletRequest request,HttpServletResponse response){
		AdvertiseList adList=null;
		HttpSession session=request.getSession();
		User user= (User) session.getAttribute("user");
		String company=null;
		if(user != null)
		{
			company =user.getCompany();
		}
		try {
			adList=advertiseService.getAdvList(company);
			JSONArray jsonArray = JSONArray.fromObject(adList);
			String resultStr = jsonArray.toString();
	        response.setContentType("text/json; charset=utf-8");
	        response.setHeader("Cache-Control", "no-cache"); //取消浏览器缓存
	        response.getWriter().write(resultStr);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		
}

