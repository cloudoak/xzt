<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>integralUser管理</title>
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
		<li class="active"><a href="${ctx}/relax/integralUser/">integralUser列表</a></li>
		<shiro:hasPermission name="relax:integralUser:edit"><li><a href="${ctx}/relax/integralUser/form">integralUser添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="integralUser" action="${ctx}/relax/integralUser/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>类型：</label>
				<form:select path="integralType" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('integral_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>审核状态：</label>
				<form:select path="status" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('integral_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>用户名称：</label>
				<form:input path="userName" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>类型</th>
				<th>内容</th>
				<th>审核状态</th>
				<th>积分</th>
				<th>用户名称</th>
				<shiro:hasPermission name="relax:integralUser:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="integralUser">
			<tr>
				<td><a href="${ctx}/relax/integralUser/form?id=${integralUser.id}">
					${fns:getDictLabel(integralUser.integralType, 'integral_type', '')}
				</a></td>
				<td>
					${integralUser.content}
				</td>
				<td>
					${fns:getDictLabel(integralUser.status, 'integral_status', '')}
				</td>
				<td>
					${integralUser.score}
				</td>
				<td>
					${integralUser.userName}
				</td>
				<shiro:hasPermission name="relax:integralUser:edit"><td>
    				<a href="${ctx}/relax/integralUser/form?id=${integralUser.id}">修改</a>
					<a href="${ctx}/relax/integralUser/delete?id=${integralUser.id}" onclick="return confirmx('确认要删除该integralUser吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>