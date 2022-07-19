package com.nttdata.productservice.repository;

import com.nttdata.productservice.entity.Product;
import com.nttdata.productservice.entity.enums.ProductCategory;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface ProductRepository extends ReactiveMongoRepository<Product, String> {

    Mono<Product> findProductByProductCategory(String productCategory);

    Flux<Product> findAllByProductCategory(String productCategory);
}
