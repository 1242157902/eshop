package com.eshop.service;

import java.util.List;
import java.util.Map;

import com.eshop.dao.TmpInStockDetailDao;
import com.eshop.entrity.Stock;
import com.eshop.entrity.TempInStock;
import com.eshop.service.interf.TmpInStockService;

 /**
  * 
  * <p>Title：        PurchaseServiceImpl<p>
  * <p>Description:  采购单Service的实现类，用于处理采购单的信息<p>
  * @date:           2015年11月2日下午8:07:15
  * @author:         ysl
  * @version         1.0
  */
public class TmpInStockDetailServiceImpl  implements TmpInStockService{

	TmpInStockDetailDao tmpInStockDetailDao = new TmpInStockDetailDao();

/*	@Override
	public List<InStockDetail> findPurchaseDetailByPurchaseId(
			String purchase_id,String company) throws Exception {
		
		return PurchaseDetailDao.findPurchaseDetailByPurchaseId(purchase_id,company);
	}*/

	@Override
	public void insertTempInStock(TempInStock tempInStock,String company){
		tmpInStockDetailDao.insertTempInStock(tempInStock,company);
	}

	@Override
	public List<TempInStock> findTmpInStockByPagination(int currentPage,
			int pageSize, Map<String, Object> m) {
		return tmpInStockDetailDao.findTmpInStockByPagination(currentPage, pageSize, m);
	}

	@Override
	public int getTotal(Map<String, Object> m) throws Exception {
		return tmpInStockDetailDao.getTotal(m);
	}

	@Override
	public TempInStock findTmpInStockById(String tmp_inStock_id, String company)
			throws Exception {
		return tmpInStockDetailDao.findTmpInStockById(tmp_inStock_id, company);
	}

	@Override
	public void updateTempInStock(TempInStock tempInStock, String company)
			throws Exception {
		tmpInStockDetailDao.updateTempInStock(tempInStock, company);
	}

	@Override
	public void deleteTmpInStock(String company) throws Exception {
		tmpInStockDetailDao.deleteTmpInStock(company);
	}
	
}
