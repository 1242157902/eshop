package com.eshop.service.interf;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.eshop.entrity.Deviceinfo;
import com.eshop.entrity.Order;
import com.eshop.entrity.RewardScore;
import com.eshop.entrity.Stock;
import com.eshop.entrity.User;

public interface RewardScoreService {

	public RewardScore getScoreBynickname(String nickname, String company);
	
	public void update(RewardScore rewardScore, String company);
	
	
	public void insertRewardScore(RewardScore rewardScore, String company);
	
	public RewardScore getRewardScoreBynickname(String nickname, String company);

	public List<RewardScore> findStockByPagination(Integer page, Integer rows,
			Map<String, Object> m);
	public List<RewardScore> getAllImportScore(String company) throws SQLException;

	public Integer getTotal(Map<String, Object> m);
	
	//获取用户的手机号
	public Deviceinfo getPhoneByNick(String nickName);
	
	//获取用户
	public User getUserByPhone(String telephone,String company);
	
	
   //获取地址和手机姓名
	public Order getOrderByUserId(int user_id,String company);
	
	
	//订单表里面有nickname,其实你只要用这个方法就可以了
	public Order getOrderByNickName(String nickName,String company);
	 
}
