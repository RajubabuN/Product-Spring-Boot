package com.service.product.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.service.product.entity.Product;
import com.service.product.model.DiscountOrTaxResponse;
import com.service.product.model.ProductDto;
import com.service.product.model.UpdatePrice;
import com.service.product.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

	private final ProductService productService;

	public ProductController(final ProductService productService) {
		this.productService = productService;
	}

	@GetMapping("/getById/{id}")
	public ProductDto getProductById(@PathVariable final Long id) throws Exception {

		return productService.getProductById(id);
	}

	@PostMapping("/create")
	public Product createProduct(@RequestBody final ProductDto input) throws Exception {

		return productService.createProduct(input);
	}

	@PutMapping("/update")
	public String updateProduct(@RequestBody final ProductDto input) throws Exception {

		return productService.updateProduct(input);
	}

	@DeleteMapping("/deleteById/{id}")
	public String deleteProductById(@PathVariable final Long id) throws Exception {

		return productService.deleteProductById(id);
	}

	@PostMapping("/applyDiscountOrTax")
	public DiscountOrTaxResponse applyDiscountOrTax(@RequestBody final UpdatePrice input) throws Exception {

		return productService.updateProductPrice(input);
	}
}
