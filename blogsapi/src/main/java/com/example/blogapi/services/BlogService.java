package com.example.blogapi.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.blogapi.dto.BlogDto;
import com.example.blogapi.dto.UserDto;
import com.example.blogapi.entities.BlogEntitie;
import com.example.blogapi.entities.UserEntities;
import com.example.blogapi.errorhandeler.NotfoundMessage;
import com.example.blogapi.repository.BlogRepository;

@Service
public class BlogService {
	
	@Autowired
	private BlogRepository blogRepository;
	
	@Autowired
	private authService service;
	
//	convertuser to Dto
	public BlogDto userToDto(BlogEntitie entites){
		BlogDto user=new BlogDto();
		user.setBlogDesc(entites.getBlogDesc());
		user.setBlogTitle(entites.getBlogTitle());
		user.setCreatedBY(service.userToDto(entites.getCreatedBy()));
		user.setImageUrl(entites.getImageName());
		user.setId(entites.getId());
		return user;
	}
	
	
	
//	post all the blogs service
	public String addBlog(BlogEntitie blogpost) {
		blogRepository.save(blogpost);
		
		return "Succesfully added post";
	}
	
//	get All the blogpost
	public List<BlogDto> blogPost(){
		List<BlogEntitie> blog=blogRepository.findAll();
		List<BlogDto> blogDto= blog.stream().map(x->userToDto(x)).collect(Collectors.toList());
		return blogDto;
	}
	
//	get Single blog ById
	
	public BlogDto getBlogById(Integer Id){
		BlogEntitie blog=blogRepository.findById(Id).orElseThrow(()->new NotfoundMessage("Coun't found this id", Id));
		return userToDto(blog);
	}
	
//	update blog
	public String updateBlog(Integer Id,BlogDto blog) {
		BlogEntitie bloga=blogRepository.findById(Id).orElseThrow(()->new NotfoundMessage("Coun't found this id", Id));
		bloga.setId(Id);
		bloga.setBlogTitle(blog.getBlogTitle());
		bloga.setBlogDesc(blog.getBlogDesc());
		bloga.setImageName(blog.getImageUrl());
		blogRepository.save(bloga);
		return "update succesfully";
	}
//	delete Blog
	
	public String DeleteBlog(Integer Id) 
	{
		blogRepository.deleteById(Id);
		return "Delete succesfully";
	}

}
