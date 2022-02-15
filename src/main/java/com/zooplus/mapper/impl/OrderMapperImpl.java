package com.zooplus.mapper.impl;

import com.zooplus.dto.OrderDTO;
import com.zooplus.mapper.OrderMapper;
import com.zooplus.model.Order;
import org.springframework.stereotype.Service;

@Service
public class OrderMapperImpl implements OrderMapper {
    @Override
    public OrderDTO toDTO(Order order) {
        return new OrderDTO(String.valueOf(order.getId()),
                String.valueOf(order.getCustomer().getId()),
                String.valueOf(order.getAmount()));
    }
}
