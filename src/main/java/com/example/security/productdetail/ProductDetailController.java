package com.example.security.productdetail;

import com.example.security.mapper.Mapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/productdetail")
public class ProductDetailController {

    private final ProductDetailService productDetailService;
    private final Mapper<ProductDetail, ProductDetailDTO> productDetailMapper;

    public ProductDetailController(ProductDetailService productDetailService, Mapper<ProductDetail, ProductDetailDTO> productDetailMapper) {
        this.productDetailService = productDetailService;
        this.productDetailMapper = productDetailMapper;
    }

    @GetMapping("/all")
    public Page<ProductDetail> getAllProductDetail(Pageable pageable){
        return productDetailService.findAll(pageable);
    }


    public List<ProductDetailDTO> getProductDetailByProductId(@PathVariable("productId") Long productId){
        List<ProductDetail> productDetails = productDetailService.getByProductId(productId);
        return productDetails.stream()
                .map(productDetailMapper::mapTo)
                .collect(Collectors.toList());
    }

}
