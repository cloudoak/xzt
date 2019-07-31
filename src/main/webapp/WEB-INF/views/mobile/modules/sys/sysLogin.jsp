<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%><!DOCTYPE >
<meta name="viewport" content="maximum-scale=1.0,minimum-scale=1.0,user-scalable=0,width=device-width,initial-scale=1.0">
	<meta name="format-detection" content="telephone=no,email=no,date=no,address=no">
<html>
<head>
    <meta charset="utf-8">
    <title>${fns:getConfig('productName')}</title>
    <meta name="viewport" content="initial-scale=1, maximum-scale=1, user-scalable=no">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    
    <link rel="stylesheet" href="${ctxStatic}/mobile-css/Land.css">
    <link rel="stylesheet" href="${ctxStatic}/mobile-css/style.css">
    <link rel="stylesheet" href="${ctxStatic}/mobile-css/mobileSelect.css">
     <title>${fns:getConfig('productName')} 酣眠登录</title>
      <style type="text/css">

        .mid {
            vertical-align: middle;
        }

        .header {
            height: 80px;
            padding-top: 20px;
            display:none;
        }

        .alert {
            position: relative;
            width: 300px;
            margin: 0 auto;
            *padding-bottom: 0px;
        }

        label.error {
            background: none;
            width: 270px;
            font-weight: normal;
            color: inherit;
            margin: 0;
        }
    </style>
    
    <script type="text/javascript">
    
		$(document).ready(function() {
			$("#loginForm").validate({
				rules: {
					validateCode: {remote: "${pageContext.request.contextPath}/servlet/validateCodeServlet"}
				},
				messages: {
					username: {required: "请填写用户名."},password: {required: "请填写密码."},
					validateCode: {remote: "验证码不正确.", required: "请填写验证码."}
				},
				errorLabelContainer: "#messageBox",
				errorPlacement: function(error, element) {
					error.appendTo($("#loginError").parent());
				}
			});
			
		
		});
		// 如果在框架或在对话框中，则弹出提示并跳转到首页
		if(self.frameElement && self.frameElement.tagName == "IFRAME" || $('#left').length > 0 || $('.jbox').length > 0){
			alert('未登录或登录超时。请重新登录，谢谢！');
			top.location = "${ctx}";
		}
	</script>
    
    
</head>
<body>
	<div class="header">
	     <div id="messageBox" class="alert alert-error ${empty message ? 'hide' : ''}">
			<label id="loginError" class="error" style="color: red;font-size: 10px;"><lable style="font-size: 22px;font-weight: bold;">×</lable> ${message}</label>
		</div>
	</div>

	<div class="Land" >
   		<div class="Header Headerparent">
			<a href="#" class="Return"><i></i></a>
			<h1>${fns:getConfig('systemName')}</h1>
		</div>
		
		<div class="logo"><img src="${ctxStatic}/mobile-images/Landlogo.png"></div>
		<form id="loginForm"  action="${ctx}/login" method="post">
		<ul class="Landshuru">
			<li>
				<i class="ico1"></i><div class="xian"></div><input class="inputText"  type="text"  name="username"  value="" />	
			</li>
			<li>
				<i class="ico2"></i><div class="xian"></div><input class="inputPass"  type="password"  value=""  name="password" />
			</li>
			<li class="liborder">
				<div class="left"><a href="${ctx}/register/form">快速注册</a></div>
				<div class="right"><a href="${ctx}/register/forget">忘记密码？</a></div>
			</li>
	    </ul>
	    <input type="hidden" name="mobileLogin" value="true">
		<div class="button_1">
			<input type="button" value="登录" class="buttoninput" />
		</div>
		<input type="submit" style="display: none" id="submitBtn" />
   </div>
   </form>
<script type="text/javascript" src="${ctxStatic}/jingle/js/lib/zepto.js"></script>
<script type="text/javascript" src="${ctxStatic}/jingle/js/lib/iscroll.js"></script>
<script type="text/javascript" src="${ctxStatic}/jingle/js/lib/Jingle.debug.js"></script>
<script type="text/javascript" src="${ctxStatic}/jingle/js/lib/zepto.touch2mouse.js"></script>
<!--- app --->
<script type="text/javascript">var ctx = '${ctx}';</script>
<script type="text/javascript" src="${ctxStatic}/jingle/js/app/app.js"></script>
<script type="text/javascript">
var sessionid = '${not empty fns:getPrincipal() ? fns:getPrincipal().sessionid : ""}';
$('body').delegate('#login_section','pageinit',function(){
	$("#loginForm").submit(function(){
		if ($('#username').val() == ''){
			J.showToast('请填写账号', 'info');
		}else if ($('#password').val() == ''){
			J.showToast('请填写密码', 'info');
		}else if ($('#validateCodeDiv').is(':visible') && $('#validateCode').val() == ''){
			J.showToast('请填写验证码', 'info');
		}else{
			var loginForm = $("#loginForm");
			$.post(loginForm.attr('action'), loginForm.serializeArray(), function(data){
				if (data && data.sessionid){
					sessionid = data.sessionid;
					J.showToast('登录成功！', 'success');
					J.Router.goTo('#index_section?index');
				}else{
					J.showToast(data.message, 'error');
					if (data.shiroLoginFailure == 'org.apache.shiro.authc.AuthenticationException'){
						$('#validateCodeDiv').show();
					}
					$('#validateCodeDiv a').click();
				}
				//console.log(data);
			});
		}
		return false;
	});
});
$('body').delegate('#login_section','pageshow',function(){
	if (sessionid != ''){
        var targetHash = location.hash;
        if (targetHash == '#login_section'){
    		//J.showToast('你已经登录！', 'success');
    		J.Router.goTo('#index_section?index');
        }
	}else{
		$('#login_article').addClass('active');
	}
});
</script>
</body>
<script src="${ctxStatic }/mobile-js/jquery-min.js"></script>
<script src="${ctxStatic }/layer/layer.js"></script>
<script>
$(function(){
	$(".buttoninput").click(function(event){
		if($(".inputText").val() == ""){
			event.preventDefault();
			layer.msg('请输入用户名！');
			return
		};
		if($(".inputPass").val() == ""){
			event.preventDefault();
			layer.msg('请输入密码！');
			return
		}
		$("#submitBtn").click();
	});
	var iHeight = $(window).height();
	$("body").css("min-height",iHeight)
});

</script>
<!-- <script type="text/javascript">
    function login() {
      $.ajax({
      //几个参数需要注意一下
        type: "POST",//方法类型
        dataType: "json",//服务端接收的数据类型
        url: "/users/login" ,//url
        data: $('#form1').serialize(),
        success: function (result) {
          console.log(result);//打印服务端返回的数据(调试用)
          if (result.resultCode == 200) {
            alert("SUCCESS");
          }
          ;
        },
        error : function() {
          alert("异常！");
        }
      });
    }
  </script>
 -->

</html>