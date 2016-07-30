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
public class VoStockEntity {

	private	Integer	page;
	private	Integer	rows;
	private	String		order;
	private	String		sort;
	
	
	//=================stock实体类=================
	private String 		good_id;					//商品id	
	private String 		good_name;			//商品名称
	private Integer		quantity;					//商品数量
	private Integer		vquantity;					//商品虚拟库存数量
	private String 		remark;		

	
	
	
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

 
	
	
}
