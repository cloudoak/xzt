<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>来访者管理</title>
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
		<li class="active"><a href="${ctx}/ante/visitorInfo/">来访者列表</a></li>
		<%-- <shiro:hasPermission name="ante:visitorInfo:edit"><li><a href="${ctx}/ante/visitorInfo/form">来访者添加</a></li></shiro:hasPermission> --%>
	</ul>
	
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>来访者编号</th>
				<th>密码</th>
				<th>机构</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="visitorInfo">
			<tr>
				<td>
					<a href="${ctx}/ante/visitorInfo/form?id=${visitorInfo.id}">
						${visitorInfo.visitorNo}
					</a>
				</td>
				<td>
					123456
				</td>
				<td>
					${visitorInfo.orgId}
				</td>
				
				<td>
					<fmt:formatDate value="${visitorInfo.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<%-- <div class="pagination">${page}</div> --%>
</body>
</html>