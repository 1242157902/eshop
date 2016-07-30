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

import com.eshop.entrity.InStock;
import com.eshop.entrity.Order;
import com.eshop.entrity.view.VoOrderEntity;
import com.eshop.utils.ConnectionUtils;
import com.eshop.utils.NcutJdbcUtils;


 /**
  * 
  * <p>Title：        PurchaseDao<p>
  * <p>Description:  用于处理采购单的信息<p>
  * @date:           2015年11月2日下午8:08:13
  * @author:         ysl
  * @version         1.0
  */
public class OrderDao {
	
 
	public List<Order> findPurchaseByPagination(int currentPage,
			int pageSize, Map<String, Object> m)  
	{
		
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		List<Order> orderList = null;
		try {
			
			String company= (String) m.get("company");
			//conn=NcutJdbcUtils.getConnection();
			conn=ConnectionUtils.selectConn(company);
			orderList = new  ArrayList<Order>();
			String sql="select * from tab_order  where status = 2 ";
			 
			Set<Entry<String, Object>> set = m.entrySet();
			Iterator io = set.iterator();
			while (io.hasNext()) {
				Map.Entry<String, Object> me = (Map.Entry<String, Object>) io.next();
				if("Order_id".equals(me.getKey())&&me.getValue()!=null && !"".equals(me.getValue())){
					sql += " and " + me.getKey() + " = '"+ me.getValue()  +"' " ;
				}
				if("Consignee_name".equals(me.getKey()) &&me.getValue()!=null&& !"".equals(me.getValue())){
					sql += " and " + me.getKey() + " like '%"+ me.getValue()  +"%'" ;
				}
				if("Consignee_phone".equals(me.getKey()) &&me.getValue()!=null&& !"".equals(me.getValue())){
					sql += " and " + me.getKey() + " = '"+ me.getValue()  +" '" ;
				}
				if("Consignee_address".equals(me.getKey()) &&me.getValue()!=null&& !"".equals(me.getValue())){
					sql += " and " + me.getKey() + " like '%"+ me.getValue()  +"%'" ;
				}
				if("Create_time".equals(me.getKey()) && !"".equals(me.getValue())){
					sql += " and " + me.getKey() + " >= '" + me.getValue() +"'";
				}
				if("endtime".equals(me.getKey()) && !"".equals(me.getValue())){
					sql += " and " + " Create_time " + " <= '" + me.getValue() +"'";
				}
			/*	if("sort".equals(me.getKey()) &&me.getValue()!=null&& !"".equals(me.getValue())){
					sql += " order by " + me.getValue() ;
				}
				if("order".equals(me.getKey()) &&me.getValue()!=null&& !"".equals(me.getValue())){
					sql += " " + me.getValue();
				}	*/		
			}
			sql = sql +"order by Create_time Desc"+" limit " + (currentPage-1)*pageSize +" , "  + pageSize ;
			
			
			statement=conn.prepareStatement(sql);
			rs=statement.executeQuery();
			while (rs.next()) {
				
				Order  order = new Order();
				order.setOrder_id(rs.getString("Order_id"));
				order.setTotal(rs.getDouble("total"));
				order.setStatus(rs.getInt("status"));
				order.setUser_id(rs.getString("User_id"));
				order.setConsignee_address(rs.getString("Consignee_address"));
				order.setConsignee_name(rs.getString("Consignee_name"));
				order.setConsignee_phone(rs.getString("Consignee_phone"));
				order.setConsignee_zipcode(rs.getString("Consignee_zipcode"));
				order.setCreate_time(rs.getString("Create_time"));
				orderList.add(order);
			}
			return orderList;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			NcutJdbcUtils.release(conn, statement, rs);
		}
		return null;
	}

