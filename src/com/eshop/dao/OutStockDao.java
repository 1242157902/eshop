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
import com.eshop.entrity.OutStock;
import com.eshop.utils.ConnectionUtils;
import com.eshop.utils.NcutJdbcUtils;

/**
 * 
 * <p>Title：        OutStockDao<p>
 * <p>Description:  出库商品的Dao<p>
 * @date:           2015年11月4日下午4:30:14
 * @author:         ysl
 * @version         1.0
 */
public class OutStockDao {
	
	
 
	 public void InsertOutStock(OutStock outStock,String company) {
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			conn=ConnectionUtils.selectConn(company);
			String sql="insert  into tab_outstock(Out_id,Order_id,Good_id,Good_name,quantity,outtime,Manager_name,status,backfill_id) value(?,?,?,?,?,?,?,?,?)";
			
			statement=conn.prepareStatement(sql);
			statement.setString(1,outStock.getOut_id());
			statement.setString(2, outStock.getOrder_id());
			statement.setString(3,outStock.getGood_id());
			statement.setString(4,outStock.getGood_name());
			statement.setInt(5, outStock.getQuantity());
			statement.setString(6,outStock.getOuttime());
			statement.setString(7,outStock.getManager_name());
			statement.setInt(8,  outStock.getStatus());
			statement.setString(9,outStock.getBackfill_id());
			statement.executeUpdate();
		 
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			NcutJdbcUtils.release(conn, statement, rs);
		}
	}
	
 
	 public int getTotal(Map<String ,Object> m) throws Exception {
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		int count = 0;
		try {
			String company=(String) m.get("company");
			conn=ConnectionUtils.selectConn(company);
			
			String sql="select count(*) from tab_outStock where status = 0 ";
			
			Set<Entry<String, Object>> set = m.entrySet();
			Iterator io = set.iterator();
			while (io.hasNext()) {
				Map.Entry<String, Object> me = (Map.Entry<String, Object>) io.next();
				if("Out_id".equals(me.getKey())&&me.getValue()!=null && !"".equals(me.getValue())){
					sql += " and " + me.getKey() + " = '"+ me.getValue()  +"' " ;
				}
				if("Order_id".equals(me.getKey())&&me.getValue()!=null && !"".equals(me.getValue())){
					sql += " and " + me.getKey() + " = '"+ me.getValue()  +"' " ;
				}
				 
				if("Good_name".equals(me.getKey()) &&me.getValue()!=null&& !"".equals(me.getValue())){
					sql += " and " + me.getKey() + " like '%"+ me.getValue()  +"%'" ;
				}
				if("Manager_name".equals(me.getKey()) &&me.getValue()!=null&& !"".equals(me.getValue())){
					sql += " and " + me.getKey() + " like '%"+ me.getValue()  +"%'" ;
				}
				if("outtime".equals(me.getKey()) && !"".equals(me.getValue())){
					sql += " and " + me.getKey() + " >= '" + me.getValue() +"'";
				}
				if("endtime".equals(me.getKey()) && !"".equals(me.getValue())){
					sql += " and " + " outtime " + " <= '" + me.getValue() +"'";
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
	
 
	 public List<OutStock> findOutStockByPagination(int currentPage, int pageSize  ,Map<String ,Object> m) {

		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		List<OutStock> outStockList = null;
		try {
			
			String company=(String) m.get("company");
			conn=ConnectionUtils.selectConn(company);
			
			outStockList = new  ArrayList<OutStock>();
			String sql="select * from tab_outStock where status = 0 ";
			
			Set<Entry<String, Object>> set = m.entrySet();
			Iterator io = set.iterator();
			while (io.hasNext()) {
				Map.Entry<String, Object> me = (Map.Entry<String, Object>) io.next();
				if("Out_id".equals(me.getKey())&&me.getValue()!=null && !"".equals(me.getValue())){
					sql += " and " + me.getKey() + " = '"+ me.getValue()  +"' " ;
				}
				if("Order_id".equals(me.getKey())&&me.getValue()!=null && !"".equals(me.getValue())){
					sql += " and " + me.getKey() + " = '"+ me.getValue()  +"' " ;
				}
				if("backfill_id".equals(me.getKey())&&me.getValue()!=null && !"".equals(me.getValue())){
					sql += " and " + me.getKey() + " = '"+ me.getValue()  +"' " ;
				}
				 
				if("Good_name".equals(me.getKey()) &&me.getValue()!=null&& !"".equals(me.getValue())){
					sql += " and " + me.getKey() + " like '%"+ me.getValue()  +"%'" ;
				}
				if("Manager_name".equals(me.getKey()) &&me.getValue()!=null&& !"".equals(me.getValue())){
					sql += " and " + me.getKey() + " like '%"+ me.getValue()  +"%'" ;
				}
			 
				if("outtime".equals(me.getKey()) && !"".equals(me.getValue())){
					sql += " and " + me.getKey() + " >= '" + me.getValue() +"'";
				}
				if("endtime".equals(me.getKey()) && !"".equals(me.getValue())){
					sql += " and " + " outtime " + " <= '" + me.getValue() +"'";
				}
		/*		if("sort".equals(me.getKey()) &&me.getValue()!=null&& !"".equals(me.getValue())){
					sql += " order by " + me.getValue() ;
				}
				if("order".equals(me.getKey()) &&me.getValue()!=null&& !"".equals(me.getValue())){
					sql += " " + me.getValue();
				}	*/		
			}
			sql = sql +" order by outtime Desc "+" limit " + (currentPage-1)*pageSize +" , "  + pageSize ;
			
			
			statement=conn.prepareStatement(sql);
			rs=statement.executeQuery();
			while (rs.next()) {
				OutStock  outStock = new OutStock();
				outStock.setOut_id(rs.getString("Out_id"));
				outStock.setOrder_id(rs.getString("Order_id"));
				outStock.setGood_id(rs.getString("Good_id"));
				outStock.setGood_name(rs.getString("Good_name"));
				outStock.setQuantity(rs.getInt("quantity"));
				outStock.setStatus(rs.getInt("status"));
				outStock.setOuttime(rs.getString("outtime"));
				outStock.setManager_name(rs.getString("Manager_name"));
				outStock.setBackfill_id(rs.getString("backfill_id"));
				outStockList.add(outStock);
			}
			return outStockList;
	} catch (Exception e) {
		e.printStackTrace();
	}finally{
		NcutJdbcUtils.release(conn, statement, rs);
	}
	return null;
	}
 
	public OutStock findOutStockById(String out_id,String company) {
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			
			conn=ConnectionUtils.selectConn(company);
			String sql="select * from tab_outstock where status = 0 and  Out_id =? ";
			
			statement=conn.prepareStatement(sql);
			statement.setString(1, out_id);
			rs=statement.executeQuery();
			while (rs.next()) {
				OutStock  outStock = new OutStock();
				outStock.setOut_id(rs.getString("Out_id"));
				outStock.setOrder_id(rs.getString("Order_id"));
				outStock.setGood_id(rs.getString("Good_id"));
				outStock.setGood_name(rs.getString("Good_name"));
				outStock.setQuantity(rs.getInt("quantity"));
				outStock.setStatus(rs.getInt("status"));
				outStock.setOuttime(rs.getString("outtime"));
				outStock.setManager_name(rs.getString("Manager_name"));
				outStock.setBackfill_id(rs.getString("backfill_id"));
				return outStock;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			NcutJdbcUtils.release(conn, statement, rs);
		}
		return null;
	}

	public void updateOutStock(OutStock outStock,String company) {
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			
			conn=ConnectionUtils.selectConn(company);
			String sql="update tab_outstock set Good_id =?, Good_name = ? ,quantity = ?,status = ?,outtime = ? ,Manager_name = ?,Order_id = ?,backfill_id =?  where  Out_id  =?  ";
			
			statement=conn.prepareStatement(sql);
			statement.setString(1, outStock.getGood_id());
			statement.setString(2, outStock.getGood_name());
			statement.setInt(3, outStock.getQuantity());
			statement.setInt(4, outStock.getStatus());
			statement.setString(5, outStock.getOuttime());
			statement.setString(6, outStock.getManager_name());
			statement.setString(7, outStock.getOrder_id());
			statement.setString(8, outStock.getBackfill_id());
			statement.setString(9, outStock.getOut_id());
			statement.executeUpdate();
		 
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			NcutJdbcUtils.release(conn, statement, rs);
		}
	}
	 
	

}