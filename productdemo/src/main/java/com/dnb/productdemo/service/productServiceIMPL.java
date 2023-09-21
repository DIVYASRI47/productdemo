package com.dnb.productdemo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dnb.productdemo.dto.Product;
import com.dnb.productdemo.exception.IdNotFoundException;
import com.dnb.productdemo.repo.ProductRepository;

@Service
public class productServiceIMPL implements productService {

	@Autowired
	ProductRepository productRepository;
	
	@Override
	public Product createProduct(Product product) {
		// TODO Auto-generated method stub
		return productRepository.save(product);
	}

	@Override
	public Optional<Product> getProductById(String productId) {
		// TODO Auto-generated method stub
		return productRepository.findById(productId);
	}

	@Override
	public Iterable<Product> getAllProducts() {
		// TODO Auto-generated method stub
		return productRepository.findAll();
	}

	@Override
	public boolean checkIdExsitence(String productId) throws IdNotFoundException {
		// TODO Auto-generated method stub
		if( productRepository.existsById(productId)==true) 
			
			return true;
		else
			throw new IdNotFoundException(productId);
		 
		 
		}

//	@Override
//	public boolean updateProductById(String productId) {
//		// TODO Auto-generated method stub
//		return true;
//	}

	@Override
	public boolean deleteProductById(String productId)throws IdNotFoundException {
		// TODO Auto-generated method stub
		if(productRepository.existsById(productId)) {
			
			 productRepository.deleteById(productId);
		}
			
		else
			throw new IdNotFoundException(productId);
		
		return false;
		 
	}

	

}
