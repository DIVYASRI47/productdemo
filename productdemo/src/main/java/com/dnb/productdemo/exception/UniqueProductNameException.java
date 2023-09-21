package com.dnb.productdemo.exception;

public class UniqueProductNameException extends Exception {

	
	public UniqueProductNameException(String msg) {
		// TODO Auto-generated constructor stub
		super(msg);
		
	}

	public String toString() {
		return super.toString()+super.getMessage();
	}

}
