package com.example.security.controller;

import com.example.security.service.OrderService;
import com.example.security.source.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/test")
public class SaleOrder {

    @Autowired
    private OrderService orderService;

    @PostMapping("/order")
    public ResponseEntity<Order> processOrder(@RequestBody Order order) throws InterruptedException{
        orderService.processOrder(order); // synchronous
        // asynchronous
        orderService.notifyUser(order);
        orderService.assignVendor(order);
        orderService.packaging(order);
        orderService.assignDeliveryPartner(order);
        orderService.assignTrailerAndDispatch(order);
        return ResponseEntity.ok(order);
    }

}
