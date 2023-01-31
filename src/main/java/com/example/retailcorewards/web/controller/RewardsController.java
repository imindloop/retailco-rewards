package com.example.retailcorewards.web.controller;

import com.example.retailcorewards.services.OrderService;
import com.example.retailcorewards.services.RewardsService;
import com.example.retailcorewards.web.model.OrderDto;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;


@RequestMapping("/api/v1/rewards")
@RestController
public class RewardsController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private RewardsService rewardsService;

    @RequestMapping
    public Map<String, Map<String, Integer>> getRewardPoints() throws Exception {
        List<OrderDto> orders = orderService.getAllOrders();
        return rewardsService.getRewardPoints(orders);
    }

}
