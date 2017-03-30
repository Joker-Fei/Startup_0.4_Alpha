package com.syf.action;

import io.rong.ApiHttpClient;
import io.rong.models.FormatType;
import io.rong.models.SdkHttpResult;

import java.io.File;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.struts2.ServletActionContext;
import com.mysql.jdbc.ResultSet;
import com.opensymphony.xwork2.ActionSupport;
import com.syf.dto.UserInfo;
import com.syf.model.Comment;
import com.syf.model.Project;
import com.syf.model.ThirdLogin;
import com.syf.model.User;
import com.syf.service.CommentService;
import com.syf.service.ProjectService;
import com.syf.service.ThirdLoginService;
import com.syf.service.UserService;
import com.syf.util.JSONTools;
import com.syf.util.FileUpload;

public class UserServlet extends ActionSupport {

	//private UserInfo userInfo=new UserInfo();
	private String register;
	  String imgUpload;
	  String imgFileName;
	  String imgContentType;
	  File img;
	  String fileId;
	//http://localhost:8080/Startup_0.1_Alpha/register?register={"name":"哎哎哎","phone_num":"66666666666","pwd":"123123","img_url":"http://hasijdhaisdh","sex":"1"}
	public String register() throws Exception{
		
	   // System.out.println("===进入register方法===");
	    HttpServletRequest request = ServletActionContext.getRequest();
	    JSONObject jsonObject = JSONObject.fromObject(register);
	    
        String name = jsonObject.getString("name");
        String phone_num = jsonObject.getString("phone_num");
	    String pwd = jsonObject.getString("pwd");
	    String img_url = jsonObject.getString("img_url");
	    String sexString = jsonObject.getString("sex");
	   // String token = jsonObject.getString("token");
	    int sex=Integer.valueOf(sexString);
	    
	    UUID uuidUUID = UUID.randomUUID();
	    String uuid=String.valueOf(uuidUUID);
	    
	    Calendar c = Calendar.getInstance();
	    int y = c.get(1);
	    int m = c.get(2)+1;
	    int d = c.get(5);
	    String date = y + "-" + m + "-" + d;
	    Date register_time = Date.valueOf(date);
	    System.out.println("time="+date);
	    
	    //获取token
	  	String key = "6tnym1brnogs7";//替换成您的appkey
	  	String secret = "a9zjfcSeGFCsCI";//替换成匹配上面key的secret
	  	SdkHttpResult resultGet = null;

	    resultGet = ApiHttpClient.getToken(key, secret, uuid, name,
	    		img_url, FormatType.json);
	  	System.out.println("gettoken=" + resultGet);
	  	
	  	JSONTools jsonTools=new JSONTools();
	  	@SuppressWarnings("static-access")
		String restlt1=jsonTools.getJsonValue("result",resultGet.toString());
	  	@SuppressWarnings("static-access")
		String token=jsonTools.getJsonValue("token",restlt1);
	  	//System.out.println("===token==="+token);
	  	
	    User user=new User(name,pwd,phone_num,img_url,sex,token,uuid,register_time);
	    UserService userService = new UserService();
	    int result = userService.register(user);

	   if (result == 0)
	    {
		   JSONObject jsonResultAll = new JSONObject();
		    JSONObject jsonResult = new JSONObject();
		    jsonResult.put("state", "ERROR105");
		    jsonResultAll.put("data", jsonResult);

		    String jsonStr = jsonResultAll.toString();
		    request.setAttribute("json", jsonStr);
	    }
	    JSONObject jsonResultAll = new JSONObject();
	    JSONObject jsonResult = new JSONObject();
	    jsonResult.put("state", "success");
	    jsonResultAll.put("data", jsonResult);

	    String jsonStr = jsonResultAll.toString();
	    request.setAttribute("json", jsonStr);

	    return "success";
	    /*
	    String state="SUCCESS";
	    JSONObject jsonAll=new JSONObject();
		JSONObject jsonContent=new JSONObject();
		jsonContent.put("state", state); 
		jsonContent.put("token", token); 
		jsonAll.put("data", jsonContent);
		String jsonStr=jsonAll.toString();
		request.setAttribute("json", jsonStr);
		
		return "token";
	   */
	}
	//获取token
	//http://localhost:8080/Startup_0.1_Alpha/getToken?tokeninfo={"name":"哎哎哎","img_url":"http://hasijdhaisdh","user_uuid":"21asdfafdsafasdfads=="}
	private String tokeninfo;
	public String getToken() throws Exception{
		
		   // System.out.println("===进入register方法===");
		    HttpServletRequest request = ServletActionContext.getRequest();
		    JSONObject jsonObject = JSONObject.fromObject(tokeninfo);
		    
	        String name = jsonObject.getString("name");
		    String img_url = jsonObject.getString("img_url");
		    String uuid = jsonObject.getString("user_uuid");
		    
		  /*  UUID uuidUUID = UUID.randomUUID();
		    String uuid=String.valueOf(uuidUUID);*/
		    
		    //获取token
		  	String key = "6tnym1brnogs7";//替换成您的appkey
		  	String secret = "a9zjfcSeGFCsCI";//替换成匹配上面key的secret
		  	SdkHttpResult resultGet = null;

		    resultGet = ApiHttpClient.getToken(key, secret, uuid, name,
		    		img_url, FormatType.json);
		  	System.out.println("gettoken=" + resultGet);
		  	
		  	JSONTools jsonTools=new JSONTools();
		  	@SuppressWarnings("static-access")
			String restlt1=jsonTools.getJsonValue("result",resultGet.toString());
		  	@SuppressWarnings("static-access")
			String token=jsonTools.getJsonValue("token",restlt1);
		  	//System.out.println("===token==="+token);
		  	
			request.setAttribute("json", token);
			
			return "token";
		}
	//手机号验证是否注册过
	private String num_check;
	//http://localhost:8080/Startup_0.1_Alpha/phone_num_check?num_check={"phone_num":"11111111111","third_login":"Wechat"}
	public String numCheck() {
		    HttpServletRequest request = ServletActionContext.getRequest();
		    JSONObject jsonObject = JSONObject.fromObject(num_check);
		    String phone_num = jsonObject.getString("phone_num");
		    String third_login = jsonObject.getString("third_login");
		    
		    if(third_login.equals("QQ")){
		    	third_login="qq_id";
		    }else if(third_login.equals("Wechat")){
		    	third_login="weixin_id";
		    }else if(third_login.equals("SinaWeibo")){
		    	third_login="xinlang_id";
		    }
		    
		    UserService userService = new UserService();

		    int result = userService.checkPhoneNum(phone_num);
		    if (result == 1) //手机号已注册过
		    {
		      if(third_login.equals("")){//第三方为空，传进来的只有手机号
		    	  JSONObject jsonResultAll = new JSONObject();
			      JSONObject jsonResult = new JSONObject();
			      jsonResult.put("state", "Registed");
			      jsonResultAll.put("data", jsonResult);
			      String jsonStr = jsonResultAll.toString();
			      request.setAttribute("json", jsonStr);
		      }	else{
		    	  int result_p_t = userService.checkPhoneAndThird(phone_num,third_login);
		    	 // int result_p_t = userService.checkPhoneAndThird(phone_num,third_login);
		    	  System.out.println("====="+result_p_t);
		    	  if(result_p_t==1){//手机号已注册，已绑定
		    		  JSONObject jsonResultAll = new JSONObject();
				      JSONObject jsonResult = new JSONObject();
				      jsonResult.put("state", "binded");
				      jsonResultAll.put("data", jsonResult);
				      String jsonStr = jsonResultAll.toString();
				      request.setAttribute("json", jsonStr);
		    	  }else{//手机号已注册，未绑定
		    		  JSONObject jsonResultAll = new JSONObject();
				      JSONObject jsonResult = new JSONObject();
				      jsonResult.put("state", "unbind");
				      jsonResultAll.put("data", jsonResult);
				      String jsonStr = jsonResultAll.toString();
				      request.setAttribute("json", jsonStr);
		    	  }
		      }
		    }
		    else//未注册-->
		    {
		    	if(third_login.equals("")){ 
		    		JSONObject jsonResultAll = new JSONObject();
		    		JSONObject jsonResult = new JSONObject();
		    		jsonResult.put("state", "unRegist");
		    		jsonResultAll.put("data", jsonResult);
		    		String jsonStr = jsonResultAll.toString();
		    		request.setAttribute("json", jsonStr);
		    	}else{
		    		JSONObject jsonResultAll = new JSONObject();
		    		JSONObject jsonResult = new JSONObject();
		    		jsonResult.put("state", "UnRegBin");
		    		jsonResultAll.put("data", jsonResult);
		    		String jsonStr = jsonResultAll.toString();
		    		request.setAttribute("json", jsonStr);
		    	}
		    }
		    return "numCheck";
		  }
	
