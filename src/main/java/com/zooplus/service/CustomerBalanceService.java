package com.zooplus.service;

import com.zooplus.model.CustomerBalance;

import java.util.Optional;

public interface CustomerBalanceService {

    void save(CustomerBalance customerBalance);

    Optional<CustomerBalance> findByCustomerId(Long customerId);
}
