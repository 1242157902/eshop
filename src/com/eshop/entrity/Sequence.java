package com.eshop.entrity;

import java.sql.Date;




/**
 * 
 * <p>Title：        Sequence<p>
 * <p>Description:  产生下一个单据id<p>
 * @date:           2015年12月1日下午3:35:23
 * @author:         ysl
 * @version         1.0
 */
public class Sequence {
	
	private Integer sequenceId;			//序列id
	private Integer 	nextId;						//最新的id
	private Date		date;						//日期
	private String  	type;						//单据类型		O:订单：ES：入库单；T：临时单；R：记录单；OS:出库单；
	public Integer getSequenceId() {
		return sequenceId;
	}
	public void setSequenceId(Integer sequenceId) {
		this.sequenceId = sequenceId;
	}
 
	public Integer getNextId() {
		return nextId;
	}
	public void setNextId(Integer nextId) {
		this.nextId = nextId;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
	
}
