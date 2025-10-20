package com.m4gpie.product_service.respository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.m4gpie.product_service.model.Product;

public interface ProductRepository extends MongoRepository<Product, String> {

}
