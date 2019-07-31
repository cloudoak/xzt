<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>

<html>
<head>
<link rel="stylesheet" type="text/css" href="${ctxStatic}/css/commonweal.css" />  /> 
    <%@ include file="/WEB-INF/views/include/head.jsp" %>
    <title>${fns:getConfig('productName')} 活动详情</title>
</head>
<body>
	<body bgcolor="#f6f6f6">
	<div class="Header">
		<a href="#" class="Return"><i></i></a>
		<h1>活动详情</h1>
		<a href="#" class="HeaderTxt">修改</a>
	</div>
  	<div class="main">
		<div class="Headerbg"></div>
		<div class="commonboxbanner"><a href="#"><img src="${ctxStatic}/images/tu1.png" width="100%"></a></div>
		<div class="clear"></div>
		<ul class="hdxqs">
			<li>
				<div class="left">活动名称：</div>
				<div class="right">北京市帮助残疾儿童</div>
				<div class="clear"></div>
			</li>
			<li>
				<div class="left">活动简介：</div>
				<div class="right">婀娜多姿的活动，妩媚的美女，漂亮的诶里的
               风景婀娜多姿的活动，妩媚的美女漂亮的房子，
               媚的美女，漂亮的房子，诶里的风景婀娜多姿
               的活动，妩媚的美女，漂亮的房子。</div>
				<div class="clear"></div>
			</li>
			<li>
				<div class="left">负责人员：</div>
				<div class="right">爱上一条狗</div>
				<div class="clear"></div>
			</li>
			<li>
				<div class="left">联系电话：</div>
				<div class="right">16765657678</div>
				<div class="clear"></div>
			</li>
			<li>
				<div class="left">开始时间：</div>
				<div class="right">2017-10-10  10:00</div>
				<div class="clear"></div>
			</li>
			<li>
				<div class="left">活动地址：</div>
				<div class="right">北京市朝阳区乐享汇2号楼大碗居</div>
				<div class="clear"></div>
			</li>
		</ul>
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