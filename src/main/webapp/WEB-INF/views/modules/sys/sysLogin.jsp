<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">
    <title>${fns:getConfig('productName')} 登录</title>
    
    <link href="${serverUrl }css/global.css" type="text/css" rel="stylesheet" />
	<link href="${serverUrl }css/reset.css" type="text/css" rel="stylesheet" />
	<link href="${serverUrl }css/style.css" type="text/css" rel="stylesheet" />
	<link href="${serverUrl }css/measure.css" type="text/css" rel="stylesheet" />
	<link href="${serverUrl }css/header.css" type="text/css" rel="stylesheet" />
	<link href="${ctxStatic }/jquery-validation/1.11.1/jquery.validate.min.css" type="text/css" rel="stylesheet" />
	<%-- <link href="${ctxStatic}/jquery-ztree/3.5.12/css/zTreeStyle/zTreeStyle.min.css" rel="stylesheet" type="text/css" /> --%>
   	<link href="${hplusStatic }/js/plugins/layui/css/layui.css" rel="stylesheet">
	<link href="${hplusStatic }/js/plugins/layui/css/layui.mobile.css" rel="stylesheet">
    <style type="text/css">
      .form-signin-heading{font-family:Helvetica, Georgia, Arial, sans-serif, 黑体;font-size:23px;margin-bottom:20px;color:#4bc05a;}
      .form-signin{position:relative;text-align:left;width:300px;padding:0px 22px 29px;margin:0 -21px 20px;background-color:#fff;
        	-webkit-border-radius:5px;-moz-border-radius:5px;border-radius:5px;-webkit-box-shadow:0 1px 2px rgba(0,0,0,.05);-moz-box-shadow:0 1px 2px rgba(0,0,0,.05);box-shadow:0 1px 2px rgba(0,0,0,.05);}
      .form-signin .checkbox{margin-bottom:10px;color:#4bc05a;} 
      .form-signin .input-label{font-size:16px;line-height:23px;color:#999;}
      .form-signin .input-block-level{font-size:16px;height:auto;margin-bottom:15px;padding:7px;*width:283px;*padding-bottom:0;_padding:7px 7px 9px 7px;}
      .form-signin .btn.btn-large{font-size:16px;} 
      .form-signin #themeSwitch{position:absolute;right:15px;bottom:10px;}
      .form-signin div.validateCode {margin-right:125px;width: 100%;} 
      .mid{vertical-align:middle;}
      .alert{position:relative;width:300px;margin:0 auto;*padding-bottom:0px;}
      label.error{background:none;width:270px;font-weight:normal;color:inherit;margin:0;}
      .login-form .btn-login{border: none;width: 107%;}
      /* .divbg{display:none; width: 100%;height: 100%;background-color: #000;opacity:0.5;z-index: 999;position: absolute;}
      .divchkorg{display:none; opacity:0.9; background-color: #fff;position: absolute;z-index: 9999;width: 80%;height: 40%;left:10%;top:30%;border-radius:10px;}
      .divchkorg h3{padding: 2em 0 2em 2em;font-weight: bold;}
      .divchkorg .form-control{width:84%;height:3em;margin-left:2em; border:1px solid #999;border-radius:4px;}
      .divchkorg button{margin-top:2em; margin-left:2em; background-color: #3a0; width: 84%;height: 3em;border: none;color:#fff;border-radius:0.5em;} */
   /*  ul.ztree {
    	border: 1px solid #617775;
    	background: #f0f6e4;
		overflow-y:scroll;
		overflow-x:auto;
		width:180px; 
    	height: 300px;
	}
    
    .menuContent{
    	display:none; 
    	left:0px;
    	top:0px;
    	width:180px; 
    	height: 300px;
    } */
    
    .layui-layer-page .layui-layer-content {overflow: inherit !important;}
    </style>
    
</head>
<body>
    <!-- 头部 -->
    <div class="header" id="header">
		<div class="wrapper clearfix">
		    <div class="logo fl-left">
		        <a href="${ctx}/web/index"><img src="${serverUrl }images/logo.png" alt="知心堂·心理云平台" width="100%"></a>
		    </div>
		</div>
	</div>
    <div class="login-container" id="container">
        <div class="wrapper">
            <img src="${serverUrl }images/balloon.png" alt="" class="balloon">
            <div class="login-form">
				<form id="loginForm" class="form-signin" action="${ctx}/login" method="post">
					<!-- <label class="input-label" for="username">登录名</label> -->
		            <h1 class="form-signin-heading">${fns:getConfig('systemName')}</h1>
					<div class="field clearfix">
	                    <span class="icon-user icon"></span>
						<input type="text" id="username" name="username" class="input-block-level required" maxlength="20" value="system" placeholder="请输入用户名">
					</div>
					<div class="field clearfix">
	                    <span class="icon-key icon"></span>
						<input type="password" id="password" name="password" class="input-block-level required" maxlength="20" lang="20" value="admin" placeholder="请输入密码">
					</div>
					<c:if test="${isValidateCodeLogin}">
					<div class="field clearfix">
	                    <span class="icon-code icon"></span>
						<div class="validateCode">
							<!-- <label class="input-label mid" for="validateCode">验证码</label> -->
							<sys:validateCode name="validateCode" inputCssStyle="margin-bottom:0;width: 115px;"/>
						</div>
					</div>
					</c:if>
					<div id="messageBox" class="alert alert-error ${empty message ? 'hide' : ''}">
						<label id="loginError" class="error" style="color: red;font-size: 10px;"><lable style="font-size: 22px;font-weight: bold;">×</lable> ${message}</label>
					</div>
					<input id="btnLogin" class="btn-login" type="submit" value="登 录"/>
					<p class="bottom clearfix">
	               <%-- <span class="fl-left">还没有账号？<a href="${ctx}/sys/register" class="signup">立即注册</a></span> --%>
	                    <span class="fl-left">还没有账号？<a href="${ctx}/register/form" class="signup">立即注册</a></span>
	                    <span class="fl-right"><a href="${ctx}/register/forget" class="forgetkey">忘记密码?</a></span>
	                </p>
					<%-- <label for="rememberMe" title="下次不需要再登录"><input type="checkbox" id="rememberMe" name="rememberMe" ${rememberMe ? 'checked' : ''}/> 记住我（公共场所慎用）</label> --%>
					<%-- <div id="themeSwitch" class="dropdown">
						<a class="dropdown-toggle" data-toggle="dropdown" href="#">${fns:getDictLabel(cookie.theme.value,'theme','默认主题')}<b class="caret"></b></a>
						<ul class="dropdown-menu">
						  <c:forEach items="${fns:getDictList('theme')}" var="dict"><li><a href="#" onclick="location='${pageContext.request.contextPath}/theme/${dict.value}?url='+location.href">${dict.label}</a></li></c:forEach>
						</ul>
					</div> --%>
				</form>
			</div>
        </div>
    </div>
    <!-- 底部 -->
    <%@ include file="/WEB-INF/views/web/common/footer.jsp" %>
    <script src="${ctxStatic}/jquery/jquery-1.9.1.min.js" type="text/javascript"></script>
    <script src="${ctxStatic}/jquery-validation/1.11.1/jquery.validate.min.js" type="text/javascript"></script>
    <script src="${serverUrl }js/header.js" type="text/javascript"></script>
    <script src="${hplusStatic }/js/plugins/layui/layui.js" type="text/javascript"></script>
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
                'margin-top': (distance > 0) ? (((distance/2) > 78) ? 78 : (distance/2)) : 0
            })
        }
        adjustScreen();
        $(window).resize(function () {
            adjustScreen();
        });
        
     // 如果在框架或在对话框中，则弹出提示并跳转到首页
		if(self.frameElement && self.frameElement.tagName == "IFRAME" || $('#left').length > 0 || $('.jbox').length > 0){
			alert('未登录或登录超时。请重新登录，谢谢！');
			top.location = "${ctx}";
		}
	
		$(function(){     
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
		    var isChk='${listOffice!=null}';
		    if(isChk=='true'){
				layui.use(['layer', 'form'], function(){
					  var layer = layui.layer ,form = layui.form;
	        		var content='<form class="layui-form" action="${ctx}/changeorg" method="post">'+
				 	'<div class="layui-form-item">'+
					'<div class="layui-inline">'+ 
					'<label class="layui-form-label">组织名称：</label>' +
					'<div class="layui-input-inline">' +
					'<select lay-verify="required" lay-search="" name="orgId">' +
						'<option value="">--请选择--</option>'+
		  			  	'<c:forEach items="${listOffice }" var="office">'+
		  			  	'<option value="${office.id }">${office.name }</option>'+
		  			  	'</c:forEach>'+
		  			'</select>'+
		  			'</div>'+
					'</div>'+
					'<button class="layui-btn" name="btnChangeOrg" lay-submit lay-filter="changeOrgFilter" style="display: none;"></button>' +
					'</div>' +
		        	'</form>';
	        		  layer.open({
	  				    type: 1,
	  				    title: "选择管理机构",
	  				    shadeClose: false,
	  				    shade: 0.3,
	  				    maxmin: true,
	  				    area: ['340px', '215px'],
	  				    offset: 'c',
	  				    shift: 2,
	  				    scrollbar: false,
	  				    content: content,
	  				    btn: ['确认','取消'],
	  				    success: function(layero, index){
	  				    	form.render();
	  				    	form.verify({
	  				    		orgId: function(value){
		  				  	    	if(!value) return '组织名称不能为空.';
		  				  	    }
			  				});
	  				    },
	  				    yes: function(index, layero){
	  				    	layero.find('button[name=btnChangeOrg]').click();
	  				    },btn2: function(index, layero){ 
	  				    	location.href = "${ctx}/logout";
	  				    } 
	  				});
	        	});
		    }
		});
    </script>
</body>
</html>