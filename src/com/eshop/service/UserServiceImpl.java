package com.eshop.service;

import com.eshop.dao.UserDao;
import com.eshop.entrity.User;
import com.eshop.service.interf.UserService;

public class UserServiceImpl implements UserService {
    UserDao userDao=new UserDao();
	@Override
	public void updateInfo(User user, String company) throws Exception {
		userDao.updateInfo(user,company);
		
	}
	@Override
	public User getInfoById(int user_id, String company) throws Exception {
		
		return userDao.getInfoById(user_id,company);
	}

}
