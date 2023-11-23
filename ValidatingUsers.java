package com.demo.jdbc.dao;
import java.sql.*;
import com.demo.pojo.*;
import com.demo.userexceptions.InvalidAdmin;
import com.demo.client.Menu;
import com.demo.dataconnect.DataConnect;
import java.util.*;

public class ValidatingUsers {
	private PreparedStatement stat;
	private Connection con;
	List<User> userlist;
	Menu menu;
	
	public ValidatingUsers() throws SQLException {
		con = DataConnect.getConnection();
		userlist = new ArrayList<>();
		menu = new Menu();
	}
	
	public void validateAdmin(String username, String password) throws SQLException, InvalidAdmin {
		stat=con.prepareStatement("select * from user");
		ResultSet resultset = stat.executeQuery();
		while(resultset.next()) {
			User u = new User();
			u.setUsername(resultset.getString(1));
			u.setPassword(resultset.getString(2));
			u.setUsertype(resultset.getString(3));
			u.setMail(resultset.getString(4));
			u.setSupercoins(resultset.getInt(5));
			userlist.add(u);
		}
		boolean found = userlist.stream().anyMatch(str -> str.getUsername().equals(username) && 
				str.getPassword().equals(password) && str.getUsertype().equalsIgnoreCase("admin"));
		if(found) {
			System.out.println("Valid admin");
			menu.adminOperations();
		}
		else {
			
			boolean userfound = userlist.stream().anyMatch(str -> str.getUsername().equals(username)
					&& str.getPassword().equals(password) && str.getUsertype().equalsIgnoreCase("customer"));
			if(userfound) {
				System.out.println("Valid customer");
				userlist.stream().filter(u1->u1.getUsername().equals(username) && u1.getPassword().equals(password)).forEach(u->
				{
					int coin=u.getSupercoins();
					try {
						menu.userOperations(coin,username,password);
					} catch (SQLException e) {
						e.printStackTrace();
					}
				});
				
			}
			else {
				throw new InvalidAdmin();
			}
		}	
	}
}
