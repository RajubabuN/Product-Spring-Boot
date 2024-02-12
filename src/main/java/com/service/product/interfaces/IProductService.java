package com.service.product.interfaces;

import org.springframework.web.bind.annotation.PathVariable;

import com.service.product.entity.Product;
import com.service.product.model.DiscountOrTaxResponse;
import com.service.product.model.ProductDto;
import com.service.product.model.UpdatePrice;

public interface IProductService {

	/**
	 * Gets the customer by id.
	 *
	 * @param id the id
	 * @return the customer by id
	 * @throws Exception the exception
	 */
	public ProductDto getProductById(final Long id) throws Exception;

	/**
	 * Creates the customer.
	 *
	 * @param input the input
	 * @return the string
	 * @throws Exception
	 * @throws CustomException the custom exception
	 */
	public Product createProduct(final ProductDto input) throws Exception;

	/**
	 * Update customer.
	 *
	 * @param input the input
	 * @return the string
	 * @throws Exception the exception
	 */
	public String updateProduct(final ProductDto input) throws Exception;

	/**
	 * Delete customer by id.
	 *
	 * @param id the id
	 * @return the string
	 * @throws Exception the exception
	 */
	public String deleteProductById(@PathVariable final Long id) throws Exception;

	/**
	 * Update product price.
	 *
	 * @param input the input
	 * @return the string
	 * @throws Exception the exception
	 */
	public DiscountOrTaxResponse updateProductPrice(final UpdatePrice input) throws Exception;
}
