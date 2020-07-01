package com.tkouleris.tweety.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tkouleris.tweety.model.Follower;
import com.tkouleris.tweety.model.User;

@Repository
public interface FollowerRepository extends JpaRepository<Follower, Long>{
	@Query("select f from Follower f where f.follower = ?1")
	List<Follower> findTweetsThatUserFollows(User user);
	
	@Query("select f from Follower f where f.followee = ?1 and f.follower=?2")
	Optional<Follower> findByFolloweeAndFollower(User followee, User follower);
}
