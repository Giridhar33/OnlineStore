package com.demo.jdbc.dao;

import java.util.Comparator;

import com.demo.pojo.Product;



public class SortByPrice implements  Comparator<Product>{

	@Override
	public int compare(Product o1, Product o2) {
		return Double.compare(o1.getSellingprice(), o2.getSellingprice());
	}

}
