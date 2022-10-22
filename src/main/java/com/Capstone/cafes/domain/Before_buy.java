package com.Capstone.cafes.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Before_buy {
	private Product product;
	private Long count;

	public Before_buy(Product product, Long count) {
        this.product = product;
        this.count = count;
    }
}
