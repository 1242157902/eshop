package com.eshop.entrity;

/**
 * 
 * <p>Title：        Purchase<p>
 * <p>Description: 采购实体类 <p>
 * @date:           2015年10月29日下午7:51:41
 * @author:         ysl
 * @version         1.0
 */
public class InStock {
	private	String 		instock_id;					//入库单号
	private	Integer	quantity;							//入库总量
	private	String 			instocktime;				//入库时间
	private	Double 		instock_total;				//入库总价
	private	String 		manager_name;				//入库员
	private 	Integer 	manager_id;					//操作员id
	private	Integer	status;								//状态位  0：代表为处理			1：代表已经处理
	
	
	//===============================================
	public String getInstock_id() {
		return instock_id;
	}
	public void setInstock_id(String instock_id) {
		this.instock_id = instock_id;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public String getInstocktime() {
		return instocktime;
	}
	public void setInstocktime(String instocktime) {
		this.instocktime = instocktime;
	}
	public Double getInstock_total() {
		return instock_total;
	}
	public void setInstock_total(Double instock_total) {
		this.instock_total = instock_total;
	}
	public String getManager_name() {
		return manager_name;
	}
	public void setManager_name(String manager_name) {
		this.manager_name = manager_name;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getManager_id() {
		return manager_id;
	}
	public void setManager_id(Integer manager_id) {
		this.manager_id = manager_id;
	}
 
 
	 
}