	 FileUpload fileUpload = new FileUpload();
	 public String imgUpload()
			    throws Exception
			  {
			    String imgUrl = this.fileUpload.upload(this.imgFileName, this.img, this.imgContentType);

			    HttpServletRequest request = ServletActionContext.getRequest();

			    JSONObject jsonResult = new JSONObject();

			    jsonResult.put("fileUrl", imgUrl);

			    jsonResult.put("fileId", this.fileId);

			    String jsonStr = jsonResult.toString();
			    request.setAttribute("json", jsonStr);
			    return "imgUrl";
			  }
	
	//用户登陆
	private String login;
	//http://localhost:8080/Startup_0.1_Alpha/user_login?login={"phone_num":"18345098136","pwd":"123","online":"1"}
	public String login(){
		 //System.out.println("开始登陆");
	    HttpServletRequest request = ServletActionContext.getRequest();
	    JSONObject jsonObject = JSONObject.fromObject(login);
	    
	    String phone_num = jsonObject.getString("phone_num");
	    String pwd = jsonObject.getString("pwd");
	    String onlineString=jsonObject.getString("online");
	    int online =Integer.valueOf(onlineString);
	    
	    UserService userService = new UserService();
	    
	    int phone_num_check=userService.checkPhoneNum(phone_num);//查看该用户是否已注册：已注册为1；未注册为0
	    //System.out.println("=="+phone_num_check);
	    if(phone_num_check==0){
	    	//手机号未注册，不存在该用户
	    	//System.out.println("手机号未注册，不存在该用户");
	    	JSONObject jsonData = new JSONObject();
	    	JSONObject jsonResult = new JSONObject();
	    	jsonResult.put("state", "ERROR101");
	    	jsonData.put("data", jsonResult);
	    	String jsonStr = jsonData.toString();
	    	request.setAttribute("json", jsonStr);
	    	return "ERROR101";
	    }else{
	    	//System.out.println("手机号不为空，已注册");
	    	User loginUser = userService.login(phone_num, pwd);
	    	if(loginUser == null){
	    	//手机号已注册，存在该用户,账号和密码不匹配
	    	//System.out.println("账号密码不匹配");
	    		JSONObject jsonData = new JSONObject();
	    		JSONObject jsonResult = new JSONObject();
	    		jsonResult.put("state", "ERROR102");
	    		jsonData.put("data", jsonResult);
	    		String jsonStr = jsonData.toString();
	    		request.setAttribute("json", jsonStr);
	    		return "ERROR102";
	    	}else{
	    	int a=userService.onlineChange(phone_num);
	    	int c=userService.updateOnline(phone_num,online);
	    		
	        //取出 token  img_url
	    	List<User> userList=userService.findProjectIds(phone_num);
	    	List<Map<String,String>> list=new ArrayList<Map<String,String>>();
	    	
	    	for(User u:userList){
	    	String user_id=u.getUuid();
	    	String img_url=u.getImg_url();
	    	String token=u.getToken();
	    	String name=u.getName();
	    	String sex=String.valueOf(u.getSex());
			 
	    	Map<String,String> map=new HashMap<String,String>();
	    	
	    	map.put("user_id",user_id);
	    	map.put("img_url",img_url);
	    	map.put("token",token);
	    	map.put("name",name);
	    	map.put("sex",sex);
	    	map.put("phone_num",phone_num);
	    	
	    	list.add(map);
	    	}
	    	JSONArray outArray=JSONArray.fromObject(list);	
	     
	    	HttpSession session = request.getSession();
	    	JSONObject jsonData=new JSONObject();
			JSONObject jsonInfo=new JSONObject();
		    String success="success";
	        jsonInfo.put("state", success);
	        for (int i = 0; i < outArray.size(); i++) {
	        	JSONObject jo = (JSONObject) outArray.get(0);
	        	jsonInfo.put("userInfo", jo);
	        }
	        
	        jsonData.put("data", jsonInfo);
	 	    String jsonStr = jsonData.toString();
	 	    session.setAttribute("json", jsonStr);

	 		return "success";}} 
	}
	//根据user_id查找用户自己的项目
	//http://localhost:8080/Startup_0.1_Alpha/my_project_list?projectInfo={"user_id":"ox5YLsyneSKdJgXNsxJfEFPXLhYU"}
	private String projectInfo;
	public String myProject(){
		HttpServletRequest request = ServletActionContext.getRequest();
		JSONObject jsonObject = JSONObject.fromObject(projectInfo);
		String user_id = jsonObject.getString("user_id");
		
		 ProjectService projectService=new ProjectService();
	     List<Project> projectList=projectService.findProjectsIdByUserId(user_id);
		 List<Map<String,String>> list=new ArrayList<Map<String,String>>();
		 
		    for(Project p:projectList){//将 : 后面集合中的元素一一取出赋给 : 左边的变量 然后执行循环体
				int p_id=(int)p.getId();
				String name=p.getName();
				int type_id=p.getType_id();
				String logo_url=p.getLog_url();
				String info=p.getInfo();
				String introduce=p.getIntroduce();
				String progress=p.getProgress();
				String investment=p.getInvestment();
				String skill_id=p.getSkill_id();
				String team_num=p.getTeam_num();
				String school=p.getSchool();
				java.util.Date start_timeDate=p.getStart_time();
				String start_time=String.valueOf(start_timeDate);
				int statusInt=p.getStatus();
				String status=String.valueOf(statusInt);
				
				Map<String,String> map=new HashMap<String,String>();
				map.put("id", String.valueOf(p_id));
				map.put("name",name);
				map.put("type_id", String.valueOf(type_id));
				map.put("logo_url",logo_url);
				map.put("info",info);
				map.put("introduce",introduce);
				map.put("progress",progress);
				map.put("investment",investment);
				map.put("skill_id",skill_id);
				map.put("team_num",team_num);
				map.put("start_time",start_time);
				//map.put("type_name",type_name);
				map.put("status",status);
				map.put("school",school);
				
				list.add(map);
			}  
		     JSONObject jsonData=new JSONObject();
			 JSONObject jsonInfo=new JSONObject();
		     String success="success";
	         JSONArray outArray=JSONArray.fromObject(list);
	         jsonInfo.put("state", success);
	         jsonInfo.put("myProject", outArray);
	         jsonData.put("data", jsonInfo);
	         String jsonStr = jsonData.toString();
	         request.setAttribute("json",jsonStr);
		    
		return "myProject";
	}
	//添加我的项目
	//http://localhost:8080/Startup_0.1_Alpha/my_project_add?addProjectInfo={"user_id":"ox5YLsyneSKdJgXNsxJfEFPXLhYU","name":"建个硬件公司","type_id":"2","logo_url":"http://asdasdas","info":"一起啊","introduce":"一起间公司","progress":"正在招募中","investment":"2亿","skill_id":"1,3,4,","team_num":"44","start_time":"2016-05-12","school":"4"}
	private String addProjectInfo;
	public String addProject(){
		HttpServletRequest request = ServletActionContext.getRequest();
		JSONObject jsonObject = JSONObject.fromObject(addProjectInfo);
		
		String user_id = jsonObject.getString("user_id");
		String name = jsonObject.getString("name");
		String type_id_str = jsonObject.getString("type_id");
		int type_id = Integer.valueOf(type_id_str);
		String logo_url = jsonObject.getString("logo_url");
		String info = jsonObject.getString("info");
		String introduce = jsonObject.getString("introduce");
		String progress = jsonObject.getString("progress");
		String investment  = jsonObject.getString("investment");
		String skill_id = jsonObject.getString("skill_id");
		String team_num = jsonObject.getString("team_num");
		//String publish_time_str = jsonObject.getString("publish_time");
		String start_time_str = jsonObject.getString("start_time");
		String school = jsonObject.getString("school");
		Date start_time=Date.valueOf(start_time_str);
		
		    Calendar c = Calendar.getInstance();
		    int y = c.get(1);
		    int m = c.get(2)+1;
		    int d = c.get(5);
		    String date = y + "-" + m + "-" + d;
		Date publish_time = Date.valueOf(date);
		
		Project project=new Project(user_id,name,type_id,logo_url,info,introduce,progress,investment,skill_id,team_num,start_time,publish_time,school);
		ProjectService projectService = new ProjectService();
		    int result = projectService.addProject(project);
		    if (result == 0)
		    {
		    	JSONObject jsonResultAll = new JSONObject();
			    JSONObject jsonResult = new JSONObject();
			    jsonResult.put("state", "ERROR402");
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
		return "addProject";
	}
	
	//删除我的项目
	//http://localhost:8080/Startup_0.1_Alpha/my_project_delete?deleteInfo={"user_uuid":"ox5YLsyneSKdJgXNsxJfEFPXLhYU","project_id":"5"}
	private String deleteInfo;
	public String deleteMyProject(){
		HttpServletRequest request = ServletActionContext.getRequest();
		JSONObject jsonObject = JSONObject.fromObject(deleteInfo);
		
		String user_uuid = jsonObject.getString("user_uuid");
		String project_id_str = jsonObject.getString("project_id");
		int project_id=Integer.valueOf(project_id_str);
		int type=1;
		int type2=2;
		
		ProjectService projectservice=new ProjectService();
		int result=projectservice.deleteMyProject(user_uuid,project_id);
		//删除_number表里关注的项目
		int deleteNumFocus=projectservice.deleNumAll(project_id, type);
		int deleteNumGood=projectservice.deleNumAll(project_id, type2);
		
		if (result == 0)
		    {
		    	JSONObject jsonResultAll = new JSONObject();
			    JSONObject jsonResult = new JSONObject();
			    jsonResult.put("state", "ERROR403");
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
		
		return "deleteMyProject";
	}
	//添加我的项目
	//http://localhost:8080/Startup_0.1_Alpha/my_project_add?addProjectInfo={"user_id":"ox5YLsyneSKdJgXNsxJfEFPXLhYU","name":"建个硬件公司","type_id":"2","logo_url":"http://asdasdas","info":"一起啊","introduce":"一起间公司","progress":"正在招募中","investment":"2亿","skill_id":"1,3,4,","team_num":"44","start_time":"2016-05-12","school":"4"}
	//修改/编辑我的项目
	//http://localhost:8080/Startup_0.1_Alpha/my_project_edit?editInfo={"pro_id":"13","name":"建个硬件公司3333","type_id":"2","logo_url":"http://asdasdas","info":"一起啊","introduce":"一起间公司","progress":"正在招募中","investment":"2亿","skill_id":"1,3,4,","team_num":"44","start_time":"2016-05-12","school":"4"}
	private String editInfo;
	public String editMyProject(){
		HttpServletRequest request = ServletActionContext.getRequest();
		JSONObject jsonObject = JSONObject.fromObject(editInfo);
		
		String pro_id_str = jsonObject.getString("pro_id");
		
		int pro_id=Integer.valueOf(pro_id_str);
		//String user_id = jsonObject.getString("user_id");
		String name = jsonObject.getString("name");
		String type_id_str = jsonObject.getString("type_id");
		int type_id = Integer.valueOf(type_id_str);
		String logo_url = jsonObject.getString("logo_url");
		String info = jsonObject.getString("info");
		String introduce = jsonObject.getString("introduce");
		String progress = jsonObject.getString("progress");
		String investment  = jsonObject.getString("investment");
		String skill_id = jsonObject.getString("skill_id");
		String team_num = jsonObject.getString("team_num");
		//String publish_time_str = jsonObject.getString("publish_time");
		String start_time_str = jsonObject.getString("start_time");
		Date start_time=Date.valueOf(start_time_str);
		String school = jsonObject.getString("school");
		int status=0;
		/*
		    Calendar c = Calendar.getInstance();
		    int y = c.get(1);
		    int m = c.get(2)+1;
		    int d = c.get(5);
		    String date = y + "-" + m + "-" + d;
		Date publish_time = Date.valueOf(date);*/
		
		Project project=new Project(pro_id,name,type_id,logo_url,info,introduce,progress,investment,skill_id,team_num,start_time,school,status);
		
		ProjectService projectService = new ProjectService();
		int result = projectService.editProject(project);
		//System.out.println("result=" + result);
		    if (result == 0)
		    {
		       JSONObject jsonResultAll = new JSONObject();
		       JSONObject jsonResult = new JSONObject();
		       jsonResult.put("state", "ERROR405");
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
		    
		return "editMyProject";
	}
	 //关注项目
	//http://localhost:8080/Startup_0.1_Alpha/project_focus?focusProject={"user_id":"ox5YLsyneSKdJgXNsxJfEFPXLhYU","project_id":"1"}
		private String focusProject;
		public String projectFocus(){
			HttpServletRequest request = ServletActionContext.getRequest();
			JSONObject jsonObject = JSONObject.fromObject(focusProject);
			
			String user_id  = jsonObject.getString("user_id");
			String project_id  = jsonObject.getString("project_id");
			
			UserService userService=new UserService();
			
			//先把项目ID查出来
			User user=userService.myProFocusInfo(user_id);
			String focus =user.getFocus();
			String focusAll=focus+project_id+",";
			
			int proFocusResult=userService.proFocus(user_id,focusAll);//关注项目Id插入
			int type=1;//1是关注；2是点赞
			if(proFocusResult==1){
				   //关注哪个项目，项目表中的attention_num加1
				   ProjectService projectService=new ProjectService();
				   int proFocusNumAdd=projectService.proFocusNumAdd(project_id);
				   
				  // int result=projectService.checkByUserAndPro(user_id,project_id,type);
				   
				   int nummberAdd=projectService.addNumber(user_id,project_id,type);
				   
				   JSONObject jsonResultAll = new JSONObject();
			       JSONObject jsonResult = new JSONObject();
			       jsonResult.put("state", "success");
			       jsonResultAll.put("data", jsonResult);

			       String jsonStr = jsonResultAll.toString();
			       request.setAttribute("json", jsonStr);
				
				return "success";
			}else{
				  JSONObject jsonResultAll = new JSONObject();
			       JSONObject jsonResult = new JSONObject();
			       jsonResult.put("state", "failed");
			       jsonResultAll.put("data", jsonResult);

			       String jsonStr = jsonResultAll.toString();
			       request.setAttribute("json", jsonStr);
				
				return "failed";
			}
			
		} 
	
	//我关注的项目信息查询
	 //http://localhost:8080/Startup_0.1_Alpha/project_focus_info?focusInfo={"user_id":"0b7afda7-2e46-4a5e-adf3-37e5aa8e2509"}
	private String focusInfo;
	@SuppressWarnings("rawtypes")
	public String projectFocusInfo(){
		HttpServletRequest request = ServletActionContext.getRequest();
		JSONObject jsonObject = JSONObject.fromObject(focusInfo);
		
		String user_id  = jsonObject.getString("user_id");
		//String project_id  = jsonObject.getString("project_id");
		
		UserService userService=new UserService();
		User user=userService.myProFocusInfo(user_id);
		
		@SuppressWarnings("unchecked")
		
		List<Map<String,String>> list=new ArrayList<Map<String,String>>();
		
		/*String focusAll =user.getFocus();//获取到User的关注项目的ID
		String[] str = focusAll.split(",");
		for(int i=0;i<str.length;i++){
			//System.out.println("i="+i);
			String focus=str[i];
			System.out.println("focus="+focus);
			//根据focus=1,2,3,  查找指定的项目，返回给前端相关项目信息
			ProjectService projectService=new ProjectService();
			//int id,String name, String type_name, String log_url, String info
			Project projectNot=new Project(0,"该项目已被删除","该项目已被删除","www.null","该项目已被删除");
			Project project=projectService.findProjectsIdByFocusId(focus);
			System.out.println("project="+project);
			if(project!=null){
				//System.out.println("不为空！");
				projectList.add(project);
			}else{
				//System.out.println("为空！");
				projectList.add(projectNot);
			}
		} */
		ProjectService projectService=new ProjectService();
		//Project project=projectService.findProjectsByNum(user_id);
		List<Project> projectList=projectService.findProjectsByNum(user_id);
		//projectList.add(project);
		 String token=user.getToken();
		 for(Project p:projectList){//将 : 后面集合中的元素一一取出赋给 : 左边的变量 然后执行循环体
				int p_id=(int)p.getId();
				//System.out.println("===id="+p_id);
				String name=p.getName();
				String info=p.getInfo();
				String logo_url=p.getLog_url();
				String type_name=p.getType_name();
				
				Map<String,String> map=new HashMap<String,String>();
				map.put("id", String.valueOf(p_id));
				map.put("name",name);
				map.put("info",info);
				map.put("logo_url",logo_url);
				map.put("type_name",type_name);
				map.put("user_id",user_id);
				map.put("token",token);
				
				list.add(map);
			 
		}
		     JSONObject jsonData=new JSONObject();
	         JSONArray outArray=JSONArray.fromObject(list);
		
		
		JSONObject jsonFocus = new JSONObject();
		jsonFocus.put("projectInfo", outArray);
		jsonData.put("data", jsonFocus);
	    String jsonStr = jsonData.toString();
	    request.setAttribute("json", jsonStr);
	    
		return "projectFocusInfo";
	}
	
	 	//删除关注的项目
		//http://localhost:8080/Startup_0.1_Alpha/project_focus_delete?focusProjectDelete={"user_id":"ox5YLsyneSKdJgXNsxJfEFPXLhYU","project_id":"3"}
			private String focusProjectDelete;
			public String projectFocusDelete(){
				HttpServletRequest request = ServletActionContext.getRequest();
				JSONObject jsonObject = JSONObject.fromObject(focusProjectDelete);
				
				String user_id  = jsonObject.getString("user_id");
				String project_id  = jsonObject.getString("project_id");
				int type=1;
				
				UserService userService=new UserService();
				
				//先把项目ID查出来
				User user=userService.myProFocusInfo(user_id);
				String focus =user.getFocus();
				
				String focusDele=focus.replaceAll(project_id+",", "");
				
				int proFocusResult=userService.proFocus(user_id,focusDele);//关注项目Id插入
				if(proFocusResult==1){
					   //关注哪个项目，项目表中的attention_num加1
					   ProjectService projectService=new ProjectService();
					   int proFocusNumdDele=projectService.proFocusNumdDele(project_id);
					   int result=projectService.deleNumber(user_id, project_id,type);
					   JSONObject jsonResultAll = new JSONObject();
				       JSONObject jsonResult = new JSONObject();
				       jsonResult.put("state", "success");
				       jsonResultAll.put("data", jsonResult);

				       String jsonStr = jsonResultAll.toString();
				       request.setAttribute("json", jsonStr);
					
					return "success";
				}else{
					   JSONObject jsonResultAll = new JSONObject();
				       JSONObject jsonResult = new JSONObject();
				       jsonResult.put("state", "ERROR408");
				       jsonResultAll.put("data", jsonResult);

				       String jsonStr = jsonResultAll.toString();
				       request.setAttribute("json", jsonStr);
					
					return "failed";
				}
			} 
	//查询学校
    //http://localhost:8080/Startup_0.1_Alpha/school_list?userId={"user_id":"0b7afda7-2e46-4a5e-adf3-37e5aa8e2509"}
	private String userId; 
	public String schoolList(){
		HttpServletRequest request = ServletActionContext.getRequest();
		JSONObject jsonObject = JSONObject.fromObject(userId);
		
		String user_id  = jsonObject.getString("user_id");
		UserService userService=new UserService();
		User user=userService.myProFocusInfo(user_id);
		String school =user.getSchool();
		
		if(school == null || school.length() <= 0){
			JSONObject jsonResultAll = new JSONObject();
		    JSONObject jsonResult = new JSONObject();
		    jsonResult.put("school", "");
		    jsonResult.put("state", "success");
		    jsonResultAll.put("data", jsonResult);

		    String jsonStr = jsonResultAll.toString();
		    request.setAttribute("json", jsonStr);
			return "schoolList";
		}
		
		JSONObject jsonResultAll = new JSONObject();
	    JSONObject jsonResult = new JSONObject();
	    jsonResult.put("school", school);
	    jsonResult.put("state", "success");
	    jsonResultAll.put("data", jsonResult);

	    String jsonStr = jsonResultAll.toString();
	    request.setAttribute("json", jsonStr);
		return "schoolList";
	}
		
	//查询技能
    //http://localhost:8080/Startup_0.1_Alpha/skill_list?userInfo={"user_id":"7f31e12c-a907-41ae-9c6c-d2accded8e7c"}
	private String userInfo; 
	public String skillList(){
		HttpServletRequest request = ServletActionContext.getRequest();
		JSONObject jsonObject = JSONObject.fromObject(userInfo);
		
		String user_id  = jsonObject.getString("user_id");
		UserService userService=new UserService();
		User user=userService.myProFocusInfo(user_id);
		String skill_id =user.getSkill_id();
		if(skill_id == null || skill_id.length() <= 0)
		{
			JSONObject jsonResultAll = new JSONObject();
		    JSONObject jsonResult = new JSONObject();
		    jsonResult.put("skill_id","");
		    jsonResult.put("state", "success");
		    jsonResultAll.put("data", jsonResult);
		    
		    String jsonStr = jsonResultAll.toString();
		    request.setAttribute("json", jsonStr);
			return "skillList";
		}else{
		JSONObject jsonResultAll = new JSONObject();
	    JSONObject jsonResult = new JSONObject();
	    jsonResult.put("skill_id", skill_id);
	    jsonResult.put("state", "success");
	    jsonResultAll.put("data", jsonResult);

	    String jsonStr = jsonResultAll.toString();
	    request.setAttribute("json", jsonStr);
		return "skillList";}
	}
	//修改学校
	 //http://localhost:8080/Startup_0.1_Alpha/school_change?schoolInfo={"user_id":"ox5YLsyneSKdJgXNsxJfEFPXLhYU","school":"2"}
	private String schoolInfo;
	public String schoolChange(){
		HttpServletRequest request = ServletActionContext.getRequest();
		JSONObject jsonObject = JSONObject.fromObject(schoolInfo);
		
		String user_id  = jsonObject.getString("user_id");
		String school  = jsonObject.getString("school");
		UserService userService=new UserService();
		
		int resultChange=userService.schoolChange(user_id,school);
		
		if(resultChange==1){
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
			jsonResult.put("state", "ERROR405");
			jsonResultAll.put("data", jsonResult);

			String jsonStr = jsonResultAll.toString();
			request.setAttribute("json", jsonStr);
		
		return "failed";
	}
	//修改技能
	//http://localhost:8080/Startup_0.1_Alpha/skill_change?skillInfo={"user_id":"ox5YLsyneSKdJgXNsxJfEFPXLhYU","skill_id":"1,2,3,4,"}
		private String skillInfo;
		public String skillChange(){
			HttpServletRequest request = ServletActionContext.getRequest();
			JSONObject jsonObject = JSONObject.fromObject(skillInfo);
			
			String user_id  = jsonObject.getString("user_id");
			String skill_id  = jsonObject.getString("skill_id");
			UserService userService=new UserService();
			
			int resultChange=userService.skillChange(user_id,skill_id);
			
			if(resultChange==1){
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
				jsonResult.put("state", "ERROR405");
				jsonResultAll.put("data", jsonResult);

				String jsonStr = jsonResultAll.toString();
				request.setAttribute("json", jsonStr);
			
			return "failed";
		}
		
	//消息推送查询
	//http://localhost:8080/Startup_0.1_Alpha/push_list?pushInfo={"user_id":"ox5YLsyneSKdJgXNsxJfEFPXLhYU"}
	private String pushInfo;
	public String pushList(){
		HttpServletRequest request = ServletActionContext.getRequest();
		JSONObject jsonObject = JSONObject.fromObject(pushInfo);
		
		String user_id  = jsonObject.getString("user_id");
		UserService userService=new UserService();
		User user=userService.myProFocusInfo(user_id);
		int info_get_int =user.getInfo_get();
		String info_get=String.valueOf(info_get_int);
		
		JSONObject jsonResultAll = new JSONObject();
	    JSONObject jsonResult = new JSONObject();
	    jsonResult.put("push", info_get);
	    jsonResult.put("state", "success");
	    jsonResultAll.put("data", jsonResult);

	    String jsonStr = jsonResultAll.toString();
	    request.setAttribute("json", jsonStr);
		return "pushList";
		
	}
		
		//修改消息推送
		 //http://localhost:8080/Startup_0.1_Alpha/push_change?pushChangeInfo={"user_id":"ox5YLsyneSKdJgXNsxJfEFPXLhYU","push":"0"}
		private String pushChangeInfo;
		public String pushChange(){
			HttpServletRequest request = ServletActionContext.getRequest();
			JSONObject jsonObject = JSONObject.fromObject(pushChangeInfo);
			
			String user_id  = jsonObject.getString("user_id");
			String push  = jsonObject.getString("push");
			UserService userService=new UserService();
			
			int resultChange=userService.pushChange(user_id,push);
			
			if(resultChange==1){
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
				jsonResult.put("state", "ERROR405");
				jsonResultAll.put("data", jsonResult);

				String jsonStr = jsonResultAll.toString();
				request.setAttribute("json", jsonStr);
			
			return "failed";
		}
		//<!-- 验证+修改密码 -->
		 //http://localhost:8080/Startup_0.1_Alpha/modifyPwd?modifyPwd={"user_id":"ox5YLs4OO7e2otU7rLv0ojLsWjEM2","pwd":"123123123","newPwd":"123123123"}
		private String modifyPwd;
		public String modifyPwd() {
		    HttpServletRequest request = ServletActionContext.getRequest();

		    JSONObject jsonObject = JSONObject.fromObject(modifyPwd);

		    String user_id=jsonObject.getString("user_id");
		    String pwd=jsonObject.getString("pwd");
		    String newPwd=jsonObject.getString("newPwd");

		    UserService userService = new UserService();

		    int checkPAndP =userService.checkPhoneNumAndPwd(user_id, pwd);
		    //System.out.println("checkPAndP=" + checkPAndP);
		    if (checkPAndP == 0)
		    {//密码不匹配
		    	
		      System.out.println("密码不匹配！");
		      JSONObject jsonResultData = new JSONObject();
		      JSONObject jsonResult = new JSONObject();
		      jsonResult.put("state", "ERROR405");
		      jsonResultData.put("data", jsonResult);
		      String jsonStr = jsonResultData.toString();
		      request.setAttribute("json", jsonStr);
		    }
		    else 
		    {//密码匹配,执行修改操作
		      //System.out.println("密码已匹配！");
		      int a = userService.changePwd(user_id, newPwd);
		      //System.out.println("a="+a);
		      if(a==1){
		      //System.out.println("密码修改成功！ a="+a);
		      JSONObject jsonResultData = new JSONObject();
		      JSONObject jsonResult = new JSONObject();
		      jsonResult.put("state", "success");
		      jsonResultData.put("data", jsonResult);
		      String jsonStr = jsonResultData.toString();
		      request.setAttribute("json", jsonStr);
		      }else{
		    	  JSONObject jsonResultData = new JSONObject();
			      JSONObject jsonResult = new JSONObject();
			      jsonResult.put("state", "ERROR415");
			      jsonResultData.put("data", jsonResult);
			      String jsonStr = jsonResultData.toString();
			      request.setAttribute("json", jsonStr);
		      }
		    }
		    return "modifyPwd";
		  }
	 //退出登陆
	 //http://localhost:8080/Startup_0.1_Alpha/exit?exitInfo={"user_id":"ox5YLsyneSKdJgXNsxJfEFPXLhYU","online":"0"}
		private String exitInfo;
		public String exit(){
			HttpServletRequest request = ServletActionContext.getRequest();
			JSONObject jsonObject = JSONObject.fromObject(exitInfo);
			
			String user_id  = jsonObject.getString("user_id");
			String online  = jsonObject.getString("online");
			UserService userService=new UserService();
			
			int resultChange=userService.onlineChange(user_id,online);
			
			if(resultChange==1){
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
				jsonResult.put("state", "ERROR405");
				jsonResultAll.put("data", jsonResult);

				String jsonStr = jsonResultAll.toString();
				request.setAttribute("json", jsonStr);
			
			return "failed";
		}
		//绑定第三方
		//http://localhost:8080/Startup_0.1_Alpha/third_bind?bindInfo={"third_id":"99999299","third_name":"QQ","phone_num":"18345098136"}
		private String bindInfo;
		public String thirdBind(){
			HttpServletRequest request = ServletActionContext.getRequest();
			JSONObject jsonObject = JSONObject.fromObject(bindInfo);
			
			String third_id  = jsonObject.getString("third_id");
			String third_name  = jsonObject.getString("third_name");
			String phone_num  = jsonObject.getString("phone_num");
			
			if(third_name.equals("QQ")){
				 third_name="qq_id";
			    }else if(third_name.equals("Wechat")){
			    	third_name="weixin_id";
			    }else if(third_name.equals("SinaWeibo")){
			    	third_name="xinlang_id";
			    }
			
			//获取用户的uuid
			UserService userService=new UserService();
			User user=userService.myProFocusInfoByPhone(phone_num);
			String user_uuid =user.getUuid();
			System.out.println("user_uuid"+user_uuid);
			
			ThirdLoginService thirdLoginService=new ThirdLoginService();
			
			int a=thirdLoginService.findByUserUuid(user_uuid);
			System.out.println("a===="+a);
			
			if(a==0){
			int bindResult=thirdLoginService.thirdBind(user_uuid,third_name,third_id);
			System.out.println("insert一下!result="+bindResult);
			if(bindResult==0){
				  JSONObject jsonResultData = new JSONObject();
			      JSONObject jsonResult = new JSONObject();
			      jsonResult.put("state", "ERROR104");
			      jsonResultData.put("data", jsonResult);
			      String jsonStr = jsonResultData.toString();
			      request.setAttribute("json", jsonStr);
			      return "failed";
			}
			 JSONObject jsonResultData = new JSONObject();
		      JSONObject jsonResult = new JSONObject();
		      jsonResult.put("state", "bindSuccess");
		      jsonResultData.put("data", jsonResult);
		      String jsonStr = jsonResultData.toString();
		      request.setAttribute("json", jsonStr);
		      
		      return "success";
			}else{
				System.out.println("--------------"+third_name);
				int bindResult=thirdLoginService.updateInfo(user_uuid,third_name,third_id);
				System.out.println("update一下!result="+bindResult);
				if(bindResult==0){
					  JSONObject jsonResultData = new JSONObject();
				      JSONObject jsonResult = new JSONObject();
				      jsonResult.put("state", "ERROR104");
				      jsonResultData.put("data", jsonResult);
				      String jsonStr = jsonResultData.toString();
				      request.setAttribute("json", jsonStr);
				      return "failed";
				}
				 JSONObject jsonResultData = new JSONObject();
			       JSONObject jsonResult = new JSONObject();
			      jsonResult.put("state", "bindSuccess");
			      jsonResultData.put("data", jsonResult);
			      String jsonStr = jsonResultData.toString();
			      request.setAttribute("json", jsonStr);
			      
			      return "success";
			}
		}
	//第三方登陆
	//http://localhost:8080/Startup_0.1_Alpha/third_login?thirdInfo={"third_id":"thirdId32123","third_name":"QQ"}
	private String thirdInfo;
	public String thirdLogin(){
		HttpServletRequest request = ServletActionContext.getRequest();
		JSONObject jsonObject = JSONObject.fromObject(thirdInfo);
		
		String third_name  = jsonObject.getString("third_name");
		String third_id  = jsonObject.getString("third_id");
		
		  if(third_name.equals("QQ")){
			  third_name="qq_id";
		    }else if(third_name.equals("Wechat")){
		    	third_name="weixin_id";
		    }else if(third_name.equals("SinaWeibo")){
		    	third_name="xinlang_id";
		    }
		
		ThirdLoginService thirdLoginService=new ThirdLoginService();
		int loginResult=thirdLoginService.thirdLogin(third_name,third_id);
		
		if(loginResult==0){
			  JSONObject jsonResultData = new JSONObject();
		      JSONObject jsonResult = new JSONObject();
		      jsonResult.put("state", "ERROR103");
		      jsonResultData.put("data", jsonResult);
		      String jsonStr = jsonResultData.toString();
		      request.setAttribute("json", jsonStr);
		      return "failed";
		}
			 //根据第三方账号找用户的uuid
			  ThirdLogin thirdLogin=thirdLoginService.findUserUuid(third_name,third_id);
			  String user_id=thirdLogin.getUuid();
			  System.out.println("user_id"+user_id);
			  
			  UserService userService=new UserService();
			  
			  //获取用户手机号phone_num
				User user=userService.myProFocusInfo(user_id);
				String phone_num =user.getPhone_num();
			  
			  //获取用户信息
			  List<User> userList=userService.findProjectIds(phone_num);
		      List<Map<String,String>> list=new ArrayList<Map<String,String>>();
		    	
		    	for(User u:userList){
		    	String user_id2=u.getUuid();
		    	String img_url=u.getImg_url();
		    	String token=u.getToken();
		    	String sex=String.valueOf(u.getSex());
		    	String name=u.getName();
				 
		    	Map<String,String> map=new HashMap<String,String>();
		    	
		    	map.put("user_id",user_id2);
		    	map.put("img_url",img_url);
		    	map.put("token",token);
		    	map.put("sex",sex);
		    	map.put("phone_num",phone_num);
		    	map.put("name",name);
		    	
		    	list.add(map);
		    	}
		    	JSONArray outArray=JSONArray.fromObject(list);	
		     
				JSONObject jsonInfo=new JSONObject();
			    //String success="success";
		       // jsonInfo.put("state", success);
		        for (int i = 0; i < outArray.size(); i++) {
		        	JSONObject jo = (JSONObject) outArray.get(0);
		        	jsonInfo.put("userInfo", jo);
		        }
		        
			  JSONObject jsonResultData = new JSONObject();
			//  JSONObject jsonResult = new JSONObject();
			  jsonInfo.put("state", "success");
	          //jsonResult.put("userInfo", jsonInfo);
	          jsonResultData.put("data", jsonInfo);
	          String jsonStr = jsonResultData.toString();
	          request.setAttribute("json", jsonStr);
		
		return "success";
	}
	//解除第三方账号
	//http://localhost:8080/Startup_0.1_Alpha/cancel_bind?cancelInfo={"user_id":"ox5YLs4OO7e2otU7rLv0ojLsWjEM2","third_name":"QQ"}
	private String cancelInfo;
	public String cancelBind(){
		HttpServletRequest request = ServletActionContext.getRequest();
		JSONObject jsonObject = JSONObject.fromObject(cancelInfo);
		
		String user_id  = jsonObject.getString("user_id");
		String third_name  = jsonObject.getString("third_name");
		
		  if(third_name.equals("QQ")){
			  third_name="qq_id";
		    }else if(third_name.equals("Wechat")){
		    	third_name="weixin_id";
		    }else if(third_name.equals("SinaWeibo")){
		    	third_name="xinlang_id";
		    }		
		
		UserService userService=new UserService();
		int cancelResult=userService.updateThirdLogin(user_id,third_name);
		
		if (cancelResult == 0)
	    {
	    	JSONObject jsonResultAll = new JSONObject();
		    JSONObject jsonResult = new JSONObject();
		    jsonResult.put("state", "ERROR106");
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
		
		return "success";
	}
	//重置密码
	//http://localhost:8080/Startup_0.1_Alpha/reset_password?pwdInfo={"phone_num":"1111","pwd":"123123"}
	private String pwdInfo;
	public String resetPassword(){
		HttpServletRequest request = ServletActionContext.getRequest();
		JSONObject jsonObject = JSONObject.fromObject(pwdInfo);
		
		String phone_num  = jsonObject.getString("phone_num");
		String pwd  = jsonObject.getString("pwd");
		UserService userService=new UserService();
		int resetResult=userService.resetPwd(phone_num,pwd);
		if (resetResult == 0)
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
		
		return "success";
		
	}
	//修改个人信息
	//http://localhost:8080/Startup_0.1_Alpha/modify_info?modifyInfo={"uuid":"ox5YLs4OO7e2otU7rLv0ojLsWjEM2","name":"磊锅2","img_url":"aaaaaaaa","sex":"1"}
	private String modifyInfo; 
	public String modifyInfo() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		JSONObject jsonObject = JSONObject.fromObject(modifyInfo);
		
		String user_id  = jsonObject.getString("uuid");
		String name   = jsonObject.getString("name");
		String img_url   = jsonObject.getString("img_url");
		String sex    = jsonObject.getString("sex");
		
		UserService userService=new UserService();
		int resetResult=userService.modifyInfo(user_id,name,img_url,sex);
		if (resetResult == 0)
	    {
	    	JSONObject jsonResultAll = new JSONObject();
		    JSONObject jsonResult = new JSONObject();
		    jsonResult.put("state", "ERROR405");
		    jsonResultAll.put("data", jsonResult);

		    String jsonStr = jsonResultAll.toString();
		    request.setAttribute("json", jsonStr);
		    return "failed";
	    }
		//返回并存储token
	  	String key = "6tnym1brnogs7";//替换成您的appkey
	  	String secret = "a9zjfcSeGFCsCI";//替换成匹配上面key的secret
	  	SdkHttpResult resultGet = null;

	    resultGet = ApiHttpClient.getToken(key, secret, user_id, name,
	    		img_url, FormatType.json);
	    
	  	JSONTools jsonTools=new JSONTools();
	  	@SuppressWarnings("static-access")
		String restlt1=jsonTools.getJsonValue("result",resultGet.toString());
	  	@SuppressWarnings("static-access")
		String token=jsonTools.getJsonValue("token",restlt1);
	    
	  	//System.out.println("token=" + resultGet);
	  	//将token更新到_user
		int tokenUpdateResult=userService.tokenUpdate(user_id,token);
		//System.out.println("token的更改状态="+tokenUpdateResult);
		
	    /*JSONObject jsonResultAll = new JSONObject();
	    JSONObject jsonResult = new JSONObject();
	    jsonResult.put("state", "success");
	    jsonResult.put("token", token);
	    jsonResultAll.put("data", jsonResult);

	    String jsonStr = jsonResultAll.toString();
	    request.setAttribute("json", jsonStr);*/
		  //取出 token  img_url
		List<User> userList=userService.findProjectIdsByUuid(user_id);
    	List<Map<String,String>> list=new ArrayList<Map<String,String>>();
    	
    	for(User u:userList){
    	String user_id2=u.getUuid();
    	String img_url2=u.getImg_url();
    	String token2=u.getToken();
    	String name2=u.getName();
    	String sex2=String.valueOf(u.getSex());
    	String phone_num=u.getPhone_num();
		 
    	Map<String,String> map=new HashMap<String,String>();
    	
    	map.put("user_id",user_id2);
    	map.put("img_url",img_url2);
    	map.put("token",token2);
    	map.put("name",name2);
    	map.put("sex",sex2);
    	map.put("phone_num",phone_num);
    	
    	list.add(map);
    	}
    	JSONArray outArray=JSONArray.fromObject(list);	
     
    	HttpSession session = request.getSession();
    	JSONObject jsonData=new JSONObject();
		JSONObject jsonInfo=new JSONObject();
	    String success="success";
        jsonInfo.put("state", success);
        for (int i = 0; i < outArray.size(); i++) {
        	JSONObject jo = (JSONObject) outArray.get(0);
        	jsonInfo.put("userInfo", jo);
        }
        
        jsonData.put("data", jsonInfo);
 	    String jsonStr = jsonData.toString();
 	    session.setAttribute("json", jsonStr);
		
		return "success";
	}
	//超阳测试接口
	//http://localhost:8080/Startup_0.1_Alpha/RIM?RIM={"user_uuid":"0b7afda7-2e46-4a5e-adf3-37e5aa8e2509"}
	private String RIM;
	public String RIM(){
		HttpServletRequest request = ServletActionContext.getRequest();
		JSONObject jsonObject = JSONObject.fromObject(RIM);
		String user_uuid  = jsonObject.getString("user_uuid");

		UserService userService=new UserService();
		List<User> userList=userService.findProjectIdsByUuid(user_uuid);
    	List<Map<String,String>> list=new ArrayList<Map<String,String>>();
    	
    	for(User u:userList){
    	String user_id2=u.getUuid();
    	String img_url2=u.getImg_url();
    	String name2=u.getName();
    	String token=u.getToken();
    	Map<String,String> map=new HashMap<String,String>();
    	map.put("id",user_id2);
    	map.put("name",name2);
    	map.put("portraitUri",img_url2);
    	map.put("token",token);
    	
    	list.add(map);
    	}
    	JSONArray outArray=JSONArray.fromObject(list);	
     
    	HttpSession session = request.getSession();
    	JSONObject jsonData=new JSONObject();
		JSONObject jsonInfo=new JSONObject();
        jsonInfo.put("state", "success");
        for (int i = 0; i < outArray.size(); i++) {
        	JSONObject jo = (JSONObject) outArray.get(0);
        	jsonInfo.put("userInfo", jo);
        }
        jsonData.put("data", jsonInfo);
 	    String jsonStr = jsonData.toString();
 	    session.setAttribute("json", jsonStr);
 	    
		return "RIM";
	}
	//get set
	public String getRegister() {
		return register;
	}

	public void setRegister(String register) {
		this.register = register;
	}

	public String getNum_check() {
		return num_check;
	}

	public void setNum_check(String num_check) {
		this.num_check = num_check;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getImgUpload() {
		return imgUpload;
	}
	public void setImgUpload(String imgUpload) {
		this.imgUpload = imgUpload;
	}
	public String getImgFileName() {
		return imgFileName;
	}
	public void setImgFileName(String imgFileName) {
		this.imgFileName = imgFileName;
	}
	public String getImgContentType() {
		return imgContentType;
	}
	public void setImgContentType(String imgContentType) {
		this.imgContentType = imgContentType;
	}
	public File getImg() {
		return img;
	}
	public void setImg(File img) {
		this.img = img;
	}
	public String getFileId() {
		return fileId;
	}
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
	public com.syf.util.FileUpload getFileUpload() {
		return fileUpload;
	}
	public void setFileUpload(com.syf.util.FileUpload fileUpload) {
		this.fileUpload = fileUpload;
	}

	public String getProjectInfo() {
		return projectInfo;
	}

	public void setProjectInfo(String projectInfo) {
		this.projectInfo = projectInfo;
	}
	public String getAddProjectInfo() {
		return addProjectInfo;
	}
	public void setAddProjectInfo(String addProjectInfo) {
		this.addProjectInfo = addProjectInfo;
	}

	public String getDeleteInfo() {
		return deleteInfo;
	}

	public void setDeleteInfo(String deleteInfo) {
		this.deleteInfo = deleteInfo;
	}

	public String getEditInfo() {
		return editInfo;
	}

	public void setEditInfo(String editInfo) {
		this.editInfo = editInfo;
	}
	public String getFocusProject() {
		return focusProject;
	}
	public void setFocusProject(String focusProject) {
		this.focusProject = focusProject;
	}
	public String getFocusInfo() {
		return focusInfo;
	}
	public void setFocusInfo(String focusInfo) {
		this.focusInfo = focusInfo;
	}


	public String getFocusProjectDelete() {
		return focusProjectDelete;
	}


	public void setFocusProjectDelete(String focusProjectDelete) {
		this.focusProjectDelete = focusProjectDelete;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(String userInfo) {
		this.userInfo = userInfo;
	}
	public String getSchoolInfo() {
		return schoolInfo;
	}
	public void setSchoolInfo(String schoolInfo) {
		this.schoolInfo = schoolInfo;
	}
	public String getSkillInfo() {
		return skillInfo;
	}
	public void setSkillInfo(String skillInfo) {
		this.skillInfo = skillInfo;
	}

	public String getPushInfo() {
		return pushInfo;
	}

	public void setPushInfo(String pushInfo) {
		this.pushInfo = pushInfo;
	}

	public String getPushChangeInfo() {
		return pushChangeInfo;
	}

	public void setPushChangeInfo(String pushChangeInfo) {
		this.pushChangeInfo = pushChangeInfo;
	}

	public String getModifyPwd() {
		return modifyPwd;
	}

	public void setModifyPwd(String modifyPwd) {
		this.modifyPwd = modifyPwd;
	}
	public String getExitInfo() {
		return exitInfo;
	}
	public void setExitInfo(String exitInfo) {
		this.exitInfo = exitInfo;
	}
	public String getBindInfo() {
		return bindInfo;
	}
	public void setBindInfo(String bindInfo) {
		this.bindInfo = bindInfo;
	}

	public String getThirdInfo() {
		return thirdInfo;
	}

	public void setThirdInfo(String thirdInfo) {
		this.thirdInfo = thirdInfo;
	}
	public String getTokeninfo() {
		return tokeninfo;
	}
	public void setTokeninfo(String tokeninfo) {
		this.tokeninfo = tokeninfo;
	}


	public String getCancelInfo() {
		return cancelInfo;
	}


	public void setCancelInfo(String cancelInfo) {
		this.cancelInfo = cancelInfo;
	}

	public String getPwdInfo() {
		return pwdInfo;
	}

	public void setPwdInfo(String pwdInfo) {
		this.pwdInfo = pwdInfo;
	}

	public String getModifyInfo() {
		return modifyInfo;
	}

	public void setModifyInfo(String modifyInfo) {
		this.modifyInfo = modifyInfo;
	}
	public String getRIM() {
		return RIM;
	}
	public void setRIM(String rIM) {
		RIM = rIM;
	}

}
