package com.Capstone.cafes.domain;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import java.util.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(exclude="categories")
@Entity
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer pId;//PK
	private String productName;
	private Integer price;
	private boolean allSale;
	@ManyToOne
	@JoinColumn(name="categoryId",nullable=false)
	private Categories categories;
	
	@OneToMany(mappedBy="product",fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	private List<Product_option_info> infoList=new ArrayList<Product_option_info>();
	
	public void setCategories(Categories c) {
		this.categories = c;
		c.getProductList().add(this);
	}
	public ArrayList<Integer> getSize() {
        ArrayList<Integer> size = new ArrayList<Integer>();
        for (Product_option_info info : infoList) {
        	if(!(size.contains(info.getSize()))) {
        		size.add(info.getSize());      
        	}
       }
        return (size);
    }
	
	public HashMap<Integer, Integer> getSizemap() {
		HashMap<Integer, Integer> size = new HashMap<Integer, Integer>();
        for (Product_option_info info : infoList) {        	 
        	size.put(info.getSize(),info.getPrice());
       }
        
        return (size);
    }
	public ArrayList<Integer> getTemp() {
        ArrayList<Integer> temp = new ArrayList<Integer>();
        for (Product_option_info info : infoList) {
        	if(!(temp.contains(info.getTemp()))) {
        		temp.add(info.getTemp());      
        	}
       }
        return (temp);
    }
	
	public Product_option_info getInfo(int size, int temp) {
		for (Product_option_info info : infoList) {   
        	if(info.getSize().equals(size)) {
        		if(info.getTemp().equals(temp)) {
        			return info;
        		}
        	}
       }		
		return null;
	}
}
