<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${sysConfig.schoolName } 咨询师管理--修改密码</title>
    <meta name="keywords" content="${fns:getConfig('keywords')} ${sysConfig.schoolName } ">
    <meta name="description" content="${fns:getConfig('description')} ${sysConfig.schoolName }">
    <link href="${hplusStatic }/favicon.ico" rel="shortcut icon">
	<link href="${hplusStatic }/css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
	<link href="${hplusStatic }/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
	<link href="${hplusStatic }/css/animate.min.css" rel="stylesheet">
	<link href="${hplusStatic }/css/style.min862f.css?v=4.1.0" rel="stylesheet">
	<link href="${hplusStatic }/css/plugins/iCheck/custom.css" rel="stylesheet">
	<link href="${hplusStatic }/css/plugins/treeview/bootstrap-treeview.css" rel="stylesheet">
	<link href="${hplusStatic }/css/plugins/awesome-bootstrap-checkbox/awesome-bootstrap-checkbox.css" rel="stylesheet">
</head>
<body class="gray-bg">
	<%-- <ul class="nav nav-tabs">
		<li><a href="${ctx}/sys/user/info">个人信息</a></li>
		<li class="active"><a href="${ctx}/sys/user/modifyPwd">修改密码</a></li>
	</ul><br/> --%>
	<div class="wrapper wrapper-content animated fadeInRight">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>修改密码</h5>
                    </div>
                    <div class="ibox-content">
						<form:form id="inputForm" modelAttribute="user" action="${ctx}/sys/user/modifyPwd" method="post" class="form-horizontal">
							<form:hidden path="id"/>
							<sys:message content="${message}"/>
							<div class="form-group">
								<label class="col-sm-3 control-label">旧密码:</label>
								<div class="col-sm-8">
									<input id="oldPassword" name="oldPassword" type="password" value="" maxlength="50" minlength="3" class="required"/>
									<span class="help-inline"><font color="red">*</font> </span>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">新密码:</label>
								<div class="col-sm-8">
									<input id="newPassword" name="newPassword" type="password" value="" maxlength="50" minlength="3" class="required"/>
									<span class="help-inline"><font color="red">*</font> </span>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">确认新密码:</label>
								<div class="col-sm-8">
									<input id="confirmNewPassword" name="confirmNewPassword" type="password" value="" maxlength="50" minlength="3" class="required" equalTo="#newPassword"/>
									<span class="help-inline"><font color="red">*</font> </span>
								</div>
							</div>
							<div class="form-group">
							<div class="col-sm-8 col-sm-offset-3">
								<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>
							</div>
							</div>
						</form:form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script src="${hplusStatic }/js/bootstrap.min.js?v=3.3.6"></script>
	<script src="${hplusStatic }/js/content.min.js?v=1.0.0"></script>
	<script src="${hplusStatic }/js/plugins/validate/jquery.validate.min.js"></script>
	<script src="${hplusStatic }/js/plugins/validate/messages_zh.min.js"></script>
	<script src="${hplusStatic }/js/demo/form-validate-demo.min.js"></script>
	<script src="${hplusStatic }/js/content.min.js?v=1.0.0"></script>
	<script src="${hplusStatic }/js/plugins/iCheck/icheck.min.js"></script>
	<script src="${ctxStatic}/My97DatePicker/WdatePicker.js" ></script>
	<script type="text/javascript">
		$(function() {
			$("#oldPassword").focus();
			$("#inputForm").validate({
				rules: {
					oldPassword: {require: true},
					newPassword: {require: true}
				},
				messages: {
					oldPassword: "旧密码必须填写",
					newPassword: "新密码必须填写",
					confirmNewPassword: {equalTo: "新密码与确认密码不一致"}
				},
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
</body>
</html>