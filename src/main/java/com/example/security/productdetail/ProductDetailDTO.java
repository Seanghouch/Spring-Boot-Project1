package com.example.security.productdetail;

import lombok.Data;


@Data
public class ProductDetailDTO {

    private Long productDetailId;
    private String uom;
    private Double unitPrice;
    private String imageUrl;
    private Long status;
    private Long productId;

}
