package com.eshop.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.eshop.entrity.AdminUser;


/**
 * 获取session中管理员adminuser的相关信息；
 * @author xia
 *
 */
public class AdminSessionUtils {
	

	public static String getSessionAdminUserCommpany(HttpServletRequest request)
	{
		String company = null;
		HttpSession session=request.getSession();
		if (session!=null)
		{
			AdminUser user= (AdminUser) session.getAttribute("adminuser");
			if(user !=null)
			{
				company=user.getCompany();
			}
		}
		return company;
	}
	public static String getSessionAdminNickName(HttpServletRequest request)
	{
		String nickName = null;
		HttpSession session=request.getSession();
		if (session!=null)
		{
			AdminUser user= (AdminUser) session.getAttribute("adminuser");
			nickName= user.getName();
		}
		return nickName;
	}
	public static String getSessionAdminPhone(HttpServletRequest request)
	{
		String phone = null;
		HttpSession session=request.getSession();
		if (session!=null)
		{
			AdminUser user= (AdminUser) session.getAttribute("adminuser");
			 phone = user.getPhone();
		}
		return phone;
	}
	
	public static int getSessionAdminUserID(HttpServletRequest request)
	{
		int usrid=0;
		HttpSession session=request.getSession();
		if (session!=null)
		{
			AdminUser user= (AdminUser) session.getAttribute("adminuser");
			usrid= user.getId();
		}
		return usrid;
	}
}
