package com.eshop.entrity;

import java.util.Date;

/**
 * @author  JYM
 * 2015-12-2 下午4:55:12
 */
public class UserAdress {
	
	private int id;
	 
	private int user_id;
	
	private String name;
	
	private String phone;
	
	private String adress;
	
	private String zipcode;
	
	private int status;
	
	private String createtime;
	
	
	private User user;


	
	public int getId() {
		return id;
	}


	
	public void setId(int id) {
		this.id = id;
	}


	
	public int getUser_id() {
		return user_id;
	}


	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getPhone() {
		return phone;
	}


	
	public void setPhone(String phone) {
		this.phone = phone;
	}


	
	public String getAdress() {
		return adress;
	}


	
	public void setAdress(String adress) {
		this.adress = adress;
	}


	
	public String getZipcode() {
		return zipcode;
	}


	
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}


	
	public int getStatus() {
		return status;
	}


	public void setStatus(int status) {
		this.status = status;
	}


	
	public User getUser() {
		return user;
	}


	
	public void setUser(User user) {
		this.user = user;
	}

	
	

	



	/**
	 * @return the createtime
	 */
	public String getCreatetime() {
		return createtime;
	}



	/**
	 * @param createtime the createtime to set
	 */
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}



	@Override
	public String toString() {
		return "UserAdress [id=" + id + ", user_id=" + user_id + ", name="
				+ name + ", phone=" + phone + ", adress=" + adress
				+ ", zipcode=" + zipcode + ", status=" + status
				+ ", createtime=" + createtime + ", user=" + user + "]";
	}



	
	
	
	
	

}
