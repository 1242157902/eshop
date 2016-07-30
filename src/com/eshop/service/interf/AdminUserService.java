package com.eshop.service.interf;

import java.util.List;
import java.util.Map;

import com.eshop.entrity.AdminUser;
import com.eshop.entrity.User;

/**
 * 
 * <p>Title：        AdminUserService<p>
 * <p>Description: 后台用户管理 <p>
 * @date:           2015年11月23日下午8:30:48
 * @author:         ysl
 * @version         1.0
 */
public interface AdminUserService {

	public List<AdminUser> findPurchaseByPagination(int currentPage, int pageSize  ,Map<String ,Object> m);
	
	 
	public int getTotal(Map<String ,Object> m) throws Exception ;
	

	public AdminUser findAdminUserById(String order_id,String company) throws Exception ;

	 
	public void updateAdminUser(AdminUser adminUser,String company)throws Exception;
		
	
	public void insertAdminUser(AdminUser adminUser,String company)throws Exception;


	
	
	
}
