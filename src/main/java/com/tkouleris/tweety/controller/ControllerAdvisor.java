package com.tkouleris.tweety.controller;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerAdvisor {
	
	
	@ExceptionHandler({Exception.class})
	public ResponseEntity<Object> handleGenericException(Exception e) {
		String message = e.getMessage() != null ? e.getMessage():"Bad Request";
		
	    Map<String, Object> body = new LinkedHashMap<>();
	    body.put("timestamp", LocalDateTime.now());
	    body.put("message", message);

	    return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
	}

}

