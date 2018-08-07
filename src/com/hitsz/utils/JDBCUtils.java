package com.hitsz.utils;


import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JDBCUtils {
	/*private static String url = "jdbc:mysql://localhost:3306/user";
    private static String user = "root";
    private static String passwd = "1122";
    private static String driver = "com.mysql.jdbc.Driver";
   
	public static ComboPooledDataSource getDataSource() throws Exception {
		ComboPooledDataSource cpds = new ComboPooledDataSource();
		cpds.setDriverClass( driver);
        cpds.setJdbcUrl( url );
        cpds.setUser(user);                                  
        cpds.setPassword(passwd);  
        cpds.setMinPoolSize(5);                                     
        cpds.setAcquireIncrement(5);
        cpds.setMaxPoolSize(30);
        cpds.setMaxIdleTime(60);
		return cpds;
	}*/
	private static DataSource ds = new ComboPooledDataSource();
	private static ThreadLocal<Connection> tl = new ThreadLocal<Connection>();
 
	public static DataSource getDataSource() {
		return ds;
	}
 
	public static Connection getConnection() throws SQLException {
		Connection con = tl.get();
		if (con != null)
			return con;
		return ds.getConnection();
	}
 
	public static void beginTransaction() throws SQLException {
		Connection con = tl.get();
		if (con != null)
			throw new SQLException("已经开启了事务，不能重复开启！");
		con = ds.getConnection();
		con.setAutoCommit(false);
		tl.set(con);
	}
 
	public static void commitTransaction() throws SQLException {
		Connection con = tl.get();
		if (con == null)
			throw new SQLException("没有事务不能提交！");
		con.commit();
		con.close();
		con = null;
		tl.remove();
	}
 
	public static void rollbackTransaction() throws SQLException {
		Connection con = tl.get();
		if (con == null)
			throw new SQLException("没有事务不能回滚！");
		con.rollback();
		con.close();
		con = null;
		tl.remove();
	}
 
	public static void releaseConnection(Connection connection)
			throws SQLException {
		Connection con = tl.get();
		if (connection != con) {
			if (connection != null && !connection.isClosed()) {
				connection.close();
			}
		}
	}

}
