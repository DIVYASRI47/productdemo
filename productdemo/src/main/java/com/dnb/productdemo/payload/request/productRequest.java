package com.dnb.productdemo.payload.request;

import java.time.LocalDate;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class productRequest {

	@Column(unique = true)
	private String productName;
	@Min(value = 0, message = "product price should  be non-negative")
	private long productPrice;
	private String productCategory;
	@NotNull(message = "product description should not empty")
	private String productDescription;
	@NotNull(message = "Expiry Date must be mentioned")
	private LocalDate productExpiryDate;

}
