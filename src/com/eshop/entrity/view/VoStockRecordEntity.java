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
public class VoStockRecordEntity {

	private	Integer	page;
	private	Integer	rows;
	private	String		order;
	private	String		sort;
	
	
	//=================stock实体类=================
	private String 		stock_record_id;					//id	
	private String 		good_id;						//商品id
	private String 		good_name;			//商品名称
	private Integer		quantity;					//商品数量
	private Integer		update_quantity;					//商品虚拟库存数量
	private String 		createtime;							//操作时间
	
	private	String 		manager_name;				//操作员名称
	private 	Integer 	manager_id;					//操作员id

	private 	String 		endtime;						//结束时间
	
	
	
	
	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public Integer getPage() {
		return page;
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

	public Integer getQuantity() {
		return quantity;
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
