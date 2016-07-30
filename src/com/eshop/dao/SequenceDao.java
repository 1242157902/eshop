package com.eshop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.eshop.entrity.InStock;
import com.eshop.entrity.Sequence;
import com.eshop.utils.ConnectionUtils;
import com.eshop.utils.NcutJdbcUtils;

public class SequenceDao {

	public Sequence getSequenceByType(String type,String company) throws Exception
	{
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			
			conn=ConnectionUtils.selectConn(company);
			String sql="select * from tab_sequence where type =? ";

			statement=conn.prepareStatement(sql);
			statement.setString(1, type);
			rs=statement.executeQuery();
			while (rs.next()) {
				Sequence sq = new Sequence();
				 sq.setSequenceId(rs.getInt("sequence_id"));
				 sq.setDate(rs.getDate("time"));
				 sq.setType(rs.getString("type"));
				 sq.setNextId(rs.getInt("nextId"));
				return sq;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			NcutJdbcUtils.release(conn, statement, rs);
		}
		return null;
	}

	public void updateSequence(Sequence sequence, String company)
			throws Exception 
	{
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			
			conn=ConnectionUtils.selectConn(company);
			String sql="update tab_sequence set time = ? ,nextId = ?  where  type =?  ";
			
			statement=conn.prepareStatement(sql);
			statement.setDate(1, sequence.getDate());
			statement.setInt(2, sequence.getNextId());
			statement.setString(3, sequence.getType());
			statement.executeUpdate();
		 
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			NcutJdbcUtils.release(conn, statement, rs);
		}
	}
}
