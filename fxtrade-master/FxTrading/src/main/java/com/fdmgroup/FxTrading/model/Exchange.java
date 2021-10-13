package com.fdmgroup.FxTrading.model;

import java.io.Serializable;
import java.math.RoundingMode;
import java.text.DecimalFormat;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import org.springframework.stereotype.Component;

import com.fdmgroup.FxTrading.idclass.ExchangeId;

@Entity
@Component

@IdClass(ExchangeId.class)
public class Exchange implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn(name="currency_id")
	private Currency currencyFrom_id;
	
	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn(name="currency_id")
	private Currency currencyTo_id;

	private double exchange_rate;
	private double sell_price;
	private double buy_price;
	private double spread;
	
	public Exchange() {
		super();
	}

	public Exchange(Currency currencyFrom_id, Currency currencyTo_id, double exchange_rate, double sell_price, double buy_price,
			double spread) {
		super();
		this.currencyFrom_id = currencyFrom_id;
		this.currencyTo_id = currencyTo_id;
		this.exchange_rate = exchange_rate;
		this.sell_price = sell_price;
		this.buy_price = buy_price;
		this.spread = spread;
	}

	public Currency getCurrencyFrom_id() {
		return currencyFrom_id;
	}

	public void setCurrencyFrom_id(Currency currencyFrom_id) { 
		this.currencyFrom_id = currencyFrom_id;
	}

	public Currency getCurrencyTo_id() {
		return currencyTo_id;
	}

	public void setCurrencyTo_id(Currency currencyTo_id) {
		this.currencyTo_id = currencyTo_id;
	}

	public double getExchange_rate() {
		return exchange_rate;
	}

	public void setExchange_rate(double exchange_rate) {
		this.exchange_rate = exchange_rate;
	}

	public double getSell_price() {
		return sell_price;
	}

	public void setSell_price(double sell_price) {
		this.sell_price = sell_price;
	}

	public double getBuy_price() {
		return buy_price;
	}

	public void setBuy_price(double buy_price) {
		this.buy_price = buy_price;
	}

	public String getSpread() {
		DecimalFormat df = new DecimalFormat("#.####");
		df.setRoundingMode(RoundingMode.UP);
		Double d = this.sell_price - this.buy_price;
		return df.format(d);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(buy_price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((currencyFrom_id == null) ? 0 : currencyFrom_id.hashCode());
		result = prime * result + ((currencyTo_id == null) ? 0 : currencyTo_id.hashCode());
		temp = Double.doubleToLongBits(exchange_rate);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(sell_price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(spread);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Exchange other = (Exchange) obj;
		if (Double.doubleToLongBits(buy_price) != Double.doubleToLongBits(other.buy_price))
			return false;
		if (currencyFrom_id == null) {
			if (other.currencyFrom_id != null)
				return false;
		} else if (!currencyFrom_id.equals(other.currencyFrom_id))
			return false;
		if (currencyTo_id == null) {
			if (other.currencyTo_id != null)
				return false;
		} else if (!currencyTo_id.equals(other.currencyTo_id))
			return false;
		if (Double.doubleToLongBits(exchange_rate) != Double.doubleToLongBits(other.exchange_rate))
			return false;
		if (Double.doubleToLongBits(sell_price) != Double.doubleToLongBits(other.sell_price))
			return false;
		if (Double.doubleToLongBits(spread) != Double.doubleToLongBits(other.spread))
			return false;
		return true;
	}
	
	
}
