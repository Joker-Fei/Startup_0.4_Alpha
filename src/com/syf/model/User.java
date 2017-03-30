package com.syf.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class User {

	private int id;
	private String name;
	private String pwd;
	private String phone_num;
	private String skill_id;
	private String focus;
	private String img_url;
	private int info_get;
	private int sex;
	private String school;
	private String major;
	private int experience_id;
	private int online;
	private String uuid;//全局唯一ID
	private String new_comment;//新评论消息提醒
	private String token;
	private Date register_time;
	private String project_ids;
	
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getPwd() {
		return pwd;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getPhone_num() {
		return phone_num;
	}
	public void setPhone_num(String phone_num) {
		this.phone_num = phone_num;
	}
	public String getSkill_id() {
		return skill_id;
	}
	public void setSkill_id(String skill_id) {
		this.skill_id = skill_id;
	}
	public String getFocus() {
		return focus;
	}
	public void setFocus(String focus) {
		this.focus = focus;
	}
	public String getImg_url() {
		return img_url;
	}
	public void setImg_url(String img_url) {
		this.img_url = img_url;
	}
	public int getInfo_get() {
		return info_get;
	}
	public void setInfo_get(int info_get) {
		this.info_get = info_get;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public int getExperience_id() {
		return experience_id;
	}
	public void setExperience_id(int experience_id) {
		this.experience_id = experience_id;
	}
	public int getOnline() {
		return online;
	}
	public void setOnline(int online) {
		this.online = online;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getNew_comment() {
		return new_comment;
	}
	public void setNew_comment(String new_comment) {
		this.new_comment = new_comment;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Date getRegister_time() {
		return register_time;
	}
	public void setRegister_time(Date register_time) {
		this.register_time = register_time;
	}
	public String getProject_ids() {
		return project_ids;
	}
	public void setProject_ids(String project_ids) {
		this.project_ids = project_ids;
	}


	public User() {
		super();
	}

	public User(String name, String pwd, String phone_num,
			String img_url, int sex, String token,String uuid,Date register_time) {
		super();
		this.name = name;
		this.pwd = pwd;
		this.phone_num = phone_num;
		this.img_url = img_url;
		this.sex = sex;
		this.token = token;
		this.uuid = uuid;
		this.register_time=register_time;
	}
	public User(int id,String name,String phone_num,String skill_id, String focus,
			String img_url,int info_get,String pwd, int sex, String school,String major,int experience_id,
			int online,String uuid,String new_comment,String token,Date register_time) {
		super();
		this.id=id;
		this.name = name;
		this.phone_num=phone_num;
		this.skill_id=skill_id;
		this.focus=focus;
		this.img_url = img_url;
		this.info_get=info_get;
		this.pwd = pwd;
		this.sex = sex;
		this.school=school;
		this.major=major;
		this.experience_id=experience_id;
		this.online=online;
		this.uuid=uuid;
		this.new_comment=new_comment;
		this.token = token;
		this.register_time=register_time;
	}
	public User(String uuid, String token,String img_url,String name,int id,int sex ) {
		super();
		this.img_url = img_url;
		this.token = token;
		this.uuid = uuid;
		this.name = name;
		this.id = id;
		this.sex = sex;
	}
	public User(String phone_num, int online) {
		super();
		this.phone_num = phone_num;
		this.online = online;
	}
	public User(String focus,String school, String skill_id,int id,int info_get,String img_url,String phone_num,String token) {
		super();
		this.focus = focus;
		this.school = school;
		this.skill_id=skill_id;
		this.id=id;
		this.info_get=info_get;
		this.img_url=img_url;
		this.phone_num=phone_num;
		this.token=token;
	}
	public User(String phone_num) {
		super();
		this.phone_num = phone_num;
	}
	public User(String uuid, String token, String img_url, String phone_num) {
		super();
		this.uuid=uuid;
		this.token=token;
		this.img_url=img_url;
		this.phone_num=phone_num;
	}
	/* public User(String focus) {
		 	super();
			this.focus=focus;
	} */
	public User(String uuid, String name) {
		super();
		this.uuid=uuid;
		this.name=name;
	}
	public User(String uuid, String token,String img_url,String name,int id,int sex,String phone_num) {
		super();
		this.img_url = img_url;
		this.token = token;
		this.uuid = uuid;
		this.name = name;
		this.id = id;
		this.sex = sex;
		this.phone_num = phone_num;
	}
}
