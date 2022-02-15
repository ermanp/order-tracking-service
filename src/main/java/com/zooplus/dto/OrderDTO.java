package com.zooplus.dto;

public class OrderDTO {

    private String orderId;
    private String customerId;
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
