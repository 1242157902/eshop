package com.eshop.service;

import java.util.List;
import java.util.Map;

import com.eshop.dao.CategoryDao;
import com.eshop.entrity.Category;
import com.eshop.service.interf.CategoryService;

public class CategoryServiceImpl implements CategoryService {
	
	CategoryDao categoryDao=new CategoryDao();
	
	@Override
	public List<Category> findCategoryByPagination(int currentPage,
			int pageSize, Map<String, Object> m,String company) {
		return categoryDao.findCategoryByPagination(currentPage, pageSize, m,company);
	}

	@Override
	public int getTotal(Map<String, Object> m,String company) throws Exception {
		return categoryDao.getTotal(m,company);
	}

	@Override
	public Category findCategoryById(String c_id,String company) throws Exception {
		return categoryDao.findCategoryById(c_id,company);
	}

	@Override
	public void updateCategory(Category category,String company) throws Exception {
		categoryDao.updateCategory(category,company);
	}

	@Override
	public void deleteCategory(String c_ids, String company) throws Exception {
		categoryDao.delete(c_ids,company);
	}

	@Override
	public void insertCategory(Category category,String company) {
		categoryDao.addCategory(category,company);
	}

	@Override
	public String findCategoryByName(String category_name, String company) {
		return categoryDao.findCategoryByName(category_name, company);
	}

	@Override
	public String findIdByParentName(String c_parent,String company) {
		return categoryDao.findIdByParentName(c_parent,company);
	}

	@Override
	public List<Category> findAllCategory(String company) {
		return categoryDao.findAllCategory(company);
	}

	@Override
	public List<Category> findByParentId(String company) {
		return categoryDao.findByParentId(company);
	}

	@Override
	public List<Category> queryCategoryById(String c_parent, String company) {
		return categoryDao.queryCategoryById(c_parent,company);
	}

	//获得一级分类最大的类别编号C_id;
	@Override
	public String findCategoryId(String p_id,String company) {
		return categoryDao.findCategoryId(p_id,company);
	}

	@Override
	public List<Category> queryCategoryByKeyWord(String keyword, String company) {
		return categoryDao.queryCategoryByKeyWord(keyword,company);
	}

}
