package com.ignishers.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.ignishers.pojo.Admin;
import com.ignishers.pojo.User;


public interface AdminRepository extends CrudRepository<User, Long>{

	Optional<Admin> findByEmail(String email);
}
