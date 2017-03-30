package com.syf.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.syf.model.Comment;
import com.syf.model.Project;
import com.syf.service.CommentService;
import com.syf.service.ProjectService;
import com.syf.util.PageTool;

public class ProjectServlet extends ActionSupport{
	
	//http://localhost:8080/Startup_0.1_Alpha/project_list?pro_type_id={"type_id":"1","currentCount":"1"}
	private String pro_type_id;
	public String projectList(){
		
		HttpServletRequest request = ServletActionContext.getRequest();
		JSONObject jsonObject = JSONObject.fromObject(pro_type_id);
		    
		String type_id_str = jsonObject.getString("type_id");
	    int type_id=Integer.valueOf(type_id_str);
	    String currentCountStr = jsonObject.getString("currentCount");
		
		ProjectService projectService=new ProjectService();
		//List<Project> projectList=projectService.findAllById(type_id);
		
		//根据类型分页
		int pageNo=1;
		if(currentCountStr!=null&&"".equals(currentCountStr)){
			pageNo=Integer.valueOf(currentCountStr);
		}
		
		int pageSize=15;//每页请求15条
		List<Project> projectList=projectService.findByTypeAndPage(type_id,pageNo,pageSize);
		
		List<Map<String,String>> list=new ArrayList<Map<String,String>>();
		
	    for(Project p:projectList){//将 : 后面集合中的元素一一取出赋给 : 左边的变量 然后执行循环体
			int id=(int)p.getId();
			String name=p.getName();
			String log_url=p.getLog_url();
			String info=p.getInfo();
			String attention_num=p.getAttention_num();
			String good_num=p.getGood_num();
			String comment_num=p.getComment_num();
			String user_id=p.getUser_uuid();
			String skill=p.getSkill();
			String type_name=p.getType_name();
			String progress=p.getProgress();
			String investment=p.getInvestment();
			String team_num=p.getTeam_num();
			String token=p.getToken();
			
			
			Map<String,String> map=new HashMap<String,String>();
			map.put("id", String.valueOf(id));
			map.put("name",name);
			map.put("log_url",log_url);
			map.put("info",info);
			map.put("attention_num",attention_num);
			map.put("good_num",good_num);
			map.put("comment_num",comment_num);
			map.put("user_id",user_id);
			map.put("skill",skill);
			map.put("type_name",type_name);
			map.put("progress",progress);
			map.put("investment",investment);
			map.put("team_num",team_num);
			map.put("token",token);
			
			list.add(map);
		}  
	  	 JSONObject jsonData=new JSONObject();
		 JSONObject jsonInfo=new JSONObject();
	     String success="success";
         JSONArray outArray=JSONArray.fromObject(list);
         
         
         jsonInfo.put("state", success);
         jsonInfo.put("ProjectOverview", outArray);
         jsonInfo.put("pageCount", "currentCount");
         jsonData.put("data", jsonInfo);
         String jsonStr = jsonData.toString();
         request.setAttribute("json",jsonStr);
		
		return "projectList";
	}
	
