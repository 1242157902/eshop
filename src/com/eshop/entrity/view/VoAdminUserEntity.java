package com.eshop.entrity.view;

public class VoAdminUserEntity {
	private	Integer	page;
	private	Integer	rows;
	private	String		order;
	private	String		sort;
	//=================good实体类=================
	private String 		id ;						//用户id
	private String 		name;					//用户姓名
	private	String		phone;					//用户手机号
	private	String		 		com_no	;	//所属公司号
	private 	String 			pwd ;					//用户密码
	private 	Integer		status;				//用户状态0：离职；1：代表在职
	
	
	
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	//==========================================
	
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
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public String getCom_no() {
		return com_no;
	}
	public void setCom_no(String com_no) {
		this.com_no = com_no;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
}
