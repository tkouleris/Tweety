package com.tkouleris.tweety.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.tkouleris.tweety.model.Comment;
import com.tkouleris.tweety.responses.ApiResponse;
import com.tkouleris.tweety.service.CommentService;

@Controller
public class CommentController {
	@Autowired
	CommentService commentService;
	@Autowired
	ApiResponse apiResponse;
	
	@PostMapping(path = "/tweet/{tweet_id}/comment", produces = "application/json")
	public ResponseEntity<Object> userMakesCommentAtTweet(
			@RequestBody Comment comment, 
			Authentication authentication,
			@PathVariable("tweet_id") long tweet_id 
	) throws Exception{
		Comment savedComment = commentService.userMakesNewCommentAtTweet(authentication, tweet_id, comment);		
		apiResponse.setData(savedComment);
		apiResponse.setMessage("Comment crated");
		return new ResponseEntity<>(apiResponse.getBodyResponse(),HttpStatus.OK);
	}

}
