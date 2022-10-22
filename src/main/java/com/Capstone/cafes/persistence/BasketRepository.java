	package com.Capstone.cafes.persistence;
	import java.util.List;

    import org.springframework.data.repository.CrudRepository;

    import com.Capstone.cafes.domain.Basket;
	import com.Capstone.cafes.domain.Customer;
	public interface BasketRepository extends CrudRepository<Basket, Integer> {
		public List<Basket> findByCustomer(Customer searchword);
			
	}
