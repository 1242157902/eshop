package com.eshop.entrity;

/**
 * @author  JYM
 * 2015-11-23 下午8:20:38
 */
public class WXcounts {
	
	private  Integer id;
	
	private String device_number;
	
	private int number_count;
	
	private  int score_count;
	
	private int in_count;
	
	private int out_count;
	
	private int nums;

	
	public Integer getId() {
		return id;
	}

	
	public void setId(Integer id) {
		this.id = id;
	}

	
	public String getDevice_number() {
		return device_number;
	}

	public void setDevice_number(String device_number) {
		this.device_number = device_number;
	}

	
	public int getNumber_count() {
		return number_count;
	}

	
	public void setNumber_count(int number_count) {
		this.number_count = number_count;
	}

	
	public int getScore_count() {
		return score_count;
	}

	
	public void setScore_count(int score_count) {
		this.score_count = score_count;
	}

	
	public int getIn_count() {
		return in_count;
	}

	
	public void setIn_count(int in_count) {
		this.in_count = in_count;
	}

	
	public int getOut_count() {
		return out_count;
	}

	public void setOut_count(int out_count) {
		this.out_count = out_count;
	}




	
	
	public int getNums() {
		return nums;
	}


	public void setNums(int nums) {
		this.nums = nums;
	}


	
	@Override
	public String toString() {
		return "WXcounts [id=" + id + ", device_number=" + device_number
				+ ", number_count=" + number_count + ", score_count="
				+ score_count + ", in_count=" + in_count + ", out_count="
				+ out_count + ", nums=" + nums + "]";
	}


	
	
	

}
