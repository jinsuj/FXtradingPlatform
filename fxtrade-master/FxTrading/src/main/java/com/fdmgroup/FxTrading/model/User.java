package com.fdmgroup.FxTrading.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.Type;
import org.springframework.stereotype.Component;

import com.fdmgroup.FxTrading.validator.ValidPassword;


@Entity
@Component
@Table(name = "Users")

public class User {

	@Id
	@GeneratedValue
	@Column(insertable=false,updatable=false)
	private int user_id;
	
	public void setQuestions(List<SecurityQuestion> questions) {
		this.questions = questions;
	}

	@NotEmpty
	private String first_name;
	@NotEmpty
	private String last_name;
	

	@NotEmpty
	private String email;
	
	@ValidPassword
	private String password;
	
	@OneToMany( fetch = FetchType.LAZY,
            cascade = {CascadeType.REMOVE},
            mappedBy = "user")
	private List<SecurityQuestion> questions = new ArrayList<>();

	@Column(nullable = false) 
	@Type(type = "org.hibernate.type.NumericBooleanType") 
	private boolean isAdmin;
	
	public User() {
		super();
	}

	public User( String first_name, String last_name, String email, String password, List<SecurityQuestion> questions) {
		super();
		this.first_name = first_name;
		this.last_name = last_name;
		this.email = email;
		this.password = password;
		this.questions = questions;
		this.isAdmin = false;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public List<SecurityQuestion> getQuestions() {
		return questions;
	}
	
		public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	
	@Override
	public String toString() {
		return "User [user_id=" + user_id + ", first_name=" + first_name + ", last_name=" + last_name + ", email="
				+ email + ", password=" + password + ", questions=" + questions + ", isAdmin=" + isAdmin + "]";
	}
}

