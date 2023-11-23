package com.demo.jdbc.dao;
import java.util.*;

import com.demo.dataconnect.DataConnect;

import java.sql.*;
public class UserOperations {
	private Connection con;
	private Scanner sc;
	private PreparedStatement stat;
	public UserOperations() throws SQLException {
		con=DataConnect.getConnection();
		sc=new Scanner(System.in);
	}
	
	public void viewProducts() throws SQLException {
		stat=con.prepareStatement("select * from product");
		ResultSet result = stat.executeQuery();
		while(result.next()) {
			System.out.println("product id is "+result.getInt(1));
			System.out.println("product name is "+result.getString(2));
			System.out.println("selling price is "+result.getDouble(3));
		}
	}

	public void searchById(int id) throws SQLException {
		stat=con.prepareStatement("select * from product where productid= ? ");
		stat.setInt(1, id);
		ResultSet result = stat.executeQuery();
		if(result.next()) {
			System.out.println("product id is "+result.getInt(1));
			System.out.println("product name is "+result.getString(2));
			System.out.println("selling price is "+result.getDouble(3));
		}
	}

	public void searchByName(String name) throws SQLException{
		stat=con.prepareStatement("select * from product where productname= ? ");
		stat.setString(1, name);
		ResultSet result = stat.executeQuery();
		if(result.next()) {
			System.out.println("product id is "+result.getInt(1));
			System.out.println("product name is "+result.getString(2));
			System.out.println("selling price is "+result.getDouble(3));
		}	
	}

	public void searchByPrice() throws SQLException {
		stat=con.prepareStatement("select productname,sellingprice from product order by sellingprice desc");
		ResultSet result = stat.executeQuery();
		while(result.next()) {
			System.out.println("product name is "+result.getString(1));
			System.out.println("selling price is "+result.getDouble(2));
		}
		
	}

	public void searchByCategory(String name) throws SQLException {
		stat=con.prepareStatement("select * from product where category = ?");
		stat.setString(1, name);
		ResultSet result = stat.executeQuery();
		while(result.next()) {
			System.out.println("product id is "+result.getInt(1));
			System.out.println("product name is "+result.getString(2));
			System.out.println("selling price is "+result.getDouble(3));
		}
	}
	
}
