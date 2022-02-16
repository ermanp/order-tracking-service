package com.zooplus.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.zooplus.dto.OrderBalanceDTO;
import com.zooplus.dto.OrderDTO;
import com.zooplus.mapper.impl.OrderBalanceMapperImpl;
import com.zooplus.model.Order;
import com.zooplus.model.OrderBalance;
import com.zooplus.service.OrderBalanceService;
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
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
    @MockBean
    private OrderBalanceMapperImpl orderBalanceMapper;
    @MockBean
    private OrderBalanceService orderBalanceService;


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

    @Test
    void whenGetOrderBalance_thenReturnNoData() throws Exception {
        when(orderBalanceService.findByOrderId(anyLong())).thenReturn(null);
        MvcResult mvcResult = mockMvc.perform(get("/order-balance")
                .param("orderId","1")
                .accept(CONTENT_TYPE)).andReturn();

        String responseBody = mvcResult.getResponse().getContentAsString();
        assertEquals("",responseBody);

    }

    @Test
    void whenGetOrderBalance_thenReturns200() throws Exception {
        Order order = new Order();
        order.setId(1L);
        order.setAmount(100.0);
        OrderBalance orderBalance = new OrderBalance();
        orderBalance.setOrder(order);
        orderBalance.setAmount(100.0);
        OrderBalanceDTO orderBalanceDTO = new OrderBalanceDTO("1","-100.0");

        when(orderBalanceService.findByOrderId(anyLong())).thenReturn(orderBalance);
        when(orderBalanceMapper.toDTO(orderBalance)).thenReturn(orderBalanceDTO);
        MvcResult mvcResult = mockMvc.perform(get("/order-balance")
                .param("orderId","1")
                .accept(CONTENT_TYPE)).andReturn();

        String responseBody = mvcResult.getResponse().getContentAsString();
        assertEquals(objectMapper.writeValueAsString(orderBalanceDTO).trim(),responseBody);

    }
}