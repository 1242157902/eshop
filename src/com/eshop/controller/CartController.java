package com.eshop.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


import com.eshop.entrity.CartItem;
import com.eshop.entrity.Stock;
import com.eshop.entrity.User;
import com.eshop.entrity.UserAdress;
import com.eshop.service.CartServiceImpl;
import com.eshop.service.StockServiceImpl;
import com.eshop.service.UserAdressServiceImpl;
import com.eshop.service.WXcountsServiceImpl;
import com.eshop.service.interf.StockService;
import com.eshop.utils.UUIDUtil;
/**
 * @author  JYM
 * 2015-11-13 下午8:32:33
 */
@Controller
@RequestMapping("cart")
public class CartController {
	
	CartServiceImpl cartService=new CartServiceImpl();
	
	
	WXcountsServiceImpl wXcountsService=new WXcountsServiceImpl();
	
	StockService stockService=new  StockServiceImpl();
	UserAdressServiceImpl userAdressService=new UserAdressServiceImpl();
	
	
	
	@RequestMapping(value = "addtocart.do")
	public String  addtocart(HttpServletRequest request,Model model) throws Exception
	{
		try {
			System.out.println(request.getRequestURI());
			
				String goodid=request.getParameter("goodid");
				String proScount=request.getParameter("proScount");
				
				
		          CartItem cartItem=new CartItem();

		           cartItem.setCartitemid(UUIDUtil.getuuid());

		          cartItem.setCreate_time(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		          cartItem.setGood_quantity(Integer.parseInt(proScount));
		         cartItem.setGood_id(goodid);

		          User user=(User) request.getSession().getAttribute("user");

		          cartItem.setUser_id(user.getUser_id()+"");

		           cartItem.setUser(user);



		              cartService.save(cartItem,cartItem.getUser().getCompany());

		              return   cartlist(request, model);
			
		}catch (Exception e) {
			e.printStackTrace();
			}
		return   cartlist(request, model);
	
	}


	
	@RequestMapping(value = "cartlist")
	public String cartlist(HttpServletRequest request,Model model) throws Exception{
		
		   User user=(User) request.getSession().getAttribute("user");
		   int uid=user.getUser_id();
		   List<CartItem> cartItems=cartService.getitems(uid+"",user.getCompany(),user.getTelephone());
		   request.setAttribute("cartItems", cartItems);
		   request.setAttribute("user", user);
		  
		return "/pages/cart";
	}
	
	@RequestMapping(value="update")
     public void  update(HttpServletRequest request,HttpServletResponse response){
    	 
    	 String cartitemid=request.getParameter("cartItemId");
    	 User user=(User) request.getSession().getAttribute("user");
    	 
    	 
 		int quantity=1;
 		try {
 			quantity=Integer.parseInt(request.getParameter("quantity"));
 		} catch (NumberFormatException e) {
 			quantity=1;
 		}
 		CartItem cartItem=cartService.updatequantity(cartitemid,quantity,user.getCompany());
 		StringBuilder sb = new StringBuilder("{");
 		sb.append("\"quantity\"").append(":").append(cartItem.getGood_quantity());
 		sb.append(",");
 		sb.append("\"subtotal\"").append(":").append(cartItem.getSubtotal());
 		sb.append("}");

 		try {
 			
 			response.getWriter().print(sb.toString());
 		
 		} catch (IOException e) {
 			
 		}

     }
	
	
	
	@RequestMapping(value="delect")
	public String delect(HttpServletRequest request,Model model) throws Exception{
		
		String cartitemids=request.getParameter("cartItemIds");
		User user=(User) request.getSession().getAttribute("user");
		cartService.delete(cartitemids,user.getCompany());
		return  cartlist(request, model);
	}
	
	
	@RequestMapping(value="loadcartitems")
	public String loadcartitems(HttpServletRequest request ,Model model) throws Exception{
		
       String cartitemids=request.getParameter("cartItemIds");
		
		double total=Double.parseDouble(request.getParameter("total"));
		
		User user=(User) request.getSession().getAttribute("user");
		
		
		List<CartItem> cartItems=cartService.getitemsbyid(cartitemids,user.getCompany());
		 int totalnum=wXcountsService.getScoreByUser(user.getTelephone());
		 
		 if(totalnum<total){
			 request.setAttribute("msg","<script>alert('对不起，你的积分不足，请修改礼品数量！')</script>");
			 return cartlist(request, model);
		 }
		 int  gquantity=0;
		    for (int i = 0; i < cartItems.size(); i++) {
		    	 Stock  stock=new Stock();
		    	 stock=stockService.findStockById(cartItems.get(i).getGood_id(), user.getCompany());
		    	
		    	 if( stock==null || stock.getQuantity()<cartItems.get(i).getGood_quantity()){
		    		 request.setAttribute("msg","<script>alert('对不起，礼品"+cartItems.get(i).getGoods().getGood_name()+"库存不足请修改礼品数量！')</script>");
		    		 return cartlist(request, model);
		    		
		    	 }
		    	 
		    	gquantity+=cartItems.get(i).getGood_quantity();
		    	
		    	
		}
		 
		model.addAttribute("gquantity", gquantity);
		model.addAttribute("cartItems", cartItems);
		model.addAttribute("total", total);
		model.addAttribute("cartItemIds", cartitemids);
		
		  //获取用户的地址列表
				List<UserAdress> adressList=new ArrayList<UserAdress>();
				
				adressList=userAdressService.findByUserid(user.getUser_id(),user.getCompany());
				
				request.setAttribute("adressList", adressList);
		
		return "/pages/orderConfirm";
	}
	
	
	//添加地址
		@RequestMapping(value="addAdress")
		public void addAdress(HttpServletRequest request,HttpServletResponse response,Model model) throws Exception{
			
			String name=request.getParameter("name");
			String adress=request.getParameter("address");
			/* if(name==null){
				 request.setAttribute("msg","<script>alert('用户姓名不能为空')</script>");
				
			 }
			 if(adress==null){
				 request.setAttribute("msg","<script>alert('用户地址不能为空')</script>");
				
			 }
			 if(request.getParameter("phone")==null){
				 request.setAttribute("msg","<script>alert('用户电话不能为空')</script>");
			 }*/
			User user=(User) request.getSession().getAttribute("user");
			UserAdress userAdress=new UserAdress();
			
			userAdress.setName(name);
			userAdress.setAdress(adress);
			userAdress.setStatus(1);
			userAdress.setCreatetime(String.format("%tF %<tT", new Date()));
			userAdress.setUser(user);
			userAdress.setUser_id(user.getUser_id());
			userAdress.setPhone(request.getParameter("phone"));
			userAdress.setZipcode(request.getParameter("zipcode"));
			
			userAdressService.save(userAdress,user.getCompany());
			
			response.getWriter().write("success");
		
			
		}
}
