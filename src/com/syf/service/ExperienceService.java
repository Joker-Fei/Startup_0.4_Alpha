package com.syf.service;

import java.sql.Date;
import java.util.List;

import com.syf.dao.ExperienceDao;
import com.syf.dao.impl.ExperienceDaoImpl;
import com.syf.model.Experience;

public class ExperienceService {

	ExperienceDao experDao=new ExperienceDaoImpl();
	public int addExper(Experience experience) {
		// TODO Auto-generated method stub
		return experDao.add(experience);
	}
	public int deleteMyExper(int id,String user_uuid) {
		// TODO Auto-generated method stub
		return experDao.delete(id,user_uuid);
	}
	public int modifyExper(Experience experience) {
		return experDao.modifyExper(  experience);
	}
	public List<Experience> findExperienceIdByUserId(String user_uuid) {
		// TODO Auto-generated method stub
		return experDao.findExperienceIdByUserId(  user_uuid);
	}

}
