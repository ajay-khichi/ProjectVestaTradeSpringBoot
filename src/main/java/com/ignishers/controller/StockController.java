package com.ignishers.controller;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ignishers.daoimpl.StockDaoImpl;
import com.ignishers.pojo.Stock;

@Controller
public class StockController {

	@Autowired
	private StockDaoImpl stockdao;
	
//	-----------------------------------------------------
	@GetMapping("/amdstock")
	public String amdstockpage() {
	    return "amdstock";
	}

//	-----------------------------------------------------
	@PostMapping("/amdstock")
	public ModelAndView amdstock(
	        @RequestParam String symbol,
	        @RequestParam String companyName,
	        @RequestParam double price,
	        @RequestParam long shares,
	        @RequestParam String action) {

	    ModelAndView mv;

	    Stock stock = stockdao.getStock(symbol);

	    if(action.equals("Add")) {

	        Stock s = new Stock();
	        s.setSymbol(symbol);
	        s.setCompanyName(companyName);
	        s.setCurrentPrice(new java.math.BigDecimal(price));
	        s.setPreviousClose(new java.math.BigDecimal(price));
	        s.setAvailableShares(shares);

	        if(stockdao.addStock(s))
	            mv = new ModelAndView("amdstock","msg","Stock Added");
	        else
	            mv = new ModelAndView("amdstock","msg","Stock Not Added");
	    }

	    else if(action.equals("Update")) {

	        if(stock!=null) {
	            stock.setCompanyName(companyName);
	            stock.setCurrentPrice(new java.math.BigDecimal(price));
	            stock.setAvailableShares(shares);

	            stockdao.updateStock(stock);

	            mv = new ModelAndView("amdstock","msg","Stock Updated");
	        }
	        else {
	            mv = new ModelAndView("amdstock","msg","Stock Not Found");
	        }
	    }

	    else if(action.equals("Delete")) {

	        if(stockdao.deleteStock(stock))
	            mv = new ModelAndView("amdstock","msg","Stock Deleted");
	        else
	            mv = new ModelAndView("amdstock","msg","Stock Not Found");
	    }

	    else {
	        mv = new ModelAndView("amdstock");
	    }

	    return mv;
	}
	
//	-----------------------------------------------------
	@PostMapping("/getstockdetail")
	public ModelAndView getstock(@RequestParam String symbol) {

	    Stock stock = stockdao.getStock(symbol);

	    if(stock == null)
	        return new ModelAndView("getstock","msg","Stock Not Found");

	    return new ModelAndView("getstk","stk",stock);
	}
	
	@PostMapping("/updateStockPrice")
	public ModelAndView updateStockPrice(
			@RequestParam String symbol,
			@RequestParam BigDecimal newPrice,
			Model model) {
		ModelAndView mv= null;
		Stock stk = stockdao.getStock(symbol);
		stk.setPreviousClose(stk.getCurrentPrice());
		stk.setCurrentPrice(newPrice);
		mv =  new ModelAndView("adminhome");
		if(stockdao.updateStock(stk)) {
			mv.addObject("msg1", "Stock Price Updated Successfully.");
			
		}
		else {
			mv.addObject("msg1", "Stock Price Could Not Be Updated!");
		}
		mv.addObject("stocks" ,stockdao.getAllStocks());
		return mv;
	}
	
	
}
