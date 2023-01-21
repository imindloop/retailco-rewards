package com.example.retailcorewards.repositories;

import com.example.retailcorewards.web.model.CustomerDto;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<CustomerDto, String> {



}
