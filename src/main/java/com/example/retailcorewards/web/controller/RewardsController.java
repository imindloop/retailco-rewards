package com.example.retailcorewards.web.controller;

import com.example.retailcorewards.services.CustomerService;
import com.example.retailcorewards.services.OrderService;
import com.example.retailcorewards.web.model.CustomerDto;
import com.example.retailcorewards.web.model.OrderDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequestMapping("/api/v1/rewards")
@RestController
public class RewardsController {

    private final CustomerService customerService;
    private final OrderService orderService;


    public RewardsController(CustomerService customerService, OrderService orderService) {
        this.customerService = customerService;
        this.orderService = orderService;
    }

    @GetMapping("/{customerId}")
    public Map<String, Integer> getRewardPointsByCustomerId(@PathVariable String customerId) {
        Map<String, Integer> result = new HashMap<>();
        int totalPoints = 0;

        Optional customer = customerService.getCustomerById(customerId);

        List<OrderDto> orders = orderService.getAllOrders(customerId);

        for(OrderDto order : orders) {

            int orderTotal = order.getTotal().intValue();

            if (orderTotal <= 0) {
                continue;
            } else if (orderTotal > 50 && orderTotal <= 100) {
                totalPoints += (orderTotal - 50) * 1;
            } else if (orderTotal > 100) {
                totalPoints += 50;
                totalPoints += (orderTotal - 100) * 2;
            }
        }

        result.put(customerId, totalPoints);

        return result;

    }

}
