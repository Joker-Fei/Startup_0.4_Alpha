package com.syf.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.syf.dao.CommentDao;
import com.syf.model.Comment;
import com.syf.model.Project;
import com.syf.model.User;
import com.syf.util.DBUtil;

public class CommentDaoImpl implements CommentDao{

	DBUtil dbUtil=new DBUtil();
	public Comment findCommentById(int id) {
		String sql="select c.id,c.user_id,c.content,c.write_time "
					+"from _comment c "
					+"where c.project_id=?";
		Object[] params={id};
		List<Comment> commentList=findBySQL(sql, params);
		if (commentList != null && commentList.size() > 0) {
			return commentList.get(0);
		}
		return null;
	}

	private List<Comment> findBySQL(String sql, Object[] params) {
		List<Comment> list = new ArrayList<Comment>();
		ResultSet rs = dbUtil.executeQuery(sql, params);
		try {
			while (rs.next()) {
				Comment comment = new Comment(rs.getInt("id"),rs.getString("content"),rs.getString("user_id"),rs.getDate("write_time"));
				list.add(comment);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtil.closeAll();
		}
		return list;
	}

public List<Comment> findCommentsIdByProId(int id) {
		
		String sql="select c.id,c.content,u.name as user_name,c.write_time "
					+"from _comment c,_user u "
					+"where c.user_id=u.uuid and c.project_id=?";
		 Object[] params = { id };
		 return findBySql(sql, params);
	}

	   //查找的通用方法
		private List<Comment> findBySql(String sql, Object[] params) {
			
			List<Comment> list=new ArrayList<Comment>();
			ResultSet rs=dbUtil.executeQuery(sql, params);
			try {
				while(rs.next()){
					Comment comment=new Comment(rs.getInt("id"),rs.getString("content"),rs.getString("user_name"),rs.getDate("write_time"));
					list.add(comment);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				dbUtil.closeAll();
			}
			return list;
		}

		/*//查找的通用方法
		private List<Comment> findBySql(String sql, Object[] params) {
			
			List<Comment> list=new ArrayList<Comment>();
			ResultSet rs=dbUtil.executeQuery(sql, params);
			try {
				while(rs.next()){
					Comment comment=new Comment(rs.getInt("id"),rs.getString("content"),rs.getString("user_name"),rs.getDate("write_time"));
					list.add(comment);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				dbUtil.closeAll();
			}
			return list;
	}*/

}
