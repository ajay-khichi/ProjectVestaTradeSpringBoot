package com.ignishers.daoimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.ignishers.dao.UserDao;
import com.ignishers.pojo.User;
import com.ignishers.repository.UserRepository;

@Repository
public class UserDaoImpl implements UserDao{

	
	@Autowired
	private UserRepository uRepository;


	

	@Override
	public User checkUserCred(String email, String pass) {
		try {
			Optional<User> op = uRepository.findByEmail(email);
			if(op.isPresent()) {
				if(op.get().getPassword().equals(pass))
					return op.get();
				else
					return null;
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	
	
	@Override
	public List<User> getAllUser() {
		try {
			List<User>lst = (List<User>) uRepository.findAll();
			return lst;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	@Transactional
	public User checkExistence(String email) {
	    try {
	    	Optional<User> op = uRepository.findByEmail(email);
	    	if(op.isPresent())
	    		return op.get();
	    	return null;

	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    }
	}

	@Override
	@Transactional
	public boolean updateUser(User user) {
		try {
			Optional<User> op = uRepository.findByEmail(user.getEmail());
	    	if(op.isPresent()) {
	    		uRepository.save(user);
	    		return true;
	    	}
	    	return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	@Transactional
	public boolean deleteUser(User user) {
		try {
			Optional<User> op = uRepository.findByEmail(user.getEmail());
	    	if(op.isPresent()) {
	    		uRepository.delete(user);
	    		return true;
	    	}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
