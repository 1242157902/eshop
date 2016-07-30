package com.eshop.service.interf;

import java.util.List;
import java.util.Map;

import com.eshop.entrity.GoodsRecord;


/**
 * 商品价格和积分记录服务类
 * @author Jane
 *
 */
public interface GoodRecordService {

	//分页查询商品价格和积分记录
	List<GoodsRecord> findgoodRecordByPagination(int currentPage, int pageSize,Map<String ,Object> m, String company);
	
	//查询记录的总数
	int getTotal(Map<String, Object> m, String company);

	//添加新的商品记录
	void addGoodRecord(GoodsRecord goodRecord, String company, int userID);
	
	
}
