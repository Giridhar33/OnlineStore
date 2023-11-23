package com.demo.service;

import java.sql.SQLException;
import java.util.*;

import com.demo.jdbc.dao.OperationsUsingList;
import com.demo.jdbc.dao.UserOperations;

public class UserServices {
	UserOperations op;
	OperationsUsingList oplist;
	Scanner sc;
	public UserServices() throws SQLException {
		op = new UserOperations();
		oplist = new OperationsUsingList();
		sc = new Scanner(System.in);
	}
	public void viewProducts() throws SQLException {
		//op.viewProducts();
		oplist.productList();
	}
	
	public void searchById() throws SQLException {
		System.out.println("Enter product id you want to search ");
		int id = sc.nextInt();
		//op.searchById(id);
		oplist.searchById(id);
	}
	
	public void searchByName() throws SQLException{
		System.out.println("Enter product name you want to search ");
		String name = sc.next();
		//op.searchByName(name);
		oplist.searchProduct(name);
	}
	
	public void sortByPrice() throws SQLException {
		//op.searchByPrice();
		oplist.priceLowToHigh();
	}
	public void searchByCategory() throws SQLException {
		System.out.println("Enter category you want to search ");
		String name = sc.next();
		//op.searchByCategory(name);
		oplist.searchCategory(name);
	}
	public void buyProducts(int coin,String name,String password) throws SQLException {
		oplist.buyProducts(coin,name,password);
	}
	public void insertProduct() throws SQLException {
		oplist.insertProducts();
	}
	
	public void calculateProfits() {
		oplist.calculateProfits();
	}
	
	public void calculateAmount() {
		oplist.calcualteAmount();
	}
}
