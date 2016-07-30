package com.eshop.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.eshop.entrity.User;
import com.eshop.entrity.UserAdress;
import com.eshop.utils.ConnectionUtils;
import com.eshop.utils.NcutJdbcUtils;

/**
 * @author  JYM
 * 2015-12-2 下午5:23:15
 */
public class UserAdressDao {
	
	LoginDao userDao=new LoginDao();

	/**
	 * @param user_id
	 * @param company
	 * @return
	 */
	public List<UserAdress> findByUserid(int user_id, String company) {
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			conn=ConnectionUtils.selectConn(company);
			List<UserAdress> adressList=new ArrayList<UserAdress>();
			
			String sql="select * from tab_Useradress where user_id =?  order by createtime desc";
			
			statement=conn.prepareStatement(sql);
			statement.setInt(1, user_id);
			rs=statement.executeQuery();
			
			while(rs.next()){
				UserAdress userAdress=new UserAdress();
				userAdress.setId(rs.getInt("id"));
				userAdress.setUser_id(rs.getInt("user_id"));
				userAdress.setName(rs.getString("name"));
				userAdress.setAdress(rs.getString("adress"));
				userAdress.setPhone(rs.getString("phone"));
				userAdress.setZipcode(rs.getString("zipcode"));
				userAdress.setStatus(rs.getInt("status"));	
				userAdress.setCreatetime(rs.getString("createtime"));
				userAdress.setUser(userDao.findUserById(user_id+"", company));
				adressList.add(userAdress);
			}
			
			return adressList;
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			NcutJdbcUtils.release(conn, statement, rs);
		}
		
		return null;
	}

	/**
	 * @param userAdress
	 * @param company
	 */
	public void save(UserAdress userAdress, String company) {
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			
			conn=ConnectionUtils.selectConn(company);
			String sql="insert  into tab_useradress(user_id,name,adress,phone,zipcode,status,createtime) value(?,?,?,?,?,?,?)";
		
			statement=conn.prepareStatement(sql);
			statement.setInt(1,userAdress.getUser_id());
			statement.setString(2,userAdress.getName());
			statement.setString(3, userAdress.getAdress());
			statement.setString(4,userAdress.getPhone());
			statement.setString(5,userAdress.getZipcode());
			statement.setInt(6, userAdress.getStatus());
			statement.setString(7, userAdress.getCreatetime());
			statement.executeUpdate();
		 
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			NcutJdbcUtils.release(conn, statement, rs);
		}
		
		
	}
	
	
	
	
	

}
