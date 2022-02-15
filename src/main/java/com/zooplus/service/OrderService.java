package com.zooplus.service;

import com.zooplus.dto.OrderDTO;
import com.zooplus.model.Order;

public interface OrderService {

    OrderDTO save(OrderDTO orderDTO);

    Order findById(Long id);
}
