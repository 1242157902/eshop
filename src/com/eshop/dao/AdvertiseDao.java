/**
 * 
 */
package com.eshop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import sun.security.timestamp.TSRequest;

import com.eshop.entrity.AdminUser;
import com.eshop.entrity.Advertise;
import com.eshop.entrity.AdvertiseList;
import com.eshop.entrity.TempInStock;
import com.eshop.utils.ConnectionUtils;
import com.eshop.utils.DateUtil;
import com.eshop.utils.NcutJdbcUtils;

/**
 * yy 2015 2015年11月24日 下午9:01:15
 *
 */
public class AdvertiseDao {
	AdminUserDao adminUserDao=new AdminUserDao();
	
	public void addAdvertise(Advertise advertise,String company){
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {			
			conn=ConnectionUtils.selectConn(company);
			String sql="insert into tab_advertise(name,operator,operdate) values(?,?,?)";
			statement=conn.prepareStatement(sql);			
			statement.setString(1,advertise.getName());
			statement.setInt(2,advertise.getOperator());
			statement.setString(3,advertise.getOperdate());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			NcutJdbcUtils.release(conn, statement, rs);
		}
	}
	
	public List<Advertise> queryPic(String company){
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		List<Advertise> advList = null;
		try {
			
			conn=ConnectionUtils.selectConn(company);
			advList= new  ArrayList<Advertise>();
			String sql="select * from tab_advertise order by  STR_TO_DATE(operdate,'%Y-%m-%d %H:%i:%s') desc";
			statement=conn.prepareStatement(sql);
			rs=statement.executeQuery();
			while (rs.next()) {
				Advertise advertise=new Advertise();
				advertise.setId(rs.getInt("id"));
				String y="<IMG width=50px height=50px  src=images/advertise/"+rs.getString("name")+" /> ";
				advertise.setName(y);
				AdminUser adminUser=adminUserDao.findAdminUserById(rs.getString("operator"), company);
				advertise.setOpername(adminUser.getName());
				advertise .setOperdate(rs.getString("operdate"));
				
				advList.add(advertise);
			}
			return advList;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			NcutJdbcUtils.release(conn, statement, rs);
		}
		return null;
	}
	
	
	//查询相应广告图片的总数
		public int getTotal(String company) {
			Connection conn = null;
			PreparedStatement statement = null;
			ResultSet rs = null;
			int count = 0;
			try {
				conn=ConnectionUtils.selectConn(company);
				String sql="select count(*) from tab_advertise";
				
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
		
		//生成临时图片列表
		public void insertAdvList(String advertise_ids, String company,int id) {
			Connection conn = null;
			PreparedStatement statement = null;
			ResultSet rs = null;
			try {			
				conn=ConnectionUtils.selectConn(company);
				String sql="insert into tab_tmp_advlist(que,operator) values(?,?)";
				statement=conn.prepareStatement(sql);			
				statement.setString(1,advertise_ids);
				statement.setInt(2,id);
				
				statement.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				NcutJdbcUtils.release(conn, statement, rs);
			}
		}
		
		//查询临时图片列表
		public List<AdvertiseList>   queryAdvList(int user_id, String company){
			Connection conn = null;
			PreparedStatement statement = null;
			ResultSet rs = null;
			List<AdvertiseList> advList = null;
			try {
				
				conn=ConnectionUtils.selectConn(company);
				advList= new  ArrayList<AdvertiseList>();
				String sql="select * from tab_tmp_advlist where operator="+user_id;
				statement=conn.prepareStatement(sql);
				rs=statement.executeQuery();
				while (rs.next()) {
					AdvertiseList advertiseList=new AdvertiseList();
					advertiseList.setId(rs.getInt("id"));
					String queue=rs.getString("que");
					String[]  advids = queue.split(",");
					String newQue="";
					for(String id:advids){
						Advertise advertise=queryAdvById(id,company);
						String y="<IMG width=50px height=50px  src=images/advertise/"+advertise.getName()+" /> ";
						newQue+=y;
					}
				
					advertiseList.setQue(newQue);
					advertiseList.setShowtime(rs.getString("showtime"));
					AdminUser adminUser=adminUserDao.findAdminUserById(rs.getString("operator"), company);
					advertiseList.setOpername(adminUser.getName());
					
					
					advList.add(advertiseList);
				}
				return advList;
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				NcutJdbcUtils.release(conn, statement, rs);
			}
			return null;
		}

		public Advertise queryAdvById(String id,String company){
			Connection conn = null;
			PreparedStatement statement = null;
			ResultSet rs = null;
			try {
				
				conn=ConnectionUtils.selectConn(company);
				
				String sql="select name from tab_advertise where id="+Integer.parseInt(id);
				statement=conn.prepareStatement(sql);
				rs=statement.executeQuery();
				while(rs.next()){
					Advertise advertise=new Advertise();
					advertise.setName(rs.getString("name"));
					return advertise;
				}

			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				NcutJdbcUtils.release(conn, statement, rs);
			}
			return null;
	}
		
	public void updateTmpAdvList(String id, String showtime, String company){
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		
		try {
			
			conn=ConnectionUtils.selectConn(company);
            String sql="update tab_tmp_advlist set showtime = ?  where  id =?  ";
			
			statement=conn.prepareStatement(sql);
			statement.setString(1, showtime);
			statement.setInt(2, Integer.parseInt(id));
			statement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			NcutJdbcUtils.release(conn, statement, rs);
		}
		
	}
	
	
	public AdvertiseList queryTmpAdvList(String id1,String company){
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			
			conn=ConnectionUtils.selectConn(company);
			String sql="select * from tab_tmp_advlist where id =? ";
			
			statement=conn.prepareStatement(sql);
			statement.setInt(1, Integer.parseInt(id1));
			rs=statement.executeQuery();
			while (rs.next()) {
				AdvertiseList advertiseList=new AdvertiseList();
				advertiseList.setId(rs.getInt("id"));
				advertiseList.setQue(rs.getString("que"));
				advertiseList.setShowtime(rs.getString("showtime"));
				advertiseList.setOperator(rs.getInt("operator"));

				return advertiseList;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			NcutJdbcUtils.release(conn, statement, rs);
		}
		return null;
		
	}
	
	public void finishAdvList(AdvertiseList advertiseList, String company){
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			
			conn=ConnectionUtils.selectConn(company);
			String sql="insert  into tab_advlist(que,showtime,createtime,operator) value(?,?,?,?)";
			
			statement=conn.prepareStatement(sql);
			
			statement.setString(1,advertiseList.getQue());
			
			@SuppressWarnings("deprecation")
			Date date=new Date(advertiseList.getShowtime());
			statement.setLong(2,date.getTime()/100000);
			
			statement.setString(3, advertiseList.getCreatetime());
			statement.setInt(4,advertiseList.getOperator());
			statement.executeUpdate();
		 
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			NcutJdbcUtils.release(conn, statement, rs);
		}
	}
	
	public void deleteTmpAdvList(String id,String company){
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			
			conn=ConnectionUtils.selectConn(company);
			String sql="Delete from tab_tmp_advlist where 1=1";
			
			statement=conn.prepareStatement(sql);
			statement.executeUpdate();
		 
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			NcutJdbcUtils.release(conn, statement, rs);
		}
	}
	
