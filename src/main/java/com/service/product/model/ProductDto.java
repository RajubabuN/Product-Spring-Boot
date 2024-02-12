package com.service.product.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductDto implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("Id")
	private Long id;

	@JsonProperty("Name")
	private String name;

	@JsonProperty("Description")
	private String description;

	@JsonProperty("Price")
	private double price;

	@JsonProperty("Quantity Available")
	private int quantityAvailable;

	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(final double price) {
		this.price = price;
	}

	public int getQuantityAvailable() {
		return quantityAvailable;
	}

	public void setQuantityAvailable(final int quantityAvailable) {
		this.quantityAvailable = quantityAvailable;
	}
}
