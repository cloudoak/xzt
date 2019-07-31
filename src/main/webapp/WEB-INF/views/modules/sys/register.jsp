<%@ page contentType="text/html;charset=UTF-8" %>
<c:set var="serverUrl" value="${pageContext.request.contextPath}/web/"/>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">
	<title>注册页</title>
	<link href="${serverUrl }css/global.css" type="text/css" rel="stylesheet" />
	<link href="${serverUrl }css/reset.css" type="text/css" rel="stylesheet" />
	<link href="${serverUrl }css/style.css" type="text/css" rel="stylesheet" />
	<link href="${serverUrl }css/measure.css" type="text/css" rel="stylesheet" />
	<link href="${serverUrl }css/header.css" type="text/css" rel="stylesheet" />
	<link href="${ctxStatic }/jquery-validation/1.11.1/jquery.validate.min.css" type="text/css" rel="stylesheet" />
    
    <script src="${ctxStatic}/jquery/jquery-1.9.1.min.js" type="text/javascript"></script>
    <script src="${ctxStatic}/jquery-validation/1.11.1/jquery.validate.min.js" type="text/javascript"></script>
    <script src="${serverUrl }js/header.js" type="text/javascript"></script>
    
</head>
<body>
	 <!-- 头部 -->
    <div class="header" id="header">
		<div class="wrapper clearfix">
		    <div class="logo fl-left">
		        <a href="#" id=""><img src="${serverUrl }images/logo.png" alt="知心堂·心理云平台" width="100%"></a>
		    </div>
		    <div class="sidebar fl-right">
		        <span class="collapsed fl-right" id="collapsed"></span>
		        <div class="entrance fl-right">
		            <a href="${ctx}/xzt/a/goLogin" class="login entrance-btn"><span>登录</span></a>
		            <a href="${ctx}/xzt/a/goRegister" class="signup entrance-btn"><span>注册</span></a>
		        </div>
		        <ul class="nav clearfix fl-right" id="navTable">
		            <li class="current"><a href="${ctx}/xzt/web/index">首页</a></li>
		            <li><a href="${ctx}/xzt/web/system">接待室</a></li>
		            <li><a href="${ctx}/xzt/web/clroom">测量室</a></li>
		            <li><a href="${ctx}/xzt/web/visitor">咨询室</a></li>
		            <li><a href="${ctx}/xzt/web/scale">档案室</a></li>
		            <li><a href="${ctx}/xzt/web/circle">心理课堂</a></li>
		            <li><a href="${ctx}/xzt/web/mirror">放松室</a></li>
		            <li class="passport"><a href="${ctx}/goLogin">登录</a></li>
		            <li class="passport"><a href="${ctx}/goRegister">注册</a></li>
		        </ul>
		    </div>
		</div>
	</div>
	<div class="register-container" id="container">
		<div class="wrapper">
			<div class="register-form">
				<h1 class="title">注册</h1>
				<div class="form-tab clearfix">
					<div class="visitor item current">来访者注册</div>
					<div class="family item">家属注册</div>
					<div class="counselor item">咨询者注册</div>
				</div>
				<div class="form-wrapper">
					<div class="left">
						<div class="field">
							<label for="">编号</label>
							<input type="text" placeholder="请输入您的编号">
						</div>
						<div class="field">
							<label for="">姓名</label>
							<input type="text" placeholder="请输入您的姓名">
						</div>
						<div class="field">
							<label for="">密码</label>
							<input type="text" placeholder="请设置密码">
						</div>
						<div class="field">
							<label for="">密码确认</label>
							<input type="text" placeholder="请确认密码">
						</div>
						<div class="field">
							<label for="">昵称</label>
							<input type="text" placeholder="请确认您的昵称">
						</div>
					</div>
					<div class="right">
						<div class="field single">
							<label for="">性别</label>
							<span class="ipt-radio"><input type="radio" class="radio"> 男</span>
							<span class="ipt-radio"><input type="radio" class="radio"> 女</span>
						</div>
						<div class="field select" id="nation">
							<label for="">民族</label>
							<select name="" id="">
								<option value="0">请选择</option>
								<option value="1">汉族</option>
								<option value="2">回族</option>
							</select>
						</div>
						<div class="field">
							<label for="">出生日期</label>
							<input type="text" placeholder="请输入您的出生日期">
						</div>
						<div class="field single">
							<label for="">组织机构</label>
							<span class="fold">一级机构</span>
						</div>
						<div class="field">
							<label for="">电话联系</label>
							<input type="text" placeholder="请输入您的联系方式">
						</div>
					</div>
					<div class="btn-register">注册</div>
				</div>
			</div>
		</div>
	</div>
	<!-- 底部 -->
    <%@ include file="/WEB-INF/views/web/common/footer.jsp" %>
	<script>
        function isPc() {
            var userAgentInfo = navigator.userAgent;
            var agents = ['Android', 'iPhone', 'SymbianOS', 'Windows Phone', 'iPad', 'iPod'];
            var flag = true;
            for (var i = 0; i < agents.length; i ++) {
                if (userAgentInfo.indexOf(agents[i]) > 0) {
                    flag = false;
                    break;
                }
            }
            return flag;
        }
        function adjustScreen() {
            var winHeight = $(window).height();
            var containerHeight = winHeight - $('#header').innerHeight();
            if (isPc()) {
            	if ($(window).width() > 890) {
	                containerHeight = winHeight - $('#header').innerHeight() - $('#footer').innerHeight() - 3;
	                var tabH = $('.form-tab').innerHeight() + 13;
	                var formH = $('.register-form').innerHeight();
	                var margin = containerHeight - formH;
	                $('.register-form').css({
	                	'margin-top': margin / 2
	                });
	            	$('#container').height(containerHeight);
            	}
            }
        }
        adjustScreen();
        $(window).resize(function () {
            adjustScreen();
        });
    </script>
</body>
</html>