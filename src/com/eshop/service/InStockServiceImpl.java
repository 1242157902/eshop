package com.eshop.service;

import java.util.List;
import java.util.Map;

import com.eshop.dao.InStockDao;
import com.eshop.entrity.InStock;
import com.eshop.service.interf.InStockService;

 /**
  * 
  * <p>Title：        PurchaseServiceImpl<p>
  * <p>Description:  采购单Service的实现类，用于处理采购单的信息<p>
  * @date:           2015年11月2日下午8:07:15
  * @author:         ysl
  * @version         1.0
  */
public class InStockServiceImpl  implements InStockService{

	InStockDao inStockDao = new InStockDao();


	@Override
	public List<InStock> findInStockByPagination(int currentPage, int pageSize,
			Map<String, Object> m) {
		return inStockDao.findInStockByPagination(currentPage, pageSize, m);
	}

	@Override
	public int getTotal(Map<String, Object> m) throws Exception {
		return inStockDao.getTotal(m);
	}

	@Override
	public InStock findInStockById(String  inStock_id,String company) throws Exception  {
		return inStockDao.findInStockById(inStock_id, company);
	}

	@Override
	public void updateInStock(InStock inStock,String company)throws Exception {
		inStockDao.updateInStock(inStock, company);
	}

	@Override
	public void insertPurchase(InStock purchase,String company) throws Exception {
		inStockDao.insertPurchase(purchase,company);
	}

 
}
