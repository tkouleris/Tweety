package com.tkouleris.tweety.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tkouleris.tweety.dao.TweetRepository;
import com.tkouleris.tweety.model.Tweet;
import com.tkouleris.tweety.model.User;

@Service
public class TweetService {
	
	@Autowired
	TweetRepository R_Tweet;
	
	public Tweet getFeed(User loggedInUser)
	{
		List<Tweet> userTweets = R_Tweet.findLatestTweetByUser(loggedInUser.getUser_id());
		return userTweets.get(0);
	}
}
