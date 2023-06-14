package com.microservice.productservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.productservice.entity.Product;
import com.microservice.productservice.exception.ProductServiceCustomException;
import com.microservice.productservice.repository.ProductRepository;
import com.microservice.productservice.request.ProductRequest;
import com.microservice.productservice.response.ProductResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import static org.springframework.beans.BeanUtils.copyProperties;

@Service
@RequiredArgsConstructor
@Log4j2
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	private final ProductRepository productRepository;

	@Override
	public long addProduct(ProductRequest productRequest) {
		log.info("ProductServiceImpl | addProduct is called");
		
		Product product=
				Product.builder()
				.productname(productRequest.getName())
				.quantity(productRequest.getQuantity())
				.price(productRequest.getPrice())
				.build();
		
		product=productRepository.save(product);
		
		log.info("ProductServiceImpl | addProduct | Product Created ");
		log.info("ProductServiceImpl | addProduct | Product Id: " +product.getProductid());
		
		return product.getProductid();
	}

	@Override
	public ProductResponse getProductById(long productId) {
	
		log.info("ProductServiceImpl | getProductId is called");
		log.info("ProductServiceImpl | getProductId | Get the product for productId: {} ", productId);
		
		Product product
		= productRepository.findById(productId)
		.orElseThrow(
				() -> new ProductServiceCustomException("Product with given id not found", "PRODUCT NOT FOUND"));
		
		ProductResponse productResponse=
				new ProductResponse();
		
		copyProperties(product, productResponse);
		
		log.info("ProductServiceImpl | getProductById | productResponse :" +productResponse.toString());
		
		
		
		return productResponse;
	}

	

	@Override
	public void reduceQuantity(long productid, long quantity) {
		// TODO Auto-generated method stub
		
		log.info("Reduce Quantity {} for Id: {}", quantity, productid);
		
		Product product=
				productRepository.findById(productid)
				.orElseThrow(()-> new ProductServiceCustomException(
						"Product with given id not found", "PRODUCT NOT FOUND"));
		if(product.getQuantity()<quantity)
		{
			throw new ProductServiceCustomException(
					
					"product does not have sufficient quantity", "INSUFFICIENT QUANTITY");
		}
		
		product.setQuantity(product.getQuantity()-quantity);
		productRepository.save(product);
		log.info("Product Quantity updated successfully");
		
	}

	@Override
	public void deleteProductById(long productId) {
		// TODO Auto-generated method stub
		log.info("product id: {}" , productId);
		if(!productRepository.existsById(productId))
		{
			log.info("Im in this loop {}", !productRepository.existsById(productId));
			throw new ProductServiceCustomException(
					"product with given with id: " +productId + "not found : " , "PRODUCT_NOT_FOUND"); 
		}
		log.info("Deleting product with id: {}", productId);
		productRepository.deleteById(productId);
		
	}

}
