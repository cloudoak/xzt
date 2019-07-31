<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<meta name="viewport" content="maximum-scale=1.0,minimum-scale=1.0,user-scalable=0,width=device-width,initial-scale=1.0">
	<meta name="format-detection" content="telephone=no,email=no,date=no,address=no">
<html>
<head>
<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile-css/Classroom.css" /> 
<link rel="stylesheet" href="${ctxStatic}/mobile-css/style.css">
    <link rel="stylesheet" href="${ctxStatic}/mobile-css/mobileSelect.css"> 
<script type="text/javascript"  src="${ctxStatic }/mobile-js/jquery-min.js"></script>
    <script type="text/javascript"  src="${ctxStatic }/mobile-js/mobileSelect.js"></script>

    <title>${fns:getConfig('productName')} 酣眠课堂</title>
</head>
<body>
	<div class="Header">
		<a href="#" class="Return"><i></i></a>
		<h1>酣眠课堂</h1>
		<a href="${ctx}/course/course/list?userId=${user.id}" class="HeaderTxt">我的课件</a>
	</div>
	<form action="${ctx}/course/course/form" method="post">
  	<div class="main">
		<div class="Headerbg"></div>
		<div class="banner"><img src="${ctxStatic}/mobile-images/tu4.png" width="100%"></div>
		<ul class="ClassroomUl">
		<%-- <shiro:hasPermission name="course:course:edit"> --%>
		<c:forEach items="${page.list}" var="course">
			<li>
				<a href="${ctx }/course/course/view?id=${course.id}">
				<p class="p1">${course.name}</p>
				<p class="p2"><span class="left ys1"><fmt:formatDate value="${course.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></span><span class="right ys2">2分</span></p>
				<div class="clear"></div>
				</a>
			</li>
			</c:forEach>
			<%-- </shiro:hasPermission> --%>
			
		</ul>
	</div>
	<div class="buttonkuuang_1">
		<input type="submit" value="新增课件" class="buttoninput">
	</div>
	</form>
</body>
</html>