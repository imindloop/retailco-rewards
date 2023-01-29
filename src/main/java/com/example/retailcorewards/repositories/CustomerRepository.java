package com.example.retailcorewards.repositories;

import com.example.retailcorewards.web.model.CustomerDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerDto, String> {

}
