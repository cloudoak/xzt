<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/web/common/common.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">
    <title>登录页</title>
</head>
<body>
    <!-- 头部 -->
    <%@ include file="/WEB-INF/views/web/common/header.jsp"%>
    <div class="login-container" id="container">
        <div class="wrapper">
            <img src="${serverUrl }images/balloon.png" alt="" class="balloon">
            <div class="login-form">
                <h1 class="title">登录</h1>
                <div class="field clearfix">
                    <span class="icon-user icon"></span>
                    <input type="text" name="username" placeholder="请输入您的用户名">
                </div>
                <div class="field clearfix">
                    <span class="icon-key icon"></span>
                    <input type="text" name="password" placeholder="请输入您的密码">
                </div>
                <div class="field clearfix">
                    <span class="icon-code icon"></span>
                    <input id="code" type="text" name="code" placeholder="请输入验证码" value="">
                    <span class="imgcode">
                    	<img id="changeCheckCode" src="${ctx}/xzt/web/code"/>
                    </span>
                </div>
               <!--  <div class="" style="font-size: 14px;text-align: center;color: #63ce72;">
                    <input id="teacher" type="radio" name="role" style="margin-top: 20px;"><label for="teacher">咨询师</label>
                    <input id="visitor" type="radio" name="role" style="margin-left: 20px;margin-top: 20px;"><label for="visitor">来访者</label>
                    <input id="parents" type="radio" name="role" style="margin-left: 20px;margin-top: 20px;"><label for="parents">家属</label>
                	<div id="msg"></div>
                </div> -->
                <div class="btn-login">登录</div>
                <p class="bottom clearfix">
                    <span class="fl-left">还没有账号？<a href="${ctx}/xzt/web/register" class="signup">立即注册</a></span>
                    <span class="fl-right"><a href="${ctx}/xzt/web/forget" class="forgetkey">忘记密码?</a></span>
                </p>
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
            var formHeight = $('.login-form').innerHeight();
            if (isPc()) {
                containerHeight = winHeight - $('#header').innerHeight() - $('#footer').innerHeight();
            }
            if (containerHeight < formHeight) {
                containerHeight = formHeight + 20;
            }
            $('#container').height(containerHeight);
            var distance = containerHeight - formHeight;
            $('.login-form').css({
                'margin-top': distance > 0 ? (distance / 2 > 78 ? 78 : distance / 2) : 0
            })
        }
        adjustScreen();
        $(window).resize(function () {
            adjustScreen();
        });
        
      	//单击切换验证码
    	$("#changeCheckCode").click(function() {
    		$.post("${ctx}/xzt/web/code", "", function(data) {
    			$("#changeCheckCode").attr("src","${ctx}/xzt/web/code");
			});
    		
    	});
      	
      	$(".btn-login").click(function(){
      		/* $.post("${ctx}/xzt/web/cCode", {
      			inCode:$("#code").val()
      		}, function(data){
      			$("#msg").htm(data);
      		}); */
      	});
    </script>
</body>
</html>