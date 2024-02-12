package com.service.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.service.product.constants.Constants;
import com.service.product.entity.Product;
import com.service.product.model.ProductDto;
import com.service.product.model.UpdatePrice;
import com.service.product.repository.ProductRepo;
import com.service.product.service.ProductService;
import com.service.product.service.ValidationService;

class ProductServiceTest {

	@InjectMocks
	ProductService service;

	@Mock
	ProductRepo repo;

	@Mock
	ValidationService validateService;

	@BeforeEach
	void setUp() {

		MockitoAnnotations.openMocks(this);
	}

	ProductDto producuDto() {
		final ProductDto dto = new ProductDto();
		dto.setName("FictionBook");
		dto.setDescription("Book Disc");
		dto.setPrice(100);
		dto.setQuantityAvailable(10);
		return dto;
	}

	Product product() {

		final Product entity = new Product();
		entity.setId(1l);
		entity.setName("FictionBook");
		entity.setDescription("Book Disc");
		entity.setPrice(100);
		entity.setQuantityAvailable(10);
		return entity;
	}

	@Test
	void createProductTest() throws Exception {

		Mockito.when(validateService.validatePayload(Mockito.any())).thenReturn(true);
		Mockito.when(repo.save(Mockito.any())).thenReturn(product());
		assertEquals(producuDto().getPrice(), service.createProduct(producuDto()).getPrice());
	}

	@Test
	void createProductValidationFailureTest() throws Exception {

		Mockito.when(validateService.validatePayload(Mockito.any())).thenReturn(false);
		assertThrows(Exception.class, () -> service.createProduct(producuDto()));
	}

	@Test
	void updateProductTest() throws Exception {

		Mockito.when(validateService.validatePayload(Mockito.any())).thenReturn(true);
		Mockito.when(repo.findById(Mockito.anyLong())).thenReturn(Optional.of(product()));
		Mockito.when(repo.save(Mockito.any())).thenReturn(product());
		assertEquals(producuDto().getPrice(), service.createProduct(producuDto()).getPrice());
	}

	@Test
	void deleteProductTest() throws Exception {

		Mockito.when(repo.findById(Mockito.anyLong())).thenReturn(Optional.of(product()));
		doNothing().when(repo).deleteById(Mockito.anyLong());
		assertEquals(Constants.PRODUCT_DELETED_SUCCEFULLY, service.deleteProductById(1l));
	}

	@Test
	void applyDiscountTest() throws Exception {

		final UpdatePrice dto = new UpdatePrice();
		dto.setId(1l);
		dto.setDiscount(10);
		dto.setTax(0);
		Mockito.when(repo.findById(Mockito.anyLong())).thenReturn(Optional.of(product()));
		Mockito.when(repo.save(Mockito.any())).thenReturn(product());
		assertEquals(producuDto().getPrice(), service.updateProductPrice(dto).getProduct().getPrice());
	}

}
