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

import com.eshop.entrity.StockRecord;
import com.eshop.utils.ConnectionUtils;
import com.eshop.utils.NcutJdbcUtils;

public class StockRecordDao {
	
	public List<StockRecord> findStockRecordByPagination(int currentPage,
			int pageSize, Map<String, Object> m) {
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		List<StockRecord> stockRecordList = null;
		try {
			
			String company=(String) m.get("company");
			conn=ConnectionUtils.selectConn(company);
			
			stockRecordList = new  ArrayList<StockRecord>();
			//String sql="select * from tab_stock_record where 1 = 1 ";
			String sql="select  s.*,g.Good_name  from tab_stock_record  as s  inner join tab_goods  as g   on s.Good_id = g.Good_id  where 1 = 1 ";
			
			Set<Entry<String, Object>> set = m.entrySet();
			Iterator io = set.iterator();
			while (io.hasNext()) {
				Map.Entry<String, Object> me = (Map.Entry<String, Object>) io.next();
				if("stock_record_id".equals(me.getKey())&&me.getValue()!=null && !"".equals(me.getValue())){
					sql += " and " + me.getKey() + " = '"+ me.getValue()  +"' " ;
				}
				if("Good_id".equals(me.getKey())&&me.getValue()!=null && !"".equals(me.getValue())){
					sql += " and " + me.getKey() + " = '"+ me.getValue()  +"' " ;
				}
				 
				if("manager_name".equals(me.getKey()) &&me.getValue()!=null&& !"".equals(me.getValue())){
					sql += " and " + me.getKey() + " like '%"+ me.getValue()  +"%'" ;
				}
				if("Good_name".equals(me.getKey()) &&me.getValue()!=null&& !"".equals(me.getValue())){
					sql += " and " + me.getKey() + " like '%"+ me.getValue()  +"%'" ;
				}
			 
				if("createtime".equals(me.getKey()) && !"".equals(me.getValue())){
					sql += " and " + me.getKey() + " >= '" + me.getValue() +"'";
				}
				if("endtime".equals(me.getKey()) && !"".equals(me.getValue())){
					sql += " and " + " createtime " + " <= '" + me.getValue() +"'";
				}
		/*		if("sort".equals(me.getKey()) &&me.getValue()!=null&& !"".equals(me.getValue())){
					sql += " order by " + me.getValue() ;
				}
				if("order".equals(me.getKey()) &&me.getValue()!=null&& !"".equals(me.getValue())){
					sql += " " + me.getValue();
				}		*/	
			}
			sql = sql +"order by createtime  Desc"+" limit  " + (currentPage-1)*pageSize +" , "  + pageSize ;
			
			
			statement=conn.prepareStatement(sql);
			rs=statement.executeQuery();
			while (rs.next()) {
				StockRecord  stockRecord = new StockRecord();
				stockRecord.setStock_record_id(rs.getString("stock_record_id"));
				stockRecord.setGood_id(rs.getString("Good_id"));
				stockRecord.setGood_name(rs.getString("Good_name"));
				stockRecord.setQuantity(rs.getInt("quantity"));
				stockRecord.setUpdate_quantity(rs.getInt("update_quantity"));
				stockRecord.setManager_name(rs.getString("Manager_name"));
				stockRecord.setCreatetime(rs.getString("createtime"));
				stockRecord.setManager_id(rs.getInt("manager_id"));
				stockRecordList.add(stockRecord);
			}
			return stockRecordList;
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
			String company=(String) m.get("company");
			conn=ConnectionUtils.selectConn(company);
			
			String sql="select count(*) from tab_stock_record where 1 = 1 ";
			
			Set<Entry<String, Object>> set = m.entrySet();
			Iterator io = set.iterator();
			while (io.hasNext()) {
				Map.Entry<String, Object> me = (Map.Entry<String, Object>) io.next();
				if("stock_record_id".equals(me.getKey())&&me.getValue()!=null && !"".equals(me.getValue())){
					sql += " and " + me.getKey() + " = '"+ me.getValue()  +"' " ;
				}
				if("Good_id".equals(me.getKey())&&me.getValue()!=null && !"".equals(me.getValue())){
					sql += " and " + me.getKey() + " = '"+ me.getValue()  +"' " ;
				}
				 
				if("manager_name".equals(me.getKey()) &&me.getValue()!=null&& !"".equals(me.getValue())){
					sql += " and " + me.getKey() + " like '%"+ me.getValue()  +"%'" ;
				}
			 
				if("createtime".equals(me.getKey()) && !"".equals(me.getValue())){
					sql += " and " + me.getKey() + " >= '" + me.getValue() +"'";
				}
				if("endtime".equals(me.getKey()) && !"".equals(me.getValue())){
					sql += " and " + " createtime " + " <= '" + me.getValue() +"'";
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

	public void insertStockRecord(StockRecord stockRecord, String company) {

		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			
			conn=ConnectionUtils.selectConn(company);
			String sql="insert  into tab_stock_record(Good_id,quantity,update_quantity,createtime,manager_name,manager_id) value(?,?,?,?,?,?)";
			
			statement=conn.prepareStatement(sql);
			statement.setString(1,stockRecord.getGood_id());
			statement.setInt(2, stockRecord.getQuantity());
			statement.setInt(3, stockRecord.getUpdate_quantity());
			statement.setString(4, stockRecord.getCreatetime());
			statement.setString(5, stockRecord.getManager_name());
			statement.setInt(6, stockRecord.getManager_id());
			statement.executeUpdate();
		 
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			NcutJdbcUtils.release(conn, statement, rs);
		}
		
	}
}
