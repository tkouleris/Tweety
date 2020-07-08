package com.tkouleris.tweety.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.tkouleris.tweety.dao.FollowerRepository;
import com.tkouleris.tweety.dao.TweetRepository;
import com.tkouleris.tweety.dao.UserRepository;
import com.tkouleris.tweety.model.Follower;
import com.tkouleris.tweety.model.Tweet;
import com.tkouleris.tweety.model.User;
import com.tkouleris.tweety.util.TimestampUtil;

@Service
public class TweetService {
	
	@Autowired
	TweetRepository R_Tweet;
	@Autowired
	UserRepository R_User;
	@Autowired
	TimestampUtil timestampUtil;
	@Autowired
	FollowerRepository R_Follower;
	
	public List<Tweet> getFeed(User loggedInUser)
	{
		Tweet latestUserTweet = null;
		List<Tweet> userTweets = R_Tweet.findLatestTweetByUser(loggedInUser.getUser_id());
		if(userTweets.size() > 0) latestUserTweet = userTweets.get(0);

		List<Tweet> followTweets = R_Tweet.findTweetsThatUserFollows(loggedInUser);
		if(latestUserTweet != null && latestUserTweet.getTweet_updated_at().after(timestampUtil.oneMinuteBackTimestamp()))
		{
			followTweets.add(0,latestUserTweet);
		}
		return followTweets;
	}
	
	public Tweet createTweet(Authentication authentication, Tweet newTweet)
	{		
        User LoggedInUser = R_User.findByUsername(authentication.getName());
        newTweet.setTweet_user_id(LoggedInUser);
        Timestamp currentTimestamp = timestampUtil.currentTimestamp();
        newTweet.setTweet_created_at(currentTimestamp );
        newTweet.setTweet_updated_at(currentTimestamp );        
        return R_Tweet.save(newTweet);
	}
	
	public Tweet updateTweet(Authentication authentication, long tweet_id, String message) throws Exception
	{
		Tweet tweetToUpdate = R_Tweet.findById(tweet_id).orElse(null);
		User loggedInUser = R_User.findByUsername(authentication.getName());
		if(tweetToUpdate == null)
		{
			throw new Exception("Tweet not found");
		}
		if(!loggedInUser.equals(tweetToUpdate.getTweet_user_id()))
		{
			throw new Exception("No permission to edit this tweet!");
		}		
		tweetToUpdate.setTweet_message(message);
		tweetToUpdate.setTweet_updated_at(timestampUtil.currentTimestamp());
		return R_Tweet.save(tweetToUpdate);
	}
	
	public Tweet deleteTweet(Authentication authentication, long tweet_id) throws Exception
	{
		Tweet tweetToDelete = R_Tweet.findById(tweet_id).orElse(null);
		if( tweetToDelete == null)
		{
			throw new Exception("Tweet to delete not found!");
		}
		
		User LoggedInUser = R_User.findByUsername(authentication.getName());		
		if(LoggedInUser != tweetToDelete.getTweet_user_id())
		{
			throw new Exception("You have no rights to delete this tweet!");
		}
		
		R_Tweet.delete(tweetToDelete);
		
		return tweetToDelete;					
	}
	
	public Tweet getTweet(long tweet_id) throws Exception
	{
		Tweet tweet = R_Tweet.findById(tweet_id).orElse(null);
		if(tweet == null)
		{
			throw new Exception("No tweet found!");			
		}
		return tweet;
	}
	
	public List<Tweet> showUserTweets(long user_id)
	{
		List<Tweet> userTweets = R_Tweet.findLatestTweetByUser(user_id);
		
		return userTweets;
	}
}
