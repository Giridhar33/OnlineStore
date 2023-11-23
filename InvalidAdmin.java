package com.demo.userexceptions;

public class InvalidAdmin extends Exception{
	public String getMessage() {
		return "Invalid  user please enter a valid username and password";
	}
}
