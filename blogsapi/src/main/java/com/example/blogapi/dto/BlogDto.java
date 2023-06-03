package com.example.blogapi.dto;

import com.example.blogapi.entities.UserEntities;

public class BlogDto {
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getBlogTitle() {
		return blogTitle;
	}
	public void setBlogTitle(String blogTitle) {
		this.blogTitle = blogTitle;
	}
	public String getBlogDesc() {
		return blogDesc;
	}
	public void setBlogDesc(String blogDesc) {
		this.blogDesc = blogDesc;
	}
	
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public UserDto getCreatedBY() {
		return CreatedBY;
	}
	public void setCreatedBY(UserDto createdBY) {
		CreatedBY = createdBY;
	}

	private Integer id;	
	private String blogTitle;
	private String blogDesc;
	private String imageUrl;
	private UserDto CreatedBY;
}
