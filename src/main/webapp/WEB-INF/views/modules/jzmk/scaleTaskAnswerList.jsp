<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>答题信息管理</title>
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
		<li class="active"><a href="${ctx}/jzmk/scaleTaskAnswer/">答题信息列表</a></li>
		<shiro:hasPermission name="jzmk:scaleTaskAnswer:edit"><li><a href="${ctx}/jzmk/scaleTaskAnswer/form">答题信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="scaleTaskAnswer" action="${ctx}/jzmk/scaleTaskAnswer/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>测评任务人员ID</th>
				<th>条目ID</th>
				<th>回答答案</th>
				<th>正确参考答案</th>
				<th>因子得分</th>
				<th>原始分</th>
				<shiro:hasPermission name="jzmk:scaleTaskAnswer:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="scaleTaskAnswer">
			<tr>
				<td><a href="${ctx}/jzmk/scaleTaskAnswer/form?id=${scaleTaskAnswer.id}">
					${scaleTaskAnswer.taskUserId}
				</a></td>
				<td>
					${scaleTaskAnswer.tid}
				</td>
				<td>
					${scaleTaskAnswer.answer}
				</td>
				<td>
					${scaleTaskAnswer.rightAnswer}
				</td>
				<td>
					${scaleTaskAnswer.score}
				</td>
				<td>
					${scaleTaskAnswer.oldScore}
				</td>
				<shiro:hasPermission name="jzmk:scaleTaskAnswer:edit"><td>
    				<a href="${ctx}/jzmk/scaleTaskAnswer/form?id=${scaleTaskAnswer.id}">修改</a>
					<a href="${ctx}/jzmk/scaleTaskAnswer/delete?id=${scaleTaskAnswer.id}" onclick="return confirmx('确认要删除该答题信息吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>