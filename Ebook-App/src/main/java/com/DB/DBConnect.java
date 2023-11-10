package com.DB;

import java.sql.Connection;
import java.sql.DriverManager;



public class DBConnect {

	private static Connection connection;
	public static Connection getConnection() {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/ebook-app";
			String user = "root";
			String password = "123456";
			return DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}
}
