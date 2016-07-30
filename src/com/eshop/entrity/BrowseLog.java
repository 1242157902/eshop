package com.eshop.entrity;

public class BrowseLog {
	private int id;
	private String good_id;
	private String good_name;
	private String browseTime;
	private User user;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getBrowseTime() {
		return browseTime;
	}
	public void setBrowseTime(String browseTime) {
		this.browseTime = browseTime;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

}
