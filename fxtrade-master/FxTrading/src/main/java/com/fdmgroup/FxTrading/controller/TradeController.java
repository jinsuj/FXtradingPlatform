package com.fdmgroup.FxTrading.controller;

import java.math.BigDecimal;
import java.sql.Timestamp;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fdmgroup.FxTrading.model.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.fdmgroup.FxTrading.model.Currency;
import com.fdmgroup.FxTrading.model.Exchange;
import com.fdmgroup.FxTrading.model.Trade;
import com.fdmgroup.FxTrading.repository.CurrencyRepository;
import com.fdmgroup.FxTrading.repository.ExchangeRateRepository;
import com.fdmgroup.FxTrading.repository.TradeRepository;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")
public class TradeController {
	
	@Autowired
	private TradeRepository tradeRepo;
	
	@GetMapping
	public String toTrade(Model model, @Autowired User buyUser, @Autowired User sellUser, @Autowired Trade trade) {
		model.addAttribute("buyUser", buyUser);
		model.addAttribute("sellUser", sellUser);
		model.addAttribute("trade",trade);
		return "redirect:/trade";
	}
	
	@Autowired
	private CurrencyRepository currencyRepo;
	
	@Autowired
	private ExchangeRateRepository exchangeRepo;
	
	private boolean isBuy;
	private boolean isSell;
	
	@PostMapping("/getExchangeData")
	private List<List<String>> getExchangeData() {
		List<Exchange> list = exchangeRepo.findAll();
		List<List<String>> table = new ArrayList<List<String>>(list.size());
		for (int i = 0; i < list.size(); i++) {
			 table.add(new ArrayList<String>());
			 table.get(i).add(String.valueOf(list.get(i).getBuy_price()));
			 table.get(i).add(String.valueOf(list.get(i).getSell_price()));
			 table.get(i).add(list.get(i).getSpread());
			 table.get(i).add(String.valueOf(list.get(i).getExchange_rate()));
			 table.get(i).add(list.get(i).getCurrencyFrom_id().getSymbol());
			 table.get(i).add(list.get(i).getCurrencyTo_id().getSymbol());
		}
		System.out.println(table.get(0).get(0));
		System.out.println(table.get(0).get(1));
		System.out.println(table.get(0).get(2));
		System.out.println(table.get(0).get(3));
		// index 0 will contain buy price
		// index 1 will contain sell price
		// index 2 will contain spread
		// index 3 will contain exchange rate
		return table;
	}
	
	@PostMapping("/listCurrencies")
	private List<String[]> getCurrencies() {
		List<Currency> currencyList = currencyRepo.findAll();
		List<String[]> list = new ArrayList<String[]>();
		for (int i = 0; i < currencyList.size(); i++) {
			String[] temp = { currencyList.get(i).getSymbol(), Integer.toString( currencyList.get(i).getCurrency_id()) };
			list.add( temp  );
		}
		return list;
	}
	
	@PostMapping("/trade")
	public void saveTrade(Model model, @Autowired @RequestBody Trade trade) {

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSSSSS");
		LocalDateTime now = LocalDateTime.now();
		String n = dtf.format(now);
		Timestamp timestamp = Timestamp.valueOf(n);
		trade.setDate(timestamp);
		Optional<Currency> currencyFrom = currencyRepo.findById(trade.getCurrencyfrom_id());
		Optional<Currency> currencyTo = currencyRepo.findById(trade.getCurrencyto_id());
		List<Exchange> list = exchangeRepo.findByIds(currencyFrom.get(), currencyTo.get());
		if (this.isBuy) {
			BigDecimal bd = new BigDecimal(0);
			trade.setProfit(bd);
		} else {
			BigDecimal bd = new BigDecimal(Double.valueOf(list.get(0).getSpread()) * trade.getLotSize().getSize());
			trade.setProfit(bd);
		}
		tradeRepo.save(trade);
	}

	@PostMapping("/getCurrencyId")
	private List<Integer> sendCurrencies(@RequestBody String currencies) {
		System.out.println(currencies);
		String[] array = currencies.split(":");
		System.out.println(array[1].substring(3, 6));
		System.out.println(array[2].substring(3, 6));
		String from = array[1].substring(3,6);
		String to = array[2].substring(3,6);
		Optional<Currency> fromCurrency = currencyRepo.findBySymbol(from);
		Optional<Currency> toCurrency = currencyRepo.findBySymbol(to);
		List<Integer> list = new ArrayList<Integer>();
		System.out.println(fromCurrency.get().getCurrency_id());
		list.add(fromCurrency.get().getCurrency_id());
		list.add(toCurrency.get().getCurrency_id());
		return list;
	}

	@PostMapping("/findTradeById")
	public Optional<Trade> findTradeById(@RequestBody String id) throws Exception {
		Optional<Trade> trade = tradeRepo.findTradeByID(Integer.parseInt(id));
		if (!trade.isPresent()){
			throw new Exception("Trade ID does not exist.");
		}
		return trade;
	}

	@PostMapping("/findTradeByUserId")
	public List<Trade> findTradeByUserId(@RequestBody String userId) throws Exception {
		List<Trade> trades = tradeRepo.findTradeByUserID(userId);
		if (trades.isEmpty()) {
			throw new Exception("No trades found for the given user.");
		}
		return trades;
	}
	
	@PostMapping("/getIsBuy")
	public void getIsBuy(@RequestBody String isBuy) {
		isBuy = isBuy.replace("\"", "");
		this.isBuy = Boolean.parseBoolean(isBuy);
	}

	@PostMapping("/getIsSell")
	public void getIsSell(@RequestBody String isSell) {
		isSell = isSell.replace("\"", "");
		this.isSell = Boolean.parseBoolean(isSell);
	}
	
	@PostMapping("/cancelTradeById")
	public void cancelTrade(@RequestBody String id)  throws Exception{
		Optional<Trade> trade = tradeRepo.findTradeByIntID(Integer.valueOf(id));
		if (!trade.isPresent()){
			throw new Exception("Trade ID does not exist.");
		}
		tradeRepo.delete(trade.get());
	}

	@PostMapping("/findCurrencySymbolById")
	public Optional<Currency> findCurrencySymbolById(@RequestBody String id)  throws Exception{
		Optional<Currency> currency = currencyRepo.findById(Integer.parseInt(id));
		if (!currency.isPresent()){
			throw new Exception("Currency ID does not exist.");
		}
		return currency;
	}
}
