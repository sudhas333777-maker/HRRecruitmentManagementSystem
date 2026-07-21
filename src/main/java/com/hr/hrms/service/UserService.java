package com.hr.hrms.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hr.hrms.entity.User;
import com.hr.hrms.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public User saveUser(User user) {
		return userRepository.save(user);
	}
	
	public Optional<User> findByEmail(String email){
		return userRepository.findByEmail(email);
	}

}
