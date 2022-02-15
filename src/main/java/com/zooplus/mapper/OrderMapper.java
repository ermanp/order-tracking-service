package com.zooplus.mapper;

import com.zooplus.dto.OrderDTO;
import com.zooplus.model.Order;

public interface OrderMapper {

    OrderDTO toDTO(Order order);
}
