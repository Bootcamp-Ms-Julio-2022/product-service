package com.nttdata.productservice.controller;

import com.nttdata.productservice.entity.Product;
import com.nttdata.productservice.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Locale;

@Slf4j
@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    // -------------------Retrieve all products

    @GetMapping
    public Flux<Product> retrieveAll() {
        log.info("Retrieving all products");
        return productService.findAll();
    }

    // -------------------Retrieve single product by category

    @GetMapping("/{category}")
    public Mono<ResponseEntity<Product>> retrieveByCategory(@PathVariable("category") String category) {
        log.info("Retrieving product with category: " + category);
        Mono<Product> product = productService.findProductByCategory(category);
        return product.map(p -> ResponseEntity.ok(p))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    // -------------------Create a product

    @PostMapping
    public Mono<Product> save(@RequestBody Product product) {
        log.info("Registering new product - category: " + product.getProductCategory() + ", type: " + product.getProductType());
        return productService.save(product);
    }

    // -------------------Update a product

    @PutMapping
    public Mono<ResponseEntity<Product>> update(@RequestBody Product product) {
        log.info("Updating product with id: " + product.getId());
        return productService.update(product)
                .map(updatedProduct -> ResponseEntity.ok(updatedProduct))
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }

    // -------------------Delete a product

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> delete(@PathVariable("id") String id) {
        log.info("Deleting product with id: " + id);
        return productService.delete(id)
                .map( r -> ResponseEntity.ok().<Void>build())
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}
