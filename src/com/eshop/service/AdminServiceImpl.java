package com.eshop.service;

import java.util.List;
import java.util.Map;

import com.eshop.dao.AdminUserDao;
import com.eshop.entrity.AdminUser;
import com.eshop.service.interf.AdminUserService;

public class AdminServiceImpl  implements AdminUserService{
	
	AdminUserDao  adminUserDao = new AdminUserDao();
	
	@Override
	public List<AdminUser> findPurchaseByPagination(int currentPage,
			int pageSize, Map<String, Object> m) {
		return adminUserDao.findPurchaseByPagination(currentPage, pageSize, m);
	}

	@Override
	public int getTotal(Map<String, Object> m) throws Exception {
		return adminUserDao.getTotal(m);
	}

	@Override
	public AdminUser findAdminUserById(String order_id, String company)
			throws Exception {
		return adminUserDao.findAdminUserById(order_id, company)
				;
	}

	@Override
	public void updateAdminUser(AdminUser adminUser, String company)
			throws Exception {
		adminUserDao.updateAdminUser(adminUser, company);
	}

	@Override
	public void insertAdminUser(AdminUser adminUser,String company) throws Exception {
		adminUserDao.insertAdminUser(adminUser, company);
	}

}
