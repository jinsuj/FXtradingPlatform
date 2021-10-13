package com.fdmgroup.FxTrading.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.transaction.Transactional;

import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.LengthRule;
import org.passay.PasswordData;
import org.passay.PasswordValidator;
import org.passay.RuleResult;
import org.passay.WhitespaceRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fdmgroup.FxTrading.model.SecurityQuestion;
import com.fdmgroup.FxTrading.model.User;
import com.fdmgroup.FxTrading.repository.SecurityQuestionRepository;
import com.fdmgroup.FxTrading.repository.UserRepository;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")
public class ResetPasswordController {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private SecurityQuestionRepository securityRepo;
	
	@Lazy
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	private int rand_int = 0;

	@PostMapping("/resetPassword")
	@Transactional
	public ResponseEntity<User> resetPassword(@RequestBody String user, BindingResult bindingResult) {
		System.out.println("hello in password");
		String[] array = user.split(",");
		String username = array[0].substring(29, array[0].length() - 4);
		String password = array[1].substring(12, array[1].length() - 1);
		String confpass = array[2].substring(15, array[2].length() - 2);
		System.out.println(username);
		System.out.println(password);
		System.out.println(confpass);
		List<User> list = userRepo.findByName(username);
		PasswordValidator validator = new PasswordValidator(Arrays.asList(
				// at least 8 characters
				new LengthRule(8, 30),
				// at least one upper-case character
				new CharacterRule(EnglishCharacterData.UpperCase, 1),
				// at least one lower-case character
				new CharacterRule(EnglishCharacterData.LowerCase, 1),
				// at least one digit character
				new CharacterRule(EnglishCharacterData.Digit, 1),
				// at least one symbol (special character)
				new CharacterRule(EnglishCharacterData.Special, 1),
				// no whitespace
				new WhitespaceRule()));
		RuleResult result = validator.validate(new PasswordData(password));
		if (!result.isValid()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		if (!password.equals(confpass)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		if (list.size() == 0) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			if (list.get(0).getPassword().equals(password)) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			} else {
				list.get(0).setPassword(passwordEncoder.encode(password));
				return new ResponseEntity<>(list.get(0), HttpStatus.OK);
			}
		}
	}

	@PostMapping("/askUsername")
	public ResponseEntity<User> resetPassword(@RequestBody String username) {
		System.out.println(username.substring(13, username.length() - 2));
		if (username.substring(13, username.length() - 2).equals("")) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		List<User> list = userRepo.findByName(username.substring(13, username.length() - 2));
		if (list.size() == 0) {
			System.out.println("not passed");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			System.out.println("passed");
			return new ResponseEntity<>(list.get(0), HttpStatus.OK);
		}
	}
	
	@PostMapping("/getQuestions") 
	public List<String> getQuestions(@RequestBody String username, Model model) {
		List<User> temp = userRepo.findByName(username.substring(29, username.length() - 5));
		List<SecurityQuestion> list = securityRepo.findByUserId(temp.get(0));
		List<String> stringList = new ArrayList<String>();
		Random rand = new Random();
		rand_int = rand.nextInt(list.size());
		stringList.add(list.get(rand_int).getQuestion().securityQuestion()); 
		return stringList;
	}

	@PostMapping("/submitAns")
	public ResponseEntity<User> submiteAns(@RequestBody String answers) {
		System.out.println(answers);
		String[] array = answers.split(",");
		System.out.println(array[0].substring(29, array[0].length()-4));
		System.out.println(array[1].substring(8,array[1].length()-2));
		String username = array[0].substring(29, array[0].length()-4);
		String ans1 = array[1].substring(8,array[1].length()-2);
		List<String> ansList = new ArrayList<String>();
		ansList.add(ans1);
		List<User> temp = userRepo.findByName(username);
		List<SecurityQuestion> list = securityRepo.findByUserId(temp.get(0));
		System.out.println(rand_int);
		if (!passwordEncoder.matches(ansList.get(0),list.get(rand_int).getAnswer())) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	} 
}
