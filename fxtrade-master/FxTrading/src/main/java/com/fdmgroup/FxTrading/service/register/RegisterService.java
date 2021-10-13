package com.fdmgroup.FxTrading.service.register;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fdmgroup.FxTrading.error.UniqueEmailException;
import com.fdmgroup.FxTrading.model.SecurityQuestion;
import com.fdmgroup.FxTrading.model.User;
import com.fdmgroup.FxTrading.repository.*;

@Service
public class RegisterService implements AbstractRegisterService {

	private final UserRepository userRepository;
	private final ApplicationContext applicationContext;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
	
	@Lazy
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	public RegisterService(UserRepository userRepository,
			ApplicationContext applicationContext) {
		super();
		this.userRepository = userRepository;
		this.applicationContext = applicationContext;
	}

	@Override
	public Optional<User> registerNewUser(String first_name, String last_name, String email, String password, List<SecurityQuestion> questions) throws UniqueEmailException {
		Optional<User> optionalUser  = userRepository.findByEmail(email);
		if (optionalUser.isPresent()) {
			throw new UniqueEmailException("Email already exists");
		}
		for (SecurityQuestion question:questions) {
			question.setAnswer(passwordEncoder.encode(question.getAnswer()));
		}
		User user = new User(first_name, last_name,email, passwordEncoder.encode(password), questions);
		System.out.println(user);
		userRepository.save(user);
		return Optional.of(user);
	}
}
