package com.zooplus.service;

import com.zooplus.dto.PaymentDTO;
import com.zooplus.exception.CustomerBalanceNotFoundException;

public interface PaymentService {

    void makePayment(PaymentDTO paymentDTO) throws CustomerBalanceNotFoundException;
}
