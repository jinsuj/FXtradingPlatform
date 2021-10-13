package com.fdmgroup.FxTrading.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fdmgroup.FxTrading.model.User;
import com.fdmgroup.FxTrading.repository.UserRepository;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")
public class LogInController {

	@Autowired
	private UserRepository userRepo;
	
	@Lazy
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@PostMapping("/login")
	public ResponseEntity<User> checkLogIn(@RequestBody String userInfo) {
		System.out.println(userInfo);
		String[] array = userInfo.split(",");
		String email = array[0].substring(13,array[0].length() - 1);
		String password = array[1].substring(12,array[1].length() - 2);
		System.out.println(email);
		System.out.println(password);
		List<User> temp = userRepo.findByName(email);
		if (temp.size() != 0) {
			if (passwordEncoder.matches(password, temp.get(0).getPassword()) ) {
				System.out.println("passed");
				return new ResponseEntity<>(temp.get(0), HttpStatus.OK);
			} else {
				System.out.println("Not passed");
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} else {
			System.out.println("Not passed2");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/getUserIdForTrade")
	public int getUserID(@RequestBody String email) {
		List<User> userList = userRepo.findByName(email);
		int idTemp = 0;
		if(userList.size() == 1) {
			idTemp = userList.get(0).getUser_id();
		}
		return idTemp;
	}
	
	@PostMapping("/getIsAdmin")
	public boolean checkIsAdmin(@RequestBody String email){
		email = email.replace("\"", "");
		List<User> userList = userRepo.findByName(email);
		boolean admin = false;
		if(userList.size() == 1) {
			admin = userList.get(0).isAdmin();
		}
		return admin;
	}
}
