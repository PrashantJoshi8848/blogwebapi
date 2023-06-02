package com.example.blogapi.errorhandeler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class UserNotFoundException{

	@ResponseBody
	@ExceptionHandler(NotfoundMessage.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public Map<String , String> exceptionHandeling(NotfoundMessage message){
			Map<String, String> errorMap=new HashMap<>();
			errorMap.put("message",message.getMessage());
			return errorMap;
	}
}
