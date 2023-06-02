package com.example.blogapi.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class LoginDto {
	
	@NotBlank(message = "can't be empty")
	@NotNull(message="email is required fileld")
	@Email(message="required the valid email")
	private String email;
	
	public String getemail() {
		return email;
	}
	public void setemail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@NotBlank(message = "can't be empty")
	@NotNull(message="password is required fileld")
	private String password;

}
