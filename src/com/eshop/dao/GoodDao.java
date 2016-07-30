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

import com.eshop.entrity.BrowseLog;
import com.eshop.entrity.Category;
import com.eshop.entrity.GoodFav;
import com.eshop.entrity.Goods;
import com.eshop.entrity.Order;
import com.eshop.entrity.OrderDetail;
import com.eshop.utils.ConnectionUtils;
import com.eshop.utils.DateUtil;
import com.eshop.utils.NcutJdbcUtils;
import com.eshop.utils.Page;


public class GoodDao {
	
	/**
	 * 
	 * @return:       List<Goods> 
	 * @param currentPage
	 * @param pageSize
	 * @param m
	 * @return
	 * <p>Description: 分页查询上架商品<p>
	 * @date:          2015年11月3日下午8:44:04
	 * @author         ysl
	 */
	public List<Goods> findSellGoodByPagination(int currentPage, int pageSize,
			Map<String, Object> m,String company) {
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		List<Goods> goodList = null;
		try {
			
			conn=ConnectionUtils.selectConn(company);
			goodList= new  ArrayList<Goods>();
			String sql="select * from tab_goods where Is_sell =1 ";
			
			Set<Entry<String, Object>> set = m.entrySet();
			Iterator io = set.iterator();
			while (io.hasNext()) {
				Map.Entry<String, Object> me = (Map.Entry<String, Object>) io.next();
				if("good_id".equals(me.getKey())&&me.getValue()!=null && !"".equals(me.getValue())){
					sql += " and " + me.getKey() + " = '"+ me.getValue()  +"' " ;
				}
				if("c_id".equals(me.getKey())&&me.getValue()!=null && !"".equals(me.getValue())){
					sql += " and " + me.getKey() + " = '"+ me.getValue()  +"' " ;
				}
				if("good_name".equals(me.getKey()) &&me.getValue()!=null&& !"".equals(me.getValue())){
					sql += " and " + me.getKey() + " like '%"+ me.getValue()  +"%'" ;
				}
				if("brand_name".equals(me.getKey()) &&me.getValue()!=null&& !"".equals(me.getValue())){
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
				
				Goods good=new Goods();
				good.setGood_id(rs.getString("Good_id"));;
				good.setGood_name(rs.getString("Good_name"));
				good.setBrand_name(rs.getString("Brand_name"));
				good.setGood_guige(rs.getString("Good_guige"));
				good.setIsvisual(rs.getBoolean("isvisual"));
				good.setC_id(rs.getString("C_id"));
				good.setGood_price(rs.getBigDecimal("Good_price").doubleValue());
				good.setGood_nowprice(rs.getBigDecimal("Good_nowprice").doubleValue());
				good.setGood_desc(rs.getString("Good_desc"));
				good.setIs_sell(rs.getBoolean("Is_sell"));
				goodList.add(good);
			}
			return goodList;
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
	 * <p>Description: 查询上架商品总记录数<p>
	 * @date:          2015年11月3日下午8:43:45
	 * @author         ysl
	 */
	public int getSellTotal(Map<String, Object> m,String company) {
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		int count = 0;
		try {
			conn=ConnectionUtils.selectConn(company);
			String sql="select count(*) from tab_goods where Is_sell =1 ";
			
			Set<Entry<String, Object>> set = m.entrySet();
			Iterator io = set.iterator();
			while (io.hasNext()) {
				Map.Entry<String, Object> me = (Map.Entry<String, Object>) io.next();
				if("good_id".equals(me.getKey())&&me.getValue()!=null && !"".equals(me.getValue())){
					sql += " and " + me.getKey() + " = '"+ me.getValue()  +"' " ;
				}
				if("c_id".equals(me.getKey())&&me.getValue()!=null && !"".equals(me.getValue())){
					sql += " and " + me.getKey() + " = '"+ me.getValue()  +"' " ;
				}
				if("good_name".equals(me.getKey()) &&me.getValue()!=null&& !"".equals(me.getValue())){
					sql += " and " + me.getKey() + " like '%"+ me.getValue()  +"%'" ;
				}
				if("brand_name".equals(me.getKey()) &&me.getValue()!=null&& !"".equals(me.getValue())){
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
	
	//分页查询商品信息
	public List<Goods> findGoodByPagination(int currentPage, int pageSize,
			Map<String, Object> m,String company) {
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		List<Goods> goodList = null;
		try {
			
			conn=ConnectionUtils.selectConn(company);
			goodList= new  ArrayList<Goods>();
			String sql="select G.*,C.C_name from tab_goods G inner join tab_category C on G.C_id=C.C_id where 1=1 ";
			
			Set<Entry<String, Object>> set = m.entrySet();
			Iterator io = set.iterator();
			while (io.hasNext()) {
				Map.Entry<String, Object> me = (Map.Entry<String, Object>) io.next();
				if("Good_id".equals(me.getKey())&&me.getValue()!=null && !"".equals(me.getValue())){
					sql += " and " + me.getKey() + " like '%"+ me.getValue()  +"%'" ;
				}
				if("c_id".equals(me.getKey())&&me.getValue()!=null && !"".equals(me.getValue())){
					sql += " and " + me.getKey() + " = '"+ me.getValue()  +"' " ;
				}
				if("good_name".equals(me.getKey()) &&me.getValue()!=null&& !"".equals(me.getValue())){
					sql += " and " + me.getKey() + " like '%"+ me.getValue()  +"%'" ;
				}
				if("brand_name".equals(me.getKey()) &&me.getValue()!=null&& !"".equals(me.getValue())){
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
				
				Goods good=new Goods();
				 good.setGood_id(rs.getString("Good_id"));;
				 good.setGood_name(rs.getString("Good_name"));
				 good.setBrand_name(rs.getString("Brand_name"));
				 good.setGood_guige(rs.getString("Good_guige"));
				 good.setIsvisual(rs.getBoolean("isvisual"));
				 good.setC_id(rs.getString("C_name"));
				 good.setGood_price(rs.getDouble("Good_price"));
				 good.setGood_nowprice(rs.getDouble("Good_nowprice"));
				 good.setGood_desc(rs.getString("Good_desc"));
				 good.setIs_sell(rs.getBoolean("Is_sell"));
				 goodList.add(good);
			}
			return goodList;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			NcutJdbcUtils.release(conn, statement, rs);
		}
		return null;
	}

	//查询相应商品的总数
	public int getTotal(Map<String, Object> m,String company) {
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		int count = 0;
		try {
			conn=ConnectionUtils.selectConn(company);
			String sql="select count(*) from tab_goods where 1=1 ";
			
			Set<Entry<String, Object>> set = m.entrySet();
			Iterator io = set.iterator();
			while (io.hasNext()) {
				Map.Entry<String, Object> me = (Map.Entry<String, Object>) io.next();
				if("good_id".equals(me.getKey())&&me.getValue()!=null && !"".equals(me.getValue())){
					sql += " and " + me.getKey() + " = '"+ me.getValue()  +"' " ;
				}
				if("c_id".equals(me.getKey())&&me.getValue()!=null && !"".equals(me.getValue())){
					sql += " and " + me.getKey() + " = '"+ me.getValue()  +"' " ;
				}
				if("good_name".equals(me.getKey()) &&me.getValue()!=null&& !"".equals(me.getValue())){
					sql += " and " + me.getKey() + " like '%"+ me.getValue()  +"%'" ;
				}
				if("brand_name".equals(me.getKey()) &&me.getValue()!=null&& !"".equals(me.getValue())){
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

	//通过商品的编号查询商品信息
	public Goods findGoodById(String good_id,String company) {
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			
			conn=ConnectionUtils.selectConn(company);
			String sql="select * from tab_goods where Good_id =? ";
			
			statement=conn.prepareStatement(sql);
			statement.setString(1, good_id);
			rs=statement.executeQuery();
			while (rs.next()) {
				Goods good=new Goods();
				 good.setGood_id(rs.getString("Good_id"));;
				 good.setGood_name(rs.getString("Good_name"));
				 good.setBrand_name(rs.getString("Brand_name"));
				 good.setGood_guige(rs.getString("Good_guige"));
				 good.setIsvisual(rs.getBoolean("isvisual"));
				 good.setC_id(rs.getString("C_id"));
				 good.setGood_price(rs.getDouble("Good_price"));
				 good.setGood_nowprice(rs.getDouble("Good_nowprice"));
				 good.setGood_detail_img(rs.getString("Good_detail_img"));
				 good.setGood_img(rs.getString("Good_img"));
				 good.setGood_category_img(rs.getString("Good_category_img"));
				 good.setGood_small_img(rs.getString("Good_small_img"));
				 good.setGood_detail_img(rs.getString("Good_detail_img"));
				 good.setGood_desc(rs.getString("Good_desc")); 
				 good.setIs_sell(rs.getBoolean("Is_sell"));
				 
				return good;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			NcutJdbcUtils.release(conn, statement, rs);
		}
		return null;
	}

	//更新商品的信息
	public void updateGood(Goods good,String company) {
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			
			conn=ConnectionUtils.selectConn(company);
			String sql="update tab_goods set Good_name=?,Brand_name=?,Good_guige=?,isvisual=?,C_id=?,Good_price=?,Good_nowprice=?,Good_desc=?,Is_sell=? where Good_id=? ";
			
			statement=conn.prepareStatement(sql);
			statement.setString(1, good.getGood_name());
			statement.setString(2, good.getBrand_name());
			statement.setString(3, good.getGood_guige());
			statement.setBoolean(4, good.getIsvisual());
			statement.setString(5, good.getC_id());
			statement.setDouble(6,good.getGood_price());
			statement.setDouble(7, good.getGood_nowprice());
			statement.setString(8, good.getGood_desc());
			statement.setBoolean(9, good.getIs_sell());
			statement.setString(10, good.getGood_id());
			statement.executeUpdate();
		 
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			NcutJdbcUtils.release(conn, statement, rs);
		}
	}

	//删除选中的商品
	public void deleteGood(String good_id, String company) {
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
	
		try {
			
			conn=ConnectionUtils.selectConn(company);
			String sql="delete from tab_goods where Good_id =? ";
			statement=conn.prepareStatement(sql);
			statement.setString(1,good_id);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			NcutJdbcUtils.release(conn, statement, rs);
		}
	}

	//增加商品
	public void addGood(Goods good, String company) {
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		
		try {
			
			conn=ConnectionUtils.selectConn(company);
			String sql="insert into tab_goods(Good_id,Good_name,Brand_name,Good_guige,isvisual,C_id,Good_price,Good_nowprice,Good_img,Good_small_img,Good_category_img,Good_detail_img,Good_desc,Is_sell,create_time) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
			statement=conn.prepareStatement(sql);
			statement.setString(1,good.getGood_id());
			statement.setString(2,good.getGood_name());
			statement.setString(3,good.getBrand_name());
			statement.setString(4,good.getGood_guige());
			statement.setBoolean(5,good.getIsvisual());
			statement.setString(6,good.getC_id());
			statement.setDouble(7,good.getGood_price());
			statement.setDouble(8,good.getGood_nowprice());
			statement.setString(9, good.getGood_img());
			statement.setString(10, good.getGood_small_img());
			statement.setString(11, good.getGood_category_img());
			statement.setString(12, good.getGood_detail_img());
			statement.setString(13, good.getGood_desc());
			statement.setBoolean(14, good.getIs_sell());
			statement.setInt(15, DateUtil.dateConverTime());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			NcutJdbcUtils.release(conn, statement, rs);
		}
	}

	//更新商品价格和积分信息
	public void updateGoodPrice(Goods good, String company) {
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try{
			conn=ConnectionUtils.selectConn(company);
			String sql="update tab_goods set Good_price=?,Good_nowprice=? where Good_id=? ";
			
			statement=conn.prepareStatement(sql);
			statement.setDouble(1,good.getGood_price());
			statement.setDouble(2, good.getGood_nowprice());
			statement.setString(3, good.getGood_id());
			statement.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			NcutJdbcUtils.release(conn, statement, rs);
		}
	}
	
	//更新商品销量信息
		public void updateGoodSaleNum(Goods good, String company) {
			Connection conn = null;
			PreparedStatement statement = null;
			ResultSet rs = null;
			try{
				conn=ConnectionUtils.selectConn(company);
				String sql="update tab_goods set sell_num=? where Good_id=? ";
				
				statement=conn.prepareStatement(sql);
				statement.setInt(1,good.getSale_num());
				statement.setString(2, good.getGood_id());
				
				statement.executeUpdate();
			}catch (Exception e) {
				e.printStackTrace();
			}finally{
				NcutJdbcUtils.release(conn, statement, rs);
			}
		}
		
	
	
	//查询新品推荐商品
	public List<Goods> queryAll(String company){
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		List<Goods> goodList = null;
		try {
			
			conn=ConnectionUtils.selectConn(company);
			goodList= new  ArrayList<Goods>();
			String sql="select * from tab_goods  where Is_sell =1 ORDER BY create_time DESC limit 0,8 ";
			statement=conn.prepareStatement(sql);
			rs=statement.executeQuery();
			while (rs.next()) {
				
				Goods good=new Goods();
				good.setGood_id(rs.getString("Good_id"));;
				good.setGood_name(rs.getString("Good_name"));
				good.setBrand_name(rs.getString("Brand_name"));
				good.setGood_guige(rs.getString("Good_guige"));
				good.setIsvisual(rs.getBoolean("isvisual"));
				good.setC_id(rs.getString("C_id"));
				good.setGood_price(rs.getBigDecimal("Good_price").doubleValue());
				good.setGood_nowprice(rs.getBigDecimal("Good_nowprice").doubleValue());
				good.setGood_img(rs.getString("Good_img"));
				good.setGood_category_img(rs.getString("Good_category_img"));
				good.setGood_small_img(rs.getString("Good_small_img"));
				good.setGood_detail_img(rs.getString("Good_detail_img"));
				good.setGood_desc(rs.getString("Good_desc"));
				good.setIs_sell(rs.getBoolean("Is_sell"));
				goodList.add(good);
			}
			return goodList;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			NcutJdbcUtils.release(conn, statement, rs);
		}
		return null;
		
	}
	
	//查询兑换排行榜商品
	public List<Goods> queryRank(String company){
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		List<Goods> goodList = null;
		try {
			
			conn=ConnectionUtils.selectConn(company);
			goodList= new  ArrayList<Goods>();
			String sql="SELECT * FROM tab_goods  WHERE Is_sell =1 ORDER BY sell_num DESC LIMIT 0,10";
			statement=conn.prepareStatement(sql);
			rs=statement.executeQuery();
			while (rs.next()) {
				
				Goods good=new Goods();
				good.setGood_id(rs.getString("Good_id"));
				good.setGood_name(rs.getString("Good_name"));
				good.setBrand_name(rs.getString("Brand_name"));
				good.setGood_guige(rs.getString("Good_guige"));
				good.setIsvisual(rs.getBoolean("isvisual"));
				good.setC_id(rs.getString("C_id"));
				good.setGood_price(rs.getBigDecimal("Good_price").doubleValue());
				good.setGood_nowprice(rs.getBigDecimal("Good_nowprice").doubleValue());
				good.setGood_img(rs.getString("Good_img"));
				good.setGood_category_img(rs.getString("Good_category_img"));
				good.setGood_small_img(rs.getString("Good_small_img"));
				good.setGood_detail_img(rs.getString("Good_detail_img"));
				good.setGood_desc(rs.getString("Good_desc"));
				good.setIs_sell(rs.getBoolean("Is_sell"));
				goodList.add(good);
			}
			return goodList;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			NcutJdbcUtils.release(conn, statement, rs);
		}
		return null;
		
	}
	
	//查询某一级类别下的所有商品
	public Page<Goods> lookGoods(String c_parent,String company,String pagenum){
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		List<Goods> goodList = null;
		int totalrecord=(int) getTotal(company,"-1",c_parent);
		try {
			Page<Goods> page=null;
			conn=ConnectionUtils.selectConn(company);
			if (pagenum==null) {
			      page=new Page<Goods>(totalrecord,12, 1);
			}
			else{
				  page=new Page<Goods>(totalrecord,12,Integer.parseInt(pagenum));
			}
			//算出了总页数，以及用户想看的页从数据库那个地方开始取
			
			goodList= new  ArrayList<Goods>();
			String sql="SELECT * FROM tab_goods  WHERE Is_sell =1  AND C_id IN (SELECT C_id FROM tab_category WHERE c_parent='"+c_parent+"') ORDER BY sell_num DESC"+" limit "+page.getStartindex()+","+page.getPagesize();
			statement=conn.prepareStatement(sql);
			rs=statement.executeQuery();
			while (rs.next()) {
				
				Goods good=new Goods();
				good.setGood_id(rs.getString("Good_id"));;
				good.setGood_name(rs.getString("Good_name"));
				good.setBrand_name(rs.getString("Brand_name"));
				good.setGood_guige(rs.getString("Good_guige"));
				good.setIsvisual(rs.getBoolean("isvisual"));
				good.setC_id(rs.getString("C_id"));
				good.setGood_price(rs.getBigDecimal("Good_price").doubleValue());
				good.setGood_nowprice(rs.getBigDecimal("Good_nowprice").doubleValue());
				good.setGood_img(rs.getString("Good_img"));
				good.setGood_small_img(rs.getString("Good_small_img"));
				good.setGood_category_img(rs.getString("Good_category_img"));
				good.setGood_detail_img(rs.getString("Good_detail_img"));
				good.setGood_desc(rs.getString("Good_desc"));
				good.setIs_sell(rs.getBoolean("Is_sell"));
				goodList.add(good);
			}
			page.setUrl("category/queryCategoryById.do");
			page.setList(goodList);
			return page;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			NcutJdbcUtils.release(conn, statement, rs);
		}
		return null;
		
	}
	
	

	public Page<Goods> lookMerchById(String c_id,String c_parent, String company,String nowprice,String sale_num,String pagenum){
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		List<Goods> goodList=null;
		int totalrecord=(int) getTotal(company,c_id,c_parent);
	
		try {
			String sql=null;
			Page<Goods> goodPage=null;
			goodList= new  ArrayList<Goods>();
			conn=ConnectionUtils.selectConn(company);
			if(pagenum==null){
				goodPage = new Page<Goods>(totalrecord, 12, 1);
			}else{
				goodPage = new Page<Goods>(totalrecord, 12, Integer.parseInt(pagenum));
			}
			
			if("-1".equals(c_id)){
				sql="SELECT * FROM tab_goods  WHERE Is_sell =1  AND C_id IN (SELECT C_id FROM tab_category WHERE c_parent='"+c_parent+"') ORDER BY sell_num DESC limit "+goodPage.getStartindex()+","+goodPage.getPagesize();
			}else{
				sql="SELECT * FROM tab_goods  WHERE Is_sell =1  AND C_id ='"+c_id+"' ORDER BY sell_num DESC limit "+goodPage.getStartindex()+","+goodPage.getPagesize();
			}
			statement=conn.prepareStatement(sql);
			rs=statement.executeQuery();
			while (rs.next()) {
				
				Goods good=new Goods();
				good.setGood_id(rs.getString("Good_id"));
				good.setGood_name(rs.getString("Good_name"));
				good.setBrand_name(rs.getString("Brand_name"));
				good.setGood_guige(rs.getString("Good_guige"));
				good.setIsvisual(rs.getBoolean("isvisual"));
				good.setC_id(rs.getString("C_id"));
				good.setGood_price(rs.getBigDecimal("Good_price").doubleValue());
				good.setGood_nowprice(rs.getBigDecimal("Good_nowprice").doubleValue());
				good.setGood_img(rs.getString("Good_img"));
				good.setGood_category_img(rs.getString("Good_category_img"));
				good.setGood_small_img(rs.getString("Good_small_img"));
				good.setGood_detail_img(rs.getString("Good_detail_img"));
				good.setGood_desc(rs.getString("Good_desc"));
				good.setIs_sell(rs.getBoolean("Is_sell"));
				goodList.add(good);
			}
			goodPage.setUrl("good/lookMerchById.do");
			goodPage.setList(goodList);
			return goodPage;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			NcutJdbcUtils.release(conn, statement, rs);
		}
		return null;
	}

	//查询所有商品
		public Page<Goods> lookAll(String company,String pagenum,String c_id,String c_parent){
			Connection conn = null;
			PreparedStatement statement = null;
			ResultSet rs = null;
			Page<Goods> goodPage=null;
			List<Goods> goodList = null;
			int totalrecord=(int) getTotal(company,c_id,c_parent);
			try {
				String sql="SELECT * FROM tab_goods  WHERE Is_sell =1";
				conn=ConnectionUtils.selectConn(company);
				goodList= new  ArrayList<Goods>();
				if(pagenum==null){
					goodPage = new Page<Goods>(totalrecord, 12, 1);
				}else{
					goodPage = new Page<Goods>(totalrecord, 12, Integer.parseInt(pagenum));
				}
				if(c_id==null ||"-1".equals(c_id)){
					sql+=" ORDER BY sell_num DESC LIMIT "+goodPage.getStartindex()+","+goodPage.getPagesize();
				}else{
					sql+=" and c_id='"+c_id+"' ORDER BY sell_num DESC LIMIT "+goodPage.getStartindex()+","+goodPage.getPagesize();
				}
				
				statement=conn.prepareStatement(sql);
				rs=statement.executeQuery();
				while (rs.next()) {
					
					Goods good=new Goods();
					good.setGood_id(rs.getString("Good_id"));
					good.setGood_name(rs.getString("Good_name"));
					good.setBrand_name(rs.getString("Brand_name"));
					good.setGood_guige(rs.getString("Good_guige"));
					good.setIsvisual(rs.getBoolean("isvisual"));
					good.setC_id(rs.getString("C_id"));
					good.setGood_price(rs.getBigDecimal("Good_price").doubleValue());
					good.setGood_nowprice(rs.getBigDecimal("Good_nowprice").doubleValue());
					good.setGood_img(rs.getString("Good_img"));
					good.setGood_category_img(rs.getString("Good_category_img"));
					good.setGood_small_img(rs.getString("Good_small_img"));
					good.setGood_detail_img(rs.getString("Good_detail_img"));
					good.setGood_desc(rs.getString("Good_desc"));
					good.setIs_sell(rs.getBoolean("Is_sell"));
					goodList.add(good);
				}
				goodPage.setList(goodList);
				goodPage.setUrl("good/lookAll.do");
				return goodPage;
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				NcutJdbcUtils.release(conn, statement, rs);
			}
			return null;
		}
	
	
	
	//通过C_id查询商品表中的相同类别中Good_id最大的记录；
	public String findGoodIdByCId(String c_id, String company) {
			Connection conn = null;
			PreparedStatement statement = null;
			ResultSet rs = null;
			String good_id=null;
			try {
				
				conn=ConnectionUtils.selectConn(company);
				//查询在相同父类别编号中时间最大的类别记录；
				String sql="select * from tab_goods where create_time=(select MAX(create_time) from tab_goods where C_id=?)";
				
				statement=conn.prepareStatement(sql);
				statement.setString(1,c_id);
				rs=statement.executeQuery();
				while (rs.next()) {
					good_id=rs.getString("Good_id");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				NcutJdbcUtils.release(conn, statement, rs);
			}
			return good_id;
		}
	
	public List<String> getKeyWord(String company){
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		List<String> goodNameList = new ArrayList<String>();	
		try {
			String sql=null;
			conn=ConnectionUtils.selectConn(company);		
			sql="SELECT Good_name FROM tab_goods";			
			statement=conn.prepareStatement(sql);
			rs=statement.executeQuery();
			while (rs.next()) {
				goodNameList.add(rs.getString("Good_name"));
			}			
			return goodNameList;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			NcutJdbcUtils.release(conn, statement, rs);
		}
		return null;
	}
	
	public Page<Goods>   queryGoodsByKeyword(String keyword,String company, String pagenum){
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		List<Goods> goodList = null;
		Page<Goods> goodPage=null;
		int totalrecord=(int) getTotalByKeyWord(company,keyword,"-1");
		try {
			String sql=null;
			conn=ConnectionUtils.selectConn(company);
			goodList= new  ArrayList<Goods>();
			if(pagenum==null){
				goodPage = new Page<Goods>(totalrecord, 12, 1);
			}else{
				goodPage = new Page<Goods>(totalrecord, 12, Integer.parseInt(pagenum));
			}
			sql="SELECT * FROM tab_goods  WHERE Is_sell =1 and Good_name like '%"+keyword+"%' ORDER BY sell_num DESC LIMIT "+goodPage.getStartindex()+","+goodPage.getPagesize();			
			statement=conn.prepareStatement(sql);
			rs=statement.executeQuery();
			while (rs.next()) {
				
				Goods good=new Goods();
				good.setGood_id(rs.getString("Good_id"));
				good.setGood_name(rs.getString("Good_name"));
				good.setBrand_name(rs.getString("Brand_name"));
				good.setGood_guige(rs.getString("Good_guige"));
				good.setIsvisual(rs.getBoolean("isvisual"));
				good.setC_id(rs.getString("C_id"));
				good.setGood_price(rs.getBigDecimal("Good_price").doubleValue());
				good.setGood_nowprice(rs.getBigDecimal("Good_nowprice").doubleValue());
				good.setGood_img(rs.getString("Good_img"));
				good.setGood_category_img(rs.getString("Good_category_img"));
				good.setGood_small_img(rs.getString("Good_small_img"));
				good.setGood_detail_img(rs.getString("Good_detail_img"));
				good.setGood_desc(rs.getString("Good_desc"));
				good.setIs_sell(rs.getBoolean("Is_sell"));
				goodList.add(good);
			}
			goodPage.setList(goodList);
			return goodPage;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			NcutJdbcUtils.release(conn, statement, rs);
		}
		return null;
	}
	
	public List<Goods>  queryRecommend(String c_id,String good_id, String company){
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		List<Goods> goodList = null;
		try {
			String sql=null;
			conn=ConnectionUtils.selectConn(company);
			goodList= new  ArrayList<Goods>();
			sql="SELECT * FROM tab_goods  WHERE Is_sell =1 and C_id='"+c_id+"' and Good_id!='"+good_id+"' ORDER BY sell_num DESC LIMIT 0,8";			
			statement=conn.prepareStatement(sql);
			rs=statement.executeQuery();
			while (rs.next()) {
				
				Goods good=new Goods();
				good.setGood_id(rs.getString("Good_id"));
				good.setGood_name(rs.getString("Good_name"));
				good.setBrand_name(rs.getString("Brand_name"));
				good.setGood_guige(rs.getString("Good_guige"));
				good.setIsvisual(rs.getBoolean("isvisual"));
				good.setC_id(rs.getString("C_id"));
				good.setGood_price(rs.getBigDecimal("Good_price").doubleValue());
				good.setGood_nowprice(rs.getBigDecimal("Good_nowprice").doubleValue());
				good.setGood_img(rs.getString("Good_img"));
				good.setGood_category_img(rs.getString("Good_category_img"));
				good.setGood_small_img(rs.getString("Good_small_img"));
				good.setGood_detail_img(rs.getString("Good_detail_img"));
				good.setGood_desc(rs.getString("Good_desc"));
				good.setIs_sell(rs.getBoolean("Is_sell"));
				goodList.add(good);
			}
			return goodList;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			NcutJdbcUtils.release(conn, statement, rs);
		}
		return null;
	}

	public void updateGoodPics(Goods good, String company) {
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			
			conn=ConnectionUtils.selectConn(company);
			String sql="update tab_goods set Good_img=?,Good_small_img=?,Good_category_img=?,Good_detail_img=? where Good_id=? ";
			
			statement=conn.prepareStatement(sql);
			statement.setString(1, good.getGood_img());
			statement.setString(2, good.getGood_small_img());
			statement.setString(3, good.getGood_category_img());
			statement.setString(4, good.getGood_detail_img());
			statement.setString(5, good.getGood_id());
			statement.executeUpdate();
		 
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			NcutJdbcUtils.release(conn, statement, rs);
		}
	}
	
	
	public int getTotal(String company,String id,String c_parent){
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		int count = 0;
		try {
			String sql="select count(*) from tab_goods where Is_sell =1";
			conn=ConnectionUtils.selectConn(company);
			if("-1".equals(id)&&!"0".equals(c_parent)){
				sql+=" AND C_id IN (SELECT C_id FROM tab_category WHERE c_parent='"+c_parent+"')";
			}else if(id!=null&&!"0".equals(c_parent)){
				sql+=" and C_id='"+id+"'";
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
	
	public int getTotalByKeyWord(String company,String keyword,String c_id){
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		int count = 0;
		try {
			
			conn=ConnectionUtils.selectConn(company);
			String sql="select count(*) from tab_goods where Is_sell =1 and Good_name like '%"+keyword+"%'";
			if(!"-1".equals(c_id)){
				sql+="  and c_id="+c_id;
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
	
	public Page<Goods> lookMerchByKeyword(String c_id, String keyword,String company, String pagenum){
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		List<Goods> goodList = null;
		Page<Goods> goodPage=null;
		int totalrecord=(int) getTotalByKeyWord(company,keyword,c_id);
		try {
			String sql="SELECT * FROM tab_goods  WHERE Is_sell =1 and Good_name like '%"+keyword+"%'";
			conn=ConnectionUtils.selectConn(company);
			goodList= new  ArrayList<Goods>();
			if(pagenum==null){
				goodPage = new Page<Goods>(totalrecord,12, 1);
			}else{
				goodPage = new Page<Goods>(totalrecord, 12, Integer.parseInt(pagenum));
			}
			if(!"-1".equals(c_id)){
				sql+=" and c_id="+c_id;
			}
			sql+=" ORDER BY sell_num DESC LIMIT "+goodPage.getStartindex()+","+goodPage.getPagesize();			
			statement=conn.prepareStatement(sql);
			rs=statement.executeQuery();
			while (rs.next()) {
				
				Goods good=new Goods();
				good.setGood_id(rs.getString("Good_id"));
				good.setGood_name(rs.getString("Good_name"));
				good.setBrand_name(rs.getString("Brand_name"));
				good.setGood_guige(rs.getString("Good_guige"));
				good.setIsvisual(rs.getBoolean("isvisual"));
				good.setC_id(rs.getString("C_id"));
				good.setGood_price(rs.getBigDecimal("Good_price").doubleValue());
				good.setGood_nowprice(rs.getBigDecimal("Good_nowprice").doubleValue());
				good.setGood_img(rs.getString("Good_img"));
				good.setGood_category_img(rs.getString("Good_category_img"));
				good.setGood_small_img(rs.getString("Good_small_img"));
				good.setGood_detail_img(rs.getString("Good_detail_img"));
				good.setGood_desc(rs.getString("Good_desc"));
				good.setIs_sell(rs.getBoolean("Is_sell"));
				goodList.add(good);
			}
			goodPage.setList(goodList);
			return goodPage;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			NcutJdbcUtils.release(conn, statement, rs);
		}
		return null;
	}
	
	
	public Page<Goods>  lookNewGoods(String company, String pagenum,String c_id, String c_parent){
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		Page<Goods> goodPage=null;
		List<Goods> goodList = null;
		int totalrecord=(int) getTotal(company,c_id,c_parent);
		try {
			String sql="SELECT * FROM tab_goods  WHERE Is_sell =1";
			conn=ConnectionUtils.selectConn(company);
			goodList= new  ArrayList<Goods>();
			if(pagenum==null){
				goodPage = new Page<Goods>(totalrecord, 12, 1);
			}else{
				goodPage = new Page<Goods>(totalrecord, 12, Integer.parseInt(pagenum));
			}
			if(c_id==null ||"-1".equals(c_id)){
				sql+=" ORDER BY create_time DESC LIMIT "+goodPage.getStartindex()+","+goodPage.getPagesize();
			}else{
				sql+=" and c_id='"+c_id+"' ORDER BY create_time DESC LIMIT "+goodPage.getStartindex()+","+goodPage.getPagesize();
			}
			
			statement=conn.prepareStatement(sql);
			rs=statement.executeQuery();
			while (rs.next()) {
				
				Goods good=new Goods();
				good.setGood_id(rs.getString("Good_id"));
				good.setGood_name(rs.getString("Good_name"));
				good.setBrand_name(rs.getString("Brand_name"));
				good.setGood_guige(rs.getString("Good_guige"));
				good.setIsvisual(rs.getBoolean("isvisual"));
				good.setC_id(rs.getString("C_id"));
				good.setGood_price(rs.getBigDecimal("Good_price").doubleValue());
				good.setGood_nowprice(rs.getBigDecimal("Good_nowprice").doubleValue());
				good.setGood_img(rs.getString("Good_img"));
				good.setGood_category_img(rs.getString("Good_category_img"));
				good.setGood_small_img(rs.getString("Good_small_img"));
				good.setGood_detail_img(rs.getString("Good_detail_img"));
				good.setGood_desc(rs.getString("Good_desc"));
				good.setIs_sell(rs.getBoolean("Is_sell"));
				goodList.add(good);
			}
			goodPage.setList(goodList);
			goodPage.setUrl("good/lookNewGoods.do");
			return goodPage;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			NcutJdbcUtils.release(conn, statement, rs);
		}
		return null;
	}

	public void addFavGood(GoodFav goodFav, String company) {
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
	try {
			
			conn=ConnectionUtils.selectConn(company);
			String sql="insert into tab_good_fav(fav_id,user_id,good_id,good_name,fav_status,createdTime) values(?,?,?,?,?,?) ";
			statement=conn.prepareStatement(sql);
			statement.setString(1,goodFav.getFav_id());
			statement.setInt(2,goodFav.getUser_id());
			statement.setString(3,goodFav.getGood_id());
			statement.setString(4,goodFav.getGood_name());
			statement.setInt(5,goodFav.getFav_status());
			statement.setString(6,goodFav.getCreatedTime());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			NcutJdbcUtils.release(conn, statement, rs);
		}
	}
	public List<Goods> queryMyFavByUser(int user_id, String company,
			int currentPage, int pageSize) {

		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		List<Goods> goodList = new ArrayList<Goods>();
		try {
			
			conn=ConnectionUtils.selectConn(company);
			String sql = "SELECT *  from tab_goods join tab_good_fav on tab_goods.Good_id=tab_good_Fav.good_id where user_id=? ORDER BY tab_good_fav.createdTime DESC limit ?,?";

			statement=conn.prepareStatement(sql);
			statement.setInt(1, user_id);
			
			statement.setInt(2, (currentPage - 1) * pageSize);
			statement.setInt(3, pageSize);
			rs=statement.executeQuery();
			while (rs.next()) {
				
				Goods good=new Goods();
				good.setGood_id(rs.getString("Good_id"));
				good.setGood_name(rs.getString("Good_name"));
				good.setBrand_name(rs.getString("Brand_name"));
				good.setGood_guige(rs.getString("Good_guige"));
				good.setIsvisual(rs.getBoolean("isvisual"));
				good.setC_id(rs.getString("C_id"));
				good.setGood_price(rs.getBigDecimal("Good_price").doubleValue());
				good.setGood_nowprice(rs.getBigDecimal("Good_nowprice").doubleValue());
				good.setGood_img(rs.getString("Good_img"));
				good.setGood_category_img(rs.getString("Good_category_img"));
				good.setGood_small_img(rs.getString("Good_small_img"));
				good.setGood_detail_img(rs.getString("Good_detail_img"));
				good.setGood_desc(rs.getString("Good_desc"));
				good.setIs_sell(rs.getBoolean("Is_sell"));
				goodList.add(good);
				
			}
			return goodList;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			NcutJdbcUtils.release(conn, statement, rs);
		}
		return null;
	}

	public int getFavTotal(int user_id, String company) {
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		int count=0;
		
		try {
			conn=ConnectionUtils.selectConn(company);
			String sql = "SELECT count(*) count  from tab_good_fav  where user_id=? ";
			        statement = conn.prepareStatement(sql);
					statement.setInt(1, user_id);
					rs = statement.executeQuery();
					while (rs.next()) {
						count = rs.getInt("count");
						}
								
							
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

	public void addBrowseLog(BrowseLog browseLog, String company) {
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
	try {
			
			conn=ConnectionUtils.selectConn(company);
			String sql="insert into tab_browseLog(user_id,user_telephone,good_id,good_name,browseTime) values(?,?,?,?,?) ";
			statement=conn.prepareStatement(sql);
			statement.setInt(1,browseLog.getUser().getUser_id());
			statement.setString(2,browseLog.getUser().getTelephone());
			statement.setString(3,browseLog.getGood_id());
			statement.setString(4,browseLog.getGood_name());
			statement.setString(5,browseLog.getBrowseTime());
			
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			NcutJdbcUtils.release(conn, statement, rs);
		}
		
	}

	public List<GoodFav> queryFavGoodByUserId(int user_id, String company) {
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		List<GoodFav> goodFavs=new ArrayList<GoodFav>();
		try {
			
			conn=ConnectionUtils.selectConn(company);
			String sql="select * from tab_good_fav where user_id =? ";
			
			statement=conn.prepareStatement(sql);
			statement.setInt(1, user_id);
			rs=statement.executeQuery();
			while (rs.next()) {
				GoodFav goodFav=new GoodFav();
				 goodFav.setFav_id(rs.getString("fav_id"));
				 goodFav.setGood_id(rs.getString("good_id"));
				 goodFav.setCreatedTime(rs.getString("createdTime"));
				 goodFav.setFav_status(rs.getInt("fav_status"));
				 goodFav.setGood_name(rs.getString("good_name"));
				 goodFavs.add(goodFav);
				
			}
			return goodFavs;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			NcutJdbcUtils.release(conn, statement, rs);
		}
		return null;
	}

	
}