	private String pro_type_info;
	//上拉加载接口
	//http://localhost:8080/Startup_0.1_Alpha/project_list_next?pro_type_info={"type_id":"1","project_id":"4"}
	public String projectListNext(){
		HttpServletRequest request = ServletActionContext.getRequest();
		JSONObject jsonObject = JSONObject.fromObject(pro_type_info);
		    
		String type_id_str = jsonObject.getString("type_id");
	    int type_id=Integer.valueOf(type_id_str);
	    String project_idStr = jsonObject.getString("project_id");
	    int project_id=Integer.valueOf(project_idStr);
	    
		ProjectService projectService=new ProjectService();
		
	    Project pro=projectService.findMinNo(type_id);
		int minNo=pro.getId();
		if(project_id!=minNo){
		
		String aa="";
		List<Project> projectIds=projectService.findIds(type_id,project_id);
		for(Project p:projectIds){//将 : 后面集合中的元素一一取出赋给 : 左边的变量 然后执行循环体
			int id=(int)p.getId();
			System.out.println("id===="+id);
			aa=aa+id+",";
			}
		String bb=aa.substring(0,aa.length()-1);	
		List<Project> projectList=projectService.findByTypeAndIds(type_id,bb);
		for(int i=0;i<projectList.size();i++){
		System.out.println(projectList);
		}
		
		List<Map<String,String>> list=new ArrayList<Map<String,String>>();
		
	    for(Project p:projectList){//将 : 后面集合中的元素一一取出赋给 : 左边的变量 然后执行循环体
			int id=(int)p.getId();
			String name=p.getName();
		    String log_url=p.getLog_url();
			String info=p.getInfo();
			String attention_num=p.getAttention_num();
			String good_num=p.getGood_num();
			String comment_num=p.getComment_num();
			String user_id=p.getUser_uuid();
			String skill=p.getSkill();
			String type_name=p.getType_name();
			String progress=p.getProgress();
			String investment=p.getInvestment();
			String team_num=p.getTeam_num(); 
			String token=p.getToken(); 
			
			Map<String,String> map=new HashMap<String,String>();
			map.put("id", String.valueOf(id));
			map.put("name",name);
			map.put("log_url",log_url);
			map.put("info",info);
			map.put("attention_num",attention_num);
			map.put("good_num",good_num);
			map.put("comment_num",comment_num);
			map.put("user_id",user_id);
			map.put("skill",skill);
			map.put("type_name",type_name);
			map.put("progress",progress);
			map.put("investment",investment);
			map.put("team_num",team_num);
			map.put("token",token);
			
			list.add(map);
		}  
	    
	  	 JSONObject jsonData=new JSONObject();
		 JSONObject jsonInfo=new JSONObject();
	     String success="success";
         JSONArray outArray=JSONArray.fromObject(list);
         jsonInfo.put("state", success);
         jsonInfo.put("ProjectOverview", outArray);
         jsonInfo.put("pageCount", "currentCount");
         jsonData.put("data", jsonInfo);
         String jsonStr = jsonData.toString();
         request.setAttribute("json",jsonStr); 
		
		return "projectListNext";
		}
		JSONObject jsonData=new JSONObject();
		JSONObject jsonInfo=new JSONObject();
		jsonInfo.put("state", "NoOne");
		jsonData.put("data", jsonInfo);
	    String jsonStr = jsonData.toString();
	    request.setAttribute("json",jsonStr); 
			
	    return "projectListNext";
	}
	
	private String project;
	//http://localhost:8080/Startup_0.1_Alpha/project_detail?project={"id":"36"}
	public String projectDetail(){
		 HttpServletRequest request = ServletActionContext.getRequest();
		 JSONObject jsonObject = JSONObject.fromObject(project);
		 idStr=new String();
	     idStr = jsonObject.getString("id");
	     int id=Integer.valueOf(idStr);
	     
	     //获取Comment详细信息
	     CommentService commentService=new CommentService();
	     List<Comment> commentList=commentService.findCommentsIdByProId(id);
		 List<Map<String,String>> list=new ArrayList<Map<String,String>>();
			
		    for(Comment c:commentList){//将 : 后面集合中的元素一一取出赋给 : 左边的变量 然后执行循环体
				int c_id=(int)c.getId();
				String user_name=c.getUser_name();
				String content=c.getContent();
				Date write_timeDate=c.getWrite_time();
				String write_time=String.valueOf(write_timeDate);
				System.out.println("user_name"+user_name);
				
				Map<String,String> map=new HashMap<String,String>();
				map.put("id", String.valueOf(c_id));
				map.put("user_name",user_name);
				map.put("content",content);
				map.put("write_time",write_time);
				
				list.add(map);
			}  
	     //获取Project详细信息
	     ProjectService projectService=new ProjectService();
	     Project project=projectService.findById(idStr);
		    int pro_idInt=project.getId();
			String pro_id=String.valueOf(pro_idInt);
			String name=project.getName();
			int type_id=project.getType_id();
			String type_name=project.getType_name();
			String logo_url=project.getLog_url();
			String info=project.getInfo();
			String introduce=project.getIntroduce();
			String progress=project.getProgress();
			String investment=project.getInvestment();
			String skill=project.getSkill();
			String team_num=project.getTeam_num();
			Date publish_timeDate=project.getPublish_time();
			 String publish_time=String.valueOf(publish_timeDate);
			//System.out.println("======publish_time"+publish_time);
			Date start_timeDate=project.getStart_time();
			String start_time=String.valueOf(start_timeDate);//此处压入的必须是String类型
		//	System.out.println("======start_time"+start_time);
			int status=project.getStatus();
			String user_uuid=project.getUser_uuid();
			String school=project.getSchool();
			String skill_id=project.getSkill_id();
			 
			JSONObject jsonProject=new JSONObject();
			
			//{"pro_id":"13","name":"建个硬件公司3333","type_id":"2","logo_url":"http://asdasdas","info":"一起啊","introduce":"一起间公司","progress":"正在招募中","investment":"2亿","skill_id":"1,3,4,","team_num":"44","start_time":"2016-05-12","school":"4"}
			jsonProject.put("id", pro_id);
			jsonProject.put("name", name);
			jsonProject.put("type_id",type_id);
			jsonProject.put("type_name",type_name);
			jsonProject.put("logo_url",logo_url);
			jsonProject.put("info",info);
			jsonProject.put("introduce",introduce);
			jsonProject.put("progress",progress);
			jsonProject.put("investment",investment);
			jsonProject.put("skill",skill);
			jsonProject.put("team_num",team_num);
			jsonProject.put("publish_time",publish_time);
			jsonProject.put("start_time",start_time);
			jsonProject.put("status",status);
			jsonProject.put("user_uuid",user_uuid);
			jsonProject.put("school",school);
			jsonProject.put("skill_id",skill_id);

			JSONObject jsonAll=new JSONObject();
			JSONObject jsonType=new JSONObject();
			
			JSONObject jsonProjectOut=new JSONObject();
			JSONObject jsonState=new JSONObject();
			
		    JSONArray outArray=JSONArray.fromObject(list);
		 	JSONObject jsonComment=new JSONObject();
			
		 	String success="success";
		 	//jsonType.put("comment", jsonComment);
		 	jsonType.put("project", jsonProject);
		 	jsonType.put("state", success);
		 	
		 	jsonAll.put("data", jsonType);
			

		 	String jsonStr = jsonAll.toString();
			request.setAttribute("json", jsonStr);
		 
		return "projectDetail";
	}

