package com.eshop.service;

import java.util.List;
import java.util.Set;

import com.eshop.dao.UserAdressDao;
import com.eshop.entrity.UserAdress;


/**
 * @author  JYM
 * 2015-12-2 下午5:22:40
 */
public class UserAdressServiceImpl {
	 
	UserAdressDao userAdressDao=new UserAdressDao();

	/**
	 * @param user_id
	 * @param company
	 * @return
	 */
	public List<UserAdress> findByUserid(int user_id, String company) {
		
		return userAdressDao.findByUserid(user_id, company);
	}

	/**
	 * @param userAdress
	 * @param company
	 */
	public void save(UserAdress userAdress, String company) {
		userAdressDao.save(userAdress,company);
		
	}

}
