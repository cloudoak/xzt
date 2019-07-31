<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>评论回复管理</title>
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
		<li class="active"><a href="${ctx}/jzmk/circleRecord/">评论回复列表</a></li>
		<shiro:hasPermission name="jzmk:circleRecord:edit"><li><a href="${ctx}/jzmk/circleRecord/form">评论回复添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="circleRecord" action="${ctx}/jzmk/circleRecord/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>圈子ID：</label>
				<form:input path="cId" htmlEscape="false" maxlength="11" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>圈子ID</th>
				<th>回复人</th>
				<th>回复人身份</th>
				<th>被回复人</th>
				<th>被回复人身份</th>
				<th>回复内容</th>
				<shiro:hasPermission name="jzmk:circleRecord:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="circleRecord">
			<tr>
				<td><a href="${ctx}/jzmk/circleRecord/form?id=${circleRecord.id}">
					${circleRecord.cId}
				</a></td>
				<td>
					${circleRecord.replier}
				</td>
				<td>
					${circleRecord.replierRole}
				</td>
				<td>
					${circleRecord.replied}
				</td>
				<td>
					${circleRecord.repliedRole}
				</td>
				<td>
					${circleRecord.content}
				</td>
				<shiro:hasPermission name="jzmk:circleRecord:edit"><td>
    				<a href="${ctx}/jzmk/circleRecord/form?id=${circleRecord.id}">修改</a>
					<a href="${ctx}/jzmk/circleRecord/delete?id=${circleRecord.id}" onclick="return confirmx('确认要删除该评论回复吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>