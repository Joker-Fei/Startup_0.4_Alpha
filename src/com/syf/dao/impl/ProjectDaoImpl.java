package com.syf.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.syf.dao.ProjectDao;
import com.syf.model.Project;
import com.syf.util.DBUtil;

public class ProjectDaoImpl implements ProjectDao{
	DBUtil dbUtil=new DBUtil();
	public List<Project> findAllById(int type_id) {
		String sql="select p.id,p.name,pt.type_name,p.logo_url,p.info,p.attention_num,p.comment_num,p.good_num,( select group_concat(s.name separator ',') from _skill s,_project g where g.id=p.id and find_in_set(s.id,REPLACE(g.skill_id,' ',''))) as skill,p.user_uuid,p.progress,p.investment,p.team_num "
					+"from _project p,_projecttype pt,_skill s "
					+"where p.type_id=? and p.type_id=pt.id "
					+"group by p.id";
		Object[] params={type_id};
		return findBySql(sql,params);
	}

	//查找的通用方法
	private List<Project> findBySql(String sql, Object[] params) {
		
		List<Project> list=new ArrayList<Project>();
		ResultSet rs=dbUtil.executeQuery(sql, params);
		try {
			while(rs.next()){
				Project project=new Project(rs.getInt("id"),rs.getString("name"),rs.getString("type_name"),rs.getString("logo_url"),
						rs.getString("info"),rs.getString("attention_num"),rs.getString("comment_num"),rs.getString("good_num"),
						rs.getString("skill"),rs.getString("user_uuid"),rs.getString("progress"),rs.getString("investment"),rs.getString("team_num"),rs.getString("token"));
				list.add(project);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			dbUtil.closeAll();
		}
		return list;
	}

	public Project findById(String id) {
		String sql="select p.id,p.name,p.type_id,pt.type_name,p.logo_url,p.info,p.introduce,p.progress,p.investment,(select group_concat(s.name separator ',') from _skill s,_project g where g.id=p.id and find_in_set(s.id,REPLACE(g.skill_id,' ',''))) as skill,p.team_num,p.publish_time,p.start_time,p.status,p.user_uuid,p.school,p.skill_id " 
					+"from _project p,_projecttype pt "
					+"where pt.id=p.type_id and p.id=?";
		Object[] params={id};
		List<Project> projectList=findBySQL(sql, params);
		if (projectList != null && projectList.size() > 0) {
			return projectList.get(0);
		}
		return null;
	}
	
	// 查找的通用方法（返回一个集合）
	private List<Project> findBySQL(String sql, Object[] params) {
					List<Project> list = new ArrayList<Project>();
					ResultSet rs = dbUtil.executeQuery(sql, params);
					try {
						while (rs.next()) {
							Project project = new Project(rs.getInt("id"), rs.getString("name"),rs.getInt("type_id"),rs.getString("type_name"),rs.getString("logo_url"),rs.getString("info"),rs.getString("introduce"),rs.getString("progress"),rs.getString("investment"),
									rs.getString("skill"),rs.getString("team_num"),rs.getDate("publish_time"),rs.getDate("start_time"),rs.getInt("status"),rs.getString("user_uuid"),rs.getString("school"),rs.getString("skill_id"));
							list.add(project);
						}
					} catch (SQLException e) {
						e.printStackTrace();
					} finally {
						dbUtil.closeAll();
					}
					return list;
	}
	
	//根据user_id查找用户自己的项目
	public List<Project> findProjectsIdByUserId(String user_id) {
			String sql="select p.id,p.name,p.type_id,p.logo_url,p.info,p.introduce,p.progress,p.investment,p.skill_id,p.team_num,p.start_time,p.school,p.status "
						+"from _project p,_projecttype pt "
						+"where p.type_id=pt.id and p.user_uuid=?";
			Object[] params={user_id};
			return findBySql4(sql,params);
	}

	private List<Project> findBySql4(String sql, Object[] params) {
		List<Project> list=new ArrayList<Project>();
		ResultSet rs=dbUtil.executeQuery(sql, params);
		try {
			while(rs.next()){
				Project project=new Project(rs.getInt("id"),rs.getString("name"),rs.getInt("type_id"),rs.getString("logo_url"),
						rs.getString("info"),rs.getString("introduce"),rs.getString("progress"),rs.getString("investment"),
						rs.getString("skill_id"),rs.getString("team_num"),rs.getDate("start_time"),rs.getString("school"),rs.getInt("status"));
				list.add(project);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			dbUtil.closeAll();
		}
		return list;
	}

	public int saveProject(Project project) {
		  {
		    String sql = "insert into _project (user_uuid,name,type_id,logo_url,info,introduce,progress,investment,skill_id,team_num,start_time,publish_time,school)values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
		    Object[] params = { 
		    		project.getUser_uuid(),
		    		project.getName(),
		    		project.getType_id(),
		    		project.getLog_url(),
		    		project.getInfo(),
		    		project.getIntroduce(),
		    		project.getProgress(),
		    		project.getInvestment(),
		    		project.getSkill_id(),
		    		project.getTeam_num(),
		    		project.getStart_time(),
		    		project.getPublish_time(),
		    		project.getSchool()
		    };
		    return this.dbUtil.executeUpdate(sql, params);
		  }
	}
	// 项目的删除
	public int deleteMyProject(String user_uuid, int project_id) {
			String sql = "delete from _project where id=? and user_uuid=?";
			Object[] params = { project_id,user_uuid };
			return dbUtil.executeUpdate(sql, params);
		}
    //更新/修改项目信息
	public int updateMyProject(Project project) {
			String sql = "update _project set name=?,type_id=?,logo_url=?,"
					+ "info=?,introduce=?,progress=?,investment=?,skill_id=?,team_num=?,start_time=?,school=?,status=? where id=?";
			//注意：此处的params中的参数顺序必须和上边SQL语句中输入的参数顺序保持一致，否则插入不成功
			Object[] params = { 
					
					project.getName(),
					project.getType_id(),
					project.getLog_url(),
					project.getInfo(),
					project.getIntroduce(),
					project.getProgress(),
					project.getInvestment(),
					project.getSkill_id(),
					project.getTeam_num(),
					project.getStart_time(),
					project.getSchool(),
					project.getStatus(),
					project.getId() 
					};
			return dbUtil.executeUpdate(sql, params);
	}
	//关注项目
	public int saveProFoucs(String user_id, String project_id) {
			  String sql = "update _user set online='" + user_id + "'" +" where phone_num=" + project_id;
			  int iii = this.dbUtil.executeUpdate2(sql);
			  return iii;
		}

	public int addOneAttentionNum(String project_id) {
		String sql = "update _project set attention_num=attention_num+1 where id="+project_id;
		  int iii = this.dbUtil.executeUpdate2(sql);
		  return iii;
	}

	public int deleOneAttentionNum(String project_id) {
		String sql = "update _project set attention_num=attention_num-1 where id="+project_id;
		  int iii = this.dbUtil.executeUpdate2(sql);
		  return iii;
	}
//rs.getInt("id"),rs.getString("name"),rs.getString("type_name"),rs.getString("logo_url"),rs.getString("info")
	public List<Project> findProjectsByNum(String user_id) {
		String sql="select p.id,p.name,pt.type_name,p.logo_url,p.info "
					+"from _project p,_number n,_projecttype pt "
					+"where pt.id=p.type_id and n.user_uuid=? and n.project_id=p.id and type=1 "
					+"order by p.id ";
		Object[] params={user_id};
		return findBySQL2(sql,params);
	}
	private List<Project> findBySQL2(String sql, Object[] params) {
		List<Project> list = new ArrayList<Project>();
		ResultSet rs = dbUtil.executeQuery(sql, params);
		try {
			while (rs.next()) {
				Project project = new Project( rs.getInt("id"),rs.getString("name"),rs.getString("type_name"),rs.getString("logo_url"),rs.getString("info"));
				list.add(project);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtil.closeAll();
		}
		return list;
}

	
	public List<Project> findByTypeAndPage(int type_id, int project_id, int pageSize) {
		
			String sql="select p.id,p.name,pt.type_name,p.logo_url,p.info,p.attention_num,p.comment_num,p.good_num,( select group_concat(s.name separator ',') from _skill s,_project g where g.id=p.id and find_in_set(s.id,REPLACE(g.skill_id,' ',''))) as skill,p.user_uuid,p.progress,p.investment,p.team_num,u.token "
						+"from _project p,_projecttype pt,_skill s,_user u "
						+"where p.status=1 and p.type_id=? and p.type_id=pt.id and p.user_uuid=u.uuid "
						+"group by p.id "
						+"order by p.id desc "
						+"limit ?,?";
		
		Object[] params={type_id,project_id,pageSize};
			return findBySql(sql,params);
	}

	public List<Project> findIds(int type_id, int project_id) {
		String sql="select pp.id from _project pp where pp.id <?  and type_id=? order by pp.id asc limit 0,15 ";
		Object[] params={project_id,type_id};
		return findBySql2(sql,params);
	}

	private List<Project> findBySql2(String sql, Object[] params) {
			List<Project> list=new ArrayList<Project>();
			ResultSet rs=dbUtil.executeQuery(sql, params);
			try {
				while(rs.next()){
					Project project=new Project(rs.getInt("id"));
					list.add(project);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				dbUtil.closeAll();
			}
			return list;
		}

	public List<Project> findByTypeAndIds(int type_id, String bb) {
		 String sql="select p.id,p.name,pt.type_name,p.logo_url,p.info,p.attention_num,p.comment_num,p.good_num,( select group_concat(s.name separator ',') from _skill s,_project g where g.id=p.id and find_in_set(s.id,REPLACE (g.skill_id,' ',''))) as skill,p.user_uuid,p.progress,p.investment,p.team_num,u.token "
				 						+"from _project p,_projecttype pt,_skill s,_user u " 
				 						+"where p.type_id=? and p.type_id=pt.id and p.user_uuid=u.uuid and p.id in("+bb+") "
				 						+"group by p.id ";
		Object[] params={type_id};
		return findBySql(sql,params);
	}
	
	public List<Project> findHotPro(int pageNo,int pageSize) {
		String sql="select p.id,p.name,pt.type_name,p.logo_url,p.info,p.attention_num,p.comment_num,p.good_num,( select group_concat(s.name separator ',') from _skill s,_project g where g.id=p.id and find_in_set(s.id,REPLACE(g.skill_id,' ',''))) as skill,p.user_uuid,p.progress,p.investment,p.team_num,u.token   "
				+"from _project p,_projecttype pt,_skill s,_user u "
				+"where p.status=1 and p.type_id=pt.id "
				+"group by p.id "
				+"order by  ((p.attention_num+p.good_num+p.comment_num)+1) desc "
				+"limit ?,?";
		Object[] params={pageNo,pageSize};
		return findBySql(sql,params);
	}

	public List<Project> findHotIds(int project_id) {
		String sql="select pp.id,pp.attention_num,pp.good_num,pp.comment_num "
				+"from _project pp "
				+"where pp.id <? "
				+"group by pp.id "
				+"order by ((pp.attention_num+pp.good_num+pp.comment_num)+1) desc "
				+"limit 0,15 ";
		Object[] params={project_id};
		return findBySql3(sql,params);
	}

	public List<Project> findHotByIds(String bb) {
		 String sql="select p.id,p.name,pt.type_name,p.logo_url,p.info,p.attention_num,p.comment_num,p.good_num,( select group_concat(s.name separator ',') from _skill s,_project g where g.id=p.id and find_in_set(s.id,REPLACE (g.skill_id,' ',''))) as skill,p.user_uuid,p.progress,p.investment,p.team_num,u.token  "
					+"from _project p,_projecttype pt,_skill s,_user u " 
					+"where p.type_id=pt.id and p.id in("+bb+") "
					+"group by p.id "
					+"order by  ((p.attention_num+p.good_num+p.comment_num)+1) desc ";
				return findBySql(sql,null);
	}
	private List<Project> findBySql3(String sql, Object[] params) {
		List<Project> list=new ArrayList<Project>();
		ResultSet rs=dbUtil.executeQuery(sql, params);
		try {
			while(rs.next()){
				Project project=new Project(rs.getInt("id"),rs.getString("attention_num"),rs.getString("good_num"),rs.getString("comment_num"));
				list.add(project);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			dbUtil.closeAll();
		}
		return list;
	}

	public int addNumber(String user_id, String project_id, int type) {
		  String sql = "insert into _number(user_uuid,project_id,type) values('"+user_id+"','"+project_id+"','"+type+"')";
		  int iii = this.dbUtil.executeUpdate2(sql);
		  return iii;
	}

	public int nummberUpdate(String user_id, String project_id, int type) {
		// TODO Auto-generated method stub
		return 0;
	}

	public Project findMinNo(int type_id) {
		String sql="select min(p.id) as min_no from _project p where p.type_id=? ";
		Object[] params={type_id};
		List<Project> projectList=findMinNo(sql, params);
		if (projectList != null && projectList.size() > 0) {
			return projectList.get(0);
		}
		return null;
		}

	private List<Project> findMinNo(String sql, Object[] params) {
		List<Project> list = new ArrayList<Project>();
		ResultSet rs = dbUtil.executeQuery(sql, params);
		try {
			while (rs.next()) {
				Project project = new Project(rs.getInt("min_no"));
				list.add(project);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtil.closeAll();
		}
		return list;
}

	public int deleNumber(String user_id, String project_id, int type) {
		String sql = "delete from _number where user_uuid=? and project_id=? and type=?";
		Object[] params = { user_id,project_id ,type};
		return dbUtil.executeUpdate(sql, params);
	}

	public Project findHotMinNo() {
		String sql="select min(p.id) as min_no from _project p ";
		List<Project> projectList=findMinNo(sql, null);
		if (projectList != null && projectList.size() > 0) {
			return projectList.get(0);
		}
		return null;
	}

	public int deleNumber(int project_id, int type) {
		String sql = "delete from _number where project_id=? and type=?";
		Object[] params = {project_id ,type};
		return dbUtil.executeUpdate(sql, params);
	}
}
