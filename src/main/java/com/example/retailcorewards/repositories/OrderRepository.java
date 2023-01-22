package com.example.retailcorewards.repositories;

import com.example.retailcorewards.web.model.OrderDto;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;


public interface OrderRepository extends CrudRepository<OrderDto, String> {

    /* We need a method that takes a customerId and returns all the orders for that customer.
     */
    public List<OrderDto> findAllByCustomerId(String customerId);


}
