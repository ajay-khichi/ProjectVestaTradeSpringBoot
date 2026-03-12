package com.ignishers.repository;

import org.springframework.data.repository.CrudRepository;

import com.ignishers.pojo.Stock;
import com.ignishers.pojo.StockPriceHistory;


public interface StockRepository extends CrudRepository<Stock, String>{

}
