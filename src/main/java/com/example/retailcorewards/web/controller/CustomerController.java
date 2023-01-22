package com.example.retailcorewards.web.controller;

import com.example.retailcorewards.services.CustomerService;
import com.example.retailcorewards.web.model.CustomerDto;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequestMapping("/api/v1/customers")
@RestController
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public List<CustomerDto> getCustomers() {
        return customerService.getAllCustomers();
    }

    @PostMapping()
    public void addCustomer(@RequestBody CustomerDto customer) {
        customerService.addCustomer(customer);
    }

    @GetMapping("/{customerId}")
    public Optional<CustomerDto> getCustomer(@PathVariable UUID customerId) {
        return customerService.getCustomerById(customerId);
    }
}
