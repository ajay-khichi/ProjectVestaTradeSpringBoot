package com.ignishers.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.ignishers.pojo.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long>{
	Optional<Customer> findByEmail(String email);
}
