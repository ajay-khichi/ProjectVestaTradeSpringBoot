package com.ignishers.daoimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ignishers.dao.FundTransactionDao;
import com.ignishers.pojo.FundTransaction;
import com.ignishers.repository.FundTransactionRepository;

@Repository
public class FundTransactionDaoImpl implements FundTransactionDao{
	
	@Autowired
	private FundTransactionRepository ftRepository;

	@Override
	public boolean addTransaction(FundTransaction ft) {
		try {
			ftRepository.save(ft);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<FundTransaction> getAllTransaction(Long walletId) {
		try {
			List<FundTransaction> lst = (List<FundTransaction>) ftRepository.findAll();
			return lst;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	
	

}
