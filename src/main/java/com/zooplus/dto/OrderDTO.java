package com.zooplus.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Order Data Transfer Object")
public class OrderDTO {

    @ApiModelProperty(required = false,value = "Order Id")
    private String orderId;
    @ApiModelProperty(required = true,value = "Customer Id")
    private String customerId;
    @ApiModelProperty(required = true,value = "Amount")
    private String amount;

    public OrderDTO() {
    }

    public OrderDTO(String orderId, String customerId, String amount) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.amount = amount;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
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
