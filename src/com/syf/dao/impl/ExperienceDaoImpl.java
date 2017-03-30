package com.syf.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.syf.dao.ExperienceDao;
import com.syf.model.Experience;
import com.syf.model.Project;
import com.syf.util.DBUtil;

public class ExperienceDaoImpl implements ExperienceDao{
	DBUtil dbUtil=new DBUtil();
	public int add(Experience experience) {
		 String sql = "insert into _experience (company_name,position,enter_time,leave_time,user_uuid)values(?,?,?,?,?)";
		    Object[] params = { 
		    		experience.getCompany_name(),
		    		experience.getPosition(),
		    		experience.getEnter_time(),
		    		experience.getLeave_time(),
		    		experience.getUser_uuid()
 		    };
		    return this.dbUtil.executeUpdate(sql, params);
		  }
	public int delete(int id,String user_uuid) {
		String sql = "delete from _experience where id=? and user_uuid=?";
		Object[] params = { id,user_uuid };
		return dbUtil.executeUpdate(sql, params);
	}
	public int modifyExper(Experience experience) {
		String sql = "update _experience set company_name=?,position=?,enter_time=?,leave_time=? where id=?";
	    Object[] params = {experience.getCompany_name(),experience.getPosition(),experience.getEnter_time(),experience.getLeave_time(),experience.getId() };
	    return this.dbUtil.executeUpdate(sql, params);
	}
	public List<Experience> findExperienceIdByUserId(String user_uuid) {
		String sql="select id,company_name,position,enter_time,leave_time from _experience where user_uuid=?";
	Object[] params={user_uuid};
	return findBySql(sql,params);
	}
	private List<Experience> findBySql(String sql, Object[] params) {
		List<Experience> list=new ArrayList<Experience>();
		ResultSet rs=dbUtil.executeQuery(sql, params);
		try {
			while(rs.next()){
				Experience exper=new Experience(rs.getInt("id"),rs.getString("company_name"), rs.getString("position"),
						rs.getDate("enter_time"),rs.getDate("leave_time"));
				list.add(exper);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			dbUtil.closeAll();
		}
		return list;
	}

}
