<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<meta name="viewport" content="maximum-scale=1.0,minimum-scale=1.0,user-scalable=0,width=device-width,initial-scale=1.0">
<meta name="format-detection" content="telephone=no,email=no,date=no,address=no">
<html>
<head>
<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile-css/circle.css" /> 
<link rel="stylesheet" href="${ctxStatic}/mobile-css/style.css">
    <link rel="stylesheet" href="${ctxStatic}/mobile-css/mobileSelect.css"> 
    <script type="text/javascript"  src="${ctxStatic }/mobile-js/jquery-min.js"></script>
    <script type="text/javascript"  src="${ctxStatic }/mobile-js/mobileSelect.js"></script>
   <%--  <%@ include file="/WEB-INF/views/include/head.jsp" %> --%>
    <title>${fns:getConfig('productName')} 发布状态</title>
</head>
<body bgcolor="#f6f6f6">
	<div class="Header">
		<a href="#" class="Return"><i></i></a>
		<h1>发布状态</h1>
			<a href="${ctx }/publish" class="Headerbut">发布</a>
	</div>
  	<div class="main">
		<div class="Headerbg"></div>
		<div class="fabuzt">
			<textarea placeholder="#幸福的镜子#我的健康指数达到了10分，快来膜拜我吧！"></textarea>
			<div class="xztu"><input type="file" class="file"></div>
		</div>
	</div>
	</body>
</html>