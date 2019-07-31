<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<meta name="viewport" content="maximum-scale=1.0,minimum-scale=1.0,user-scalable=0,width=device-width,initial-scale=1.0">
	<meta name="format-detection" content="telephone=no,email=no,date=no,address=no">
<html>
<head>
<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile-css/commonweal.css" />  
<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile-css/style.css" />  
<script type="text/javascript"  src="${ctxStatic }/mobile-js/jquery-min.js"></script>
    <script type="text/javascript"  src="${ctxStatic }/mobile-js/mobileSelect.js"></script>  
    
    <title>${fns:getConfig('productName')} 活动详情</title>
</head>
<body>
	<body bgcolor="#f6f6f6">
	<div class="Header">
		<a href="${ctx }/ante/publicActivity/list" class="Return"><i></i></a>
		<h1>活动详情</h1>
		<a href="${ctx}/ante/publicActivity/form?id=${publicActivity.id}" class="HeaderTxt">修改</a>
	</div>
  	<div class="main">
		<div class="Headerbg"></div>
		<div class="commonboxbanner"><a href="#"><img src="${ctxStatic}/mobile-images/tu1.png" width="100%"></a></div>
		<div class="clear"></div>
		<form:form id="inputForm" modelAttribute="publicActivity" method="post" class="form-horizontal m-t">
		<ul class="hdxqs">
			<li>
				<div class="left">活动名称：</div>
				<div class="right">${publicActivity.title}</div>
				<div class="clear"></div>
			</li>
			<li>
				<div class="left">活动简介：</div>
				<div class="right">${publicActivity.content}</div>
				<div class="clear"></div>
			</li>
			<li>
				<div class="left">负责人员：</div>
				<div class="right">${publicActivity.createName}</div>
				<div class="clear"></div>
			</li>
			<li>
				<div class="left">联系电话：</div>
				<div class="right">16765657678</div>
				<div class="clear"></div>
			</li>
			<li>
				<div class="left">开始时间：</div>
				<div class="right"><fmt:formatDate value="${publicActivity.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/></div>
				<div class="clear"></div>
			</li>
			<li>
				<div class="left">活动地址：</div>
				<div class="right">北京市朝阳区乐享汇2号楼大碗居</div>
				<div class="clear"></div>
			</li>
		</ul>
		</form:form>
		<div class="rwglul_top">已报名人员</div>
		<ul class="rwglul">
			<li>
				<p class="p1"><span class="left">用户名：爱上一条狗</span></p>
				<p class="p1"><span class="left">手机号：14345456767</span><span class="right">报名时间：2017-10-10</span></p>
				<div class="clear"></div>
			</li>
			<li>
				<p class="p1"><span class="left">用户名：爱上一条狗</span></p>
				<p class="p1"><span class="left">手机号：14345456767</span><span class="right">报名时间：2017-10-10</span></p>
				<div class="clear"></div>
			</li>
			<li>
				<p class="p1"><span class="left">用户名：爱上一条狗</span></p>
				<p class="p1"><span class="left">手机号：14345456767</span><span class="right">报名时间：2017-10-10</span></p>
				<div class="clear"></div>
			</li>
			<li>
				<p class="p1"><span class="left">用户名：爱上一条狗</span></p>
				<p class="p1"><span class="left">手机号：14345456767</span><span class="right">报名时间：2017-10-10</span></p>
				<div class="clear"></div>
			</li>
		</ul>
		<div class="button_2">
		<input type="button" value="立即报名" class="buttoninput">
	</div>
	</div>
	
</body>
</html>