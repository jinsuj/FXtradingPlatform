package com.fdmgroup.FxTrading.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fdmgroup.FxTrading.model.Currency;
import com.fdmgroup.FxTrading.model.Trade;
import com.fdmgroup.FxTrading.model.User;
import com.fdmgroup.FxTrading.repository.CurrencyRepository;
import com.fdmgroup.FxTrading.repository.TradeRepository;
import com.fdmgroup.FxTrading.repository.UserRepository;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")
public class UserController {
	
	@Autowired
	private TradeRepository tradeRepo;
	
	@Autowired
	private UserRepository userRepo;

	@Autowired
	private CurrencyRepository currencyRepo;
	
	@PostMapping("/getTradeData")
	public List<List<String>> getTradeData(@RequestBody String userId) {
		List<Trade> list = tradeRepo.findTradeByUserID2(Integer.valueOf(userId));
		List<List<String>> table = new ArrayList<List<String>>(list.size());
		for (int i = 0; i < list.size(); i++) {
			 table.add(new ArrayList<String>());
			 Optional<Currency> fromCurrency = currencyRepo.findById(list.get(i).getCurrencyfrom_id());
			 Optional<Currency> toCurrency = currencyRepo.findById(list.get(i).getCurrencyto_id());
			 table.get(i).add(String.valueOf(list.get(i).getTrade_id()));
			 table.get(i).add(fromCurrency.get().getSymbol());
			 table.get(i).add(toCurrency.get().getSymbol());
			 table.get(i).add(String.valueOf(list.get(i).getDate()));
			 table.get(i).add(String.valueOf(list.get(i).getLotSize().getSize()));
			 table.get(i).add(String.valueOf(list.get(i).getProfit()));
		}
		return table;
	}
	
	@PostMapping("/getTotalProfit")
	public double getTotalProfit(@RequestBody String userId) {
		System.out.println(userId);
		BigDecimal total = new BigDecimal(0);
		List<Trade> list = tradeRepo.findTradeByUserID2(Integer.valueOf(userId));
		for (int i = 0; i < list.size(); i++) {
			total = total.add(list.get(i).getProfit());
		}
		System.out.println(total.doubleValue());
		return total.doubleValue();
	}

	@PostMapping("/getUserData")
	private User getUserData(@RequestBody String userId) {
		Optional<User> user = userRepo.findById(Integer.parseInt(userId));
		System.out.println(user.get());
		return user.get();
	}
	
	@GetMapping("/getUserList")
	private List<List<String>> getUserList() {
		List<User> list = userRepo.findAll();
		List<List<String>> table = new ArrayList<List<String>>(list.size());
		for (int i = 0; i < list.size(); i++) {
			table.add(new ArrayList<String>());
			table.get(i).add(String.valueOf(list.get(i).getUser_id()));
			table.get(i).add(list.get(i).getFirst_name());
			table.get(i).add(list.get(i).getLast_name());
			table.get(i).add(list.get(i).getEmail());
		}
		return table;
	}


}
