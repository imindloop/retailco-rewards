package com.example.retailcorewards.web.controller;

import com.example.retailcorewards.services.RewardsService;
import org.springframework.beans.factory.annotation.Autowired;

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

    @Autowired
    private RewardsService rewardsService;

    @RequestMapping
    public Map<String, Map<String, Integer>> getRewardPoints() {
        return rewardsService.getRewardPoints();
    }

}
