package com.syf.model;

import java.util.Date;

public class Experience {

	private int id;
	private String company_name;
	private String position;
	private String user_uuid;
	private Date enter_time;
	private Date leave_time;
	public Experience(){
		super();
	}
	public Experience(String user_uuid, String company_name,
			String position, Date enter_time,
			Date leave_time) {
		super();
		this.user_uuid =user_uuid;
		this.company_name =company_name;
		this.position =position;
		this.enter_time =enter_time;
		this.leave_time =leave_time;
	}
	public Experience(String company_name, String position,
			Date enter_time, Date leave_time,
			int id) {
		super();
		this.company_name =company_name;
		this.position =position;
		this.enter_time =enter_time;
		this.leave_time =leave_time;
		this.id =id;
	}
	public Experience(int id, String company_name, String position,
			 Date enter_time, Date leave_time) {
		super();
		this.id = id;
		this.company_name = company_name;
		this.position = position;
		this.enter_time = enter_time;
		this.leave_time = leave_time;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCompany_name() {
		return company_name;
	}
	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public Date getEnter_time() {
		return enter_time;
	}
	public void setEnter_time(Date enter_time) {
		this.enter_time = enter_time;
	}
	public Date getLeave_time() {
		return leave_time;
	}
	public void setLeave_time(Date leave_time) {
		this.leave_time = leave_time;
	}
	public String getUser_uuid() {
		return user_uuid;
	}
	public void setUser_uuid(String user_uuid) {
		this.user_uuid = user_uuid;
	}
}
