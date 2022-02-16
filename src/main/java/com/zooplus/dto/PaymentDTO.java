package com.zooplus.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Payment Data Transfer Object")
public class PaymentDTO {

    @ApiModelProperty(required = true,value = "Order Id")
    private String orderId;
    @ApiModelProperty(required = true,value = "Amount")
    private String amount;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
