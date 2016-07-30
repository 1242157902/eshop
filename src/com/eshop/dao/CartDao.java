package com.eshop.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.eshop.entrity.CartItem;
import com.eshop.entrity.MobileModel;
import com.eshop.entrity.User;
import com.eshop.utils.ConnectionUtils;
import com.eshop.utils.DefaultjdbcUtils;
import com.eshop.utils.DxtJdbcUtils;
import com.eshop.utils.NcutJdbcUtils;



/**
 * @author  JYM
 * 2015-11-12 下午4:54:56
 */
public class CartDao {
	
	 /**
	  * 添加购物车
	  * */
   LoginDao loginDao=new LoginDao();  
   GoodDao goodDao=new GoodDao();
   
  
	
	
	

	/**
	 * @param user_id
	 * @param good_id
	 * @return
	 */
	public CartItem getByuserAndgoodid(User  user, String good_id) {
	
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		  
		MobileModel  mobilmodel=loginDao.getmbbynum(user.getTelephone());
		
		  String unitno=mobilmodel.getCompany();
		
		  try {
				
				if ("100001".equals(unitno)) {
					conn=NcutJdbcUtils.getConnection();
				}else if ("100002".equals(unitno)) {
					
					conn=DxtJdbcUtils.getConnection();
				}else {
					conn=DefaultjdbcUtils.getConnection();
				}
				
				String  sql="select * from tab_cart where user_id=? and good_id=?";
				
				statement=conn.prepareStatement(sql);
				statement.setString(1, user.getUser_id()+"");
				statement.setString(2, good_id);
				
				rs=statement.executeQuery();
				while (rs.next()) {
				
					CartItem item=new CartItem();
					item.setCartitemid(rs.getString("cartitemid"));
					item.setGood_id(rs.getString("good_id"));
					item.setUser(getuserbyid(unitno,rs.getString("user_id")));
					item.setUser_id(rs.getString("user_id"));
					item.setGood_quantity(rs.getInt("good_quantity"));
					item.setGoods(goodDao.findGoodById(good_id, unitno));
					return item;
					
				}
		
		  }catch (Exception e) {
		       e.printStackTrace();
		}finally{
			NcutJdbcUtils.release(conn, statement, rs);
		}
		  return null;
	
	}


	public  User getuserbyid(String unitno, String string) {
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			if ("100001".equals(unitno)) {
				conn=NcutJdbcUtils.getConnection();
			}else if ("100002".equals(unitno)) {
				
				conn=DxtJdbcUtils.getConnection();
			}else {
				conn=DefaultjdbcUtils.getConnection();
			}
			String  sql="select * from tab_user where user_id=? ";
			statement=conn.prepareStatement(sql);
			statement.setInt(1, Integer.parseInt(string));
			rs=statement.executeQuery();
			while (rs.next()) {
				User user=new User();
				user.setUser_id(rs.getInt("user_id"));
			    user.setNickname(rs.getString("nickname"));
			    user.setBirthday(rs.getString("birthday"));
			    user.setEmail(rs.getString("email"));
			    user.setTelephone(rs.getString("telephone"));
			    user.setLogintimes(rs.getInt("logintimes"));
			    user.setSex(rs.getInt("sex"));
			 
			  
			     return user;
				
				
			}

		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			NcutJdbcUtils.release(conn, statement, rs);
		}
		  
