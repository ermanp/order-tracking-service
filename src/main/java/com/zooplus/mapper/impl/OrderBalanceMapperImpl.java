package com.zooplus.mapper.impl;

import com.zooplus.dto.OrderBalanceDTO;
import com.zooplus.mapper.OrderBalanceMapper;
import com.zooplus.model.OrderBalance;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
public class OrderBalanceMapperImpl implements OrderBalanceMapper {
    @Override
    public OrderBalanceDTO toDTO(OrderBalance orderBalance) {
        if(ObjectUtils.isEmpty(orderBalance))
            return new OrderBalanceDTO();
        return new OrderBalanceDTO(String.valueOf(orderBalance.getOrder().getId()),String.valueOf(orderBalance.getAmount()));
    }
}
