package com.demo.pojo;

import java.util.List;

public class Product extends Item {
	private int productid;
	private String productname;
	private double sellingprice;
	private int quantity;
	public int getProductid() {
		return productid;
	}

	public void setProductid(int productid) {
		this.productid = productid;
	}
	public String getProductname() {
		return productname;
	}
	public void setProductname(String productname) {
		this.productname = productname;
	}
	public double getSellingprice() {
		return this.sellingprice;
	}
	public void setSellingprice(double sellingprice) {
		this.sellingprice = sellingprice + (sellingprice*0.5);
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}	
