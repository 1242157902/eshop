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

import com.eshop.entrity.User;



/**
 * 录拦截器
 * @author wzq
 *
 * 2014-5-18
 */
public class LoginFilter implements Filter {
@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
	
	    HttpServletRequest request=(HttpServletRequest) req;
		HttpServletResponse response=(HttpServletResponse) resp;
		User user = (User) request.getSession().getAttribute("user");
		if (user!= null) {
			
			chain.doFilter(request, response);
			
		}else {
			
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			
		}
		
		
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}

	
	@Override
	public void destroy() {
   }

}
