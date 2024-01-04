package com.example.security.core;

import com.example.security.source.repo.ProductDetailRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CoreBase {

    public static ProductDetailRepo productDetailRepo;

    @Autowired
    public CoreBase(ProductDetailRepo productDetailRepo) {

        CoreBase.productDetailRepo = productDetailRepo;

    }
}
