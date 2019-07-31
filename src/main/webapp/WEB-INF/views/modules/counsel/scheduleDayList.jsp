<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>日程安排天管理</title>
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
		<li class="active"><a href="${ctx}/counsel/scheduleDay/">日程安排天列表</a></li>
		<shiro:hasPermission name="counsel:scheduleDay:edit"><li><a href="${ctx}/counsel/scheduleDay/form">日程安排天添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="scheduleDay" action="${ctx}/counsel/scheduleDay/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>某年某月某天：</label>
				<input name="day" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${scheduleDay.day}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
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
				<th>某年某月某天</th>
				<shiro:hasPermission name="counsel:scheduleDay:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="scheduleDay">
			<tr>
				<td><a href="${ctx}/counsel/scheduleDay/form?id=${scheduleDay.id}">
					<fmt:formatDate value="${scheduleDay.day}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</a></td>
				<shiro:hasPermission name="counsel:scheduleDay:edit"><td>
    				<a href="${ctx}/counsel/scheduleDay/form?id=${scheduleDay.id}">修改</a>
					<a href="${ctx}/counsel/scheduleDay/delete?id=${scheduleDay.id}" onclick="return confirmx('确认要删除该日程安排天吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>