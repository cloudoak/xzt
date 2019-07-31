<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<meta name="viewport" content="maximum-scale=1.0,minimum-scale=1.0,user-scalable=0,width=device-width,initial-scale=1.0">
	<meta name="format-detection" content="telephone=no,email=no,date=no,address=no">
<html>
<head>
<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile-css/commonweal.css" />  
<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile-css/style.css" />  
<link rel="stylesheet" href="${ctxStatic}/mobile-css/mobileSelect.css">
<script type="text/javascript"  src="${ctxStatic }/mobile-js/jquery-min.js"></script>
<script type="text/javascript"  src="${ctxStatic }/mobile-js/mobileSelect.js"></script>

    <title>${fns:getConfig('productName')} 全部公益</title>
</head>
<body>
	<div class="Header">
		<a href="#" class="Return"><i></i></a>
		<h1>公益活动</h1>
	</div>
	<form action="${ctx}/ante/publicActivity/form?myActivity=false" method="post">
  	<div class="main">
		<div class="Headerbg"></div>
		<div class="commonboxbanner"><a href="#"><img src="${ctxStatic }/mobile-images/tu1.png" width="100%"></a></div>
		<div class="clear"></div>
		<ul class="SupportNav">
			<li><a href="javascript:" class="on">全部公益</a></li>
			<li><a href="${ctx}/ante/publicActivity/myList">我的公益</a></li>
		</ul>
		<div class="conBox">
			<!--全部公益-->
			<ul class="rwglul">
			<c:forEach items="${page.list}" var="publicActivity">
				<li>
					<a href="${ctx}/ante/publicActivity/view?id=${publicActivity.id}">
					<p class="p1"><span class="left">公益活动标题:${publicActivity.title}</span><span class="right ys">${fns:getDictLabel(publicActivity.activityStatus, 'public_activity_status', '')}</span></p>
					<p class="p3"><span class="left">参与人数：${publicActivity.parterNum }</span></p>
					<p class="p3"><span class="left">发布人：	${publicActivity.createName}</span><span class="right ys1"><fmt:formatDate value="${publicActivity.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/></span></p>
					</a>
					<div class="clear"></div>
				</li>
				</c:forEach>
			</ul>
		</div>
		
	</div>
	<div class="buttonbot">
		<input type="submit" value="发布公益活动" class="buttoninput">
	</div>
	</form>
</body>
<script>
	$(function(){
		$(".SupportNav li").click(function(){
			$(".SupportNav li a").removeClass("on");
			$(this).children("a").addClass("on");
			/* var oIndex = $(this).index();
			$(".conBox>ul").eq(oIndex).show().siblings().hide(); */
		})
		
	})
</script>
</html>