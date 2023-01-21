package com.example.retailcorewards.services;


import com.example.retailcorewards.web.model.CustomerDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public interface CustomerService {

    List<CustomerDto> getAllCustomers();

    Optional<CustomerDto> getCustomerById(UUID customerId);

    void addCustomer(CustomerDto customer);

    void deleteCustomer(CustomerDto customer);

}
