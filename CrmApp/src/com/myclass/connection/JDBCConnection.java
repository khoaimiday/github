package com.myclass.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnection {

	// Database
	// Add cmr_app?useSSL=false" de bo bao loi <MESSAGE: closing inbound before receiving peer's close_notify>

	private final static String url = "jdbc:mysql://localhost:3306/cmr_app?useSSL=false";
	private final static String username = "root";
	private final static String password = "pemonhon";

	public static Connection getConnection() {

		try {
			// Su dung class com.mysql.cj.jdbc.Driver de thuc hien ket noi database
			Class.forName("com.mysql.cj.jdbc.Driver");
			//Goi ham getConnection cua DriverManager de thuc hien ket noi
			//Ham nay tra ve doi tuong Connection
			return DriverManager.getConnection(url, username, password);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
