package com.syf.service;

import java.util.List;

import com.syf.dao.ProjectDao;
import com.syf.dao.impl.ProjectDaoImpl;
import com.syf.model.Project;
import com.syf.model.User;

public class ProjectService {
	ProjectDao proDao=new ProjectDaoImpl();

	public List<Project> findAllById(int type_id) {
		return proDao.findAllById(type_id);
	}

	public Project findById(String id) {
		return  new ProjectDaoImpl().findById(id);
	}

	public List<Project> findProjectsIdByUserId(String user_id) {
		return proDao.findProjectsIdByUserId(user_id);
	}

	public int addProject(Project project) {
		return proDao.saveProject(project);
	}

	public int deleteMyProject(String user_uuid, int project_id) {
		return proDao.deleteMyProject(user_uuid, project_id);
	}

	public int editProject(Project project) {
		return proDao.updateMyProject(project);
	}

	public int proFocusNumAdd(String project_id) {
		return proDao.addOneAttentionNum(project_id);
	}

	public int proFocusNumdDele(String project_id) {
		return proDao.deleOneAttentionNum(project_id);
	}

	public List<Project> findProjectsByNum(String user_id) {
		return proDao.findProjectsByNum(user_id);
	}

	public List<Project> findByTypeAndPage(int type_id, int project_id, int pageSize) {
		return proDao.findByTypeAndPage(type_id,project_id,pageSize);
	}

	public List<Project> findIds(int type_id, int project_id) {
		return proDao.findIds(type_id,project_id);
	}

	public List<Project> findByTypeAndIds(int type_id, String bb) {
		return proDao.findByTypeAndIds(type_id,bb);
	}

	public List<Project> listHotPro(int pageNo,int pageSize) {
		return proDao.findHotPro(pageNo,pageSize);
	}

	public List<Project> findHotIds(int project_id) {
		return proDao.findHotIds(project_id);
	}

	public List<Project> findHotByIds(String bb) {
		return proDao.findHotByIds(bb);
	}

	public int addNumber(String user_id, String project_id, int type) {
		return proDao.addNumber(user_id,project_id,type);
	}

	public Project findMinNo(int type_id) {
		return proDao.findMinNo(type_id);
	}

	public int deleNumber(String user_id, String project_id, int type) {
		return proDao.deleNumber(  user_id,   project_id,   type);
	}

	public Project findHotMinNo() {
		return proDao.findHotMinNo();
	}

	public int deleNumAll(int project_id, int type) {
		return proDao.deleNumber( project_id,   type);
	}

}
