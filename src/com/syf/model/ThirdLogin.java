package com.syf.model;

public class ThirdLogin {

	private int id;
	private String uuid;
	private String qq_id;
	private String weixin_id;
	private String xinlang_id;
	public ThirdLogin() {
		super();
	}
	
	public ThirdLogin(int id, String uuid, String qq_id, String weixin_id,
			String xinlang_id) {
		super();
		this.id = id;
		this.uuid = uuid;
		this.qq_id = qq_id;
		this.weixin_id = weixin_id;
		this.xinlang_id = xinlang_id;
	}

	public ThirdLogin(String uuid) {
		// TODO Auto-generated constructor stub
		super();
		this.uuid=uuid;
	}

	public ThirdLogin(String qq_id, String weixin_id, String xinlang_id) {
		super();
		this.qq_id=qq_id;
		this.weixin_id=weixin_id;
		this.xinlang_id=xinlang_id;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getQq_id() {
		return qq_id;
	}
	public void setQq_id(String qq_id) {
		this.qq_id = qq_id;
	}
	public String getWeixin_id() {
		return weixin_id;
	}
	public void setWeixin_id(String weixin_id) {
		this.weixin_id = weixin_id;
	}
	public String getXinlang_id() {
		return xinlang_id;
	}
	public void setXinlang_id(String xinlang_id) {
		this.xinlang_id = xinlang_id;
	}
}
