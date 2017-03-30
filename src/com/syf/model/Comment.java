package com.syf.model;

import java.util.Date;

public class Comment {

	private int id;
	private String content;
	private String user_id;
	private int project_id;
	private Date write_time;
	private String user_name;
	
	public Comment(int id, String content, String user_name,Date write_time) {
		super();
		this.id = id;
		this.content = content;
		this.user_name = user_name;
		//this.project_id = project_id;
		this.write_time = write_time;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getProject_id() {
		return project_id;
	}
	public void setProject_id(int project_id) {
		this.project_id = project_id;
	}
	public Date getWrite_time() {
		return write_time;
	}
	public void setWrite_time(Date write_time) {
		this.write_time = write_time;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
}
