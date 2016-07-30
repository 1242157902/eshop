package com.eshop.service.interf;

import java.util.List;
import java.util.Map;

import com.eshop.entrity.Order;
import com.eshop.entrity.InStock;
import com.eshop.entrity.view.VoOrderEntity;


/**
 * 
 * <p>Title：        PurchaseService<p>
 * <p>Description: 采购单Service的接口 <p>
 * @date:           2015年11月2日下午8:06:31
 * @author:         ysl
 * @version         1.0
 */
public interface OrderService {
	
	
	 /**
	  * 
	  * @return:       List<Order> 
	  * @param currentPage
	  * @param pageSize
	  * @param m
	  * @return
	  * <p>Description: 分页显示Order的订单列表<p>
	  * @date:          2015年11月4日上午11:19:35
	  * @author         ysl
	  */
	public List<Order> findPurchaseByPagination(int currentPage, int pageSize  ,Map<String ,Object> m);
	
 
	public int getTotal(Map<String ,Object> m) throws Exception ;
	

	public Order findOrderById(String order_id,String company) throws Exception ;

	 
	public void updateOrder(Order order,String company)throws Exception;


	/**
	 * @param order
	 * @param company 
	 */
	public void save(Order order, String company);


	/**
	 * @param user_id
	 * @param company
	 * @param parseInt
	 * @param i
	 * @return
	 */
	public List<Order> getAllByUser(int user_id, String company, int currentPage,
			int pageSize);

	
	/**
	 * 
	 * @return:       List<VoOrderEntity> 
	 * @param m
	 * @return
	 * <p>Description: 用于导出该月的订单<p>
	 * @date:          2015年12月4日下午2:13:47
	 * @author         ysl
	 */
	public List<VoOrderEntity> getVoOrderList(Map<String, Object> m);


	/**
	 * @param user_id
	 * @param company
	 * @return
	 */
	public int getTotalCount(int user_id, String company);
		
	/* 
	public void insertPurchase(Purchase purchase)throws Exception;*/
}
