package com.example.blogapi.Controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.blogapi.dto.commentDto;
import com.example.blogapi.entities.BlogEntitie;
import com.example.blogapi.entities.CommentEntitie;
import com.example.blogapi.errorhandeler.NotfoundMessage;
import com.example.blogapi.repository.BlogRepository;
import com.example.blogapi.repository.UserRepository;
import com.example.blogapi.services.BlogService;
import com.example.blogapi.services.CommentService;
import com.example.blogapi.services.authService;

@RestController
@RequestMapping("/api/v1")
public class CommentController {
	@Autowired
	private BlogService blog;
	
	@Autowired 
	private CommentService commentService;
	
	@Autowired
	private authService auth;
	
	@Autowired
	private UserRepository user;
	
	@Autowired
	private BlogRepository blogrepo;
	
	
	@PostMapping("/blog/{userId}/comment")
	public ResponseEntity<String> addComment(@PathVariable("userId") Integer userId,@RequestParam("blogid") Integer blogId,@RequestBody commentDto comment){
		CommentEntitie comments=new CommentEntitie();
	    comments.setComment(comment.getComment());
	    comments.setUser(user.findById(userId).orElseThrow(()->new NotfoundMessage("could't found user id", blogId)));
	    comments.setBlog(blogrepo.findById(blogId).orElseThrow(()->new NotfoundMessage("could't found user id", blogId)));
	    commentService.addMessage(comments);	
	return ResponseEntity.ok("successufully added");
	}
	
	@GetMapping("/comment/all")
	public ResponseEntity<List<commentDto>> getAllComment(){		
		return ResponseEntity.status(HttpStatus.OK).body( commentService.getAllMessage());
	}
}
