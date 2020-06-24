package com.tkouleris.tweety.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tkouleris.tweety.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{
	User findByUsername(String username);
	User findByEmail(String username);
	
	@Query( value="select "
//			+ "user_id, "	
			+ "username "
//			+ "case when id is not null "
//			+ "then 1 "
//			+ "else 0 "
//			+ "end as i_follow "
			+ "from user "
			+ "left join follower on (followee = userid) "
			+ "where userid not in( ?1 )",
			nativeQuery = true
	)
	List<Object[]> listUsers(User user);	
}
