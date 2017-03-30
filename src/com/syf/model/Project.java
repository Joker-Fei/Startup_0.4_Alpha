package com.syf.model;

import java.util.Date;

public class Project {

	private int id;
	private String name;
	private int type_id;
	private String log_url;
	private String info;
	private String introduce;
	private String progress;
	private String investment;
	private String skill_id;
	private String team_num;
	private String attention_num;
	private String good_num;
	private String comment_num;
	private int comment_id;
	private String school;
	private Date publish_time;
	private int rank;
	private Date start_time;
	private String user_uuid;
	private int status;
	private String uuid;
	private String skill;
	private String type_name;
	private int min_no;
	private String token;
	//此处构造函数内的属性顺序必须和DAO层对应的方法内的属性顺序相同
	public Project(int id, String name, String type_name, String log_url, String info,
			String attention_num, String comment_num,  String good_num,
			String skill, String user_uuid,String progress,String investment,String team_num,String token) {
		super();
		this.id = id;
		this.name = name;
		this.log_url = log_url;
		this.info = info;
		this.attention_num = attention_num;
		this.good_num = good_num;
		this.comment_num = comment_num;
		this.user_uuid = user_uuid;
		this.skill = skill;
		this.type_name = type_name;
		this.progress = progress;
		this.investment = investment;
		this.team_num = team_num;
		this.token = token;
	}

	public Project(int id, String name,int type_id, String type_name, String log_url,
			String info, String introduce, String progress, String investment,
			String skill, String team_num, Date publish_time,
			Date start_time, int status,String user_uuid,String school,String skill_id) {
		super();
		this.id = id;
		this.name = name;
		this.type_id = type_id;
		this.log_url = log_url;
		this.info = info;
		this.introduce = introduce;
		this.progress = progress;
		this.investment = investment;
		this.team_num = team_num;
		this.publish_time = publish_time;
		this.start_time = start_time;
		this.status = status;
		 this.skill = skill;
		this.type_name = type_name;
		this.user_uuid = user_uuid;
		this.school = school;
		this.skill_id = skill_id;
	}
	public Project(String user_id, String name, int type_id,
			String log_url, String info, String introduce, String progress,
			String investment, String skill_id, String team_num,
			java.sql.Date start_time, java.sql.Date publish_time,String school) {
		super();
		this.name = name;
		this.type_id = type_id;
		this.log_url= log_url;
		this.info = info;
		this.introduce = introduce;
		this.progress = progress;
		this.investment = investment;
		this.skill_id = skill_id;
		this.team_num = team_num;
		this.publish_time = publish_time;
		this.start_time = start_time;
		this.user_uuid = user_id;
		this.school = school;
	}
	public Project(int pro_id, String name, int type_id,
			String log_url, String info, String introduce, String progress,
			String investment, String skill_id, String team_num,
			java.sql.Date start_time,String school) {
		super();
		this.name = name;
		this.type_id = type_id;
		this.log_url= log_url;
		this.info = info;
		this.introduce = introduce;
		this.progress = progress;
		this.investment = investment;
		this.skill_id = skill_id;
		this.team_num = team_num;
		//this.publish_time = publish_time;
		this.start_time = start_time;
		this.id = pro_id;
		this.school = school;
	}
	//测试用
	public Project(int pro_id, String name) {
		super();
		this.name = name;
		this.id = pro_id;
	}

	public Project(int id,String name, String type_name, String log_url, String info) {
		super();
		this.id = id;
		this.name = name;
		this.type_name = type_name;
		this.log_url = log_url;
		this.info = info;
	}

	public Project(int id) {
		super();
		this.id = id;
	}


	public Project(int id, String attention_num, String good_num, String comment_num) {
		super();
		this.id = id;
		this.attention_num = attention_num;
		this.good_num = good_num;
		this.comment_num = comment_num;
	}


	public Project(int id, String name, int type_id, String logo_url,
			String info, String introduce, String progress, String investment,
			String skill_id, String team_num, Date start_time, String school,
			int status) {
			super();
			this.id = id;
			this.name = name;
			this.type_id = type_id;
			this.log_url= logo_url;
			this.info = info;
			this.introduce = introduce;
			this.progress = progress;
			this.investment = investment;
			this.skill_id = skill_id;
			this.team_num = team_num;
			//this.publish_time = publish_time;
			this.start_time = start_time;
			this.school = school;
			this.status = status;
	}

	public Project(int min_no, int type_id) {
		super();
		this.min_no = min_no;
		this.type_id = type_id;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getType_id() {
		return type_id;
	}
	public void setType_id(int type_id) {
		this.type_id = type_id;
	}
	public String getLog_url() {
		return log_url;
	}
	public void setLog_url(String log_url) {
		this.log_url = log_url;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	public String getProgress() {
		return progress;
	}
	public void setProgress(String progress) {
		this.progress = progress;
	}
	public String getInvestment() {
		return investment;
	}
	public void setInvestment(String investment) {
		this.investment = investment;
	}
	public String getTeam_num() {
		return team_num;
	}
	public void setTeam_num(String team_num) {
		this.team_num = team_num;
	}
	public String getAttention_num() {
		return attention_num;
	}
	public void setAttention_num(String attention_num) {
		this.attention_num = attention_num;
	}
	public String getGood_num() {
		return good_num;
	}
	public void setGood_num(String good_num) {
		this.good_num = good_num;
	}
	public String getComment_num() {
		return comment_num;
	}
	public void setComment_num(String comment_num) {
		this.comment_num = comment_num;
	}
	public int getComment_id() {
		return comment_id;
	}
	public void setComment_id(int comment_id) {
		this.comment_id = comment_id;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public Date getPublish_time() {
		return publish_time;
	}
	public void setPublish_time(Date publish_time) {
		this.publish_time = publish_time;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public Date getStart_time() {
		return start_time;
	}
	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}
	public String getUser_uuid() {
		return user_uuid;
	}
	public void setUser_uuid(String user_uuid) {
		this.user_uuid = user_uuid;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getSkill() {
		return skill;
	}
	public void setSkill(String skill) {
		this.skill = skill;
	} 
	public String getType_name() {
		return type_name;
	}
	public void setType_name(String type_name) {
		this.type_name = type_name;
	}

	public String getSkill_id() {
		return skill_id;
	}

	public void setSkill_id(String skill_id) {
		this.skill_id = skill_id;
	}

	public int getMin_no() {
		return min_no;
	}

	public void setMin_no(int min_no) {
		this.min_no = min_no;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
}
