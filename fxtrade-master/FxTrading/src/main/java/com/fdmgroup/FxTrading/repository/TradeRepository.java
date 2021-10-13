package com.fdmgroup.FxTrading.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.fdmgroup.FxTrading.model.Trade;


/**
 * 
 * @author ben.filbert
 *
 */
@Repository
public interface TradeRepository extends JpaRepository <Trade,Integer> {

	@Query("select t from Trade t where t.trade_id = :searchTerm  ")
	Optional<Trade> findTradeByID(@Param("searchTerm") int searchTerm);
	
	@Query("select t from Trade t where t.trade_id = :searchTerm  ")
	Optional<Trade> findTradeByIntID(@Param("searchTerm") int searchTerm);
	
	@Query("select t from Trade t where t.user_id = :searchTerm  ")
	List<Trade> findTradeByUserID(@Param("searchTerm") String searchTerm);
	
	@Query("select t from Trade t where t.user_id = ?1")
	List<Trade> findTradeByUserID2(@Param("searchTerm") int user_id);

}
