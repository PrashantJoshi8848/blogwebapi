package com.example.blogapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
public class AuthConfig {
	
	@Bean
	public SecurityFilterChain secutiyChain(HttpSecurity http)throws Exception {
			http.csrf(e->e.disable())
			.authorizeHttpRequests(request->
			request.requestMatchers("/api/v1/auth/login","/api/v1/auth/register")
			.permitAll()
			.requestMatchers("/api/v1/auth/alluser")
			.hasAuthority("ADMIN")
			.anyRequest()
			.authenticated()			
			
					);
			http.httpBasic(Customizer.withDefaults());
//			http.formLogin(Customizer.withDefaults());
			http.authenticationProvider(authProvider());
			
			return http.build();
	}
	
	@Bean
	public UserDetailsService userDetailsService() {
		return new com.example.blogapi.services.UserDetails();
	}
	
	@Bean
	public PasswordEncoder passwordEncode() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationProvider authProvider() {
		DaoAuthenticationProvider authprov=new DaoAuthenticationProvider();
		authprov.setUserDetailsService(userDetailsService());
		authprov.setPasswordEncoder(passwordEncode());
		return authprov;
	}
	@Bean
	public AuthenticationManager authenticateManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();		
	}
	

}