	//查询图片列表
	public List<AdvertiseList>   queryQueue(String company){
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		List<AdvertiseList> advList = null;
		try {
			
			conn=ConnectionUtils.selectConn(company);
			advList= new  ArrayList<AdvertiseList>();
			String sql="select * from tab_advlist order by  STR_TO_DATE(createtime,'%Y-%m-%d %H:%i:%s') desc";
			statement=conn.prepareStatement(sql);
			rs=statement.executeQuery();
			
			while (rs.next()) {
				AdvertiseList advertiseList=new AdvertiseList();
				advertiseList.setId(rs.getInt("id"));
				String queue=rs.getString("que");
				String[]  advids = queue.split(",");
				String newQue="";
				for(String id:advids){
					Advertise advertise=queryAdvById(id,company);
					String y="<IMG width=50px height=50px  src='images/advertise/"+advertise.getName()+"' /> ";
					newQue+=y;
				}
			
				advertiseList.setQue(newQue);
				
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				long time=Long.valueOf(rs.getString("showtime")); 		
				String d = format.format(time*100000);	
				
				advertiseList.setShowtime(d);
				advertiseList.setCreatetime(rs.getString("createtime"));
				AdminUser adminUser=adminUserDao.findAdminUserById(rs.getString("operator"), company);
				advertiseList.setOpername(adminUser.getName());
				
				
				
				advList.add(advertiseList);
			}
			return advList;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			NcutJdbcUtils.release(conn, statement, rs);
		}
		return null;
	}
	
	public AdvertiseList getAdvList(String company){
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			
			conn=ConnectionUtils.selectConn(company);
			String sql="select * from tab_advlist where showtime <= ? order by showtime desc limit 0,1";
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd" );
			statement=conn.prepareStatement(sql);
			statement.setLong(1, format.parse(format.format(new Date())).getTime()/100000);
			rs=statement.executeQuery();
			while (rs.next()) {
				AdvertiseList advertiseList=new AdvertiseList();
				advertiseList.setId(rs.getInt("id"));
				String queue=rs.getString("que");
				String[]  advids = queue.split(",");
				String newQue="";
				for(String id:advids){
					Advertise advertise=getAdvById(id,company);
					String y=advertise.getName()+",";
					newQue+=y;
				}
			
				advertiseList.setQue(newQue.substring(0, newQue.length()-1));
				advertiseList.setShowtime(rs.getString("showtime"));
				advertiseList.setOperator(rs.getInt("operator"));

				return advertiseList;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			NcutJdbcUtils.release(conn, statement, rs);
		}
		return null;
	}
	public void deleteAdvList(String id,String company){
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		int id1=Integer.parseInt(id);
		try {
			
			conn=ConnectionUtils.selectConn(company);
			String sql="Delete from tab_advlist where id="+id1;
			
			statement=conn.prepareStatement(sql);
			statement.executeUpdate();
		 
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			NcutJdbcUtils.release(conn, statement, rs);
		}
	}
	
	public Advertise getAdvById(String id,String company){
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			conn=ConnectionUtils.selectConn(company);
			String sql="select name from tab_advertise where id="+Integer.parseInt(id);
			statement=conn.prepareStatement(sql);
			rs=statement.executeQuery();
			while(rs.next()){
				Advertise advertise=new Advertise();
				advertise.setName(rs.getString("name"));
				return advertise;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			NcutJdbcUtils.release(conn, statement, rs);
		}
		return null;
}

}
