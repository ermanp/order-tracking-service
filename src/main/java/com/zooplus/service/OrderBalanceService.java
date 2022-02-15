package com.zooplus.service;

import com.zooplus.model.OrderBalance;

public interface OrderBalanceService {

    OrderBalance save(OrderBalance orderBalance);

    OrderBalance findByOrderId(Long orderId);
}
