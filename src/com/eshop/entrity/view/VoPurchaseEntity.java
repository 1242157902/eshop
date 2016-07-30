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
public class VoPurchaseEntity {

	private	Integer	page;
	private	Integer	rows;
	private	String		order;
	private	String		sort;
	
	
	//=================purchase实体类=================
	private	String 		purchase_id;					//采购单号
	private	Integer	quantity;							//采购总量
	private	String 			purchasetime;				//采购时间
	private	Double 		purchase_total;				//采购总价
	private	String 		manager_name;				//采购员
	private	Integer	status;								//状态位  0：代表为处理			1：代表已经处理
	
	

	//=================stock实体类=================
	

	public Integer getPage() {
		return page;
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


	public String getPurchase_id() {
		return purchase_id;
	}


	public void setPurchase_id(String purchase_id) {
		this.purchase_id = purchase_id;
	}


	public Integer getQuantity() {
		return quantity;
	}


	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}


	public String getPurchasetime() {
		return purchasetime;
	}


	public void setPurchasetime(String purchasetime) {
		this.purchasetime = purchasetime;
	}


	public Double getPurchase_total() {
		return purchase_total;
	}


	public void setPurchase_total(Double purchase_total) {
		this.purchase_total = purchase_total;
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

 
	
	
}
