package com.ignishers.daoimpl;

import java.util.List;
import java.util.Optional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ignishers.dao.CustomerDao;
import com.ignishers.pojo.Customer;
import com.ignishers.pojo.User;
import com.ignishers.repository.CustomerRepository;

@Repository
public class CustomerDaoImpl implements CustomerDao{

	@Autowired
	private CustomerRepository cRepository;


	@Override
	@Transactional
	public boolean addCustomer(Customer cst) {
		try {
			Optional<Customer> op = cRepository.findByEmail(cst.getEmail());
			if(op.isEmpty()) {
				cRepository.save(cst);
				return true;
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
}
