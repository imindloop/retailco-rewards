package com.example.retailcorewards.services;

import com.example.retailcorewards.repositories.OrderRepository;
import com.example.retailcorewards.web.model.OrderDto;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    /**
     *
     * @return
     */
    @Override
    public List<OrderDto> getAllOrders() {
        return orderRepository.findAll();
    }

    /**
     *
     * @param orderDto
     */
    @Override
    public void addOrder(OrderDto orderDto) {
        orderRepository.save(orderDto);
    }

    /**
     *
     * @param orderDto
     */
    @Override
    public void deleteOrder(OrderDto orderDto) {
        orderRepository.delete(orderDto);
    }

}
