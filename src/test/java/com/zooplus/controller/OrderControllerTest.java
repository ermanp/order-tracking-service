package com.zooplus.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.zooplus.dto.OrderDTO;
import com.zooplus.service.OrderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = OrderController.class)
class OrderControllerTest {
    private final static String CONTENT_TYPE = "application/json";

    @InjectMocks
    private OrderController orderController;
    @MockBean
    private OrderService orderservice;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void whenValidInput_thenReturns200() throws Exception {
        OrderDTO orderDTO = new OrderDTO("1","1","100");
        when(orderservice.save(any(OrderDTO.class))).thenReturn(orderDTO);

        ResultActions actions = mockMvc.perform(post("/order")
                .contentType(CONTENT_TYPE)
                .content(objectMapper.writeValueAsString(orderDTO)));

        ArgumentCaptor<OrderDTO> captor = ArgumentCaptor.forClass(OrderDTO.class);
        verify(orderservice,times(1)).save(captor.capture());
        assertEquals(captor.getValue().getAmount(),orderDTO.getAmount());
        assertEquals(captor.getValue().getCustomerId(),orderDTO.getCustomerId());
        actions.andExpect(status().isOk());
    }
}