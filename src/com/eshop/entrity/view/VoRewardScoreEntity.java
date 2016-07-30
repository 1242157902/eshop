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
public class VoRewardScoreEntity {

	private	Integer	page;
	private	Integer	rows;
	private	String		order;
	private	String		sort;
	
	
	
	private int id;
	
	private String nick_name;
	
	private String device_number;
	
	private int reward_score;
	
	private int status;
	
	private String name;
	
	private String department;
	
	
	
	
	
	
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNick_name() {
		return nick_name;
	}
	public void setNick_name(String nick_name) {
		this.nick_name = nick_name;
	}
	public String getDevice_number() {
		return device_number;
	}
	public void setDevice_number(String device_number) {
		this.device_number = device_number;
	}
	public int getReward_score() {
		return reward_score;
	}
	public void setReward_score(int reward_score) {
		this.reward_score = reward_score;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public Integer getPage() {
		return page;
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
