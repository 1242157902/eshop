package com.eshop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.eshop.entrity.AdminUser;
import com.eshop.entrity.MobileModel;
import com.eshop.entrity.User;
import com.eshop.utils.DefaultjdbcUtils;
import com.eshop.utils.DxtJdbcUtils;
import com.eshop.utils.JdbcUtils;
import com.eshop.utils.NcutJdbcUtils;


/**
 * 管理员登录Dao类
 * @author Jane
 *
 */
public class AdminLoginDao {
	
	
	//管理员登录
	public AdminUser login(String telephone,String password,String companyId) throws SQLException{
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		 
		/*AdminUser mb=getmbbynum(telephone);
		*/
		
		
		try {
			String uninno=companyId;
			if ("100001".equals(uninno)) {
				conn=NcutJdbcUtils.getConnection();
			}else if ("100002".equals(uninno)) {
				
				conn=DxtJdbcUtils.getConnection();
			}else {
				conn=DefaultjdbcUtils.getConnection();
			}
				
				
				
				String sql="select * from tab_manageruser where phone=? and pwd=?";
				
				statement=conn.prepareStatement(sql);
				statement.setString(1, telephone);
				statement.setString(2, password);
				
				
				rs=statement.executeQuery();
				while (rs.next()) {
					
					AdminUser user=new AdminUser();
					
				     user.setId(rs.getInt("id"));
				     user.setName(rs.getString("name"));
				     user.setPhone(rs.getString("phone"));
				     user.setCom_no(String.valueOf(rs.getInt("com_no")));
				     user.setPwd(rs.getString("pwd"));
				     user.setStatus(rs.getInt("status"));
				     return user;
					
				}
			}catch (NullPointerException e) {
				return null;
			} catch (Exception e) {
				e.printStackTrace();
			} finally{
				NcutJdbcUtils.release(conn, statement, rs);
			}
			
			return null;
		}
		

	public AdminUser getmbbynum(String telephone) throws SQLException {
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		
		try {
			
			conn=NcutJdbcUtils.getConnection();
			String sql="select * from tab_manageruser where phone=?";
			
			statement=conn.prepareStatement(sql);
			statement.setString(1, telephone);
			
			
			rs=statement.executeQuery();
			while (rs.next()) {
				
				AdminUser admin=new AdminUser();
				admin.setCom_no(rs.getString("com_no"));
				
				return admin;
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			NcutJdbcUtils.release(conn, statement, rs);
		}
		
		return null;
	}
}
