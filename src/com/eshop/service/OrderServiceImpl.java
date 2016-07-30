package com.eshop.service;

import java.util.List;
import java.util.Map;

import com.eshop.dao.OrderDao;
import com.eshop.dao.InStockDao;
import com.eshop.entrity.Order;
import com.eshop.entrity.InStock;
import com.eshop.entrity.view.VoOrderEntity;
import com.eshop.service.interf.OrderService;

 /**
  * 
  * <p>Title：        PurchaseServiceImpl<p>
  * <p>Description:  采购单Service的实现类，用于处理采购单的信息<p>
  * @date:           2015年11月2日下午8:07:15
  * @author:         ysl
  * @version         1.0
  */
public class OrderServiceImpl  implements OrderService{

	OrderDao orderDao = new OrderDao();
	
	@Override
	public List<Order> findPurchaseByPagination(int currentPage,
			int pageSize, Map<String, Object> m) {
		 
		return orderDao.findPurchaseByPagination(currentPage, pageSize, m);
	}

	 
	@Override
	public int getTotal(Map<String, Object> m) throws Exception {
		return orderDao.getTotal(m);
	}

	@Override
	public Order findOrderById(String order_id,String company) throws Exception{
		return orderDao.findOrderById(order_id,company);
	}

	@Override
	public void updateOrder(Order order,String company)throws Exception {
		orderDao.updateOrder(order,company);
	}
	/*
	@Override
	public void insertPurchase(Purchase purchase) throws Exception {
		purchaseDao.insertPurchase(purchase);
	}
*/


	/* (non-Javadoc)
	 * @see com.eshop.service.interf.OrderService#save(com.eshop.entrity.Order)
	 */
	@Override
	public void save(Order order, String company) {
		orderDao.save(order,company);
		
	}


	/* (non-Javadoc)
	 * @see com.eshop.service.interf.OrderService#getAllByUser(int, java.lang.String, int, int)
	 */
	@Override
	public List<Order> getAllByUser(int user_id, String company,
			int currentPage, int pageSize) {
		
		return orderDao.getAllByUser(user_id, company,currentPage, pageSize) ;
	}


	@Override
	public List<VoOrderEntity> getVoOrderList(Map<String, Object> m) {
		return orderDao.getVoOrderList(m);
	}


	/* (non-Javadoc)
	 * @see com.eshop.service.interf.OrderService#getTotalCount(int, java.lang.String)
	 */
	@Override
	public int getTotalCount(int user_id, String company) {
		
		return orderDao.getTotalCount(user_id,company);
	}
	
	
	
	
 
}
