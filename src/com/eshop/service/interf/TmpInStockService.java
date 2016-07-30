package com.eshop.service.interf;

import java.util.List;
import java.util.Map;

import com.eshop.entrity.Stock;
import com.eshop.entrity.TempInStock;

 /**
  * 
  * <p>Title：        TmpInStockService<p>
  * <p>Description:  <p>
  * @date:           2015年11月10日下午5:21:36
  * @author:         ysl
  * @version         1.0
  */
public interface TmpInStockService {
	
	
 
 
	/*public List<TempInStock> findPurchaseDetailByPurchaseId(String purchase_id,String company) throws Exception ;*/
 
	/**
	 * 
	 * @return:       TempInStock 
	 * @param tmp_inStock_id
	 * @param company
	 * @return
	 * @throws Exception
	 * <p>Description: <p>
	 * @date:          2015年11月10日下午7:18:28
	 * @author         ysl
	 */
	public TempInStock findTmpInStockById(String tmp_inStock_id,String company) throws Exception ;
	
	/**
	 * 
	 * @return:       void 
	 * @param tempInStock
	 * @param company
	 * @throws Exception
	 * <p>Description: <p>
	 * @date:          2015年11月10日下午7:18:33
	 * @author         ysl
	 */
	public void updateTempInStock(TempInStock tempInStock,String company)throws Exception;
	/**
	 * 
	 * @return:       void 
	 * @param tempInStock
	 * @param company
	 * <p>Description: 插入一条记录<p>
	 * @date:          2015年11月10日下午3:50:40
	 * @author         ysl
	 */
	public void insertTempInStock(TempInStock tempInStock,String company);

	/**
	 * 
	 * @return:       List<TempInStock> 
	 * @param currentPage
	 * @param pageSize
	 * @param m
	 * @return
	 * <p>Description:分页显示 <p>
	 * @date:          2015年11月10日下午4:14:36
	 * @author         ysl
	 */
	public List<TempInStock> findTmpInStockByPagination(int currentPage, int pageSize  ,Map<String ,Object> m);
	
	/**
	 * 
	 * @return:       int 
	 * @param m
	 * @return
	 * @throws Exception
	 * <p>Description: 总记录数<p>
	 * @date:          2015年11月10日下午4:14:55
	 * @author         ysl
	 */
	public int getTotal(Map<String ,Object> m) throws Exception ;
	
	/**
	 * 
	 * @return:       void 
	 * @throws Exception
	 * <p>Description: 删除临时表中的数据<p>
	 * @date:          2015年11月10日下午7:48:36
	 * @author         ysl
	 */
	public void deleteTmpInStock(String company)throws Exception;
	
}
