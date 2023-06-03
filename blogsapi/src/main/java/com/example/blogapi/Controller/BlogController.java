package com.example.blogapi.Controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.blogapi.dto.BlogDto;
import com.example.blogapi.entities.BlogEntitie;
import com.example.blogapi.errorhandeler.NotfoundMessage;
import com.example.blogapi.repository.CommentRepository;
import com.example.blogapi.repository.UserRepository;
import com.example.blogapi.services.BlogService;
import com.example.blogapi.services.authService;

@RestController
@RequestMapping("/api/v1/blog")
public class BlogController {
	public static String uploadDir=System.getProperty("user.dir")+"/src/main/resources/static/blogImages";

	@Autowired
	private authService auth;
	@Autowired
	private BlogService blogs;
	@Autowired
	private UserRepository user;
	
	
//	add Blog
	@PostMapping("/addpost/{id}")
	public ResponseEntity<String> addBlog(@PathVariable("id") Integer id,@RequestBody BlogDto blog)throws IOException{
		
		BlogEntitie blogEntitie=new BlogEntitie();
		blogEntitie.setBlogDesc(blog.getBlogDesc());
		blogEntitie.setBlogTitle(blog.getBlogTitle());
		blogEntitie.setImageName(blog.getImageUrl());
		blogEntitie.setCreatedBy(user.findById(id).orElseThrow(()->new NotfoundMessage("could't found this user id :", id)));		
		return ResponseEntity.status(HttpStatus.OK).body(blogs.addBlog(blogEntitie)); 
	} 
	
//	@PostMapping("/add")
//	public void addsBlog(@RequestParam("file")MultipartFile file)throws IOException{
////		file.transferTo(new File(uploadDir+file.getOriginalFilename()));
//		String UUID;
//		if(!file.isEmpty()) {
//			UUID=file.getOriginalFilename();
//			Path fileNameAndPath=Paths.get(uploadDir,UUID);
//			Files.write(fileNameAndPath, file.getBytes());
//		}else {
//			UUID= null;
//		}
	
//	} 
	
	@GetMapping("/blogs/all")
	public ResponseEntity<List<BlogDto>> getAllBlog(){
		return ResponseEntity.status(HttpStatus.OK).body(blogs.blogPost());
	}
//	get Single Blog
	@GetMapping("/blogs/{id}")
	public ResponseEntity<BlogDto> getAllBlog(@PathVariable("id") Integer id){
		return ResponseEntity.status(HttpStatus.OK).body(blogs.getBlogById(id));
	}
// Update blog
	@PutMapping("/updateblog/{id}")
	public ResponseEntity<String> updateBlog(@PathVariable("id") Integer id,@RequestBody BlogDto blog ){
		return ResponseEntity.status(HttpStatus.OK).body(blogs.updateBlog(id,blog));
	}
	
//	delete blog
	@DeleteMapping("/deleteblog/{id}")
	public ResponseEntity<String> updateBlog(@PathVariable("id") Integer id){
		return ResponseEntity.status(HttpStatus.OK).body(blogs.DeleteBlog(id));
	}
	
}
