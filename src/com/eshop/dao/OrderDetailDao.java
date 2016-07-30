package com.eshop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.eshop.entrity.Order;
import com.eshop.entrity.OrderDetail;
import com.eshop.service.OrderServiceImpl;
import com.eshop.service.interf.OrderService;
import com.eshop.utils.ConnectionUtils;
import com.eshop.utils.NcutJdbcUtils;


 /**
  * 
  * <p>Title：        OrderDetailDao<p>
  * <p>Description: 处理订单详细的dao类 <p>
  * @date:           2015年11月4日下午2:29:56
  * @author:         ysl
  * @version         1.0
  */
public class OrderDetailDao {
	
	GoodDao goodDao=new GoodDao();
	
 
	public List<OrderDetail> findOrderDetailByOrderId(String order_id,String company) throws Exception {
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		
		try {
			
			conn=ConnectionUtils.selectConn(company);
			List<OrderDetail> orderDetailList=new ArrayList<OrderDetail>();
			String sql="select * from tab_order_detail where Order_id =? ";

			statement=conn.prepareStatement(sql);
			statement.setString(1, order_id);
			rs=statement.executeQuery();
			while (rs.next()) {
				
				OrderDetail  orderDetail = new OrderDetail();
				orderDetail.setOrderitemid(rs.getString("orderitemid"));
				orderDetail.setGood_id(rs.getString("Good_id"));
				orderDetail.setGood_quantity(rs.getInt("Good_quantity")) ;
				orderDetail.setGood_name(rs.getString("Good_name"));
				orderDetail.setCurrprice(rs.getDouble("currprice"));
				orderDetail.setOrder_id(rs.getString("Order_id"));
				orderDetail.setSubtotal(rs.getDouble("subtotal"));
				orderDetail.setComment_time(rs.getString("comment_time"));
				orderDetail.setGrade(rs.getInt("grade"));
				orderDetail.setGood_comment(rs.getString("Good_comment"));
				orderDetail.setGood(goodDao.findGoodById(rs.getString("Good_id"), company));
				orderDetailList.add(orderDetail);
			}
			return orderDetailList;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			NcutJdbcUtils.release(conn, statement, rs);
		}
		return null;
	}

	/**
	 * @param orderItem
	 * @param company
	 */
	public void save(OrderDetail orderItem, String company) {
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
try {
			
			conn=ConnectionUtils.selectConn(company);
			
			String sql="insert  into tab_order_detail(orderitemid,Good_quantity,Good_id,currprice,Order_id,subtotal,Good_name) value(?,?,?,?,?,?,?)";
		 
			statement=conn.prepareStatement(sql);
			statement.setString(1,orderItem.getOrderitemid());
			statement.setInt(2,orderItem.getGood_quantity());
			statement.setString(3,orderItem.getGood_id());
			statement.setDouble(4,orderItem.getCurrprice());
			statement.setString(5,orderItem.getOrder_id());
			statement.setDouble(6,orderItem.getSubtotal());
			statement.setString(7,orderItem.getGood_name());
			statement.executeUpdate();
		 
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			NcutJdbcUtils.release(conn, statement, rs);
		}
		
	}

	public OrderDetail findOrderDetailById(String orderDetail_id, String company) {
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		
		try {
			
			conn=ConnectionUtils.selectConn(company);
			
			String sql="select * from tab_order_detail where orderitemid =? ";

			statement=conn.prepareStatement(sql);
			statement.setString(1, orderDetail_id);
			rs=statement.executeQuery();
			while (rs.next()) {
				
				OrderDetail orderDetail=new OrderDetail();
				orderDetail.setOrderitemid(rs.getString("orderitemid"));
				orderDetail.setGood_id(rs.getString("Good_id"));
				orderDetail.setGood_quantity(rs.getInt("Good_quantity")) ;
				orderDetail.setGood_name(rs.getString("Good_name"));
				orderDetail.setCurrprice(rs.getDouble("currprice"));
				orderDetail.setOrder_id(rs.getString("Order_id"));
				orderDetail.setSubtotal(rs.getDouble("subtotal"));
				orderDetail.setComment_time(rs.getString("comment_time"));
				orderDetail.setGrade(rs.getInt("grade"));
				orderDetail.setGood_comment(rs.getString("Good_comment"));
				orderDetail.setGood(goodDao.findGoodById(rs.getString("Good_id"), company));
				return orderDetail;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			NcutJdbcUtils.release(conn, statement, rs);
		}
		return null;
	}

	public void saveComment(OrderDetail orderDetail, String company) {
		
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			
			conn=ConnectionUtils.selectConn(company);
			
			String sql="update   tab_order_detail set grade=?,Good_comment=?,comment_time=? where orderitemid=?";
		 
			statement=conn.prepareStatement(sql);
			statement.setInt(1,orderDetail.getGrade());
			statement.setString(2,orderDetail.getGood_comment());
			statement.setString(3,orderDetail.getComment_time());
			statement.setString(4,orderDetail.getOrderitemid());
			statement.executeUpdate();
		 
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			NcutJdbcUtils.release(conn, statement, rs);
		}
		
	}

/* 
	public void insertPurchaseDetail(PurchaseDetail purchaseDetail) {
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			
			conn=NcutJdbcUtils.getConnection();
			String sql="insert  into tab_purchase_detail(purchaseitemid,Good_id,Good_name,Good_quantity,Good_price,purchase_id,subtotal) value(?,?,?,?,?,?,?)";
			
			statement=conn.prepareStatement(sql);
			statement.setString(1,purchaseDetail.getPurchaseitemid());
			statement.setString(2,purchaseDetail.getGood_id());
			statement.setString(3,purchaseDetail.getGood_name());
			statement.setInt(4, purchaseDetail.getGood_quantity());
			statement.setDouble(5,purchaseDetail.getGood_price());
			statement.setString(6,purchaseDetail.getPurchase_id());
			statement.setDouble(7, purchaseDetail.getSubtotal());
			statement.executeUpdate();
		 
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			NcutJdbcUtils.release(conn, statement, rs);
		}
	}*/
	
}