	/**
	 * 
	 * @return:       int 
	 * @param m
	 * @return
	 * @throws Exception
	 * <p>Description:查询分页的记录总数 <p>
	 * @date:          2015年11月1日下午7:09:34
	 * @author         ysl
	 */
	public int getTotal(Map<String, Object> m) 
	{
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		int count = 0;
		try {
			String company= (String) m.get("company");
			//conn=NcutJdbcUtils.getConnection();
			conn=ConnectionUtils.selectConn(company);
			
			String sql="select count(*) from   tab_order  where status = 2 ";
			
			Set<Entry<String, Object>> set = m.entrySet();
			Iterator io = set.iterator();
			while (io.hasNext()) {
				Map.Entry<String, Object> me = (Map.Entry<String, Object>) io.next();
				if("Order_id".equals(me.getKey())&&me.getValue()!=null && !"".equals(me.getValue())){
					sql += " and " + me.getKey() + " = '"+ me.getValue()  +"' " ;
				}
				if("Consignee_name".equals(me.getKey()) &&me.getValue()!=null&& !"".equals(me.getValue())){
					sql += " and " + me.getKey() + " like '%"+ me.getValue()  +"%'" ;
				}
				if("Consignee_phone".equals(me.getKey()) &&me.getValue()!=null&& !"".equals(me.getValue())){
					sql += " and " + me.getKey() + " = '"+ me.getValue()  +" '" ;
				}
				if("Consignee_address".equals(me.getKey()) &&me.getValue()!=null&& !"".equals(me.getValue())){
					sql += " and " + me.getKey() + " like '%"+ me.getValue()  +"%'" ;
				}
				if("Create_time".equals(me.getKey()) && !"".equals(me.getValue())){
					sql += " and " + me.getKey() + " >= '" + me.getValue() +"'";
				}
				if("endtime".equals(me.getKey()) && !"".equals(me.getValue())){
					sql += " and " + " Create_time " + " <= '" + me.getValue() +"'";
				}
			/*	if("sort".equals(me.getKey()) &&me.getValue()!=null&& !"".equals(me.getValue())){
					sql += " order by " + me.getValue() ;
				}
				if("order".equals(me.getKey()) &&me.getValue()!=null&& !"".equals(me.getValue())){
					sql += " " + me.getValue();
				}		*/	
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

	public Order findOrderById(String order_id,String company) throws Exception{
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			
			conn=ConnectionUtils.selectConn(company);
			String sql="select * from tab_order where Order_id =? ";

			statement=conn.prepareStatement(sql);
			statement.setString(1, order_id);
			rs=statement.executeQuery();
			while (rs.next()) {
				
				Order  order = new Order();
				order.setOrder_id(rs.getString("Order_id"));
				order.setTotal(rs.getDouble("total"));
				order.setStatus(rs.getInt("status"));
				order.setUser_id(rs.getString("User_id"));
				order.setConsignee_address(rs.getString("Consignee_address"));
				order.setConsignee_name(rs.getString("Consignee_name"));
				order.setConsignee_phone(rs.getString("Consignee_phone"));
				order.setConsignee_zipcode(rs.getString("Consignee_zipcode"));
				order.setCreate_time(rs.getString("Create_time"));
				order.setDelivery(rs.getInt("delivery"));
				order.setNick_name(rs.getString("nick_name"));
				return order;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			NcutJdbcUtils.release(conn, statement, rs);
		}
		return null;
	}
	
	public void updateOrder(Order order,String company)throws Exception{
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			
			conn=ConnectionUtils.selectConn(company);
			String sql="update tab_order set total = ?,User_id = ?,Consignee_name = ? ,status = ?, Consignee_address =?,Consignee_zipcode =?,Create_time =? ,Consignee_phone = ?  where  Order_id=?  ";
			
			statement=conn.prepareStatement(sql);
			
			
			statement.setDouble(1, order.getTotal());
			statement.setString(2, order.getUser_id());
			statement.setString(3, order.getConsignee_name());
			statement.setInt(4,  order.getStatus());
			statement.setString(5, order.getConsignee_address());
			statement.setString(6, order.getConsignee_zipcode());
			statement.setString(7, order.getCreate_time());
			statement.setString(8, order.getConsignee_phone());
			statement.setString(9, order.getOrder_id());
			
			
			statement.executeUpdate();
		 
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			NcutJdbcUtils.release(conn, statement, rs);
		}
	}

	/**
	 * 
	 * @return:       void 
	 * @param purchase
	 * <p>Description:插入到采购表中 <p>
	 * @date:          2015年11月4日上午9:56:45
	 * @author         ysl
	 */
	public void insertPurchase(InStock purchase,String company) {
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			
			conn=ConnectionUtils.selectConn(company);
			String sql="insert  into tab_purchase(purchase_id,quantity,purchasetime,purchase_total,Manager_name,status) value(?,?,?,?,?,?)";
			
			statement=conn.prepareStatement(sql);
			/*statement.setString(1,purchase.getPurchase_id());
			statement.setInt(2,purchase.getQuantity());
			statement.setString(3,purchase.getPurchasetime());
			statement.setDouble(4, purchase.getPurchase_total());*/
			statement.setString(5,purchase.getManager_name());
			statement.setInt(6,purchase.getStatus());
			statement.executeUpdate();
		 
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			NcutJdbcUtils.release(conn, statement, rs);
		}
	}

	/**
	 * @param order
	 * @param company
	 */
	public void save(Order order, String company) {
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			
			conn=ConnectionUtils.selectConn(company);
			String sql="insert  into tab_order(Order_id,Create_time,total,User_id,Consignee_name,Consignee_phone,Consignee_address,Consignee_zipcode,delivery,status,nick_name) value(?,?,?,?,?,?,?,?,?,?,?)";
		
			statement=conn.prepareStatement(sql);
			statement.setString(1,order.getOrder_id());
			statement.setString(2,order.getCreate_time());
			statement.setDouble(3,order.getTotal());
			statement.setString(4,order.getUser_id() );
			statement.setString(5,order.getConsignee_name());
			statement.setString(6,order.getConsignee_phone());
			statement.setString(7,order.getConsignee_address());
			statement.setString(8,order.getConsignee_zipcode());
			statement.setInt(9,order.getDelivery());
			statement.setInt(10,order.getStatus());
			statement.setString(11, order.getNick_name());
			statement.executeUpdate();
		 
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			NcutJdbcUtils.release(conn, statement, rs);
		}
		
	}

