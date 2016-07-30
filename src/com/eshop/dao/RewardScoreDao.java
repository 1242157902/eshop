package com.eshop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.eshop.entrity.RewardScore;
import com.eshop.utils.ConnectionUtils;
import com.eshop.utils.NcutJdbcUtils;

/**
 * @author  JYM
 * 2015-12-2 下午6:00:54
 */
public class RewardScoreDao {

	/**
	 * @param nickname
	 * @param company
	 * @return
	 */
	public RewardScore getScoreBynickname(String nickname, String company) {
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			conn=ConnectionUtils.selectConn(company);
			String sql="select * from tab_rewardscore where nick_name=?  and status=1 ";
			statement=conn.prepareStatement(sql);
			statement.setString(1, nickname);
			rs=statement.executeQuery();
			while (rs.next()) {
				RewardScore rewardScore=new RewardScore();
				rewardScore.setId(rs.getInt("id"));
				rewardScore.setDevice_number(rs.getString("device_number"));
				rewardScore.setNick_name(rs.getString("nick_name"));
				rewardScore.setStatus(rs.getInt("status"));
				rewardScore.setReward_score(rs.getInt("reward_score"));
				rewardScore.setBefore_rscore(rs.getInt("before_score"));
				rewardScore.setDepartment(rs.getString("department"));
				rewardScore.setImport_time(rs.getString("import_time"));
				rewardScore.setName(rs.getString("name"));
				return rewardScore;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			NcutJdbcUtils.release(conn, statement, rs);
		}
		return null;
	}
	public RewardScore getRewardScoreBynickname(String nickname, String company) {
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			conn=ConnectionUtils.selectConn(company);
			String sql="select * from tab_rewardscore where nick_name=?  ";
			statement=conn.prepareStatement(sql);
			statement.setString(1, nickname);
			rs=statement.executeQuery();
			while (rs.next()) {
				RewardScore rewardScore=new RewardScore();
				rewardScore.setId(rs.getInt("id"));
				rewardScore.setDevice_number(rs.getString("device_number"));
				rewardScore.setNick_name(rs.getString("nick_name"));
				rewardScore.setStatus(rs.getInt("status"));
				rewardScore.setReward_score(rs.getInt("reward_score"));
				rewardScore.setBefore_rscore(rs.getInt("before_score"));
				rewardScore.setScore(rs.getInt("score"));
				rewardScore.setDepartment(rs.getString("department"));
				rewardScore.setImport_time(rs.getString("import_time"));
				rewardScore.setLast_time(rs.getString("last_time"));
				rewardScore.setName(rs.getString("name"));
				return rewardScore;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			NcutJdbcUtils.release(conn, statement, rs);
		}
		return null;
	}

