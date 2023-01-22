package com.example.retailcorewards.web.controller;

import com.example.retailcorewards.services.OrderService;
import com.example.retailcorewards.web.model.CustomerDto;
import com.example.retailcorewards.web.model.OrderDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequestMapping("/api/v1/customers")
@RestController
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     *
     * @param customerId
     * @return
     */
    @GetMapping("{customerId}/orders")
    public List<OrderDto> getAllOrders(@PathVariable String customerId) {
        System.out.println(customerId);
        return orderService.getAllOrders(customerId);
    }

    /**
     *
     * @param customerId
     * @param orderId
     * @return
     */
    @GetMapping("/{customerId}/orders/{orderId}")
    public Optional<OrderDto> getOrderbyId(@PathVariable String customerId, @PathVariable String orderId) {
        return orderService.getOrderById(orderId);
    }

    /**
     *
     * @param orderDto
     */
    @PostMapping("/{customerId}/orders")
    public void createNewOrder(@PathVariable String customerId, @RequestBody OrderDto orderDto) {
        orderDto.setCustomer(new CustomerDto(customerId, "", "", ""));
        orderService.saveNewOrder(orderDto);
    }

    /**
     *
     * @param orderDto
     */
    @PutMapping("/{customerId}/orders/{orderId}")
    public void updateOrder(@RequestBody OrderDto orderDto, @PathVariable String customerId, @PathVariable String orderId) {
        orderDto.setCustomer(new CustomerDto(customerId, "", "", ""));
        orderService.updateOrder(orderDto);
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
