package com.eshop.entrity;

import java.util.Date;

/**
 * @author  JYM
 * 2015-12-3 下午9:02:14
 */
public class PerScore {
	
	private int id ;
	
	private  float percentage;
	
	private Date createtime;
	
	private int admin_id;
	
	private String adminname;
	
	private int status;
	
	private AdminUser adminUser;

	
	public String getAdminname() {
		return adminname;
	}

	public void setAdminname(String adminname) {
		this.adminname = adminname;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the percentage
	 */
	public float getPercentage() {
		return percentage;
	}

	/**
	 * @param percentage the percentage to set
	 */
	public void setPercentage(float percentage) {
		this.percentage = percentage;
	}

	/**
	 * @return the createtime
	 */
	public Date getCreatetime() {
		return createtime;
	}

	/**
	 * @param createtime the createtime to set
	 */
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	/**
	 * @return the admin_id
	 */
	public int getAdmin_id() {
		return admin_id;
	}

	/**
	 * @param admin_id the admin_id to set
	 */
	public void setAdmin_id(int admin_id) {
		this.admin_id = admin_id;
	}

	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}

	/**
	 * @return the adminUser
	 */
	public AdminUser getAdminUser() {
		return adminUser;
	}

	/**
	 * @param adminUser the adminUser to set
	 */
	public void setAdminUser(AdminUser adminUser) {
		this.adminUser = adminUser;
	}

	
	@Override
	public String toString() {
		return "PerScore [id=" + id + ", percentage=" + percentage
				+ ", createtime=" + createtime + ", admin_id=" + admin_id
				+ ", status=" + status + ", adminUser=" + adminUser + "]";
	}
	
	

}
