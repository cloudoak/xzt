<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>部门管理</title>
	<meta charset="utf-8">
	<meta name="decorator" content="default"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="keywords" content="${fns:getConfig('keywords')} ${sysConfig.schoolName } ">
    <meta name="description" content="${fns:getConfig('description')} ${sysConfig.schoolName }">

	<%@ include file="/WEB-INF/views/include/commoncss.jsp" %>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				rules:{
            		"deptName":{required: true}
            	},
            	messages:{
            		deptName: {required: "请输入部门名称"}
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
</head>
<body class="gray-bg">
	<%-- <ul class="nav nav-tabs">
		<li><a href="${ctx}/ante/department/">部门管理列表</a></li>
		<li class="active"><a href="${ctx}/ante/department/form?id=${department.id}">部门管理<shiro:hasPermission name="ante:department:edit">${not empty department.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="ante:department:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/> --%>
	<div class="wrapper wrapper-content animated fadeInRight">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5><a href="${ctx}/ante/department/form?id=${department.id}">部门管理<shiro:hasPermission name="ante:department:edit">${not empty department.id?'修改':'新增'}</shiro:hasPermission><shiro:lacksPermission name="ante:department:edit">查看</shiro:lacksPermission></a></h5>
                    </div>
                    <div class="ibox-content">
						<form:form id="inputForm" modelAttribute="department" action="${ctx}/ante/department/save" method="post" class="form-horizontal">
							<form:hidden path="id"/>
							<sys:message content="${message}"/>		
							<div class="control-group">
								<label class="control-label">部门名称：</label>
								<div class="controls">
									<form:input path="deptName" htmlEscape="false" maxlength="20" class="input-xlarge " required="true"/>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">部门简介：</label>
								<div class="controls">
									<form:textarea path="deptBrief" htmlEscape="false" rows="6" maxlength="250" class="input-xlarge " required="true"/>
								</div>
							</div>
							<div class="form-actions">
								<shiro:hasPermission name="ante:department:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
								<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
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