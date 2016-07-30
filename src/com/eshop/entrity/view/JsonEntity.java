package com.eshop.entrity.view;

/**
 * 
 * <p>Title：        JsonEntity<p>
 * <p>Description:用于传给Easyui界面的实体，可以通过springmvc将其转换为json字符串  <p>
 * @date:           2015年11月1日下午2:52:50
 * @author:         ysl
 * @version         1.0
 */
public class JsonEntity {

	private String		rows;								//具体的内容
	private Integer total;									//总记录数
	
	
	
	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public String getRows() {
		return rows;
	}

	public void setRows(String rows) {
		this.rows = rows;
	}
}
