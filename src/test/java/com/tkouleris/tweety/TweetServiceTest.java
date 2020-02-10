package com.tkouleris.tweety;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.tkouleris.tweety.dao.TweetRepository;
import com.tkouleris.tweety.dao.UserRepository;
import com.tkouleris.tweety.model.Tweet;
import com.tkouleris.tweety.model.User;
import com.tkouleris.tweety.service.TweetService;
import com.tkouleris.tweety.util.TimestampUtil;

import io.jsonwebtoken.lang.Assert;

@SpringBootTest
public class TweetServiceTest {
	
	@InjectMocks
	TweetService service;
	@Mock
	TweetRepository R_Tweet;
	@Mock
	UserRepository R_User;
	@Mock
	TimestampUtil timestampUtil;
	
	@Test
	void verify_that_feed_is_returning_Tweet_object()
	{
		User loggedInUser = new User();
		loggedInUser.setUser_id(1);
		List<Tweet> myList = new ArrayList<>();
		Tweet t1 = new Tweet();
		t1.setTweet_user_id(loggedInUser);
		Tweet t2 = new Tweet();
		t2.setTweet_user_id(loggedInUser);
		myList.add(t1);
		myList.add(t1);
		
		Mockito.when(R_Tweet.findLatestTweetByUser(loggedInUser.getUser_id())).thenReturn(myList);	

		Tweet tweet = service.getFeed(loggedInUser);

		Assert.isTrue(tweet instanceof Tweet);
	}
	
	@Test
	void should_return_null_when_no_feed_found()
	{
		User loggedInUser = new User();
		loggedInUser.setUser_id(1);
		List<Tweet> myList = new ArrayList<>();
		
		Mockito.when(R_Tweet.findLatestTweetByUser(loggedInUser.getUser_id())).thenReturn(myList);	

		Tweet tweet = service.getFeed(loggedInUser);

		Assert.isTrue(tweet == null);
	}
}	
