package com.example.retailcorewards.services;

import com.example.retailcorewards.web.model.CustomerDto;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Override
    public CustomerDto getCustomerById(UUID customerId) {
        return CustomerDto.builder()
                .id(UUID.randomUUID())
                .email("customer@example.com")
                .firstName("customer name")
                .lastName("customer last name")
                .build();
    }

}
