package com.example.retailcorewards.services;

import com.example.retailcorewards.repositories.CustomerRepository;
import com.example.retailcorewards.web.model.CustomerDto;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    /**
     *
     * @return
     */
    @Override
    public List<CustomerDto> getAllCustomers() {
        return customerRepository.findAll();
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
     * @param customer
     */
    public void deleteCustomer(CustomerDto customer) {
        customerRepository.delete(customer);
    }
}
