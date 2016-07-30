package com.eshop.service;

import com.eshop.dao.LoginDao;
import com.eshop.dao.WXcountsDao;
import com.eshop.entrity.WXcounts;
import com.eshop.entrity.WxScoreTrade;


/**
 * @author  JYM
 * 2015-11-23 下午8:35:19
 */
public class WXcountsServiceImpl {
	
	
	WXcountsDao wxDao=new WXcountsDao();
	LoginDao loginDao=new LoginDao();

	/**
	 * @param string
	 * @return
	 */
	public int getScoreByUser(String phone) {
		
		WXcounts wXcounts=wxDao.getScoreByPhone(phone);
		
		int totalnum=wXcounts.getScore_count();
		
		return totalnum;
	}

	/**
	 * @param telephone
	 * @return
	 */
	public WXcounts getWSByUser(String telephone) {
		return  wxDao.getScoreByPhone(telephone);
		
	}

	/**
	 * @param wXcounts
	 */
	public void updateWS(WXcounts wXcounts) {
		wxDao.updateWS(wXcounts);
		
	}

	/**
	 * @param wxScoreTrade
	 */
	public void saveTrade(WxScoreTrade wxScoreTrade) {
		wxDao.saveTrade(wxScoreTrade);
	
		
	}

	/**
	 * @param telephone
	 * @param ws
	 */
	public void updateInTrade(String telephone, int ws) {
		wxDao.updateInTrade( telephone,  ws);
		
	}
	
	
	

}
