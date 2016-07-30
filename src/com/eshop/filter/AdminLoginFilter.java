/**
 * 
 */
package com.eshop.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.eshop.entrity.AdminUser;


/**
 * yy 2015 2015年12月14日 上午10:13:11
 *
 */
public class AdminLoginFilter  implements Filter { 
	
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
	
	    HttpServletRequest request=(HttpServletRequest) req;
		HttpServletResponse response=(HttpServletResponse) resp;
		AdminUser adminUser = (AdminUser) request.getSession().getAttribute("adminuser");
		if (adminUser!= null) {
			
			chain.doFilter(request, response);
			
		}else {
			request.getRequestDispatcher("/managers/index.jsp").forward(request, response);
		}
		
		
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}

	
	@Override
	public void destroy() {
   }


}
