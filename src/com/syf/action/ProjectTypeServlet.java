package com.syf.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.syf.model.ProjectType;
import com.syf.service.ProjectTypeService;

public class ProjectTypeServlet extends ActionSupport{

	//http://localhost:8080/Startup_0.1_Alpha/project_type_list
	public String typeList(){
		HttpServletRequest request=ServletActionContext.getRequest();
		ProjectTypeService projectTypeService=new ProjectTypeService();
		List<ProjectType> projectList=projectTypeService.findAll();
		List<Map<String,String>> list=new ArrayList<Map<String,String>>();
		
	  for(ProjectType p:projectList){//将 : 后面集合中的元素一一取出赋给 : 左边的变量 然后执行循环体
			int proId=(int)p.getId();
			String proTypeName=p.getType_name();
			
			Map<String,String> map=new HashMap<String,String>();
			map.put("id", String.valueOf(proId));
			map.put("type_name", proTypeName);
			
			list.add(map);
		}  
	  	 JSONObject jsonAll=new JSONObject();
		 JSONObject jsonType=new JSONObject();
	     String success="success";
         JSONArray outArray=JSONArray.fromObject(list);
         jsonType.put("state", success);
         jsonType.put("type", outArray);
         jsonAll.put("data", jsonType);
         String jsonStr = jsonAll.toString();
         request.setAttribute("json",jsonStr);
		
		return "typeList";
	}
}