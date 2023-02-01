package com.example.retailcorewards.web.controller;

import com.example.retailcorewards.services.OrderService;
import com.example.retailcorewards.web.model.CustomerDto;
import com.example.retailcorewards.web.model.OrderDto;
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

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@WebMvcTest(OrderController.class)
class OrderControllerTest {

    @MockBean
    private OrderService orderService;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void checkItReturnsListListOfOrders() throws Exception {
        // given
        CustomerDto ramza = CustomerDto.builder()
                .id("ramlve")
                .firstName("Ramza")
                .lastName("Beoulve")
                .email("ramza.beoulve@fftexample.com")
                .build();

        // when
        when(orderService.getAllOrders())
                .thenReturn(List.of(OrderDto.builder()
                    .id("order1")
                    .description("Razor x 5, Brush x 1, Labrador x 1, Black Mail Armor x 1")
                    .creationDate(LocalDate.of(2022, 12, 22))
                    .total(new BigDecimal("70"))
                    .customer(ramza)
                    .build()
                ));

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/api/v1/orders"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is("order1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].description", Matchers.is("Razor x 5, Brush x 1, Labrador x 1, Black Mail Armor x 1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].creationDate", Matchers.is("2022-12-22")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].customer").value(ramza)
                );
    }

    @Test
    void createNewOrder() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                .post("/api/v1/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\": \"order1\", \"description\": \"Razor x 5, Brush x 1, Labrador x 1, Black Mail Armor x 1\", \"creation\": \"2022-12-22\"}")
        )
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(orderService).addOrder(any(OrderDto.class));
    }

}