	/**
	 * @param user_id
	 * @param company
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public List<Order> getAllByUser(int user_id, String company,
			int currentPage, int pageSize) {
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		List<Order> orderList=new ArrayList<Order>();
		try {
			conn=ConnectionUtils.selectConn(company);
			String sql = "SELECT *  from tab_order  where user_id=?  order by Create_time DESC limit ?,?";
			        statement = conn.prepareStatement(sql);
					statement.setInt(1, user_id);
					statement.setInt(2, (currentPage - 1) * pageSize);
					statement.setInt(3, pageSize);
					rs = statement.executeQuery();
					while (rs.next()) {
						Order order= new Order();
						order.setOrder_id(rs.getString("Order_id"));
						order.setTotal(rs.getDouble("total"));
						order.setStatus(rs.getInt("status"));
						order.setUser_id(rs.getString("User_id"));
						order.setConsignee_address(rs.getString("Consignee_address"));
						order.setConsignee_name(rs.getString("Consignee_name"));
						order.setConsignee_phone(rs.getString("Consignee_phone"));
						order.setConsignee_zipcode(rs.getString("Consignee_zipcode"));
						order.setCreate_time(rs.getString("Create_time"));
						order.setDelivery(rs.getInt("delivery"));
							orderList.add(order);
						}
								
					return orderList;		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<VoOrderEntity> getVoOrderList(Map<String, Object> m) {
		
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		List<VoOrderEntity> voOrderEntityList = null;
		try {
			
			String company= (String) m.get("company");
			conn=ConnectionUtils.selectConn(company);
			voOrderEntityList = new  ArrayList<VoOrderEntity>();
			String sql="select * from tab_order  where status = 3  and nick_name IS NOT  NULL   ";
			 
			Set<Entry<String, Object>> set = m.entrySet();
			Iterator io = set.iterator();
			while (io.hasNext()) {
				Map.Entry<String, Object> me = (Map.Entry<String, Object>) io.next();
				if("Create_time".equals(me.getKey()) && !"".equals(me.getValue())){
					sql += " and " + me.getKey() + " >= '" + me.getValue() +"'";
				}
				if("endtime".equals(me.getKey()) && !"".equals(me.getValue())){
					sql += " and " + " Create_time " + " <= '" + me.getValue() +"'";
				}
			}
			sql = sql +"order by nick_name ASC"  ;
			
			
			statement=conn.prepareStatement(sql);
			rs=statement.executeQuery();
			while (rs.next()) {
				VoOrderEntity  voOrderEntity = new VoOrderEntity();
				voOrderEntity.setOrder_id(rs.getString("Order_id"));
				voOrderEntity.setConsignee_address(rs.getString("Consignee_address"));
				voOrderEntity.setConsignee_name(rs.getString("Consignee_name"));
				voOrderEntity.setConsignee_phone(rs.getString("Consignee_phone"));
				voOrderEntity.setNick_name(rs.getString("nick_name"));
				voOrderEntity.setCreate_time(rs.getString("Create_time"));
				voOrderEntityList.add(voOrderEntity);
			}
			return voOrderEntityList;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			NcutJdbcUtils.release(conn, statement, rs);
		}
		return null;
	}

	/**
	 * @param user_id
	 * @param company
	 * @return
	 */
	public int getTotalCount(int user_id, String company) {
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		int count=0;
		
		try {
			conn=ConnectionUtils.selectConn(company);
			String sql = "SELECT count(*) count  from tab_order  where user_id=? ";
			        statement = conn.prepareStatement(sql);
					statement.setInt(1, user_id);
					rs = statement.executeQuery();
					while (rs.next()) {
						count = rs.getInt("count");
						}
								
							
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

	/**
	 * @param user_id
	 * @param company
	 * @return
	 */
	public Order getOrderByUserId(int user_id, String company) {
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			
			conn=ConnectionUtils.selectConn(company);
			String sql="select * from tab_order where user_id =? order by Create_time DESC limit 0,1 ";

			statement=conn.prepareStatement(sql);
			statement.setInt(1, user_id);
			rs=statement.executeQuery();
			while (rs.next()) {
				
				Order  order = new Order();
				order.setOrder_id(rs.getString("Order_id"));
				order.setTotal(rs.getDouble("total"));
				order.setStatus(rs.getInt("status"));
				order.setUser_id(rs.getString("User_id"));
				order.setConsignee_address(rs.getString("Consignee_address"));
				order.setConsignee_name(rs.getString("Consignee_name"));
				order.setConsignee_phone(rs.getString("Consignee_phone"));
				order.setConsignee_zipcode(rs.getString("Consignee_zipcode"));
				order.setCreate_time(rs.getString("Create_time"));
				order.setDelivery(rs.getInt("delivery"));
				order.setNick_name(rs.getString("nick_name"));
				return order;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			NcutJdbcUtils.release(conn, statement, rs);
		}
		return null;
	}

	/**
	 * @param nickName
	 * @param company
	 * @return
	 */
	public Order getOrderByNickName(String nickName, String company) {
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			
			conn=ConnectionUtils.selectConn(company);
			String sql="select * from tab_order where nick_name =? order by Create_time DESC limit 0,1 ";

			statement=conn.prepareStatement(sql);
			statement.setString(1, nickName);
			rs=statement.executeQuery();
			while (rs.next()) {
				
				Order  order = new Order();
				order.setOrder_id(rs.getString("Order_id"));
				order.setTotal(rs.getDouble("total"));
				order.setStatus(rs.getInt("status"));
				order.setUser_id(rs.getString("User_id"));
				order.setConsignee_address(rs.getString("Consignee_address"));
				order.setConsignee_name(rs.getString("Consignee_name"));
				order.setConsignee_phone(rs.getString("Consignee_phone"));
				order.setConsignee_zipcode(rs.getString("Consignee_zipcode"));
				order.setCreate_time(rs.getString("Create_time"));
				order.setDelivery(rs.getInt("delivery"));
				order.setNick_name(rs.getString("nick_name"));
				return order;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			NcutJdbcUtils.release(conn, statement, rs);
		}
		return null;
	}
	
	
	
}
