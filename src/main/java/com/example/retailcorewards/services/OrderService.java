package com.example.retailcorewards.services;

import com.example.retailcorewards.web.model.OrderDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {

    List<OrderDto> getAllOrders();

    void addOrder(OrderDto order);

    void deleteOrder(OrderDto orderId);

}
