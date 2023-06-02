package com.example.blogapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.blogapi.entities.UserEntities;

@Repository
public interface UserRepository  extends JpaRepository<UserEntities, Integer>{
	
	 Optional<UserEntities> findByEmail(String email);
	
}
