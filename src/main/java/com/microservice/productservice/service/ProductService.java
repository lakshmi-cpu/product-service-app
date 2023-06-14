package com.microservice.productservice.service;

import com.microservice.productservice.request.ProductRequest;
import com.microservice.productservice.response.ProductResponse;

public interface ProductService {
	
	long addProduct(ProductRequest productRequest);
	ProductResponse getProductById(long productId);
	void reduceQuantity(long productid, long quantity);
	public void deleteProductById(long productId);
	

}
