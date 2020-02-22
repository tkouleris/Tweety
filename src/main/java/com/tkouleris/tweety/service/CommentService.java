package com.tkouleris.tweety.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.tkouleris.tweety.dao.CommentRepository;
import com.tkouleris.tweety.dao.TweetRepository;
import com.tkouleris.tweety.dao.UserRepository;
import com.tkouleris.tweety.model.Comment;
import com.tkouleris.tweety.model.Tweet;
import com.tkouleris.tweety.model.User;

@Service
public class CommentService {
	@Autowired
	private TweetRepository R_Tweet;
	@Autowired
	private UserRepository R_User;
	@Autowired
	private CommentRepository R_Comment;	
	
	public void userMakesNewCommentAtTweet(Authentication authentication, Long tweet_id, Comment comment ) throws Exception
	{
		System.out.println(tweet_id);
		Tweet CommentedTweet = R_Tweet.findById(tweet_id).orElse(null);
		if(CommentedTweet == null)
		{
			throw new Exception("Tweet not found!");
		}
        User LoggedInUser = R_User.findByUsername(authentication.getName());
        comment.setComment_tweet_id(CommentedTweet);
        comment.setComment_user_id(LoggedInUser);
        
		R_Comment.save(comment);
	}
}
