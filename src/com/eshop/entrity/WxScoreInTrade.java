package com.eshop.entrity;

/**
 * @author  JYM
 * 2015-11-25 下午12:51:09
 */
public class WxScoreInTrade {
	
	private int id;
	
	private String  device_numberString;
	
	private  int score;
	
	private String start_date;
	
	private String end_date;
	
	private int status;

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
	 * @return the device_numberString
	 */
	public String getDevice_numberString() {
		return device_numberString;
	}

	/**
	 * @param device_numberString the device_numberString to set
	 */
	public void setDevice_numberString(String device_numberString) {
		this.device_numberString = device_numberString;
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
	 * @return the start_date
	 */
	public String getStart_date() {
		return start_date;
	}

	/**
	 * @param start_date the start_date to set
	 */
	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}

	/**
	 * @return the end_date
	 */
	public String getEnd_date() {
		return end_date;
	}

	/**
	 * @param end_date the end_date to set
	 */
	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}

	
	public int getStatus() {
		return status;
	}

	
	public void setStatus(int status) {
		this.status = status;
	}

	
	@Override
	public String toString() {
		return "WxScoreInTrade [id=" + id + ", device_numberString="
				+ device_numberString + ", score=" + score + ", start_date="
				+ start_date + ", end_date=" + end_date + ", status=" + status
				+ "]";
	}
	
	
	

	

}
