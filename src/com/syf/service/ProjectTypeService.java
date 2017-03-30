package com.syf.service;

import java.util.List;

import com.syf.dao.ProjectTypeDao;
import com.syf.dao.impl.ProjectTypeDaoImpl;
import com.syf.model.ProjectType;

public class ProjectTypeService {
	ProjectTypeDao proTypeDao=new ProjectTypeDaoImpl();

	public List<ProjectType> findAll() {
		return proTypeDao.findAll();
	}

}
