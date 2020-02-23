package com.tkouleris.tweety.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Follower {
	@Id
	@GeneratedValue
	private Long id;

    @ManyToOne
    @JoinColumn(name = "followee")
    private User followee;
    
    @ManyToOne
    @JoinColumn(name = "follower")
    private User follower;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getFollowee() {
		return followee;
	}

	public void setFollowee(User followee) {
		this.followee = followee;
	}

	public User getFollower() {
		return follower;
	}

	public void setFollower(User follower) {
		this.follower = follower;
	}
    
    
}
