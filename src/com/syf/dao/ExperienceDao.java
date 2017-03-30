package com.syf.dao;

import java.util.List;

import com.syf.model.Experience;

public interface ExperienceDao {

	int add(Experience experience);

	int delete(int id,String user_uuid);

	int modifyExper(Experience experience);

	List<Experience> findExperienceIdByUserId(String user_uuid);

}
