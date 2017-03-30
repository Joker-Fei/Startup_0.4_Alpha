package com.syf.action;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.syf.model.Update;
import com.syf.service.UpdateService;

public class UpdateServlet extends ActionSupport{

	//http://localhost:8080/Startup_0.1_Alpha/check_version?version={"currentVersion":"2.0"}
	private String version;
	public String checkVersion(){
		HttpServletRequest request = ServletActionContext.getRequest();
		JSONObject jsonObject = JSONObject.fromObject(version);
		String version = jsonObject.getString("currentVersion");
		
		UpdateService updateService=new UpdateService();
		//Update update=new Update(version);
		Update update=updateService.findNewVersion();
		String newVerion=update.getVersion();
		
		System.out.println("version="+version);
		System.out.println("new version="+newVerion);
		
		//int result=updateService.findByVersion(update);
		if((version !=null && version.equals(newVerion))){

			JSONObject jsonResultAll = new JSONObject();
			JSONObject jsonResult = new JSONObject();
			jsonResult.put("state", "normal");
			jsonResultAll.put("data", jsonResult);

			String jsonStr = jsonResultAll.toString();
			request.setAttribute("json", jsonStr);
		
			return "failed";
		}
		JSONObject jsonResultAll = new JSONObject();
	    JSONObject jsonResult = new JSONObject();
	    jsonResult.put("state", "update");

	    String url=update.getUrl();
	    String updateInfo=update.getUpdate_info();
	    String newVersion=update.getVersion();
	    
	    JSONObject jsonUpdate=new JSONObject();
	    jsonUpdate.put("newVersion", newVersion);
	    jsonUpdate.put("apkDownloadPath", url);
	    jsonUpdate.put("updateInfo", updateInfo);
	    jsonResult.put("Info", jsonUpdate);
	    
	    jsonResultAll.put("data", jsonResult);
	    String jsonStr = jsonResultAll.toString();
	    request.setAttribute("json", jsonStr);
	    
	    return "success";
		
		
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
}
