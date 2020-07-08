package com.tkouleris.tweety.controller;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tkouleris.tweety.dao.UserRepository;
import com.tkouleris.tweety.model.Tweet;
import com.tkouleris.tweety.model.User;
import com.tkouleris.tweety.responses.ApiResponse;
import com.tkouleris.tweety.service.TweetService;
import java.util.List;


@Controller
@RequestMapping("/tweety")
public class TweetController 
{
	@Autowired
	private UserRepository R_User;
	@Autowired
	private TweetService tweetService;
	@Autowired
	private ApiResponse apiResponse;
	
	@GetMapping(path = "/tweet/feed", produces = "application/json")
	public ResponseEntity<Object> getFeed(Authentication authentication)
	{			
        User LoggedInUser = R_User.findByUsername(authentication.getName());
        List<Tweet> userFeed = tweetService.getFeed(LoggedInUser);
	    apiResponse.setMessage("User Feed!");
	    apiResponse.setData(userFeed);	    
	   
		return new ResponseEntity<>(apiResponse.getBodyResponse(),HttpStatus.OK);
	}
	
	@GetMapping(path = "/tweet/user/{user_id}", produces = "application/json")
	public ResponseEntity<Object> showTweetsByUser(@PathVariable("user_id") long user_id)
	{			
		List<Tweet> userTweets = tweetService.showUserTweets(user_id);    
	    apiResponse.setMessage("Tweets by user");
	    apiResponse.setData(userTweets);
	    
		return new ResponseEntity<>(apiResponse.getBodyResponse(),HttpStatus.OK);
	}	
	
	@PostMapping(path = "/tweet/create", produces = "application/json")
	public ResponseEntity<Object> createTweet(Authentication authentication, @RequestBody Tweet newTweet)
	{			
		Tweet savedTweet = tweetService.createTweet(authentication,newTweet);	    
	    apiResponse.setMessage("Tweet created!");
	    apiResponse.setData(savedTweet);

		return new ResponseEntity<>(apiResponse.getBodyResponse(),HttpStatus.CREATED);
	}	
	
	@PutMapping(path = "tweet/update/{tweet_id}", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Object> updateTweet(
			@PathVariable("tweet_id") long tweet_id, 
			@RequestBody @Valid Tweet tweet, 
			Authentication authentication
	) throws Exception
	{		
		Tweet updatedTweet = tweetService.updateTweet(authentication, tweet_id, tweet.getTweet_message());	   
	    apiResponse.setMessage("Tweet updated!");
	    apiResponse.setData(updatedTweet);

		return new ResponseEntity<>(apiResponse.getBodyResponse(),HttpStatus.OK);
	}		
	
	@DeleteMapping(path = "tweet/{tweet_id}")
	public ResponseEntity<Object> delete(
			@PathVariable("tweet_id") long tweet_id,
			Authentication authentication
	) throws Exception
	{
		Tweet deletedTweet = tweetService.deleteTweet(authentication, tweet_id);
	    apiResponse.setMessage("Tweet deleted!");
	    apiResponse.setData(deletedTweet);

		return new ResponseEntity<>(apiResponse.getBodyResponse(),HttpStatus.OK);
	}	
	
	@GetMapping(path = "/tweet/{tweet_id}", produces = "application/json")
	public ResponseEntity<Object> getTweet(@PathVariable("tweet_id") long tweet_id) throws Exception
	{
		Tweet tweet = tweetService.getTweet(tweet_id);
		apiResponse.setMessage("Tweet");
		apiResponse.setData(tweet);
		
		return new ResponseEntity<>(apiResponse.getBodyResponse(),HttpStatus.OK);
	}
}
