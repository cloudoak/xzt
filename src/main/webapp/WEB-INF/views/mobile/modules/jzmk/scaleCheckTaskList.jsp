<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<meta name="viewport" content="maximum-scale=1.0,minimum-scale=1.0,user-scalable=0,width=device-width,initial-scale=1.0">
<meta name="format-detection" content="telephone=no,email=no,date=no,address=no">
<html>
<head>
<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile-css/Assessment.css" /> 
<link rel="stylesheet" href="${ctxStatic}/mobile-css/style.css">
<link rel="stylesheet" href="${ctxStatic}/mobile-css/mobileSelect.css">  
<script type="text/javascript"  src="${ctxStatic }/mobile-js/jquery-min.js"></script>
 <script type="text/javascript"  src="${ctxStatic }/mobile-js/mobileSelect.js"></script>
    <title>${fns:getConfig('productName')} 测评任务管理-列表</title>
</head>
<body>
		<div class="Header">
		<a href="#" class="Return"><i></i></a>
		<h1>测评任务管理</h1>
		<a href="${ctx }/jzmk/delete" class="HeaderTxt">删除</a>
	</div>
	<form action="${ctx }/jzmk/addChoose"  method="post">
  	<div class="main">
		<div class="Headerbg"></div>
		<div class="plglsousuo">
			<input type="text" placeholder="选择条件搜索测评任务">
		</div>
		<ul class="rwglul">
		<c:forEach items="${page.list}" var="scaleCheckTask">
			<li>
				<p class="p1"><span class="left">批号  ${scaleCheckTask.batchNumber}</span><span class="right">${scaleCheckTask.principalName}</span></p>
				<p class="p2"><span class="left"><fmt:formatDate value="${scaleCheckTask.startTime}"
												pattern="yyyy-MM-dd HH:mm:ss" /></span><span class="right"><fmt:formatDate value="${scaleCheckTask.endTime}"
												pattern="yyyy-MM-dd HH:mm:ss" /></span></p>
				<div class="clear"></div>
			</li>
			</c:forEach>
			
		</ul>
	
	<!-- <div class="button_2">
		<input type="submit" value="新增任务" class="buttoninput">
	</div> -->
	<div  style="position:fixed;  bottom:0.5rem;width:30%; margin-left:35%;padding:1rem  0; z-index:1000">
					<input  type="submit"  value="新增任务" class="buttoninput"  style="padding:0 "/>
			</div>
	</div>
	</form>
</body>
</html>