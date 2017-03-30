<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'project_list.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!-- 使用设备的宽度作为视图宽度并禁止初始的缩放-->
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
	<link rel="stylesheet" type="text/css" href="bootstrap/css/list.css">
	
	<!-- <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="bootstrap/js/jquery-2.2.3.min.js"></script>
    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css"> 
  </head>
  
  <body>
   	<div class="div">
  		<div class="top">
  				<table class="table1">
				<tr>
				<th class="th1">
				 <div class="top_left">
						 <img src="image/mark.png" class="img-responsive"> 
				 </div>
				 </th>
				<!-- <th class="th1">
					<a class="a_top" href="#">首页|</a><a class="a_top" href="#">APP|</a><a class="a_top" href="#">微信公众号|</a><a class="a_top" href="#">帮助中心|</a><a class="a_top" href="#">咨询电话:0000-0000000</a>
				 </th> -->
				 <th class="th1">
				 <div class="top_right">
				 	<a class="a_top" href="#">登陆|</a><a class="a_top" href="#">注册</a>
				 	</div>
				 </th>
				</tr>
				</table>
				</div>
  		
  		<div class="center">
  				<div class="center_top">
				<c:forEach items="${requestScope.projectList }" var="nn">  
					<a class="btn btn-default btn-md active" role="button" href="ProjectTypeServlet?op=toProInfo&id=${nn.id }">
					 <B>${nn.type_name}</B> 
					 </a>
     			</c:forEach> 
				 	<!-- <a href="#" class="btn btn-default btn-md active" role="button"><B>热门</B></a>
					 <a href="#" class="btn btn-warning btn-md active" role="button"><B>电子商务</B></a> -->
  					
  				<c:forEach items="${requestScope.projectInfo }" var="nn">  
  					 ${nn.name} 
  				</c:forEach> 
  				</div>
  				
  				<div class="center_bottom2">
  				 
  					<div class="center_bottom_left">
  					<div class="center_bottom_left1">
							 <img src="image/focuspro.jpg" class="img-responsive">  
									
  					</div>
  					<div class="center_bottom_left2">
							<img src="image/startpro.jpg" class="img-responsive"> 
  					</div>
  					</div>
  					<div class="center_bottom_right">
  					
  						<div class="center_bottom_right1">
  					  	  <div class="center_bottom_right1_left">
  							<div class="center_bottom_right1_logo">
  								<img src="image/logo.png" class="img-responsive">
							</div>
  							<div class="center_bottom_right1_focus">
  								<img src="image/focus.png" class="img-responsive">
  							</div>
						 </div>
						 
						  <div class="center_bottom_right1_center">
  								<img src="image/name.png" class="img-responsive">
						  </div>
						
						  <div class="center_bottom_right1_right">
  								<img src="image/name.png" class="img-responsive">
						  </div>
						 
  					  </div>
  					</div>
  				  </div>
  						
  					
  				</div>
  			<div class="bottom">
  				<div class="text-center">
  					<H6>
  					<a class="a_top" href="#">首页|</a><a class="a_top" href="#">APP|</a><a class="a_top" href="#">微信公众号|</a><a class="a_top" href="#">帮助中心|</a><a class="a_top" href="#">咨询电话:0000-0000000</a>
  					</H6>	
  				</div>
  			</div>
  		</div>
  
  </body>
</html>
