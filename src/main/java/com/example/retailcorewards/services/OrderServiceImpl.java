package com.example.retailcorewards.services;

import com.example.retailcorewards.repositories.OrderRepository;
import com.example.retailcorewards.web.model.OrderDto;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    /**
     *
     * @return
     */
    @Override
    public List<OrderDto> getAllOrders(String customerId) {
        List<OrderDto> orders = new ArrayList<>();
        orderRepository.findAllByCustomerId(customerId)
                .forEach(orders::add);
        return orders;
    }

    /**
     *
     * @param orderId
     * @return
     */
    @Override
    public Optional<OrderDto> getOrderById(String orderId) {
        return orderRepository.findById(orderId.toString());
    }

    /**
     *
     * @param orderDto
     */
    public void saveNewOrder(OrderDto orderDto) {
        orderRepository.save(orderDto);
    }

    /**
     *
     * @param orderDto
     */
    public void updateOrder(OrderDto orderDto) {
        orderRepository.save(orderDto);
    }

    /**
     *
     * @param orderDto
     */
    public void deleteOrder(OrderDto orderDto) {
        orderRepository.delete(orderDto);
    }

}
