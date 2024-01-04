package com.example.security.mapper.impl;

import com.example.security.mapper.Mapper;
import com.example.security.productdetail.ProductDetail;
import com.example.security.productdetail.ProductDetailDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ProductDetailMapper implements Mapper<ProductDetail, ProductDetailDTO> {

    private final ModelMapper modelMapper;

    public ProductDetailMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public ProductDetailDTO mapTo(ProductDetail productDetail) {
        return modelMapper.map(productDetail, ProductDetailDTO.class);
    }

    @Override
    public ProductDetail mapFrom(ProductDetailDTO productDetailDTO) {
        return modelMapper.map(productDetailDTO, ProductDetail.class);
    }
}
