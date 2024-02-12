package com.service.product.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.service.product.Utility.Utility;
import com.service.product.constants.Constants;
import com.service.product.entity.Product;
import com.service.product.interfaces.IProductService;
import com.service.product.model.DiscountOrTaxResponse;
import com.service.product.model.ProductDto;
import com.service.product.model.UpdatePrice;
import com.service.product.repository.ProductRepo;

@Service
public class ProductService implements IProductService {

	/** The Constant log. */
	private static final Logger log = LoggerFactory.getLogger(ProductService.class);

	private final ProductRepo productRepo;

	private final ValidationService validationService;

	public ProductService(final ProductRepo productRepo, final ValidationService validationService) {
		this.productRepo = productRepo;
		this.validationService = validationService;
	}

	@Override
	public ProductDto getProductById(final Long id) throws Exception {
		return productRepo.findById(id).map(Utility::covertEntityToDto)
				.orElseThrow(() -> new Exception(Constants.PRODUCT_NOT_FOUND));
	}

	@Override
	public Product createProduct(final ProductDto input) throws Exception {
		if (validationService.validatePayload(input)) {

			return productRepo.save(Utility.covertDtoToEntity(input));
		}
		throw new Exception("Provide valid product Details");
	}

	private String updateProduct(final ProductDto input, final Product entity) throws Exception {

		if (validationService.validatePayload(input)) {

			entity.setName(input.getName());
			entity.setDescription(input.getDescription());
			entity.setPrice(input.getPrice());
			entity.setQuantityAvailable(input.getQuantityAvailable());
			final Product dbRecordProduct = productRepo.save(entity);
			log.debug("Product updated successfully. Name:" + dbRecordProduct.getName() + " Id:"
					+ dbRecordProduct.getId());

			return Constants.PRODUCT_UPDATED_SUCCEFULLY;
		}

		throw new Exception("Provide valid product Details");
	}

	@Override
	public String updateProduct(final ProductDto input) throws Exception {
		if (input.getId() != 0) {

			return updateProduct(input, getProductDetails(input.getId()));

		}
		throw new Exception(Constants.PRODUCT_ID_NOT_VALID);
	}

	private Product getProductDetails(final Long id) throws Exception {
		final Optional<Product> product = productRepo.findById(id);

		if (product.isEmpty()) {
			throw new Exception(Constants.PRODUCT_NOT_FOUND);
		} else {
			return product.get();
		}
	}

	@Override
	public String deleteProductById(final Long id) throws Exception {
		if (id != 0) {
			productRepo.deleteById(id);
			return Constants.PRODUCT_DELETED_SUCCEFULLY;
		}
		throw new Exception(Constants.PRODUCT_ID_NOT_VALID);
	}

	@Override
	public DiscountOrTaxResponse updateProductPrice(final UpdatePrice input) throws Exception {
		if ((input.getDiscount() != 0) && (input.getTax() != 0)) {

			throw new Exception("Either Discount or Tax can be applied");
		}
		final Product productDetails = getProductDetails(input.getId());
		double price = 0;
		if (input.getDiscount() != 0) {

			price = getDiscountPrice(productDetails.getPrice(), input.getDiscount());

		} else {

			price = getTaxPrice(productDetails.getPrice(), input.getTax());
		}
		try {

			productDetails.setPrice(price);
			return new DiscountOrTaxResponse("Price updated successfully",
					Utility.covertEntityToDto(productRepo.save(productDetails)));
		} catch (final Exception ex) {

			return new DiscountOrTaxResponse(ex.getMessage());
		}
	}

	private double getDiscountPrice(final double currentPrice, final Integer discount) {

		final double discountAmount = (currentPrice * discount) / 100;
		return currentPrice - discountAmount;

	}

	private double getTaxPrice(final double currentPrice, final Integer discount) {

		final double discountAmount = (currentPrice * discount) / 100;
		return currentPrice + discountAmount;

	}
}
