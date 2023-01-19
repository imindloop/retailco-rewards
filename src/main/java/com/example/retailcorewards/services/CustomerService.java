package com.example.retailcorewards.services;


import com.example.retailcorewards.web.model.CustomerDto;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface CustomerService {
    CustomerDto getCustomerById(UUID customerId);
}
