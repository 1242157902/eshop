package com.eshop.entrity.view;

import java.util.Date;

import com.eshop.entrity.AdminUser;

/**
 * 用于接收页面的参数
 * @author Administrator
 *
 */
public class VPerScoreEntity {
	private	Integer	page;
	private	Integer	rows;
	private	String		order;
	private	String		sort;
	
	//=================PerScore实体类=================
	private int id ;
	private  float percentage;
	private Date createtime;
	private int admin_id;
	private int status;
	private AdminUser adminUser;
	//==============================================

	
	public Integer getPage() {
		return page;
	}

	public AdminUser getAdminUser() {
		return adminUser;
	}

	public void setAdminUser(AdminUser adminUser) {
		this.adminUser = adminUser;
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getPercentage() {
		return percentage;
	}

	public void setPercentage(float percentage) {
		this.percentage = percentage;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public int getAdmin_id() {
		return admin_id;
	}

	public void setAdmin_id(int admin_id) {
		this.admin_id = admin_id;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	
}
