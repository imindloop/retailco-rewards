package com.example.retailcorewards.services;


import com.example.retailcorewards.web.model.CustomerDto;

import java.util.UUID;

public interface CustomerService {
    CustomerDto getCustomerById(UUID customerId);
}
