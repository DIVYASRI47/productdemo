package com.dnb.productdemo.dto;

import java.time.LocalDate;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.validator.constraints.UniqueElements;

import com.dnb.productdemo.utils.productCustomIdGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Product {

	@Id

	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "prod_seq")

	@GenericGenerator(name = "prod_seq", strategy = "com.dnb.productdemo.utils.productCustomIdGenerator", parameters = {

			@Parameter(name = productCustomIdGenerator.INCREMENT_PARAM, value = "1"),

			@Parameter(name = productCustomIdGenerator.VALUE_PREFIX_PARAMETER, value = "Pr_"),

			@Parameter(name = productCustomIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d") })

	private String productId;
	@Column(unique = true)
	private String productName;
	@Min(value = 0, message = "product price should  be non-negative")
	private long productPrice;
	private String productCategory;
	@NotBlank(message = "product description should not empty")
	private String productDescription;
	@NotNull(message = "Expiry Date must be mentioned")
	private LocalDate productExpiryDate;

}
