package com.eshop.entrity;

/**
 * 
 * <p>Title：        AdminUser<p>
 * <p>Description: 后台用户 <p>
 * @date:           2015年11月23日下午8:22:59
 * @author:         ysl
 * @version         1.0
 */
public class AdminUser {
	
	private Integer 		id ;						//用户id
	private String 		name;					//用户姓名
	private	String		phone;					//用户手机号
	private	String		 		com_no	;	//所属公司号
	private 	String 			pwd ;					//用户密码
	private 	Integer		status;				//用户状态0：离职；1：代表在职
	private String  company;          //所属公司编号
	private String companyName;       //所属公司
	
	
	
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
 
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
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
