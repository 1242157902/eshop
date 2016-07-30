package com.eshop.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eshop.entrity.AdminUser;
import com.eshop.entrity.User;
import com.eshop.entrity.view.JsonEntity;
import com.eshop.entrity.view.VoAdminUserEntity;
import com.eshop.service.AdminServiceImpl;
import com.eshop.service.UserServiceImpl;
import com.eshop.service.interf.AdminUserService;
import com.eshop.service.interf.UserService;
import com.eshop.utils.AdminSessionUtils;
import com.eshop.utils.SessionUtils;

@Controller
@RequestMapping("user")
public class AdminUserController {

		AdminUserService adminUserService = new AdminServiceImpl();
		UserService userService=new UserServiceImpl(); 
	@RequestMapping(value = "findAllAdminUser")
	public  void findAllAdminUser(HttpServletRequest request, VoAdminUserEntity voAdminUserEntity,HttpServletResponse response)  {
		List<AdminUser> adminUserList = null;
		JsonEntity jsonEntity = new JsonEntity(); 
		Map<String, Object> m = new HashMap<String, Object>();
	/*	String company = SessionUtils.getSessionUserCommpany(request);*/
		String company = AdminSessionUtils.getSessionAdminUserCommpany(request);
		if(company ==null)
		{
			return ;
		}
		try{
			if (voAdminUserEntity!=null) {
				if (company!=null) {
					m.put("company", company);
				}
				if (voAdminUserEntity.getId()!=null) {
					m.put("id", voAdminUserEntity.getId());
				}
		 
				if (voAdminUserEntity.getName()!=null) {
					m.put("name", voAdminUserEntity.getName());
				}
				if (voAdminUserEntity.getPhone()!=null) {
					m.put("phone", voAdminUserEntity.getPhone());
				}
				if (voAdminUserEntity.getStatus()!=null) {
					m.put("status", voAdminUserEntity.getStatus());
				}
				if (voAdminUserEntity.getCom_no()!=null) {
					m.put("com_no", voAdminUserEntity.getCom_no());
				}
				 
				if (voAdminUserEntity.getOrder()!=null) {
					m.put("order", voAdminUserEntity.getOrder());
				}
				if (voAdminUserEntity.getSort()!=null) {
					m.put("sort", voAdminUserEntity.getSort());
				}
			}
			adminUserList = adminUserService.findPurchaseByPagination(voAdminUserEntity.getPage(), voAdminUserEntity.getRows(), m);
			 jsonEntity.setTotal(adminUserService.getTotal(m));
			String responseJson ="{\"total\":"+jsonEntity.getTotal()+" , \"rows\":"+JSONArray.fromObject(adminUserList).toString()+"}";
			 response.setContentType("text/json;charset=utf-8");
			 response.getWriter().write(responseJson);
		}catch(Exception e )
		{
			e.printStackTrace();
		} 
	}
	

	@RequestMapping(value = "insertAdminUser")
	public void insertAdminUser(HttpServletRequest request,VoAdminUserEntity voAdminUserEntity, HttpServletResponse response)
	{
		
	/*	String company = SessionUtils.getSessionUserCommpany(request);*/
		String company = AdminSessionUtils.getSessionAdminUserCommpany(request);
		if(company==null)
		{
			return ;
		}
		try
		{
		
			
			 if (voAdminUserEntity!=null) {
				 
				 AdminUser  adminUser = new AdminUser();
				 adminUser.setCom_no(voAdminUserEntity.getCom_no());
				 adminUser.setName(voAdminUserEntity.getName());
				 adminUser.setPhone(voAdminUserEntity.getPhone());
				 adminUser.setStatus(voAdminUserEntity.getStatus());
				 adminUser.setPwd(voAdminUserEntity.getPwd());
				 if (adminUser!=null) {
					 adminUserService.insertAdminUser(adminUser, company);
				}
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
	
	
	
	
	
	
	
	@RequestMapping(value = "updateAdminUser")
	public void updateAdminUser(HttpServletRequest request,VoAdminUserEntity voAdminUserEntity,HttpServletResponse response)
	{
		AdminUser  adminUser = null;
		/*String company = SessionUtils.getSessionUserCommpany(request);*/
		String company = AdminSessionUtils.getSessionAdminUserCommpany(request);
		try
		{
			 if (voAdminUserEntity!=null&&voAdminUserEntity.getId()!=null) {
				 
				 //先通过id查询出该商品的库存信息
				 adminUser = adminUserService.findAdminUserById(voAdminUserEntity.getId(), company);
				 if (adminUser!=null) {
					 adminUser.setCom_no(voAdminUserEntity.getCom_no());
					 adminUser.setName(voAdminUserEntity.getName());
					 adminUser.setPhone(voAdminUserEntity.getPhone());
					 adminUser.setStatus(voAdminUserEntity.getStatus());
					 adminUser.setPwd(voAdminUserEntity.getPwd());
				}
				 //更新该商品
				 adminUserService.updateAdminUser(adminUser, company);
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
	
	@RequestMapping(value = "logout")
	public String logout(HttpServletRequest request) {
	
		HttpSession session = request.getSession();
		session.removeAttribute("user");
	   return "redirect:/index.jsp";
	}
	
	
	@RequestMapping(value = "userInfo.do")
	public String userInfo(HttpServletRequest request, Model model) {
	
		User user=(User) request.getSession().getAttribute("user");
		String company = null;
		if(user != null)
		{
			company =user.getCompany();
		}else{
			return "/index";
		}
		try {
			
		User userInfo=userService.getInfoById(user.getUser_id(),company);
        
        
		request.setAttribute("user", userInfo);
	
		
		} catch (Exception e) {
				e.printStackTrace();
		}
		
		
		return "/pages/userInfo";
	}
	
	@RequestMapping(value = "updateInfo.do")
	public String updateInfo(HttpServletRequest request, Model model) {
		String sex=request.getParameter("sex");
		String nickname=request.getParameter("nickname");
		
		String birthday=request.getParameter("birthday");
		String email=request.getParameter("email");
		
		String career=request.getParameter("career");
		
		User user=(User) request.getSession().getAttribute("user");
		String company = null;
		if(user != null)
		{
			company =user.getCompany();
		}else{
			return "/index";
		}
		try {

			
			
			user.setBirthday(birthday);
			user.setEmail(new String(email.getBytes("ISO-8859-1"),"utf-8"));
			user.setNickname(new String(nickname.getBytes("ISO-8859-1"),"utf-8"));
			user.setSex(Integer.parseInt(sex));
			user.setCareer(new String(career.getBytes("ISO-8859-1"),"utf-8"));
			userService.updateInfo(user,company);
			
			
			
			
			
			
			
			
	
		
		} catch (Exception e) {
				e.printStackTrace();
		}
		
		
		return "/pages/userInfo";
	}
}
