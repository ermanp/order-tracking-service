package com.zooplus.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Customer Balance Data Transfer Object")
public class CustomerBalanceDTO {

    @ApiModelProperty(required = false,value = "Customer Id")
    private String customerId;
    @ApiModelProperty(required = false,value = "Customer Balance Amount")
    private String amount;

    public CustomerBalanceDTO(String customerId, String amount) {
        this.customerId = customerId;
        this.amount = amount;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
