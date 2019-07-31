<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<meta name="viewport" content="maximum-scale=1.0,minimum-scale=1.0,user-scalable=0,width=device-width,initial-scale=1.0">
	<meta name="format-detection" content="telephone=no,email=no,date=no,address=no">
<html>
<head>
<link rel="stylesheet" href="${ctxStatic}/mobile-css/style.css">
<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile-css/Support.css" /> 
<link rel="stylesheet" href="${ctxStatic}/mobile-css/mobileSelect.css">  
<script type="text/javascript"  src="${ctxStatic }/mobile-js/jquery-min.js"></script>
    <script type="text/javascript"  src="${ctxStatic }/mobile-js/mobileSelect.js"></script> 
  
    <title>${fns:getConfig('productName')} 酣眠支持</title>
</head>
<body>
	<div class="Header">
		<a href="${ctx }/web/index" class="Return"><i></i></a>
		<h1>音乐列表</h1>
	</div>
  	<div class="main">
		<div class="Headerbg"></div>
		<!-- 音乐分类列表 -->
		<ul class="SupportNav">
		<c:forEach items="${list}" var="item">
		<li><a href="${ctx}/relax/musicCatalog?musicType=1&id=${item.id}&parentIds=${item.parentIds}" <c:if test="${checkedId == item.id}"> class="on" </c:if>>${item.name}</a></li>
		</c:forEach>
		</ul>
		<!-- 音乐列表 -->
		<ul class="Songlist">
		<c:forEach items="${musicList}" var="music">
			<li>
				<a href="${ctx}/relax/music/form?id=${music.id}">
					<h2>${music.name}</h2>
					<p>${music.singer }</p>
					<img src="${ctxStatic}/mobile-images/ico10.png" />
					<img> 
				</a>
			</li>
		</c:forEach>
		</ul>
		<div class="clear"></div>
	</div>
</body>
<script>
	$(function(){
		$(".SupportNav li").click(function(){
			$(".SupportNav li a").removeClass("on");
			$(this).children("a").addClass("on");
			var iIndex = $(this).index();
			$(".conBox>ul").eq(iIndex).show().siblings().hide();
		})
	})
</script>
</html>