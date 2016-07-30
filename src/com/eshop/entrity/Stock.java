package com.eshop.entrity;


/**
 * 
 * <p>Title：        Stock<p>
 * <p>Description: 库存实体类 <p>
 * @date:           2015年10月29日下午7:23:15
 * @author:         ysl
 * @version         1.0
 */
public class Stock {
	private String 		good_id;					//商品id	
	private String 		good_name;			//商品名称
	private Integer		quantity;					//商品数量
	private Integer		vquantity;					//商品虚拟库存数量
	private String 		remark;					
	
	
	//=====================================
	 
	public Integer getQuantity() {
		return quantity;
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
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Integer getVquantity() {
		return vquantity;
	}
	public void setVquantity(Integer vquantity) {
		this.vquantity = vquantity;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Override
	public String toString() {
		return "Stock [good_id=" + good_id + ", good_name=" + good_name
				+ ", quantity=" + quantity + ", vquantity=" + vquantity
				+ ", remark=" + remark + "]";
	}
	
	
 
	
	

	
}
