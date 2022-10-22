package com.Capstone.cafes.persistence;
import java.util.List;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;

import com.Capstone.cafes.domain.Product;
import com.Capstone.cafes.domain.ProductImage;

@EnableJpaRepositories
public interface ProductImageRepository extends CrudRepository<ProductImage, Integer> {
	
	public List<ProductImage> findByProduct(Product searchword);
    
}
