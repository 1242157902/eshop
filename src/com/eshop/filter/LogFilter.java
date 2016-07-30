/**
 * 
 */
package com.eshop.filter;
import java.io.IOException;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.eshop.entrity.BrowseLog;
import com.eshop.entrity.Goods;
import com.eshop.entrity.User;
import com.eshop.service.GoodServiceImpl;
import com.eshop.service.interf.GoodService;



/**
 * 浏览拦截器
 * @author cjx
 *
 * 2016-1-17
 */
public class LogFilter implements Filter {
	
	GoodService goodService=new GoodServiceImpl();
@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
	
	    HttpServletRequest request=(HttpServletRequest) req;
	    HttpServletResponse response=(HttpServletResponse) resp;
	    
	    StringBuffer userurl=request.getRequestURL();
	    
	    String clickurl=userurl.toString();
	    
	    String time=String.format("%tF %<tT", new Date());
	    
	      HttpSession session=request.getSession();
	      
	      Goods good=null;
	      
	      String company=null;
	      
	       User user=(User)session.getAttribute("user");
	       
	    String good_id=request.getParameter("good_id");
	       
	       if(user!=null){
	    	   company=user.getCompany();
	    	   if (clickurl.contains("do")) {
	    		   if (good_id!=null) {
	    			   
	    			   try {
	    				 
						good=goodService.findGoodById(good_id, company);
					} catch (Exception e) {
						
						e.printStackTrace();
					}
	    			System.out.println(clickurl+" "+good_id+"  "+good.getGood_name()+"  "+time+" "+user.getTelephone());
	    			BrowseLog browseLog=new BrowseLog();
	    			browseLog.setUser(user);
	    			browseLog.setGood_id(good_id);
	    			browseLog.setGood_name(good.getGood_name());
	    			browseLog.setBrowseTime(time);
	    			goodService.addBrowseLog(browseLog,company);  
				}
	    		   
			}
	    	 
	       
	       }
	       
	      
	       
	       
		  
			
			
			chain.doFilter(request, response);
			
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}

	
	@Override
	public void destroy() {
   }

}
