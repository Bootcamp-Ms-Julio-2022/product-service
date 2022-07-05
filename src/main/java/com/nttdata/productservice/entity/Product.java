package com.nttdata.productservice.entity;

import com.nttdata.productservice.entity.enums.ProductCategory;
import com.nttdata.productservice.entity.enums.ProductType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "product")
public class Product {

    @Id
    private String id;

    private ProductType productType;

    private ProductCategory productCategory;

    private String state;

    private Date createdAt;

    public ProductCategory regulateProductCategory(String input) {
        switch (input) {
            case "cuenta_ahorro":
            case "ahorro":
            case "cuentaahorro":
                return ProductCategory.CUENTA_BANCARIA_AHORRO;
            case "cuenta_corriente":
            case "corriente":
            case "cuentacorriente":
                return ProductCategory.CUENTA_BANCARIA_CUENTA_CORRIENTE;
            case "plazo_fijo":
            case "cuenta_plazo_fijo":
            case "cuentaplazofijo":
            case "plazofijo":
                return ProductCategory.CUENTA_BANCARIA_PLAZO_FIJO;
            case "credito_personal":
            case "creditopersonal":
                return ProductCategory.CREDITO_PERSONAL;
            case "credito_empresarial":
            case "creditoempresarial":
                return ProductCategory.CREDITO_EMPRESARIAL;
            case "tarjeta_credito":
            case "tarjetacredito":
            case "tarjeta":
                return ProductCategory.TARJETA_DE_CREDITO;
        }
        return null;
    }

}
