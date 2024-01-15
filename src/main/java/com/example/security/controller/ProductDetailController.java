package com.example.security.controller;

import com.example.security.dto.response.ResponseData;
import com.example.security.service.ProductDetailService;
import com.example.security.source.entity.ProductDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/productdetail")
public class ProductDetailController {

    @Autowired
    private ProductDetailService productDetailService;

    @GetMapping("/all")
    public Page<ProductDetail> getAllProductDetail(Pageable pageable){
        return productDetailService.findAll(pageable);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Object> getProductDetailByProductId(@PathVariable("productId") Long productId){
        Object data = productDetailService.getByProductId(productId);
        return ResponseEntity.ok(new ResponseData(200, data , null));
    }

}
