package com.example.retailcorewards.services;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface RewardsService {
    Map<String, Map<String, Integer>> getRewardPoints();
}
