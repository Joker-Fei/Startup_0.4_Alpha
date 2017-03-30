package com.syf.model;

public class ProjectType {

	private int id;
	private String type_name;
	//查找的通用方法处使用
	public ProjectType(int id, String type_name) {
		super();
		this.id=id;
		this.type_name=type_name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getType_name() {
		return type_name;
	}
	public void setType_name(String type_name) {
		this.type_name = type_name;
	}
}
