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
    public Mono<Product> findProductByCategory(String productCategory) {
        return productRepository.findProductByProductCategory(productCategory);
    }

    @Override
    public Mono<Product> save(Product product) {
        product.setCreatedAt(new Date());
        product.setState("enabled");
        return productRepository.save(product);
    }

    @Override
    public Mono<Product> update(Product product) {
        return productRepository.findById(product.getId())
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
