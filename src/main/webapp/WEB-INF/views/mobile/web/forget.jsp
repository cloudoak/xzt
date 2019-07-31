<!-- 

忘记密码

@author wyz 
@since 2018/01/20

-->
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">
	<title>重置密码页</title>
	<meta name="keywords" content="${fns:getConfig('keywords')} ${sysConfig.schoolName } ">
    <meta name="description" content="${fns:getConfig('description')} ${sysConfig.schoolName }">
	  <link rel="stylesheet" href="${ctxStatic}/mobile-css/Land.css">
    <link rel="stylesheet" href="${ctxStatic}/mobile-css/style.css">
    <link rel="stylesheet" href="${ctxStatic}/mobile-css/mobileSelect.css">
    <script type="text/javascript"  src="${ctxStatic }/mobile-js/jquery-min.js"></script>
    <script type="text/javascript"  src="${ctxStatic }/mobile-js/mobileSelect.js"></script>
</head>
<body class="gray-bg">
    <div class="Header">
		<a href="#" class="Return"><i></i></a>
		<h1>重置密码</h1>
	</div>
  
	<form action="${ctx}/register/forgetSave"  method="post">
  	<div class="main">
		<div class="Headerbg"></div>
		<ul class="registerList">
			<li>
				<div class="left1"><img src="${ctxStatic }/mobile-images/ico6.png"></div>
				<input type="text" placeholder="请输入新密码" name="password" >
				<div class="clear"></div>
			</li>
			<li>
				<div class="left1"><img src="${ctxStatic }/mobile-images/ico6.png"></div>
				<input type="text" placeholder="请确认新密码" name="confirmpassword" >
				<div class="clear"></div>
			</li>
		</ul>
		<div class="button_2">
			<input type="submit" value="确定" class="buttoninput"  />
		</div>
	</div>
	</form>
</body>
	
<script type="text/javascript">

	//倒计时
	 var clock = '';
	 var nums = 60;
	 var btn;
	 function sendCode(thisBtn){ 
		 btn = thisBtn;
		 btn.disabled = true; 
		 btn.value = '重新获取'+nums+'s';
		 btn.style.color="#999";
		 clock = setInterval(doLoop, 1000); 
	}
	function doLoop(){
		 nums--;
		 if(nums > 0){
			  btn.value = '重新获取'+nums+'s';
			  btn.style.color="#999";
		 }else{
			  clearInterval(clock); 
			  btn.disabled = false;
			  btn.value = '获取验证码';
			  btn.style.color="#f3892d";
			  nums = 60; 
		 }
	 }
	 
</script>
</body>
</html>