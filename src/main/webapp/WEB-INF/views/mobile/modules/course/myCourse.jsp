<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<meta name="viewport" content="maximum-scale=1.0,minimum-scale=1.0,user-scalable=0,width=device-width,initial-scale=1.0">
	<meta name="format-detection" content="telephone=no,email=no,date=no,address=no">
<html>
<head>
<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile-css/Classroom.css" /> 
<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile-css/style.css" />  
<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile-css/mobileSelect.css" /> 
<script type="text/javascript"  src="${ctxStatic }/mobile-js/jquery-min.js"></script>
    <script type="text/javascript"  src="${ctxStatic }/mobile-js/mobileSelect.js"></script>
    <%-- <%@ include file="/WEB-INF/views/include/head.jsp" %> --%>
    
    <title>${fns:getConfig('productName')} 我的课件</title>
</head>
<body>
	<div class="Header">
		<a href="#" class="Return"><i></i></a>
		<h1>我的课件</h1>
	</div>
  	<div class="main">
		<div class="Headerbg"></div>
		<div class="banner"><img src="${ctxStatic }/mobile-images/tu4.png" width="100%"></div>
		<form action="${ctx}/jzmk/addCourse" method="post">
		<ul class="ClassroomUl">
			<li>
				<a href="#">
				<p class="p1">童年缺失的心理营养，如何在成年后补足？</p>
				<p class="p2"><span class="left ys1">2017-01-01</span><span class="right ys2">2分</span></p>
				<div class="clear"></div>
				</a>
			</li>
			<li>
				<a href="#">
				<p class="p1">童年缺失的心理营养，如何在成年后补足？</p>
				<p class="p2"><span class="left ys1">2017-01-01</span><span class="right ys2">2分</span></p>
				<div class="clear"></div>
				</a>
			</li>
			<li>
				<a href="#">
				<p class="p1">童年缺失的心理营养，如何在成年后补足？</p>
				<p class="p2"><span class="left ys1">2017-01-01</span><span class="right ys2">2分</span></p>
				<div class="clear"></div>
				</a>
			</li>
			<li>
				<a href="#">
				<p class="p1">童年缺失的心理营养，如何在成年后补足？</p>
				<p class="p2"><span class="left ys1">2017-01-01</span><span class="right ys2">2分</span></p>
				<div class="clear"></div>
				</a>
			</li>
			<li>
				<a href="#">
				<p class="p1">童年缺失的心理营养，如何在成年后补足？</p>
				<p class="p2"><span class="left ys1">2017-01-01</span><span class="right ys2">2分</span></p>
				<div class="clear"></div>
				</a>
			</li>
			<li>
				<a href="#">
				<p class="p1">童年缺失的心理营养，如何在成年后补足？</p>
				<p class="p2"><span class="left ys1">2017-01-01</span><span class="right ys2">2分</span></p>
				<div class="clear"></div>
				</a>
			</li>
		</ul>
	</div>
	<div class="buttonkuuang_1">
		<input type="submit" value="新增课件" class="buttoninput">
	</div>
	</form>
</body>
</html>