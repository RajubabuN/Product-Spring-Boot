package com.service.product.Utility;

import com.service.product.entity.Product;
import com.service.product.model.ProductDto;

public class Utility {

	public static ProductDto covertEntityToDto(final Product data) {
		final ProductDto dto = new ProductDto();
		dto.setId(data.getId());
		dto.setName(data.getName());
		dto.setDescription(data.getDescription());
		dto.setPrice(data.getPrice());
		dto.setQuantityAvailable(data.getQuantityAvailable());
		return dto;
	}

	public static Product covertDtoToEntity(final ProductDto data) {
		final Product entity = new Product();
		entity.setName(data.getName());
		entity.setDescription(data.getDescription());
		entity.setPrice(data.getPrice());
		entity.setQuantityAvailable(data.getQuantityAvailable());
		return entity;
	}
}
