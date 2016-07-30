package com.eshop.entrity.view;

import com.eshop.entrity.Stock;

/**
 * 
 * <p>Title：        VoInStockEntity<p>
 * <p>Description: 入库单界面的传递实体 <p>
 * @date:           2015年11月3日上午10:51:46
 * @author:         ysl
 * @version         1.0
 */
public class VoInStockEntity {

	private	Integer	page;
	private	Integer	rows;
	private	String		order;
	private	String		sort;
	
	
	//=================stock实体类=================
	private	String 		instock_id;					//入库单号
	private	Integer	quantity;							//入库总量
	private	String 			instocktime;				//入库时间
	private	Double 		instock_total;				//入库总价
	private	String 		manager_name;				//入库员
	private 	Integer 	manager_id;					//操作员id
	private	Integer	status;								//状态位  0：代表为处理			1：代表已经处理
	private   String 		endtime;

	//=================stock实体类=================
	

	public Integer getPage() {
		return page;
	}


	public String getEndtime() {
		return endtime;
	}


	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}


	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}


	public String getManager_name() {
		return manager_name;
	}

	public void setManager_name(String manager_name) {
		this.manager_name = manager_name;
	}


	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
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


	public String getInstock_id() {
		return instock_id;
	}


	public void setInstock_id(String instock_id) {
		this.instock_id = instock_id;
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


	public Integer getManager_id() {
		return manager_id;
	}


	public void setManager_id(Integer manager_id) {
		this.manager_id = manager_id;
	}


	public Integer getQuantity() {
		return quantity;
	}

	
}
