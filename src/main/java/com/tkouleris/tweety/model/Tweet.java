package com.tkouleris.tweety.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Tweet {
	@Id
	@GeneratedValue
	private long tweet_id;
	
	private String tweet_message;
	private Timestamp tweet_created_at;
	private Timestamp tweet_updated_at;
	@ManyToOne
	private User tweet_user_id;
	
	public long getTweet_id() {
		return tweet_id;
	}
	public void setTweet_id(long tweet_id) {
		this.tweet_id = tweet_id;
	}
	public String getTweet_message() {
		return tweet_message;
	}
	public void setTweet_message(String tweet_message) {
		this.tweet_message = tweet_message;
	}
	public Timestamp getTweet_created_at() {
		return tweet_created_at;
	}
	public void setTweet_created_at(Timestamp tweet_created_at) {
		this.tweet_created_at = tweet_created_at;
	}
	public Timestamp getTweet_updated_at() {
		return tweet_updated_at;
	}
	public void setTweet_updated_at(Timestamp tweet_updated_at) {
		this.tweet_updated_at = tweet_updated_at;
	}
	public User getTweet_user_id() {
		return tweet_user_id;
	}
	public void setTweet_user_id(User tweet_user_id) {
		this.tweet_user_id = tweet_user_id;
	}
	
	
	
	
}
