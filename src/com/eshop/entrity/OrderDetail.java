package com.eshop.entrity;



/**
 * 
 * <p>Title：        OrderDetail<p>
 * <p>Description:订单明细实体类  <p>
 * @date:           2015年10月29日下午7:46:04
 * @author:         ysl
 * @version         1.0
 */
public class OrderDetail {

		private	String 		orderitemid;							//订单明细号
		private	Integer	  good_quantity;						//订单商品数量
		private	String		good_id;									//订单商品id 		
		private 	String 		good_name;								//订单商品的名称
		private	Double	  currprice;									//一件商品所需积分
		private	String 		order_id;								//该订单明细属于哪个订单
		private	Double	 subtotal;									//小计
		private Integer grade;		                            //评分等级
		private String good_comment; //商品评价
		private String createdTime;
		private String comment_time;
		//==============================================================
		
		public String getOrderitemid() {
			return orderitemid;
		}
		public String getGood_name() {
			return good_name;
		}
		public void setGood_name(String good_name) {
			this.good_name = good_name;
		}
		public void setOrderitemid(String orderitemid) {
			this.orderitemid = orderitemid;
		}
	 
		public Double getCurrprice() {
			return currprice;
		}
		public void setCurrprice(Double currprice) {
			this.currprice = currprice;
		}
	 
		public Integer getGood_quantity() {
			return good_quantity;
		}
		public void setGood_quantity(Integer good_quantity) {
			this.good_quantity = good_quantity;
		}
		public String getGood_id() {
			return good_id;
		}
		public void setGood_id(String good_id) {
			this.good_id = good_id;
		}
		public String getOrder_id() {
			return order_id;
		}
		public void setOrder_id(String order_id) {
			this.order_id = order_id;
		}
		public Double getSubtotal() {
			return subtotal;
		}
		public void setSubtotal(Double subtotal) {
			this.subtotal = subtotal;
		}
		
		//JYM 2015/11/24
		private Goods good;
		private Order order;
		
		public Goods getGood() {
			return good;
		}
		
		public void setGood(Goods good) {
			this.good = good;
		}
		
		public Order getOrder() {
			return order;
		}
		
		public void setOrder(Order order) {
			this.order = order;
		}
	 
		public String getGood_comment() {
			return good_comment;
		}
		public void setGood_comment(String good_comment) {
			this.good_comment = good_comment;
		}
		public Integer getGrade() {
			return grade;
		}
		public void setGrade(Integer grade) {
			this.grade = grade;
		}
		public String getCreatedTime() {
			return createdTime;
		}
		public void setCreatedTime(String createdTime) {
			this.createdTime = createdTime;
		}
		public String getComment_time() {
			return comment_time;
		}
		public void setComment_time(String comment_time) {
			this.comment_time = comment_time;
		}
		@Override
		public String toString() {
			return "OrderDetail [orderitemid=" + orderitemid
					+ ", good_quantity=" + good_quantity + ", good_id="
					+ good_id + ", good_name=" + good_name + ", currprice="
					+ currprice + ", order_id=" + order_id + ", subtotal="
					+ subtotal + ", grade=" + grade + ", good_comment="
					+ good_comment + ", createdTime=" + createdTime
					+ ", comment_time=" + comment_time + ", good=" + good
					+ ", order=" + order + "]";
		}
 
}
