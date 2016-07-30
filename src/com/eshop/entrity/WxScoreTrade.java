package com.eshop.entrity;

/**
 * @author  JYM
 * 2015-11-25 下午12:55:43
 */
public class WxScoreTrade {
	
	private  int  trade_id;
	
	private  String device_number;
	
	private int score;
	
	private String date;
	
	private int  status;

	/**
	 * @return the trade_id
	 */
	public int getTrade_id() {
		return trade_id;
	}

	/**
	 * @param trade_id the trade_id to set
	 */
	public void setTrade_id(int trade_id) {
		this.trade_id = trade_id;
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
	 * @return the score
	 */
	public int getScore() {
		return score;
	}

	/**
	 * @param score the score to set
	 */
	public void setScore(int score) {
		this.score = score;
	}

	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
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

	
	@Override
	public String toString() {
		return "WxScoreTrade [trade_id=" + trade_id + ", device_number="
				+ device_number + ", score=" + score + ", date=" + date
				+ ", status=" + status + "]";
	}
	
	
	
	
	

}
