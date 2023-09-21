package com.dnb.productdemo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dnb.productdemo.dto.Product;
import com.dnb.productdemo.exception.IdNotFoundException;
import com.dnb.productdemo.payload.request.productRequest;
import com.dnb.productdemo.repo.ProductRepository;
import com.dnb.productdemo.service.productService;
import com.dnb.productdemo.utils.ProductReqToEntityMapper;

import jakarta.validation.Valid;

@RestController/* allows to handle requests of REST APIs(get,post,delete,put */

@RequestMapping("/api/products")/* to map web requests to Spring Controller methods */
public class productContoller {

	@Autowired
	productService productservice;
	@Autowired
	ProductReqToEntityMapper mapper;
	@Autowired
	ProductRepository productRepository;

	@PostMapping("/create")//to create new product 
	ResponseEntity<?> createProduct(@Valid @RequestBody Product product) {

		try {
			
			Product prod1 = productservice.createProduct(product);

			return new ResponseEntity(prod1, HttpStatus.CREATED);//to get status report for a saved product 

		} catch (Exception e) {
			// TODO Auto-generated catch block

			return ResponseEntity.badRequest().body(e.getMessage());
		}

	}

	@GetMapping("/{productId}") // to get specific detials through productId

	public ResponseEntity<?> getProductById(@PathVariable("productId") String productId) throws IdNotFoundException {
		Optional<Product> optional = productservice.getProductById(productId);
		//check if the productId is present 
		if (optional.isPresent()) {
			return ResponseEntity.ok(optional.get());
		} else {

			throw new IdNotFoundException("productId not Found");
		}

	}

	@DeleteMapping("/{productId}")//to delete product details based on its productId

	public ResponseEntity<?> deleteProductById(@PathVariable("productId") String productId) throws IdNotFoundException {
		boolean delpro = productservice.checkIdExsitence(productId);
		if (delpro) {

			productservice.deleteProductById(productId);
		}

		if (delpro) {
			return ResponseEntity.noContent().build();
		}

		else
			throw new IdNotFoundException(" productId not found");

	}

	@GetMapping("/all")//to retrieve all products in prod(database) through postman

	public ResponseEntity<?> getAllProducts() {
		
		
		Iterable<Product> optional = productservice.getAllProducts();

		return ResponseEntity.ok(optional);

	}

	@PutMapping("/{productId}")// to update product details based on its unique productName with its productId

	public ResponseEntity<String> updateProductByProductName(@PathVariable("productId") String productId,
			@RequestBody productRequest product1) {

		Optional<Product> productOptional = productRepository.findById(productId);

		if (productOptional.isPresent()) {

			Product product = productOptional.get();

			// Check if the new name is unique among existing products

			Optional<Product> optional = productRepository.findByProductName(product1.getProductName());

			System.out.println(optional.isPresent());

			if (!optional.isPresent()) {

				product.setProductName(product1.getProductName());

				product.setProductDescription(product1.getProductDescription());

				product.setProductCategory(product1.getProductCategory());

				product.setProductPrice(product1.getProductPrice());

				product.setProductExpiryDate(product1.getProductExpiryDate());

				productRepository.save(product); // Save the updated product to the database

				return ResponseEntity.ok("Product Name Updated: " + product1.getProductName());

			} else {

				return ResponseEntity.badRequest().body("Product name must be unique.");

			}

		} else {

			return ResponseEntity.notFound().build();

		}

	}
}
