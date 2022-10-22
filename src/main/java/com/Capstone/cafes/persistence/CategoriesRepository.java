package com.Capstone.cafes.persistence;

import com.Capstone.cafes.domain.Categories;
import org.springframework.data.repository.CrudRepository;

public interface CategoriesRepository extends CrudRepository<Categories, Integer>{
//	public List<Categories> findByCategoryName(String searchword);
//
//	
//	@Query("select b from Categories b where b.categoryName=:name1 or b.categoryName=:name2")
//	public List<Categories> findbyDrinkMenu(String name1, String name2);
}
