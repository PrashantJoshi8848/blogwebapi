package com.example.blogapi.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.blogapi.dto.UserDto;
import com.example.blogapi.entities.UserEntities;
import com.example.blogapi.errorhandeler.NotfoundMessage;
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
//	convertuser to Dto
	public UserDto userToDto(UserEntities entites){
		UserDto user=new UserDto();
		user.setEmail(entites.getEmail());
		user.setId(entites.getId());
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
	public List<UserDto> getAllUser(){
		List<UserEntities> list=userRepository.findAll();
	  List<UserDto> Dtolist= list.stream().map(x-> userToDto(x)).collect(Collectors.toList());
		return Dtolist;
	}
//	Getting user by id
	public UserDto getSingleUser(Integer id){
			UserEntities user=userRepository.findById(id).orElseThrow(()->new NotfoundMessage("could't found this user id :", id));
		return userToDto(user);
	}
	
}
