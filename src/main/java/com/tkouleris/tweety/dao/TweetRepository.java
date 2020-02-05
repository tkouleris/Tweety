package com.tkouleris.tweety.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tkouleris.tweety.model.Tweet;

@Repository
public interface TweetRepository  extends JpaRepository<Tweet, Long>{
	
	@Query("select t from Tweet t where tweet_user_userid =?1 order by tweet_updated_at desc")
	List<Tweet> findLatestTweetByUser(Long userid);
}
