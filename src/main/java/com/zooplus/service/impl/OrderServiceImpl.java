package com.zooplus.service.impl;

import com.zooplus.dto.OrderDTO;
import com.zooplus.mapper.OrderMapper;
import com.zooplus.model.Customer;
import com.zooplus.model.CustomerBalance;
import com.zooplus.model.Order;
import com.zooplus.model.OrderBalance;
import com.zooplus.repository.OrderRepository;
import com.zooplus.service.CustomerBalanceService;
import com.zooplus.service.CustomerService;
import com.zooplus.service.OrderBalanceService;
import com.zooplus.service.OrderService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    private final CustomerService customerService;
    private final OrderRepository orderRepository;
    private final OrderBalanceService orderBalanceService;
    private final CustomerBalanceService customerBalanceService;
    private final OrderMapper orderMapper;

    public OrderServiceImpl(CustomerService customerService, OrderRepository orderRepository, OrderBalanceService orderBalanceService, CustomerBalanceService customerBalanceService, OrderMapper orderMapper) {
        this.customerService = customerService;
        this.orderRepository = orderRepository;
        this.orderBalanceService = orderBalanceService;
        this.customerBalanceService = customerBalanceService;
        this.orderMapper = orderMapper;
    }

    @Override
    public OrderDTO save(OrderDTO orderDTO) {
        Order order = new Order();
        Customer customer = customerService.findById(Long.valueOf(orderDTO.getCustomerId()));
        order.setCustomer(customer);
        order.setAmount(Double.valueOf(orderDTO.getAmount()));

        OrderBalance orderBalance = new OrderBalance();
        orderBalance.setAmount(getOrderBalanceAmount(orderDTO));
        orderBalance.setOrder(order);

        order.setOrderBalance(orderBalance);
        Order createdOrder = orderRepository.save(order);
        orderBalanceService.save(orderBalance);

        CustomerBalance customerBalance = customerBalanceService.findByCustomerId(Long.valueOf(orderDTO.getCustomerId()))
                .orElse(new CustomerBalance());

        customerBalance.setAmount(customerBalance.getAmount() - createdOrder.getAmount());
        customerBalance.setCustomer(customer);

        customerBalanceService.save(customerBalance);

        return orderMapper.toDTO(createdOrder);
    }

    @Override
    public Order findById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    private Double getOrderBalanceAmount(OrderDTO orderDTO) {
        return -1 * Double.parseDouble(orderDTO.getAmount());
    }
}
