package com.zooplus.mapper;

import com.zooplus.dto.CustomerBalanceDTO;
import com.zooplus.model.CustomerBalance;

public interface CustomerBalanceMapper {

    CustomerBalanceDTO toDTO(CustomerBalance customerBalance);
}
