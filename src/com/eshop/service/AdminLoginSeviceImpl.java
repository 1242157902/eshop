package com.eshop.service;

import java.sql.SQLException;

import com.eshop.dao.AdminLoginDao;
import com.eshop.entrity.AdminUser;
import com.eshop.service.interf.AdminLoginSevice;

/**
 * 管理员登录实现类
 * @author Jane
 *
 */
public class AdminLoginSeviceImpl implements AdminLoginSevice {

	AdminLoginDao Dao=new AdminLoginDao();
	
	@Override
	public AdminUser login(String telephone, String password,String companyId) throws SQLException {
		
		return Dao.login(telephone, password,companyId);
	}

	@Override
	public AdminUser getmbbynum(String telephone) throws SQLException {
		return Dao.getmbbynum(telephone);
	}

}
