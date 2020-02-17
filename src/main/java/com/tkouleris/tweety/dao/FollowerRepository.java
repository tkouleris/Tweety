package com.tkouleris.tweety.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tkouleris.tweety.model.Follower;

@Repository
public interface FollowerRepository extends CrudRepository<Follower, Long>{

}
