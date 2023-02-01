package com.example.retailcorewards.web.controller;

import com.example.retailcorewards.services.OrderService;
import com.example.retailcorewards.services.RewardsService;
import com.example.retailcorewards.web.model.CustomerDto;
import com.example.retailcorewards.web.model.OrderDto;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.when;

@WebMvcTest(RewardsController.class)
class RewardsControllerTest {


    @MockBean
    private OrderService orderService;

    @MockBean
    private RewardsService rewardsService;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void checkCorrectlyReturnsRewardPoints() throws Exception {
        // given
        LocalDate localDate1 = LocalDate.parse("2022-12-03");
        LocalDate localDate2 = LocalDate.parse("2022-11-03");

        CustomerDto ramza = CustomerDto.builder()
                .id("Ramlve")
                .firstName("Ramza")
                .lastName("Beoulve")
                .email("ramza.beoulve@fftexample.com")
                .build();


        // when
        when(orderService.getAllOrders())
                .thenReturn(List.of(OrderDto.builder()
                        .id("order1")
                        .description("Razor x 5, Brush x 1, Labrador x 1, Black Mail Armor x 1")
                        .creationDate(localDate1)
                        .total(new BigDecimal("70"))
                        .customer(ramza)
                        .build()
                ));

        when(rewardsService.getRewardPoints(new ArrayList<OrderDto>() {
            {
                // Ramza Orders
                add(OrderDto.builder()
                        .id("order1")
                        .description("Razor x 5, Brush x 1, Labrador x 1, Black Mail Armor x 1")
                        .creationDate(localDate1)
                        .total(new BigDecimal("70"))
                        .customer(ramza)
                        .build()
                );
            }}))
            .thenReturn(new HashMap<String, Map<String, Integer>>() {{
                put("Ramza Beoulve", new HashMap<String, Integer>(){{
                    put("DECEMBER", 20);
                }});
            }});

        this.mockMvc
            .perform(MockMvcRequestBuilders
                .get("/api/v1/rewards"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.size()", Matchers.is(1)));
    }
}