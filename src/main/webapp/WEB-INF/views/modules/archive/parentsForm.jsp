<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>家长档案管理</title>
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
		<li><a href="${ctx}/archive/parents/">家长档案列表</a></li>
		<li class="active"><a href="${ctx}/archive/parents/form?id=${parents.id}">家长档案<shiro:hasPermission name="archive:parents:edit">${not empty parents.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="archive:parents:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="parents" action="${ctx}/archive/parents/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">家长编号：</label>
			<div class="controls">
				<form:input path="parentNo" htmlEscape="false" maxlength="20" class="input-xlarge " readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">用户名：</label>
			<div class="controls">
				<form:input path="account" htmlEscape="false" maxlength="20" class="input-xlarge " readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">家长姓名：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="20" class="input-xlarge " readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">性别：</label>
			<div class="controls">
			${fns:getDictLabel(parents.gender, 'sex', '')}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">年龄：</label>
			<div class="controls">
				<form:input path="age" htmlEscape="false" maxlength="11" class="input-xlarge " readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">民族ID：</label>
			<div class="controls">
				<form:input path="nationId" htmlEscape="false" maxlength="11" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">出生年月：</label>
			<div class="controls">
				<input name="birthday" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${parents.birthday}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">邮箱：</label>
			<div class="controls">
				<form:input path="email" htmlEscape="false" maxlength="50" class="input-xlarge " readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">联系电话：</label>
			<div class="controls">
				<form:input path="phone" htmlEscape="false" maxlength="11" class="input-xlarge " readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">家庭年收入：</label>
			<div class="controls">
				<form:input path="householdIncome" htmlEscape="false" class="input-xlarge " readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">家庭总人数：</label>
			<div class="controls">
				<form:input path="householdTotal" htmlEscape="false" maxlength="11" class="input-xlarge " readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">兄弟姐妹数：</label>
			<div class="controls">
				<form:input path="householdBsTotal" htmlEscape="false" maxlength="11" class="input-xlarge " readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否与父母同住0是，1否，2其它：</label>
			<div class="controls">
				<form:input path="isLwyp" htmlEscape="false" maxlength="11" class="input-xlarge " />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">最后登录时间：</label>
			<div class="controls">
				<input name="lastLoginTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${parents.lastLoginTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">登录次数：</label>
			<div class="controls">
				<form:input path="loginCount" htmlEscape="false" maxlength="11" class="input-xlarge " readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否审核0是，1否：</label>
			<div class="controls">
				<form:input path="isCheck" htmlEscape="false" maxlength="11" class="input-xlarge " readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">学生编号：</label>
			<div class="controls">
				<form:input path="studentCode" htmlEscape="false" maxlength="20" class="input-xlarge " readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">用户状态0正常，1异常：</label>
			<div class="controls">
				<form:input path="state" htmlEscape="false" maxlength="11" class="input-xlarge " readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">机构ID：</label>
			<div class="controls">
				<form:input path="orgId" htmlEscape="false" maxlength="11" class="input-xlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="archive:parents:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="关 闭" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>