package com.example.blogapi.entities;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Comment")
public class CommentEntitie {	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getComment() {
		return Comment;
	}

	public void setComment(String comment) {
		Comment = comment;
	}

	public UserEntities getUser() {
		return user;
	}

	public void setUser(UserEntities user) {
		this.user = user;
	}

	public BlogEntitie getBlog() {
		return blog;
	}

	public void setBlog(BlogEntitie blog) {
		this.blog = blog;
	}

	private String Comment;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="user_id")
	private UserEntities user;
	
	@ManyToOne
	private BlogEntitie blog ;
}
