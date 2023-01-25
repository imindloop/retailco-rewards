package com.example.retailcorewards.services;

import com.example.retailcorewards.web.model.OrderDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface OrderService {

    List<OrderDto> getAllOrders(String customerId);

    Optional<OrderDto> getOrderById(String orderId);

    void saveNewOrder(OrderDto order);

    void updateOrder(OrderDto order);

    void deleteOrder(OrderDto orderId);

}
