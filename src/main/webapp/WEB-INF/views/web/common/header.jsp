<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<link href="${serverUrl }css/global.css" type="text/css" rel="stylesheet" />
<link href="${serverUrl }css/reset.css" type="text/css" rel="stylesheet" />
<link href="${serverUrl }css/style.css" type="text/css" rel="stylesheet" />
<link href="${serverUrl }css/measure.css" type="text/css" rel="stylesheet" />
<link href="${serverUrl }css/index.css" type="text/css" rel="stylesheet" />
<link href="${serverUrl }css/header.css" type="text/css" rel="stylesheet" />
<script src="${serverUrl }js/jquery.js" type="text/javascript"></script>
<script src="${serverUrl }js/header.js" type="text/javascript"></script>
<div class="header" id="header">
	<div class="wrapper clearfix">
	    <div class="logo fl-left">
	        <a href="${ctx}/web/index"><img src="${serverUrl }images/logo.png" alt="知心堂·心理云平台" width="100%"></a>
	    </div>
	    <div class="sidebar fl-right">
	        <span class="collapsed fl-right" id="collapsed"></span>
	        <c:if test="${empty currentUser }">
	        <div class="entrance fl-right">
	            <a href="${ctx}/xzt/a/login" class="login entrance-btn"><span>登录</span></a>
	            <a href="${ctx}/register/form" class="signup entrance-btn"><span>注册</span></a>
	        </div>
	        </c:if>
	        <c:if test="${not empty currentUser }">
	        <div class="entrance fl-right">
	            <a href="${ctx}/backstage" class="login entrance-btn"><span>后台管理</span></a>
	            <a href="${ctx}/logout" class="signup entrance-btn"><span>注销</span></a>
	        </div>
			</c:if>
			<ul class="nav clearfix fl-right" id="navTable">
			<c:if test="${not empty currentUser }">
			<li>欢迎您, ${currentUser.name }</li>
			<li>当前积分: ${currentUser.score == null ? 0 : currentUser.score  }</li>
			</c:if>
			</ul>
	    </div>
	</div>
</div>