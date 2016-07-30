package com.eshop.entrity;

import java.util.ArrayList;
import java.util.List;

public class GoodFav {

	private String fav_id;
	private Goods goods;
	private User user;
	private String createdTime;
	private int user_id;
	private String good_id;
	private String good_name;
		
	
	private int fav_status;
	
	
	public String getFav_id() {
		return fav_id;
	}
	public void setFav_id(String fav_id) {
		this.fav_id = fav_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getGood_id() {
		return good_id;
	}
	public void setGood_id(String good_id) {
		this.good_id = good_id;
	}
	public int getFav_status() {
		return fav_status;
	}
	public void setFav_status(int fav_status) {
		this.fav_status = fav_status;
	}
	public Goods getGoods() {
		return goods;
	}
	public void setGoods(Goods goods) {
		this.goods = goods;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}
	
	List<Goods> goodFavList=new ArrayList<Goods>();
	
	
	public List<Goods> getGoodFavList() {
		return goodFavList;
	}
	public void setGoodFavList(List<Goods> goodFavList) {
		this.goodFavList = goodFavList;
	}
	public String getGood_name() {
		return good_name;
	}
	public void setGood_name(String good_name) {
		this.good_name = good_name;
	}
	
	
	
	
	
}
