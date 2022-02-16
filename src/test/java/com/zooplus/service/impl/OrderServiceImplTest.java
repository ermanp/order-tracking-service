package com.zooplus.service.impl;


import com.zooplus.dto.OrderDTO;
import com.zooplus.mapper.OrderBalanceMapper;
import com.zooplus.mapper.impl.OrderMapperImpl;
import com.zooplus.model.Customer;
import com.zooplus.model.Order;
import com.zooplus.repository.OrderRepository;
import com.zooplus.service.CustomerBalanceService;
import com.zooplus.service.CustomerService;
import com.zooplus.service.OrderBalanceService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {

    @InjectMocks
    private OrderServiceImpl orderService;
    @Mock
    private CustomerService customerService;
    @Mock
    private OrderRepository orderRepository;
    @Mock
    private OrderBalanceService orderBalanceService;
    @Mock
    private CustomerBalanceService customerBalanceService;
    @Mock
    private OrderMapperImpl orderMapper;
    @Mock
    private OrderBalanceMapper orderBalanceMapper;


    @Test
    void test_save() {
        OrderDTO orderDTO = new OrderDTO("","1","100");
        Customer customer = new Customer();
        customer.setId(1L);
        Order createdOrder = new Order();
        createdOrder.setCustomer(customer);
        createdOrder.setAmount(Double.valueOf(orderDTO.getAmount()));
        createdOrder.setId(1L);
        when(customerService.findById(anyLong())).thenReturn(customer);
        when(orderRepository.save(any(Order.class))).thenReturn(createdOrder);
        when(orderMapper.toDTO(any(Order.class))).thenReturn(orderDTO);

        OrderDTO result = orderService.save(orderDTO);
        assertEquals(orderDTO.getCustomerId(),result.getCustomerId());
        assertEquals(orderDTO.getAmount(),result.getAmount());
    }

}