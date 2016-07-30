package com.eshop.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eshop.entrity.AdminUser;
import com.eshop.entrity.Category;
import com.eshop.entrity.Deviceinfo;
import com.eshop.entrity.Goods;
import com.eshop.entrity.RewardScore;
import com.eshop.entrity.User;
import com.eshop.entrity.WXcounts;
import com.eshop.entrity.view.JsonEntity;
import com.eshop.entrity.view.VCategoryEntity ;
import com.eshop.service.CategoryServiceImpl;
import com.eshop.service.DeviceInfoServiceImpl;
import com.eshop.service.GoodServiceImpl;
import com.eshop.service.RewardScoreServiceImpl;
import com.eshop.service.WXcountsServiceImpl;
import com.eshop.service.interf.CategoryService;
import com.eshop.service.interf.GoodService;
import com.eshop.service.interf.RewardScoreService;
import com.eshop.utils.AdminSessionUtils;
import com.eshop.utils.NumStringAddUtil;
import com.eshop.utils.Page;
import com.eshop.utils.SessionUtils;
import com.eshop.utils.UUIDUtil;

/**
 * @Description:商品类别控制controller
 *
 *version 1.0 2015-10-18 下午5:49:33
 */
@Controller
@RequestMapping("category")
public class CategoryController {
	
	CategoryService categoryService=new CategoryServiceImpl();
	GoodService goodService=new GoodServiceImpl();
	DeviceInfoServiceImpl deviceInfoService=new DeviceInfoServiceImpl(); 
	RewardScoreService rewardScoreService=new RewardScoreServiceImpl();
	WXcountsServiceImpl  wXcountsService=new WXcountsServiceImpl();
	
	@RequestMapping(value ="queryCategory")
	public void queryAllCategory(HttpServletRequest request,HttpServletResponse  response,VCategoryEntity  vCategory){
		List<Category> categoryList = null;
		JsonEntity jsonEntity = new JsonEntity(); 
		Map<String, Object> m = new HashMap<String, Object>();
		String company=AdminSessionUtils.getSessionAdminUserCommpany(request);
		try{
			if (vCategory!=null) {
				if (vCategory.getC_id()!=null) {
					m.put("C_id", vCategory.getC_id());
				}
				if (vCategory.getC_name()!=null) {
					m.put("c_name", vCategory.getC_name());
				}
				if (vCategory.getC_parent()!=null) {
					m.put("c_parent", vCategory.getC_parent());
				}
				if (vCategory.getOrder()!=null) {
					m.put("order", vCategory.getOrder());
				}
				if (vCategory.getSort()!=null) {
					m.put("sort", vCategory.getSort());
				}
			}
			categoryList = categoryService.findCategoryByPagination(vCategory.getPage(), vCategory.getRows(), m,company);
			 jsonEntity.setTotal(categoryService.getTotal(m,company));
			 jsonEntity.setRows(JSONArray.fromObject(categoryList).toString());
			String responseJson ="{\"total\":"+jsonEntity.getTotal()+" , \"rows\":"+JSONArray.fromObject(categoryList).toString()+"}";
			 response.setContentType("text/json;charset=utf-8");
			 response.getWriter().write(responseJson);
		}catch(Exception e )
		{
			e.printStackTrace();
		} 
	}
	@RequestMapping(value = "addCategory")
	public void addCategory(HttpServletResponse response,HttpServletRequest request,VCategoryEntity  vCategory){
	String company=AdminSessionUtils.getSessionAdminUserCommpany(request);
	Category category=null;
	if(vCategory!=null&&vCategory.getC_parent()!=null){
		
		 category=new Category();
		 /*category.setC_id(UUIDUtil.getuuid());*/
		 category.setC_name(vCategory.getC_name());
		 //根据父类别名称查询是否存在该父类别信息；如果不存在则新建父类别；
		 String P_id=categoryService.findIdByParentName(vCategory.getC_parent(),company);
		 if(P_id!=null){
			//获得一级分类最大的类别编号C_id;
			 String c_id=categoryService.findCategoryId(P_id,company);
			 //如果为该分类的第一个分类，则需要进行以下处理；
			 if(c_id==null){
				 category.setC_id(NumStringAddUtil.StringAddString(P_id));
			 }else{
				 category.setC_id(NumStringAddUtil.numStringAdd2(P_id, c_id));
			 }
			 category.setC_parent(P_id);
		 }else{
			 //获得一级分类最大的类别编号C_id;
			 String c_id=categoryService.findCategoryId("00",company);
			 //如果为一级分类的第一个分类，则需要进行以下处理；
			 if(c_id==null){
				 category.setC_id("01");
			 }else{
				 category.setC_id(NumStringAddUtil.numStringAdd(c_id));
			 }
			 category.setC_parent("00");
		 }
		 category.setC_desc(vCategory.getC_desc());
		 categoryService.insertCategory(category,company);
	}
	}
	
