package com.eshop.entrity;

/**
 * @author  JYM
 * 2015-12-2 下午4:47:49
 */
public class Deviceinfo {
	
	private int device_id;
	
	private String imei;//设备号
	private String device_number;//手机号
	private String company;//用户所属单位
	private String seller;//销售商
	private String gender;//用户性别
	private String birth;//用户年龄
	private String mbos;//设备操作系统号
	private String area;//归属地
	private String provider;//运营商
	private String mbtype;//设备类型
	private String seller_depid;//销售部门号
	private String seller_stuffid;//销售员工号
	private String imsi;//卡号
	private String regtime;//用户注册时间
	private String recque;//当前序列
	private int theme;//当前模式
	private int themestatus;//模式切换状态
	private String entertime;//首次进入系统时间
	
	private String nick_name;

	/**
	 * @return the device_id
	 */
	public int getDevice_id() {
		return device_id;
	}

	/**
	 * @param device_id the device_id to set
	 */
	public void setDevice_id(int device_id) {
		this.device_id = device_id;
	}

	/**
	 * @return the imei
	 */
	public String getImei() {
		return imei;
	}

	/**
	 * @param imei the imei to set
	 */
	public void setImei(String imei) {
		this.imei = imei;
	}

	/**
	 * @return the device_number
	 */
	public String getDevice_number() {
		return device_number;
	}

	/**
	 * @param device_number the device_number to set
	 */
	public void setDevice_number(String device_number) {
		this.device_number = device_number;
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
	 * @return the seller
	 */
	public String getSeller() {
		return seller;
	}

	/**
	 * @param seller the seller to set
	 */
	public void setSeller(String seller) {
		this.seller = seller;
	}

	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * @return the birth
	 */
	public String getBirth() {
		return birth;
	}

	/**
	 * @param birth the birth to set
	 */
	public void setBirth(String birth) {
		this.birth = birth;
	}

	/**
	 * @return the mbos
	 */
	public String getMbos() {
		return mbos;
	}

	/**
	 * @param mbos the mbos to set
	 */
	public void setMbos(String mbos) {
		this.mbos = mbos;
	}

	/**
	 * @return the area
	 */
	public String getArea() {
		return area;
	}

	/**
	 * @param area the area to set
	 */
	public void setArea(String area) {
		this.area = area;
	}

	/**
	 * @return the provider
	 */
	public String getProvider() {
		return provider;
	}

	/**
	 * @param provider the provider to set
	 */
	public void setProvider(String provider) {
		this.provider = provider;
	}

	/**
	 * @return the mbtype
	 */
	public String getMbtype() {
		return mbtype;
	}

	/**
	 * @param mbtype the mbtype to set
	 */
	public void setMbtype(String mbtype) {
		this.mbtype = mbtype;
	}

	/**
	 * @return the seller_depid
	 */
	public String getSeller_depid() {
		return seller_depid;
	}

	/**
	 * @param seller_depid the seller_depid to set
	 */
	public void setSeller_depid(String seller_depid) {
		this.seller_depid = seller_depid;
	}

	/**
	 * @return the seller_stuffid
	 */
	public String getSeller_stuffid() {
		return seller_stuffid;
	}

	/**
	 * @param seller_stuffid the seller_stuffid to set
	 */
	public void setSeller_stuffid(String seller_stuffid) {
		this.seller_stuffid = seller_stuffid;
	}

	/**
	 * @return the imsi
	 */
	public String getImsi() {
		return imsi;
	}

	/**
	 * @param imsi the imsi to set
	 */
	public void setImsi(String imsi) {
		this.imsi = imsi;
	}

	/**
	 * @return the regtime
	 */
	public String getRegtime() {
		return regtime;
	}

	/**
	 * @param regtime the regtime to set
	 */
	public void setRegtime(String regtime) {
		this.regtime = regtime;
	}

	/**
	 * @return the recque
	 */
	public String getRecque() {
		return recque;
	}

	/**
	 * @param recque the recque to set
	 */
	public void setRecque(String recque) {
		this.recque = recque;
	}

	/**
	 * @return the theme
	 */
	public int getTheme() {
		return theme;
	}

	/**
	 * @param theme the theme to set
	 */
	public void setTheme(int theme) {
		this.theme = theme;
	}

	/**
	 * @return the themestatus
	 */
	public int getThemestatus() {
		return themestatus;
	}

	/**
	 * @param themestatus the themestatus to set
	 */
	public void setThemestatus(int themestatus) {
		this.themestatus = themestatus;
	}

	/**
	 * @return the entertime
	 */
	public String getEntertime() {
		return entertime;
	}

	/**
	 * @param entertime the entertime to set
	 */
	public void setEntertime(String entertime) {
		this.entertime = entertime;
	}

	/**
	 * @return the nick_name
	 */
	public String getNick_name() {
		return nick_name;
	}

	/**
	 * @param nick_name the nick_name to set
	 */
	public void setNick_name(String nick_name) {
		this.nick_name = nick_name;
	}

	
	@Override
	public String toString() {
		return "Deviceinfo [device_id=" + device_id + ", imei=" + imei
				+ ", device_number=" + device_number + ", company=" + company
				+ ", seller=" + seller + ", gender=" + gender + ", birth="
				+ birth + ", mbos=" + mbos + ", area=" + area + ", provider="
				+ provider + ", mbtype=" + mbtype + ", seller_depid="
				+ seller_depid + ", seller_stuffid=" + seller_stuffid
				+ ", imsi=" + imsi + ", regtime=" + regtime + ", recque="
				+ recque + ", theme=" + theme + ", themestatus=" + themestatus
				+ ", entertime=" + entertime + ", nick_name=" + nick_name + "]";
	}
	
	
	

}
