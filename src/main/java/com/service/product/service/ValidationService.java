package com.service.product.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringJoiner;

import org.springframework.stereotype.Service;

import com.service.product.model.ProductDto;

import io.micrometer.common.util.StringUtils;

@Service
public class ValidationService {

	public boolean validatePayload(final ProductDto input) throws Exception {

		final Map<String, String> errorMessage = new HashMap<>();
		if (StringUtils.isBlank(input.getName())) {

			errorMessage.put("Name", "Product Name should not be empty");
		}
		if (input.getPrice() <= 0) {

			errorMessage.put("Price", "Price should be grater than 0");
		}

		if (errorMessage.isEmpty()) {
			return true;
		}
		final StringJoiner joiner = new StringJoiner(",");
		int i = 1;
		for (final Entry<String, String> map : errorMessage.entrySet()) {

			joiner.add(i + "-" + map.getValue());
			i++;
		}
		throw new Exception(joiner.toString());
	}
}
