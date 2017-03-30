<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'FirstWeb.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<!-- 使用设备的宽度作为视图宽度并禁止初始的缩放-->
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
	<link rel="stylesheet" type="text/css" href="bootstrap/css/webstyle.css">
	
	  <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
      <script src="bootstrap/js/jquery-2.2.3.min.js"></script>
      <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">  
</head>
<body>
 <div class="container-fluid">
	  <div class="div">
		<div class="div_center">
			<div class="div_center_top">
			
				<table>
				<tr>
				<th>
				 <div class="div_center_top_bgcolor">
						 <img src="image/mark.png" class="img-responsive"> 
				 </div>
				 </th>
				 <th>
				 <div class="div_center_top_bgcolor2">
						<a href="#"><img src="image/goweb.png" class="img-responsive"></a>
				 </div>
				 </th>
				</tr>
				</table>
			</div>
			<div class="div_center_bottom">
				    <div class="div_center_bottom_image">
						<img src="image/img1.png" class="img-responsive">
				    </div>
			</div>
		</div> 
		
		<div class="div_bottom">
			<div class="div_bottom_top">
			<div class="div_bottom_top_in">
				<div class="row">
  					  <div class="col-md-4 "><a href="#"><img src="image/work1.png" class="img-responsive"></a></div>
 					  <div class="col-md-4 "><a href="#"><img src="image/work2.png" class="img-responsive"></a></div>
					  <div class="col-md-4"><a href="#"><img src="image/work3.png" class="img-responsive"></a></div>
				</div>
			</div>
		</div> 
		
		<div class="div_bottom_bottom">
		<div class="text-center">
		<h4>联系电话：15663688897 &nbsp &nbsp 联系邮箱：sobermen@163.com &nbsp@创业帮  &nbsp哈 &nbspICP &nbsp备112310</h4>
		</div>
		</div>
	</div>  
	</div>
	</div>
</body>
</html>
