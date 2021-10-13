package com.fdmgroup.FxTrading.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fdmgroup.FxTrading.model.User;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
	
	@Query("select u from User u where u.email = :searchTerm  ")
	Optional<User> findByEmail(@Param("searchTerm") String searchTerm);
	
	@Query("select u from User u where upper(u.email) like concat('%', upper(:email), '%')")
	List<User> findByName(@Param("email") String email);
	
	

}
