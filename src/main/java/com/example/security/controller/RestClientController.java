package com.example.security.controller;


import com.example.security.auth.AuthenticationRequest;
import com.example.security.auth.AuthenticationResponse;
import com.example.security.dto.request.ListRequest;
import com.example.security.dto.response.ResponseData;
import com.example.security.service.RestClientService;
import com.example.security.source.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/restclient")
public class RestClientController {

    @Autowired
    private RestClientService RestClientService;

    private final String token = "houch eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJseXNlYW5naG91Y2g5OUBnbWFpbC5jb20iLCJpYXQiOjE3MDUyOTI1MzUsImV4cCI6MTcwNTM3ODkzNX0.vJjhPjhvxh03N3Z4oXNd1cXwMMnHBKbyw27ImA4hXHg";

    @PostMapping(headers = "action=auth")
    public ResponseEntity<AuthenticationResponse> getAuth(@RequestBody AuthenticationRequest request){
        AuthenticationResponse auth = RestClientService.getAuth(request);
        return ResponseEntity.ok(auth);
    }

    @PostMapping(headers = "action=order")
    public ResponseEntity<Object> restClient(@RequestBody Order order){
        Object data = RestClientService.getTestOrder(order);
        return ResponseEntity.ok(new ResponseData(200, data, null));
    }

    @PostMapping(value = "/{productId}", headers = "action=productid")
    public ResponseEntity<Object> restClientGetProductDetail(@PathVariable("productId") Long productId){
        Object data = RestClientService.getProductDetail(productId, token);
        return ResponseEntity.ok(data);
    }

    @PostMapping(headers = "action=list")
    public ResponseEntity<Object> restClientGetProduct(@RequestBody ListRequest request){
        Object data = RestClientService.getProduct(request, token);
        return ResponseEntity.ok(data);
    }

}
