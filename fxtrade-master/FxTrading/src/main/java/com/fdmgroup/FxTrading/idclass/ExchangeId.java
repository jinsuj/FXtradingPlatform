package com.fdmgroup.FxTrading.idclass;

import java.io.Serializable;

import com.fdmgroup.FxTrading.model.Currency;

public class ExchangeId implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Currency currencyFrom_id;
	private Currency currencyTo_id;
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((currencyFrom_id == null) ? 0 : currencyFrom_id.hashCode());
		result = prime * result + ((currencyTo_id == null) ? 0 : currencyTo_id.hashCode());
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
		ExchangeId other = (ExchangeId) obj;
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
		return true;
	}
	
	
}
