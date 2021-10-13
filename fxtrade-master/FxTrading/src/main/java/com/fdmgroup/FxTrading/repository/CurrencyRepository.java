package com.fdmgroup.FxTrading.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fdmgroup.FxTrading.model.Currency;

/**
 * 
 * @author ben.filbert
 *
 */
@Repository
public interface CurrencyRepository extends JpaRepository<Currency,Integer> {

	@Query("select c from Currency c where c.symbol = :searchTerm  ")
	Optional<Currency> findBySymbol(@Param("searchTerm") String searchTerm);

	@Query("select c from Currency c where c.currency_id = :searchTerm  ")
	Optional<Currency> findById(@Param("searchTerm") int searchTerm);
}
