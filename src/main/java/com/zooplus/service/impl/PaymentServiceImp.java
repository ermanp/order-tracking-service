package com.zooplus.service.impl;

import com.zooplus.dto.PaymentDTO;
import com.zooplus.exception.CustomerBalanceNotFoundException;
import com.zooplus.model.CustomerBalance;
import com.zooplus.model.Order;
import com.zooplus.model.OrderBalance;
import com.zooplus.model.Payment;
import com.zooplus.repository.PaymentRepository;
import com.zooplus.service.CustomerBalanceService;
import com.zooplus.service.OrderBalanceService;
import com.zooplus.service.OrderService;
import com.zooplus.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class PaymentServiceImp implements PaymentService {
    private final OrderBalanceService orderBalanceService;
    private final OrderService orderService;
    private final CustomerBalanceService customerBalanceService;
    private final PaymentRepository paymentRepository;

    public PaymentServiceImp(OrderBalanceService orderBalanceService, OrderService orderService, CustomerBalanceService customerBalanceService, PaymentRepository paymentRepository) {
        this.orderBalanceService = orderBalanceService;
        this.orderService = orderService;
        this.customerBalanceService = customerBalanceService;
        this.paymentRepository = paymentRepository;
    }


    @Override
    public void makePayment(PaymentDTO paymentDTO) throws CustomerBalanceNotFoundException {
        OrderBalance orderBalance = orderBalanceService.findByOrderId(Long.valueOf(paymentDTO.getOrderId()));
        orderBalance.setAmount(Double.parseDouble(paymentDTO.getAmount()) + orderBalance.getAmount());
        orderBalanceService.save(orderBalance);

        Order order = orderService.findById(Long.valueOf(paymentDTO.getOrderId()));
        Long customerId = order.getCustomer().getId();

        Optional<CustomerBalance> customerBalance = customerBalanceService.findByCustomerId(customerId);
        if(customerBalance.isPresent()){
            customerBalance.get().setAmount(Double.parseDouble(paymentDTO.getAmount()) + customerBalance.get().getAmount());
            customerBalanceService.save(customerBalance.get());
        }else{
            throw new CustomerBalanceNotFoundException();
        }

        Payment payment = new Payment();
        payment.setOrder(order);
        paymentRepository.save(payment);


    }
}
