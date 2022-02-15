package com.zooplus.service.impl;

import com.zooplus.model.CustomerBalance;
import com.zooplus.repository.CustomerBalanceRepository;
import com.zooplus.service.CustomerBalanceService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerBalanceServiceImpl implements CustomerBalanceService {
    private final CustomerBalanceRepository customerBalanceRepository;

    public CustomerBalanceServiceImpl(CustomerBalanceRepository customerBalanceRepository) {
        this.customerBalanceRepository = customerBalanceRepository;
    }

    @Override
    public void save(CustomerBalance customerBalance) {
        customerBalanceRepository.save(customerBalance);
    }

    @Override
    public Optional<CustomerBalance> findByCustomerId(Long customerId) {
        return customerBalanceRepository.findByCustomerId(customerId);
    }
}
