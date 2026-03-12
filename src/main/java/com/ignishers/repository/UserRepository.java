package com.ignishers.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ignishers.pojo.User;

public interface UserRepository extends CrudRepository<User, Long>{


	
	Optional<User> findByEmail(String email);
}
