package com.tkouleris.tweety.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Comment {
	@Id
	@GeneratedValue
	private long comment_id;
	
	private String comment_text;
	@ManyToOne	
	private User comment_user_id;
	@ManyToOne
	private Tweet comment_tweet_id;
	private Timestamp comment_created_at;
	private Timestamp comment_updated_at;
	
	public long getComment_id() {
		return comment_id;
	}
	public void setComment_id(long comment_id) {
		this.comment_id = comment_id;
	}
	public String getComment_text() {
		return comment_text;
	}
	public void setComment_text(String comment_text) {
		this.comment_text = comment_text;
	}
	public User getComment_user_id() {
		return comment_user_id;
	}
	public void setComment_user_id(User comment_user_id) {
		this.comment_user_id = comment_user_id;
	}
	public Tweet getComment_tweet_id() {
		return comment_tweet_id;
	}
	public void setComment_tweet_id(Tweet comment_tweet_id) {
		this.comment_tweet_id = comment_tweet_id;
	}
	public Timestamp getComment_created_at() {
		return comment_created_at;
	}
	public void setComment_created_at(Timestamp comment_created_at) {
		this.comment_created_at = comment_created_at;
	}
	public Timestamp getComment_updated_at() {
		return comment_updated_at;
	}
	public void setComment_updated_at(Timestamp comment_updated_at) {
		this.comment_updated_at = comment_updated_at;
	}
	
	
}
