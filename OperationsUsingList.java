package com.demo.jdbc.dao;
import java.sql.*;
import com.demo.pojo.*;
import java.util.*;
import com.demo.dataconnect.DataConnect;
public class OperationsUsingList {
	private Connection con;
	private Scanner sc;
	private PreparedStatement stat;
	private List<Product> productlist;
	private List<Product> buylist;
	private List<User> userlist;
	public OperationsUsingList() throws SQLException {
		con = DataConnect.getConnection();
		productlist = new ArrayList<>();
		userlist = new ArrayList<>();
		buylist = new ArrayList<>();
		stat=con.prepareStatement("select * from product");
		ResultSet result = stat.executeQuery();
		sc = new Scanner(System.in);
		while(result.next()) {
			Product p = new Product();
			p.setProductid(result.getInt(1));
			p.setProductname(result.getString(2));
			p.setSellingprice(result.getDouble(3));
			p.setQuantity(result.getInt(4));
			p.setItemname(result.getString(5));
			p.setCategory(result.getString(6));
			p.setBuyingprice(result.getDouble(7));
			productlist.add(p);
		}
	}
	
	public void productList() {
		for(Product p : productlist) {
			System.out.println("product id is "+p.getProductid());
			System.out.println("Product name is "+p.getProductname());
			System.out.println("selling price is "+p.getSellingprice());
			System.out.println("----------------------------------------");
		}
	}
	
	public void searchProduct(String name) {
		boolean found = false;
		for(Product p : productlist) {
			if(p.getProductname().equalsIgnoreCase(name)) {
				found = true;
			System.out.println("product id is "+p.getProductid());
			System.out.println("Product name is "+p.getProductname());
			
			System.out.println("selling price is "+p.getSellingprice());
			System.out.println("----------------------------------------");
		}
		}
		if(!found) {
			System.out.println("product not found ");
		}
	}
	
	public void searchById(int id) {
		boolean found = false;
		for(Product p : productlist) {
			if(p.getProductid()==id) {
				found = true;
			System.out.println("product id is "+p.getProductid());
			System.out.println("Product name is "+p.getProductname());
			System.out.println("selling price is "+p.getSellingprice());
			System.out.println("----------------------------------------");
		}
		}
		if(!found) {
			System.out.println("product not found ");
		}
	}
	
	public void searchCategory(String name) {
		boolean found = false;
		for(Product p : productlist) {
			if(p.getCategory().equalsIgnoreCase(name)) {
				found = true;
			System.out.println("product id is "+p.getProductid());
			System.out.println("Product name is "+p.getProductname());
			System.out.println("selling price is "+p.getSellingprice());
			System.out.println("----------------------------------------");
		}
		}
		if(!found) {
			System.out.println("category not found ");
		}
	}
	
	public void calcualteAmount() {
		double amount = 0.0;
		for(Product p : productlist) {
			amount = p.getBuyingprice()+amount;
		}
		System.out.println("Total amount spend is "+amount);
	}
	
	public void displayProfits() {
		double amount = 0.0;
		for(Product p : productlist) {
			amount = amount+(p.getSellingprice()-p.getBuyingprice());
		}
		System.out.println("Total amount spend is "+amount);
	}
	
	public void insertProducts() throws SQLException {
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
			productlist.add(pro);
			if(result>0) {
				System.out.println("Product inserted");
			}
		}
	}
	
	public void priceLowToHigh() {
		Collections.sort(productlist, new SortByPrice());
		for(Product p : productlist) {
			System.out.println("product id is "+p.getProductid());
			System.out.println("Product name is "+p.getProductname());
			System.out.println("selling price is "+p.getSellingprice());
			System.out.println("----------------------------------------");
		}
	}
	
	public void calculateAmountSpend() {
		double amount=0.0;
		for(Product p : productlist) {
			amount=amount+p.getBuyingprice();
		}
		System.out.println("Total amount spend on buying is "+amount);
	}
	
	public void calculateProfits() {
		double profits=0.0;
		for(Product p : productlist) {
			profits=profits+(p.getSellingprice()-p.getBuyingprice());
		}
		System.out.println("Total profits are "+profits);
	}
	
	public void buyProducts(int coin,String name, String password) throws SQLException {
		String ch="y";
		while(ch.equalsIgnoreCase("y")) {
			System.out.println("Enter product name ");
			String productname = sc.next();
			boolean found = false;
			for(Product p : productlist) {
				if(p.getProductname().equalsIgnoreCase(productname)) {
					buylist.add(p);
					stat = con.prepareStatement("update product set quantity = quantity - 1 where productname = ?");
					stat.setString(1, productname);
					stat.executeUpdate();
					stat.close();
					found = true;
				}
			}
			if(!found) {
				System.out.println("Product not avaliable at the moment ");
			}
			System.out.println("Do you want to add more products to cart (y/n)");
			ch = sc.next();	
			int supercoins=0;
			if(!(coin<=0)) {
				System.out.println("you have "+coin+" supercoins. \nEnter how many supercoins u want to use");
				 supercoins = sc.nextInt();
			}
			if(supercoins<=coin) {
				double price = 0.0;
				for(Product p : buylist) {
					System.out.println("Product id is "+p.getProductid());
					System.out.println("Product name is "+p.getProductname());
					System.out.println("Price is "+p.getSellingprice());
					price = price + p.getSellingprice();
					
				}
				double bonus = supercoins /20;
				price = price - bonus;
				System.out.println("Total amount to pay "+price);
				
				stat = con.prepareStatement("update user set welcomebonus = welcomebonus - ? where username = ?");
				stat.setInt(1, supercoins);
				stat.setString(2,name);
				stat.executeUpdate();
			}
			else {
				System.out.println("Insufficient coins.");
			}
			stat.close();
		}
	}
}
