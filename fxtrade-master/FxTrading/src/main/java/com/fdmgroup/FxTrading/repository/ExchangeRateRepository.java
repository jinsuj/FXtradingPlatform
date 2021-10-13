package com.fdmgroup.FxTrading.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fdmgroup.FxTrading.idclass.ExchangeId;
import com.fdmgroup.FxTrading.model.Currency;
import com.fdmgroup.FxTrading.model.Exchange;

public interface ExchangeRateRepository extends JpaRepository<Exchange, ExchangeId> {
	@Query("select e from Exchange e where currencyFrom_id = ?1 and currencyTo_id = ?2")
	List<Exchange> findByIds(Currency currencyFrom_id, Currency currencyTo_id);
}
