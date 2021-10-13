package com.fdmgroup.FxTrading.model;

import java.math.BigDecimal;
import java.sql.Timestamp;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 
 * @author ben.filbert
 *
 */
@Entity
@Component
@Table(name = "FX_Trading_TradeTest")
public class Trade {
	
	@Id
	@GeneratedValue
	@Column(insertable=false,updatable=false)
	private int trade_id;

	private int user_id;
	
	private int currencyfrom_id;

	
	private int currencyto_id;
	
	@Enumerated(EnumType.ORDINAL)
	private LotSizeEnum lotSize;
	
	@Column(nullable = false, name = "Trade_Date", columnDefinition = "TIMESTAMP")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Timestamp date;
	
	private BigDecimal profit;

	public Trade() {
		super();
	}
	
	public Trade(int user_id, int currencyfrom_id, int currencyto_id, LotSizeEnum lotSize, @NotEmpty int date,
			@NotEmpty BigDecimal profit) {
		super();
		this.user_id = user_id;
		this.currencyfrom_id = currencyfrom_id;
		this.currencyto_id = currencyto_id;
		this.lotSize = lotSize;
		this.date = new Timestamp(0);
		this.date.setNanos(date);
		this.profit = profit;
	}

	public int getTrade_id() {
		return trade_id;
	}

	public void setTrade_id(int trade_id) {
		this.trade_id = trade_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getCurrencyfrom_id() {
		return currencyfrom_id;
	}

	public void setCurrencyfrom_id(int currencyfrom_id) {
		this.currencyfrom_id = currencyfrom_id;
	}

	public int getCurrencyto_id() {
		return currencyto_id;
	}

	public void setCurrencyto_id(int currencyto_id) {
		this.currencyto_id = currencyto_id;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public BigDecimal getProfit() {
		return profit;
	}

	public void setProfit(BigDecimal profit) {
		this.profit = profit;
	}
	
	public LotSizeEnum getLotSize() {
		return lotSize;
	}

	public void setLotSize(LotSizeEnum lotSize) {
		this.lotSize = lotSize;
	}

	@Override
	public String toString() {
		return "Trade [trade_id=" + trade_id + ", user_id=" + user_id + ", currencyfrom_id=" + currencyfrom_id
				+ ", currencyto_id=" + currencyto_id + ", lotSize=" + lotSize + ", date=" + date.toString() + ", profit=" + profit
				+ "]";
	}
}

