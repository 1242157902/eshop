package com.eshop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.eshop.entrity.AdminUser;
import com.eshop.entrity.User;
import com.eshop.utils.ConnectionUtils;
import com.eshop.utils.NcutJdbcUtils;

public class UserDao {

	public void updateInfo(User user, String company) throws Exception {
		
			Connection conn = null;
			PreparedStatement statement = null;
			ResultSet rs = null;
			try {
				
				conn=ConnectionUtils.selectConn(company);
				String sql="update tab_user set   nickname = ? ,sex = ?, birthday=?, email=? ,career=?  where  user_id=?  ";
				
				statement=conn.prepareStatement(sql);
				
				
				 statement.setString(1, user.getNickname());
				 statement.setInt(2, user.getSex());
				 statement.setString(3, user.getBirthday());
				 statement.setString(4, user.getEmail());
				 statement.setString(5, user.getCareer());
				 statement.setInt(6, user.getUser_id());
				 
				
				statement.executeUpdate();
			 
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				NcutJdbcUtils.release(conn, statement, rs);
			}
		
	}

	public User getInfoById(int user_id, String company)throws Exception {
		
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			
			conn=ConnectionUtils.selectConn(company);
			String sql="select * from tab_user  where  user_id=?  ";
			
			statement=conn.prepareStatement(sql);
			statement.setInt(1, user_id);
			rs=statement.executeQuery();
			while (rs.next()) {
				
				User  user = new User();
				user.setCompany(company);
				user.setUser_id(rs.getInt("user_id"));
				user.setBirthday(rs.getString("birthday"));
				user.setNickname(rs.getString("nickname"));
				user.setSex(rs.getInt("sex"));
				user.setEmail(rs.getString("email"));
				user.setCareer(rs.getString("career"));
				user.setLastdate(rs.getString("lastdate"));
				user.setLogintimes(rs.getInt("logintimes"));
				user.setPassword(rs.getString("password"));
				user.setRegdate(rs.getString("regdate"));
				user.setTelephone(rs.getString("telephone"));
				return user;
			}
			} catch (Exception e) {
			e.printStackTrace();
			}finally{
			NcutJdbcUtils.release(conn, statement, rs);
		}
		
		return null;
	}
}

