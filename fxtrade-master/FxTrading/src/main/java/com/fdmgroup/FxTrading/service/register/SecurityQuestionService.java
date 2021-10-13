package com.fdmgroup.FxTrading.service.register;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import  java.util.ArrayList;
import com.fdmgroup.FxTrading.model.SecurityQuestion;
import com.fdmgroup.FxTrading.model.SecurityQuestionEnum;

import com.fdmgroup.FxTrading.model.User;

import com.fdmgroup.FxTrading.repository.SecurityQuestionRepository;
/**
 * 
 * @author Ben.Filbert
 *
 */
@Service
public class SecurityQuestionService {

	@Autowired
	private SecurityQuestionRepository secQuestRepo;
	
	public <Optional>SecurityQuestion saveNewResponse(SecurityQuestionEnum enumm,String answer, User user) {
		SecurityQuestion secQuest = new SecurityQuestion(enumm, answer,user);
		return secQuestRepo.save(secQuest);
	}
	
	public List<SecurityQuestion> getUserQuestions(String userId) {
		return secQuestRepo.findByUser(userId);
	}
	
	public List<String> getAllEnums() {
		List<String> vals = new ArrayList<String>();
		for ( SecurityQuestionEnum temp : Arrays.asList(SecurityQuestionEnum.values()) ) {
	    		vals.add(temp.securityQuestion());
	    }
	    return vals;
	}
}
