package com.example.blogapi.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.blogapi.dto.commentDto;
import com.example.blogapi.entities.CommentEntitie;
import com.example.blogapi.repository.BlogRepository;
import com.example.blogapi.repository.CommentRepository;

@Service
public class CommentService {
		@Autowired
		private CommentRepository commentrepo;
		@Autowired
		private BlogService blog;
		
//		ConvertoCommentDto
		public commentDto conveter(CommentEntitie coment) {
			commentDto commDto=new commentDto();
			commDto.setBlogDto(blog.userToDto(coment.getBlog()));
			commDto.setId(coment.getId());
			commDto.setComment(coment.getComment());
			return commDto;
		}
		
//		Save comment
		public void addMessage(CommentEntitie comment ) {
			commentrepo.save(comment);
		}
		
//		Get all Comment
		
		public List<commentDto> getAllMessage() 
		
		{
			List<CommentEntitie > listcomment=commentrepo.findAll();
			List<commentDto> commentsDtoList=listcomment.stream().map(x->conveter(x)).collect(Collectors.toList());
			return commentsDtoList;
					
		}
		
	
}
