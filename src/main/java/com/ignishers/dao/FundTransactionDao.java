package com.ignishers.dao;

import java.util.List;

import com.ignishers.pojo.FundTransaction;

public interface FundTransactionDao {
	boolean addTransaction(FundTransaction ft);
	List<FundTransaction> getAllTransaction(Long walletId);
}
