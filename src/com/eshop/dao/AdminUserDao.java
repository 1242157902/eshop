package com.eshop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.eshop.entrity.AdminUser;
import com.eshop.utils.ConnectionUtils;
import com.eshop.utils.NcutJdbcUtils;

public class AdminUserDao {

	public List<AdminUser> findPurchaseByPagination(int currentPage,
			int pageSize, Map<String, Object> m) {

		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		List<AdminUser> adminUserList = null;
		try {
			
			String company= (String) m.get("company");
			//conn=NcutJdbcUtils.getConnection();
			conn=ConnectionUtils.selectConn(company);
			adminUserList = new  ArrayList<AdminUser>();
			String sql="select * from tab_managerUser  where 1 = 1";
			 
			Set<Entry<String, Object>> set = m.entrySet();
			Iterator io = set.iterator();
			while (io.hasNext()) {
				Map.Entry<String, Object> me = (Map.Entry<String, Object>) io.next();
				if("phone".equals(me.getKey())&&me.getValue()!=null && !"".equals(me.getValue())){
					sql += " and " + me.getKey() + " = '"+ me.getValue()  +"' " ;
				}
				if("name".equals(me.getKey()) &&me.getValue()!=null&& !"".equals(me.getValue())){
					sql += " and " + me.getKey() + " like '%"+ me.getValue()  +"%'" ;
				}
				if("com_no".equals(me.getKey()) &&me.getValue()!=null&& !"".equals(me.getValue())){
					sql += " and " + me.getKey() + " = '"+ me.getValue()  +" '" ;
				}
				if("status".equals(me.getKey()) &&me.getValue()!=null&& !"".equals(me.getValue())){
					sql += " and " + me.getKey() + " = '"+ me.getValue()  +" '" ;
				}
				if("sort".equals(me.getKey()) &&me.getValue()!=null&& !"".equals(me.getValue())){
					sql += " order by " + me.getValue() ;
				}
				if("order".equals(me.getKey()) &&me.getValue()!=null&& !"".equals(me.getValue())){
					sql += " " + me.getValue();
				}			
			}
			sql = sql +" limit " + (currentPage-1)*pageSize +" , "  + pageSize ;
			
			
			statement=conn.prepareStatement(sql);
			rs=statement.executeQuery();
			while (rs.next()) {
				
				AdminUser  adminUser = new AdminUser();
				adminUser.setId(rs.getInt("id"));
				adminUser.setCom_no(rs.getString("com_no"));
				adminUser.setName(rs.getString("name"));
				adminUser.setPhone(rs.getString("phone"));
				adminUser.setPwd(rs.getString("pwd"));
				adminUser.setStatus(rs.getInt("status"));
				adminUserList.add(adminUser);
			}
			return adminUserList;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			NcutJdbcUtils.release(conn, statement, rs);
		}
		return null;
	}

	public int getTotal(Map<String, Object> m) throws Exception {
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		int count = 0;
		try {
			String company= (String) m.get("company");
			//conn=NcutJdbcUtils.getConnection();
			conn=ConnectionUtils.selectConn(company);
			
			String sql="select count(*) from tab_managerUser  where 1 = 1";
			 
			Set<Entry<String, Object>> set = m.entrySet();
			Iterator io = set.iterator();
			while (io.hasNext()) {
				Map.Entry<String, Object> me = (Map.Entry<String, Object>) io.next();
				if("phone".equals(me.getKey())&&me.getValue()!=null && !"".equals(me.getValue())){
					sql += " and " + me.getKey() + " = '"+ me.getValue()  +"' " ;
				}
				if("name".equals(me.getKey()) &&me.getValue()!=null&& !"".equals(me.getValue())){
					sql += " and " + me.getKey() + " like '%"+ me.getValue()  +"%'" ;
				}
				if("com_no".equals(me.getKey()) &&me.getValue()!=null&& !"".equals(me.getValue())){
					sql += " and " + me.getKey() + " = '"+ me.getValue()  +" '" ;
				}
				if("status".equals(me.getKey()) &&me.getValue()!=null&& !"".equals(me.getValue())){
					sql += " and " + me.getKey() + " = '"+ me.getValue()  +" '" ;
				}
				if("sort".equals(me.getKey()) &&me.getValue()!=null&& !"".equals(me.getValue())){
					sql += " order by " + me.getValue() ;
				}
				if("order".equals(me.getKey()) &&me.getValue()!=null&& !"".equals(me.getValue())){
					sql += " " + me.getValue();
				}			
			}
			statement=conn.prepareStatement(sql);
			rs=statement.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally
		{
			NcutJdbcUtils.release(conn, statement, rs);
		}
		return count;
	}

	public AdminUser findAdminUserById(String order_id, String company)
			throws Exception {
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			
			conn=ConnectionUtils.selectConn(company);
			String sql="select * from tab_managerUser where  id =? ";

			statement=conn.prepareStatement(sql);
			statement.setString(1, order_id);
			rs=statement.executeQuery();
			while (rs.next()) {
				
				AdminUser  adminUser = new AdminUser();
				adminUser.setId(rs.getInt("id"));
				adminUser.setCom_no(rs.getString("com_no"));
				adminUser.setName(rs.getString("name"));
				adminUser.setPhone(rs.getString("phone"));
				adminUser.setPwd(rs.getString("pwd"));
				adminUser.setStatus(rs.getInt("status"));
				return adminUser;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			NcutJdbcUtils.release(conn, statement, rs);
		}
		return null;
	}

	public void updateAdminUser(AdminUser adminUser, String company)
			throws Exception {
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			
			conn=ConnectionUtils.selectConn(company);
			String sql="update tab_managerUser set   name = ? ,phone = ?, pwd =?,com_no =?,status = ?   where  id=?  ";
			
			statement=conn.prepareStatement(sql);
			
			
			 statement.setString(1, adminUser.getName());
			 statement.setString(2, adminUser.getPhone());
			 statement.setString(3, adminUser.getPwd());
			 statement.setString(4, adminUser.getCom_no());
			 statement.setInt(5, adminUser.getStatus());
			 statement.setInt(6, adminUser.getId());
			
			
			statement.executeUpdate();
		 
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			NcutJdbcUtils.release(conn, statement, rs);
		}
	}

	public void insertAdminUser(AdminUser adminUser,String company) throws Exception {
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			
			conn=ConnectionUtils.selectConn(company);
			String sql="insert  into tab_managerUser(name,phone,pwd,com_no) value(?,?,?,?)";
			
			statement=conn.prepareStatement(sql);
			statement.setString(1,adminUser.getName());
			statement.setString(2,adminUser.getPhone());
			statement.setString(3,adminUser.getPwd());
			statement.setString(4,adminUser.getCom_no());
			 
			statement.executeUpdate();
		 
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			NcutJdbcUtils.release(conn, statement, rs);
		}
	}
}
