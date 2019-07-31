<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<meta charset="utf-8">
	<meta name="decorator" content="default"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="keywords" content="${fns:getConfig('keywords')} ${sysConfig.schoolName } ">
    <meta name="description" content="${fns:getConfig('description')} ${sysConfig.schoolName }">
	<title>部门管理</title>
	
	<%@ include file="/WEB-INF/views/include/commoncss.jsp" %>
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
<body class="gray-bg">
	<%-- <ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/ante/department/list">部门管理列表</a></li>
		<shiro:hasPermission name="ante:department:edit"><li><a href="${ctx}/ante/department/form">部门管理添加</a></li></shiro:hasPermission>
	</ul> --%>
	<div class="wrapper wrapper-content animated fadeInRight">
        <div class="ibox float-e-margins">
            <div class="ibox-content">
                <div class="row row-lg">
                    <div class="col-sm-12">
                        <h4 class="example-title">部门管理列表</h4>
						<div class="example">
                   			<div class="btn-group " id="exampleTableEventsToolbar" role="group">
                                <shiro:hasPermission name="ante:department:edit">
									<a class="J_menuItem" href="${ctx}/ante/department/form">
										<button type="button" class="btn btn-outline btn-default">
	                                    	<i class="glyphicon glyphicon-plus text-navy" aria-hidden="false"></i>
		                                </button>
	                               	</a>
                               	</shiro:hasPermission>
                               	<a>
	                                <button type="button" class="btn btn-outline btn-default">
	                                    <i class="glyphicon glyphicon-edit text-navy" aria-hidden="false"></i>
	                                </button>
                               	</a>
                               	<a>
	                                <button type="button" class="btn btn-outline btn-default">
	                                    <i class="glyphicon glyphicon-trash text-navy" aria-hidden="false"></i>
	                                </button>
                               	</a>
                            </div>
							<sys:message content="${message}"/>
							<form id="searchForm" modelAttribute="department" action="${ctx}/ante/department/list" method="post" class="breadcrumb form-search">
								<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
								<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
								<div>
									<div>
										<label>部门名称：</label>
										<input id="deptName" name="deptName" htmlEscape="false" maxlength="50" class="input-medium"/>
										
										<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
									</div>
								</div>
							</form>
							<sys:message content="${message}"/>
							<table id="departmentTable" data-toggle="table" data-query-params="queryParams" data-mobile-responsive="true" data-height="400" data-pagination="true">
								<thead>
									<tr>
										<th data-checkbox="true" aria-hidden="true"></th>
                                    	<th data-field="id" data-visible="false"></th>
										<th data-field="deptName">部门名称</th>
										<th data-field="deptBrief">部门简介</th>
										<shiro:hasPermission name="ante:department:edit"><th>操作</th></shiro:hasPermission>
									</tr>
								</thead>
								<tbody>
								<c:forEach items="${page.list}" var="department">
									<tr>
										<td>${department.id}</td>
										<td>${department.id}</td>
										<td>
											${department.deptName}
										</td>
										<td>
											${department.deptBrief}
										</td>
										<shiro:hasPermission name="ante:department:edit"><td>
						    				<a href="${ctx}/ante/department/form?id=${department.id}">修改</a>
											<a href="${ctx}/ante/department/delete?id=${department.id}" onclick="return confirmx('确认要删除该部门管理吗？', this.href)">删除</a>
										</td></shiro:hasPermission>
									</tr>
								</c:forEach>
								</tbody>
							</table>
	<div class="pagination">${page}</div>
	
	<%@ include file="/WEB-INF/views/include/commonlistjs.jsp" %>
</body>
</html>