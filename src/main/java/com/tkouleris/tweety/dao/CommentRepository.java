package com.tkouleris.tweety.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tkouleris.tweety.model.Comment;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long>{

}
