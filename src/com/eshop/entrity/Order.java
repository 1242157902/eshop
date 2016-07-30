package com.eshop.entrity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;



/**
 * 
 * <p>Title：        Order<p>
 * <p>Description:订单实体类  <p>
 * @date:           2015年10月29日下午7:37:46
 * @author:         ysl
 * @version         1.0
 */
public class Order {
	private 	String				order_id;									//订单id
	private 	String				create_time;								//生成订单时间
	private	Double			total;											//该订单所需总积分
	private	Integer			status;											//订单状态，有五种	分别为12345
	private 	String				user_id;										//订单用户，谁下的订单
	private 	String				consignee_name;						//收货人姓名
	private 	String				consignee_phone;					//收货人手机号
	private 	String				consignee_address;					//收货人地址
	private 	String				consignee_zipcode;					//收货人邮编
	private   Integer       		 delivery;                 //送货方式
	
	private String 				nick_name;					//工号
	//==============================================================
	
	public String getOrder_id() {
		return order_id;
	}
	public String getNick_name() {
		return nick_name;
	}
	public void setNick_name(String nick_name) {
		this.nick_name = nick_name;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getConsignee_name() {
		return consignee_name;
	}
	public void setConsignee_name(String consignee_name) {
		this.consignee_name = consignee_name;
	}
	public String getConsignee_phone() {
		return consignee_phone;
	}
	public void setConsignee_phone(String consignee_phone) {
		this.consignee_phone = consignee_phone;
	}
	public String getConsignee_address() {
		return consignee_address;
	}
	public void setConsignee_address(String consignee_address) {
		this.consignee_address = consignee_address;
	}
	public String getConsignee_zipcode() {
		return consignee_zipcode;
	}
	public void setConsignee_zipcode(String consignee_zipcode) {
		this.consignee_zipcode = consignee_zipcode;
	}
	
	

	public Integer getDelivery() {
		return delivery;
	}
	
	public void setDelivery(Integer delivery) {
		this.delivery = delivery;
	}
	
	
	
	//JYM2015//11/24
	List<OrderDetail> orderDetails=new ArrayList<OrderDetail>();
	
	
	public List<OrderDetail> getOrderDetails() {
		return orderDetails;
	}
	
	public void setOrderDetails(List<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}
	@Override
	public String toString() {
		return "Order [order_id=" + order_id + ", create_time=" + create_time
				+ ", total=" + total + ", status=" + status + ", user_id="
				+ user_id + ", consignee_name=" + consignee_name
				+ ", consignee_phone=" + consignee_phone
				+ ", consignee_address=" + consignee_address
				+ ", consignee_zipcode=" + consignee_zipcode + ", delivery="
				+ delivery + "]";
	}
	
	
	
}
