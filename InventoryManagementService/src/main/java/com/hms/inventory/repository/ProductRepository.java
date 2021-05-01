package com.hms.inventory.repository;


import org.springframework.stereotype.Repository;

import com.hms.inventory.model.ProductModel;

import org.springframework.data.mongodb.repository.MongoRepository;


@Repository
public interface ProductRepository extends MongoRepository<ProductModel, Long> {

}
