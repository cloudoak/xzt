<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>老师档案</title>
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
		<li><a href="${ctx}/archive/teacherInfo/">老师档案列表</a></li>
		<li class="active"><a href="${ctx}/archive/teacherInfo/form?id=${teacherInfo.id}">老师档案<shiro:hasPermission name="archive:teacherInfo:edit">${not empty teacherInfo.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="archive:teacherInfo:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="teacherInfo" action="${ctx}/archive/teacherInfo/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">用户名：</label>
			<div class="controls">
				<form:input path="teaAcct" htmlEscape="false" maxlength="64" class="input-xlarge "  readonly="true" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">教师姓名：</label>
			<div class="controls">
				<form:input path="teaName" htmlEscape="false" maxlength="50" class="input-xlarge " readonly="true" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">性别</label>
			<div class="controls">
			<td>
					${fns:getDictLabel(teacherInfo.teaSex, 'sex', '')}
				</td>
				
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">邮箱：</label>
			<div class="controls">
				<form:input path="teaMail" htmlEscape="false" maxlength="64" class="input-xlarge "  readonly="true" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">类型：</label>
			<div class="controls">
				<form:input path="teaTypeid" htmlEscape="false" maxlength="64" class="input-xlarge " />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">部门：</label>
			<div class="controls">
				<form:input path="teaDepno" htmlEscape="false" maxlength="64" class="input-xlarge "  readonly="true" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">职务：</label>
			<div class="controls">
				<form:input path="teaJob" htmlEscape="false" maxlength="64" class="input-xlarge "  readonly="true" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">职称：</label>
			<div class="controls">
				<form:input path="teaJobName" htmlEscape="false" maxlength="64" class="input-xlarge "  readonly="true" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">出生年月：</label>
			<div class="controls">
				<input name="teaBirth" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${teacherInfo.teaBirth}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					/>
			</div>
		</div>
		<!--
		<div class="control-group">
			<label class="control-label">上传图片路径：</label>
			<div class="controls">
				<form:input path="teaPicpath" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		-->
		<div class="control-group">
			<label class="control-label">简介：</label>
			<div class="controls">
				<form:input path="teaBrief" htmlEscape="false" maxlength="255" class="input-xlarge "  readonly="true" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">审核状态 0:待审核 1:审核通过 2:驳回：</label>
			<div class="controls">
				<form:input path="teaStatus" htmlEscape="false" maxlength="1" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">组织：</label>
			<div class="controls">
				<form:input path="teaOrg" htmlEscape="false" maxlength="11" class="input-xlarge "  readonly="true"   />
			</div>
		</div>
		<!--
		<div class="control-group">
			<label class="control-label">积分：</label>
			<div class="controls">
				<form:input path="teaJf" htmlEscape="false" maxlength="11" class="input-xlarge "/>
			</div>
		</div>
		-->
		<div class="control-group">
			<label class="control-label">备注：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "  readonly="true" />
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="archive:teacherInfo:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>