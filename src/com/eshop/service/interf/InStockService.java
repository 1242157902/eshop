package com.eshop.service.interf;

import java.util.List;
import java.util.Map;

import com.eshop.entrity.InStock;


 /**
  * 
  * <p>Title：        InStockService<p>
  * <p>Description:  <p>
  * @date:           2015年11月10日下午8:36:01
  * @author:         ysl
  * @version         1.0
  */
public interface InStockService {
	
	
	 /**
	  * 
	  * @return:       List<InStock> 
	  * @param currentPage
	  * @param pageSize
	  * @param m
	  * @return
	  * <p>Description: 分页显示条件查询入库单信息<p>
	  * @date:          2015年11月3日上午10:43:12
	  * @author         ysl
	  */
	 public List<InStock> findInStockByPagination(int currentPage, int pageSize  ,Map<String ,Object> m);
	 
	 /**
	  * 
	  * @return:       int 
	  * @param m
	  * @return
	  * @throws Exception
	  * <p>Description: 获取分页显示条件查询入库单的总记录数<p>
	  * @date:          2015年11月3日上午10:49:14
	  * @author         ysl
	  */
	public int getTotal(Map<String ,Object> m) throws Exception ;
	
 
	/**
	 * 
	 * @return:       InStock 
	 * @param inStock_id
	 * @param company
	 * @return
	 * @throws Exception
	 * <p>Description: 根据instock_id查询该InStock<p>
	 * @date:          2015年11月11日下午7:50:36
	 * @author         ysl
	 */
	public InStock findInStockById(String  inStock_id,String company) throws Exception ;

	
	/**
	 * 
	 * @return:       void 
	 * @param purchase
	 * @param company
	 * @throws Exception
	 * <p>Description: <p>
	 * @date:          2015年11月11日下午8:52:25
	 * @author         ysl
	 */
	public void updateInStock(InStock inStock,String company)throws Exception;
		
	/**
	 * 
	 * @return:       void 
	 * @param purchase
	 * @throws Exception
	 * <p>Description: 插入到采购表中<p>
	 * @date:          2015年11月4日上午9:55:48
	 * @author         ysl
	 */
	public void insertPurchase(InStock purchase,String company)throws Exception;
}
