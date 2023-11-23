package com.demo.service;

import java.util.Scanner;

import com.demo.client.Menu;
import com.demo.dataconnect.DataConnect;
import com.demo.jdbc.dao.ValidatingUsers;
import com.demo.pojo.User;
import com.demo.userexceptions.InvalidAdmin;

import java.sql.*;

public class Validation {
	Scanner sc;
	ValidatingUsers validate;
	private Connection con;
	private PreparedStatement stat;
	Menu menu;

	public Validation() throws SQLException {
		sc = new Scanner(System.in);
		con=DataConnect.getConnection();
		validate = new ValidatingUsers();
		menu = new Menu();
	}
	
	public void options() throws SQLException, InvalidAdmin {
		System.out.println("1.New user");
		System.out.println("2.Existing user");
		int ch=sc.nextInt();
		switch(ch) {
		case 1:
			newUser();
			break;
		case 2:
			validateUser();
			break;
		}

	}
	public void validateUser() throws SQLException, InvalidAdmin {
		System.out.println("Enter username ");
		String customer=sc.next();
		System.out.println("Enter password ");
		String password = sc.next();
		validate.validateAdmin(customer,password);
	}
	public void newUser() throws SQLException {
		System.out.println("Enter username");
		String name = sc.next();
		System.out.println("Enter password");
		String password = sc.next();
		System.out.println("Enter email");
		String mail = sc.next();
		stat=con.prepareStatement("insert into user values(?,?,?,?,?)");
		stat.setString(1, name);
		stat.setString(2, password);
		stat.setString(3, "customer");
		stat.setString(4, mail);
		stat.setInt(5, 100);
		int result = stat.executeUpdate();
		int bonus = 100;
		if(result>0) {
			System.out.println("congracts you won 100 super coins ");
		}
		
		menu.userOperations(100,name,password);
		
	}
	
}
