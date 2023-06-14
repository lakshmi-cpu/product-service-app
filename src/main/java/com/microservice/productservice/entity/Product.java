package com.microservice.productservice.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="Product")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long productid;
	private String productname;
	private Long price;
	private Long quantity;
}
