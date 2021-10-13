package com.fdmgroup.FxTrading.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.springframework.stereotype.Component;
/**
 * 
 * @author ben.filbert
 *
 */
@Entity
@Component
@Table(name = "FX_trading_Currency2")
public class Currency {

	@Id
	@GeneratedValue
	@Column(insertable=false,updatable=false)
	private int currency_id;
	
	
	@NotEmpty
	private String name;
	@NotEmpty
	private String symbol;
	
	public Currency() {
		super();
	}
	
	public Currency(String name, String symbol) {
		this.name = name;
		this.symbol= symbol;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	
	public void setCurrency_id(int id) {
		this.currency_id = id;
	}

	public int getCurrency_id() {
		return currency_id;
	}

	@Override
	public String toString() {
		return "Currency [currency_id=" + currency_id + ", name=" + name + ", symbol=" + symbol + "]";
	}
	
	
	
}
