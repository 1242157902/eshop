package com.eshop.service.interf;

import java.util.List;
import java.util.Map;

import com.eshop.entrity.Stock;

/**
 * 
 * <p>Title：        StockService<p>
 * <p>Description: 库存Service的接口 <p>
 * @date:           2015年11月1日下午1:05:13
 * @author:         ysl
 * @version         1.0
 */
public interface StockService {
	
	 /**
	  * 
	  * @return:       List<Stock> 
	  * @param currentPage
	  * @param pageSize
	  * @param m
	  * @return
	  * <p>Description: 分页查询库存信息<p>
	  * @date:          2015年11月1日下午2:35:56
	  * @author         ysl
	  */
	public List<Stock> findStockByPagination(int currentPage, int pageSize  ,Map<String ,Object> m);
	
	/**
	 * 
	 * @return:       List<Stock> 
	 * @return
	 * <p>Description:查询所有库存中的信息 <p>
	 * @date:          2015年11月3日下午1:50:31
	 * @author         ysl
	 * @param company 
	 */
	public List<Stock> findAllStock(String company);
	
	/**
	 * 
	 * @return:       int 
	 * @param m
	 * @return
	 * @throws Exception
	 * <p>Description: 查询库存信息中，分页的记录数<p>
	 * @date:          2015年11月1日下午7:08:18
	 * @author         ysl
	 */
	public int getTotal(Map<String ,Object> m) throws Exception ;
	
	/**
	 * 
	 * @return:       Stock 
	 * @param good_id
	 * @return
	 * @throws Exception
	 * <p>Description: 通过库存商品的id来查询该库存<p>
	 * @date:          2015年11月1日下午9:39:33
	 * @author         ysl
	 * @param company 
	 */
	public Stock findStockById(String good_id,String company) throws Exception ;

	/**
	 * 
	 * @return:       void 
	 * @param stock
	 * @throws Exception
	 * <p>Description: 更新商品库存信息<p>
	 * @date:          2015年11月1日下午9:55:49
	 * @author         ysl
	 * @param company 
	 */
	public void updateStock(Stock stock,String company)throws Exception;
	
	/**
	 * 
	 * @return:       void 
	 * @param stock
	 * @throws Exception
	 * <p>Description: 插入库存<p>
	 * @date:          2015年11月3日下午6:09:56
	 * @author         ysl
	 */
	public void insertStock(Stock stock,String company)throws Exception;
		
}
