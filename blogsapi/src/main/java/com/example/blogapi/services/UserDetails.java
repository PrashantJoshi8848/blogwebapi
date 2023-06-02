package com.example.blogapi.services;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.example.blogapi.config.UserInfoConfig;
import com.example.blogapi.entities.UserEntities;
import com.example.blogapi.repository.UserRepository;

@Component
public class UserDetails implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
			Optional<UserEntities> user=userRepository.findByEmail(username);			
		return user.map(UserInfoConfig::new).orElseThrow(()->new UsernameNotFoundException("User Not found"));
	}

}
