package com.Capstone.cafes.persistence;

import org.springframework.data.repository.CrudRepository;

import com.Capstone.cafes.domain.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Integer>{
//	public List<Customer> findByUserId(String searchword);
//	public List<Customer> findByRole(Integer num);
//	
}
