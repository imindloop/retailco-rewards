package com.example.retailcorewards.services;

import com.example.retailcorewards.repositories.CustomerRepository;
import com.example.retailcorewards.web.model.CustomerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    /**
     *
     * @return
     */
    @Override
    public List<CustomerDto> getAllCustomers() {
        List<CustomerDto> customers = new ArrayList<>();
        customerRepository.findAll().forEach(customers::add);
        return customers;
    }

    /**
     *
     * @param customer
     */
    @Override
    public void addCustomer(CustomerDto customer) {
        customerRepository.save(customer);
    }

    /**
     *
     * @param customerId
     * @return
     */
    @Override
    public Optional<CustomerDto> getCustomerById(UUID customerId) {
        return customerRepository.findById(customerId.toString());
    }

    /**
     *
     * @param customer
     */
    public void deleteCustomer(CustomerDto customer) {
        customerRepository.delete(customer);
    }

}
