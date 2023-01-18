package com.example.retailcorewards.web.controller;

import com.example.retailcorewards.services.OrderService;
import com.example.retailcorewards.web.model.OrderDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/api/v1/order")
public class OrderController {

    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }


    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDto> getOrderbyId(@PathVariable UUID orderId) {
        return new ResponseEntity<>(orderService.getOrderById(orderId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity createNewOrder(@RequestBody OrderDto orderDto) {
        OrderDto savedOrderDto = orderService.saveNewOrder(orderDto);
        HttpHeaders headers = new HttpHeaders();
        headers.add("createdResource", "/api/v1/order/" + savedOrderDto.getId().toString());
        return new ResponseEntity(HttpStatus.CREATED);

    }

}
