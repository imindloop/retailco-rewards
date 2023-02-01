package com.example.retailcorewards.web.controller;

import com.example.retailcorewards.services.CustomerService;
import com.example.retailcorewards.web.model.CustomerDto;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@WebMvcTest(CustomerController.class)
class CustomerControllerTest {

    @MockBean
    private CustomerService customerService;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void checkIfReturnsListOfCustomers() throws Exception {
        // when
        when(customerService.getAllCustomers())
            .thenReturn(List.of(CustomerDto.builder()
                .id("Ramlve")
                .firstName("Ramza")
                .lastName("Beoulve")
                .email("ramza.beoulve@fftexample.com")
                .build()
            ));

        this.mockMvc
            .perform(MockMvcRequestBuilders
                .get("/api/v1/customers"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.size()", Matchers.is(1)))
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is("Ramlve")))
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].firstName", Matchers.is("Ramza")))
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].lastName", Matchers.is("Beoulve")))
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].email", Matchers.is("ramza.beoulve@fftexample.com")));

    }

    @Test
    void shouldAddCustomer() throws Exception {

        this.mockMvc.perform(MockMvcRequestBuilders
            .post("/api/v1/customers")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"id\": \"Ramlve\", \"firstName\": \"Ramza\", \"lastName\": \"Beoulve\", \"email\": \"ramza.beoulve@fftexample.com\"}")
        )
        .andExpect(MockMvcResultMatchers.status().isOk());

        verify(customerService).addCustomer(any(CustomerDto.class));

    }
}