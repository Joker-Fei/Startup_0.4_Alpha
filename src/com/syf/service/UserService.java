package com.syf.service;

import io.rong.models.SdkHttpResult;

import java.sql.ResultSet;
import java.util.List;

import com.syf.dao.UserDao;
import com.syf.dao.impl.UserDaoImpl;
import com.syf.model.User;

public class UserService {

	//注意：在创造UserDao时，用的是实现类的实例
	UserDao userDao=new UserDaoImpl();

	public int register(User user) {
		//System.out.println("===6666====");
		//UserDao userDao = new UserDao(); //接口中没有构造方法，不能被实例化
		int result = userDao.save(user);
		//System.out.println("service层中的result="+result);
		return result;
	}
	 public int checkPhoneNum(String phone_num)
	  {
	    return userDao.checkPhoneNum(phone_num);
	  }
	 public User login(String phone_num, String pwd)
	  {
	    return userDao.findByPNumberAndPwd(phone_num, pwd);
	  }
	public List<User> findProjectIds(String phone_num) {
		return userDao. findProIdByPhoneNum(phone_num);
	}
	public int onlineChange(String phone_num) {
		return userDao.onlineChange(phone_num);
	}
	public int insertOnline(User user) {
		return userDao.insertOnline(user);
	}
	public int updateOnline(String phone_num,int online) {
		return userDao.updateOnline(phone_num,online);
	}
	public int checkPhoneAndThird(String phone_num, String third_login) {
		return userDao.checkPhoneAndThird(phone_num, third_login);
	}
	public User myProFocusInfo(String user_id) {
		return userDao.findMyProFocus(user_id);
	}
	public int proFocus(String user_id, String focusAll) {
		return userDao.updateProFocus(user_id, focusAll);
	}
	public int schoolChange(String user_id, String school) {
		return userDao.updateSchool(user_id, school);
	}
	public int skillChange(String user_id, String skill_id) {
		return userDao.updateSkill(user_id, skill_id);
	}
	public int pushChange(String user_id, String push) {
		return userDao.updatePush(user_id, push);
	}
	public int checkPhoneNumAndPwd(String user_id, String pwd) {
		return userDao.checkPhoneNumAndPwd(user_id,pwd);
	}
	public int changePwd(String user_id, String newPwd) {
		return userDao.changePwd(user_id, newPwd);
	}
	public int onlineChange(String user_id, String online) {
		return userDao.onlineUpdate(user_id, online);
	}
	public User myProFocusInfoByPhone(String phone_num) {
		return userDao.myProFocusInfoByPhone(phone_num);
	}
	public int updateThirdLogin(String user_id, String third_name) {
		return userDao.updateThirdLogin(user_id,third_name);
	}
	public int resetPwd(String phone_num, String pwd) {
		return userDao.updatePwd(phone_num,pwd);
	}
	public int modifyInfo(String user_id, String name,String img_url,String sex) {
		return userDao.updateInfo( user_id,name,img_url, sex);
	}
	public int tokenUpdate(String user_id, String token) {
		return userDao.tokenUpdate(user_id,token);
	}
	public List<User> findProjectIdsByUuid(String user_id) {
		// TODO Auto-generated method stub
		return userDao.findProjectIdsByUuid( user_id);
	}
}
