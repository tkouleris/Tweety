package com.tkouleris.tweety.controller;

import java.util.List;

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

import com.tkouleris.tweety.model.Comment;
import com.tkouleris.tweety.responses.ApiResponse;
import com.tkouleris.tweety.service.CommentService;

@Controller
@RequestMapping("/tweety")
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
	

	@GetMapping(path = "/tweet/{tweet_id}/comment", produces = "application/json")
	public ResponseEntity<Object> showTweetComments(@PathVariable("tweet_id") long tweet_id)
	{
		List<Comment> tweet_comments = commentService.showTweetComments(tweet_id);
		apiResponse.setData(tweet_comments);
		apiResponse.setMessage("tweet comments");
		return new ResponseEntity<>(apiResponse.getBodyResponse(),HttpStatus.OK);
	}

	@DeleteMapping(path="/comment/{comment_id}", produces = "application/json")
	public ResponseEntity<Object> deleteComment(
			Authentication authentication, 
			@PathVariable("comment_id") 
			long comment_id
	) throws Exception
	{
		Comment deletedComment = commentService.deleteComment(authentication, comment_id);
		apiResponse.setData(deletedComment);
		apiResponse.setMessage("Comment deleted");
		return new ResponseEntity<>(apiResponse.getBodyResponse(),HttpStatus.OK);
	}
	
	@PutMapping(path="comment", produces = "application/json")
	public ResponseEntity<Object> updateComment(Authentication authentication,@RequestBody Comment comment) throws Exception
	{
		Comment updatedComment =commentService.updateComment(authentication, comment);
		apiResponse.setData(updatedComment);
		apiResponse.setMessage("Comment updated");
		return new ResponseEntity<>(apiResponse.getBodyResponse(),HttpStatus.OK);
	}
	
}
