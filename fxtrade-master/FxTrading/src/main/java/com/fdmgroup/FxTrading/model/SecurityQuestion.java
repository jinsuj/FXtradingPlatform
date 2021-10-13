package com.fdmgroup.FxTrading.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity
@Component
@Table(name = "Security_Questions")
public class SecurityQuestion { 
	@Id
	@GeneratedValue
	@Column(name="SECURITYQUESTION_ID")
	private int id;
	
	@Enumerated(EnumType.STRING)
	SecurityQuestionEnum question; 
	
	private String answer;
	
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name="USER_ID")
	@JsonIgnore
	private User user;





	public SecurityQuestion() {
		super();

	}
	

	public SecurityQuestion(SecurityQuestionEnum question, String answer,User user) {

		this.question = question;
		this.answer = answer;
		this.user = user;
	}



	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}



	public SecurityQuestionEnum getQuestion() {
		return question;
	}



	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;

	}

	@Override
	public String toString() {
		return "SecurityQuestion [id=" + id + ", question=" + question + ", answer=" + answer +"]";

	}

	public void setQuestion(SecurityQuestionEnum question) {
		this.question = question;
	}	
	
}
