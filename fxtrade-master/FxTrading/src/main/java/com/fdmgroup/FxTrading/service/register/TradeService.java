package com.fdmgroup.FxTrading.service.register;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.fdmgroup.FxTrading.model.LotSizeEnum;
import com.fdmgroup.FxTrading.model.Trade;
import com.fdmgroup.FxTrading.repository.TradeRepository;

@Service
public class TradeService {

	private final TradeRepository tradeRepository;
	private final ApplicationContext applicationContext;

	@Autowired
	public TradeService(TradeRepository tradeRepository, ApplicationContext applicationContext) {
		super();
		this.tradeRepository = tradeRepository;
		this.applicationContext = applicationContext;
	}
	
	public Optional<Trade> createTrade(int user_id, int currencyFrom_id, int currencyTo_id, LotSizeEnum lotSize, Timestamp date, BigDecimal profit) {
		Trade trade = new Trade(user_id, currencyFrom_id, currencyTo_id, lotSize, date.getNanos() , profit);
		System.out.println(trade);
		tradeRepository.save(trade);
		return Optional.of(trade);
	}
}
