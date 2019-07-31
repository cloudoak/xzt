<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>幸福镜子管理</title>
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
		<li><a href="${ctx}/ante/happyMirror/">幸福镜子列表</a></li>
		<li class="active"><a href="${ctx}/ante/happyMirror/form?id=${happyMirror.id}">幸福镜子<shiro:hasPermission name="ante:happyMirror:edit">查看</shiro:hasPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="happyMirror" action="${ctx}/ante/happyMirror/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">测试人：</label>
			<div class="controls">
				<form:input path="testName" htmlEscape="false" readonly="true" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">身份类型：</label>
			 <div class="controls">
             	<select class="form-control m-b" id="testIdType" name="testIdType" disabled="disabled" width="100%">
             		  <option value="0" hassubinfo="true" ${happyMirror.testIdType==0?"selected":""}>平台管理员</option>
	                  <option value="1" hassubinfo="true" ${happyMirror.testIdType==1?"selected":""}>机构默认管理员</option>
	                  <option value="2" hassubinfo="true" ${happyMirror.testIdType==2?"selected":""}>咨询师</option>
	                  <option value="3" hassubinfo="true" ${happyMirror.testIdType==3?"selected":""}>家属</option>
	                  <option value="4" hassubinfo="true" ${happyMirror.testIdType==4?"selected":""}>来访者</option>
              	</select>
             </div>
			
		</div>
		<div class="control-group">
			<label class="control-label">分数：</label>
			<div class="controls">
				<form:input path="testScore" htmlEscape="false" readonly="true" class="input-xlarge " />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">机构：</label>
			<div class="controls" >
				<c:forEach items="${offices}" var="office">
					<c:if test="${happyMirror.orgId==office.id }">
						<form:input path="orgId" items="${offices}" var="office" value="${office.name}" htmlEscape="false" readonly="true" class="input-xlarge "/>
					</c:if>
				</c:forEach>
			</div>
		</div>
		
		<div class="form-actions">
			<%-- <shiro:hasPermission name="ante:happyMirror:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission> --%>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>