package com.eshop.entrity.view;

public class VGoodEntity {
	private	Integer	page;
	private	Integer	rows;
	private	String		order;
	private	String		sort;
	//=================good实体类=================
	private String		good_id;						//	主键 uuid
	private String		good_name;				//商品名称
	private String		brand_name;				//商品品牌
	private String		good_guige;				//商品规格
	private Boolean isvisual;						//是否为虚拟商品0不是1是
	private String		c_id;							//商品分类id
	private Double	good_price;				//商品原价
	private Double	good_nowprice;			//积分
	private String 	good_img;					//商品首页显示图片
	private String 	good_desc;				//商品描述
	private Boolean is_sell;						//是否上架
	private Integer 	purchase_num;			//采购数量
	//==========================================
	
	public Integer getPage() {
		return page;
	}
	public Integer getPurchase_num() {
		return purchase_num;
	}
	public void setPurchase_num(Integer purchase_num) {
		this.purchase_num = purchase_num;
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
	public String getBrand_name() {
		return brand_name;
	}
	public void setBrand_name(String brand_name) {
		this.brand_name = brand_name;
	}
	public String getGood_guige() {
		return good_guige;
	}
	public void setGood_guige(String good_guige) {
		this.good_guige = good_guige;
	}
	public Boolean getIsvisual() {
		return isvisual;
	}
	public void setIsvisual(Boolean isvisual) {
		this.isvisual = isvisual;
	}
	public String getC_id() {
		return c_id;
	}
	public void setC_id(String c_id) {
		this.c_id = c_id;
	}
	public Double getGood_price() {
		return good_price;
	}
	public void setGood_price(Double good_price) {
		this.good_price = good_price;
	}
	public Double getGood_nowprice() {
		return good_nowprice;
	}
	public void setGood_nowprice(Double good_nowprice) {
		this.good_nowprice = good_nowprice;
	}
	public String getGood_img() {
		return good_img;
	}
	public void setGood_img(String good_img) {
		this.good_img = good_img;
	}
	public String getGood_desc() {
		return good_desc;
	}
	public void setGood_desc(String good_desc) {
		this.good_desc = good_desc;
	}
	public Boolean getIs_sell() {
		return is_sell;
	}
	public void setIs_sell(Boolean is_sell) {
		this.is_sell = is_sell;
	}

	
}
