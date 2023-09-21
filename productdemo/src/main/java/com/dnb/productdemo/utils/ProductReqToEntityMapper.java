package com.dnb.productdemo.utils;

import org.springframework.stereotype.Component;

import com.dnb.productdemo.dto.Product;
import com.dnb.productdemo.payload.request.productRequest;

@Component//to Scan our application for classes annotated with @component
public class ProductReqToEntityMapper {

	public Product getProductEntityObject(productRequest productrequest ) {

		Product prod = new Product();
		
		prod.setProductName(productrequest.getProductName());
		prod.setProductPrice(productrequest.getProductPrice());
		prod.setProductCategory(productrequest.getProductCategory());
		prod.setProductDescription(productrequest.getProductCategory());
		prod.setProductExpiryDate(productrequest.getProductExpiryDate());
		
		return prod;
	}
}
