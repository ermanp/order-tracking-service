package com.zooplus.controller;

import com.zooplus.dto.CustomerBalanceDTO;
import com.zooplus.mapper.CustomerBalanceMapper;
import com.zooplus.model.CustomerBalance;
import com.zooplus.service.CustomerBalanceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "Customer Api Documentation")
public class CustomerController {
    private static final Logger logger = LogManager.getLogger(OrderController.class);

    private final CustomerBalanceService customerBalanceService;
    private final CustomerBalanceMapper customerBalanceMapper;

    public CustomerController(CustomerBalanceService customerBalanceService, CustomerBalanceMapper customerBalanceMapper) {
        this.customerBalanceService = customerBalanceService;
        this.customerBalanceMapper = customerBalanceMapper;
    }

    @GetMapping("/customer-balance")
    @ApiOperation(value = "Get Customer Balance By Customer Id")
    public ResponseEntity<CustomerBalanceDTO> getCustomerBalanceByCustomerId(@RequestParam("customerId") String customerId){
        try{
            CustomerBalance customerBalance = customerBalanceService.findByCustomerId(Long.valueOf(customerId))
                    .orElse(new CustomerBalance());
            return ResponseEntity.ok(customerBalanceMapper.toDTO(customerBalance));
        }catch (Exception e){
            logger.error("getCustomerBalanceByCustomerId method received an error {}", e.getMessage());
        }
        return ResponseEntity.internalServerError().build();
    }
}
