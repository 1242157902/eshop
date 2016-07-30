package com.eshop.service.interf;

import java.util.List;
import java.util.Map;

import com.eshop.entrity.Category;
import com.eshop.entrity.Stock;


/**
 * Description:类别服务
 * @author Jane
 *
 */
public interface CategoryService {
	 
	//分页查询类别信息；
	public List<Category> findCategoryByPagination(int currentPage, int pageSize,Map<String ,Object> m, String company);
	
	//查询总记录数；
	public int getTotal(Map<String ,Object> m,String company)throws Exception;
	
	//通过类名查询类别信息；
	public Category findCategoryById(String c_id,String company)throws Exception;
	
	//更新分类信息
	public void updateCategory(Category category,String company)throws Exception;

	public void deleteCategory(String c_ids, String company)throws Exception;
	
	//增加分类
	public void insertCategory(Category category,String company);
	
	//通过类别名查询类别信息
	public String findCategoryByName(String category_name, String company);
	
	//根据父类别名称查询是否存在该父类别信息
	public String findIdByParentName(String c_parent,String company);

	public List<Category> findAllCategory(String company);
	
	//查看一级类别
	public List<Category> findByParentId(String company);
	
	
	//查看二级类别
	public List<Category> queryCategoryById(String c_parent,String company);
	
	//获得一级分类最大的类别编号C_id;
	public String findCategoryId(String p_id,String company);
	
	//根据关键词查询二级类别
	public List<Category> queryCategoryByKeyWord(String keyword,String company);
	
}
