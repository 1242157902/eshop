package com.eshop.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.omg.CORBA.COMM_FAILURE;

import com.eshop.entrity.AdminUser;
import com.eshop.entrity.User;

/**
 * 
 * <p>Title：        SessionUtils<p>
 * <p>Description: 获得session中的user的公司直属 <p>
 * @date:           2015年11月5日下午7:22:49
 * @author:         ysl
 * @version         1.0
 */
public class SessionUtils {
	
	public static String getSessionUserCommpany(HttpServletRequest request)
	{
		String company = null;
		HttpSession session=request.getSession();
		if (session!=null)
		{
			User user= (User) session.getAttribute("user");
			if(user !=null)
			{
				company=user.getCompany();
			}
		}
		return company;
	}
	public static String getSessionUserNickName(HttpServletRequest request)
	{
		String nickName = null;
		HttpSession session=request.getSession();
		if (session!=null)
		{
			User user= (User) session.getAttribute("user");
			nickName= user.getNickname();
		}
		return nickName;
	}
	public static String getSessionUserPhone(HttpServletRequest request)
	{
		String phone = null;
		HttpSession session=request.getSession();
		if (session!=null)
		{
			User user= (User) session.getAttribute("user");
			 phone = user.getTelephone();
		}
		return phone;
	}
	
	public static int getSessionUserID(HttpServletRequest request)
	{
		int usrid=0;
		HttpSession session=request.getSession();
		if (session!=null)
		{
			User user= (User) session.getAttribute("user");
			usrid= user.getUser_id();
		}
		return usrid;
	}
}
