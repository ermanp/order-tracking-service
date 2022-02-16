package com.zooplus.controller;

import com.zooplus.dto.OrderDTO;
import com.zooplus.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "Order Api documentation")
public class OrderController {
    private static final Logger logger = LogManager.getLogger(OrderController.class);

    private final OrderService orderservice;

    public OrderController(OrderService orderservice) {
        this.orderservice = orderservice;
    }

    @PostMapping("/order")
    @ApiOperation(value = "Order Creation Method")
    public ResponseEntity<OrderDTO> createOrder(@RequestBody @ApiParam(value = "order") OrderDTO orderDTO){
        try{
            return ResponseEntity.ok(orderservice.save(orderDTO));
        }catch (Exception e){
            logger.error("createOrder method received an error {}", e.getMessage());
        }
        return ResponseEntity.internalServerError().build();
    }
}
