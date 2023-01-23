package com.example.retailcorewards.services;


import com.example.retailcorewards.web.model.CustomerDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface CustomerService {

    List<CustomerDto> getAllCustomers();

    Optional<CustomerDto> getCustomerById(String customerId);

    void addCustomer(CustomerDto customer);

    void deleteCustomer(CustomerDto customer);

}
