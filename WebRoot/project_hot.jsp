<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'project_hot.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	 <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="this is my page">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    
    <!--<link rel="stylesheet" type="text/css" href="./styles.css">-->
	<!-- 使用设备的宽度作为视图宽度并禁止初始的缩放-->
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
	<link rel="stylesheet" type="text/css" href="bootstrap/css/hot.css">
	
	<!-- <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="bootstrap/js/jquery-2.2.3.min.js"></script>
    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css"> 
    
    
    
    
    
    
    
<!-- 可无视 -->
<link href="test/css/styles.css" rel="stylesheet">

<!-- Load jQuery -->
<script type="text/javascript" src="test/js/jquery.min.js"></script>

<!-- 必要样式 -->
<link href="test/css/mislider.css" rel="stylesheet">
<link href="test/css/mislider-skin-cameo.css" rel="stylesheet">

<script type="text/javascript" src="test/js/mislider.js"></script>
<script type="text/javascript">
jQuery(function ($) {
	var slider = $('.mis-stage').miSlider({
		//  The height of the stage in px. Options: false or positive integer. false = height is calculated using maximum slide heights. Default: false
		stageHeight: 380,
		//  Number of slides visible at one time. Options: false or positive integer. false = Fit as many as possible.  Default: 1
		slidesOnStage: false,
		//  The location of the current slide on the stage. Options: 'left', 'right', 'center'. Defualt: 'left'
		slidePosition: 'center',
		//  The slide to start on. Options: 'beg', 'mid', 'end' or slide number starting at 1 - '1','2','3', etc. Defualt: 'beg'
		slideStart: 'mid',
		//  The relative percentage scaling factor of the current slide - other slides are scaled down. Options: positive number 100 or higher. 100 = No scaling. Defualt: 100
		slideScaling: 150,
		//  The vertical offset of the slide center as a percentage of slide height. Options:  positive or negative number. Neg value = up. Pos value = down. 0 = No offset. Default: 0
		offsetV: -5,
		//  Center slide contents vertically - Boolean. Default: false
		centerV: true,
		//  Opacity of the prev and next button navigation when not transitioning. Options: Number between 0 and 1. 0 (transparent) - 1 (opaque). Default: .5
		navButtonsOpacity: 1
	});
});
</script>
    
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
					<a href="#" class="btn btn-warning btn-md active" role="button"><B>热门</B></a>
					<a href="#" class="btn btn-default btn-md active" role="button"><B>电子商务</B></a>
					<a href="#" class="btn btn-default btn-md active" role="button"><B>体育</B></a>
					<a href="#" class="btn btn-default btn-md active" role="button"><B>互联网</B></a>
					<a href="#" class="btn btn-default btn-md active" role="button"><B>金融</B></a>
					<a href="#" class="btn btn-default btn-md active" role="button"><B>技术</B></a>
					<a href="#" class="btn btn-default btn-md active" role="button"><B>自媒体</B></a>
					<a href="#" class="btn btn-default btn-md active" role="button"><B>营销</B></a>
					<a href="#" class="btn btn-default btn-md active" role="button"><B>医疗</B></a>
					<a href="#" class="btn btn-default btn-md active" role="button"><B>其他</B></a>
  					
  				</div>
  				<div class="center_bottom">
  				
  			
  			
  				
  				
  				
	<div class="mis-stage">
		<!-- The element to select and apply miSlider to - the class is optional -->
		<ol class="mis-slider">
			<!-- The slider element - the class is optional -->
			<li class="mis-slide">
				<!-- A slide element - the class is optional -->
				<a href="#" class="mis-container">
					<!-- A slide container - this element is optional, if absent the plugin adds it automatically -->
					<figure>
						<!-- Slide content - whatever you want -->
						<img src="image/hot_img.jpg" alt="Pink Water Lillies">
						<figcaption>Pink Water Lillies</figcaption>
					</figure>
				</a>
			</li>
			<li class="mis-slide">
				<a href="#" class="mis-container">
					<figure>
						<img src="image/hot_img.jpg" alt="Pond with Lillies">
						<figcaption>Pond with Lillies</figcaption>
					</figure>
				</a>
			</li>
			<li class="mis-slide">
				<a href="#" class="mis-container">
					<figure>
						<img src="image/hot_img.jpg" alt="Hidden Pond">
						<figcaption>Hidden Pond</figcaption>
					</figure>
				</a>
			</li>
			<li class="mis-slide">
				<a href="#" class="mis-container">
					<figure>
						<img src="image/hot_img.jpg" alt="Shrine">
						<figcaption>Shrine</figcaption>
					</figure>
				</a>
			</li>
			<li class="mis-slide">
				<a href="#" class="mis-container">
					<figure>
						<img src="image/hot_img.jpg" alt="White Water Lillies">
						<figcaption>White Water Lillies</figcaption>
					</figure>
				</a>
			</li>
			<li class="mis-slide">
				<a href="#" class="mis-container">
					<figure>
						<img src="image/hot_img.jpg" alt="Garden Walkway">
						<figcaption>Garden Walkway</figcaption>
					</figure>
				</a>
			</li>
			<li class="mis-slide">
				<a href="#" class="mis-container">
					<figure>
						<img src="image/hot_img.jpg" alt="Lilly with Bee">
						<figcaption>Lilly with Bee</figcaption>
					</figure>
				</a>
			</li>
		</ol>
	</div>

  					</div>
  				</div>
  				
  			<div class="bottom">
  				<div class="text-center">
  			<H4>
  			<a class="a_top" href="#">首页|</a><a class="a_top" href="#">APP|</a><a class="a_top" href="#">微信公众号|</a><a class="a_top" href="#">帮助中心|</a><a class="a_top" href="#">咨询电话:0000-0000000</a>
  			</H4>	
  				</div>
  			</div>
  		</div>
  </body>
</html>
