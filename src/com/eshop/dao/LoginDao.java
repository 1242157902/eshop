package com.eshop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

import com.eshop.entrity.MobileModel;
import com.eshop.entrity.User;
import com.eshop.utils.DefaultjdbcUtils;
import com.eshop.utils.DxtJdbcUtils;
import com.eshop.utils.JdbcUtils;
import com.eshop.utils.NcutJdbcUtils;

/**
 * @author wzq
 *
 *version 1.0 2015-10-19 上午10:00:01
 */
public class LoginDao {

	/**
	 * @param phonenum
	 * @return
	 */
	public MobileModel getmbbynum(String phonenum) {
		
		
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		
		try {
			
			conn=JdbcUtils.getConnection();
			String sql="select * from tab_deviceinfo where device_number=?";
			
			statement=conn.prepareStatement(sql);
			statement.setString(1, phonenum);
			
			
			rs=statement.executeQuery();
			while (rs.next()) {
				
				MobileModel mb=new MobileModel();
				
			   mb.setCompany(rs.getString("user_unit"));
				
				return mb;
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally
		{
			JdbcUtils.release(conn, statement, rs);
		}
		
		
		return null;
	}

	
	public User getuser(String phonenum, String uninno) {
		
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		
		try {
			
		if ("100001".equals(uninno)) {
			conn=NcutJdbcUtils.getConnection();
		}else if ("100002".equals(uninno)) {
			
			conn=DxtJdbcUtils.getConnection();
		}else {
			conn=DefaultjdbcUtils.getConnection();
		}
			
			
			
			String sql="select * from tab_user where telephone=?";
			
			statement=conn.prepareStatement(sql);
			statement.setString(1, phonenum);
			
			
			rs=statement.executeQuery();
			while (rs.next()) {
				
				User user=new User();
				
			     user.setUser_id(rs.getInt("user_id"));
				
			     
			     return user;
				
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally
		{
			JdbcUtils.release(conn, statement, rs);
		}
		
		
		
		
		return null;
	}


	/**
	 * @param telephone
	 * @param password
	 * @return
	 */
	public User login(String telephone, String password) {
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		
		MobileModel mb=getmbbynum(telephone);
		
		String uninno=mb.getCompany();
		
		try {
			
		if ("100001".equals(uninno)) {
			conn=NcutJdbcUtils.getConnection();
		}else if ("100002".equals(uninno)) {
			
			conn=DxtJdbcUtils.getConnection();
		}else {
			conn=DefaultjdbcUtils.getConnection();
		}
			
			
			
			String sql="select * from tab_user where telephone=? and password=?";
			
			statement=conn.prepareStatement(sql);
			statement.setString(1, telephone);
			statement.setString(2, password);
			
			
			rs=statement.executeQuery();
			while (rs.next()) {
				
				User user=new User();
				
			     user.setUser_id(rs.getInt("user_id"));
			     user.setNickname(rs.getString("nickname"));
			     user.setBirthday(rs.getString("birthday"));
			     user.setEmail(rs.getString("email"));
			     user.setTelephone(rs.getString("telephone"));
			     user.setLogintimes(rs.getInt("logintimes"));
			  
			     return user;
				
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally
		{
			JdbcUtils.release(conn, statement, rs);
		}
		
		
		
		
		return null;
	}


	/**
	 * @param user
	 */
	public void save(User user) {
		
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
	    String uninno=user.getCompany();
		
		try {
			
		if ("100001".equals(uninno)) {
			conn=NcutJdbcUtils.getConnection();
		}else if ("100002".equals(uninno)) {
			
			conn=DxtJdbcUtils.getConnection();
		}else {
			conn=DefaultjdbcUtils.getConnection();
		}
			
			
			
			String sql="insert  into tab_user(telephone,password,sex,nickname,birthday,email,regdate,lastdate) value(?,?,?,?,?,?,?,?)";
			
			statement=conn.prepareStatement(sql);
			
			statement.setString(1, user.getTelephone());
			statement.setString(2, user.getPassword());
			statement.setInt(3, user.getSex());
			statement.setString(4, user.getNickname());
			statement.setString(5, user.getBirthday());
			statement.setString(6, user.getEmail());
			statement.setString(7, user.getRegdate());
			statement.setString(8, user.getLastdate());
			
			
			statement.executeUpdate();
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally
		{
			JdbcUtils.release(conn, statement, rs);
		}
	}


	/**
	 * @param user
	 */
	public void update(User user) {
		
		int times=user.getLogintimes()+1;
		
		String uninno=user.getCompany();
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			
		if ("100001".equals(uninno)) {
			conn=NcutJdbcUtils.getConnection();
		}else if ("100002".equals(uninno)) {
			
			conn=DxtJdbcUtils.getConnection();
		}else {
			conn=DefaultjdbcUtils.getConnection();
		}
		
		String sql="update tab_user set lastdate=? ,logintimes=? where user_id=?";
		
		  statement=conn.prepareStatement(sql);
		  
		  statement.setString(1, String.format("%tF %<tT", new Date()));
		  statement.setInt(2, times);
		  statement.setInt(3, user.getUser_id());
		
		  statement.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}finally
		{
			JdbcUtils.release(conn, statement, rs);
		}
	
		
	}


	/**
	 * @param string
	 * @param uninno
	 * @return
	 */
	public User findUserById(String user_id, String uninno) {
		
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		
		try {
			
		if ("100001".equals(uninno)) {
			conn=NcutJdbcUtils.getConnection();
		}else if ("100002".equals(uninno)) {
			
			conn=DxtJdbcUtils.getConnection();
		}else {
			conn=DefaultjdbcUtils.getConnection();
		}
			
			
			
			String sql="select * from tab_user where user_id=?";
			
			statement=conn.prepareStatement(sql);
			statement.setString(1, user_id);
			
			
			rs=statement.executeQuery();
			while (rs.next()) {
				
				User user=new User();
				
			     user.setUser_id(rs.getInt("user_id"));
				
			     
			     return user;
				
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally
		{
			JdbcUtils.release(conn, statement, rs);
		}
		
		
		
		
		return null;
	}


	

}
