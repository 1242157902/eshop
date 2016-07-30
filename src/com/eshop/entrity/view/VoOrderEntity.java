package com.eshop.entrity.view;

import com.eshop.entrity.Stock;

/**
 * 
 * <p>Title：        VoEntity<p>
 * <p>Description:用于传界面上的参数的实体  <p>
 * @date:           2015年11月1日下午12:16:08
 * @author:         ysl
 * @version         1.0
 */
public class VoOrderEntity {

	private	Integer	page;
	private	Integer	rows;
	private	String		order;
	private	String		sort;
	
	
	//=================Order实体类=================
	private 	String				order_id;									//订单id
	private 	String				create_time;								//生成订单时间
	private	Double			total;											//该订单所需总积分
	private	Integer			status;											//订单状态，有五种	分别为12345
	private 	String				user_id;										//订单用户，谁下的订单
	private 	String				consignee_name;						//收货人姓名
	private 	String				consignee_phone;					//收货人手机号
	private 	String				consignee_address;					//收货人地址
	private 	String				consignee_zipcode;					//收货人邮编
	private 	String					endtime;									//结束时间
	private String 				nick_name;					//工号
	private 	String 				good_name;										//商品名称
	private	Integer	  good_quantity;						//订单商品数量

	//=================Order实体类=================
	

	public Integer getPage() {
		return page;
	}

	 
	public Integer getGood_quantity() {
		return good_quantity;
	}


	public void setGood_quantity(Integer good_quantity) {
		this.good_quantity = good_quantity;
	}


	public String getNick_name() {
		return nick_name;
	}


	public void setNick_name(String nick_name) {
		this.nick_name = nick_name;
	}


	public String getGood_name() {
		return good_name;
	}


	public void setGood_name(String good_name) {
		this.good_name = good_name;
	}


	public String getEndtime() {
		return endtime;
	}


	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}


	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}


	public String getOrder_id() {
		return order_id;
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


 
	
	
}
