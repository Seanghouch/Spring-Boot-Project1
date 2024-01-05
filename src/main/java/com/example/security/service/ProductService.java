package com.example.security.service;

import com.example.security.dto.request.ListRequest;
import com.example.security.dto.request.ProductRequest;
import com.example.security.dto.response.*;
import com.example.security.component.ObjectConverter;
import com.example.security.source.entity.Product;
import com.example.security.source.entity.ProductDetail;
import com.example.security.source.repo.ProductDetailRepo;
import com.example.security.source.repo.ProductRepo;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService extends BaseService {
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private ProductDetailRepo productDetailRepo;

    public Object list(ListRequest request) {
        Pageable pageable = getPage(request);
        Specification<Product> filter = filterColumn(request);
        Page<Product> list = productRepo.findAll(filter, pageable);
        ListResponse data = new ListResponse(list.getContent(), list.getTotalElements());
        data.setList(ObjectConverter.convertArrayToArrayObject(data.getList(), new TypeReference<List<ProductResponse>>(){}));
        return data;
    }

    @Transactional(rollbackFor = {ResponseStatusException.class, Exception.class})
    public Object save(ProductRequest request) {
        if (request.getProductDetail().isEmpty()){
            return new ResponseData(200, "At least have 1 Product Detail.",null);
        }
        Product product = Product.builder()
                .productCode(request.getProductCode())
                .productNameEn(request.getProductNameEn())
                .productNameKh(request.getProductNameKh())
                .productDescription(request.getProductDescription())
                .build();
        product = productRepo.save(product);
        this.saveProductDetail(product, request.getProductDetail());
        Product data = productRepo.findByProductId(product.getProductId()).orElseGet(Product::new);
        return ObjectConverter.convertArrayToArrayObject(data, new TypeReference<ProductResponse>() {});
    }

    public void saveProductDetail(Product product, List<ProductDetail> productDetails){
        for (ProductDetail ProductDetail: productDetails){
            ProductDetail detail = new ProductDetail();
            detail.setUom(ProductDetail.getUom());
            detail.setUnitPrice(ProductDetail.getUnitPrice());
            detail.setStatus(ProductDetail.getStatus());
            detail.setImageUrl(ProductDetail.getImageUrl());
            detail.setProductId(product.getProductId());
            productDetailRepo.save(detail);
        }
    }

    @Transactional(rollbackFor = {ResponseStatusException.class, Exception.class})
    public Object update(ProductRequest request) {
        if(!isExists(request.getProductId())){
            return "This request id not found.";
        }
        if (request.getProductDetail().isEmpty()){
            return "Update data error, At least have 1 Product Detail.";
        }
        Optional<Product> product = productRepo.findByProductId(request.getProductId());
        if (product.isPresent()){
            Product existingProduct = product.get();
            existingProduct.setProductCode(request.getProductCode());
            existingProduct.setProductNameEn(request.getProductNameEn());
            existingProduct.setProductNameKh(request.getProductNameKh());
            existingProduct.setProductDescription(request.getProductDescription());
            productRepo.save(existingProduct);
            this.updateProductDetail(request.getProductId(), request.getProductDetail());
            return ObjectConverter.convertArrayToArrayObject(existingProduct, new TypeReference<ProductResponse>() {});
        }else {
            return "No data for update";
        }
    }

    public void updateProductDetail(Long productId, List<ProductDetail> productDetails){
        int i = 0;
        List<Long> activeId = new ArrayList<>();
        for (ProductDetail productDetail: productDetails){
            ProductDetail detail = new ProductDetail();
            if (detail.getProductDetailId() != null) {
                detail = productDetailRepo.findByProductDetailId(productDetail.getProductDetailId()).orElseGet(ProductDetail::new);
            }
            detail.setProductDetailId(productDetail.getProductDetailId());
            detail.setUom(productDetail.getUom());
            detail.setUnitPrice(productDetail.getUnitPrice());
            detail.setStatus(productDetail.getStatus());
            detail.setImageUrl(productDetail.getImageUrl());
            detail.setProductId(productId);
            productDetailRepo.save(detail);
            activeId.add(detail.getProductDetailId());
            i++;
        }
        productDetailRepo.deleteByProductDetailIdNotIn(activeId, productId);
    }

    public Object delete(){
        return null;
    }

    public boolean isExists(Long productId) {
        return productRepo.existsByProductId(productId);
    }

}
