<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>幸福镜子管理</title>
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
		<li class="active"><a href="${ctx}/ante/happyMirror/">幸福镜子列表</a></li>
		<%-- <shiro:hasPermission name="ante:happyMirror:edit"><li><a href="${ctx}/ante/happyMirror/form">幸福镜子添加</a></li></shiro:hasPermission> --%>
	</ul>
	<form:form id="searchForm" modelAttribute="happyMirror" action="${ctx}/ante/happyMirror/list" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<!-- <ul class="ul-form">
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul> -->
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>测试人</th>
				<th>身份类型</th>
				<th>分数</th>
				<th>测试时间</th>
				<th>机构</th>
				<shiro:hasPermission name="ante:happyMirror:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="happyMirror">
			<tr>
				<td>
					<a href="${ctx}/ante/happyMirror/form?id=${happyMirror.id}">${happyMirror.testName}</a>
				</td>
				<td>
					<c:if test="${happyMirror.testIdType=='0'}">平台管理员</c:if>
					<c:if test="${happyMirror.testIdType=='1'}">机构默认管理员</c:if>
					<c:if test="${happyMirror.testIdType=='2'}">咨询师</c:if>
					<c:if test="${happyMirror.testIdType=='3'}">家属</c:if>
					<c:if test="${happyMirror.testIdType=='4'}">来访者</c:if>
				</td>
				<td>
					${happyMirror.testScore}
				</td>
				<td>
					<fmt:formatDate value="${happyMirror.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<%-- ${happyMirror.orgId} --%>
					<c:forEach items="${offices.list}" var="office">
						${happyMirror.orgId==office.id?office.name:''}
					</c:forEach>
				</td>
				<shiro:hasPermission name="ante:happyMirror:edit"><td>
    				<a href="${ctx}/ante/happyMirror/form?id=${happyMirror.id}">查看</a>
					<a href="${ctx}/ante/happyMirror/delete?id=${happyMirror.id}" onclick="return confirmx('确认要删除该幸福镜子吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>