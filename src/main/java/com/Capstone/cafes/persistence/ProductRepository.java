package com.Capstone.cafes.persistence;

import org.springframework.data.repository.CrudRepository;

import com.Capstone.cafes.domain.Product;

public interface ProductRepository extends CrudRepository<Product,Integer> {
	public Product findByProductName(String searchword);
	
}
