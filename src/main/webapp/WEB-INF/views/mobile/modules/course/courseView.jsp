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

    <title>${fns:getConfig('productName')} 酣眠课堂-内容页</title>
</head>
<body bgcolor="#f6f6f6">
	<div class="Header">
		<a href="#" class="Return"><i></i></a>
		<h1>酣眠课堂</h1>
		<a href="${ctx }/jzmk/myCourse" class="HeaderTxt">我的课件</a>
	</div>
  	<div class="main">
		<div class="Headerbg"></div>
		<form action="${ctx}/jzmk/message" method="post">
		<ul class="ClassroomUl">
			<li>
				<p class="p1">${course.name}</p>
				<p class="p3">${course.intro }</p>
				<p class="p2"><span class="left ys1"><fmt:formatDate value="${course.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></span><span class="right ys2">2分</span></p>
				<div class="clear"></div>
			</li>
		</ul>
		<div class="ClassroomUl_top">留言</div>
		<ul class="ClassroomUl">
			<li>
				<p class="p1">童年缺失的心理营养，如何在成年后补足？</p>
				<p class="p3">留言内容，留言内容，留言内容，</p>
				<p class="p2"><span class="left ys1">2017-01-01</span><span class="right">张三</span></p>
				<div class="clear"></div>
			</li>
			<li>
				<p class="p1">童年缺失的心理营养，如何在成年后补足？</p>
				<p class="p3">留言内容，留言内容，留言内容，</p>
				<p class="p2"><span class="left ys1">2017-01-01</span><span class="right">张三</span></p>
				<div class="clear"></div>
			</li>
			<li>
				<p class="p1">童年缺失的心理营养，如何在成年后补足？</p>
				<p class="p3">留言内容，留言内容，留言内容，</p>
				<p class="p2"><span class="left ys1">2017-01-01</span><span class="right">张三</span></p>
				<div class="clear"></div>
			</li>
			<li>
				<p class="p1">童年缺失的心理营养，如何在成年后补足？</p>
				<p class="p3">留言内容，留言内容，留言内容，</p>
				<p class="p2"><span class="left ys1">2017-01-01</span><span class="right">张三</span></p>
				<div class="clear"></div>
			</li>
			<li>
				<p class="p1">童年缺失的心理营养，如何在成年后补足？</p>
				<p class="p3">留言内容，留言内容，留言内容，</p>
				<p class="p2"><span class="left ys1">2017-01-01</span><span class="right">张三</span></p>
				<div class="clear"></div>
			</li>
		</ul>
	</div>
	<div class="buttonkuuang_1">
		<input type="submit" value="留言" class="buttoninput">
	</div>
	</form>
</body>
</html>