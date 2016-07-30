package com.eshop.entrity;

/**
 * 
 * Description:商品分类实体类
 *
 */

public class Category {

	private String c_id;  //分类编号；
	private String c_name;   //分类名称；
	private String c_parent; //父类编号；
	private String c_desc;	//分类描述；
	public String getC_id() {
		return c_id;
	}
	public void setC_id(String c_id) {
		this.c_id = c_id;
	}
	public String getC_name() {
		return c_name;
	}
	public void setC_name(String c_name) {
		this.c_name = c_name;
	}
	public String getC_parent() {
		return c_parent;
	}
	public void setC_parent(String c_parent) {
		this.c_parent = c_parent;
	}
	public String getC_desc() {
		return c_desc;
	}
	public void setC_desc(String c_desc) {
		this.c_desc = c_desc;
	}
	
	@Override
	public String toString() {
		return "Category [c_id=" + c_id + ", c_name=" + c_name + ", c_parent="
				+ c_parent + ", c_desc=" + c_desc + "]";
	}
	
	
}
