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
    private int lowerRewardsThreshold = 50;

    @Value("${rewards.highestrewardsthreshold}")
    private int upperRewardsThreshold = 100;

    @Value("${rewards.pointsforlowestrewardsthreshold}")
    private int rewardPointsForLowerThreshold = 1;

    @Value("${rewards.pointsforhighestrewardsthreshold}")
    private int rewardPointsForUpperThreshold = 2;

    @Autowired
    private OrderService orderService;

    int ORDER_TOTAL;
    String CUSTOMER_FULL_NAME;
    String MONTH_OF_ORDER;

    @Override
    public Map<String, Map<String, Integer>> getRewardPoints(List<OrderDto> orders) throws Exception {

        if (lowerRewardsThreshold <= 0) {
            throw new Exception("Invalid value for lower rewards threshold, it shouldn't be lesser than 0, please input a valid value in application.properties");
        }

        if (upperRewardsThreshold <= 0) {
            throw new Exception("Invalid value for upper rewards threshold, it shouldn't be lesser than 0, please input a valid value in application.properties");
        }

        if (rewardPointsForLowerThreshold <= 0) {
            throw new Exception("Invalid value for points per dollar in lower threshold, it shouldn't be lesser than 0, please input a valid value in application.properties");
        }

        if (rewardPointsForUpperThreshold <= 0) {
            throw new Exception("Invalid value for points per dollar in upper threshold, it shouldn't be lesser than 0, please input a valid value in application.properties");
        }

        Map<String, Map<String, Integer>> result = new HashMap<>();

        if (null != orders && !orders.isEmpty()) {
            for (OrderDto order : orders) {
                ORDER_TOTAL = order.getTotal().intValue();
                CUSTOMER_FULL_NAME = order.getCustomer().getFirstName() + " " + order.getCustomer().getLastName();
                MONTH_OF_ORDER = order.getCreationDate().getMonth().toString();

                if (ORDER_TOTAL > lowerRewardsThreshold && ORDER_TOTAL <= upperRewardsThreshold) {
                    if (result.containsKey(CUSTOMER_FULL_NAME)) {
                        Map<String, Integer> map = result.get(CUSTOMER_FULL_NAME);
                        if (map.containsKey(MONTH_OF_ORDER)) {
                            map.replace(MONTH_OF_ORDER,
                                    map.get(MONTH_OF_ORDER) + calculatePointsInLowerThreshold(ORDER_TOTAL, lowerRewardsThreshold, rewardPointsForLowerThreshold));
                        } else {
                            map.put(MONTH_OF_ORDER, calculatePointsInLowerThreshold(ORDER_TOTAL, lowerRewardsThreshold, rewardPointsForLowerThreshold));
                        }
                    } else {
                        result.put(CUSTOMER_FULL_NAME, new HashMap<String, Integer>() {{
                            put(MONTH_OF_ORDER, calculatePointsInLowerThreshold(ORDER_TOTAL, lowerRewardsThreshold, rewardPointsForLowerThreshold));
                        }});
                    }
                } else if (ORDER_TOTAL > upperRewardsThreshold) {
                    if (result.containsKey(CUSTOMER_FULL_NAME)) {
                        Map<String, Integer> map = result.get(CUSTOMER_FULL_NAME);
                        if (map.containsKey(MONTH_OF_ORDER)) {
                            map.replace(MONTH_OF_ORDER,
                                    map.get(MONTH_OF_ORDER) + calculatePointsInUpperThreshold(lowerRewardsThreshold, ORDER_TOTAL, upperRewardsThreshold, rewardPointsForUpperThreshold));
                        } else {
                            map.put(MONTH_OF_ORDER, calculatePointsInUpperThreshold(lowerRewardsThreshold , ORDER_TOTAL, upperRewardsThreshold, rewardPointsForUpperThreshold));
                        }
                    } else {
                        result.put(CUSTOMER_FULL_NAME, new HashMap<String, Integer>() {{
                            put(MONTH_OF_ORDER, calculatePointsInUpperThreshold(lowerRewardsThreshold , ORDER_TOTAL, upperRewardsThreshold, rewardPointsForUpperThreshold));
                        }});
                    }
                }
            }
        }
        return result;
    }

    public Integer calculatePointsInLowerThreshold(int orderTotal, int lowerRewardsThreshold, int rewardPointsForLowerThreshold) {
        Integer result = 0;

        if (orderTotal >= lowerRewardsThreshold) {
            result =  (orderTotal - lowerRewardsThreshold) * rewardPointsForLowerThreshold;
        }

        return result;
    }

    public Integer calculatePointsInUpperThreshold(int orderTotal, int lowerRewardsThreshold, int upperRewardsThreshold, int rewardPointsForUpperThreshold) {
        return lowerRewardsThreshold + (orderTotal - upperRewardsThreshold) * rewardPointsForUpperThreshold;
    }
}
