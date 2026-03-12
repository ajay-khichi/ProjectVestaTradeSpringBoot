package com.ignishers.repository;

import org.springframework.data.repository.CrudRepository;

import com.ignishers.pojo.StockPriceHistory;
import com.ignishers.pojo.User;


public interface StockPriceHistoryRepository extends CrudRepository<StockPriceHistory, Long>{

}
