package com.dnb.productdemo.exception;

public class IdNotFoundException extends Exception {

	public IdNotFoundException(String msg) {
		// TODO Auto-generated constructor stub
		super(msg);
		
	}

	public String toString() {
		return super.toString()+super.getMessage();
	}
		
	}


