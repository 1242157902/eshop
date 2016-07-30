package com.eshop.entrity;


/**
 * 
 * <p>Title：        OutStock<p>
 * <p>Description:  出库实体类<p>
 * @date:           2015年10月29日下午7:34:56
 * @author:         ysl
 * @version         1.0
 */
public class OutStock {
	private String 			out_id;					//出库的单号
	private String 			order_id	;			//出库商品的id
	private Integer			quantity;				//出库数量
	private	String			good_id;				//出库商品id
	private	String 			good_name;		//出库商品名称
	private	Integer		status;					//出库状态
	private String 			outtime;			//出库时间
	private String 			manager_name;	//操作员
	private String 			backfill_id;				//回填单号
	
	//============================================
	
	
	 
	public String getGood_id() {
		return good_id;
	}
	public String getBackfill_id() {
		return backfill_id;
	}
	public void setBackfill_id(String backfill_id) {
		this.backfill_id = backfill_id;
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
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
 
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public String getOuttime() {
		return outtime;
	}
	public void setOuttime(String outtime) {
		this.outtime = outtime;
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
	public String getManager_name() {
		return manager_name;
	}
	@Override
	public String toString() {
		return "OutStock [out_id=" + out_id + ", order_id=" + order_id
				+ ", quantity=" + quantity + ", good_id=" + good_id
				+ ", good_name=" + good_name + ", status=" + status
				+ ", outtime=" + outtime + ", manager_name=" + manager_name
				+ "]";
	}
	public void setManager_name(String manager_name) {
		this.manager_name = manager_name;
	}
 
 
	
	
	
}
