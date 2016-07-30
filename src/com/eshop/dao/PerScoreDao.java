package com.eshop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import com.eshop.entrity.GoodsRecord;
import com.eshop.entrity.PerScore;
import com.eshop.utils.ConnectionUtils;
import com.eshop.utils.DateUtil;
import com.eshop.utils.NcutJdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.eshop.entrity.PerScore;
import com.eshop.entrity.Stock;
import com.eshop.utils.ConnectionUtils;
import com.eshop.utils.NcutJdbcUtils;

/**
 * @author  JYM
 * 2015-12-3 下午9:14:21
 */
public class PerScoreDao {

	//分页查询积分比例信息
	public List<PerScore> findperScoreByPagination(Integer currentPage, Integer pageSize,
			Map<String, Object> m, String company) {
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		List<PerScore> perList = null;
		try {
			conn=ConnectionUtils.selectConn(company);
			perList=new ArrayList<PerScore>();
			String sql="select P.*,M.name from tab_perscore P inner join tab_manageruser M on P.admin_id=M.id  where 1=1 order by P.id desc";
			Set<Entry<String, Object>> set = m.entrySet();
			Iterator io = set.iterator();
			while (io.hasNext()) {
				Map.Entry<String, Object> me = (Map.Entry<String, Object>) io.next();
				if("createtime".equals(me.getKey())&&me.getValue()!=null && !"".equals(me.getValue())){
					sql += " and " + me.getKey() + " = '"+ me.getValue()  +"' " ;
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
				PerScore per=new PerScore();
				per.setId(rs.getInt("id"));
				per.setPercentage(rs.getFloat("percentage"));
				per.setStatus(rs.getInt("status"));
				per.setAdminname(rs.getString("name"));
				//per.setCreatetime(DateUtil.timestampToDate(rs.getTimestamp("createtime")));
				perList.add(per);
			}
		    }catch (Exception e) {
				e.printStackTrace();
			}finally{
				NcutJdbcUtils.release(conn, statement, rs);
			}
			return perList;
	}
	//查询总数
	public Integer getTotal(Map<String, Object> m, String company) {
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		int count = 0;
		try {
			conn=ConnectionUtils.selectConn(company);
			String sql="select count(*) from tab_perscore where 1=1 ";
			
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
	public void addPerScore(PerScore perscore, String company, int userID) {
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try{
			conn=ConnectionUtils.selectConn(company);
			String sql="insert into tab_perscore(percentage,admin_id,status,createtime) values(?,?,?,?)";
			
			statement=conn.prepareStatement(sql);
			statement.setFloat(1,perscore.getPercentage());
			statement.setInt(2,userID);
			statement.setInt(3, perscore.getStatus());
			statement.setString(4, DateUtil.formatDate1(perscore.getCreatetime()));
			statement.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally
		{
			NcutJdbcUtils.release(conn, statement, rs);
		}
	}
	
	//更新比例记录信息的状态为0；
	public void updatePerScore(String company) {
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try{
			conn=ConnectionUtils.selectConn(company);
			String sql="update tab_perscore set status=?";
			
			statement=conn.prepareStatement(sql);
			statement.setInt(1,0);
			statement.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally
		{
			NcutJdbcUtils.release(conn, statement, rs);
		}
	}

	public PerScore getImportPercent(String status, String company) {
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			
			conn=ConnectionUtils.selectConn(company);
			String sql="select * from tab_perscore where status  =? ";
			
			statement=conn.prepareStatement(sql);
			statement.setString(1, status);
			rs=statement.executeQuery();
			while (rs.next()) {
				PerScore  perScore = new PerScore();
				perScore.setId(rs.getInt("id"));
				 perScore.setPercentage(rs.getFloat("percentage"));
				return perScore;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			NcutJdbcUtils.release(conn, statement, rs);
		}
		return null;
	}

	
}
