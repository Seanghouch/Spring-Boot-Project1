package com.example.security.service;

import com.example.security.source.entity.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PaymentService {

    public void processPayment(Order order) throws InterruptedException{
        log.info("Initiate payment for order " + order.getProductId());
        Thread.sleep(2000);
        log.info("Completed payment for order " + order.getProductId());
    }

}
