<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>

<html>
<head>
<link rel="stylesheet" type="text/css" href="${ctxStatic}/css/commonweal.css" />  /> 
    <%@ include file="/WEB-INF/views/include/head.jsp" %>
    <title>${fns:getConfig('productName')} 活动编辑</title>
</head>
<body>
	<div class="Header">
		<a href="#" class="Return"><i></i></a>
		<h1>活动编辑</h1>
		<a href="#" class="HeaderTxt">删除</a>
	</div>
  	<div class="main">
		<div class="Headerbg"></div>
		<ul class="registerList">
			<li>
				<div class="left">活动名称</div>
				<input type="text" placeholder="请输入活动名称">
				<div class="clear"></div>
			</li>
			<li>
				<div class="left">活动时间</div>
				<input type="text" placeholder="请输入活动时间">
				<div class="clear"></div>
			</li>
			<li>
				<div class="left">联系人</div>
				<input type="text" placeholder="请输入联系人">
				<div class="clear"></div>
			</li>
			<li>
				<div class="left">联系电话</div>
				<input type="text" placeholder="请输入联系电话">
				<div class="clear"></div>
			</li>
			<li>
				<div class="left">活动简介</div>
				<textarea placeholder="请输入活动简介"></textarea>
				<div class="clear"></div>
			</li>
			<li class="jiantou">
				<div class="left">活动状况</div>
				<input type="text" placeholder="请选择活动状况">
				<div class="clear"></div>
			</li>
		</ul>
		<div class="button_2">
			<input type="button" value="保存" class="buttoninput"  />
		</div>
	</div>
	
</body>
</html>