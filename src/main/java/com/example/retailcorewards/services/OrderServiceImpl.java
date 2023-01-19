package com.example.retailcorewards.services;

import com.example.retailcorewards.web.model.OrderDto;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Arrays;

import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {

    @Override
    public OrderDto getOrderById(UUID orderId) {
        return OrderDto.builder()
                .id(UUID.randomUUID())
                .skus(new ArrayList<>(Arrays.asList("JG13", "JG14", "JG12")))
                .total(new BigDecimal("450.00"))
                .creationDate(OffsetDateTime.now())
                .build();
    }

    @Override
    public OrderDto saveNewOrder(OrderDto orderDto) {
        return OrderDto.builder()
                .id(UUID.randomUUID())
                .build();
    }

}
