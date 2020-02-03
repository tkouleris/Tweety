package com.tkouleris.tweety.service;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.tkouleris.tweety.dao.UserRepository;
import com.tkouleris.tweety.model.User;

@Service
public class MyUserDetailsService implements UserDetailsService{

	@Autowired
	private UserRepository R_user;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = R_user.findByUsername(username);
		
		return new MyUserDetails(user);
	}
	
}
