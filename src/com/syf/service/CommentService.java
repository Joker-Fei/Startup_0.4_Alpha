package com.syf.service;

import java.util.List;

import com.syf.dao.CommentDao;
import com.syf.dao.impl.CommentDaoImpl;
import com.syf.model.Comment;


public class CommentService {
	CommentDao commentDao=new CommentDaoImpl();
	public Comment findCommentById(int id) {
		return commentDao.findCommentById(id);
	}
	public List<Comment> findCommentsIdByProId(int id) {
		return commentDao.findCommentsIdByProId(id);
	}
}
