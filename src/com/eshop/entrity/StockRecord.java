package com.eshop.entrity;


 /**
  * 
  * <p>Title：        StockRecord<p>
  * <p>Description:  <p>
  * @date:           2015年11月17日下午9:34:09
  * @author:         ysl
  * @version         1.0
  */
public class StockRecord {
	private String 		stock_record_id;					//id	
	private String 		good_id;						//商品id
	private String 		good_name;			//商品名称
	private Integer		quantity;					//商品数量
	private Integer		update_quantity;					//商品虚拟库存数量
	private String 		createtime;							//操作时间
	
	private	String 		manager_name;				//操作员名称
	private 	Integer 	manager_id;					//操作员id
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
	public String getStock_record_id() {
		return stock_record_id;
	}
	public void setStock_record_id(String stock_record_id) {
		this.stock_record_id = stock_record_id;
	}
	public Integer getUpdate_quantity() {
		return update_quantity;
	}
	public void setUpdate_quantity(Integer update_quantity) {
		this.update_quantity = update_quantity;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public String getManager_name() {
		return manager_name;
	}
	public void setManager_name(String manager_name) {
		this.manager_name = manager_name;
	}
	public Integer getManager_id() {
		return manager_id;
	}
	public void setManager_id(Integer manager_id) {
		this.manager_id = manager_id;
	}
}
