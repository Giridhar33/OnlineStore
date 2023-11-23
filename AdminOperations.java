package com.demo.jdbc.dao;
import java.sql.*;
import java.util.*;
import com.demo.dataconnect.DataConnect;
import com.demo.pojo.Product;
public class AdminOperations {
	private Connection con;
	private PreparedStatement stat;
	Scanner sc;
	public AdminOperations() throws SQLException {
		con = DataConnect.getConnection();
		sc = new Scanner(System.in);
	}
	
	public void productsList() throws SQLException {
		stat=con.prepareStatement("select * from product");
		ResultSet result = stat.executeQuery();
		while(result.next()) {
			System.out.println("product id is "+result.getInt(1));
			System.out.println("product name is "+result.getString(2));
			System.out.println("selling price is "+result.getDouble(3));
		}
	}
	
	public void searchProduct() throws SQLException {
		System.out.println("Enter product id you want to search");
		int id = sc.nextInt();
		stat=con.prepareStatement("select * from product where productid = "+id);
		ResultSet result = stat.executeQuery();
		if(result.next()) {
			System.out.println("product id is "+result.getInt(1));
			System.out.println("product name is "+result.getString(2));
			double selling = result.getDouble(7);
			System.out.println("selling price is "+selling);
		}
	}
	
	public void listByCategory() throws SQLException {
		System.out.println("Enter category you want to search ");
		String category = sc.next();
		stat=con.prepareStatement("select * from product where category  = ?");
		stat.setString(1, category);
		ResultSet result= stat.executeQuery();
		while(result.next()) {
			System.out.println("product id is "+result.getInt(1));
			System.out.println("product name is "+result.getString(2));
			System.out.println("selling price is "+result.getDouble(3));
		}
	}
	
	public void searchProductByName() throws SQLException {
		System.out.println("Enter product name to search ");
		String name = sc.next();
		stat=con.prepareStatement("select * from product where productname = ?");
		stat.setString(1, name);
		ResultSet result = stat.executeQuery();
		if(result.next()) {
			System.out.println("product id is "+result.getInt(1));
			System.out.println("product name is "+result.getString(2));
			System.out.println("selling price is "+result.getDouble(3));
		}
	}
	
	public void calculateAmount() throws SQLException {
		stat=con.prepareStatement("select buyingprice from product");
		ResultSet result = stat.executeQuery();
		double total =0.0;
		while(result.next()) {
			total=total + result.getDouble(1);
		}
		System.out.println("Total amount is "+total);
	}
	
	public void displayProfits() throws SQLException {
		stat=con.prepareStatement("select buyingprice,sellingprice from product ");
		ResultSet result = stat.executeQuery();
		double profit = 0.0;
		while(result.next()) {
			profit = result.getDouble(2)-result.getDouble(1);
		}
		System.out.println("Total profit is "+profit);
	}
	
	public void insertProducts() throws SQLException
	{
		System.out.println("How many products do you want to insert ");
		int size = sc.nextInt();
		for(int i=0;i<size;i++) {
			Product pro = new Product();
			stat=con.prepareStatement("insert into product values(?,?,?,?,?,?,?)");
			System.out.println("Enter product id ");
			pro.setProductid(sc.nextInt());
			System.out.println("Enter product name ");
			String name = sc.next();
			pro.setProductname(name);
			pro.setItemname(name);
			System.out.println("Enter buying price ");
			double price = sc.nextDouble();
			pro.setBuyingprice(price);
			pro.setSellingprice(price);
			System.out.println("Enter quantity ");
			pro.setQuantity(sc.nextInt());
			System.out.println("Enter category ");
			pro.setCategory(sc.next());
			stat.setInt(1, pro.getProductid());
			stat.setString(2,pro.getProductname());
			stat.setDouble(3, pro.getSellingprice());
			stat.setInt(4, pro.getQuantity());
			stat.setString(5, pro.getItemname());
			stat.setString(6, pro.getCategory());
			stat.setDouble(7, pro.getBuyingprice());
			int result = stat.executeUpdate();
			if(result>0) {
				System.out.println("Product inserted");
			}
			
		}
	}
	
}
