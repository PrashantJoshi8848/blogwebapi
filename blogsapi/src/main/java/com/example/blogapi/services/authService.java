package com.example.blogapi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.blogapi.entities.UserEntities;
import com.example.blogapi.repository.UserRepository;

@Service
public class authService {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;

	public Optional<UserEntities> findByEmail(String username){
		Optional<UserEntities> user=userRepository.findByEmail(username);
		return user;
	}
	
//	Register User Service class
	public boolean registerUser(UserEntities user) {
		if(!findByEmail(user.getEmail()).isPresent()) {
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			userRepository.save(user);
			return true;
		} 
		return false;
	}
	
//	Getting all User
	public List<UserEntities> getAllUser(){
		return userRepository.findAll();
	}
}
