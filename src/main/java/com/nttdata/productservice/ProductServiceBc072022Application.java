package com.nttdata.productservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@SpringBootApplication
@EnableReactiveMongoRepositories
public class ProductServiceBc072022Application {

    public static void main(String[] args) {
        SpringApplication.run(ProductServiceBc072022Application.class, args);
    }

}
