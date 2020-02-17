package com.tkouleris.tweety.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.tkouleris.tweety.responses.ApiResponse;

@ControllerAdvice
public class ControllerAdvisor {
	
	@Autowired
	private ApiResponse apiResponse;
	
	@ExceptionHandler({Exception.class})
	public ResponseEntity<Object> handleGenericException(Exception e) {
		String message = e.getMessage() != null ? e.getMessage():"Bad Request";
	    apiResponse.setMessage(message);
	    apiResponse.setData(null);

	    return new ResponseEntity<>(apiResponse.getBodyResponse(), HttpStatus.BAD_REQUEST);
	}

}

