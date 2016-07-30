package com.eshop.service;

import java.util.List;
import java.util.Map;

import com.eshop.dao.InStockDetailDao;
import com.eshop.entrity.InStockDetail;
import com.eshop.service.interf.InStockDetailService;

 /**
  * 
  * <p>Title：        PurchaseServiceImpl<p>
  * <p>Description:  采购单Service的实现类，用于处理采购单的信息<p>
  * @date:           2015年11月2日下午8:07:15
  * @author:         ysl
  * @version         1.0
  */
public class InStockDetailServiceImpl  implements InStockDetailService{

	InStockDetailDao inStockDetailDao = new InStockDetailDao();

	@Override
	public List<InStockDetail> findInStockDetailByInStockId(String inStock_id,String company) throws Exception  {
		
		return inStockDetailDao.findInStockDetailByInStockId(inStock_id,company);
	}

	@Override
	public void insertInStockDetail(InStockDetail inStockDetail,String company) {
		inStockDetailDao.insertInStockDetail(inStockDetail,company);
	}
	

 
}
