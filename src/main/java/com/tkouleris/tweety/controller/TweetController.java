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
	private UserRepository R_User;
	@Autowired
	private TweetService tweetService;
	
	@GetMapping(path = "/feed", produces = "application/json")
	public ResponseEntity<Object> getFeed(Authentication authentication)
	{			
        User LoggedInUser = R_User.findByUsername(authentication.getName());
        Tweet latestUserTweet = tweetService.getFeed(LoggedInUser);
        
	    Map<String, Object> body = new LinkedHashMap<>();
	    body.put("timestamp", LocalDateTime.now());
	    body.put("message", "User created!");
	    body.put("data", latestUserTweet );
	    
		return new ResponseEntity<>(body,HttpStatus.OK);
	}
}
