package com.tkouleris.tweety.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.tkouleris.tweety.dao.FollowerRepository;
import com.tkouleris.tweety.dao.UserRepository;
import com.tkouleris.tweety.model.Follower;
import com.tkouleris.tweety.model.User;
import com.tkouleris.tweety.responses.ApiResponse;

@Controller
public class FollowController {
	@Autowired
	private UserRepository R_User;
	@Autowired
	private ApiResponse apiResponse;
	@Autowired
	private FollowerRepository R_follower;
	
	@PostMapping(path = "follow/add/{user_id}", produces = "application/json")
	public ResponseEntity<Object> addFolower(
			Authentication authentication, 
			@PathVariable("user_id") long user_id
	) throws Exception
	{			    		
		User LoggedInUser = R_User.findByUsername(authentication.getName());
		User FolloweeUser = R_User.findById(user_id).orElse(null);
		
		if(FolloweeUser == null)
		{
			throw new Exception("User not found!");
		}
		
		Follower f = new Follower();
		f.setFollower(LoggedInUser);
		f.setFollowee(FolloweeUser);
		
		R_follower.save(f);
		
	    apiResponse.setMessage("Followee Added!");
	    apiResponse.setData(user_id);

		return new ResponseEntity<>(apiResponse.getBodyResponse(),HttpStatus.CREATED);
	}
}
