package com.dnb.productdemo.advice;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.dnb.productdemo.exception.IdNotFoundException;

@ControllerAdvice
public class ProductAdvice {

	@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "invalid id provided") // it provides the customized reason
																					// message for exception status

	@ExceptionHandler(IdNotFoundException.class) // it handles exceptions by returning ResponseEntity with formatted
													// error details.

	/* provides status messages with suggestions for exceptions at postman */

	public void invalidProductIdExceptionHandler(IdNotFoundException e) {

//		Map<String,String> mp=new HashMap<>();
//		
//		mp.put("message", e.getMessage());
//		
//		mp.put("HttpStatus", HttpStatus.NOT_FOUND+"");
//		
//	return new ResponseEntity(mp,HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)

	/* to provides expcetional status messages as key,values pairs through mapping. */

	public ResponseEntity<Map<String, String>> ExceptionHandler(HttpRequestMethodNotSupportedException e)
			throws IOException {

		String given = e.getMethod();

		List<String> supported = List.of(e.getSupportedMethods());

		String error = given + "is not one of the supported Http methods(" + String.join(";", supported) + ")";

		Map<String, String> errorResponse = Map.of("error", error, "message", e.getLocalizedMessage(), "status",
				HttpStatus.METHOD_NOT_ALLOWED.toString());

		return new ResponseEntity<>(errorResponse, HttpStatus.METHOD_NOT_ALLOWED);

	}
}
