package com.tkouleris.tweety.service;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.tkouleris.tweety.model.User;

public class CustomUserDetails implements UserDetails{
	
	private User user;

	public CustomUserDetails(User user)
	{
		this.user = user;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Future feature
		return null;
	}

	@Override
	public String getPassword() {		
		return this.user.getPassword();
	}

	@Override
	public String getUsername() {
		return this.user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Future feature
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Future feature
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Future feature
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Future feature
		return true;
	}

}
