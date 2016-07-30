package com.eshop.service.interf;


import java.util.List;





import com.eshop.entrity.Order;
import com.eshop.entrity.OrderDetail;

public interface OrderDetailService {
	
	
 
	public List<OrderDetail> findOrderDetailByOrderId(String order_id,String company) throws Exception ;

	/**
	 * @param orderItem
	 * @param company
	 */
	public void save(OrderDetail orderItem, String company);

	public OrderDetail findOrderDetailById(String orderDetail_id, String company);

	public void saveComment(OrderDetail orderDetail, String company);
	

/*	public void insertPurchaseDetail(PurchaseDetail purchaseDetail);*/
 
		
}
