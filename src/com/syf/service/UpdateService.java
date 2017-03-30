package com.syf.service;

import com.syf.dao.UpdateDao;
import com.syf.dao.impl.UpdateDaoImpl;
import com.syf.model.Update;

public class UpdateService {

	UpdateDao updateDao=new UpdateDaoImpl();
	public int findByVersion(Update update) {
		return updateDao.find(update);
	}

	public Update findUpdateByVersion(Update update) {
		return updateDao.findByVersion(update);
	}

	public Update findNewVersion() {
		 
		return updateDao.findNewVersion();
	}

}
