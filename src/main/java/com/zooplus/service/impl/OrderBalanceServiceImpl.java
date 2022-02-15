package com.zooplus.service.impl;

import com.zooplus.model.OrderBalance;
import com.zooplus.repository.OrderBalanceRepository;
import com.zooplus.service.OrderBalanceService;
import org.springframework.stereotype.Service;

@Service
public class OrderBalanceServiceImpl implements OrderBalanceService {
    private final OrderBalanceRepository orderBalanceRepository;

    public OrderBalanceServiceImpl(OrderBalanceRepository orderBalanceRepository) {
        this.orderBalanceRepository = orderBalanceRepository;
    }

    @Override
    public OrderBalance save(OrderBalance orderBalance) {
        return orderBalanceRepository.save(orderBalance);
    }

    @Override
    public OrderBalance findByOrderId(Long orderId) {
        return orderBalanceRepository.findByOrderId(orderId);
    }
}
