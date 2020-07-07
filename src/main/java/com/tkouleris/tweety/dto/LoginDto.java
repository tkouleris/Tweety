package com.tkouleris.tweety.dto;

import org.springframework.stereotype.Component;

@Component
public class LoginDto {
	private String username;
	private String jwt;
	private long userid;
	
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
	public long getUserid() {
		return userid;
	}
	public void setUserid(long userid) {
		this.userid = userid;
	}	
}
