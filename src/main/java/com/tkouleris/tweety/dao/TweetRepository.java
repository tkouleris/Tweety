package com.tkouleris.tweety.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tkouleris.tweety.model.Tweet;
import com.tkouleris.tweety.model.User;

@Repository
public interface TweetRepository  extends JpaRepository<Tweet, Long>{
	
	@Query("select t from Tweet t where tweet_user_userid =?1 order by tweet_updated_at desc")
	List<Tweet> findLatestTweetByUser(Long userid);
	
	@Query( value="SELECT * "
			+ "FROM tweet "
			+ "WHERE tweet_user_userid IN"
			+ "("
			+ "SELECT followee FROM follower WHERE follower = ?1"
			+ ") "
			+ "ORDER BY tweet_updated_at DESC",
			nativeQuery = true
	)
	List<Tweet> findTweetsThatUserFollows(User user);
}
