package com.fdmgroup.FxTrading.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;

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
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

import com.fdmgroup.FxTrading.model.Currency;
import com.fdmgroup.FxTrading.model.Exchange;
import com.fdmgroup.FxTrading.model.LotSizeEnum;
import com.fdmgroup.FxTrading.model.Trade;
import com.fdmgroup.FxTrading.repository.CurrencyRepository;
import com.fdmgroup.FxTrading.repository.ExchangeRateRepository;
import com.fdmgroup.FxTrading.repository.TradeRepository;

public class TradeControllerTest {

    @InjectMocks
    TradeController tradeController;

    @Mock
    TradeRepository tradeRepo;

    @Mock
    CurrencyRepository currencyRepo;

    @Mock
    ExchangeRateRepository exchangeRepo;
    
    @Mock
    Model model;

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

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
    public void testFindTradeById() throws Exception {

        Mockito.when(tradeRepo.findTradeByID(1)).thenReturn(Optional.of(trade));

        Optional<Trade> foundTrade = tradeController.findTradeById("1");

        Mockito.verify(tradeRepo, times(1)).findTradeByID(1);
        assertEquals("Optional[" + trade.toString() + "]", foundTrade.toString());

    }

    @Test
    public void testFindByUserId() throws Exception {

        List<Trade> trades = new ArrayList<>();
        trades.add(trade);

        Mockito.when(tradeRepo.findTradeByUserID("1")).thenReturn(trades);

        List<Trade> foundTrades = tradeController.findTradeByUserId("1");

        Mockito.verify(tradeRepo, times(1)).findTradeByUserID("1");
        assertEquals(trades.toString(), foundTrades.toString());

    }

    @Test
    public void testFindTradeByIdWhenTradeIdIsNotPresent() throws Exception {
        expectedEx.expect(Exception.class);
        expectedEx.expectMessage("Trade ID does not exist.");

        Mockito.when(tradeRepo.findTradeByID(1)).thenReturn(Optional.empty());
        tradeController.findTradeById("1");
    }

    @Test
    public void testFindTradeByUserIdWhenUserHasNoTrades() throws Exception {
        expectedEx.expect(Exception.class);
        expectedEx.expectMessage("No trades found for the given user.");

        Mockito.when(tradeRepo.findTradeByUserID("1")).thenReturn(new ArrayList());
        tradeController.findTradeByUserId("1");
    }
    
    @Test
    public void testBuyTrade() {
    	tradeController.getIsBuy("Buy");
    	model = new ExtendedModelMap();
    	Currency currency = new Currency();
    	currency.setCurrency_id(0);
    	Currency currency2 = new Currency();
    	currency2.setCurrency_id(1);
    	currencyRepo.save(currency);
    	currencyRepo.save(currency2);
    	Optional<Currency> c = Optional.of(currency);
    	Optional<Currency> c2 = Optional.of(currency2);
    	Mockito.when(currencyRepo.findById(0)).thenReturn(c);
    	Mockito.when(currencyRepo.findById(1)).thenReturn(c2);
    	List<Exchange> list = new ArrayList<Exchange>();
    	Exchange exchange = new Exchange();
    	exchange.setCurrencyFrom_id(currency);
    	exchange.setCurrencyTo_id(currency2);
    	exchangeRepo.save(exchange);
    	list.add(exchange);
    	Mockito.when(exchangeRepo.findByIds(exchange.getCurrencyFrom_id(), exchange.getCurrencyTo_id())).thenReturn(list);
    	tradeController.saveTrade(model, trade);
    	Mockito.verify(tradeRepo, times(1)).save(trade);
    }

}
