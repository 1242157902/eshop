package com.eshop.service;

import java.util.List;
import java.util.Map;

import com.eshop.dao.StockRecordDao;
import com.eshop.entrity.StockRecord;
import com.eshop.service.interf.StockRecordService;

public class StockRecordServiceImpl implements StockRecordService {

	StockRecordDao stockRecordDao = new  StockRecordDao();
	@Override
	public List<StockRecord> findStockRecordByPagination(int currentPage,
			int pageSize, Map<String, Object> m) {
		return stockRecordDao.findStockRecordByPagination(currentPage, pageSize, m);
	}

	@Override
	public int getTotal(Map<String, Object> m) throws Exception {
		return stockRecordDao.getTotal(m);
	}

	@Override
	public void insertStockRecord(StockRecord stockRecord, String company)
			throws Exception {
		stockRecordDao.insertStockRecord(stockRecord,company);
	}

}
