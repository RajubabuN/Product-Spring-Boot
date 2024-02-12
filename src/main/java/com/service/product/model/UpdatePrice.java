package com.service.product.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UpdatePrice implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("ProductId")
	private Long id;

	@JsonProperty("Discount Percentage")
	private Integer discount;

	@JsonProperty("Tax Rate")
	private Integer tax;

	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public Integer getDiscount() {
		return discount;
	}

	public void setDiscount(final Integer discount) {
		this.discount = discount;
	}

	public Integer getTax() {
		return tax;
	}

	public void setTax(final Integer tax) {
		this.tax = tax;
	}
}
