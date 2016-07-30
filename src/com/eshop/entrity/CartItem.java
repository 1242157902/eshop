package com.eshop.entrity;

import java.math.BigDecimal;

/**
 * @author  JYM
 * 2015-11-12 上午11:19:54
 */
public class CartItem {
	private String cartitemid;  
	private int good_quantity;
	
	private Goods goods;
	private User user;
	private String create_time;
	
	private String good_id;
	
	
	private String  user_id;
	
	
	
	public String getGood_id() {
		return good_id;
	}

	
	public void setGood_id(String good_id) {
		this.good_id = good_id;
	}

	
	public String getUser_id() {
		return user_id;
	}

	
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getCartitemid() {
		return cartitemid;
	}
	
	public void setCartitemid(String cartitemid) {
		this.cartitemid = cartitemid;
	}
	
	public int getGood_quantity() {
		return good_quantity;
	}
	
	public void setGood_quantity(int good_quantity) {
		this.good_quantity = good_quantity;
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
	
	
	public String getCreate_time() {
		return create_time;
	}
	
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}


	

	// 添加小计方法
		public double getSubtotal() {
			/*
			 * 使用BigDecimal不会有误差
			 * 要求必须使用String类型构造器
			 */
			BigDecimal b1 = new BigDecimal(goods.getGood_nowprice());
			BigDecimal b2 = new BigDecimal(good_quantity);
			BigDecimal b3 = b1.multiply(b2);
			return b3.doubleValue();
		}

		
	
	@Override
	public String toString() {
		return "CartItem [cartitemid=" + cartitemid + ", good_quantity="
				+ good_quantity + ", goods=" + goods + ", user=" + user
				+ ", create_time=" + create_time + ", good_id=" + good_id
				+ ", user_id=" + user_id + "]";
	}

	
	
	
	
	
	

}
