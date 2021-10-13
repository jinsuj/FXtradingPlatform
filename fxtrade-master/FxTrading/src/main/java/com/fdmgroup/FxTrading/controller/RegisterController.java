package com.fdmgroup.FxTrading.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.fdmgroup.FxTrading.error.UniqueEmailException;
import com.fdmgroup.FxTrading.model.SecurityQuestion;

import com.fdmgroup.FxTrading.model.User;

import com.fdmgroup.FxTrading.service.register.AbstractRegisterService;
import com.fdmgroup.FxTrading.service.register.SecurityQuestionService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")

@RequestMapping("/register")
public class RegisterController {
	@Autowired
	private final AbstractRegisterService registerService;
	
	@Autowired
	private SecurityQuestionService secQuestService;

	@Autowired
	public RegisterController(AbstractRegisterService registerService) {
		super();
		this.registerService = registerService;
	}
	
	@GetMapping
	public String toRegister(Model model, 
							@Autowired User user) {
		for (int i =0; i<3;i++) {
			user.getQuestions().add(new SecurityQuestion());
		}
		model.addAttribute("user", user);

		return "redirect:/register";
	}

	@PostMapping
	public String processRegistration(Model model,
									  @Valid @RequestBody User user, 
									  BindingResult bindingResult) throws UniqueEmailException {
		if (bindingResult.hasErrors()) {
			return "redirect:/register";
	    }
		
		System.out.println(user);

		for (SecurityQuestion question: user.getQuestions()) { 
			question.setUser(user);
			System.out.println(question);
		} 
		
		Optional<User> optionalUser = registerService.registerNewUser(user.getFirst_name(), user.getLast_name(), user.getEmail(), user.getPassword(),user.getQuestions());
		
			for (SecurityQuestion question: user.getQuestions()) { 
				secQuestService.saveNewResponse(question.getQuestion(), question.getAnswer(), optionalUser.get());
			} 

		return "redirect:/home";
	}
	
}
