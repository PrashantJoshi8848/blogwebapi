package com.example.blogapi.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Table(name = "User_Details")
@Entity
public class UserEntities {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}

//	email 
	@NotBlank(message = "can't be empty")
	@NotNull(message="email is required fileld")
	@Email(message="required the valid email")
	private String email;
	
//	password 
	@NotBlank(message = "can't be empty")
	@NotNull(message = "password is required")
	private String Password;
	
//	Role
	@NotNull(message = "role is required field")
	private String role;

}
