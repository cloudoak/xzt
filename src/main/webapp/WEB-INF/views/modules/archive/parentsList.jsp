<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${sysConfig.schoolName } 机构--家长档案管理</title>
    <meta name="keywords" content="${fns:getConfig('keywords')} ${sysConfig.schoolName } ">
    <meta name="description" content="${fns:getConfig('description')} ${sysConfig.schoolName }">
	
	<%@ include file="/WEB-INF/views/include/commoncss.jsp" %>
</head>
<!--
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/archive/parents/">家长档案列表</a></li>
		<shiro:hasPermission name="archive:parents:edit"><li><a href="${ctx}/archive/parents/form">家长档案添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="parents" action="${ctx}/archive/parents/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>家长编号：</label>
				<form:input path="parentNo" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li><label>家长姓名：</label>
				<form:input path="name" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li><label>学生编号：</label>
				<form:input path="studentCode" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	-->
	
	
	<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="ibox float-e-margins">
            <div class="ibox-content">
                <div class="row row-lg">
                    <div class="col-sm-12">
                        <h4 class="example-title">家长档案列表</h4>
                        <div class="example">
                    		<div class="btn-group " id="exampleTableEventsToolbar" role="group">
                                
								<button type="button" class="btn btn-outline btn-default">                                   
                                    <i class="glyphicon glyphicon-plus text-navy" aria-hidden="false"></i>
                                </button>
								
                                <button type="button" class="btn btn-outline btn-default">
                                    <i class="glyphicon glyphicon-edit text-navy" aria-hidden="false"></i>
                                </button>
                                <button type="button" class="btn btn-outline btn-default">
                                    <i class="glyphicon glyphicon-trash text-navy" aria-hidden="false"></i>
                                </button>
                            </div>
	
	<sys:message content="${message}"/>
	<table id="exampleTableToolbar" data-toggle="table" data-query-params="queryParams" data-mobile-responsive="true" data-height="400" data-pagination="true" data-search="true">
                                <thead>
                                    <tr>
                                        <th data-field="id" data-checkbox="true" aria-hidden="true"></th>
      									<th data-field="parentNo">家长编号</th>
										<th data-field="name">家长姓名</th>
										<th data-field="gender">性别</th>
										<th data-field="age">年龄</th>
										<th data-field="studentCode">学生编号</th>
										<th data-field="updateDate">修改日期</th>										
                                        <th>操作</th>
                                    </tr>
                                </thead>
                                <c:forEach items="${page.list}" var="parents">									
									<tr>
										<td data-field="id" data-checkbox="true" aria-hidden="true"></td>
										<td><a class="text-navy" href="${ctx}/archive/parents/form?id=${parents.id}">${parents.parentNo}</a></td>										
										<td>${parents.name}</td>
										<td>${fns:getDictLabel(parents.gender, 'sex', '')}</td>
										<td>${parents.age}</td>
										<td>${parents.studentCode}</td>
										<td><fmt:formatDate value="${parents.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
										<td>
										<!--
											<a href="#" class="text-info">
												<i class="fa fa-pie-chart" aria-hidden="true"></i>
											</a>&nbsp;
										-->
											<a class="text-navy" href="${ctx}/archive/parents/form?id=${parents.id}">
												<i class="glyphicon glyphicon-edit" aria-hidden="true"></i>
											</a>&nbsp;
											
											<a  class="text-danger" href="${ctx}/archive/parents/delete?id=${parents.id}" onclick="return confirmx('确认要删除吗？', this.href)">
												<i class="glyphicon glyphicon-trash" aria-hidden="true"></i>
											</a>
											
										</td>
									</tr>
									
								</c:forEach>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- End Panel Basic -->
    </div>
	
	<!--
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>家长编号</th>
				<th>家长姓名</th>
				<th>性别</th>
				<th>年龄</th>
				<th>学生编号</th>
				<th>修改日期</th>
				<shiro:hasPermission name="archive:parents:view"><th>操作</th></shiro:hasPermission>				
				<shiro:hasPermission name="archive:parents:edit"><th>操作</th></shiro:hasPermission>				
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="parents">
			<tr>
				<td><a href="${ctx}/archive/parents/form?id=${parents.id}">
					${parents.parentNo}
				</a></td>
				<td>
					${parents.name}
				</td>
				<td>
					${fns:getDictLabel(parents.gender, 'sex', '')}
				</td>
				<td>
					${parents.age}
				</td>
				<td>
					${parents.studentCode}
				</td>
				<td>
					<fmt:formatDate value="${parents.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>				
				<shiro:hasPermission name="archive:parents:edit"><td>
    				<a href="${ctx}/archive/parents/form?id=${parents.id}">修改</a>
					<a href="${ctx}/archive/parents/delete?id=${parents.id}" onclick="return confirmx('确认要删除该家长档案吗？', this.href)">删除</a>
				</td></shiro:hasPermission>				
				<shiro:hasPermission name="archive:parents:view"><td>
    				<a href="${ctx}/archive/parents/form?id=${parents.id}">查看</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	-->
</body>

<%@ include file="/WEB-INF/views/include/commonlistjs.jsp" %>

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
	
</html>