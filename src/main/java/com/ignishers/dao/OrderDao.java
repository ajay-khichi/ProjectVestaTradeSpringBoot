package com.ignishers.dao;

import java.util.List;
import com.ignishers.pojo.Order;

public interface OrderDao {
    boolean addOrder(Order order);
    List<Order> getOrdersByCustomerId(Long customerId);
}
