package com.eshop.utils;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * 通过所属公司判断该用户连接哪个数据库;
 * @author Jane
 *
 */

public class ConnectionUtils {

	public static Connection selectConn(String company){
		Connection conn = null;
		
		try {
			if ("100001".equals(company)) {
				conn=NcutJdbcUtils.getConnection();
			}else if ("100002".equals(company)) {
				
				conn=DxtJdbcUtils.getConnection();
			}else {
				conn=DefaultjdbcUtils.getConnection();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

}
