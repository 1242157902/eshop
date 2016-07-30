package com.eshop.service;

import java.util.List;
import java.util.Map;

import com.eshop.dao.OutStockDao;
import com.eshop.entrity.OutStock;
import com.eshop.service.interf.OutStockService;

 /**
  * 
  * <p>Title：        OutStockServiceImpl<p>
  * <p>Description: 出库的service类<p>
  * @date:           2015年11月4日下午3:07:23
  * @author:         ysl
  * @version         1.0
  */
public class OutStockServiceImpl  implements OutStockService{

	OutStockDao outStockDao = new OutStockDao();

	@Override
	 public void InsertOutStock(OutStock outStock,String company) {
		outStockDao.InsertOutStock(outStock,company);
	}

	@Override
	 public List<OutStock> findOutStockByPagination(int currentPage, int pageSize  ,Map<String ,Object> m) {
		return outStockDao.findOutStockByPagination(currentPage, pageSize, m);
	}

	@Override
	public int getTotal(Map<String ,Object> m) throws Exception {
		return outStockDao.getTotal(m);
	}

	@Override
	public OutStock findOutStockById(String out_id,String company) {
		return outStockDao.findOutStockById(out_id,company);
	}

	@Override
	public void updateOutStock(OutStock outStock,String company) {
		outStockDao.updateOutStock(outStock,company);
	}
  
}
