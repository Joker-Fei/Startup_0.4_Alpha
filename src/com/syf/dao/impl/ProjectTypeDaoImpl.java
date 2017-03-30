package com.syf.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.syf.dao.ProjectTypeDao;
import com.syf.model.ProjectType;
import com.syf.util.DBUtil;

public class ProjectTypeDaoImpl implements ProjectTypeDao{

	DBUtil dbUtil=new DBUtil();
	
	public List<ProjectType> findAll() {
		String sql="select id,type_name from _projecttype";
		return findBySql(sql,null);
	}

	//查找的通用方法
	private List<ProjectType> findBySql(String sql, Object[] params) {
		
		List<ProjectType> list=new ArrayList<ProjectType>();
		ResultSet rs=dbUtil.executeQuery(sql, params);
		try {
			while(rs.next()){
				ProjectType projectType=new ProjectType(rs.getInt("id"),rs.getString("type_name"));
				list.add(projectType);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			dbUtil.closeAll();
		}
		return list;
	}

}
