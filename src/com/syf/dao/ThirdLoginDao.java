package com.syf.dao;

import com.syf.model.ThirdLogin;

public interface ThirdLoginDao {

	int insertThirdBind(String user_uuid, String third_name, String third_id);

	int thirdLogin(String third_name, String third_id);

	ThirdLogin findUserUuid(String third_name,String third_id);

	int findByUserUuid(String user_uuid);

	int updateInfo(String user_uuid, String third_name, String third_id);

	ThirdLogin findThirdInfo(String user_uuid);

}
