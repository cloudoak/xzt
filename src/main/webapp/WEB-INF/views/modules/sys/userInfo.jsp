<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>个人信息管理</title>
	<meta charset="utf-8">
	<meta name="decorator" content="default"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="keywords" content="${fns:getConfig('keywords')} ${sysConfig.schoolName } ">
    <meta name="description" content="${fns:getConfig('description')} ${sysConfig.schoolName }">
	
	<%@ include file="/WEB-INF/views/include/commoncss.jsp" %>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
	</script>
</head>
<body>
	<%-- <ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/sys/user/info">修改个人信息</a></li>
		<li><a href="${ctx}/sys/user/modifyPwd">修改密码</a></li>
	</ul><br/> --%>
	<div class="wrapper wrapper-content animated fadeInRight">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                    	<h5><a href="${ctx}/sys/user/info">修改个人信息</a></h5>
                    </div>
                    <div class="ibox-content">
						<form:form id="inputForm" modelAttribute="user" action="${ctx}/sys/user/info" method="post" class="form-horizontal">
							<sys:message content="${message}"/>
							<div class="control-group">
								<label class="control-label">头像:</label>
								<div class="controls">
									<form:hidden id="nameImage" path="photo" htmlEscape="false" maxlength="255" class="input-xlarge"/>
									<sys:ckfinder input="nameImage" type="images" uploadPath="/photo" selectMultiple="false" maxWidth="100" maxHeight="100"/>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">姓名:</label>
								<div class="controls">
									<form:input path="name" htmlEscape="false" maxlength="50" class="required" readonly="true"/>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">邮箱:</label>
								<div class="controls">
									<form:input path="email" htmlEscape="false" maxlength="50" class="email"/>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">手机:</label>
								<div class="controls">
									<form:input path="mobile" htmlEscape="false" maxlength="11"/>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">用户角色:</label>
								<div class="controls">
									<label class="lbl">${user.roleNames}</label>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">上次登录:</label>
								<div class="controls">
									<label class="lbl">IP: ${user.oldLoginIp}&nbsp;&nbsp;&nbsp;&nbsp;时间：<fmt:formatDate value="${user.oldLoginDate}" type="both" dateStyle="full"/></label>
								</div>
							</div>
							<div class="form-actions">
								<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>
							</div>
						</form:form>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<%@ include file="/WEB-INF/views/include/commonlistjs.jsp" %>
</body>
</html>