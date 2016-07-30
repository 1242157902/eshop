package com.eshop.entrity;

import java.util.Date;

/**
 * 商品价格和积分变动记录实体类
 * @author Jane
 *
 */
public class GoodsRecord {
	private String record_id;  //记录编号
	private String good_id;    //商品编号；
	private String goodname;		//商品名称；
	private double good_price;  //商品价格；
	private double good_integration;  //商品积分；
	private String username;      //操作员姓名
	private String creatime;   //创建时间
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getGood_id() {
		return good_id;
	}
	public void setGood_id(String good_id) {
		this.good_id = good_id;
	}
	public String getGoodname() {
		return goodname;
	}
	public void setGoodname(String goodname) {
		this.goodname = goodname;
	}
	public String getRecord_id() {
		return record_id;
	}
	public void setRecord_id(String record_id) {
		this.record_id = record_id;
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
	public String  getCreatime() {
		return creatime;
	}
	public void setCreatime(String  creatime) {
		this.creatime = creatime;
	}
	
	
}
