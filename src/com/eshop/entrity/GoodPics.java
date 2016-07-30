package com.eshop.entrity;


/**
 * 商品图片管理实体类
 * @author xia
 *
 */
public class GoodPics {
	private int pic_id;  //商品图片编号
	private String pic_url;		//商品对应的链接
	private String good_id;		//商品编号；
	private String good_pic;    //商品图片名称
	private int isdel;			//是否删除
	private int order;			//显示顺序；
	
	public int getPic_id() {
		return pic_id;
	}
	public void setPic_id(int pic_id) {
		this.pic_id = pic_id;
	}
	
	public String getPic_url() {
		return pic_url;
	}
	public void setPic_url(String pic_url) {
		this.pic_url = pic_url;
	}
	public String getGood_id() {
		return good_id;
	}
	public void setGood_id(String good_id) {
		this.good_id = good_id;
	}
	public String getGood_pic() {
		return good_pic;
	}
	public void setGood_pic(String good_pic) {
		this.good_pic = good_pic;
	}
	public int getIsdel() {
		return isdel;
	}
	public void setIsdel(int isdel) {
		this.isdel = isdel;
	}
	public int getOrder() {
		return order;
	}
	public void setOrder(int order) {
		this.order = order;
	}
	
}
