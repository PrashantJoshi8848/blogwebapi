package com.example.blogapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.blogapi.entities.BlogEntitie;

@Repository
public interface BlogRepository extends JpaRepository<BlogEntitie, Integer> {

}
