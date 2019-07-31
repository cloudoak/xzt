<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<!-- Mirrored from www.zi-han.net/theme/hplus/table_bootstrap.html by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 20 Jan 2016 14:20:03 GMT -->
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${sysConfig.schoolName } 机构--咨询报表管理</title>
    <meta name="keywords" content="${fns:getConfig('keywords')} ${sysConfig.schoolName } ">
    <meta name="description" content="${fns:getConfig('description')} ${sysConfig.schoolName }">
	
	<%@ include file="/WEB-INF/views/include/commoncss.jsp" %>
	
</head>

<!--
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/counsel/counselReport/">咨询报表列表</a></li>
		<shiro:hasPermission name="counsel:counselReport:edit"><li><a href="${ctx}/counsel/counselReport/form">咨询报表添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="counselReport" action="${ctx}/counsel/counselReport/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>编号：</label>
				<form:input path="sn" htmlEscape="false" maxlength="16" class="input-medium"/>
			</li>
			<li><label>标题：</label>
				<form:input path="title" htmlEscape="false" maxlength="32" class="input-medium"/>
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
                        <h4 class="example-title">咨询报表列表</h4>
                        <div class="example">
                    		<div class="btn-group " id="exampleTableEventsToolbar" role="group">
                                
								<button type="button" class="btn btn-outline btn-default">
                                    <shiro:hasPermission name="counsel:counselReport:edit">
										<a class="J_menuItem" href="${ctx}/counsel/counselReport/form">
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
	<!--
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>编号</th>
				<th>标题</th>
				<th>创建时间</th>
				<shiro:hasPermission name="counsel:counselReport:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="counselReport">
			<tr>
				<td><a href="${ctx}/counsel/counselReport/form?id=${counselReport.id}">
					${counselReport.sn}
				</a></td>
				<td>
					${counselReport.title}
				</td>
				<td>
					<fmt:formatDate value="${counselReport.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="counsel:counselReport:edit"><td>
    				<a href="${ctx}/counsel/counselReport/form?id=${counselReport.id}">修改</a>
					<a href="${ctx}/counsel/counselReport/delete?id=${counselReport.id}" onclick="return confirmx('确认要删除该咨询报表吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	-->
	
		<table id="exampleTableToolbar" data-toggle="table" data-query-params="queryParams" data-mobile-responsive="true" data-height="400" data-pagination="true" data-search="true">
                                <thead>
                                    <tr>
                                        <th data-field="id" data-checkbox="true" aria-hidden="true"></th>
                                        <th data-field="sn">编号</th>
                                        <th data-field="title">标题</th>
                                        <th data-field="createName">制作人</th>
										<th data-field="beginTime">开始时间</th>
										<th data-field="endTime">结束时间</th>
                                        <th>操作</th>
                                    </tr>
                                </thead>
                                <c:forEach items="${page.list}" var="counselorReport">									
									<tr>
										<td data-field="id" data-checkbox="true" aria-hidden="true"></td>
										<td><a class="text-navy" href="${ctx}/counsel/counselorUserBook/form?id=${counselorUserBook.id}">${counselorReport.sn}</a></td>
										<td>${counselorReport.title}</td>
										<td>${counselorReport.createName}</td>
										<td><fmt:formatDate value="${counselorReport.beginTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
										<td><fmt:formatDate value="${counselorReport.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
										<td>
											<a href="#" class="text-info">
												<i class="fa fa-pie-chart" aria-hidden="true"></i>
											</a>&nbsp;
											<a class="text-navy" href="${ctx}/counsel/counselReport/form?id=${counselorReport.id}">
												<i class="glyphicon glyphicon-edit" aria-hidden="true"></i>
											</a>&nbsp;
											
											<a class="text-danger" href="${ctx}/counsel/counselReport/delete?id=${counselReport.id}" onclick="return confirmx('确认要删除该咨询报表吗？', this.href)">
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
	
	<!-- <div class="pagination">${page}</div> -->
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