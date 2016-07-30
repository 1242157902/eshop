package com.eshop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import com.eshop.entrity.InStockDetail;
import com.eshop.entrity.Stock;
import com.eshop.entrity.TempInStock;
import com.eshop.utils.ConnectionUtils;
import com.eshop.utils.NcutJdbcUtils;


 /**
  * 
  * <p>Title：        TmpInStockDetailDao<p>
  * <p>Description:  <p>
  * @date:           2015年11月10日下午3:52:49
  * @author:         ysl
  * @version         1.0
  */
public class TmpInStockDetailDao {
	
	
 /*
	public List<InStockDetail> findPurchaseDetailByPurchaseId(
			String purchase_id,String company) throws Exception {
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		List<InStockDetail> purchaseDetailList = null;
		try {
			
			conn=ConnectionUtils.selectConn(company);
			purchaseDetailList = new ArrayList<InStockDetail>();
			
			String sql="select * from tab_purchase_detail where purchase_id =? ";

			statement=conn.prepareStatement(sql);
			statement.setString(1, purchase_id);
			rs=statement.executeQuery();
			while (rs.next()) {
				
				InStockDetail  purchaseDetail = new InStockDetail();
				purchaseDetail.setPurchaseitemid(rs.getString("purchaseitemid"));
				purchaseDetail.setPurchase_id(rs.getString("purchase_id"));
				purchaseDetail.setGood_id(rs.getString("Good_id"));
				purchaseDetail.setGood_name(rs.getString("Good_name"));
				purchaseDetail.setGood_price(rs.getDouble("Good_price"));
				purchaseDetail.setGood_quantity(rs.getInt("Good_quantity"));
				purchaseDetail.setSubtotal(rs.getDouble("subtotal"));
				purchaseDetailList.add(purchaseDetail);
			}
			return purchaseDetailList;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			NcutJdbcUtils.release(conn, statement, rs);
		}
		return null;
	}*/

	/**
	 * 
	 * @return:       void 
	 * @param purchaseDetail
	 * <p>Description: 插入到采购详细表<p>
	 * @date:          2015年11月4日上午9:39:40
	 * @author         ysl
	 */
	public void insertTempInStock(TempInStock tempInStock,String company){
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			
			conn=ConnectionUtils.selectConn(company);
			String sql="insert  into tab_tmp_instock(tmp_in_id,Good_id,quantity,Good_price,manager_id,subtotal,manager_name) value(?,?,?,?,?,?,?)";
			
			statement=conn.prepareStatement(sql);
			statement.setString(1,tempInStock.getTmp_in_id());
			statement.setString(2,tempInStock.getGood_id());
			statement.setInt(3,tempInStock.getGood_quantity());
			statement.setDouble(4,tempInStock.getGood_price());
			statement.setInt(5, tempInStock.getUser_id());
			statement.setDouble(6, tempInStock.getSubtotal());
			 statement.setString(7, tempInStock.getManager_name());
			statement.executeUpdate();
		 
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			NcutJdbcUtils.release(conn, statement, rs);
		}
	}
	
	public List<TempInStock> findTmpInStockByPagination(int currentPage,
			int pageSize, Map<String, Object> m) {
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		List<TempInStock> tempInStockList = null;
		try {
			
			String company= (String) m.get("company");
			//conn=NcutJdbcUtils.getConnection();
			conn=ConnectionUtils.selectConn(company);
			tempInStockList = new  ArrayList<TempInStock>();
			String sql="select * from tab_tmp_instock where 1=1 ";
			
			Set<Entry<String, Object>> set = m.entrySet();
			Iterator io = set.iterator();
			while (io.hasNext()) {
				Map.Entry<String, Object> me = (Map.Entry<String, Object>) io.next();
				 
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
				
				TempInStock  tempInStock = new TempInStock();
				tempInStock.setGood_id(rs.getString("Good_id"));
				tempInStock.setTmp_in_id(rs.getString("tmp_in_id"));
				tempInStock.setGood_price(rs.getDouble("Good_price"));
				tempInStock.setGood_quantity(rs.getInt("quantity"));
				tempInStock.setManager_name(rs.getString("manager_name"));
				tempInStock.setSubtotal(rs.getDouble("subtotal"));
				tempInStock.setUser_id(rs.getInt("manager_id"));
				tempInStockList.add(tempInStock);
			}
			return tempInStockList;
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
			
			String sql="select count(*) from tab_tmp_instock where 1=1 ";
			
			Set<Entry<String, Object>> set = m.entrySet();
			Iterator io = set.iterator();
			while (io.hasNext()) {
				Map.Entry<String, Object> me = (Map.Entry<String, Object>) io.next();
				 
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
	
	public TempInStock findTmpInStockById(String tmp_inStock_id, String company)
			throws Exception {
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			
			conn=ConnectionUtils.selectConn(company);
			String sql="select * from tab_tmp_instock where tmp_in_id =? ";
			
			statement=conn.prepareStatement(sql);
			statement.setString(1, tmp_inStock_id);
			rs=statement.executeQuery();
			while (rs.next()) {
				TempInStock  tempInStock = new TempInStock();
				tempInStock.setTmp_in_id(rs.getString("tmp_in_id"));
				tempInStock.setGood_id(rs.getString("Good_id"));
				tempInStock.setGood_quantity(rs.getInt("quantity"));
				tempInStock.setGood_price(rs.getDouble("Good_price"));
				tempInStock.setManager_name(rs.getString("manager_name"));
				tempInStock.setUser_id(rs.getInt("manager_id"));
				tempInStock .setSubtotal(rs.getDouble("subtotal"));
				return tempInStock;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			NcutJdbcUtils.release(conn, statement, rs);
		}
		return null;
	}

	public void updateTempInStock(TempInStock tempInStock, String company)
			throws Exception {
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			
			conn=ConnectionUtils.selectConn(company);
			String sql="update tab_tmp_instock set quantity = ? ,subtotal = ?  where  tmp_in_id =?  ";
			
			statement=conn.prepareStatement(sql);
			statement.setInt(1, tempInStock.getGood_quantity());
			statement.setDouble(2, tempInStock.getSubtotal());
			statement.setString(3, tempInStock.getTmp_in_id());
			statement.executeUpdate();
		 
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			NcutJdbcUtils.release(conn, statement, rs);
		}
	}
	public void deleteTmpInStock(String company) throws Exception {
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			
			conn=ConnectionUtils.selectConn(company);
			String sql="Delete from tab_tmp_instock where 1=1  ";
			
			statement=conn.prepareStatement(sql);
			statement.executeUpdate();
		 
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			NcutJdbcUtils.release(conn, statement, rs);
		}
	}
}
