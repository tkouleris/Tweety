package com.tkouleris.tweety.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tkouleris.tweety.model.Follower;
import com.tkouleris.tweety.model.User;

import java.util.List;

@Repository
public interface FollowerRepository extends JpaRepository<Follower, Long>{
	@Query("select f from Follower f where f.follower = ?1")
	List<Follower> findTweetsThatUserFollows(User user);
}
