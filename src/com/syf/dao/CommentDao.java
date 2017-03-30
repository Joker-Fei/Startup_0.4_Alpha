package com.syf.dao;

import java.util.List;

import com.syf.model.Comment;

public interface CommentDao {
	Comment findCommentById(int id);

	List<Comment> findCommentsIdByProId(int id);
}
