package com.zooplus.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.zooplus.dto.PaymentDTO;
import com.zooplus.service.PaymentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = PaymentController.class)
class PaymentControllerTest {
    private final static String CONTENT_TYPE = "application/json";

    @InjectMocks
    private PaymentController paymentController;
    @MockBean
    private PaymentService paymentService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void whenValidInput_thenReturns200() throws Exception {
        PaymentDTO paymentDTO = new PaymentDTO();
        paymentDTO.setAmount("110");
        paymentDTO.setOrderId("1");

        mockMvc.perform(post("/payment")
                .contentType(CONTENT_TYPE)
                .content(objectMapper.writeValueAsString(paymentDTO)))
                .andExpect(status().isOk());

    }
}