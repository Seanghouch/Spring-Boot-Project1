package com.example.security.service;

import com.example.security.source.entity.ProductDetail;
import com.example.security.source.repo.ProductDetailRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductDetailService {

    private final ProductDetailRepo productDetailRepo;

    public ProductDetailService(ProductDetailRepo productDetailRepo) {
        this.productDetailRepo = productDetailRepo;
    }

    public Page<ProductDetail> findAll(Pageable pageable){
        return productDetailRepo.findAll(pageable);
    }

    public Object getByProductId(Long productId) {
        return productDetailRepo.findByProductId(productId);

    }

}
