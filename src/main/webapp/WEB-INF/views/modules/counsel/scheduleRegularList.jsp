<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>日程安排常规管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/counsel/scheduleRegular/">日程安排常规列表</a></li>
		<shiro:hasPermission name="counsel:scheduleRegular:edit"><li><a href="${ctx}/counsel/scheduleRegular/form">日程安排常规添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="scheduleRegular" action="${ctx}/counsel/scheduleRegular/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>一星期的星期几：</label>
				<form:input path="week" htmlEscape="false" maxlength="4" class="input-medium"/>
			</li>
			<li><label>咨询师名称：</label>
				<form:input path="counselorName" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			<li><label>分中心ID：</label>
				<form:input path="orgId" htmlEscape="false" maxlength="10" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<shiro:hasPermission name="counsel:scheduleRegular:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="scheduleRegular">
			<tr>
				<shiro:hasPermission name="counsel:scheduleRegular:edit"><td>
    				<a href="${ctx}/counsel/scheduleRegular/form?id=${scheduleRegular.id}">修改</a>
					<a href="${ctx}/counsel/scheduleRegular/delete?id=${scheduleRegular.id}" onclick="return confirmx('确认要删除该日程安排常规吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>