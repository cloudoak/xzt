<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>来访者管理</title>
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
		<%-- <li><a href="${ctx}/ante/visitorInfo/">来访者列表</a></li> --%>
		<li class="active"><a href="${ctx}/ante/visitorInfo/form?id=${visitorInfo.id}">添加来访者信息<shiro:hasPermission name="ante:visitorInfo:edit">${not empty visitorInfo.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="ante:visitorInfo:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="visitorInfo" action="${ctx}/ante/visitorInfo/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">来访者编号：</label>
			<div class="controls">
				${visitorInfo.visitorNo}		
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">姓名：</label>
			<div class="controls">
				${visitorInfo.visitorNo}
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">密码：</label>
			<div class="controls">
				<input type="password"  name="password"/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="ante:visitorInfo:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>