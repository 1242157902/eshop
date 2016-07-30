package com.eshop.entrity;

import com.sun.xml.internal.bind.v2.runtime.Name;

/**
 * @author  JYM
 * 2015-12-2 下午5:02:45
 */
public class RewardScore {
	
	private int id;
	
	private String nick_name;
	
	private String device_number;
	
	private int reward_score;
	
	private int status;
	
	private String name;
	
	private String department;

	private Integer  before_rscore;
	private Integer score ;
	private String import_time;
	private String last_time	;
	
	
	
	public String getLast_time() {
		return last_time;
	}


	public void setLast_time(String last_time) {
		this.last_time = last_time;
	}


	public Integer getBefore_rscore() {
		return before_rscore;
	}


	public void setBefore_rscore(Integer before_rscore) {
		this.before_rscore = before_rscore;
	}


	public Integer getScore() {
		return score;
	}


	public void setScore(Integer score) {
		this.score = score;
	}


	public String getImport_time() {
		return import_time;
	}


	public void setImport_time(String import_time) {
		this.import_time = import_time;
	}


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


	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}


	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}


	public String getDepartment() {
		return department;
	}


	public void setDepartment(String department) {
		this.department = department;
	}



	@Override
	public String toString() {
		return "RewardScore [id=" + id + ", nick_name=" + nick_name
				+ ", device_number=" + device_number + ", reward_score="
				+ reward_score + ", status=" + status + ", name=" + name
				+ ", department=" + department + "]";
	}

	
	
	

}
