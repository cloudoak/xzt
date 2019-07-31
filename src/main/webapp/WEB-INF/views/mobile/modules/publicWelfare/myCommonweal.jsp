<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>

<html>
<head>
<link rel="stylesheet" type="text/css" href="${ctxStatic}/css/commonweal.css" />  /> 
    <%@ include file="/WEB-INF/views/include/head.jsp" %>
    <title>${fns:getConfig('productName')} 我的公益</title>
</head>
<body>
	<div class="Header">
		<a href="#" class="Return"><i></i></a>
		<h1>公益活动</h1>
		<a href="#" class="HeaderTxt">删除</a>
	</div>
  	<div class="main">
		<div class="Headerbg"></div>
		<div class="commonboxbanner"><a href="#"><img src="${ctxStatic}/images/tu1.png" width="100%"></a></div>
		<div class="clear"></div>
		<ul class="SupportNav">
			<li><a href="javascript:">全部公益</a></li>
			<li><a href="javascript:" class="on">我的公益</a></li>
		</ul>
		<ul class="rwglul">
			<li>
				<a href="#">
				<p class="p1"><span class="left">公益活动标题</span><span class="right ys">报名中</span></p>
				<p class="p3"><span class="left">参与人数：12</span><span class="right ys1">2017-07-08 18:01:32</span></p>
				</a>
				<div class="clear"></div>
			</li>
			<li>
				<a href="#">
				<p class="p1"><span class="left">公益活动标题</span><span class="right ys">报名中</span></p>
				<p class="p3"><span class="left">参与人数：12</span><span class="right ys1">2017-07-08 18:01:32</span></p>
				</a>
				<div class="clear"></div>
			</li>
			<li>
				<a href="#">
				<p class="p1"><span class="left">公益活动标题</span><span class="right ys">报名中</span></p>
				<p class="p3"><span class="left">参与人数：12</span><span class="right ys1">2017-07-08 18:01:32</span></p>
				</a>
				<div class="clear"></div>
			</li>
			<li>
				<a href="#">
				<p class="p1"><span class="left">公益活动标题</span><span class="right ys">报名中</span></p>
				<p class="p3"><span class="left">参与人数：12</span><span class="right ys1">2017-07-08 18:01:32</span></p>
				</a>
				<div class="clear"></div>
			</li>
		</ul>
	</div>
	<div class="buttonbot">
		<input type="button" value="发布公益活动" class="buttoninput">
	</div>
</body>
</html>