package com.example.retailcorewards.repositories;

import com.example.retailcorewards.web.model.OrderDto;
import org.hibernate.criterion.Order;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface OrderRepository extends CrudRepository<OrderDto, String> {

    /* We need a method that takes a customerId and returns all the orders for that customer.
     */
    public List<OrderDto> findAllByCustomerId(String customerId);

    public List<OrderDto> findAll();


}
