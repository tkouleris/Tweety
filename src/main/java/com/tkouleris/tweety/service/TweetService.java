package com.tkouleris.tweety.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.tkouleris.tweety.dao.TweetRepository;
import com.tkouleris.tweety.dao.UserRepository;
import com.tkouleris.tweety.model.Tweet;
import com.tkouleris.tweety.model.User;

@Service
public class TweetService {
	
	@Autowired
	TweetRepository R_Tweet;
	@Autowired
	UserRepository R_User;
//	@Autowired
//	Authentication authentication;
	
	public Tweet getFeed(User loggedInUser)
	{
		List<Tweet> userTweets = R_Tweet.findLatestTweetByUser(loggedInUser.getUser_id());
		return userTweets.get(0);
	}
	
	public Tweet createTweet(Authentication authentication, Tweet newTweet)
	{		
        User LoggedInUser = R_User.findByUsername(authentication.getName());
        newTweet.setTweet_user_id(LoggedInUser);
        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
        newTweet.setTweet_created_at(currentTimestamp );
        newTweet.setTweet_updated_at(currentTimestamp );
        return R_Tweet.save(newTweet);
	}
}
