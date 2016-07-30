package com.eshop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;


import com.eshop.entrity.Deviceinfo;
import com.eshop.entrity.WXcounts;
import com.eshop.entrity.WxScoreTrade;
import com.eshop.utils.JdbcUtils;
import com.eshop.utils.mydb01jdbcUtils;

/**
 * @author  JYM
 * 2015-11-23 下午8:42:46
 */
public class WXcountsDao {

	/**
	 * @param phone
	 * @return
	 */
	public WXcounts getScoreByPhone(String phone) {
		
		
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		WXcounts wXcounts=new WXcounts();
		
		  try {
				conn=mydb01jdbcUtils.getConnection();
                String  sql="select * from tab_wxcounts where device_number=? ";
				
				statement=conn.prepareStatement(sql);
				statement.setString(1, phone);
				
				rs=statement.executeQuery();
				while (rs.next()) {
				
					
					wXcounts.setId(rs.getInt("id"));
					wXcounts.setDevice_number(rs.getString("device_number"));
					wXcounts.setNumber_count(rs.getInt("number_count"));
					wXcounts.setScore_count(rs.getInt("score_count"));
					wXcounts.setIn_count(rs.getInt("in_count"));
					wXcounts.setOut_count(rs.getInt("out_count"));
					wXcounts.setNums(rs.getInt("nums"));
					
					
				}
					
		  }catch (Exception e) {
			e.printStackTrace();
		}finally{
			mydb01jdbcUtils.release(conn, statement, rs);
		}
		  return wXcounts;
	}

	/**
	 * @param wXcounts
	 */
	public void updateWS(WXcounts wXcounts) {
		
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try{
			conn=mydb01jdbcUtils.getConnection();
			String sql="update tab_wxcounts set score_count=?,out_count=? where  id=? ";
			
			statement=conn.prepareStatement(sql);
			statement.setInt(1,wXcounts.getScore_count());
			statement.setInt(2,wXcounts.getOut_count());
			statement.setInt(3,wXcounts.getId());
			statement.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			mydb01jdbcUtils.release(conn, statement, rs);
		}
	}

	/**
	 * @param wxScoreTrade
	 */
	public void saveTrade(WxScoreTrade wxScoreTrade) {
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		Date dt=new Date();
	     SimpleDateFormat matter1=new SimpleDateFormat("yyyy-MM-dd");
	     String date=matter1.format(dt);
		try {
			conn=mydb01jdbcUtils.getConnection();
			String sql = "insert into tab_wxscoretrade(device_number,score,date,status) values(?,?,?,?)";
			statement = conn.prepareStatement(sql);
			statement.setString(1, wxScoreTrade.getDevice_number());
			statement.setInt(2, wxScoreTrade.getScore());
			statement.setString(3, wxScoreTrade.getDate());
			statement.setInt(4, wxScoreTrade.getStatus());
			
			statement.executeUpdate(); 
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			mydb01jdbcUtils.release(conn, statement, result);
		}
		
	}

	/**
	 * @param telephone
	 * @param ws
	 */
	public void updateInTrade(String telephone, int ws) {
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet result = null;
	    int score_count=0;
		try {
			conn = mydb01jdbcUtils.getConnection();

			String sql = "select * from tab_wxscoreintrade where device_number='"+telephone+"' and status in(0,3) order by start_date asc";
			statement = conn.prepareStatement(sql);  
			result=statement.executeQuery();
			while(result.next()){
				int score=result.getInt("score");
				int id=result.getInt("id");
				score_count+=score;
				if(score_count<=ws){
					//若第一个不满足
					String sql2="update tab_wxscoreintrade set status=1 where id="+id;
					statement = conn.prepareStatement(sql2);
					statement.executeUpdate();
				}else if(score_count>ws){
					String sql3="update tab_wxscoreintrade set status=1 where id="+id;
					statement = conn.prepareStatement(sql3);
					statement.executeUpdate();
					int score_now=score_count-ws;
					String start_date=result.getString("start_date");
					String end_date=result.getString("end_date"); 
					int status=result.getInt("status");
					String sql4="insert into tab_wxscoreintrade(id,device_number,score,start_date,end_date,status) " +
							"values(?,?,?,?,?,?)";
					statement = conn.prepareStatement(sql4);
					statement.setInt(1, id); 
					statement.setString(2, telephone);
					statement.setInt(3, score_now); 
					statement.setString(4, start_date);
					statement.setString(5, end_date);
					statement.setInt(6, status); 
					statement.executeUpdate();
					break;
				}
			}
		} catch (Exception e) {

			e.printStackTrace();
		} finally {

			mydb01jdbcUtils.release(conn, statement, result);

		}
		
	}

	
	
	
	/**
	 * 获取工号
	 * @param telephone
	 * @return
	 */
	public Deviceinfo getNameByPhone(String telephone) {
		
		
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		
		  try {
				conn=mydb01jdbcUtils.getConnection();
                String  sql="select * from tab_deviceinfo where device_number=? ";
				
				statement=conn.prepareStatement(sql);
				statement.setString(1, telephone);
				
				rs=statement.executeQuery();
				while (rs.next()) {
				
					Deviceinfo deviceinfo=new Deviceinfo();
					deviceinfo.setDevice_id(rs.getInt("device_id"));
					deviceinfo.setDevice_number(rs.getString("device_number"));
					deviceinfo.setCompany(rs.getString("user_unit"));
					deviceinfo.setNick_name(rs.getString("nick_name"));
					
					
					return deviceinfo;
				}
					
		  }catch (Exception e) {
			e.printStackTrace();
		}finally{
			mydb01jdbcUtils.release(conn, statement, rs);
		}
		return null;
	}

	/**
	 * @param nickName
	 * @return
	 */
	public Deviceinfo getPhoneByNick(String nickName) {
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		
		  try {
				conn=mydb01jdbcUtils.getConnection();
                String  sql="select * from tab_deviceinfo where nick_name=? ";
				
				statement=conn.prepareStatement(sql);
				statement.setString(1, nickName);
				
				rs=statement.executeQuery();
				while (rs.next()) {
				
					Deviceinfo deviceinfo=new Deviceinfo();
					deviceinfo.setDevice_id(rs.getInt("device_id"));
					deviceinfo.setDevice_number(rs.getString("device_number"));
					deviceinfo.setCompany(rs.getString("user_unit"));
					deviceinfo.setNick_name(rs.getString("nick_name"));
					
					
					return deviceinfo;
				}
					
		  }catch (Exception e) {
			e.printStackTrace();
		}finally{
			mydb01jdbcUtils.release(conn, statement, rs);
		}
		return null;
	}
		
	
	
	
	
	

}
