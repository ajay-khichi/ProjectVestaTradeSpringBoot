package com.ignishers.repository;

import org.springframework.data.repository.CrudRepository;

import com.ignishers.pojo.StockPriceHistory;


public interface StockPriceHistoryRepository extends CrudRepository<StockPriceHistory, Long>{

}
