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

    @RequestMapping
    public Map<String, Map<String, Integer>> getRewardPoints() {

        /**
         * Map with customer name as the key and a map with a month and its corresponding points.
         */
        Map<String, Map<String, Integer>> result = new HashMap<>();

        List<OrderDto> orders = orderService.getAllOrders("all");

        for(OrderDto order : orders) {

            int orderTotal = order.getTotal().intValue();
            String customerFullName = order.getCustomer().getFirstName() + " " + order.getCustomer().getLastName();
            String orderMonth = order.getCreationDate().getMonth().toString();

            if (orderTotal <= 0) {
                continue;
            } else if (orderTotal > 50 && orderTotal <= 100) {
                if(result.containsKey(customerFullName)) {
                    Map<String, Integer> map = result.get(customerFullName);
                    if (map.containsKey(orderMonth)) {
                        map.replace(orderMonth,
                                map.get(orderMonth) + (orderTotal - 50));
                    } else {
                        map.put(orderMonth, orderTotal - 50);
                    }
                } else {
                    result.put(customerFullName, new HashMap<String, Integer>() {{
                        put(orderMonth, orderTotal - 50);
                    }});
                }
            } else if (orderTotal > 100) {
                if(result.containsKey(customerFullName)) {
                    Map<String, Integer> map = result.get(customerFullName);
                    if (map.containsKey(orderMonth)) {
                        map.replace(orderMonth,
                                map.get(orderMonth) + 50 + (orderTotal - 100) * 2);
                    } else {
                        map.put(orderMonth, orderTotal + 50 + (orderTotal - 100) * 2);
                    }
                } else {
                    result.put(customerFullName, new HashMap<String, Integer>() {{
                        put(orderMonth, orderTotal + 50 + (orderTotal - 100) * 2);
                    }});
                }
            }
        }

        return result;
    }

}
