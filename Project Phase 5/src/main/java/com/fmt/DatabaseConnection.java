package com.fmt;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This class is used to establish a database connection.
 *
 * @author Cong Nguyen
 * @author Yashraj Purbey
 *
 */
public class DatabaseConnection {

	/**
	 * This method is used to open the connection to database.
	 * 
	 * @return
	 */
	public static Connection getConn() {
		
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DatabaseInfo.URL, DatabaseInfo.USERNAME, DatabaseInfo.PASSWORD);

		} catch (SQLException e) {
			System.out.println("Wrong password, or user name, or URL");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		return conn;
	}

	/**
	 * This method is used to close the result sets, prepare statement, and
	 * connection to database.
	 * 
	 * @param conn
	 * @param ps
	 * @param rs
	 */
	public static void getClose(ResultSet rs, PreparedStatement ps, Connection conn) {

		try {
			if (rs != null && !rs.isClosed())
				rs.close();
			if (ps != null && !ps.isClosed())
				ps.close();
			if (conn != null && !conn.isClosed())
				conn.close();
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
