package com.example.security.dto.request;

import com.example.security.productdetail.ProductDetail;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class ProductRequest {

    private Long productId;
    @NotEmpty(message = "required")
    private String productCode;
    @NotEmpty(message = "required")
    private String productNameEn;
    private String productNameKh;
    private String productDescription;

    private List<ProductDetail> productDetail = new ArrayList<>();

}
