package com.zooplus.service.impl;

import com.zooplus.dto.PaymentDTO;
import com.zooplus.exception.CustomerBalanceNotFoundException;
import com.zooplus.model.Customer;
import com.zooplus.model.CustomerBalance;
import com.zooplus.model.Order;
import com.zooplus.model.OrderBalance;
import com.zooplus.service.CustomerBalanceService;
import com.zooplus.service.OrderBalanceService;
import com.zooplus.service.OrderService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class PaymentServiceImpTest {
    @InjectMocks
    private PaymentServiceImp paymentService;
    @Mock
    private OrderBalanceService orderBalanceService;
    @Mock
    private OrderService orderService;
    @Mock
    private CustomerBalanceService customerBalanceService;

    @Test
    void test_makePayment() throws CustomerBalanceNotFoundException {
        PaymentDTO paymentDTO = new PaymentDTO();
        paymentDTO.setAmount("110");
        paymentDTO.setOrderId("1");

        OrderBalance orderBalance = new OrderBalance();
        orderBalance.setAmount(-100.0);
        orderBalance.setId(1L);

        Customer customer = new Customer();
        customer.setId(1L);
        Order order = new Order();
        order.setCustomer(customer);
        order.setAmount(100.0);

        CustomerBalance customerBalance = new CustomerBalance();
        customerBalance.setCustomer(customer);
        customerBalance.setAmount(-100.0);

        when(orderBalanceService.findByOrderId(anyLong())).thenReturn(orderBalance);
        when(orderService.findById(anyLong())).thenReturn(order);
        when(customerBalanceService.findByCustomerId(anyLong())).thenReturn(Optional.of(customerBalance));

        paymentService.makePayment(paymentDTO);

        assertEquals(10.0,customerBalance.getAmount());
    }

    @Test
    void when_CustomerBalanceIsNull_then_ReturnsCustomerBalanceNotFoundException() {
        PaymentDTO paymentDTO = new PaymentDTO();
        paymentDTO.setAmount("110");
        paymentDTO.setOrderId("1");

        OrderBalance orderBalance = new OrderBalance();
        orderBalance.setAmount(-100.0);
        orderBalance.setId(1L);

        Customer customer = new Customer();
        customer.setId(1L);
        Order order = new Order();
        order.setCustomer(customer);
        order.setAmount(100.0);

        CustomerBalance customerBalance = new CustomerBalance();
        customerBalance.setCustomer(customer);
        customerBalance.setAmount(-100.0);

        when(orderBalanceService.findByOrderId(anyLong())).thenReturn(orderBalance);
        when(orderService.findById(anyLong())).thenReturn(order);
        when(customerBalanceService.findByCustomerId(anyLong())).thenReturn(Optional.empty());

        Assertions.assertThrows(CustomerBalanceNotFoundException.class, () -> paymentService.makePayment(paymentDTO));



    }
}