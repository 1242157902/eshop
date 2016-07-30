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

import sun.security.timestamp.TSRequest;

import com.eshop.entrity.Category;
import com.eshop.entrity.Stock;
import com.eshop.utils.ConnectionUtils;
import com.eshop.utils.DateUtil;
import com.eshop.utils.DefaultjdbcUtils;
import com.eshop.utils.DxtJdbcUtils;
import com.eshop.utils.NcutJdbcUtils;

public class CategoryDao {

	public List<Category> findCategoryByPagination(int currentPage,int pageSize, Map<String, Object> m, String company) {
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		List<Category> categoryList = null;
		try {
			conn=ConnectionUtils.selectConn(company);
			categoryList= new  ArrayList<Category>();
			String sql="select * from tab_category where 1=1 ";
			
			Set<Entry<String, Object>> set = m.entrySet();
			Iterator io = set.iterator();
			while (io.hasNext()) {
				Map.Entry<String, Object> me = (Map.Entry<String, Object>) io.next();
				if("C_id".equals(me.getKey())&&me.getValue()!=null && !"".equals(me.getValue())){
					sql += " and " + me.getKey() + " like '%"+ me.getValue()  +"%'" ;
				}
				if("c_name".equals(me.getKey()) &&me.getValue()!=null&& !"".equals(me.getValue())){
					sql += " and " + me.getKey() + " like '%"+ me.getValue()  +"%'" ;
				}
				if("c_parent".equals(me.getKey()) &&me.getValue()!=null&& !"".equals(me.getValue())){
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
				
				Category category = new Category();
				category.setC_id(rs.getString("C_id"));
				category.setC_name(rs.getString("C_name"));
				category.setC_parent(findCategoryById(rs.getString("C_parent"),company).getC_name());
				category.setC_desc(rs.getString("C_desc"));
				categoryList.add(category);
			}
			return categoryList;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			NcutJdbcUtils.release(conn, statement, rs);
		}
		return null;
	}

	
	
	
	// Description:查询分页的记录总数
	 
	public int getTotal(Map<String, Object> m,String company) 
	{
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		int count = 0;
		try {
			conn=ConnectionUtils.selectConn(company);
			String sql="select count(*) from tab_category where 1=1 ";
			
			Set<Entry<String, Object>> set = m.entrySet();
			Iterator io = set.iterator();
			while (io.hasNext()) {
				Map.Entry<String, Object> me = (Map.Entry<String, Object>) io.next();
				if("c_id".equals(me.getKey())&&me.getValue()!=null && !"".equals(me.getValue())){
					sql += " and " + me.getKey() + " = '"+ me.getValue()  +"' " ;
				}
				if("c_name".equals(me.getKey()) &&me.getValue()!=null&& !"".equals(me.getValue())){
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

	//通过类别ID查询类别信息
	public Category findCategoryById(String c_id,String company) {
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		Category category=null;
		try {
			if(c_id.equals("00")){
				category=new Category();
				category.setC_name("一级");
			}else{
			conn=ConnectionUtils.selectConn(company);
			String sql="select * from tab_category where C_id =? ";
			
			statement=conn.prepareStatement(sql);
			statement.setString(1, c_id);
			rs=statement.executeQuery();
			while (rs.next()) {
				category=new Category();
				category.setC_id(rs.getString("C_id"));
				category.setC_name(rs.getString("C_name"));
				category.setC_parent(rs.getString("C_parent"));
				category.setC_desc(rs.getString("C_desc"));
			}
		  }
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			NcutJdbcUtils.release(conn, statement, rs);
		}
		return category;
	}
	

	//更新类别信息
	public void updateCategory(Category category,String company) {
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			
			conn=ConnectionUtils.selectConn(company);
			String sql="update tab_category set C_name=?,C_parent=?,C_desc=? where C_id=? ";
			
			statement=conn.prepareStatement(sql);
			statement.setString(1, category.getC_name());
			statement.setString(2, category.getC_parent());
			statement.setString(3, category.getC_desc());
			statement.setString(4, category.getC_id());
			statement.executeUpdate();
		 
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			NcutJdbcUtils.release(conn, statement, rs);
		}
	}




	public void delete(String c_ids, String company) {
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
	
		try {
			
			conn=ConnectionUtils.selectConn(company);
			String sql="delete from tab_category where C_id =? ";
			statement=conn.prepareStatement(sql);
			statement.setString(1,c_ids);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			NcutJdbcUtils.release(conn, statement, rs);
		}
	}




	public void addCategory(Category category, String company) {
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;

		try {
			
			conn=ConnectionUtils.selectConn(company);
			String sql="insert into tab_category(C_id,C_name,C_parent,C_desc,create_time) values(?,?,?,?,?)";
			statement=conn.prepareStatement(sql);
			statement.setString(1,category.getC_id());
			statement.setString(2,category.getC_name());
			statement.setString(3,category.getC_parent());
			statement.setString(4,category.getC_desc());
			statement.setInt(5, DateUtil.dateConverTime());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			NcutJdbcUtils.release(conn, statement, rs);
		}
	}



	//通过类别名查询类别信息；
	public String findCategoryByName(String category_name, String company) {
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			
			conn=ConnectionUtils.selectConn(company);
			String sql="select * from tab_category where C_name =? ";
			
			statement=conn.prepareStatement(sql);
			statement.setString(1, category_name);
			rs=statement.executeQuery();
			while (rs.next()) {
				Category category=new Category();
				category.setC_id(rs.getString("C_id"));
				category.setC_name(rs.getString("C_name"));
				category.setC_parent(rs.getString("C_parent"));
				category.setC_desc(rs.getString("C_desc"));
				return category.getC_id();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			NcutJdbcUtils.release(conn, statement, rs);
		}
		return null;
	}

	//根据父类别名称查询父id
	public String findIdByParentName(String c_parent,String company) {
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		String P_id=null;
		try {
			
			conn=ConnectionUtils.selectConn(company);
			String sql="select * from tab_category where C_name =? ";
			
			statement=conn.prepareStatement(sql);
			statement.setString(1, c_parent);
			rs=statement.executeQuery();
			while (rs.next()) {
				P_id=rs.getString("C_id");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			NcutJdbcUtils.release(conn, statement, rs);
		}
		return P_id;
	}




	public List<Category> findAllCategory(String company) {
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		List<Category> cat=new ArrayList<Category>();
		try {
			
			conn=ConnectionUtils.selectConn(company);
			String sql="select t.C_name from tab_category t where t.C_parent!=?";
			
			statement=conn.prepareStatement(sql);
			statement.setString(1, "00");
			rs=statement.executeQuery();
			while (rs.next()) {
				Category c=new Category();
				c.setC_name(rs.getString("C_name"));
				cat.add(c);
			}
			return cat;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			NcutJdbcUtils.release(conn, statement, rs);
		}
		return null;
	}
	
	public List<Category> findByParentId(String company) {
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		List<Category> cat=new ArrayList<Category>();
		try {
			
			conn=ConnectionUtils.selectConn(company);
			String sql="select * from tab_category where C_parent='00' LIMIT 0,9";
			
			statement=conn.prepareStatement(sql);
			rs=statement.executeQuery();
			while (rs.next()) {
				Category c=new Category();
				c.setC_id(rs.getString("C_id"));
				c.setC_name(rs.getString("C_name"));
				c.setC_parent(rs.getString("C_parent"));
				c.setC_desc(rs.getString("C_desc"));
				cat.add(c);
			}
			return cat;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			NcutJdbcUtils.release(conn, statement, rs);
		}
		return null;
	}
	
	
	public List<Category> queryCategoryById(String c_parent,String company) {
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		List<Category> cat=new ArrayList<Category>();
		try {
			String sql=null;
			conn=ConnectionUtils.selectConn(company);
			if(c_parent!=null){
			    sql="SELECT * FROM tab_category WHERE c_parent='"+c_parent+"'";
			}else{
				sql="SELECT * FROM tab_category WHERE c_parent!=00";
			}
			statement=conn.prepareStatement(sql);
			rs=statement.executeQuery();
			while (rs.next()) {
				Category c=new Category();
				c.setC_id(rs.getString("C_id"));
				c.setC_name(rs.getString("C_name"));
				c.setC_parent(rs.getString("C_parent"));
				c.setC_desc(rs.getString("C_desc"));
				cat.add(c);
			}
			return cat;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			NcutJdbcUtils.release(conn, statement, rs);
		}
		return null;
	}


	//获得分类最大的类别编号C_id;
	public String findCategoryId(String p_id,String company) {
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		String c_id=null;
		try {
			
			conn=ConnectionUtils.selectConn(company);
			//查询在相同父类别编号中时间最大的类别记录；
			String sql="select * from tab_category where create_time=(select MAX(create_time) from tab_category where C_parent=?)";
			
			statement=conn.prepareStatement(sql);
			statement.setString(1,p_id);
			rs=statement.executeQuery();
			while (rs.next()) {
				c_id=rs.getString("C_id");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			NcutJdbcUtils.release(conn, statement, rs);
		}
		return c_id;
	}
	
	public List<Category> queryCategoryByKeyWord(String keyword,String company){
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		List<Category> cat=new ArrayList<Category>();
		try {
			conn=ConnectionUtils.selectConn(company);
			String sql="SELECT * FROM tab_category WHERE c_id IN (SELECT DISTINCT c_id FROM tab_goods WHERE good_name LIKE '%"+keyword+"%')";
			statement=conn.prepareStatement(sql);
			rs=statement.executeQuery();
			while (rs.next()) {
				Category c=new Category();
				c.setC_id(rs.getString("C_id"));
				c.setC_name(rs.getString("C_name"));
				c.setC_parent(rs.getString("C_parent"));
				c.setC_desc(rs.getString("C_desc"));
				cat.add(c);
			}
			return cat;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			NcutJdbcUtils.release(conn, statement, rs);
		}
		return null;
	}
}
