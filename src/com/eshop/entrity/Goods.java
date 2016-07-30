package com.eshop.entrity;

/**
 * 
 * <p>Title：        Goods<p>
 * <p>Description:商品实体类  <p>
 * @date:           2015年11月2日下午5:09:23
 * @author:         ysl
 * @version         1.0
 */
public class Goods {
	
	private String		good_id;						//	主键 uuid
	private String		good_name;				//商品名称
	private String		brand_name;				//商品品牌
	private String		good_guige;				//商品规格
	private Boolean isvisual;						//是否为虚拟商品0不是1是
	private String		c_id;							//商品分类id
	private Double	good_price;				//商品原价
	private Double	good_nowprice;			//积分
	private String 	good_img;	             //商品首页显示图片
	private String  good_category_img;      //商品类别图片
	private String  good_detail_img;		//商品详细页大图片
	private String  good_small_img;			//商品首页显示小图片；
	private String 	good_desc;				//商品描述
	private Boolean is_sell;						//是否上架
	private Integer stockQuantity;				//库存数量
	
	private int sale_num;
	private  int create_time;
	//============================================
	
	
	
	public String getGood_id() {
		return good_id;
	}
	public Integer getStockQuantity() {
		return stockQuantity;
	}
	public void setStockQuantity(Integer stockQuantity) {
		this.stockQuantity = stockQuantity;
	}
	public String getGood_category_img() {
		return good_category_img;
	}
	public void setGood_category_img(String good_category_img) {
		this.good_category_img = good_category_img;
	}
	public String getGood_detail_img() {
		return good_detail_img;
	}
	public void setGood_detail_img(String good_detail_img) {
		this.good_detail_img = good_detail_img;
	}
	public String getGood_small_img() {
		return good_small_img;
	}
	public void setGood_small_img(String good_small_img) {
		this.good_small_img = good_small_img;
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
	public int getSale_num() {
		return sale_num;
	}
	public void setSale_num(int sale_num) {
		this.sale_num = sale_num;
	}
	@Override
	public String toString() {
		return "Goods [good_id=" + good_id + ", good_name=" + good_name
				+ ", brand_name=" + brand_name + ", good_guige=" + good_guige
				+ ", isvisual=" + isvisual + ", c_id=" + c_id + ", good_price="
				+ good_price + ", good_nowprice=" + good_nowprice
				+ ", good_img=" + good_img + ", good_desc=" + good_desc
				+ ", is_sell=" + is_sell + "]";
	}
	public int getCreate_time() {
		return create_time;
	}
	public void setCreate_time(int creat_time) {
		this.create_time = creat_time;
	}
	
}
