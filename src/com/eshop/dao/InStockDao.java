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
public class InStockDao {
	
 
	/**
	 * 
	 * @return:       int 
	 * @param m
	 * @return
	 * @throws Exception
	 * <p>Description: 分页显示条件查询的总记录数<p>
	 * @date:          2015年11月3日上午10:50:21
	 * @author         ysl
	 */
	public int getTotal(Map<String, Object> m) throws Exception {
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		int count = 0;
		try {
			String company = (String) m.get("company");
			conn=ConnectionUtils.selectConn(company);
			
			String sql="select count(*) from tab_instock where 1 = 1 ";
			
			Set<Entry<String, Object>> set = m.entrySet();
			Iterator io = set.iterator();
			while (io.hasNext()) {
				Map.Entry<String, Object> me = (Map.Entry<String, Object>) io.next();
				if("instock_id".equals(me.getKey())&&me.getValue()!=null && !"".equals(me.getValue())){
					sql += " and " + me.getKey() + " = '"+ me.getValue()  +"' " ;
				}
				if("status".equals(me.getKey())&&me.getValue()!=null && !"".equals(me.getValue())){
					sql += " and " + me.getKey() + " = '"+ me.getValue()  +"' " ;
				}
			/*	if("Good_id".equals(me.getKey())&&me.getValue()!=null && !"".equals(me.getValue())){
					sql += " and " + me.getKey() + " = '"+ me.getValue()  +"' " ;
				}
				if("Good_name".equals(me.getKey()) &&me.getValue()!=null&& !"".equals(me.getValue())){
					sql += " and " + me.getKey() + " like '%"+ me.getValue()  +"%'" ;
				}*/
				if("Manager_name".equals(me.getKey()) &&me.getValue()!=null&& !"".equals(me.getValue())){
					sql += " and " + me.getKey() + " like '%"+ me.getValue()  +"%'" ;
				}
				if("instocktime".equals(me.getKey()) && !"".equals(me.getValue())){
					sql += " and " + me.getKey() + " >= '" + me.getValue() +"'";
				}
				if("endtime".equals(me.getKey()) && !"".equals(me.getValue())){
					sql += " and " + " instocktime " + " <= '" + me.getValue() +"'";
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
	
	/**
	 * 
	 * @return:       List<InStock> 
	 * @param currentPage
	 * @param pageSize 
	 * @param m
	 * @return
	 * <p>Description: 分页显示条件查询的入库信息<p>
	 * @date:          2015年11月3日上午10:48:18
	 * @author         ysl
	 */
	public List<InStock> findInStockByPagination(int currentPage, int pageSize,
			Map<String, Object> m)
	{

		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		List<InStock> inStockList = null;
		try {
			String company = (String) m.get("company");
			conn=ConnectionUtils.selectConn(company);
			inStockList = new  ArrayList<InStock>();
			String sql="select * from tab_instock where 1 = 1 ";
			
			Set<Entry<String, Object>> set = m.entrySet();
			Iterator io = set.iterator();
			while (io.hasNext()) {
				Map.Entry<String, Object> me = (Map.Entry<String, Object>) io.next();
				if("instock_id".equals(me.getKey())&&me.getValue()!=null && !"".equals(me.getValue())){
					sql += " and " + me.getKey() + " = '"+ me.getValue()  +"' " ;
				}
				if("status".equals(me.getKey())&&me.getValue()!=null && !"".equals(me.getValue())){
					sql += " and " + me.getKey() + " = '"+ me.getValue()  +"' " ;
				}
			/*	if("Good_id".equals(me.getKey())&&me.getValue()!=null && !"".equals(me.getValue())){
					sql += " and " + me.getKey() + " = '"+ me.getValue()  +"' " ;
				}
				if("Good_name".equals(me.getKey()) &&me.getValue()!=null&& !"".equals(me.getValue())){
					sql += " and " + me.getKey() + " like '%"+ me.getValue()  +"%'" ;
				}*/
				if("Manager_name".equals(me.getKey()) &&me.getValue()!=null&& !"".equals(me.getValue())){
					sql += " and " + me.getKey() + " like '%"+ me.getValue()  +"%'" ;
				}
				if("instocktime".equals(me.getKey()) && !"".equals(me.getValue())){
					sql += " and " + me.getKey() + " >= '" + me.getValue() +"'";
				}
				if("endtime".equals(me.getKey()) && !"".equals(me.getValue())){
					sql += " and " + " instocktime " + " <= '" + me.getValue() +"'";
				}
			/*	if("sort".equals(me.getKey()) &&me.getValue()!=null&& !"".equals(me.getValue())){
					sql += " order by " + me.getValue() ;
				}
				if("order".equals(me.getKey()) &&me.getValue()!=null&& !"".equals(me.getValue())){
					sql += " " + me.getValue();
				}	*/		
			}
			sql = sql +" order by  instocktime DESC"+" limit " + (currentPage-1)*pageSize +" , "  + pageSize  ;
			 
			
			statement=conn.prepareStatement(sql);
			rs=statement.executeQuery();
			while (rs.next()) {
				InStock  inStock = new InStock();
				inStock.setInstock_id(rs.getString("instock_id"));
				inStock.setQuantity(rs.getInt("quantity"));
				inStock.setStatus(rs.getInt("status"));
				inStock.setInstocktime(rs.getString("instocktime"));
				inStock.setManager_name(rs.getString("Manager_name"));
				inStock.setInstock_total(rs.getDouble("instock_total"));
				inStock.setManager_id(rs.getInt("manager_id"));
				inStockList.add(inStock);
			}
			return inStockList;
	} catch (Exception e) {
		e.printStackTrace();
	}finally{
		NcutJdbcUtils.release(conn, statement, rs);
	}
	return null;
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
	public InStock findInStockById(String  inStock_id,String company) throws Exception {
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			
			conn=ConnectionUtils.selectConn(company);
			String sql="select * from tab_instock where instock_id =? ";

			statement=conn.prepareStatement(sql);
			statement.setString(1, inStock_id);
			rs=statement.executeQuery();
			while (rs.next()) {
				
				InStock  inStock = new InStock();
				inStock.setInstock_id(rs.getString("instock_id"));
				inStock.setManager_name(rs.getString("Manager_name"));
				inStock.setManager_id(rs.getInt("manager_id"));
				inStock.setQuantity(rs.getInt("quantity"));
				inStock.setInstocktime(rs.getString("instocktime"));
				inStock.setStatus(rs.getInt("status"));
				inStock.setInstock_total(rs.getDouble("instock_total"));
				return inStock;
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
	 * @param inStock
	 * @param company
	 * @throws Exception
	 * <p>Description:更新Instock的状态 <p>
	 * @date:          2015年11月11日下午8:55:08
	 * @author         ysl
	 */
	public void updateInStock(InStock inStock,String company)throws Exception {
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			
			conn=ConnectionUtils.selectConn(company);
			String sql="update tab_instock set instocktime = ? ,quantity = ?,instock_total = ?,Manager_name = ? ,status = ?,manager_id = ?  where  instock_id =?  ";
			
			statement=conn.prepareStatement(sql);
			statement.setString(1, inStock.getInstocktime());
			statement.setInt(2, inStock.getQuantity());
			statement.setDouble(3, inStock.getInstock_total());
			statement.setString(4, inStock.getManager_name());
			statement.setInt(5, inStock.getStatus());
			statement.setInt(6, inStock.getManager_id());
			statement.setString(7, inStock.getInstock_id());
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
	 * <p>Description:插入到入库中 <p>
	 * @date:          2015年11月4日上午9:56:45
	 * @author         ysl
	 */
	public void insertPurchase(InStock instock,String company) {
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			
			conn=ConnectionUtils.selectConn(company);
			String sql="insert  into tab_instock(instock_id,quantity,instocktime,instock_total,Manager_name,status,manager_id) value(?,?,?,?,?,?,?)";
			
			statement=conn.prepareStatement(sql);
			statement.setString(1,instock.getInstock_id());
			statement.setInt(2,instock.getQuantity());
			statement.setString(3,instock.getInstocktime());
			statement.setDouble(4, instock.getInstock_total());
			statement.setString(5,instock.getManager_name());
			statement.setInt(6,instock.getStatus());
			statement.setInt(7, instock.getManager_id());
			statement.executeUpdate();
		 
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			NcutJdbcUtils.release(conn, statement, rs);
		}
	}
}
