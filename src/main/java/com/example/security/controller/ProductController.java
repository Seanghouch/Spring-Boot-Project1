package com.example.security.controller;

import com.example.security.dto.request.ProductRequest;
import com.example.security.dto.request.ListRequest;
import com.example.security.dto.response.ResponseData;
import com.example.security.service.ProductService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/products")
@Slf4j
public class ProductController {
    @Autowired
    private ProductService productService;


    @PostMapping(headers = "action=list")
    public ResponseEntity<Object> list(@Valid @RequestBody ListRequest request) throws Exception{
        Object data = productService.list(request);
        return ResponseEntity.ok(new ResponseData(200, data, null));
    }

    @PostMapping(headers = "action=save")
    public ResponseEntity<Object> save(@Valid @RequestBody ProductRequest request) throws Exception{
        Object data = productService.save(request);
        return ResponseEntity.ok(new ResponseData(200, data, "data has been save."));
    }

    @PostMapping(headers = "action=update")
    public ResponseEntity<Object> update(@Valid @RequestBody ProductRequest request) throws Exception{
        Object data = productService.update(request);
        return ResponseEntity.ok(new ResponseData(200, data, "data has been update."));
    }

    @PostMapping(headers = "action=delete")
    public ResponseEntity<Object> delete(@RequestBody List<Long> productId){
        Object p = productId;
        System.out.println(p);
        return null;
    }

}
