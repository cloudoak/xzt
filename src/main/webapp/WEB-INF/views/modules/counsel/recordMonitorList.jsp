<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<!-- Mirrored from www.zi-han.net/theme/hplus/table_bootstrap.html by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 20 Jan 2016 14:20:03 GMT -->
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${sysConfig.schoolName }-- 咨询督导审核</title>
    <meta name="keywords" content="${fns:getConfig('keywords')} ${sysConfig.schoolName } ">
    <meta name="description" content="${fns:getConfig('description')} ${sysConfig.schoolName }">

    <link rel="shortcut icon" href="${hplusStatic }favicon.ico"> 
    <link href="${hplusStatic }/css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
    <link href="${hplusStatic }/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
    <link href="${hplusStatic }/css/plugins/bootstrap-table/bootstrap-table.min.css" rel="stylesheet">
    <link href="${hplusStatic }/css/animate.min.css" rel="stylesheet">
    <link href="${hplusStatic }/css/style.min862f.css?v=4.1.0" rel="stylesheet">
	
</head>

<!--
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/counsel/recordMonitor/">咨询督导列表</a></li>
		<shiro:hasPermission name="counsel:recordMonitor:edit"><li><a href="${ctx}/counsel/recordMonitor/form">咨询督导添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="recordMonitor" action="${ctx}/counsel/recordMonitor/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>咨询记录标识：</label>
				<form:input path="recordId" htmlEscape="false" maxlength="11" class="input-medium"/>
			</li>
			<li><label>督导咨询师标识：</label>
				<form:input path="counselorId" htmlEscape="false" maxlength="11" class="input-medium"/>
			</li>
			<li><label>督导咨询师名称：</label>
				<form:input path="counselorName" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			<li><label>督导状态：</label>
				<form:select path="monitorStatus" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('monitor_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>创建者：</label>
				<form:input path="createBy" htmlEscape="false" maxlength="11" class="input-medium"/>
			</li>
			<li><label>分校id：</label>
				<form:input path="orgId" htmlEscape="false" maxlength="11" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	
	
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>督导咨询师标识</th>
				<th>督导咨询师名称</th>
				<shiro:hasPermission name="counsel:recordMonitor:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="recordMonitor">
			<tr>
				<td><a href="${ctx}/counsel/recordMonitor/form?id=${recordMonitor.id}">
					${recordMonitor.counselorId}
				</a></td>
				<td>
					${recordMonitor.counselorName}
				</td>
				<shiro:hasPermission name="counsel:recordMonitor:edit"><td>
    				<a href="${ctx}/counsel/recordMonitor/form?id=${recordMonitor.id}">修改</a>
					<a href="${ctx}/counsel/recordMonitor/delete?id=${recordMonitor.id}" onclick="return confirmx('确认要删除该咨询督导吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	-->
	
	
<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="ibox float-e-margins">
            <div class="ibox-content">
                <div class="row row-lg">
                    <div class="col-sm-12">
                        <h4 class="example-title">咨询督导列表</h4>
                        <div class="example">
                    		<div class="btn-group " id="exampleTableEventsToolbar" role="group">
                                <button type="button" class="btn btn-outline btn-default">
                                    <shiro:hasPermission name="counsel:recordMonitor:edit">
									<a class="J_menuItem" href="${ctx}/counsel/recordMonitor/form">
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
                                        <th data-field="counselorName">咨询师名称</th>
                                        <th data-field="status">督导咨询状态</th>
										 <th data-field="content">内容</th>
                                        <th>操作</th>
                                    </tr>
                                </thead>
                                <c:forEach items="${page.list}" var="recordMonitor">
									
									<tr>
										<td data-field="id" data-checkbox="true" aria-hidden="true"></td>
										<td><a class="text-navy" href="${ctx}/counsel/recordMonitor/form?id=${recordMonitor.id}">${recordMonitor.counselorName}</a></td>
										<td>${fns:getDictLabel(recordMonitor.monitorStatus, 'monitor_status', '')}</td>
										<td title="${recordMonitor.content}"> 
											${fns:abbr(recordMonitor.content,30)}</td>
										<td>
											<!--
											<a href="#" class="text-info">
												<i class="fa fa-pie-chart" aria-hidden="true"></i>
											</a>&nbsp;
											-->
											<a class="text-navy" href="${ctx}/counsel/recordMonitor/form?id=${recordMonitor.id}">
												<i class="glyphicon glyphicon-edit" aria-hidden="true"></i>
											</a>&nbsp;
											<a class="text-danger" href="${ctx}/counsel/recordMonitor/delete?id=${recordMonitor.id}" onclick="return confirmx('确认要删除该督导咨询吗？', this.href)">
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