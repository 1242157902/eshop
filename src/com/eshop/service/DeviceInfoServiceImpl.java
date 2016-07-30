package com.eshop.service;

import com.eshop.dao.WXcountsDao;
import com.eshop.entrity.Deviceinfo;

/**
 * @author  JYM
 * 2015-12-2 下午7:44:21
 */
public class DeviceInfoServiceImpl {
	
	WXcountsDao wxDao=new WXcountsDao();

	/**
	 * @param telephone
	 * @return
	 */
	public Deviceinfo getNameByPhone(String telephone) {
		
		return wxDao.getNameByPhone(telephone);
	}

}
