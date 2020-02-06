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
    @JoinColumn(name = "follower")
    private User follower;

    @ManyToOne
    @JoinColumn(name = "followed")
    private User followed;
}
