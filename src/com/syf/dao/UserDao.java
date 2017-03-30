package com.syf.dao;
import java.util.List;

import com.mysql.jdbc.ResultSet;
import com.syf.model.User;

public interface UserDao {

	public int save(User u);
	//public boolean checkUserExistByName(String name);
	public List<User> getUsers();
	public int checkPhoneNum(String phone_num);
	public User findByPNumberAndPwd(String phone_num, String pwd);
	public List<User> findProIdByPhoneNum(String phone_num);
	public int onlineChange(String phone_num);
	public int insertOnline(User user);
	public int updateOnline(String phone_num,int online);
	public int checkPhoneAndThird(String phone_num, String third_login);
	public User findMyProFocus(String user_id);
	public int updateProFocus(String user_id, String focusAll);
	public int updateSchool(String user_id, String school);
	public int updateSkill(String user_id, String skill_id);
	public int updatePush(String user_id, String push);
	public int checkPhoneNumAndPwd(String user_id, String pwd);
	public int changePwd(String user_id, String newPwd);
	public int onlineUpdate(String user_id, String online);
	public User myProFocusInfoByPhone(String phone_num);
	public int updateThirdLogin(String user_id, String third_name);
	public int updatePwd(String phone_num, String pwd);
	public int updateInfo(String user_id, String name, String img_url,String sex);
	public int tokenUpdate(String user_id, String token);
	public List<User> findProjectIdsByUuid(String user_id);
}
