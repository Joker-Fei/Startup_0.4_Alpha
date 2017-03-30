package com.syf.action;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.syf.model.Experience;
import com.syf.service.ExperienceService;

public class ExperienceServlet extends ActionSupport{

	//添加实习经历
	//http://localhost:8080/Startup_0.1_Alpha/add_exper?addExperInfo={"user_uuid":"ox5YLsyneSKdJgXNsxJfEFPXLhYU","company_name":"777","position":"开发","enter_time":"2012-04-12","leave_time":"2016-06-22"}
	private String addExperInfo;
	public String addExper(){
		HttpServletRequest request = ServletActionContext.getRequest();
		JSONObject jsonObject = JSONObject.fromObject(addExperInfo);
		
		String user_uuid = jsonObject.getString("user_uuid");
		String company_name = jsonObject.getString("company_name");
		String position = jsonObject.getString("position");
		String enter_timeStr = jsonObject.getString("enter_time");
		String leave_timeStr = jsonObject.getString("leave_time");
		Date enter_time=Date.valueOf(enter_timeStr);
		Date leave_time=Date.valueOf(leave_timeStr);
		
		ExperienceService experienceService=new ExperienceService();
		Experience experience=new Experience(user_uuid,company_name,position,enter_time,leave_time);
		int result=experienceService.addExper(experience);
		  if (result == 0)
		    {
		    	JSONObject jsonResultAll = new JSONObject();
			    JSONObject jsonResult = new JSONObject();
			    jsonResult.put("state", "failed");
			    jsonResultAll.put("data", jsonResult);

			    String jsonStr = jsonResultAll.toString();
			    request.setAttribute("json", jsonStr);
			    return "failed";
		    }
		    JSONObject jsonResultAll = new JSONObject();
		    JSONObject jsonResult = new JSONObject();
		    jsonResult.put("state", "success");
		    jsonResultAll.put("data", jsonResult);

		    String jsonStr = jsonResultAll.toString();
		    request.setAttribute("json", jsonStr);
		    
		    return "addExper";
	}
	//删除实习经历
	//http://localhost:8080/Startup_0.1_Alpha/my_exper_delete?deleteInfo={"user_uuid":"ox5YLsyneSKdJgXNsxJfEFPXLhYU","id":"3"}
	private String deleteInfo;
	public String deleteMyExper(){
		HttpServletRequest request = ServletActionContext.getRequest();
		JSONObject jsonObject = JSONObject.fromObject(deleteInfo);
		String user_uuid = jsonObject.getString("user_uuid");
		String exper_id_str = jsonObject.getString("id");
		int id=Integer.valueOf(exper_id_str);
		
		ExperienceService experienceService=new ExperienceService();
		int result=experienceService.deleteMyExper(id,user_uuid);
		
		if (result == 0)
		    {
		    	JSONObject jsonResultAll = new JSONObject();
			    JSONObject jsonResult = new JSONObject();
			    jsonResult.put("state", "failed");
			    jsonResultAll.put("data", jsonResult);

			    String jsonStr = jsonResultAll.toString();
			    request.setAttribute("json", jsonStr);
			    return "failed";
		    }
		    JSONObject jsonResultAll = new JSONObject();
		    JSONObject jsonResult = new JSONObject();
		    jsonResult.put("state", "success");
		    jsonResultAll.put("data", jsonResult);

		    String jsonStr = jsonResultAll.toString();
		    request.setAttribute("json", jsonStr);
		
		return "deleteMyExper";
	}
	//修改实习经历
	//http://localhost:8080/Startup_0.1_Alpha/modify_exper?experInfo={"id":"2","company_name":"萨伯666","position":"开发","enter_time":"2012-04-12","leave_time":"2016-06-22"}
	private String experInfo;
	public String modifyExper(){
		HttpServletRequest request = ServletActionContext.getRequest();
		JSONObject jsonObject = JSONObject.fromObject(experInfo);
		String idStr = jsonObject.getString("id");
		int id=Integer.valueOf(idStr);
		String company_name = jsonObject.getString("company_name");
		String position = jsonObject.getString("position");
		String enter_timeStr = jsonObject.getString("enter_time");
		String leave_timeStr = jsonObject.getString("leave_time");
		Date enter_time=Date.valueOf(enter_timeStr);
		Date leave_time=Date.valueOf(leave_timeStr);
		ExperienceService experienceService=new ExperienceService();
		Experience experience=new Experience(company_name,position,enter_time,leave_time,id);
		int result=experienceService.modifyExper(experience);
		//System.out.println("result="+result);
		if(result==1){
			JSONObject jsonResultAll = new JSONObject();
		    JSONObject jsonResult = new JSONObject();
		    jsonResult.put("state", "success");
		    jsonResultAll.put("data", jsonResult);

		    String jsonStr = jsonResultAll.toString();
		    request.setAttribute("json", jsonStr);
		    
		    return "success";
		}
			JSONObject jsonResultAll = new JSONObject();
			JSONObject jsonResult = new JSONObject();
			jsonResult.put("state", "falied");
			jsonResultAll.put("data", jsonResult);

			String jsonStr = jsonResultAll.toString();
			request.setAttribute("json", jsonStr);
		
		return "failed";
	}
	//http://localhost:8080/Startup_0.1_Alpha/list_exper?experId={"user_uuid":"ox5YLsyneSKdJgXNsxJfEFPXLhYU"}
	private String experId;
	public String listExper(){
		HttpServletRequest request = ServletActionContext.getRequest();
		JSONObject jsonObject = JSONObject.fromObject(experId);
		String user_uuid = jsonObject.getString("user_uuid");
		
		 ExperienceService experienceService=new ExperienceService();
	     List<Experience> experienceList=experienceService.findExperienceIdByUserId(user_uuid);
		 List<Map<String,String>> list=new ArrayList<Map<String,String>>();
		 
		    for(Experience e:experienceList){//将 : 后面集合中的元素一一取出赋给 : 左边的变量 然后执行循环体
		    	int id=e.getId();
				String company_name=e.getCompany_name();
				String position=e.getPosition();
				java.util.Date enter_time=(Date) e.getEnter_time();
				java.util.Date leave_time=e.getLeave_time();
				
				Map<String,String> map=new HashMap<String,String>();
				map.put("id",String.valueOf(id));
				map.put("company_name",company_name);
				map.put("position",position);
				map.put("enter_time",String.valueOf(enter_time));
				map.put("leave_time",String.valueOf(leave_time));
				
				list.add(map);
			}  
		     JSONObject jsonData=new JSONObject();
			 JSONObject jsonInfo=new JSONObject();
		     String success="success";
	         JSONArray outArray=JSONArray.fromObject(list);
	         jsonInfo.put("state", success);
	         jsonInfo.put("myExperience", outArray);
	         jsonData.put("data", jsonInfo);
	         String jsonStr = jsonData.toString();
	         request.setAttribute("json",jsonStr);
		    
		return "listExper";
	}
	
	public String getAddExperInfo() {
		return addExperInfo;
	}

	public void setAddExperInfo(String addExperInfo) {
		this.addExperInfo = addExperInfo;
	}
	public String getDeleteInfo() {
		return deleteInfo;
	}
	public void setDeleteInfo(String deleteInfo) {
		this.deleteInfo = deleteInfo;
	}

	public String getExperInfo() {
		return experInfo;
	}

	public void setExperInfo(String experInfo) {
		this.experInfo = experInfo;
	}

	public String getExperId() {
		return experId;
	}

	public void setExperId(String experId) {
		this.experId = experId;
	}
}
