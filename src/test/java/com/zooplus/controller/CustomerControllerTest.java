package com.zooplus.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.zooplus.dto.CustomerBalanceDTO;
import com.zooplus.mapper.CustomerBalanceMapper;
import com.zooplus.model.Customer;
import com.zooplus.model.CustomerBalance;
import com.zooplus.service.CustomerBalanceService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = CustomerController.class)
class CustomerControllerTest {
    private final static String CONTENT_TYPE = "application/json";

    @InjectMocks
    private CustomerController customerController;
    @MockBean
    private CustomerBalanceService customerBalanceService;
    @MockBean
    private CustomerBalanceMapper customerBalanceMapper;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void whenGetCustomerBalance_thenReturnNoData() throws Exception {
        when(customerBalanceService.findByCustomerId(anyLong())).thenReturn(Optional.of(new CustomerBalance()));
        MvcResult mvcResult = mockMvc.perform(get("/customer-balance")
                .param("customerId","1")
                .accept(CONTENT_TYPE)).andReturn();

        String responseBody = mvcResult.getResponse().getContentAsString();
        assertEquals("",responseBody);
    }

    @Test
    void whenGetCustomerBalance_thenReturns200() throws Exception {
        Customer customer = new Customer();
        customer.setId(1L);
        CustomerBalance customerBalance = new CustomerBalance();
        customerBalance.setCustomer(customer);
        customerBalance.setAmount(-40.0);
        CustomerBalanceDTO dto = new CustomerBalanceDTO("1","-40.0");
        when(customerBalanceService.findByCustomerId(anyLong())).thenReturn(Optional.of(customerBalance));
        when(customerBalanceMapper.toDTO(customerBalance)).thenReturn(dto);

        MvcResult mvcResult = mockMvc.perform(get("/customer-balance")
                .param("customerId","1")
                .accept(CONTENT_TYPE)).andReturn();

        String responseBody = mvcResult.getResponse().getContentAsString();
        assertEquals(objectMapper.writeValueAsString(dto).trim(),responseBody);

    }

}