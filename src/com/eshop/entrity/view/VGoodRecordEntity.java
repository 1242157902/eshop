package com.eshop.entrity.view;

import java.util.Date;

/**
 * 
 * <p>Title：        VoEntity<p>
 * <p>Description:用于传界面上的参数的实体  <p>
 * @date:           2015年11月1日下午12:16:08
 * @author:         ysl
 * @version         1.0
 */
public class VGoodRecordEntity {
	private	Integer	page;
	private	Integer	rows;
	private	String		order;
	private	String		sort;
	
	//=================goodRecord实体类=================
	private String record_id;  //记录编号
	private String good_id;    //商品编号；
	private String goodname;		//商品名称；
	private double good_price;  //商品价格；
	private double good_integration;  //商品积分；
	private String creatime;   //创建时间
	//================================================
	
	
	public Integer getPage() {
		return page;
	}
	public String getGood_id() {
		return good_id;
	}
	public void setGood_id(String good_id) {
		this.good_id = good_id;
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
	public String getRecord_id() {
		return record_id;
	}
	public void setRecord_id(String record_id) {
		this.record_id = record_id;
	}
	
	public String getGoodname() {
		return goodname;
	}
	public void setGoodname(String goodname) {
		this.goodname = goodname;
	}
	public double getGood_price() {
		return good_price;
	}
	public void setGood_price(double good_price) {
		this.good_price = good_price;
	}
	public double getGood_integration() {
		return good_integration;
	}
	public void setGood_integration(double good_integration) {
		this.good_integration = good_integration;
	}
	public String getCreatime() {
		return creatime;
	}
	public void setCreatime(String  creatime) {
		this.creatime = creatime;
	}
	
	
}
