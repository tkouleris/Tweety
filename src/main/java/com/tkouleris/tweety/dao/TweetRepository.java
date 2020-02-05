package com.tkouleris.tweety.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.tkouleris.tweety.model.Tweet;

public interface TweetRepository  extends CrudRepository<Tweet, Long>{
	public List<Tweet> findByTweet_User(Long userid);
}