	//查询热门项目信息（下拉刷新）
	//http://localhost:8080/Startup_0.1_Alpha/pro_hot?proHotInfo={"currentCount":"1"}
	private String proHotInfo;
	public String proHot(){
		HttpServletRequest request = ServletActionContext.getRequest();
		JSONObject jsonObject = JSONObject.fromObject(proHotInfo);
	    String currentCountStr = jsonObject.getString("currentCount");
		ProjectService projectService=new ProjectService();
		//根据类型分页
				int pageNo=1;
				if(currentCountStr!=null&&"".equals(currentCountStr)){
					pageNo=Integer.valueOf(currentCountStr);
				}
				int pageSize=15;//每页请求15条
				//List<Project> projectList=projectService.findByTypeAndPage(type_id,pageNo,pageSize);
				List<Project> proHotList=projectService.listHotPro(pageNo,pageSize);
				
				List<Map<String,String>> list=new ArrayList<Map<String,String>>();
				
			    for(Project p:proHotList){//将 : 后面集合中的元素一一取出赋给 : 左边的变量 然后执行循环体
					int id=(int)p.getId();
					String name=p.getName();
					String log_url=p.getLog_url();
					String info=p.getInfo();
					String attention_num=p.getAttention_num();
					String good_num=p.getGood_num();
					String comment_num=p.getComment_num();
					String user_id=p.getUser_uuid();
					String skill=p.getSkill();
					String type_name=p.getType_name();
					String progress=p.getProgress();
					String investment=p.getInvestment();
					String team_num=p.getTeam_num();
					String token=p.getToken();
					
					Map<String,String> map=new HashMap<String,String>();
					map.put("id", String.valueOf(id));
					map.put("name",name);
					map.put("log_url",log_url);
					map.put("info",info);
					map.put("attention_num",attention_num);
					map.put("good_num",good_num);
					map.put("comment_num",comment_num);
					map.put("user_id",user_id);
					map.put("skill",skill);
					map.put("type_name",type_name);
					map.put("progress",progress);
					map.put("investment",investment);
					map.put("team_num",team_num);
					map.put("token",token);
					
					list.add(map);
				}  
			  	 JSONObject jsonData=new JSONObject();
				 JSONObject jsonInfo=new JSONObject();
			     String success="success";
		         JSONArray outArray=JSONArray.fromObject(list);
		         jsonInfo.put("state", success);
		         jsonInfo.put("ProjectOverview", outArray);
		         jsonInfo.put("pageCount", "currentCount");
		         jsonData.put("data", jsonInfo);
		         String jsonStr = jsonData.toString();
		         request.setAttribute("json",jsonStr);
		
		return "proHot";
	}
	//查询热门信息（上拉加载）
	private String pro_hot;
	private String idStr;
	//http://localhost:8080/Startup_0.1_Alpha/project_hot_next?pro_hot={"project_id":"3"}
	public String proHotListNext(){
		HttpServletRequest request = ServletActionContext.getRequest();
		JSONObject jsonObject = JSONObject.fromObject(pro_hot);
		    
	    String project_idStr = jsonObject.getString("project_id");
	    int project_id=Integer.valueOf(project_idStr);
	    
		ProjectService projectService=new ProjectService();
		
		Project pro=projectService.findHotMinNo();
	    int minNo=pro.getId();
		if(project_id!=minNo){
		
		String aa="";
		List<Project> projectIds=projectService.findHotIds(project_id);
		for(Project p:projectIds){//将 : 后面集合中的元素一一取出赋给 : 左边的变量 然后执行循环体
			int id=(int)p.getId();
			aa=aa+id+",";
			}
		String bb=aa.substring(0,aa.length()-1);	
		List<Project> projectList=projectService.findHotByIds(bb);
		for(int i=0;i<projectList.size();i++){
		}
		
		List<Map<String,String>> list=new ArrayList<Map<String,String>>();
		
	    for(Project p:projectList){//将 : 后面集合中的元素一一取出赋给 : 左边的变量 然后执行循环体
			int id=(int)p.getId();
			String name=p.getName();
		    String log_url=p.getLog_url();
			String info=p.getInfo();
			String attention_num=p.getAttention_num();
			String good_num=p.getGood_num();
			String comment_num=p.getComment_num();
			String user_id=p.getUser_uuid();
			String skill=p.getSkill();
			String type_name=p.getType_name();
			String progress=p.getProgress();
			String investment=p.getInvestment();
			String team_num=p.getTeam_num(); 
			String token=p.getToken(); 
			
			Map<String,String> map=new HashMap<String,String>();
			map.put("id", String.valueOf(id));
			map.put("name",name);
			map.put("log_url",log_url);
			map.put("info",info);
			map.put("attention_num",attention_num);
			map.put("good_num",good_num);
			map.put("comment_num",comment_num);
			map.put("user_id",user_id);
			map.put("skill",skill);
			map.put("type_name",type_name);
			map.put("progress",progress);
			map.put("investment",investment);
			map.put("team_num",team_num);
			map.put("token",token);
			
			list.add(map);
		}  
	  	 JSONObject jsonData=new JSONObject();
		 JSONObject jsonInfo=new JSONObject();
	     String success="success";
         JSONArray outArray=JSONArray.fromObject(list);
         jsonInfo.put("state", success);
         jsonInfo.put("ProjectOverview", outArray);
         jsonInfo.put("pageCount", "currentCount");
         jsonData.put("data", jsonInfo);
         String jsonStr = jsonData.toString();
         request.setAttribute("json",jsonStr); 
		
		return "proHotListNext";
		}
		JSONObject jsonData=new JSONObject();
		JSONObject jsonInfo=new JSONObject();
		jsonInfo.put("state", "NoOne");
		jsonData.put("data", jsonInfo);
	    String jsonStr = jsonData.toString();
	    request.setAttribute("json",jsonStr); 
			
	    return "proHotListNext";
	}
	
	public String getProject() {
		return project;
	}
	public void setProject(String project) {
		this.project = project;
	}
	public String getPro_type_id() {
		return pro_type_id;
	}
	public void setPro_type_id(String pro_type_id) {
		this.pro_type_id = pro_type_id;
	}
	public String getPro_type_info() {
		return pro_type_info;
	}
	public void setPro_type_info(String pro_type_info) {
		this.pro_type_info = pro_type_info;
	}


	public String getProHotInfo() {
		return proHotInfo;
	}


	public void setProHotInfo(String proHotInfo) {
		this.proHotInfo = proHotInfo;
	}

	public String getPro_hot() {
		return pro_hot;
	}

	public void setPro_hot(String pro_hot) {
		this.pro_hot = pro_hot;
	}

}
