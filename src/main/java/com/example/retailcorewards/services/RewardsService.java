package com.example.retailcorewards.services;

import com.example.retailcorewards.web.model.OrderDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface RewardsService {
    Map<String, Map<String, Integer>> getRewardPoints(List<OrderDto> orders) throws Exception;
}
