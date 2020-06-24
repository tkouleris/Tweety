package com.tkouleris.tweety.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tkouleris.tweety.responses.ApiResponse;
import com.tkouleris.tweety.service.UserCrudService;

@Controller
@RequestMapping("/tweety")
public class UserController {
	@Autowired
	private UserCrudService userService;
	@Autowired
	private ApiResponse apiResponse;
	
	@GetMapping(path = "/user/list", produces = "application/json")
	public ResponseEntity<Object> listOfUsers(Authentication authentication)
	{
		List<Object[]> userList = userService.listUsers(authentication);
		apiResponse.setData(userList);
		apiResponse.setMessage("User List");
		
		return new ResponseEntity<>(apiResponse.getBodyResponse(),HttpStatus.OK);
	}
}
