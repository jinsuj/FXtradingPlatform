package com.fdmgroup.FxTrading.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fdmgroup.FxTrading.model.SecurityQuestion;
import com.fdmgroup.FxTrading.model.User;


/**
 * 
 * @author Ben.Filbert
 *
 */
@Repository
public interface SecurityQuestionRepository extends JpaRepository <SecurityQuestion, Integer>{


	List<SecurityQuestion> findByUser(@Param("id") String id);

	@Query("select u from User u where u.email = :searchTerm  ")
	Optional<User> findByUsername(@Param("searchTerm") String searchTerm);
	
	@Query("select s from SecurityQuestion s where s.user = :user")
	List<SecurityQuestion> findByUserId(@Param("user") User user);
}
