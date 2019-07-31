<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>答题信息管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
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
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/jzmk/scaleTaskAnswer/">答题信息列表</a></li>
		<li class="active"><a href="${ctx}/jzmk/scaleTaskAnswer/form?id=${scaleTaskAnswer.id}">答题信息<shiro:hasPermission name="jzmk:scaleTaskAnswer:edit">${not empty scaleTaskAnswer.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="jzmk:scaleTaskAnswer:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="scaleTaskAnswer" action="${ctx}/jzmk/scaleTaskAnswer/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">测评任务人员ID：</label>
			<div class="controls">
				<form:input path="taskUserId" htmlEscape="false" maxlength="11" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">条目ID：</label>
			<div class="controls">
				<form:input path="tid" htmlEscape="false" maxlength="11" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">回答答案：</label>
			<div class="controls">
				<form:input path="answer" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">正确参考答案：</label>
			<div class="controls">
				<form:input path="rightAnswer" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">因子得分：</label>
			<div class="controls">
				<form:input path="score" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">原始分：</label>
			<div class="controls">
				<form:input path="oldScore" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否异常：</label>
			<div class="controls">
				<form:select path="isCheckPass" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('is_check_pass')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">机构ID：</label>
			<div class="controls">
				<form:input path="orgId" htmlEscape="false" maxlength="11" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="jzmk:scaleTaskAnswer:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>