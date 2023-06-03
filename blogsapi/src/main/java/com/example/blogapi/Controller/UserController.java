package com.example.blogapi.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import com.example.blogapi.dto.LoginDto;
import com.example.blogapi.dto.UserDto;
import com.example.blogapi.entities.UserEntities;
import com.example.blogapi.errorhandeler.NotfoundMessage;
import com.example.blogapi.services.authService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth")
public class UserController {
	
	@Autowired
	private authService auth;
	
	@Autowired
	private AuthenticationManager authenticationManager;


//	Register user Controller
	@PostMapping("/register")
	public ResponseEntity<String> home(@Valid @RequestBody UserEntities user) {
		if(auth.registerUser(user))return ResponseEntity.status(HttpStatus.OK).body("User added Succesfully"); 
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("user Already Exist");
	}
	
//	Login User Controller
	@PostMapping("/login") 
	public ResponseEntity<Optional<UserEntities>> AuthandGetToken(@Valid @RequestBody LoginDto userDto) throws AuthenticationException {	
		Authentication auths=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDto.getemail(), userDto.getPassword()));
				if(auths.isAuthenticated()) {					
					return ResponseEntity.status(HttpStatus.OK).body(auth.findByEmail(userDto.getemail()));
				}else {
					throw new UsernameNotFoundException("user Not Found");
				}
	}
	
	
//	Get All user Only can get by Admin
	@GetMapping("/alluser")
	public ResponseEntity<List<UserDto>> getAlluser() 
	{
		return ResponseEntity.status(HttpStatus.OK).body(auth.getAllUser());
	}
	
//	get single user By id only by Admin		
	
	@GetMapping("/alluser/{id}")
	public ResponseEntity<UserDto> getSingleUser(@PathVariable Integer id){
		return ResponseEntity.status(HttpStatus.OK).body(auth.getSingleUser(id));
	}
	
	
}

