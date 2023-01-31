package com.example.retailcorewards.web.controller;

import com.example.retailcorewards.services.OrderService;
import com.example.retailcorewards.web.model.CustomerDto;
import com.example.retailcorewards.web.model.OrderDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/orders")
@RestController
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     *
     * @return
     */
    @RequestMapping
    public List<OrderDto> getAllOrders() {
        return orderService.getAllOrders();
    }

    /**
     *
     * @param orderDto
     */
    @PostMapping("/{customerId}/orders")
    public void createNewOrder(@PathVariable String customerId, @RequestBody OrderDto orderDto) {
        orderDto.setCustomer(new CustomerDto(customerId, "", "", ""));
        orderService.addOrder(orderDto);
    }

    /**
     *
     * @param orderDto
     */
    @DeleteMapping("/{customerId}/orders/{orderId}")
    public void deleteOrder(@RequestBody OrderDto orderDto) {
        orderService.deleteOrder(orderDto);
    }

}
