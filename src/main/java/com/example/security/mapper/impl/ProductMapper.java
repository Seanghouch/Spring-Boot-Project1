package com.example.security.mapper.impl;

import com.example.security.mapper.Mapper;
import com.example.security.source.entity.Product;
import com.example.security.product.ProductDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper implements Mapper<Product, ProductDTO> {

    private final ModelMapper modelMapper;

    public ProductMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public ProductDTO mapTo(Product product) {
        return modelMapper.map(product, ProductDTO.class);
    }

    @Override
    public Product mapFrom(ProductDTO productResponse) {
        return modelMapper.map(productResponse, Product.class);
    }

}
