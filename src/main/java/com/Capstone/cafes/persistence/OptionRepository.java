package com.Capstone.cafes.persistence;

import com.Capstone.cafes.domain.Product_option_info;
import org.springframework.data.repository.CrudRepository;

public interface OptionRepository extends CrudRepository<Product_option_info, Integer>{

}
