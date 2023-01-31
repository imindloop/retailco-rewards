package com.example.retailcorewards.repositories;

import com.example.retailcorewards.web.model.OrderDto;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface OrderRepository extends CrudRepository<OrderDto, String> {

    public List<OrderDto> findAll();


}
