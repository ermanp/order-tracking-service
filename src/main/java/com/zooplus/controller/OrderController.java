package com.zooplus.controller;

import com.zooplus.dto.OrderBalanceDTO;
import com.zooplus.dto.OrderDTO;
import com.zooplus.mapper.OrderBalanceMapper;
import com.zooplus.model.OrderBalance;
import com.zooplus.service.OrderBalanceService;
import com.zooplus.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(value = "Order Api Documentation")
public class OrderController {
    private static final Logger logger = LogManager.getLogger(OrderController.class);

    private final OrderService orderservice;
    private final OrderBalanceMapper orderBalanceMapper;
    private final OrderBalanceService orderBalanceService;

    public OrderController(OrderService orderservice, OrderBalanceMapper orderBalanceMapper, OrderBalanceService orderBalanceService) {
        this.orderservice = orderservice;
        this.orderBalanceMapper = orderBalanceMapper;
        this.orderBalanceService = orderBalanceService;
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

    @GetMapping("/order-balance")
    @ApiOperation(value = "Get Order Balance By Order Id")
    public ResponseEntity<OrderBalanceDTO> getOrderBalanceByOrderId(@RequestParam("orderId") String orderId){
        try{
            OrderBalance orderBalance = orderBalanceService.findByOrderId(Long.valueOf(orderId));
            return ResponseEntity.ok(orderBalanceMapper.toDTO(orderBalance));
        }catch (Exception e){
            logger.error("getOrderBalanceByOrderId method received an error {}", e.getMessage());
        }
        return ResponseEntity.internalServerError().build();
    }
}
