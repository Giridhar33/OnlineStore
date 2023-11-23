package com.demo.client;
import java.sql.SQLException;
import com.demo.service.Validation;
import com.demo.userexceptions.InvalidAdmin;

public class Main {

	public static void main(String[] args) {
		try {
			Validation va = new Validation();
			va.options();
		} catch (SQLException | InvalidAdmin e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}
}
