package com.eshop.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.eshop.entrity.Category;
import com.eshop.entrity.GoodFav;
import com.eshop.entrity.GoodPics;
import com.eshop.entrity.Goods;
import com.eshop.entrity.Stock;
import com.eshop.entrity.TempInStock;
import com.eshop.entrity.User;
import com.eshop.entrity.view.JsonEntity;
import com.eshop.entrity.view.VGoodEntity;
import com.eshop.entrity.view.VoTmpInStock;
import com.eshop.service.CategoryServiceImpl;
import com.eshop.service.DeviceInfoServiceImpl;
import com.eshop.service.GoodPicServiceImpl;
import com.eshop.service.GoodServiceImpl;
import com.eshop.service.InStockDetailServiceImpl;
import com.eshop.service.InStockServiceImpl;
import com.eshop.service.RewardScoreServiceImpl;
import com.eshop.service.StockServiceImpl;
import com.eshop.service.TmpInStockDetailServiceImpl;
import com.eshop.service.WXcountsServiceImpl;
import com.eshop.service.interf.CategoryService;
import com.eshop.service.interf.GoodPicService;
import com.eshop.service.interf.GoodService;
import com.eshop.service.interf.InStockDetailService;
import com.eshop.service.interf.InStockService;
import com.eshop.service.interf.RewardScoreService;
import com.eshop.service.interf.StockService;
import com.eshop.service.interf.TmpInStockService;
import com.eshop.utils.AdminSessionUtils;
import com.eshop.utils.Page;
import com.eshop.utils.SequenceUtils;
import com.eshop.utils.UUIDUtil;


/**
 * @Description:商品管理控制Controller
 * @author Jane
 *
 */

@Controller
@RequestMapping("good")
public class GoodController {

	GoodService goodService=new GoodServiceImpl();
	CategoryService categoryService=new CategoryServiceImpl();
	InStockDetailService purchaseDetailService = new InStockDetailServiceImpl();
	InStockService purchaseService = new InStockServiceImpl();
	TmpInStockService tmpInStockService = new TmpInStockDetailServiceImpl();
	DeviceInfoServiceImpl deviceInfoService=new DeviceInfoServiceImpl(); 
	RewardScoreService rewardScoreService=new RewardScoreServiceImpl();
	WXcountsServiceImpl  wXcountsService=new WXcountsServiceImpl();
	
