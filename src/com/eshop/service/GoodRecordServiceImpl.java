package com.eshop.service;

import java.util.List;
import java.util.Map;

import com.eshop.dao.GoodRecordDao;
import com.eshop.entrity.GoodsRecord;
import com.eshop.service.interf.GoodRecordService;

/**
 * 商品价格和积分记录服务类实现类
 * @author Jane
 *
 */
public class GoodRecordServiceImpl implements GoodRecordService {

	GoodRecordDao recordDao=new GoodRecordDao();
	
	@Override
	public List<GoodsRecord> findgoodRecordByPagination(int currentPage, int pageSize,Map<String ,Object> m, String company) {
		return recordDao.findgoodRecordByPagination(currentPage,pageSize,m,company);
	}

	@Override
	public int getTotal(Map<String, Object> m, String company) {
		return recordDao.getTotal(m,company);
	}

	@Override
	public void addGoodRecord(GoodsRecord goodRecord, String company,int userID) {
		recordDao.addGoodRecord(goodRecord,company,userID);
	}

}
