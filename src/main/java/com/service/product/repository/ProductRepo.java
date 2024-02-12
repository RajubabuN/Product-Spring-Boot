package com.service.product.repository;

import org.springframework.data.repository.CrudRepository;

import com.service.product.entity.Product;

public interface ProductRepo extends CrudRepository<Product, Long> {

}
