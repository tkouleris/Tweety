package com.tkouleris.tweety.dao;

import org.springframework.data.repository.CrudRepository;

import com.tkouleris.tweety.model.User;

public interface UserRepository extends CrudRepository<User, Long>{
	User findByUsername(String username);
}
