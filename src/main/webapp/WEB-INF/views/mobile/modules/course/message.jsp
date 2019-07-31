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
    <title>${fns:getConfig('productName')} 输入留言</title>
</head>
<body>
	<div class="Header">
		<a href="#" class="Return"><i></i></a>
		<h1>留言</h1>
	</div>
  	<div class="main">
		<div class="Headerbg"></div>
		<ul class="registerList">
			<li>
				<div class="left">标题</div>
				<input type="text" placeholder="请输入标题">
				<div class="clear"></div>
			</li>
			<li>
				<div class="left">内容</div>
				<textarea placeholder="请输入内容"></textarea>
				<div class="clear"></div>
			</li>
		</ul>
		<div class="button_2">
			<input type="button" value="提交" class="buttoninput"  />
		</div>
	</div>

</body>
</html>