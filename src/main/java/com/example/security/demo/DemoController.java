package com.example.security.demo;

import com.example.security.source.entity.Product;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/demo-controller")
public class DemoController {

    @GetMapping
    public ResponseEntity<String> Hello() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Product product = Product.builder()
                .productId(1L)
                .productCode("Item-001")
                .productDescription("Description Item-001")
                .productNameEn("Item 001")
                .productNameKh("Item 002")
                .build();
        String result = objectMapper.writeValueAsString(product);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
