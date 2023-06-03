package com.example.blogapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.blogapi.entities.CommentEntitie;

public interface CommentRepository extends JpaRepository<CommentEntitie, Integer>
{
		
}
