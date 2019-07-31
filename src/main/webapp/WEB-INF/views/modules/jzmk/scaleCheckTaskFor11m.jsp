<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>量表评测任务管理</title>
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
		<li><a href="${ctx}/jzmk/scaleCheckTask/list">量表评测任务列表</a></li>
		<li class="active"><a href="${ctx}/jzmk/scaleCheckTask/form?id=${scaleCheckTask.id}">量表评测任务<shiro:hasPermission name="jzmk:scaleCheckTask:edit">${not empty scaleCheckTask.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="jzmk:scaleCheckTask:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="scaleCheckTask" action="${ctx}/jzmk/scaleCheckTask/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<div class="control-group">
			<label class="control-label">人员选择：</label>
			<div class="controls">
				<table id="contentTable" class="table table-striped table-bordered table-condensed">
					<thead>
						<tr>
							<th>ID</th>
							<th>姓名</th>
							<!-- <th>性别</th> -->
							<th>身份</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
					<c:forEach items="${users }" var="user">
						<tr>
							<td>
								<input type="checkbox" name="id" value="${user.id}">
							</td>
							<td><a href="${ctx}/jzmk/scale/form?id=${user.id}">
								${user.name}
							</a></td>
							<%-- <td>
								${user.sex==1?"男":"女"}
							</td> --%>
							<td>
								教师
							</td>
							<td><a href="${ctx}/jzmk/scale/form?id=${user.id}">
								删除
							</a></td>
						</tr>
					</c:forEach>
					</tbody>
				</table>
				<%-- <form:input path="taskUserId" htmlEscape="false" maxlength="11" class="input-xlarge  digits"/> --%>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">量表选择：</label>
			<div class="controls">
				<table id="contentTable" class="table table-striped table-bordered table-condensed">
					<thead>
						<tr>
							<th></th>
							<th>量表名称</th>
							<th>所需积分</th>
							<th>答题时间</th>
							<th>量表简介</th>
						</tr>
					</thead>
					<tbody>
					<c:forEach items="${scales }" var="scale">
						<tr>
							<td>
								<form:checkbox path="taskGeneId" value="${scale.id}" label=""/>
							</td>
							<td><a href="${ctx}/jzmk/scale/form?id=${scale.id}">
								${scale.name}
							</a></td>
							<td>
								${scale.integral}
							</td>
							<td>
								${scale.maxAnswerTime}
							</td>
							<td>
								${scale.introduce}
							</td>
						</tr>
					</c:forEach>
					</tbody>
				</table>
				<%-- <form:select path="taskGeneId" items="${scales }" itemValue="id" itemLabel="name"  class="input-xlarge  digits"></form:select> --%>
				<%-- <form:input path="taskGeneId" htmlEscape="false" maxlength="11" class="input-xlarge  digits"/> --%>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">开始时间：</label>
			<div class="controls">
				<input name="startTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${scaleCheckTask.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">结束时间：</label>
			<div class="controls">
				<input name="endTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${scaleCheckTask.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">负责人：</label>
			<div class="controls">
				<form:select path="principalId" items="${fns:getDictList('scale_check_task_principal_id')}" itemLabel="label" itemValue="value" htmlEscape="false" class="input-xlarge "></form:select>
				<%-- <form:input path="principalId" htmlEscape="false" maxlength="11" class="input-xlarge  digits"/> --%>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">测评批号：</label>
			<div class="controls">
				<form:input path="batchNumber" htmlEscape="false" maxlength="20" class="input-xlarge "/>
			</div>
		</div>
		<form:hidden path="principalName"/>
		<%-- <div class="control-group">
			<label class="control-label">负责人名称：</label>
			<div class="controls">
				<form:input path="principalName" htmlEscape="false" maxlength="20" class="input-xlarge "/>
			</div>
		</div> --%>
		<div class="control-group">
			<label class="control-label">开启状态</label>
			<div class="controls">
				<form:select path="state" class="input-xlarge ">
					<form:options items="${fns:getDictList('scale_check_task_state')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<form:hidden path="orgId"/>
		<%-- <div class="control-group">
			<label class="control-label">机构ID：</label>
			<div class="controls">
				<form:input path="orgId" htmlEscape="false" maxlength="11" class="input-xlarge  digits"/>
			</div>
		</div> --%>
		<div class="form-actions">
			<shiro:hasPermission name="jzmk:scaleCheckTask:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>