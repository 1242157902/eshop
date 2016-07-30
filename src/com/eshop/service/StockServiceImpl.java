package com.eshop.service;

import java.util.List;
import java.util.Map;

import com.eshop.dao.StockDao;
import com.eshop.entrity.Stock;
import com.eshop.service.interf.StockService;

/**
 * 
 * <p>Title：        StockServiceImpl<p>
 * <p>Description: 处理库存信息的业务 <p>
 * @date:           2015年11月1日下午1:02:42
 * @author:         ysl
 * @version         1.0
 */
public class StockServiceImpl  implements StockService{

	 StockDao stockDao = new StockDao();

	@Override
	public List<Stock> findStockByPagination(int currentPage, int pageSize,
			Map<String, Object> m) {
		 
		return stockDao.findStockByPagination(currentPage, pageSize, m);
	}

	@Override
	public int getTotal(Map<String, Object> m) throws Exception {
		return stockDao.getTotal(m);
	}

	@Override
	public Stock findStockById(String good_id,String company) throws Exception {
		return stockDao.findStockById(good_id,company);
	}

	@Override
	public void updateStock(Stock stock,String company) throws Exception {
		stockDao.updateStock(stock,company);
	}

	@Override
	public List<Stock> findAllStock(String company) {
		return stockDao.findAllStock(company);
	}

	@Override
	public void insertStock(Stock stock,String company) throws Exception {
		stockDao.insertStock(stock,company);
	}

 
}
