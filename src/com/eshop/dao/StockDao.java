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

import com.eshop.entrity.Stock;
import com.eshop.utils.ConnectionUtils;
import com.eshop.utils.NcutJdbcUtils;


/**
 * 
 * <p>Title：        StockDao<p>
 * <p>Description: 用于处理库存的Dao <p>
 * @date:           2015年10月29日下午10:00:30
 * @author:         ysl
 * @version         1.0
 */
public class StockDao {
	
	/**
	 * 
	 * @return:       List<Stock> 
	 * @param currentPage
	 * @param pageSize
	 * @param m
	 * @return
	 * <p>Description: 分页查询库存信息<p>
	 * @date:          2015年11月1日下午2:34:44
	 * @author         ysl
	 */
	public List<Stock> findStockByPagination(int currentPage, int pageSize  ,Map<String ,Object> m)
	{
		
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		List<Stock> stockList = null;
		try {
			
			String company= (String) m.get("company");
			//conn=NcutJdbcUtils.getConnection();
			conn=ConnectionUtils.selectConn(company);
			stockList = new  ArrayList<Stock>();
			String sql="select * from tab_stock where 1=1 ";
			
			Set<Entry<String, Object>> set = m.entrySet();
			Iterator io = set.iterator();
			while (io.hasNext()) {
				Map.Entry<String, Object> me = (Map.Entry<String, Object>) io.next();
				if("Good_id".equals(me.getKey())&&me.getValue()!=null && !"".equals(me.getValue())){
					sql += " and " + me.getKey() + " = '"+ me.getValue()  +"' " ;
				}
				if("Good_name".equals(me.getKey()) &&me.getValue()!=null&& !"".equals(me.getValue())){
					sql += " and " + me.getKey() + " like '%"+ me.getValue()  +"%'" ;
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
				
				Stock  stock = new Stock();
				stock.setGood_id(rs.getString("Good_id"));
				stock.setGood_name(rs.getString("Good_name"));
				stock.setQuantity(rs.getInt("quantity"));
				stock.setVquantity(rs.getInt("vquantity"));
				stock.setRemark(rs.getString("remark"));
				stockList.add(stock);
			}
			return stockList;
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
			
			String sql="select count(*) from tab_stock where 1=1 ";
			
			Set<Entry<String, Object>> set = m.entrySet();
			Iterator io = set.iterator();
			while (io.hasNext()) {
				Map.Entry<String, Object> me = (Map.Entry<String, Object>) io.next();
				if("Good_id".equals(me.getKey())&&me.getValue()!=null && !"".equals(me.getValue())){
					sql += " and " + me.getKey() + " = '"+ me.getValue()  +"'" ;
				}
				if("Good_name".equals(me.getKey()) &&me.getValue()!=null&& !"".equals(me.getValue())){
					sql += " and " + me.getKey() + " like '%"+ me.getValue()  +"%'" ;
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
	/**
	 * 
	 * @return:       Stock 
	 * @param good_id
	 * @return
	 * <p>Description: 通过商品id查询该商品的库存<p>
	 * @date:          2015年11月1日下午9:41:08
	 * @author         ysl
	 */
	public Stock findStockById(String good_id,String company) {
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			
			conn=ConnectionUtils.selectConn(company);
			String sql="select * from tab_stock where Good_id =? ";
			
			statement=conn.prepareStatement(sql);
			statement.setString(1, good_id);
			rs=statement.executeQuery();
			while (rs.next()) {
				Stock  stock = new Stock();
				stock.setGood_id(rs.getString("Good_id"));
				stock.setGood_name(rs.getString("Good_name"));
				stock.setQuantity(rs.getInt("quantity"));
				stock.setVquantity(rs.getInt("vquantity"));
				stock.setRemark(rs.getString("remark"));
				return stock;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			NcutJdbcUtils.release(conn, statement, rs);
		}
		return null;
	}
	
	/**
	 * 
	 * @return:       void 
	 * @param stock
	 * <p>Description:更新商品库存信息 <p>
	 * @date:          2015年11月1日下午9:56:54
	 * @author         ysl
	 */
	public void updateStock(Stock stock,String company) {
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			
			conn=ConnectionUtils.selectConn(company);
			String sql="update tab_stock set Good_name = ? ,quantity = ?,vquantity = ?,remark = ?  where  Good_id=?  ";
			
			statement=conn.prepareStatement(sql);
			statement.setString(1, stock.getGood_name());
			statement.setInt(2, stock.getQuantity());
			statement.setInt(3, stock.getVquantity());
			statement.setString(4, stock.getRemark());
			statement.setString(5, stock.getGood_id());
			statement.executeUpdate();
		 
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			NcutJdbcUtils.release(conn, statement, rs);
		}
	}

	/**
	 * 
	 * @return:       List<Stock> 
	 * @return
	 * <p>Description: 查询所有库存中的商品<p>
	 * @date:          2015年11月3日下午5:17:29
	 * @author         ysl
	 */
	public List<Stock> findAllStock(String company) {
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		List<Stock> stockList = null;
		try {
			
			conn=ConnectionUtils.selectConn(company);
			stockList = new  ArrayList<Stock>();
			String sql="select * from tab_stock where 1=1 ";
			statement=conn.prepareStatement(sql);
			rs=statement.executeQuery();
			while (rs.next()) {
				Stock  stock = new Stock();
				stock.setGood_id(rs.getString("Good_id"));
				stock.setGood_name(rs.getString("Good_name"));
				stock.setQuantity(rs.getInt("quantity"));
				stock.setVquantity(rs.getInt("vquantity"));
				stock.setRemark(rs.getString("remark"));
				stockList.add(stock);
			}
			return stockList;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			NcutJdbcUtils.release(conn, statement, rs);
		}
		return null;
	}
	
	/**
	 * 
	 * @return:       void 
	 * @param stock
	 * <p>Description: 插入到库<p>
	 * @date:          2015年11月3日下午6:11:12
	 * @author         ysl
	 */
	public void insertStock(Stock stock,String company) 
	{
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			
			conn=ConnectionUtils.selectConn(company);
			String sql="insert  into tab_stock(Good_id,Good_name,quantity,vquantity,remark) value(?,?,?,?,?)";
			
			statement=conn.prepareStatement(sql);
			statement.setString(1,stock.getGood_id());
			statement.setString(2,stock.getGood_name());
			statement.setInt(3, stock.getQuantity());
			statement.setInt(4, stock.getVquantity());
			statement.setString(5, stock.getRemark());
			statement.executeUpdate();
		 
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			NcutJdbcUtils.release(conn, statement, rs);
		}
		
	}
}
