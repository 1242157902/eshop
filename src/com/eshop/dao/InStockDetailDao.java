package com.eshop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.eshop.entrity.InStockDetail;
import com.eshop.utils.ConnectionUtils;
import com.eshop.utils.NcutJdbcUtils;


/**
 * 
 * <p>Title：        PurchaseDetailDao<p>
 * <p>Description:  用于处理采购单详细的信息<p>
 * @date:           2015年11月2日下午9:44:04
 * @author:         ysl
 * @version         1.0
 */
public class InStockDetailDao {
	
	
	/**
	 * 
	 * @return:       List<InStockDetail> 
	 * @param inStock_id
	 * @param company
	 * @return
	 * @throws Exception
	 * <p>Description: 根据入库单的单号，查询该入库单下的所有入库明细<p>
	 * @date:          2015年11月11日下午5:10:55
	 * @author         ysl
	 */
	public List<InStockDetail> findInStockDetailByInStockId(String inStock_id,String company) throws Exception  {
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		List<InStockDetail> inStockDetailList = null;
		try {
			
			conn=ConnectionUtils.selectConn(company);
			inStockDetailList = new ArrayList<InStockDetail>();
			
			String sql="select * from tab_instock_detail where instock_id =? ";

			statement=conn.prepareStatement(sql);
			statement.setString(1, inStock_id);
			rs=statement.executeQuery();
			while (rs.next()) {
				
				InStockDetail  inStockDetail = new InStockDetail();
				inStockDetail.setInstock_id(rs.getString("instock_id"));
				inStockDetail.setInstockitemid(rs.getString("instockitemid"));
				inStockDetail.setGood_id(rs.getString("Good_id"));
				inStockDetail.setGood_price(rs.getDouble("Good_price"));
				inStockDetail.setGood_quantity(rs.getInt("Good_quantity"));
				inStockDetail.setSubtotal(rs.getDouble("subtotal"));
				inStockDetailList.add(inStockDetail);
			}
			return inStockDetailList;
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
	 * @param inStockDetail
	 * @param company
	 * <p>Description: 插入入库明细<p>
	 * @date:          2015年11月10日下午8:25:27
	 * @author         ysl
	 */
	public void insertInStockDetail(InStockDetail inStockDetail,String company) {
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			
			conn=ConnectionUtils.selectConn(company);
			String sql="insert  into tab_instock_detail(instockitemid,Good_id,Good_quantity,Good_price,instock_id,subtotal) value(?,?,?,?,?,?)";
			
			statement=conn.prepareStatement(sql);
			statement.setString(1,inStockDetail.getInstockitemid());
			statement.setString(2,inStockDetail.getGood_id());
			statement.setInt(3, inStockDetail.getGood_quantity());
			statement.setDouble(4,inStockDetail.getGood_price());
			statement.setString(5,inStockDetail.getInstock_id());
			statement.setDouble(6, inStockDetail.getSubtotal());
			statement.executeUpdate();
		 
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			NcutJdbcUtils.release(conn, statement, rs);
		}
	}
	
}
