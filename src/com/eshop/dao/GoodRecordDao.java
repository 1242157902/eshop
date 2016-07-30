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

import com.eshop.entrity.GoodsRecord;
import com.eshop.utils.ConnectionUtils;
import com.eshop.utils.DateUtil;
import com.eshop.utils.NcutJdbcUtils;

/**
 * 商品记录数据访问Dao层
 * @author Jane
 *
 */
public class GoodRecordDao {
	
	//分页查询商品记录
	public List<GoodsRecord> findgoodRecordByPagination(int currentPage, int pageSize,Map<String ,Object> m, String company) {
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		List<GoodsRecord> recordList=null;
		try {
			conn=ConnectionUtils.selectConn(company);
			recordList=new ArrayList<GoodsRecord>();
			String sql="select R.*,G.Good_name,M.name from tab_good_record R inner join tab_goods G on R.good_id=G.Good_id inner join tab_manageruser M on M.id=R.user_id  where 1=1 ORDER BY G.good_id";
			Set<Entry<String, Object>> set = m.entrySet();
			Iterator io = set.iterator();
			while (io.hasNext()) {
				Map.Entry<String, Object> me = (Map.Entry<String, Object>) io.next();
				if("record_id".equals(me.getKey())&&me.getValue()!=null && !"".equals(me.getValue())){
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
				GoodsRecord record=new GoodsRecord();
				record.setRecord_id(rs.getString("record_id"));
				record.setGood_id(rs.getString("good_id"));
				record.setGoodname(rs.getString("Good_name"));
				record.setGood_price(rs.getDouble("good_price"));
				record.setGood_integration(rs.getDouble("good_integration"));
				record.setUsername(rs.getString("name"));
				record.setCreatime(DateUtil.timeConverString(rs.getInt("create_time")));
				recordList.add(record);
			}
			return recordList;
		    }catch (Exception e) {
				e.printStackTrace();
			}finally{
				NcutJdbcUtils.release(conn, statement, rs);
			}
			return null;
	}

	//得到总数
	public int getTotal(Map<String, Object> m, String company) {
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		int count = 0;
		try {
			conn=ConnectionUtils.selectConn(company);
			String sql="select count(*) from tab_good_record where 1=1 ";
			
			Set<Entry<String, Object>> set = m.entrySet();
			Iterator io = set.iterator();
			while (io.hasNext()) {
				Map.Entry<String, Object> me = (Map.Entry<String, Object>) io.next();
				if("record_id".equals(me.getKey())&&me.getValue()!=null && !"".equals(me.getValue())){
					sql += " and " + me.getKey() + " = '"+ me.getValue()  +"' " ;
				}
				if("good_name".equals(me.getKey()) &&me.getValue()!=null&& !"".equals(me.getValue())){
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

	//添加商品记录
	public void addGoodRecord(GoodsRecord goodRecord, String company, int userID) {
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try{
			conn=ConnectionUtils.selectConn(company);
			String sql="insert into tab_good_record(record_id,good_id,good_price,good_integration,user_id,create_time) values(?,?,?,?,?,?)";
			
			statement=conn.prepareStatement(sql);
			statement.setString(1, goodRecord.getRecord_id());
			statement.setString(2, goodRecord.getGood_id());
			statement.setDouble(3, goodRecord.getGood_price());
			statement.setDouble(4, goodRecord.getGood_integration());
			statement.setInt(5, userID);
			statement.setInt(6, DateUtil.dateConverTime());
			statement.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally
		{
			NcutJdbcUtils.release(conn, statement, rs);
		}
	}
	
}
