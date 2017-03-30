package com.syf.dao;

import java.util.List;

import com.syf.model.Project;


public interface ProjectDao {
	List<Project> findAllById(int type_id);

	Project findById(String id);

	List<Project> findProjectsIdByUserId(String user_id);

	int saveProject(Project project);
	public int deleteMyProject(String user_uuid, int project_id);

	int updateMyProject(Project project);

	int saveProFoucs(String user_id, String project_id);

	int addOneAttentionNum(String project_id);

	int deleOneAttentionNum(String project_id);

	List<Project> findProjectsByNum(String focus);

	List<Project> findByTypeAndPage(int type_id, int project_id, int pageSize);

	List<Project> findIds(int type_id, int project_id);


	List<Project> findByTypeAndIds(int type_id, String bb);

	List<Project> findHotPro(int pageNo,int pageSize);

	List<Project> findHotIds(int project_id);

	List<Project> findHotByIds(String bb);

	int addNumber(String user_id, String project_id, int type);

	int nummberUpdate(String user_id, String project_id, int type);

	Project findMinNo(int type_id);

	int deleNumber(String user_id, String project_id, int type);

	Project findHotMinNo();

	int deleNumber(int project_id, int type);

}
