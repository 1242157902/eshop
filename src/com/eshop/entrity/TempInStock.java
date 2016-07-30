
package com.eshop.entrity;

 
/**
 * 
 * <p>Title：        TempInStock<p>
 * <p>Description:  <p>
 * @date:           2015年11月10日下午2:55:45
 * @author:         ysl
 * @version         1.0
 */
public class TempInStock {
	
	private	String 				tmp_in_id;						//入库明细号
	private	String				good_id;							//入库商品id
	private	String 				good_name;					//商品名称
	private	Integer			good_quantity;				//入库商品数量
	private	Double			good_price;					//商品单价
	private	Double			subtotal;							//小计				//小计
	private String 				manager_name;				//操作员名称
	private	Integer			user_id;							//操作员id
	
	
	
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public String getManager_name() {
		return manager_name;
	}
	public void setManager_name(String manager_name) {
		this.manager_name = manager_name;
	}
	public String getTmp_in_id() {
		return tmp_in_id;
	}
	public void setTmp_in_id(String tmp_in_id) {
		this.tmp_in_id = tmp_in_id;
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
