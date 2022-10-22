package com.Capstone.cafes.domain;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import java.util.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Getter
@Setter
@ToString
@Entity

public class Orders {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer oId;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date orederDate;
	
	private String state;
	
	@ManyToOne
	@JoinColumn(name="id",nullable=false)
	private Customer customer;
	
	@ManyToOne
	@JoinColumn(name="pId",nullable=false)
	private Product product;
	
	
	
	private String info;
	private Integer count;
	private String season;
	private String day_time;
	
	
	
}
