package com.syf.service;

import com.syf.dao.ThirdLoginDao;
import com.syf.dao.impl.ThirdLoginDaoImpl;
import com.syf.model.ThirdLogin;

public class ThirdLoginService {

	ThirdLoginDao thirdDao=new ThirdLoginDaoImpl();
	public int thirdBind(String user_uuid, String third_name, String third_id) {
		return thirdDao.insertThirdBind(user_uuid,third_name,third_id);
	}
	public int thirdLogin(String third_name, String third_id) {
		return thirdDao.thirdLogin(third_name,third_id);
	}
	public ThirdLogin findUserUuid(String third_name,String third_id) {
		return thirdDao.findUserUuid(third_name,third_id);
	}
	public int findByUserUuid(String user_uuid) {
		return thirdDao.findByUserUuid(user_uuid);
	}
	public int updateInfo(String user_uuid, String third_name, String third_id) {
		return thirdDao.updateInfo(user_uuid,third_name,third_id);
	}
	public ThirdLogin findThirdInfo(String user_uuid) {
		// TODO Auto-generated method stub
		return thirdDao.findThirdInfo(user_uuid);
	}
}
