package com.dnb.productdemo.service;

import java.util.Optional;

import com.dnb.productdemo.dto.Product;
import com.dnb.productdemo.exception.IdNotFoundException;

public interface productService {

	public Product createProduct(Product product);

	public Optional<Product> getProductById(String productId);

	Iterable<Product> getAllProducts();

	public boolean checkIdExsitence(String productId) throws IdNotFoundException;

	//public boolean updateProductById(String productId);

	public boolean deleteProductById(String productId) throws IdNotFoundException;

}
