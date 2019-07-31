<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>save管理</title>
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
		<li class="active"><a href="${ctx}/.testh/testH/">save列表</a></li>
		<shiro:hasPermission name=".testh:testH:edit"><li><a href="${ctx}/.testh/testH/form">save添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="testH" action="${ctx}/.testh/testH/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>name：</label>
				<form:input path="name" htmlEscape="false" maxlength="12" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>name</th>
				<shiro:hasPermission name=".testh:testH:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="testH">
			<tr>
				<td><a href="${ctx}/.testh/testH/form?id=${testH.id}">
					${testH.name}
				</a></td>
				<shiro:hasPermission name=".testh:testH:edit"><td>
    				<a href="${ctx}/.testh/testH/form?id=${testH.id}">修改</a>
					<a href="${ctx}/.testh/testH/delete?id=${testH.id}" onclick="return confirmx('确认要删除该save吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>