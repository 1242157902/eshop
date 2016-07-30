package com.eshop.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eshop.entrity.CartItem;
import com.eshop.entrity.Deviceinfo;
import com.eshop.entrity.Goods;
import com.eshop.entrity.Order;
import com.eshop.entrity.OrderDetail;
import com.eshop.entrity.OutStock;
import com.eshop.entrity.RewardScore;
import com.eshop.entrity.Stock;
import com.eshop.entrity.User;
import com.eshop.entrity.UserAdress;
import com.eshop.entrity.WXcounts;
import com.eshop.entrity.WxScoreTrade;
import com.eshop.entrity.view.JsonEntity;
import com.eshop.entrity.view.VoOrderEntity;
import com.eshop.service.CartServiceImpl;
import com.eshop.service.DeviceInfoServiceImpl;
import com.eshop.service.GoodServiceImpl;
import com.eshop.service.OrderDetailServiceImpl;
import com.eshop.service.OrderServiceImpl;
import com.eshop.service.OutStockServiceImpl;
import com.eshop.service.RewardScoreServiceImpl;
import com.eshop.service.StockServiceImpl;
import com.eshop.service.UserAdressServiceImpl;
import com.eshop.service.WXcountsServiceImpl;
import com.eshop.service.interf.GoodService;
import com.eshop.service.interf.OrderDetailService;
import com.eshop.service.interf.OrderService;
import com.eshop.service.interf.OutStockService;
import com.eshop.service.interf.StockService;
import com.eshop.utils.AdminSessionUtils;
import com.eshop.utils.DateUtil;
import com.eshop.utils.SequenceUtils;

 /**
  * 
  * <p>Title：        OrderController<p>
  * <p>Description:  订单控制器，处理订单的相关业务<p>
  * @date:           2015年11月4日上午11:04:51
  * @author:         ysl
  * @version         1.0
  */
@Controller
@RequestMapping("order")
public class OrderController {

	OrderService   orderService  = new OrderServiceImpl();
	OrderDetailService orderDetailService  = new OrderDetailServiceImpl();
	OutStockService   outStockService = new OutStockServiceImpl();
	StockService stockService = new StockServiceImpl();
	GoodService goodService=new GoodServiceImpl();
	RewardScoreServiceImpl rewardScoreService=new RewardScoreServiceImpl();
	DeviceInfoServiceImpl deviceInfoService=new DeviceInfoServiceImpl();
	UserAdressServiceImpl userAdressService=new UserAdressServiceImpl();
	
	WXcountsServiceImpl wXcountsService=new WXcountsServiceImpl();
	
	@RequestMapping(value = "findAllOrder")
	public  void findAllOrder(HttpServletRequest request, VoOrderEntity voOrderEntity,HttpServletResponse response)  {
		
		
		List<Order> purchaseList = null;
		JsonEntity jsonEntity = new JsonEntity(); 
		Map<String, Object> m = new HashMap<String, Object>();
		//String company = SessionUtils.getSessionUserCommpany(request);
		String company = AdminSessionUtils.getSessionAdminUserCommpany(request);
		if(company ==null)
		{
			return ;
		}
		try{
			if (voOrderEntity!=null) {
				if (company!=null) {
					m.put("company", company);
				}
				if (voOrderEntity.getOrder_id()!=null) {
					m.put("Order_id", voOrderEntity.getOrder_id());
				}
				if (voOrderEntity.getCreate_time()!=null) {
					m.put("Create_time", voOrderEntity.getCreate_time());
				}
				if (voOrderEntity.getEndtime()!=null) {
					m.put("endtime", voOrderEntity.getEndtime());
				}
				if (voOrderEntity.getConsignee_name()!=null) {
					m.put("Consignee_name", voOrderEntity.getConsignee_name());
				}
				if (voOrderEntity.getConsignee_phone()!=null) {
					m.put("Consignee_phone", voOrderEntity.getConsignee_phone());
				}
				if (voOrderEntity.getConsignee_address()!=null) {
					m.put("Consignee_address", voOrderEntity.getConsignee_address());
				}
				if (voOrderEntity.getOrder()!=null) {
					m.put("order", voOrderEntity.getOrder());
				}
				if (voOrderEntity.getSort()!=null) {
					m.put("sort", voOrderEntity.getSort());
				}
			}
			purchaseList = orderService.findPurchaseByPagination(voOrderEntity.getPage(), voOrderEntity.getRows(), m);
			 jsonEntity.setTotal(orderService.getTotal(m));
			String responseJson ="{\"total\":"+jsonEntity.getTotal()+" , \"rows\":"+JSONArray.fromObject(purchaseList).toString()+"}";
			 response.setContentType("text/json;charset=utf-8");
			 response.getWriter().write(responseJson);
		}catch(Exception e )
		{
			e.printStackTrace();
		} 
	}

