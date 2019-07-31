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