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
	
	public List<Tweet> getFeed(User loggedInUser)
	{
		return R_Tweet.findByTweet_UserUserid(loggedInUser.getUser_id());
	}
}
