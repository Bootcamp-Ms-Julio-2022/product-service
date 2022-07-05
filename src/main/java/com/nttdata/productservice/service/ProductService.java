package com.nttdata.productservice.service;

import com.nttdata.productservice.entity.Product;
import com.nttdata.productservice.entity.enums.ProductCategory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductService {

    Flux<Product> findAll();

    Flux<Product> findAllByProductCategory(ProductCategory productCategory);

    Mono<Product> findById(String id);

    Mono<Product> save(Product product);

    Mono<Product> update(String id, Product product);

    Mono<Product> delete(String id);
}
