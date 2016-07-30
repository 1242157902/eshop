package com.eshop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.eshop.entrity.GoodPics;
import com.eshop.entrity.Goods;
import com.eshop.utils.ConnectionUtils;
import com.eshop.utils.NcutJdbcUtils;

public class GoodPicDao {
	
	
	public void addGoodPic(GoodPics goodpic, String company) {
			Connection conn = null;
			PreparedStatement statement = null;
			ResultSet rs = null;
			
			try {
				
				conn=ConnectionUtils.selectConn(company);
				String sql="insert into tab_good_pics(Pic_url,good_id,Good_pic,isdel,Pic_order) values(?,?,?,?,?) ";
				statement=conn.prepareStatement(sql);
				statement.setString(1,goodpic.getPic_url());
				statement.setString(2,goodpic.getGood_id());
				statement.setString(3,goodpic.getGood_pic());
				statement.setInt(4, 1);
				statement.setInt(5,goodpic.getOrder());
				statement.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				NcutJdbcUtils.release(conn, statement, rs);
			}
		}

	public String findGoodByPic(String storeFile, String company) {
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		String good_id=null;
		try {
			
			conn=ConnectionUtils.selectConn(company);
			String sql="select * from tab_good_pics where Pic_url =?";
			
			statement=conn.prepareStatement(sql);
			statement.setString(1, storeFile);
			rs=statement.executeQuery();
			while (rs.next()) {
				good_id=rs.getString("good_id");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			NcutJdbcUtils.release(conn, statement, rs);
		}
		return good_id;
	}
	
	public List<GoodPics> queryAllPic(String good_id, String company) {
    	Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		List<GoodPics> goodPicList = null;
		try {
			
			conn=ConnectionUtils.selectConn(company);
			String sql="select * from tab_good_pics where good_id =? ";
			goodPicList=new ArrayList<GoodPics>();
			statement=conn.prepareStatement(sql);
			statement.setString(1, good_id);
			rs=statement.executeQuery();
			while (rs.next()) {
				String pic_url=rs.getString("Pic_url");
				String small=pic_url.substring(8, 13);
				if("small".equals(small)){
					 GoodPics goodPics=new GoodPics();
					 goodPics.setPic_id(rs.getInt("id"));
					 goodPics.setPic_url(rs.getString("Pic_url"));
					 goodPics.setGood_id(rs.getString("good_id"));
					 goodPics.setGood_pic(rs.getString("Good_pic"));
					 goodPics.setIsdel(rs.getInt("isdel"));
					 goodPics.setOrder(rs.getInt("Pic_order")); 
					 goodPicList.add(goodPics);
				}
				
			}
			return goodPicList;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			NcutJdbcUtils.release(conn, statement, rs);
		}
		return null;
    }
	
	public GoodPics queryOne(String good_id,String company){
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		GoodPics goodPic= null;
		
		try {
			
			conn=ConnectionUtils.selectConn(company);
			String sql="select * from tab_good_pics where good_id =? ";
			
			statement=conn.prepareStatement(sql);
			statement.setString(1, good_id);
			rs=statement.executeQuery();
			while (rs.next()) {
				String pic_url=rs.getString("Pic_url");
				String middle=pic_url.substring(8, 14);
				if("middle".equals(middle)){
					goodPic=new GoodPics();
					goodPic.setPic_id(rs.getInt("id"));
					goodPic.setPic_url(rs.getString("Pic_url"));
					goodPic.setGood_id(rs.getString("good_id"));
					goodPic.setGood_pic(rs.getString("Good_pic"));
					goodPic.setIsdel(rs.getInt("isdel"));
					goodPic.setOrder(rs.getInt("Pic_order")); 
					break;
					 
				}
				
			}
			return goodPic;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			NcutJdbcUtils.release(conn, statement, rs);
		}
		return null;
	}

}
