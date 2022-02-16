package com.zooplus.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Order Balance Data Transfer Object")
public class OrderBalanceDTO {

    @ApiModelProperty(required = false,value = "Order Id")
    private String orderId;
    @ApiModelProperty(required = true,value = "Amount")
    private String amount;

    public OrderBalanceDTO() {
    }

    public OrderBalanceDTO(String orderId, String amount) {
        this.orderId = orderId;
        this.amount = amount;
    }

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
