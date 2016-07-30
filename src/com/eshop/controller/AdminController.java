package com.eshop.controller;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eshop.entrity.AdminUser;
import com.eshop.entrity.User;
import com.eshop.service.AdminLoginSeviceImpl;
import com.eshop.service.TmpInStockDetailServiceImpl;
import com.eshop.service.interf.AdminLoginSevice;
import com.eshop.service.interf.TmpInStockService;

/**
 * 管理员服务控制controller
 * @author Jane
 *
 */
@Controller
@RequestMapping("admin")
public class AdminController {

	AdminLoginSevice service=new AdminLoginSeviceImpl();
	TmpInStockService tmpInStockService = new TmpInStockDetailServiceImpl();
	@RequestMapping(value = "adminLogin")
	public String adminLogin(HttpServletRequest request,HttpServletResponse response,Model model) throws SQLException, UnsupportedEncodingException{
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		String telephone = request.getParameter("telephone");
        String password = request.getParameter("password");
        String unitno=request.getParameter("company");
        String companyName;
        AdminUser user=service.login(telephone, password,unitno);
        
        String URL=null;
		try {
			if(user!=null){
	            if("100001".equals(unitno)){
	            	companyName="北方工大后台管理系统";
	            }else if("100002".equals(unitno)){
	            	companyName="迪信通积分商城后台管理系统";
	            }else{
	            	companyName="后台管理系统";
	            }
	            user.setCompanyName(companyName);
	            user.setCompany(unitno);
	        	HttpSession session=request.getSession();
				session.setAttribute("adminuser", user);
				URL="/managers/main";
	        }else {
				request.setAttribute("errmsg", "<script>alert('用户名或密码错误！')</script>");
				URL="/managers/index";
			}
		} catch (NullPointerException e) {
			request.setAttribute("errmsg", "<script>alert('电话号码错误！')</script>");
			URL="/managers/index";
		}
		return URL;
	}
	
	@RequestMapping(value = "logout")
	public String logout(HttpServletRequest request) {
	
		HttpSession session = request.getSession();
		AdminUser user = (AdminUser) session.getAttribute("adminuser");
		if(user!=null&&user.getCompany()!=null)
		{
			try {
				tmpInStockService.deleteTmpInStock(user.getCompany());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		session.removeAttribute("adminuser");
	return "redirect:/managers/index.jsp";
	}
}
