package com.example.blogapi.errorhandeler;

public class NotfoundMessage extends RuntimeException {
		
 public NotfoundMessage(String message,Integer id)
	{
		super(message + id);
	}
}
