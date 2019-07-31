<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${sysConfig.schoolName } --老师管理</title>
    <meta name="keywords" content="${fns:getConfig('keywords')} ${sysConfig.schoolName } ">
    <meta name="description" content="${fns:getConfig('description')} ${sysConfig.schoolName }">
	
	<%@ include file="/WEB-INF/views/include/commoncss.jsp" %>
	<!--
    <link rel="shortcut icon" href="${hplusStatic }favicon.ico"> 
    <link href="${hplusStatic }/css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
    <link href="${hplusStatic }/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
    <link href="${hplusStatic }/css/plugins/bootstrap-table/bootstrap-table.min.css" rel="stylesheet">
    <link href="${hplusStatic }/css/animate.min.css" rel="stylesheet">
    <link href="${hplusStatic }/css/style.min862f.css?v=4.1.0" rel="stylesheet">
	-->
</head>	

<!--
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/archive/counselor/">老师档案查询</a></li>
		<shiro:hasPermission name="archive:counselor:edit"><li><a href="${ctx}/archive/counselor/form">老师档案添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="counselor" action="${ctx}/archive/counselor/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>教师姓名：</label>
				<form:input path="userName" htmlEscape="false" maxlength="50" class="input-medium"/>
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
                        <h4 class="example-title">老师档案</h4>
                        <div class="example">
                    		<div class="btn-group " id="exampleTableEventsToolbar" role="group">
                                
								<button type="button" class="btn btn-outline btn-default">
                                    <shiro:hasPermission name="archive:counselor:edit">
										<a class="J_menuItem" href="${ctx}/archive/counselor/form">
                                    <i class="glyphicon glyphicon-plus text-navy" aria-hidden="false"></i>
                                   	</a></shiro:hasPermission>
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
   										<th data-field="name" >教师姓名</th>
										<th data-field="sex">性别 </th>
										<th data-field="age">年龄</th>										
										<th data-field="isCheck">审核状态</th>
										<th data-field="createDate">创建时间</th>											
                                        <th>操作</th>
                                    </tr>
                                </thead>
                                <c:forEach items="${page.list}" var="counselor">									
									<tr>
										<td data-field="id" data-checkbox="true" aria-hidden="true"></td>
										<td><a class="text-navy" href="${ctx}/archive/counselor/form?id=${counselor.id}">${counselor.userName}</a></td>
										<td>${fns:getDictLabel(counselor.sex, 'sex', '')}</td>
										<td>${counselor.age}</a></td>
										<td>${fns:getDictLabel(counselor.isCheck, 'yes_no', '')}</td>
										<td><fmt:formatDate value="${counselor.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
										
										<td>
										<!--
											<a href="#" class="text-info">
												<i class="fa fa-pie-chart" aria-hidden="true"></i>
											</a>&nbsp;
										
											<a class="text-navy" href="${ctx}/archive/counselor/form?id=${counselor.id}">
												<i class="glyphicon glyphicon-edit" aria-hidden="true"></i>
											</a>&nbsp;
											
											<a  class="text-danger" href="${ctx}/archive/counselor/delete?id=${counselor.id}" onclick="return confirmx('确认要删除吗？', this.href)">
												<i class="glyphicon glyphicon-trash" aria-hidden="true"></i>
											</a>
											-->
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
				<th>教师姓名</th>
				<th>性别 </th>
				<th>年龄</th>
				<th>出生年月</th>
				<th>审核状态</th>
				<th>创建时间</th>
				<shiro:hasPermission name="archive:counselor:view"><th>操作</th></shiro:hasPermission>
				<shiro:hasPermission name="archive:counselor:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="counselor">
			<tr>
				<td><a href="${ctx}/archive/counselor/form?id=${counselor.id}">
					${counselor.userName}
				</a></td>
				<td>
					${fns:getDictLabel(counselor.sex, 'sex', '')}
				</td>
				
				<td>
					${counselor.age}
				</td>
				<td>
					<fmt:formatDate value="${counselor.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${fns:getDictLabel(counselor.isCheck, 'yes_no', '')}
				</td>
				<td>
					<fmt:formatDate value="${counselor.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="archive:counselor:edit"><td>
    				<a href="${ctx}/archive/counselor/form?id=${counselor.id}">修改</a>
					<a href="${ctx}/archive/counselor/delete?id=${counselor.id}" onclick="return confirmx('确认要删除该老师档案吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
				<shiro:hasPermission name="archive:counselor:view"><td>
    				<a href="${ctx}/archive/counselor/form?id=${counselor.id}">查看</a>
					
					<a href="${ctx}/archive/counselor/delete?id=${counselor.id}" onclick="return confirmx('确认要删除该老师档案吗？', this.href)">删除</a>
				
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