		return null;
	}
	
	
	/**
	 * @param cartItem
	 * @param company
	 */
	public void save(CartItem cartItem, String unitno) {
		
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		
		try {
			if ("100001".equals(unitno)) {
				conn=NcutJdbcUtils.getConnection();
			}else if ("100002".equals(unitno)) {
				
				conn=DxtJdbcUtils.getConnection();
			}else {
				conn=DefaultjdbcUtils.getConnection();
			}
			String  sql="insert into tab_cart (cartitemid,good_quantity,good_id,user_id,create_time) value(?,?,?,?,?)";
            statement=conn.prepareStatement(sql);
			statement.setString(1, cartItem.getCartitemid());
			statement.setInt(2, cartItem.getGood_quantity());
			statement.setString(3, cartItem.getGood_id());
			statement.setString(4, cartItem.getUser_id());
			statement.setString(5, cartItem.getCreate_time());
			statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			NcutJdbcUtils.release(conn, statement, rs);
		}
		
	}

	

	/**
	 * @param _cartItem
	 */
	public void update(CartItem _cartItem,String unitno) {
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		
		try {
			
		if ("100001".equals(unitno)) {
			conn=NcutJdbcUtils.getConnection();
		}else if ("100002".equals(unitno)) {
			
			conn=DxtJdbcUtils.getConnection();
		}else {
			conn=DefaultjdbcUtils.getConnection();
		}
		
		String sql="update tab_cart set good_quantity=? , create_time=?  where cartitemid=?";
		
		  statement=conn.prepareStatement(sql);
		  
		  statement.setInt(1,_cartItem.getGood_quantity() );
		  statement.setString(2, String.format("%tF %<tT", new Date()));
		  statement.setString(3,  _cartItem.getCartitemid());
		  
		  statement.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			NcutJdbcUtils.release(conn, statement, rs);
		}
	
	}


	/**
	 * @param userid
	 * @param uninno 
	 * @return
	 */
	public List<CartItem> find(String userid, String uninno) {
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		List<CartItem> cartItems=new ArrayList<CartItem>();
		try {
			if ("100001".equals(uninno)) {
				conn=NcutJdbcUtils.getConnection();
			}else if ("100002".equals(uninno)) {
				
				conn=DxtJdbcUtils.getConnection();
			}else {
				conn=DefaultjdbcUtils.getConnection();
			}
			
			String sql="select * from tab_cart where user_id=?";
			statement=conn.prepareStatement(sql);
			statement.setString(1, userid);
			rs=statement.executeQuery();
			while (rs.next()) {
				CartItem cartItem=new CartItem();
				cartItem.setCartitemid(rs.getString("cartitemid"));
				cartItem.setGood_quantity(rs.getInt("good_quantity"));
				cartItem.setGood_id(rs.getString("good_id"));
				cartItem.setGoods(goodDao.findGoodById(rs.getString("good_id"), uninno));
				cartItem.setUser_id(rs.getString("user_id"));
				cartItem.setUser(loginDao.findUserById(rs.getString("user_id"), uninno));
				cartItems.add(cartItem);
				
				
			}
			return cartItems;
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			NcutJdbcUtils.release(conn, statement, rs);
		}
		
		return null;
	}


	/**
	 * @param cartitemid
	 * @param uninno
	 * @return
	 */
	public CartItem getCartById(String cartitemid, String uninno) {
		
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			
			conn=ConnectionUtils.selectConn(uninno);
			
			String sql="select * from tab_cart where cartitemid=?";
			
			statement=conn.prepareStatement(sql);
			
			statement.setString(1, cartitemid);
			
			rs=statement.executeQuery();
			
			while (rs.next()) {
				CartItem cartItem=new CartItem();
				
				cartItem.setCartitemid(rs.getString("cartitemid"));
				cartItem.setGood_quantity(rs.getInt("good_quantity"));
				cartItem.setGood_id(rs.getString("good_id"));
				cartItem.setGoods(goodDao.findGoodById(rs.getString("good_id"), uninno));
				cartItem.setUser_id(rs.getString("user_id"));
				cartItem.setUser(loginDao.findUserById(rs.getString("user_id"), uninno));
				
				return cartItem;
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			NcutJdbcUtils.release(conn, statement, rs);
		}
		
		
		
		
		return null;
	}


	/**
	 * @param cartItem
	 * @param company
	 */
	public void delete(CartItem cartItem, String company) {
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			
			conn=ConnectionUtils.selectConn(company);
			
			String sql="delete from tab_cart where cartitemid=?";
			statement=conn.prepareStatement(sql);
			statement.setString(1, cartItem.getCartitemid());
		    statement.execute();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			NcutJdbcUtils.release(conn, statement, rs);
		}
		
	}



}
