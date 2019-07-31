<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>分配量表管理</title>
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
		<li class="active"><a href="${ctx}/jzmk/orgScale/">分配量表列表</a></li>
		<shiro:hasPermission name="jzmk:orgScale:edit"><li><a href="${ctx}/jzmk/orgScale/form">分配量表添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="orgScale" action="${ctx}/jzmk/orgScale/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>量表ID：</label>
				<form:input path="sid" htmlEscape="false" maxlength="11" class="input-medium"/>
			</li>
			<li><label>机构ID：</label>
				<form:input path="orgId" htmlEscape="false" maxlength="11" class="input-medium"/>
			</li>
			<li><label>是否删除 0否 1是：</label>
				<form:radiobuttons path="delFlag" items="${fns:getDictList('del_flag')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>量表ID</th>
				<th>机构ID</th>
				<shiro:hasPermission name="jzmk:orgScale:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="orgScale">
			<tr>
				<td><a href="${ctx}/jzmk/orgScale/form?id=${orgScale.id}">
					${orgScale.sid}
				</a></td>
				<td>
					${orgScale.orgId}
				</td>
				<shiro:hasPermission name="jzmk:orgScale:edit"><td>
    				<a href="${ctx}/jzmk/orgScale/form?id=${orgScale.id}">修改</a>
					<a href="${ctx}/jzmk/orgScale/delete?id=${orgScale.id}" onclick="return confirmx('确认要删除该分配量表吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>