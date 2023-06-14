package com.microservice.productservice.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {
	
	private String productname;
	private Long productid;
	private Long price;
	private Long quantity;

}
