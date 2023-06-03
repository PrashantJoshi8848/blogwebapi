package com.example.blogapi.entities;
import java.util.HashSet;
import java.util.Set;
import com.example.blogapi.entities.CommentEntitie;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
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
@Table(name = "blogpost")
public class BlogEntitie {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;	
	public Set<CommentEntitie> getComment() 
	{
		return comment;
	}

	public void setComment(Set<CommentEntitie> comment) {
		this.comment = comment;
	}

	private String blogTitle;
	private String blogDesc;
	private String imageName;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="user_id")
	private UserEntities createdBy;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="comment_id")
	private Set<CommentEntitie> comment=new HashSet<>();
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

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public UserEntities getCreatedBy()
	{
		return createdBy;
	}

	public void setCreatedBy(UserEntities createdBy) {
		this.createdBy = createdBy;
	}
	
	
}
