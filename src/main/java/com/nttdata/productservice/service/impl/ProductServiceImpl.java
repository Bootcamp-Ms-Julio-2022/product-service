package com.nttdata.productservice.service.impl;

import com.nttdata.productservice.entity.Product;
import com.nttdata.productservice.entity.enums.ProductCategory;
import com.nttdata.productservice.repository.ProductRepository;
import com.nttdata.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Flux<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Flux<Product> findAllByProductCategory(ProductCategory productCategory) {
        return productRepository.findAllByProductCategory(productCategory);
    }

    @Override
    public Mono<Product> findById(String id) {
        return productRepository.findById(id);
    }

    @Override
    public Mono<Product> save(Product product) {
        product.setCreatedAt(new Date());
        return productRepository.save(product);
    }

    @Override
    public Mono<Product> update(String id, Product product) {
        return productRepository.findById(id)
                .flatMap(p -> {
                    p.setProductType(product.getProductType());
                    p.setProductCategory(product.getProductCategory());
                    p.setState(product.getState());
                    return productRepository.save(p);
                });
    }

    @Override
    public Mono<Product> delete(String id) {
        return productRepository.findById(id)
                .flatMap(p -> productRepository.delete(p)
                        .then(Mono.just(p)));
    }
}