	@RequestMapping(value = "findAllCategory")
	public void findAllCategory(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String company=AdminSessionUtils.getSessionAdminUserCommpany(request);
		List<Category> list=categoryService.findAllCategory(company);
		 //用Json格式进行传递数据；
		JSONArray jsonArray=JSONArray.fromObject(list);
		response.setCharacterEncoding("UTF-8");
	/*	response.setContentType("text/json;charset=UTF-8");*/
		 PrintWriter out=response.getWriter();
		 out.write(jsonArray.toString());
		 System.out.print(jsonArray.toString());
	}
	@RequestMapping(value = "updateCategory")
	public void updatecategory(VCategoryEntity  vCategory,HttpServletResponse response,HttpServletRequest request)
	{
		Category category=null;
		HttpSession session=request.getSession();
		AdminUser user= (AdminUser) session.getAttribute("adminuser");
		String company=user.getCompany();
		try
		{
			 if (vCategory!=null&&vCategory.getC_id()!=null) {
				 
				 //先通过id查询出该商品的分类信息
				 category = categoryService.findCategoryById(vCategory.getC_id(),company);
				 category.setC_id(vCategory.getC_id());
				 category.setC_name(vCategory.getC_name());
				 //根据父类别名称查询是否存在该父类别信息；
				 String P_id=categoryService.findIdByParentName(vCategory.getC_parent(),company);
				 category.setC_parent(P_id);
				 category.setC_desc(vCategory.getC_desc());
				 //更新分类
				 categoryService.updateCategory(category,company);
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
	@RequestMapping(value = "deleteCategory")
	public void deleteCategory(String c_ids,HttpServletRequest request){
		String company=AdminSessionUtils.getSessionAdminUserCommpany(request);
		try {
			if(c_ids!=null){
				String[]  Cids = c_ids.split(",");
				for (String  c_id : Cids)
				 {
				categoryService.deleteCategory(c_id,company);
				 }
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@RequestMapping(value="queryCategoryById")
	public String queryCategoryById(HttpServletRequest request) throws Exception{
		List<Category> firstcategories=null;
		List<Category> secondCategories = null;
		List<Goods> rankGoodList=null;
		Page<Goods> page=null;
		String c_parent=request.getParameter("c_parent");
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
		
		secondCategories=categoryService.queryCategoryById(c_parent,company);
		firstcategories=categoryService.findByParentId(company);
		rankGoodList=goodService.queryRank(company);
		page=goodService.lookGoods(c_parent,company,pagenum);
		
		request.setAttribute("firstcategories", firstcategories);
		request.setAttribute("rankGoodList", rankGoodList);
		request.setAttribute("secondCategories", secondCategories);
		request.setAttribute("page",page);
		request.setAttribute("goodList", page.getList());
		request.setAttribute("c_id",-1);
		request.setAttribute("c_parent", c_parent);
		return "/pages/merchcategory";
	}
	
	
	
}
