package com.ignishers.daoimpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ignishers.dao.AdminDao;
import com.ignishers.pojo.Admin;
import com.ignishers.repository.AdminRepository;

@Repository
public class AdminDaoImpl implements AdminDao{
	
	@Autowired
	private AdminRepository aRepository;
	


	@Override
	@Transactional
	public boolean addAdmin(Admin admin) {
		try {
			Optional<Admin> op = aRepository.findByEmail(admin.getEmail());
			if(op.isEmpty()) {
				aRepository.save(admin);
				return true;
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
}
