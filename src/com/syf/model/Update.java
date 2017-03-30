package com.syf.model;

import java.util.Date;

public class Update {

	private int id;
	private String version;
	private String update_info;
	private String url;
	
	public Update(String version ) {
		super();
		this.version =version;
	}
 	public Update(String version, String update_info,String url) {
		super();
		this.version =version;
		this.update_info =update_info;
		this.url =url;
	}
 	
	public Update() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getUpdate_info() {
		return update_info;
	}
	public void setUpdate_info(String update_info) {
		this.update_info = update_info;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
}
