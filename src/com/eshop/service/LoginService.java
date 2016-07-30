package com.eshop.service;

import com.eshop.dao.LoginDao;
import com.eshop.entrity.MobileModel;
import com.eshop.entrity.User;

/**
 * @author wzq
 *
 *version 1.0 2015-10-19 上午9:59:32
 */
public class LoginService {
  LoginDao dao=new LoginDao();

/**
 * @param phonenum
 * @return
 */
public MobileModel getmbbynum(String phonenum) {
	
	return dao.getmbbynum(phonenum);
}

/**
 * @param phonenum
 * @param uninno
 * @return
 */
public User getuser(String phonenum, String uninno) {
	// TODO Auto-generated method stub
	return dao.getuser(phonenum,uninno);
}

/**
 * @param telephone
 * @param password
 * @return
 */
public User login(String telephone, String password) {
	
	return dao.login(telephone,password);
}

/**
 * @param user
 */
public void save(User user) {
dao.save(user);
}

/**
 * @param user
 */
public void update(User user) {
	dao.update(user);
	
}
	
	
	
}
