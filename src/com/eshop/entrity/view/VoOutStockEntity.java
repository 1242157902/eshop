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
public class VoOutStockEntity {

	private	Integer	page;
	private	Integer	rows;
	private	String		order;
	private	String		sort;
	
	
	//=================Outstock实体类=================
	private String 			out_id;					//出库的单号
	private String 			order_id	;			//出库商品的id
	private Integer			quantity;				//出库数量
	private	String			good_id;				//出库商品id
	private	String 			good_name;		//出库商品名称
	private String 			outtime;			//出库时间
	private String 			manager_name;	//操作员
	private String 			endtime;				//结束时间
	private String 			backfill_id;				//回填单号
	//=================Outstock实体类=================
	
	
	public Integer getPage() {
		return page;
	}
 

	public String getEndtime() {
		return endtime;
	}


	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}


	public String getBackfill_id() {
		return backfill_id;
	}


	public void setBackfill_id(String backfill_id) {
		this.backfill_id = backfill_id;
	}




	public String getManager_name() {
		return manager_name;
	}

	public void setManager_name(String manager_name) {
		this.manager_name = manager_name;
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


	public String getOut_id() {
		return out_id;
	}


	public void setOut_id(String out_id) {
		this.out_id = out_id;
	}


	public String getOrder_id() {
		return order_id;
	}


	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}


	public String getOuttime() {
		return outtime;
	}


	public void setOuttime(String outtime) {
		this.outtime = outtime;
	}

	
}
