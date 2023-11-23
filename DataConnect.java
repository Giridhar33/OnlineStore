package com.demo.dataconnect;

import java.sql.*;

public class DataConnect {
	private static Connection con;
       private  DataConnect() throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/onlinestore","root","mysql");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static Connection getConnection() throws SQLException {
		if(con==null) {
			DataConnect dataconnect = new DataConnect();
			return con;
		}
		else {
		return con;
		}
		
	}
}
