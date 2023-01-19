package com.example.retailcorewards.services;

import com.example.retailcorewards.web.model.OrderDto;
import org.springframework.stereotype.Service;


import java.util.UUID;

@Service
public interface OrderService {
    OrderDto getOrderById(UUID orderId);
    OrderDto saveNewOrder(OrderDto orderDto);
}
