package com.ignishers.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.ignishers.daoimpl.StockDaoImpl;
import com.ignishers.daoimpl.UserDaoImpl;
import com.ignishers.pojo.Stock;

@Controller
public class CustomerController {
	@Autowired
	private UserDaoImpl userdao;
	
	@Autowired
	public StockDaoImpl stockdao;
	
	@GetMapping("/customerhome")
	public String customerhomepage(Model model) {
		List<Stock> lst = stockdao.getAllStocks();
		model.addAttribute("stocks", lst);
		return "customerhome";
	}
	@GetMapping("/portfolio")
	public String portfoliopage() {
		return "portfolio";		
	}
	@GetMapping("/wallet")
	public String walletpage() {
		return "wallet";		
	}
	@GetMapping("/editprofile")
	public String editprofilepage() {
		return "editprofile";		
	}
	@PostMapping("/trading")
	public String tradingpage() {
		return "trading";		
	}
	
}
