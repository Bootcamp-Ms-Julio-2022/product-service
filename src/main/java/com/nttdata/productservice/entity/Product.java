package com.nttdata.productservice.entity;

import com.nttdata.productservice.entity.enums.ProductCategory;
import com.nttdata.productservice.entity.enums.ProductType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document(collection = "product")
public class Product {

    @Id
    private String id;

    private ProductType productType;

    private ProductCategory productCategory;

    private String state;

    private Date createdAt;

}
