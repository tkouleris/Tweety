package com.tkouleris.tweety.dto;

import org.springframework.stereotype.Component;

@Component
public class LoginDto {
	private String username;
	private String jwt;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getJwt() {
		return jwt;
	}
	public void setJwt(String jwt) {
		this.jwt = jwt;
	}
	
	
}
