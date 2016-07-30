package com.eshop.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * @author  JYM
 * 2015-11-23 下午8:52:57
 */
public class mydb01jdbcUtils {
	private static ComboPooledDataSource ds=null;
	static{
		
		ds=new ComboPooledDataSource("mydb01");
		
		
	}
	public static  DataSource getDataSource() {
		return ds;
		
	}
    public static Connection getConnection() throws SQLException {
    	return ds.getConnection();
		
	}

public static void  release(Connection conn,Statement st,ResultSet rs) {
		
		if(rs!=null){
			try{
				rs.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
			rs = null;

		}
		if(st!=null){
			try{
				st.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		if(conn!=null){
			try{
				conn.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
			
		}

		
		
	}
public static void main(String[] args) throws Exception {
	System.err.println(getConnection());
}

}