	@RequestMapping(value = "updateOrder")
	public void updateOrder(HttpServletRequest request,VoOrderEntity voOrderEntity,HttpServletResponse response)
	{
		Order  order = null;
		/*String company = SessionUtils.getSessionUserCommpany(request);*/
		String company = AdminSessionUtils.getSessionAdminUserCommpany(request);
		try
		{
			 if (voOrderEntity!=null&&voOrderEntity.getOrder_id()!=null) {
				 
				 //先通过id查询出该商品的库存信息
				 order = orderService.findOrderById(voOrderEntity.getOrder_id(),company);
				 if (order!=null) {
					 order.setStatus(voOrderEntity.getStatus());
					 order.setConsignee_address(voOrderEntity.getConsignee_address());
					 order.setConsignee_name(voOrderEntity.getConsignee_name());
					 order.setConsignee_phone(voOrderEntity.getConsignee_phone());
					 order.setConsignee_zipcode(voOrderEntity.getConsignee_zipcode());
					 order.setUser_id(voOrderEntity.getUser_id());
					 order.setCreate_time(voOrderEntity.getCreate_time());
					 order.setTotal(voOrderEntity.getTotal());
				}
				 //更新该商品
				 orderService.updateOrder(order,company);
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
	
	
	
	/**
	 * 	
	 * @return:       void 
	 * @param request
	 * @param order_ids
	 * @param response
	 * <p>Description: 查询订单明细<p>
	 * @date:          2015年11月24日下午6:32:38
	 * @author         ysl
	 */
	@RequestMapping(value = "searchOrderDetail")
	public  void searchOrderDetail(HttpServletRequest request, String  order_ids,HttpServletResponse response)
	{
			List<List<OrderDetail>>  list =null;
			List<OrderDetail> orderDetailList = null;
		 	/*String company = SessionUtils.getSessionUserCommpany(request);*/
			String company = AdminSessionUtils.getSessionAdminUserCommpany(request);
		 	 try
		 	 {
		 		 
		 		if (order_ids!=null&&!"".equals(order_ids))
				{
		 			list = new ArrayList<List<OrderDetail>>();
		 			orderDetailList = new ArrayList<OrderDetail>();
					 //1、将instock_ids分解成数组，
					String[]  orderIds = order_ids.split(",");
					//2、根据instockIds查询该入库单下的所有入库详细
					for (String order_id : orderIds) 
					{
						
						List<OrderDetail>   orderDetailList1 = orderDetailService.findOrderDetailByOrderId(order_id, company);
						
					/*	//加入商品名称
						for (InStockDetail inStockDetail : inStockDetailList1) 
						{
							Goods good = goodService.findGoodById(inStockDetail.getGood_id(), company);
							if (good!=null&&good.getGood_name()!=null) {
								inStockDetail.setGood_name(good.getGood_name());
							}
						}*/
						list.add(orderDetailList1);
					}
					//3、将所有详细入库的单据重新加入到一个集合中，再将集合转化为json格式
					for (List<OrderDetail> orderDetailList2 : list) 
					{
						for (OrderDetail  orderDetail : orderDetailList2) 
						{
							orderDetailList.add(orderDetail);
						}
					}
				}
		 		
				String responseJson ="{\"total\":"+orderDetailList.size()+" , \"rows\":"+JSONArray.fromObject(orderDetailList).toString()+"}";
				 response.setContentType("text/json;charset=utf-8");
				 response.getWriter().write(responseJson);
		 	 }catch(Exception e )
		 	 {
		 		 e.printStackTrace();
		 	 }
	
	}
	
	
	
	
	
	
	
	
	/**
	 * 
	 * @return:       void 
	 * @param request
	 * @param order_ids
	 * @param response
	 * <p>Description:假设前提条件：只要下了订单，就说明库中有足够多的商品供应，就能够全部出库
	 * 							出库的同时， 直接生成出库记录<p>
	 * @date:          2015年11月17日下午4:51:13
	 * @author         ysl
	 */
	
	
	@RequestMapping(value = "insertOutStock")
	public void insertOutStock(HttpServletRequest request,String  order_ids, HttpServletResponse response)
	{
		List<OrderDetail> orderDetailList = null;
		int flag = 1;
		int  isGoodsQuantityMore = 0;
		String  lackingName = null;
		/*String company = SessionUtils.getSessionUserCommpany(request);*/
		String company = AdminSessionUtils.getSessionAdminUserCommpany(request);
		try {
			
			if (order_ids!=null&&!"".equals(order_ids))
			{
				//1、先分解purchase_ids ，得到采购单表的id
				String[]  orderIds = order_ids.split(",");
				List<Stock> stockList = stockService.findAllStock(company);
				
				 for (String  order_id : orderIds)
				 {
					 
					//2、根据订单表id，查询订单明细
					 orderDetailList = orderDetailService.findOrderDetailByOrderId(order_id,company);
					 
					 //3、根据订单明细条目，出库，再生成出库单据
					 /*for (OrderDetail  orderDetail : orderDetailList)
					 {
						 //4、先判断是否订单明细中所有的商品数量都足够
						 for (Stock stock : stockList) 
						 {
							if (stock.getGood_id().equals(orderDetail.getGood_id()))
							{
								int stockQuantity = stock.getQuantity();
								int outStockQuantity = orderDetail.getGood_quantity();
								if (outStockQuantity > stockQuantity)
								{
									isGoodsQuantityMore = -1;
									flag = 2;
									lackingName = orderDetail.getGood_name();
									break;
								}
								 
							}
						}
					}*/
					 
					 //如若商品的数量足够，则出库
					 
					 if (isGoodsQuantityMore!=-1) 
					 {
						 for (OrderDetail  orderDetail : orderDetailList)
						 {
							 
						/*		//4、出库
							 for (Stock stock : stockList) 
							 {
								if (stock.getGood_id().equals(orderDetail.getGood_id()))
								{
									int stockQuantity = stock.getQuantity();
									int outStockQuantity = orderDetail.getGood_quantity();
									if (outStockQuantity <= stockQuantity)
									{
										flag = 1;
										stockQuantity = stockQuantity - outStockQuantity;
										stock.setQuantity(stockQuantity);
										//更新库存
										stockService.updateStock(stock, company);
									}
								}
							}*/
						 
							 //5、生成出库记录
							 OutStock outStock = new OutStock();
							 outStock.setOut_id("OS"+AdminSessionUtils.getSessionAdminPhone(request).toString()+SequenceUtils.getNextId("OS", company) );
							 outStock.setGood_id(orderDetail.getGood_id());
							 outStock.setGood_name(orderDetail.getGood_name());
							 outStock.setQuantity(orderDetail.getGood_quantity());
							 outStock.setOrder_id(order_id);
							 outStock.setStatus(0);
							 //从session中获得用户名
							 outStock.setManager_name(AdminSessionUtils.getSessionAdminNickName(request));
							 outStock.setOuttime(DateUtil.formatDate1(new Date()));
							//3、根据订单明细，生成入库单表
							outStockService.InsertOutStock(outStock,company);
						 }
						 
						 //4、根据orderid获取采购订单表，并修改状态出库发货
						 Order order = orderService.findOrderById(order_id,company);
						 order.setStatus(3);
						 orderService.updateOrder(order,company);
					 }
					 
				
				}
				 
				 
					switch (flag) {
					case 0:
						response.setContentType("text/html;charset=utf-8");
						String str = "{\"status\":\"ok\" , \"message\":\"库中无该类商品，请采购!\"}";
						response.getWriter().write(str);
						break;
					case 1:
						response.setContentType("text/html;charset=utf-8");
						String str1 = "{\"status\":\"ok\" , \"message\":\"操作成功!\"}";
						response.getWriter().write(str1);
						break;
					case 2:
						response.setContentType("text/html;charset=utf-8");
						//String str2 = "{\"status\":\"ok\" , \"message\":\"库存不够，请采购!\"}";
						String str2 = "{\"status\":\"ok\" , \"message\":\" "+lackingName+ "-------库存不够，请采购!\"}";
						response.getWriter().write(str2);
						break;

					default:
						break;
					}
				 
				 
				 
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**JYM order  2015/11/24*/
	
	CartServiceImpl cartService=new CartServiceImpl();
	
	@RequestMapping(value="createorder")
   public String createorder(HttpServletRequest request,Model model)  throws Exception{
		
		
		String cartitemids=request.getParameter("cartItemIds");
		User user=(User) request.getSession().getAttribute("user");
		double totals=Double.parseDouble(request.getParameter("total"));
	
		List<CartItem> cartItems=new ArrayList<CartItem>();
		try {
			cartItems = cartService.getitemsbyid(cartitemids,user.getCompany());
		} catch (Exception e1) {
		
			return "/pages/orderConfirm";
		}
		List<UserAdress> adressList=userAdressService.findByUserid(user.getUser_id(),user.getCompany());
		model.addAttribute("adressList", adressList);
		request.setCharacterEncoding("UTF-8");
		String name=request.getParameter("name");
		String address=request.getParameter("address");
		String phone=request.getParameter("phone");
		
		 if(name==""||address==""||phone==""){
			 request.setAttribute("msg","<script>alert('请选择地址')</script>");
			 model.addAttribute("cartItems", cartItems);
			model.addAttribute("cartItemIds", cartitemids);
			model.addAttribute("total", totals);
		    model.addAttribute("adressList", adressList);
			return "/pages/orderConfirm";
			
		 }

		//创建order
		 Deviceinfo deviceinfo=deviceInfoService.getNameByPhone(user.getTelephone());
		
		Order order=new Order();
		           
		
		order.setOrder_id("O"+user.getTelephone()+SequenceUtils.getNextId("O", user.getCompany()) );
		
		order.setCreate_time(String.format("%tF %<tT", new Date()));
		
		order.setStatus(2);//表示为付款
		
		order.setConsignee_address(new String(address.getBytes("ISO-8859-1"),"utf-8"));
		order.setConsignee_name(new String(name.getBytes("ISO-8859-1"),"utf-8"));
		order.setConsignee_phone(request.getParameter("phone"));
		order.setConsignee_zipcode(request.getParameter("zipcode"));
		order.setDelivery(Integer.parseInt(request.getParameter("delivery")));
		order.setNick_name(deviceinfo.getNick_name());
	
		order.setUser_id(user.getUser_id()+"");
		
		BigDecimal total=new BigDecimal("0");
		
		
		
		/*order.setOwner(user);*/
		
		
		for (CartItem cartItem : cartItems) {
			
			
			total=total.add(new BigDecimal(cartItem.getSubtotal()+""));
		}
		
		order.setTotal(total.doubleValue());
		
		
		List<OrderDetail> orderItems=new ArrayList<OrderDetail>();
		for (CartItem cartItem : cartItems) {
			
			OrderDetail orderItem=new OrderDetail();
			
			orderItem.setOrderitemid("OD"+user.getTelephone()+SequenceUtils.getNextId("OD", user.getCompany()) );
			
			orderItem.setGood_quantity(cartItem.getGood_quantity());
			
			orderItem.setGood_id(cartItem.getGood_id());
			 
			orderItem.setCurrprice(cartItem.getGoods().getGood_nowprice());
			
			orderItem.setSubtotal(cartItem.getSubtotal());
			
			orderItem.setGood(cartItem.getGoods());
			orderItem.setGood_name(cartItem.getGoods().getGood_name());
			orderItem.setOrder_id(order.getOrder_id());
			orderItem.setOrder(order);
			
			orderDetailService.save(orderItem,user.getCompany());
		
			
			orderItems.add(orderItem);
			
			
		}
		
	for (CartItem cartItem : cartItems) {
			
		Stock stock=new Stock();
		stock=stockService.findStockById(cartItem.getGood_id(), user.getCompany());
		stock.setQuantity(stock.getQuantity()-cartItem.getGood_quantity());
		System.out.println(stock.getQuantity());
		stockService.updateStock(stock,user.getCompany());
		Goods goods=new Goods();
		goods=goodService.findGoodById(cartItem.getGood_id(), user.getCompany());
		goods.setSale_num(goods.getSale_num()+cartItem.getGood_quantity());
		System.out.println(goods.getSale_num());
		goodService.updateGoodSaleNum(goods, user.getCompany());

		}
		
	
		
		
		try {
			orderService.save(order,user.getCompany());
		} catch (Exception e) {
		
			e.printStackTrace();
		}
		
		
		//查询奖励积分
	   
		RewardScore rewardScore=rewardScoreService.getScoreBynickname(deviceinfo.getNick_name(), user.getCompany());
		WXcounts wXcounts=new WXcounts();
		wXcounts=wXcountsService.getWSByUser(user.getTelephone());
		WxScoreTrade wxScoreTrade=new WxScoreTrade();
		int totalscore=0;
		//奖励积分不为空
		if(rewardScore!=null){
			//奖励积分大于订单积分
			int reward=rewardScore.getReward_score();
			if(reward>order.getTotal())
			{
				 rewardScore.setReward_score((int) (reward-order.getTotal()));
				 rewardScoreService.update(rewardScore, user.getCompany());
			}
			if(reward<=order.getTotal()){
				rewardScore.setReward_score(0);
				rewardScoreService.update(rewardScore, user.getCompany());
				int syscore=(int) (order.getTotal()-reward);
				  if(syscore>0){
				    wXcounts.setOut_count(wXcounts.getOut_count()+1);
				    wXcounts.setScore_count(wXcounts.getScore_count()-syscore);
				    wXcountsService.updateWS(wXcounts);
				    
				    //减积分交易
				    wxScoreTrade.setDevice_number(user.getTelephone());
					wxScoreTrade.setDate(String.format("%tF %<tT", new Date()));
					wxScoreTrade.setScore(syscore);
					wxScoreTrade.setStatus(3);
					wXcountsService.saveTrade(wxScoreTrade);
					
					//标记intrade表
					wXcountsService.updateInTrade(user.getTelephone(),syscore);
					
				}
			}
			
			totalscore=rewardScore.getReward_score()+wXcounts.getScore_count();
		}
		//没有奖励积分
		if(rewardScore==null){
			//减总积分
			
			wXcounts.setOut_count(wXcounts.getOut_count()+1);
			wXcounts.setScore_count((int) (wXcounts.getScore_count()-order.getTotal()));
			wXcountsService.updateWS(wXcounts);
			
			//减积分交易记录
			double stotal=order.getTotal();
			int ws=(int)stotal;
			
			wxScoreTrade.setDevice_number(user.getTelephone());
			wxScoreTrade.setDate(String.format("%tF %<tT", new Date()));
			wxScoreTrade.setScore(ws);
			wxScoreTrade.setStatus(3);
			
			wXcountsService.saveTrade(wxScoreTrade);
			
			//标记intrade表
			wXcountsService.updateInTrade(user.getTelephone(),ws);
			totalscore=wXcounts.getScore_count();
			
		}
		//删除购物车条目
		
		cartService.delete(cartitemids,user.getCompany());
		
		
		
		request.setAttribute("order", order);
		request.setAttribute("totalscore", totalscore);
		
		return "/pages/orderSuccess";
	}
	
	
	@RequestMapping(value="myorder")
	public String myorder(HttpServletRequest request,Model model,Integer currentPage){
		
		currentPage = (currentPage == null) ? 1 : currentPage;
		User user=(User) request.getSession().getAttribute("user");
		try {
			
			
			List<Order> orders=orderService.getAllByUser(user.getUser_id(),user.getCompany(),currentPage, 5);
		
				
			
			int totalCount=orderService.getTotalCount(user.getUser_id(),user.getCompany());
			
			long count = totalCount /5;
			if(totalCount % 5 > 0){
				count++;
			}
				
		
			//Set<OrderDetail> orderDetails=new HashSet<OrderDetail>();
			List<OrderDetail>  orderDetails=new ArrayList<OrderDetail>();
		for (Order order : orders) {
			orderDetails= orderDetailService.findOrderDetailByOrderId(order.getOrder_id(), user.getCompany());
			order.setOrderDetails(orderDetails);
			
		    }
		
		//总积分
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
		 
		request.setAttribute("totalCount", totalCount);
		request.setAttribute("count", count);
		request.setAttribute("orders", orders);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("totalscore", totalscore);
		
		} catch (Exception e) {
				e.printStackTrace();
	}
			 
		
		return "/pages/myorder";
}
	@RequestMapping(value="showOrderListByXls")
	public String showOrderListByXls(HttpServletRequest request, VoOrderEntity voOrderEntity,ModelMap model,HttpServletResponse response){
		
		List<VoOrderEntity> voOrderEntityList = null;
		List<VoOrderEntity>  totalList = new ArrayList<VoOrderEntity>();
		Map<String, Object> m = new HashMap<String, Object>();
		String company = AdminSessionUtils.getSessionAdminUserCommpany(request);
		try{
			if (voOrderEntity!=null) {
				if (company!=null) {
					m.put("company", company);
				}
				if (voOrderEntity.getCreate_time()!=null) {
					m.put("Create_time", voOrderEntity.getCreate_time());
				}
				if (voOrderEntity.getEndtime()!=null) {
					m.put("endtime", voOrderEntity.getEndtime());
				}
			 
			}
			voOrderEntityList = orderService.getVoOrderList(m);
			for (VoOrderEntity vOrderEntity : voOrderEntityList)
			{
				List<OrderDetail> orderDetailList = orderDetailService.findOrderDetailByOrderId(vOrderEntity.getOrder_id(), company);
				for (OrderDetail orderDetail : orderDetailList)
				{
					vOrderEntity.setGood_name(orderDetail.getGood_name());
					vOrderEntity.setGood_quantity(orderDetail.getGood_quantity());
					totalList.add(vOrderEntity);
				}
			}
			model.addAttribute("totalList", totalList);
		}catch(Exception e )
		{
			e.printStackTrace();
		} 
		
		return "OrderListExcelView";
	}
	@RequestMapping(value="showOrder")
	public void showOrder(HttpServletRequest request, VoOrderEntity voOrderEntity,ModelMap model,HttpServletResponse response){
		
		List<VoOrderEntity> voOrderEntityList = null;
		JsonEntity jsonEntity = new JsonEntity(); 
		List<VoOrderEntity>  totalList = new ArrayList<VoOrderEntity>();
		Map<String, Object> m = new HashMap<String, Object>();
		String company = AdminSessionUtils.getSessionAdminUserCommpany(request);
		try{
			if (voOrderEntity!=null) {
				if (company!=null) {
					m.put("company", company);
				}
				if (voOrderEntity.getCreate_time()!=null) {
					m.put("Create_time", voOrderEntity.getCreate_time());
				}
				if (voOrderEntity.getEndtime()!=null) {
					m.put("endtime", voOrderEntity.getEndtime());
				}
				
			}
			voOrderEntityList = orderService.getVoOrderList(m);
			for (VoOrderEntity vOrderEntity : voOrderEntityList)
			{
				List<OrderDetail> orderDetailList = orderDetailService.findOrderDetailByOrderId(vOrderEntity.getOrder_id(), company);
				for (OrderDetail orderDetail : orderDetailList)
				{
					vOrderEntity.setGood_name(orderDetail.getGood_name());
					vOrderEntity.setGood_quantity(orderDetail.getGood_quantity());
					totalList.add(vOrderEntity);
				}
			}
			 jsonEntity.setTotal(totalList.size());
				String responseJson ="{\"total\":"+jsonEntity.getTotal()+" , \"rows\":"+JSONArray.fromObject(totalList).toString()+"}";
				 response.setContentType("text/json;charset=utf-8");
				 response.getWriter().write(responseJson);
		}catch(Exception e )
		{
			e.printStackTrace();
		} 
		
	}
	
	@RequestMapping(value="showComment")
	public String showComment(HttpServletRequest request,Model model,String order_id){
		
		//显示评价
		User user=(User) request.getSession().getAttribute("user");
		String company = null;
		if(user != null)
		{
			company =user.getCompany();
		}else{
			return "/index";
		}
		
		try {
			
			List<OrderDetail>  orderDetails=new ArrayList<OrderDetail>();
			orderDetails= orderDetailService.findOrderDetailByOrderId(order_id, company); 
			Order order =new Order();
			Goods good=null;
			System.out.println(orderDetails);
			for (OrderDetail orderDetail : orderDetails) {
				good= goodService.findGoodById(orderDetail.getGood_id(), company);
				orderDetail.setGood(good);
				order=orderService.findOrderById(order_id, company);
				orderDetail.setCreatedTime(order.getCreate_time());
				System.out.println(orderDetail.getGood());
			    }
			
			request.setAttribute("orderDetails", orderDetails);
		
		} catch (Exception e) {
				e.printStackTrace();
		}
		return "/pages/orderComment";
		
		
		
	
	}
	@RequestMapping(value="orderComment")
	public String orderComment(HttpServletRequest request,Model model,String orderDetail_id) throws UnsupportedEncodingException{
    	String good_comment= request.getParameter("good_comment");
    	String good_id= request.getParameter("good_id");
    	OrderDetail orderDetail=null;
		//显示评价
		User user=(User) request.getSession().getAttribute("user");
		String company = null;
		if(user != null)
		{
			company =user.getCompany();
		}else{
			return "/index";
		}
		try {
			
			
			orderDetail=orderDetailService.findOrderDetailById(orderDetail_id, company);
		    if(orderDetail!=null){
		    	orderDetail.setGood_comment(good_comment);
		    	orderDetail.setComment_time(String.format("%tF %<tT", new Date()));
		    	if(request.getParameter("score")!=null)
		    	{
		    		orderDetail.setGrade(Integer.parseInt(request.getParameter("score")));
		    	}
		    	orderDetailService.saveComment(orderDetail,company);
		    	
		    	
		    }
		} catch (Exception e) {
				e.printStackTrace();
	    }
		return "redirect:/order/showComment.do?order_id="+orderDetail.getOrder_id();
		
		
		
	
	}
}
