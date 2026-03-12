package com.ignishers.repository;

import org.springframework.data.repository.CrudRepository;

import com.ignishers.pojo.Order;



public interface OrderRepository extends CrudRepository<Order, Long>{

}
