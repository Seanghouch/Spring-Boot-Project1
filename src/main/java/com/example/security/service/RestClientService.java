package com.example.security.service;

import com.example.security.auth.AuthenticationRequest;
import com.example.security.auth.AuthenticationResponse;
import com.example.security.dto.request.ListRequest;
import com.example.security.dto.response.ProductResponse;
import com.example.security.dto.response.ResponseData;
import com.example.security.source.entity.Order;
import com.example.security.source.entity.ProductDetail;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class RestClientService {

    private final RestClient restClient;

    public RestClientService() {
        restClient = RestClient.builder()
                .baseUrl("http://10.20.10.138:8181")
//                .baseUrl("http://localhost:8080")
                .build();
    }

    public Object getTestOrder(Order order){
        return  restClient.post()
                .uri("/api/v1/test/order")
                .body(order)
                .retrieve()
                .body(new ParameterizedTypeReference<Order>() {
                });
    }

    public Object getProductDetail(Long productId, String token) {
        return restClient.get()
                .uri("/api/v1/productdetail/{productId}", productId)
                .header("Authorization", token)
                .retrieve()
                .body(ResponseData.class);
    }

    public Object getProduct(ListRequest request, String token) {
        return restClient.post()
                .uri("/api/v1/products")
                .header("Authorization", token)
                .header("action", "list")
                .header("Accept", MediaType.ALL_VALUE)
                .contentType(MediaType.APPLICATION_JSON)
                .body(request)
                .retrieve()
                .body(ResponseData.class);
    }

    public AuthenticationResponse getAuth(AuthenticationRequest request) {
        return restClient.post()
                .uri("/api/v1/auth/authenticate")
                .contentType(MediaType.APPLICATION_JSON)
                .body(request)
                .retrieve()
                .body(AuthenticationResponse.class);
    }
}
