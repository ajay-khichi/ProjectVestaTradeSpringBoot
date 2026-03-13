package com.ignishers.daoimpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ignishers.dao.OrderDao;
import com.ignishers.pojo.Order;
import com.ignishers.repository.OrderRepository;

import jakarta.transaction.Transactional;

@Repository
public class OrderDaoImpl implements OrderDao {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    @Transactional
    public boolean addOrder(Order order) {
        try {
            orderRepository.save(order);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    @Transactional
    public List<Order> getOrdersByCustomerId(Long customerId) {
        try {
            return orderRepository.findByCustomerId(customerId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
