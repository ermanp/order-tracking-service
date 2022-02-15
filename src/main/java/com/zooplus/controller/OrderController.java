package com.zooplus.controller;

import com.zooplus.dto.OrderDTO;
import com.zooplus.service.OrderService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
    private static final Logger logger = LogManager.getLogger(OrderController.class);

    private final OrderService orderservice;

    public OrderController(OrderService orderservice) {
        this.orderservice = orderservice;
    }

    @PostMapping("/order")
    public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderDTO orderDTO){
        try{
            return ResponseEntity.ok(orderservice.save(orderDTO));
        }catch (Exception e){
            logger.error("createOrder method received an error {}", e.getMessage());
        }
        return ResponseEntity.internalServerError().build();
    }
}