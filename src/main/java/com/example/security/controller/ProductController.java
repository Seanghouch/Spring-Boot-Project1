package com.example.security.controller;

import com.example.security.dto.request.ProductRequest;
import com.example.security.dto.request.ListRequest;
import com.example.security.dto.response.ResponseData;
import com.example.security.service.ProductService;
import com.example.security.source.entity.Product;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/all")
    public Page<Product> getAllProduct(Pageable pageable) {
        return productService.findAll(pageable);
    }

//    @PostMapping(value = "/list", headers = "action=list")
//    public String returnString() throws Exception{
//        return "12312";
//    }

    @PostMapping(headers = "action=list")
    public ResponseEntity<Object> getAllProducts(){
        Object data = productService.list();
        return ResponseEntity.ok(data);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Object> getProductById(@PathVariable("id") Long id){
        Object data = productService.getProductById(id);
        return ResponseEntity.ok(data);
    }

//    @GetMapping("/ids")
//    public ResponseEntity<Object> getProductByIds(@RequestBody() ListRequest request){
//        Object data = productService.getProductByIds(request.getProductId());
//        return ResponseEntity.ok(data);
//    }

    @PostMapping(value = "/create")
    public ResponseEntity<Object> save(@Valid @RequestBody ProductRequest request) throws Exception{
        Object data = productService.saveProduct(request);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Object> fullUpdate(@RequestBody ProductRequest request){
        Object data = productService.update(request);
        return ResponseEntity.ok(new ResponseData(200, data, null));
    }

    @PostMapping(headers = "action=show")
    public ResponseEntity<Object> show(@Valid @RequestBody ListRequest request) throws Exception{
        Object data = productService.show(request);
        return ResponseEntity.ok(new ResponseData(200, data, null));
    }

}
