
package com.Capstone.cafes.persistence;
import java.util.List;

import com.Capstone.cafes.domain.Product_option_info;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.Capstone.cafes.domain.Product;


public interface OptionInfoRepo extends CrudRepository<Product_option_info, Integer> {
	public List<Product_option_info> findByProduct(Product searchword);
	
	@Modifying
	@Query("select b from Product_option_info b where b.product=:product and b.size=:size and b.temp=:temp")
	public List<Product_option_info> findOptionbyinfos(Product product, int size, int temp); 
}


