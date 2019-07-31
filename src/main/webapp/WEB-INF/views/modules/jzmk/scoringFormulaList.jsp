<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>计分方式管理</title>
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
		<li class="active"><a href="${ctx}/jzmk/scoringFormula/">计分方式列表</a></li>
		<shiro:hasPermission name="jzmk:scoringFormula:edit"><li><a href="${ctx}/jzmk/scoringFormula/form">计分方式添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="scoringFormula" action="${ctx}/jzmk/scoringFormula/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>名称</th>
				<th>公式</th>
				<shiro:hasPermission name="jzmk:scoringFormula:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="scoringFormula">
			<tr>
				<td><a href="${ctx}/jzmk/scoringFormula/form?id=${scoringFormula.id}">
					${scoringFormula.name}
				</a></td>
				<td>
					${scoringFormula.formula}
				</td>
				<shiro:hasPermission name="jzmk:scoringFormula:edit"><td>
    				<a href="${ctx}/jzmk/scoringFormula/form?id=${scoringFormula.id}">修改</a>
					<a href="${ctx}/jzmk/scoringFormula/delete?id=${scoringFormula.id}" onclick="return confirmx('确认要删除该计分方式吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>