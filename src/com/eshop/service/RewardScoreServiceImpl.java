package com.eshop.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.eshop.dao.LoginDao;
import com.eshop.dao.OrderDao;
import com.eshop.dao.RewardScoreDao;
import com.eshop.dao.WXcountsDao;
import com.eshop.entrity.Deviceinfo;
import com.eshop.entrity.Order;
import com.eshop.entrity.RewardScore;
import com.eshop.entrity.User;
import com.eshop.service.interf.RewardScoreService;


/**
 * @author  JYM
 * 2015-12-2 下午6:01:32
 */
public class RewardScoreServiceImpl  implements RewardScoreService{

	RewardScoreDao rewardScoreDao=new RewardScoreDao();
	WXcountsDao wxDao=new WXcountsDao();
	LoginDao userDao=new LoginDao();
	OrderDao orderDao=new OrderDao();
	/**
	 * @param nickname
	 * @param company
	 * @return
	 */
	public RewardScore getScoreBynickname(String nickname, String company) {
		return rewardScoreDao.getScoreBynickname(nickname, company);
	}

	/**
	 * @param rewardScore
	 * @param string 
	 */
	public void update(RewardScore rewardScore, String company) {
		rewardScoreDao.update(rewardScore,company);
		
	}

	@Override
	public void insertRewardScore(RewardScore rewardScore, String company) {
		rewardScoreDao.insertRewardScore(rewardScore,company);
	}

	@Override
	public RewardScore getRewardScoreBynickname(String nickname, String company) {
		return rewardScoreDao.getRewardScoreBynickname(nickname, company);
	}

	@Override
	public List<RewardScore> findStockByPagination(Integer page, Integer pageSize,
			Map<String, Object> m) {
		return rewardScoreDao.findStockByPagination(page,pageSize,m);
	}

	@Override
	public Integer getTotal(Map<String, Object> m) {
		return rewardScoreDao.getTotal(m);
	}

	@Override
	public Deviceinfo getPhoneByNick(String nickName) {
		
		return  wxDao.getPhoneByNick(nickName);
	}

	
	@Override
	public User getUserByPhone(String telephone, String company) {
		return userDao.getuser(telephone, company);
	}

	
	@Override
	public Order getOrderByUserId(int user_id, String company) {
		
		return orderDao.getOrderByUserId( user_id, company);
	}

	/* (non-Javadoc)
	 * @see com.eshop.service.interf.RewardScoreService#getOrderByNickName(java.lang.String, java.lang.String)
	 */
	@Override
	public Order getOrderByNickName(String nickName, String company) {
		
		return orderDao.getOrderByNickName(nickName, company);
	}

	@Override
	public List<RewardScore> getAllImportScore(String company) throws SQLException {
		return rewardScoreDao.getAllImportScore(company);
	}

	 

	

}
