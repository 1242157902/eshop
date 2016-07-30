package com.eshop.service.interf;

import java.sql.SQLException;

import com.eshop.entrity.AdminUser;
import com.eshop.entrity.MobileModel;
import com.eshop.entrity.User;


/**
 * 管理员服务service类
 * @author Jane
 *
 */
public interface AdminLoginSevice {
	
	//管理员登录；
	public AdminUser login(String telephone,String password, String companyId)throws SQLException;

	//通过电话号码获取
	public AdminUser getmbbynum(String telephone)throws SQLException;
}
