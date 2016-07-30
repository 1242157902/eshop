
package com.eshop.entrity;

/**
 * 
 * <p>Title：        InStockDetail<p>
 * <p>Description:  <p>
 * @date:           2015年11月17日下午12:24:59
 * @author:         ysl
 * @version         1.0
 */
public class InStockDetail {
	
	private	String 				instockitemid;				//入库明细号
	private	String				good_id;							//入库商品id
	private	String 				good_name;					//商品名称
	private	Integer			good_quantity;				//入库商品数量
	private	Double			good_price;					//商品单价
	private	String				instock_id;					//该订单明细所属当单号
	private	Double			subtotal;							//小计
	
	
	
	public String getInstockitemid() {
		return instockitemid;
	}
	public void setInstockitemid(String instockitemid) {
		this.instockitemid = instockitemid;
	}
	public String getInstock_id() {
		return instock_id;
	}
	public void setInstock_id(String instock_id) {
		this.instock_id = instock_id;
	}
	public Double getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(Double subtotal) {
		this.subtotal = subtotal;
	}
	public String getGood_id() {
		return good_id;
	}
	public void setGood_id(String good_id) {
		this.good_id = good_id;
	}
	public String getGood_name() {
		return good_name;
	}
	public void setGood_name(String good_name) {
		this.good_name = good_name;
	}
	public Integer getGood_quantity() {
		return good_quantity;
	}
	public void setGood_quantity(Integer good_quantity) {
		this.good_quantity = good_quantity;
	}
	public Double getGood_price() {
		return good_price;
	}
	public void setGood_price(Double good_price) {
		this.good_price = good_price;
	}
	
	 
	
}
