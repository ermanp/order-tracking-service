package com.zooplus.mapper;

import com.zooplus.dto.OrderBalanceDTO;
import com.zooplus.model.OrderBalance;

public interface OrderBalanceMapper {

    OrderBalanceDTO toDTO(OrderBalance orderBalance);
}
