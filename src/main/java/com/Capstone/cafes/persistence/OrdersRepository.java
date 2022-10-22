package com.Capstone.cafes.persistence;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.Capstone.cafes.domain.Before_buy;
import com.Capstone.cafes.domain.Customer;
import com.Capstone.cafes.domain.Orders;


public interface OrdersRepository extends CrudRepository<Orders, Long>{

//	@Query("select o.product.pId, COUNT(o.product) from Orders o where o.customer=:customer group by o.product order by COUNT(o.product) DESC ")
//	@Query("select o.product.pId, COUNT(o.product) from Orders o where o.customer=:customer group by o.product order by COUNT(o.product) DESC")
//	public List<Integer[]> findbyBestMenu(Customer customer);

//	@Query("select o.product from Orders o where o.customer=:customer ")
//	public List<Product> findbyBestMenu(Customer customer);
//	
	@Query("select new com.example.cafes.domain.Before_buy(o.product, COUNT(o.product)) from Orders o where o.customer=:customer group by o.product order by COUNT(o.product) DESC")
	public List<Before_buy> findbyBestMenu(Customer customer);
	
	@Query("select new com.example.cafes.domain.Before_buy(o.product, COUNT(o.product)) from Orders o group by o.product order by COUNT(o.product) DESC ")
	public List<Before_buy> findbyBestseller();
}
