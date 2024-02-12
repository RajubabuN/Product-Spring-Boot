package com.service.product.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfiguration {

	/**
	 * Open API.
	 *
	 * @return the open API
	 */
	@Bean
	OpenAPI openAPI() {
		return new OpenAPI()
				.info(new Info().title("Product Service").description("Manage customer information").version("1.0"));
	}

}
