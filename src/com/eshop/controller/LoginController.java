package com.eshop.controller;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eshop.entrity.Category;
import com.eshop.entrity.Deviceinfo;
import com.eshop.entrity.Goods;
import com.eshop.entrity.MobileModel;
import com.eshop.entrity.RewardScore;
import com.eshop.entrity.User;
import com.eshop.entrity.WXcounts;
import com.eshop.service.CategoryServiceImpl;
import com.eshop.service.DeviceInfoServiceImpl;
import com.eshop.service.GoodServiceImpl;
import com.eshop.service.LoginService;
import com.eshop.service.RewardScoreServiceImpl;
import com.eshop.service.WXcountsServiceImpl;
import com.eshop.service.interf.CategoryService;
import com.eshop.service.interf.GoodService;
import com.eshop.service.interf.RewardScoreService;

/**
 * @author wzq
 *
 *version 1.0 2015-10-18 下午5:49:33
 */
@Controller
@RequestMapping("login")
public class LoginController {
	
	LoginService service=new LoginService();
	

	@RequestMapping(value = "login.do")
	public String login(HttpServletRequest request, Model model) {
	
		String phonenum=request.getParameter("phonenum");
		
	    MobileModel mb=service.getmbbynum(phonenum);
	    
	    if (mb==null) {//如果用户不存在 跳转到易划app下载页面
			
	    	return "/pages/download";
	    	
		}
		
	    String uninno=mb.getCompany();
	    
	    
	    User user=service.getuser(phonenum,uninno);//判断商城用户是否存在
	    if (uninno!=null&&user!=null) 
        {
        	user.setCompany(uninno);
        	
		}
        
	    request.setAttribute("flag",uninno);//区分不同商城的用户
			
			
		if (user!=null) {
			request.setAttribute("phonenum",phonenum);
			return "/pages/login";
		}else  {
				
			
			request.setAttribute("phonenum",phonenum);
			
			return "/pages/register";
			}
			
	
	
	}
	
	
	@RequestMapping(value = "userlogin.do")
	public String userlogin(HttpServletRequest request, Model model) {
		
		             String telephone = request.getParameter("telephone");
		             String password = request.getParameter("password");
		             
		            User user=service.login(telephone,password);
		            
		            String unitno=service.getmbbynum(telephone).getCompany();
		            if (unitno!=null&&user!=null) 
		            {
		            	user.setCompany(unitno);
		            	service.update(user);
					}
		            
  
		            if (user!=null) {
						HttpSession session=request.getSession();
						
						session.setAttribute("user", user);
						
						if ("100001".equals(unitno)) {
							
							/*return "/pages/ncut";*/
							
							return queryAllContent(request,model);
						}else if ("100002".equals(unitno)) {
							
							return queryAllContent(request,model);
						}else {
							return queryAllContent(request,model);
						}
						
						
					}else {
						
						
						request.setAttribute("errmsg", "<script>alert('用户名或密码错误！')</script>");
						
						return "/pages/login";
						
					}
		
	}
	
	
	
	
	@RequestMapping(value = "register.do")
	public String register(HttpServletRequest request,Model model) throws UnsupportedEncodingException {
		
		String password=request.getParameter("password");
		String sex=request.getParameter("sex");
		String nickname=request.getParameter("nickname");
		
		String birthday=request.getParameter("birthday");
		String email=request.getParameter("email");
		String telephone=request.getParameter("phonenum");
		String flag=request.getParameter("flag");
		User user=new User();
		
		user.setBirthday(birthday);
		user.setEmail(email);
		user.setNickname(nickname);
		user.setPassword(password);
		user.setTelephone(telephone);
		user.setCompany(flag);
		user.setRegdate(String.format("%tF %<tT", new Date()));
		user.setLastdate(String.format("%tF %<tT", new Date()));
		
		user.setSex(Integer.parseInt(sex));
		service.save(user);
		
		
		
		request.getSession().setAttribute("user", user);
		
		
		
		
		
		if ("100001".equals(flag)) {
			
			return queryAllContent(request,model);
		}else if ("100002".equals(flag)) {
			
			return queryAllContent(request,model);
		}else {
			return queryAllContent(request,model);
		}
		
	
		
	}
	
	CategoryService categoryService=new CategoryServiceImpl();
	GoodService goodService=new GoodServiceImpl();
	DeviceInfoServiceImpl deviceInfoService=new DeviceInfoServiceImpl(); 
	RewardScoreService rewardScoreService=new RewardScoreServiceImpl();
	WXcountsServiceImpl  wXcountsService=new WXcountsServiceImpl();
	
	
	
	@RequestMapping(value="queryAllContent")
	public String queryAllContent(HttpServletRequest request, Model model){
		try {
			List<Category> categoryList = null;
			List<Goods> newGoodList=null;
			List<Goods> rankGoodList=null;
			
			HttpSession session=request.getSession();
			User user=(User) session.getAttribute("user");
			String company=user.getCompany();
			categoryList=categoryService.findByParentId(company);
			newGoodList=goodService.queryAll(company);
			rankGoodList=goodService.queryRank(company);
			Deviceinfo deviceinfo=deviceInfoService.getNameByPhone(user.getTelephone());
			RewardScore rewardScore=rewardScoreService.getScoreBynickname(deviceinfo.getNick_name(), user.getCompany());
			WXcounts wXcounts=wXcountsService.getWSByUser(user.getTelephone());
			int totalscore=0;
			if(rewardScore!=null){
				totalscore=rewardScore.getReward_score()+wXcounts.getScore_count();
			}
			if(rewardScore==null){
				totalscore=wXcounts.getScore_count();
			}
			
			request.getSession().setAttribute("totalscore",totalscore);
			request.setAttribute("categoryList", categoryList);
			request.setAttribute("newGoodList", newGoodList);
			request.setAttribute("rankGoodList", rankGoodList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "/pages/main";
	}

	
	
}
