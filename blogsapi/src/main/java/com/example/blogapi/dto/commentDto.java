package com.example.blogapi.dto;

public class commentDto {
	private Integer Id;
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	public String getComment() {
		return Comment;
	}
	public void setComment(String comment) {
		Comment = comment;
	}
	
//	public Integer getUserDto() {
//		return UserDto;
//	}
//	public void setUserDto(Integer userDto) {
//		UserDto = userDto;
//	}
	public BlogDto getBlogDto() {
		return blogDto;
	}
	public void setBlogDto(BlogDto blogDto) {
		this.blogDto = blogDto;
	}

	private String Comment;
//	private Integer UserDto;
	private BlogDto blogDto;
}
