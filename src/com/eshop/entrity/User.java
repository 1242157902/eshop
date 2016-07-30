package com.eshop.entrity;

/**
 * @author wzq
 *
 *version 1.0 2015-10-19 上午10:52:09
 */
public class User {
	
	
	
	private int   user_id;// int(11) DEFAULT NULL,
	private String  telephone; //char(11) DEFAULT NULL,
	private String  password; //varchar(20) DEFAULT NULL,
	private int  sex; //tinyint(1) DEFAULT NULL,
	private String  nickname; //varchar(20) DEFAULT NULL,
	private String  birthday; //char(10) DEFAULT NULL,
	private String  email; //varchar(30) DEFAULT NULL,
	private String  regdate; //char(19) DEFAULT NULL,
	private String  lastdate; //char(19) DEFAULT NULL,
	private int   logintimes;// int(11) DEFAULT NULL
	private String  company;   //所属公司编号
	private String companyName; 
	private String career;//所属公司
	//cjxR
	private Order order;
	
	
	
	
	
	
	
	
	
	
     	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
		/**
	 * @return the company
	 */
	public String getCompany() {
		return company;
	}
	/**
	 * @param company the company to set
	 */
	public void setCompany(String company) {
		this.company = company;
	}
		/**
	 * @return the user_id
	 */
	public int getUser_id() {
		return user_id;
	}
	/**
	 * @param user_id the user_id to set
	 */
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	/**
	 * @return the telephone
	 */
	public String getTelephone() {
		return telephone;
	}
	/**
	 * @param telephone the telephone to set
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the sex
	 */
	public int getSex() {
		return sex;
	}
	/**
	 * @param sex the sex to set
	 */
	public void setSex(int sex) {
		this.sex = sex;
	}
	/**
	 * @return the nickname
	 */
	public String getNickname() {
		return nickname;
	}
	/**
	 * @param nickname the nickname to set
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	/**
	 * @return the birthday
	 */
	public String getBirthday() {
		return birthday;
	}
	/**
	 * @param birthday the birthday to set
	 */
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the regdate
	 */
	public String getRegdate() {
		return regdate;
	}
	/**
	 * @param regdate the regdate to set
	 */
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	/**
	 * @return the lastdate
	 */
	public String getLastdate() {
		return lastdate;
	}
	/**
	 * @param lastdate the lastdate to set
	 */
	public void setLastdate(String lastdate) {
		this.lastdate = lastdate;
	}
	/**
	 * @return the logintimes
	 */
	public int getLogintimes() {
		return logintimes;
	}
	/**
	 * @param logintimes the logintimes to set
	 */
	public void setLogintimes(int logintimes) {
		this.logintimes = logintimes;
	}
	
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public String getCareer() {
		return career;
	}
	public void setCareer(String career) {
		this.career = career;
	}
	
	
			
	
	
	
	
	
	
	
	
	
	
	
	
}
