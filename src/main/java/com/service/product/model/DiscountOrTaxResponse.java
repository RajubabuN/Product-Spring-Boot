package com.service.product.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DiscountOrTaxResponse {

	@JsonProperty("Message")
	private String message;

	@JsonProperty("Product")
	private ProductDto product;

	public DiscountOrTaxResponse(final String message) {
		this.message = message;
	}

	public DiscountOrTaxResponse(final String message, final ProductDto product) {
		this.message = message;
		this.product = product;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(final String message) {
		this.message = message;
	}

	public ProductDto getProduct() {
		return product;
	}

	public void setProduct(final ProductDto product) {
		this.product = product;
	}
}
