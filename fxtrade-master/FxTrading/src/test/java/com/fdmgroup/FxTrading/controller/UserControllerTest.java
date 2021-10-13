package com.fdmgroup.FxTrading.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.fdmgroup.FxTrading.model.Currency;
import com.fdmgroup.FxTrading.model.LotSizeEnum;
import com.fdmgroup.FxTrading.model.Trade;
import com.fdmgroup.FxTrading.repository.CurrencyRepository;
import com.fdmgroup.FxTrading.repository.TradeRepository;

public class UserControllerTest {

	 	@InjectMocks
	    UserController userController;
		
	    @Mock
	    TradeRepository tradeRepo;
	    
	    @Mock
	    CurrencyRepository currencyRepo;
	    
	    Trade trade;

	    int userId;
	    int currencyFromId;
	    int currencyToId;
	    LotSizeEnum lotSize;
	    DateFormat dateFormat;
	    Date dateObj;
	    long time;
	    Timestamp date;
	    BigDecimal profit;
	    
	    @Before
	    public void init() throws ParseException {
	        MockitoAnnotations.openMocks(this);
	        userId = 1;
	        currencyFromId = 0;
	        currencyToId = 1;
	        lotSize = LotSizeEnum.MICRO;
	        dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	        dateObj = dateFormat.parse("23/09/2007");
	        time = dateObj.getTime();
	        date = new Timestamp(time);
	        profit = BigDecimal.valueOf(100);
	        trade = new Trade(userId, currencyFromId, currencyToId, lotSize, date.getNanos(), profit);
	    }

		@Test
		public void testGetUserTradeHistory() {
			List<Trade> list = new ArrayList<>();
			list.add(trade);
			Mockito.when(tradeRepo.findTradeByUserID2(1)).thenReturn(list);
			Currency currency = new Currency();
	    	currency.setCurrency_id(0);
	    	Currency currency2 = new Currency();
	    	currency2.setCurrency_id(1);
	    	Optional<Currency> c = Optional.of(currency);
	    	Optional<Currency> c2 = Optional.of(currency2);
	    	Mockito.when(currencyRepo.findById(0)).thenReturn(c);
	    	Mockito.when(currencyRepo.findById(1)).thenReturn(c2);
			List<List<String>> table = userController.getTradeData("1");
			assertEquals(table.size(), 1);
		}


}
