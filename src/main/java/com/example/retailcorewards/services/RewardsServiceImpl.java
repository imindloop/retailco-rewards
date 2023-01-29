package com.example.retailcorewards.services;

import com.example.retailcorewards.web.model.OrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RewardsServiceImpl implements RewardsService {

    @Value("${rewards.lowestrewardsthreshold}")
    private int LOWER_REWARDS_THRESHOLD;

    @Value("${rewards.highestrewardsthreshold}")
    private int UPPER_REWARDS_THRESHOLD;

    @Value("${rewards.pointsforlowestrewardsthreshold}")
    private int POINTS_PER_DOLLAR_IN_LOWER_THRESHOLD;

    @Value("${rewards.pointsforhighestrewardsthreshold}")
    private int POINTS_PER_DOLLAR_IN_UPPER_THRESHOLD;

    private int ORDER_TOTAL;
    private String CUSTOMER_FULL_NAME;
    private String MONTH_OF_ORDER;

    @Autowired
    private OrderService orderService;

    @Override
    public Map<String, Map<String, Integer>> getRewardPoints() {

        /**
         * Map with customer name as the key and a map with a month and its corresponding points.
         */
        Map<String, Map<String, Integer>> result = new HashMap<>();
        List<OrderDto> orders = orderService.getAllOrders();

        for(OrderDto order : orders) {
            ORDER_TOTAL = order.getTotal().intValue();
            CUSTOMER_FULL_NAME = order.getCustomer().getFirstName() + " " + order.getCustomer().getLastName();
            MONTH_OF_ORDER = order.getCreationDate().getMonth().toString();


            if (ORDER_TOTAL > LOWER_REWARDS_THRESHOLD && ORDER_TOTAL <= UPPER_REWARDS_THRESHOLD) {
                if(result.containsKey(CUSTOMER_FULL_NAME)) {
                    updateCustomerInLowerThreshold(result);
                } else {
                    addCustomerInLowerThreshold(result);
                }
            } else if (ORDER_TOTAL > UPPER_REWARDS_THRESHOLD) {
                if(result.containsKey(CUSTOMER_FULL_NAME)) {
                    updateCustomerInUpperThreshold(result);
                } else {
                    addCustomerInUpperThreshold(result);
                }
            }
        }
        return result;
    }

    private void updateCustomerInUpperThreshold(Map<String, Map<String, Integer>> result) {
        Map<String, Integer> map = result.get(CUSTOMER_FULL_NAME);
        if (map.containsKey(MONTH_OF_ORDER)) {
            updateMonthPointsInHigherThreshold(map);
        } else {
            addMonthPointsInUpperThreshold(map);
        }
    }

    private void updateCustomerInLowerThreshold(Map<String, Map<String, Integer>> result) {
        Map<String, Integer> map = result.get(CUSTOMER_FULL_NAME);
        if (map.containsKey(MONTH_OF_ORDER)) {
            updateMonthPointsInLowerThreshold(map);
        } else {
            addMonthPointsToLowerThreshold(map);
        }
    }

    private void addMonthPointsInUpperThreshold(Map<String, Integer> map) {
        map.put(MONTH_OF_ORDER, ORDER_TOTAL + LOWER_REWARDS_THRESHOLD + (ORDER_TOTAL - UPPER_REWARDS_THRESHOLD) * POINTS_PER_DOLLAR_IN_UPPER_THRESHOLD);
    }

    private void addMonthPointsToLowerThreshold(Map<String, Integer> map) {
        map.put(MONTH_OF_ORDER, ORDER_TOTAL - LOWER_REWARDS_THRESHOLD);
    }

    private void addCustomerInUpperThreshold(Map<String, Map<String, Integer>> result) {
        result.put(CUSTOMER_FULL_NAME, new HashMap<String, Integer>() {{
            put(MONTH_OF_ORDER, ORDER_TOTAL + LOWER_REWARDS_THRESHOLD + (ORDER_TOTAL - UPPER_REWARDS_THRESHOLD) * POINTS_PER_DOLLAR_IN_UPPER_THRESHOLD);
        }});
    }

    private void addCustomerInLowerThreshold(Map<String, Map<String, Integer>> result) {
        result.put(CUSTOMER_FULL_NAME, new HashMap<String, Integer>() {{
            put(MONTH_OF_ORDER, ORDER_TOTAL - LOWER_REWARDS_THRESHOLD);
        }});
    }

    private void updateMonthPointsInHigherThreshold(Map<String, Integer> map) {
        map.replace(MONTH_OF_ORDER,
                map.get(MONTH_OF_ORDER) + LOWER_REWARDS_THRESHOLD + (ORDER_TOTAL - UPPER_REWARDS_THRESHOLD) * POINTS_PER_DOLLAR_IN_UPPER_THRESHOLD);
    }

    private void updateMonthPointsInLowerThreshold(Map<String, Integer> map) {
        map.replace(MONTH_OF_ORDER,
                map.get(MONTH_OF_ORDER) + (ORDER_TOTAL - LOWER_REWARDS_THRESHOLD) * POINTS_PER_DOLLAR_IN_LOWER_THRESHOLD);
    }
}
