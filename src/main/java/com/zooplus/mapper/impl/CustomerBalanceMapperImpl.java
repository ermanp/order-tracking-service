package com.zooplus.mapper.impl;

import com.zooplus.dto.CustomerBalanceDTO;
import com.zooplus.mapper.CustomerBalanceMapper;
import com.zooplus.model.CustomerBalance;
import org.springframework.stereotype.Service;

@Service
public class CustomerBalanceMapperImpl implements CustomerBalanceMapper {
    @Override
    public CustomerBalanceDTO toDTO(CustomerBalance customerBalance) {
        return new CustomerBalanceDTO(String.valueOf(customerBalance.getCustomer().getId()), String.valueOf(customerBalance.getAmount()));
    }
}
