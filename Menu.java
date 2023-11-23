package com.demo.client;
import com.demo.jdbc.dao.*;
import com.demo.service.UserServices;

import java.sql.SQLException;
import java.util.*;
public class Menu {
	AdminOperations ao;
	Scanner sc;
	UserServices us;
	
	public Menu() throws SQLException {
		ao = new AdminOperations();
		sc = new Scanner(System.in);
		us = new UserServices();
	}
	public void adminOperations() throws SQLException {
		System.out.println("Choose from below options ");
		String ch="y";
		int choice=0;
		while(ch.equalsIgnoreCase("y")) {
			System.out.println("1.List the products ");
			System.out.println("2.Search product by productid");
			System.out.println("3.List product by category ");
			System.out.println("4.Search product by name ");
			System.out.println("5.check total amount spend");
			System.out.println("6.Total profit amount");
			System.out.println("7.Insert products ");
			choice = sc.nextInt();
			switch(choice) {
			case 1:
				//ao.productsList();
				us.viewProducts();
				break;
			case 2:
				//ao.searchProduct();
				us.searchById();
				break;
			case 3:
				//ao.listByCategory();
				us.searchByCategory();
				break;
			case 4:
				//ao.searchProductByName();
				us.searchByName();
				break;
			case 5:
				//ao.calculateAmount();
				us.calculateAmount();
				break;
			case 6:
				//ao.displayProfits();
				us.calculateProfits();
				break;
			case 7:
				//ao.insertProducts();
				us.insertProduct();
				break;
			}
			System.out.println("Do you want to continue (y/n)");
			ch = sc.next();
		}
	}
	public void userOperations(int coin,String name,String password) throws SQLException {
		System.out.println("Choose from below options ");
		String ch="y";
		int choice=0;
		while(ch.equalsIgnoreCase("y")){
			System.out.println("1.List the products ");
			System.out.println("2.display products price from low to high ");
			System.out.println("3.search products from category ");
			System.out.println("4.seacrh products by name");
			System.out.println("5.search products by id ");
			System.out.println("6.Buy products ");
			choice=sc.nextInt();
			switch(choice) {
			case 1:
				us.viewProducts();
				break;
			case 2:
				us.sortByPrice();
				break;
			case 3:
				us.searchByCategory();
				break;
			case 4:
				us.searchByName();
				break;
			case 5:
				us.searchById();
				break;
			case 6:
				us.buyProducts(coin,name,password);
				break;
			}
			System.out.println("Do you want to continue (y/n)");
			ch=sc.next();
			
		}
	}
}
