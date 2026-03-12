package com.ignishers.daoimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ignishers.dao.StockDao;
import com.ignishers.pojo.Stock;
import com.ignishers.repository.StockRepository;

import jakarta.transaction.Transactional;

@Repository
public class StockDaoImpl implements StockDao {

   @Autowired
   private StockRepository sRepository;
   
    @Override
    @Transactional
    public boolean addStock(Stock stock) {
        try {
        	Optional<Stock> op =  sRepository.findById(stock.getSymbol());
        	if(op.isEmpty()) {
        		sRepository.save(stock);
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
    public boolean updateStock(Stock stock) {
        try {
        	Optional<Stock> op =  sRepository.findById(stock.getSymbol());
        	if(op.isPresent()) {
        		sRepository.save(stock);
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
    public boolean deleteStock(Stock stock) {
        try {
        	Optional<Stock> op =  sRepository.findById(stock.getSymbol());
        	if(op.isPresent()) {
        		sRepository.delete(stock);
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
    public Stock getStock(String symbol) {
        try {
            Optional<Stock> op = sRepository.findById(symbol);
            if(op.isPresent()) {
            	return op.get();
            }
            return null;
        } catch (Exception e) {
        	e.printStackTrace();
            return null;
        }
    }

    @Override
    @Transactional
    public List<Stock> getAllStocks() {
    	try {
			List<Stock> lst = (List<Stock>) sRepository.findAll();
			return lst;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
    }
}