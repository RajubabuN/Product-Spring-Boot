package com.service.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.service.product.controller.ProductController;
import com.service.product.entity.Product;
import com.service.product.model.ProductDto;
import com.service.product.service.ProductService;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {

	@Mock
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@InjectMocks
	ProductController controller;

	@Mock
	ProductService service;

	@BeforeEach
	void setup() {
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Test
	void createProductTest() throws Exception {
		final String requestBody = "{\"Name\": \"Book\", \"Price\": 100, \"QuantityAvailable\": 100}";

		Mockito.when(service.createProduct(Mockito.any())).thenReturn(new Product());
		final MvcResult result = mockMvc
				.perform(post("/product/create").contentType(MediaType.APPLICATION_JSON).content(requestBody))
				.andExpect(status().isOk()).andReturn();

		final String responseContent = result.getResponse().getContentAsString();
		System.out.println("Create Product Response: " + responseContent);
	}

	@Test
	void getProductByIdTest() throws Exception {

		Mockito.when(service.getProductById(Mockito.anyLong())).thenReturn(new ProductDto());
		final MvcResult result = mockMvc.perform(get("/product/getById/1")).andExpect(status().isOk()).andReturn();

		final String responseContent = result.getResponse().getContentAsString();
		System.out.println("Create Product Response: " + responseContent);
	}

}
