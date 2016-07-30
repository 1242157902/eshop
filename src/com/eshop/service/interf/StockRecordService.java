package com.eshop.service.interf;

import java.util.List;
import java.util.Map;

import com.eshop.entrity.StockRecord;

public interface StockRecordService {
	
	public List<StockRecord> findStockRecordByPagination(int currentPage, int pageSize  ,Map<String ,Object> m);
	public int getTotal(Map<String ,Object> m) throws Exception ;
	public void insertStockRecord(StockRecord stockRecord,String company)throws Exception;
}
