package com.tkouleris.tweety.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import java.util.List;

import com.tkouleris.tweety.dao.CommentRepository;
import com.tkouleris.tweety.dao.TweetRepository;
import com.tkouleris.tweety.dao.UserRepository;
import com.tkouleris.tweety.model.Comment;
import com.tkouleris.tweety.model.Tweet;
import com.tkouleris.tweety.model.User;
import com.tkouleris.tweety.util.TimestampUtil;

@Service
public class CommentService {
	@Autowired
	private TweetRepository R_Tweet;
	@Autowired
	private UserRepository R_User;
	@Autowired
	private CommentRepository R_Comment;	
	@Autowired
	private TimestampUtil timestampUtil;
	
	public Comment userMakesNewCommentAtTweet(Authentication authentication, Long tweet_id, Comment comment ) throws Exception
	{
		Tweet CommentedTweet = R_Tweet.findById(tweet_id).orElse(null);
		if(CommentedTweet == null)
		{
			throw new Exception("Tweet not found!");
		}
        User LoggedInUser = R_User.findByUsername(authentication.getName());
        comment.setComment_tweet_id(CommentedTweet);
        comment.setComment_user_id(LoggedInUser);
        comment.setComment_created_at(timestampUtil.currentTimestamp());
        comment.setComment_updated_at(timestampUtil.currentTimestamp());
        
		return R_Comment.save(comment);		
	}
	
	public Comment deleteComment(Authentication authentication, Long comment_id) throws Exception
	{
		Comment commentToDelete = R_Comment.findById(comment_id).orElse(null);
		if(commentToDelete == null)
		{
			throw new Exception("Comment not found");
		}
		User LoggedInUser = R_User.findByUsername(authentication.getName());

		if(commentToDelete.getComment_user_id() != LoggedInUser)
		{
			throw new Exception("Not authorized to delete this comment");
		}
		
		R_Comment.delete(commentToDelete);
				
		return commentToDelete;
	}
	
	public Comment updateComment(Authentication authentication, Comment comment) throws Exception
	{
		Comment commentToUpdate = R_Comment.findById(comment.getComment_id()).orElse(null);
		if(commentToUpdate == null)
		{
			throw new Exception("Comment does not exist!");
		}
		User LoggedInUser = R_User.findByUsername(authentication.getName());
		
		if(LoggedInUser != commentToUpdate.getComment_user_id())
		{
			throw new Exception("Not authorized to edit this comment");
		}
		
		commentToUpdate.setComment_text(comment.getComment_text());
		commentToUpdate.setComment_updated_at(timestampUtil.currentTimestamp());
		
		Comment updatedComment = R_Comment.save(commentToUpdate);
		
		return updatedComment;
	}
	
	public List<Comment> showTweetComments(long tweet_id)
	{
		Tweet commented_tweet = R_Tweet.findById(tweet_id).orElse(null);
		return R_Comment.findByTweetId(commented_tweet);
	}
}
