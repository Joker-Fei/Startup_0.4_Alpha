package com.syf.action;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.syf.model.ThirdLogin;
import com.syf.service.ThirdLoginService;

public class ThirdLoginServlet extends ActionSupport{

	
	//http://localhost:8080/Startup_0.1_Alpha/third_result?thirdInfo={"user_uuid":"ox5YLs4OO7e2otU7rLv0ojLsWjEM2"}
	private String thirdInfo;
	public String getThirdInfo() {
		return thirdInfo;
	}
	public void setThirdInfo(String thirdInfo) {
		this.thirdInfo = thirdInfo;
	}
	public String thirdResult(){
		 HttpServletRequest request = ServletActionContext.getRequest();
		 JSONObject jsonObject = JSONObject.fromObject(thirdInfo);
		    
	     String user_uuid = jsonObject.getString("user_uuid");
	     
		 ThirdLoginService thirdLoginService=new ThirdLoginService();

		 ThirdLogin thirdLogin=thirdLoginService.findThirdInfo(user_uuid);
		 String qq_id=thirdLogin.getQq_id();
		 String weixin_id=thirdLogin.getWeixin_id();
		 String xinlang_id=thirdLogin.getXinlang_id();
		 
		   JSONObject jsonResultAll = new JSONObject();
	       JSONObject jsonResult = new JSONObject();
	       //jsonResult.put("state", "success");
	       //jsonResultAll.put("data", jsonResult);
		 
		 if(qq_id==null){
			 jsonResult.put("QQ", "false");
		 }else{
			 jsonResult.put("QQ", "true");
		 }
		 
	 	  if(weixin_id==null){
			 jsonResult.put("Wechat", "false");
		 }else{
			 jsonResult.put("Wechat", "true");
		 }
		 
		 if(xinlang_id==null){
			 jsonResult.put("SinaWeibo", "false");
		 }else{
			 jsonResult.put("SinaWeibo", "true");
		 }  
		 
		 jsonResultAll.put("data", jsonResult);
		 String jsonStr = jsonResultAll.toString();
	       request.setAttribute("json", jsonStr);
		 
		 return "thirdResult";
}
}