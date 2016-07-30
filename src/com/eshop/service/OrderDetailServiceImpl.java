package com.eshop.service;

import java.util.List;
import java.util.Set;

import com.eshop.dao.OrderDetailDao;
import com.eshop.dao.InStockDetailDao;
import com.eshop.entrity.OrderDetail;
import com.eshop.service.interf.OrderDetailService;
 
public class OrderDetailServiceImpl  implements OrderDetailService{

	OrderDetailDao orderDetailDao = new OrderDetailDao();
	
	@Override
	public List<OrderDetail> findOrderDetailByOrderId(String order_id,String company) throws Exception {
		
		return orderDetailDao.findOrderDetailByOrderId(order_id,company);
	}

	/* (non-Javadoc)
	 * @see com.eshop.service.interf.OrderDetailService#save(com.eshop.entrity.OrderDetail, java.lang.String)
	 */
	@Override
	public void save(OrderDetail orderItem, String company) {
		orderDetailDao.save(orderItem,company);
		
	}

	@Override
	public OrderDetail findOrderDetailById(String orderDetail_id, String company) {
		
		return orderDetailDao.findOrderDetailById(orderDetail_id, company);
	}

	@Override
	public void saveComment(OrderDetail orderDetail, String company) {
		orderDetailDao.saveComment(orderDetail,company);
		
	}

/*	@Override
	public void insertPurchaseDetail(PurchaseDetail purchaseDetail) {
		PurchaseDetailDao.insertPurchaseDetail(purchaseDetail);
	}
	*/

 
}