	StockService ss = new StockServiceImpl();
	/**
	 * 
	 * @return:       void 
	 * @param response
	 * @param vGood
	 * <p>Description:分页显示所有已上架商品 <p>
	 * @date:          2015年11月3日下午8:36:58queryTmpInStock
	 * @author         ysl
	 */
	@RequestMapping(value ="querySellGood")
	public void querySellGood(HttpServletRequest request,HttpServletResponse  response,VGoodEntity vGood){
		List<Goods> goodList = null;
		String company = null;
		JsonEntity jsonEntity = new JsonEntity(); 
		Map<String, Object> m = new HashMap<String, Object>();
		HttpSession session=request.getSession();
		AdminUser user= (AdminUser) session.getAttribute("adminuser");
		if(user != null)
		{
			company =user.getCompany();
		}
		if (company == null) {
			return ;
		}
		try{
			if (vGood!=null) {
				if (vGood.getGood_id()!=null) {
					m.put("good_id", vGood.getGood_id());
				}
				if (vGood.getGood_name()!=null) {
					m.put("good_name", vGood.getGood_name());
				}
				if (vGood.getBrand_name()!=null) {
					m.put("brand_name", vGood.getBrand_name());
				}
				if (vGood.getC_id()!=null) {
					m.put("c_id", vGood.getC_id());
				}
				if (vGood.getOrder()!=null) {
					m.put("order", vGood.getOrder());
				}
				if (vGood.getSort()!=null) {
					m.put("sort", vGood.getSort());
				}
			}
			goodList = goodService.findSellGoodByPagination(vGood.getPage(), vGood.getRows(), m,company);
			jsonEntity.setTotal(goodService.getSellTotal(m,company));
			jsonEntity.setRows(JSONArray.fromObject(goodList).toString());
			String responseJson ="{\"total\":"+jsonEntity.getTotal()+" , \"rows\":"+JSONArray.fromObject(goodList).toString()+"}";
			response.setContentType("text/json;charset=utf-8");
			response.getWriter().write(responseJson);
		}catch(Exception e )
		{
			e.printStackTrace();
		} 
	}
	//删除商品
	@RequestMapping(value ="deleteGood")
	public void deleteGood(HttpServletRequest request,HttpServletResponse  response,String good_ids){
		
		String company=AdminSessionUtils.getSessionAdminUserCommpany(request);
		
		try {
			if(good_ids!=null){
				String[]  goodid = good_ids.split(",");
				for (String  good_id : goodid)
				 {
					goodService.deleteGood(good_id,company);
				 }
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//收藏商品
	@RequestMapping(value ="favGood")
	public void favGood(HttpServletRequest request,HttpServletResponse response){
		
		HttpSession session=request.getSession();
		String flag=null;
		User user= (User) session.getAttribute("user");
		String company = null;
		String good_id=request.getParameter("good_id");
		System.out.println("商品id为：--------"+good_id);
		if(user != null)
		{
			company =user.getCompany();
		}
		try{
		GoodFav goodFav=new GoodFav();
		Goods goods=new Goods();
		goods=goodService.findGoodById(good_id, company);
		goodFav.setFav_id(UUIDUtil.getuuid());
		goodFav.setGood_id(good_id);
		goodFav.setGood_name(goods.getGood_name());
		goodFav.setUser_id(user.getUser_id());
		goodFav.setFav_status(1);
		goodFav.setCreatedTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		List<GoodFav> goodFavs=goodService.queryFavGoodByUserId(user.getUser_id(),company);
		if(goodFavs!=null){
			for(GoodFav fav:goodFavs){
				if(fav.getGood_id().equals(good_id)){
					    
					flag="1";
					break;
				}
			}
		}
		if(flag==null){
			
			goodService.addFavGood(goodFav, company);
			
		}
		
		
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	//根据条件查询商品信息
	@RequestMapping(value ="queryGood")
	public void queryAllGood(HttpServletRequest request,HttpServletResponse  response,VGoodEntity vGood){
		List<Goods> goodList = null;
		JsonEntity jsonEntity = new JsonEntity(); 
		String company = null;
		Map<String, Object> m = new HashMap<String, Object>();
		HttpSession session=request.getSession();
		AdminUser user= (AdminUser) session.getAttribute("adminuser");
		if(user != null)
		{
			company =user.getCompany();
		}
		if (company == null) {
			return ;
		}
		try{
			if (vGood!=null) {
				if (vGood.getGood_id()!=null) {
					m.put("Good_id", vGood.getGood_id());
				}
				if (vGood.getGood_name()!=null) {
					m.put("good_name", vGood.getGood_name());
				}
				if (vGood.getBrand_name()!=null) {
					m.put("brand_name", vGood.getBrand_name());
				}
				if (vGood.getC_id()!=null) {
					m.put("c_id", vGood.getC_id());
				}
				if (vGood.getOrder()!=null) {
					m.put("order", vGood.getOrder());
				}
				if (vGood.getSort()!=null) {
					m.put("sort", vGood.getSort());
				}
			}
			goodList = goodService.findGoodByPagination(vGood.getPage(), vGood.getRows(), m,company);
			 jsonEntity.setTotal(goodService.getTotal(m,company));
			 jsonEntity.setRows(JSONArray.fromObject(goodList).toString());
			String responseJson ="{\"total\":"+jsonEntity.getTotal()+" , \"rows\":"+JSONArray.fromObject(goodList).toString()+"}";
			 response.setContentType("text/json;charset=utf-8");
			 response.getWriter().write(responseJson);
		}catch(Exception e )
		{
			e.printStackTrace();
		} 
	}
	@RequestMapping(value ="queryTmpInStock")
	public void queryTmpInStock(HttpServletRequest request,HttpServletResponse  response,VoTmpInStock voTmpInStock){
		List<TempInStock> tempInStockList = null;
		JsonEntity jsonEntity = new JsonEntity(); 
		String company = null;
		Map<String, Object> m = new HashMap<String, Object>();
		HttpSession session=request.getSession();
		AdminUser user= (AdminUser) session.getAttribute("adminuser");
		if(user != null)
		{
			company =user.getCompany();
		}
		if (company == null) {
			return ;
		}
		try{
			if (voTmpInStock!=null) {
				if (company!=null) {
					m.put("company", company);
				}
				if (voTmpInStock.getOrder()!=null) {
					m.put("order", voTmpInStock.getOrder());
				}
				if (voTmpInStock.getSort()!=null) {
					m.put("sort", voTmpInStock.getSort());
				}
			}
			tempInStockList = tmpInStockService.findTmpInStockByPagination(voTmpInStock.getPage(), voTmpInStock.getRows(), m);
			for (TempInStock tempInStock : tempInStockList)
			{
				String good_id = tempInStock.getGood_id();
				Goods good = goodService.findGoodById(good_id, company);
				tempInStock.setGood_name(good.getGood_name());
			}
			//得到的无操作员的名字
			jsonEntity.setTotal(goodService.getTotal(m,company));
			String responseJson ="{\"total\":"+jsonEntity.getTotal()+" , \"rows\":"+JSONArray.fromObject(tempInStockList).toString()+"}";
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
	 * @param good_ids
	 * @param purchase_nums
	 * @param response
	 * <p>Description: 根据前台传来的商品id以及相应的采购数量，插入到临时的入库表中<p>
	 * @date:          2015年11月4日上午9:09:21
	 * @author         ysl
	 */
	@RequestMapping(value = "insertTempInStock")
	public void insertTempInStock(String  good_ids, String purchase_nums,HttpServletResponse response,HttpServletRequest request)
	{
		HttpSession session=request.getSession();
		AdminUser user= (AdminUser) session.getAttribute("adminuser");
		String company = null;
		if(user != null)
		{
			company =user.getCompany();
		}
		String[]   goodIds = null;
		String[]		purchaseNums = null;
		Goods		good = null;
		Integer    quantity = 0;
		Double 	subtotal = 0.0;				//小计
		//1、分解字符串 ，生成数组
		if (good_ids!=null&&!"".equals(good_ids))
		{
			goodIds = good_ids.split(",");
		}
		if (purchase_nums!=null&&!"".equals(purchase_nums))
		{
			purchaseNums = purchase_nums.split(",");
		}
		
		try {
			
			/**
			 * 根据前台传来的商品id和数量，插入到临时表中
			 */
			for (int i = 0;i<goodIds.length;i++) 
			{
				//1、先根据id查询出来商品，获得商品的属性信息
				good = goodService.findGoodById(goodIds[i],company);
				if (good!=null) 
				{
					//2、再根据数量计算出小计
					TempInStock tempInStock = new TempInStock();
					if(purchaseNums[i]!=null)
					{
						quantity = Integer.parseInt(purchaseNums[i]);
					}
					subtotal = quantity * good.getGood_price();
					String ss = SequenceUtils.getInstance().getNextId("T", company) ;
					tempInStock.setTmp_in_id("T"+AdminSessionUtils.getSessionAdminPhone(request).toString() + ss);
					//tempInStock.setTmp_in_id("T"+AdminSessionUtils.getSessionAdminPhone(request).toString() + SequenceUtils.getNextId("T", company) );
					tempInStock.setGood_id(good.getGood_id());
					tempInStock.setGood_price(good.getGood_price());
					tempInStock.setGood_quantity(quantity);
					tempInStock.setSubtotal(subtotal);
					tempInStock.setUser_id(user.getId());
					tempInStock.setManager_name(user.getName());
					//3、插入到临时入库表中
					tmpInStockService.insertTempInStock(tempInStock, company);
				}
			}
			
			
		} catch (Exception e) {
			 e.printStackTrace();
		}
	 
	}
	
	//修改商品信息
	@RequestMapping(value = "updateGood")
	public void updategood(VGoodEntity vGood,HttpServletResponse response,HttpServletRequest request)
	{
		Goods good=null;
		HttpSession session=request.getSession();
		AdminUser user= (AdminUser) session.getAttribute("adminuser");
		String company = null;
		if(user != null)
		{
			company =user.getCompany();
		}
		try
		{
			 if (vGood!=null&&vGood.getC_id()!=null) {
				 
				 //先通过id查询出该商品的信息
				 good = goodService.findGoodById(vGood.getGood_id(),company);
				 good.setGood_id(vGood.getGood_id());
				 good.setGood_name(vGood.getGood_name());
				 good.setBrand_name(vGood.getBrand_name());
				 good.setGood_guige(vGood.getGood_guige());
				 good.setIsvisual(vGood.getIsvisual());
				/* good.setC_id(vGood.getC_id());*/
				 good.setGood_price(vGood.getGood_price());
				 good.setGood_nowprice(vGood.getGood_nowprice());
				 good.setGood_desc(vGood.getGood_desc());
				 good.setIs_sell(vGood.getIs_sell());
				 //更新商品信息
				 goodService.updateGood(good,company);
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
	GoodPicService goodPicService=new GoodPicServiceImpl();
	@RequestMapping(value="lookGoodById")
	public String lookGoodById(HttpServletRequest request) throws Exception{
		List<Category> categoryList = null;
		List<Goods> rankGoodList=null;
		List<GoodPics> goodPics=null;
		List<Goods> recomGoods=null;
		Category category=null;
		String good_id=request.getParameter("good_id");
		Goods good=null;
		GoodPics goodPic=null;
		HttpSession session=request.getSession();
		User user= (User) session.getAttribute("user");
		String company = null;
		if(user != null)
		{
			company =user.getCompany();
		}else{
			return "/index";
		}
		
		good=goodService.findGoodById(good_id,company);
		
		if (good!=null) 
		{
			Stock s = ss.findStockById(good_id, company);
			if (s!= null&&s.getQuantity()!=null) 
			{
				good.setStockQuantity(s.getQuantity());
			}
		}
		//查询该商品的小图
		goodPics=goodPicService.queryAllPic(good_id,company);
		goodPic=goodPicService.queryOne(good_id,company);
		categoryList=categoryService.findByParentId(company);
		//查询兑换排行榜
		rankGoodList=goodService.queryRank(company);
		//查询推荐商品
		recomGoods=goodService.queryRecommend(good.getC_id(),good_id,company);
		//查询该商品分类
		category=categoryService.findCategoryById(good.getC_id(), company);
		request.setAttribute("good",good);
		request.setAttribute("goodPic",goodPic);
		request.setAttribute("categoryList", categoryList);
		request.setAttribute("rankGoodList", rankGoodList);
		request.setAttribute("goodPics", goodPics);
		request.setAttribute("category", category);
		request.setAttribute("recomGoods", recomGoods);
		
		return "/pages/merchdetail";
	}
	
	@RequestMapping(value="lookMerchById")
	public String lookMerchById(HttpServletRequest request,HttpServletResponse response) throws IOException{
		Page<Goods> goodList=null;
		List<Category> firstcategories=null;
		List<Category> secondCategories = null;
		List<Goods> rankGoodList=null;
		String c_id=request.getParameter("c_id");
		String c_parent=request.getParameter("c_parent");
		String nowprice=request.getParameter("nowprice");
		String sale_num=request.getParameter("sale_num");
		String pagenum=request.getParameter("pagenum");
		HttpSession session=request.getSession();
		User user= (User) session.getAttribute("user");
		String company = null;
		if(user != null)
		{
			company =user.getCompany();
		}else{
			return "/index";
		}
		
		goodList=goodService.lookMerchById(c_id,c_parent,company,nowprice,sale_num,pagenum);
		secondCategories=categoryService.queryCategoryById(c_parent,company);
		firstcategories=categoryService.findByParentId(company);
		rankGoodList=goodService.queryRank(company);
		
		request.setAttribute("firstcategories", firstcategories);
		request.setAttribute("rankGoodList", rankGoodList);
		request.setAttribute("secondCategories", secondCategories);
		request.setAttribute("page",goodList);
		request.setAttribute("goodList", goodList.getList());
		request.setAttribute("c_id",c_id);
		request.setAttribute("c_parent",c_parent);
		return "/pages/merchcategory";
	}
	
	@RequestMapping(value="lookAll")
	public String lookAll(HttpServletRequest request){
		List<Category> firstcategories=null;
		List<Category> secondCategories = null;
		List<Goods> rankGoodList=null;
		Page<Goods> goodPage=null;
		String pagenum=request.getParameter("pagenum");
		String c_id=request.getParameter("c_id");
		String c_parent=request.getParameter("c_parent");
		HttpSession session=request.getSession();
		User user= (User) session.getAttribute("user");
		String company = null;
		if(user != null)
		{
			company =user.getCompany();
		}else{
			return "/index";
		}
		
		goodPage=goodService.lookAll(company,pagenum,c_id,c_parent);	
		firstcategories=categoryService.findByParentId(company);
		secondCategories=categoryService.queryCategoryById(null,company);
		rankGoodList=goodService.queryRank(company);
		
		request.setAttribute("firstcategories", firstcategories);
		request.setAttribute("rankGoodList", rankGoodList);
		request.setAttribute("secondCategories", secondCategories);
		request.setAttribute("goodList", goodPage.getList());
		request.setAttribute("page", goodPage);
		if(c_id==null){
			request.setAttribute("c_id",-1);
			request.setAttribute("c_parent",0);
		}else{
			request.setAttribute("c_id",c_id);
			request.setAttribute("c_parent",c_parent);
		}
		
		return "/pages/merchandiseAll";
	}
	
	@RequestMapping(value="getKeyWord")
	public void getKeyWord(HttpServletRequest request,HttpServletResponse response) throws IOException{
		List<String> list=null;
		HttpSession session=request.getSession();
		User user= (User) session.getAttribute("user");
		String company = null;
		if(user != null)
		{
			company =user.getCompany();
		}
		list=goodService.getKeyWord(company);
		String page=JSONArray.fromObject(list).toString();
		response.setContentType("text/json; charset=utf-8");
        response.setHeader("Cache-Control", "no-cache"); //取消浏览器缓存
		response.getWriter().write(page);
		
		
	}
	@RequestMapping(value="queryGoodsByKeyword")
	public String queryGoodsByKeyword(HttpServletRequest request,HttpServletResponse response) throws Exception{
		List<Category> firstcategories=null;
		List<Category> secondCategories = null;
		List<Goods> rankGoodList=null;
		Page<Goods> goodPage=null;
		String keyword=request.getParameter("keyword");
		String pagenum=request.getParameter("pagenum");
		HttpSession session=request.getSession();
		User user= (User) session.getAttribute("user");
		String company = null;
		if(user != null)
		{
			company =user.getCompany();
		}else{
			return "/index";      
		}
		String str=new String(keyword.getBytes("ISO-8859-1"),"utf-8");
		goodPage=goodService.queryGoodsByKeyword(str,company,pagenum);
		firstcategories=categoryService.findByParentId(company);
		secondCategories=categoryService.queryCategoryByKeyWord(keyword,company);
		rankGoodList=goodService.queryRank(company);
		
		request.setAttribute("firstcategories", firstcategories);
		request.setAttribute("rankGoodList", rankGoodList);
		request.setAttribute("secondCategories", secondCategories);
		request.setAttribute("goodList", goodPage.getList());
		request.setAttribute("page", goodPage);
		request.setAttribute("keyword",str);
		return "/pages/merchKeyWord";
	}
	
	
	@RequestMapping(value="lookMerchByKeyword")
	public void lookMerchByKeyword(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String c_id=request.getParameter("c_id");
		String keyword=request.getParameter("keyword");
		String pagenum=request.getParameter("pagenum");
		HttpSession session=request.getSession();
		User user= (User) session.getAttribute("user");
		Page<Goods> goodPage=null;
		String company = null;
		if(user != null)
		{
			company =user.getCompany();
		}
		goodPage=goodService.lookMerchByKeyword(c_id,keyword,company,pagenum);
		String page=JSONArray.fromObject(goodPage).toString();
		response.setContentType("text/json; charset=utf-8");
        response.setHeader("Cache-Control", "no-cache"); //取消浏览器缓存
		response.getWriter().write(page);
		
	}
	
	
	@RequestMapping(value="lookNewGoods")
	public String lookNewGoods(HttpServletRequest request){
		List<Category> firstcategories=null;
		List<Category> secondCategories = null;
		List<Goods> rankGoodList=null;
		Page<Goods> goodPage=null;
		String pagenum=request.getParameter("pagenum");
		String c_id=request.getParameter("c_id");
		String c_parent=request.getParameter("c_parent");
		HttpSession session=request.getSession();
		User user= (User) session.getAttribute("user");
		String company = null;
		if(user != null)
		{
			company =user.getCompany();
		}else{
			return "/index";
		}
		
		goodPage=goodService.lookNewGoods(company,pagenum,c_id,c_parent);	
		firstcategories=categoryService.findByParentId(company);
		secondCategories=categoryService.queryCategoryById(null,company);
		rankGoodList=goodService.queryRank(company);
		
		request.setAttribute("firstcategories", firstcategories);
		request.setAttribute("rankGoodList", rankGoodList);
		request.setAttribute("secondCategories", secondCategories);
		request.setAttribute("goodList", goodPage.getList());
		request.setAttribute("page", goodPage);
		if(c_id==null){
			request.setAttribute("c_id",-1);
			request.setAttribute("c_parent",0);
		}else{
			request.setAttribute("c_id",c_id);
			request.setAttribute("c_parent",c_parent);
		}
		
		return "/pages/merchandiseAll";
	}
	
	@RequestMapping(value="myFavorite")
	public String myFavorite(HttpServletRequest request,Model model,Integer currentPage){
		currentPage = (currentPage == null) ? 1 : currentPage;
		User user=(User) request.getSession().getAttribute("user");
		String company = null;
		if(user != null)
		{
			company =user.getCompany();
		}else{
			return "/index";
		}
		try {
			
			
			List<Goods> goodFavList=goodService.queryMyFavByUser(user.getUser_id(),company,currentPage, 8);
		
				
			
			int totalCount=goodService.getFavTotal(user.getUser_id(), company);
			System.out.println(totalCount);
			long count = totalCount/8;
			if(totalCount % 8 > 0){
				count++;
			}
 
		request.setAttribute("totalCount", totalCount);
		request.setAttribute("count", count);
		request.setAttribute("goodFavList", goodFavList);
		request.setAttribute("currentPage", currentPage);
		
		
		} catch (Exception e) {
				e.printStackTrace();
		}
			 
		
		return "/pages/MyFav";
	}
}