	/**
	 * @param rewardScore
	 * @param company 
	 */
	public void update(RewardScore rewardScore, String company) {
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			conn=ConnectionUtils.selectConn(company);
			String sql="update tab_rewardscore set nick_name=?,device_number=?,status=?,reward_score=?,name=?,department=?,before_score =?,import_time =?,score = ?,last_time=? where id=?";
			statement=conn.prepareStatement(sql);
			statement.setString(1, rewardScore.getNick_name());
			statement.setString(2, rewardScore.getDevice_number());
			statement.setInt(3, rewardScore.getStatus());
			statement.setInt(4, rewardScore.getReward_score());
			statement.setString(5, rewardScore.getName());
			statement.setString(6 ,rewardScore.getDepartment());
			statement.setInt(7, rewardScore.getBefore_rscore());
			statement.setString(8, rewardScore.getImport_time());
			statement.setInt(9, rewardScore.getScore());
			statement.setString(10, rewardScore.getLast_time());
			statement.setInt(11, rewardScore.getId());
			statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			NcutJdbcUtils.release(conn, statement, rs);
		}
		
		
	}

	public void insertRewardScore(RewardScore rewardScore, String company) {
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			conn=ConnectionUtils.selectConn(company);
			String sql="insert  into tab_rewardscore(nick_name,reward_score,import_time,score,name,device_number,department) value(?,?,?,?,?,?,?)";
			statement=conn.prepareStatement(sql);
			statement.setString(1, rewardScore.getNick_name());
			statement.setInt(2, rewardScore.getReward_score());
			statement.setString(3, rewardScore.getImport_time());
			 statement.setInt(4, rewardScore.getScore());
			 statement.setString(5, rewardScore.getName());
			 statement.setString(6, rewardScore.getDevice_number());
			 statement.setString(7, rewardScore.getDepartment());
			statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			NcutJdbcUtils.release(conn, statement, rs);
		}
	}
	public List<RewardScore> findStockByPagination(Integer currentPage,
			Integer pageSize,Map<String, Object> m) {
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		List<RewardScore> rewardScoreList = null;
		try {
			
			String company=(String) m.get("company");
			conn=ConnectionUtils.selectConn(company);
			
			rewardScoreList = new  ArrayList<RewardScore>();
			String sql="select * from tab_rewardscore where  status=1 ";
		 
			sql = sql +" order by nick_name ASC "+" limit " + (currentPage-1)*pageSize +" , "  + pageSize ;
			
			
			statement=conn.prepareStatement(sql);
			rs=statement.executeQuery();
			while (rs.next()) {
				RewardScore rewardScore=new RewardScore();
				rewardScore.setId(rs.getInt("id"));
				rewardScore.setDevice_number(rs.getString("device_number"));
				rewardScore.setNick_name(rs.getString("nick_name"));
				rewardScore.setStatus(rs.getInt("status"));
				rewardScore.setReward_score(rs.getInt("reward_score"));
				rewardScore.setBefore_rscore(rs.getInt("before_score"));
				rewardScore.setScore(rs.getInt("score"));
				rewardScore.setDepartment(rs.getString("department"));
				rewardScore.setImport_time(rs.getString("import_time"));
				rewardScore.setName(rs.getString("name"));
				rewardScoreList.add(rewardScore);
			}
			return rewardScoreList;
	} catch (Exception e) {
		e.printStackTrace();
	}finally{
		NcutJdbcUtils.release(conn, statement, rs);
	}
	return null;
	}
	public Integer getTotal(Map<String, Object> m) {
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		int count = 0;
		try {
			String company= (String) m.get("company");
			//conn=NcutJdbcUtils.getConnection();
			conn=ConnectionUtils.selectConn(company);
			
			String sql="select count(*) from   tab_rewardscore  where  status=1 ";
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
	public List<RewardScore> getAllImportScore(String company) throws SQLException {
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		List<RewardScore> rewardScoreList = null;
	 
		conn=ConnectionUtils.selectConn(company);
		
		rewardScoreList = new  ArrayList<RewardScore>();
		String sql="select * from tab_rewardscore where  status=1 and score !=0 and before_score!=0";
	 
			statement=conn.prepareStatement(sql);
			rs=statement.executeQuery();
			while (rs.next()) {
				RewardScore rewardScore=new RewardScore();
				rewardScore.setId(rs.getInt("id"));
				rewardScore.setDevice_number(rs.getString("device_number"));
				rewardScore.setNick_name(rs.getString("nick_name"));
				rewardScore.setStatus(rs.getInt("status"));
				rewardScore.setReward_score(rs.getInt("reward_score"));
				rewardScore.setBefore_rscore(rs.getInt("before_score"));
				rewardScore.setScore(rs.getInt("score"));
				rewardScore.setDepartment(rs.getString("department"));
				rewardScore.setImport_time(rs.getString("import_time"));
				rewardScore.setLast_time(rs.getString("last_time"));
				rewardScore.setName(rs.getString("name"));
				rewardScoreList.add(rewardScore);
			}
		return rewardScoreList;
	}

}
