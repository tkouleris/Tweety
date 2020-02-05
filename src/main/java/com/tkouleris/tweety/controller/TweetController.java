package com.tkouleris.tweety.controller;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.tkouleris.tweety.dao.UserRepository;
import com.tkouleris.tweety.model.Tweet;
import com.tkouleris.tweety.model.User;
import com.tkouleris.tweety.service.TweetService;


@Controller
public class TweetController 
{
	@Autowired
	UserRepository R_User;
	@Autowired
	TweetService tweetService;
	
	@GetMapping(path = "/tweet/feed", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Object> getFeed(Authentication authentication)
	{		
		
		System.out.println(authentication.getName());
        User LoggedInUser = R_User.findByUsername(authentication.getName());
        List<Tweet> tweets = tweetService.getFeed(LoggedInUser);
        
	    Map<String, Object> body = new LinkedHashMap<>();
	    body.put("timestamp", LocalDateTime.now());
	    body.put("message", "User created!");
	    body.put("data", tweets );
		
		return new ResponseEntity<>(body,HttpStatus.OK);
	}
}
