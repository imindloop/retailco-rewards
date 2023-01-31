package com.example.retailcorewards.services;

import com.example.retailcorewards.web.model.OrderDto;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RewardsServiceImpl implements RewardsService {

    private int lowerRewardsThreshold = 50;

    private int upperRewardsThreshold = 100;

    private int rewardPointsForLowerThreshold = 1;

    private int rewardPointsForUpperThreshold = 2;

    int orderTotal;
    String customerFullName;
    String monthOfOrder;


    @Override
    public Map<String, Map<String, Integer>> getRewardPoints(List<OrderDto> orders) throws Exception {

        if (lowerRewardsThreshold <= 0 || upperRewardsThreshold <= 0 || rewardPointsForLowerThreshold <= 0 || rewardPointsForUpperThreshold <= 0) {
            throw new Exception("Invalid configuration value for rewards points calculation, negative values are not accepted.");
        }
        Map<String, Map<String, Integer>> result = new HashMap<>();

        if (null != orders && !orders.isEmpty()) {
            for (OrderDto order : orders) {
                orderTotal = order.getTotal().intValue();
                customerFullName = order.getCustomer().getFirstName() + " " + order.getCustomer().getLastName();
                monthOfOrder = order.getCreationDate().getMonth().toString();

                if (orderTotal >= lowerRewardsThreshold && orderTotal < upperRewardsThreshold) {
                    if (result.containsKey(customerFullName)) {
                        Map<String, Integer> map = result.get(customerFullName);
                        if (map.containsKey(monthOfOrder)) {
                            map.replace(monthOfOrder,
                                    map.get(monthOfOrder) + calculatePointsInLowerThreshold(orderTotal, lowerRewardsThreshold, rewardPointsForLowerThreshold));
                        } else {
                            map.put(monthOfOrder, calculatePointsInLowerThreshold(orderTotal, lowerRewardsThreshold, rewardPointsForLowerThreshold));
                        }
                    } else {
                        result.put(customerFullName, new HashMap<String, Integer>() {{
                            put(monthOfOrder, calculatePointsInLowerThreshold(orderTotal, lowerRewardsThreshold, rewardPointsForLowerThreshold));
                        }});
                    }
                } else if (orderTotal >= upperRewardsThreshold) {
                    if (result.containsKey(customerFullName)) {
                        Map<String, Integer> map = result.get(customerFullName);
                        if (map.containsKey(monthOfOrder)) {
                            map.replace(monthOfOrder,
                                    map.get(monthOfOrder) + calculatePointsInUpperThreshold(lowerRewardsThreshold, orderTotal, upperRewardsThreshold, rewardPointsForUpperThreshold));
                        } else {
                            map.put(monthOfOrder, calculatePointsInUpperThreshold(lowerRewardsThreshold , orderTotal, upperRewardsThreshold, rewardPointsForUpperThreshold));
                        }
                    } else {
                        result.put(customerFullName, new HashMap<String, Integer>() {{
                            put(monthOfOrder, calculatePointsInUpperThreshold(lowerRewardsThreshold , orderTotal, upperRewardsThreshold, rewardPointsForUpperThreshold));
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
