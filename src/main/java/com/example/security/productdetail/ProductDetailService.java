package com.example.security.productdetail;

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

    public List<ProductDetail> getByProductId(Long productId) {
        return productDetailRepo.findByProductId(productId);
    }

}
