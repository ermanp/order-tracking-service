package com.zooplus.controller;

import com.zooplus.dto.PaymentDTO;
import com.zooplus.service.PaymentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "Payment Api Documentation")
public class PaymentController {
    private static final Logger logger = LogManager.getLogger(PaymentController.class);

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/payment")
    @ApiOperation(value = "Make Payment Method")
    public ResponseEntity<Void> makePayment(@RequestBody PaymentDTO paymentDTO){
        try{
            paymentService.makePayment(paymentDTO);
            return ResponseEntity.ok().build();
        }catch (Exception e){
            logger.error("makePayment method received an error {}", e.getMessage());
        }
        return ResponseEntity.internalServerError().build();
    }
}
