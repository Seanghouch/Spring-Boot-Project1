package com.example.security.product;

import lombok.Data;

import java.util.Date;

@Data
public class ProductDTO {

    private Long productId;
    private String productCode;
    private String productNameEn;
    private String productNameKh;
    private String productDescription;

}
