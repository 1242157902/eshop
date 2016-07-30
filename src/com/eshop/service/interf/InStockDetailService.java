package com.eshop.service.interf;

import java.util.List;

import com.eshop.entrity.InStockDetail;

/**
 * 
 * <p>Title：        InStockDetailService<p>
 * <p>Description:  <p>
 * @date:           2015年11月10日下午8:22:08
 * @author:         ysl
 * @version         1.0
 */
public interface InStockDetailService {
	
	
 
	public List<InStockDetail> findInStockDetailByInStockId(String inStock_id,String company) throws Exception ;

	public void insertInStockDetail(InStockDetail inStockDetail,String company);

 

	 
 
		
}
