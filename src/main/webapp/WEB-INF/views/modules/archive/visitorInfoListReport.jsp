<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<!-- Mirrored from www.zi-han.net/theme/hplus/table_bootstrap.html by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 20 Jan 2016 14:20:03 GMT -->
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${sysConfig.schoolName } 机构--学生档案管理</title>
    <meta name="keywords" content="${fns:getConfig('keywords')} ${sysConfig.schoolName } ">
    <meta name="description" content="${fns:getConfig('description')} ${sysConfig.schoolName }">
	
	<%@ include file="/WEB-INF/views/include/commoncss.jsp" %>
	
</head>
<!--
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/archive/visitorInfo/">学生档案列表</a></li>
		<shiro:hasPermission name="archive:visitorInfo:edit"><li><a href="${ctx}/archive/visitorInfo/form">学生档案添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="visitorInfo" action="${ctx}/archive/visitorInfo/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>来访者编号：</label>
				<form:input path="visitorNo" htmlEscape="false" maxlength="30" class="input-medium"/>
			</li>
			<li><label>姓名：</label>
				<form:input path="realName" htmlEscape="false" maxlength="30" class="input-medium"/>
			</li>
			<li><label>电话号码：</label>
				<form:input path="phoneNum" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	
	
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>来访者编号</th>
				<th>姓名</th>
				<th>性别</th>
				<th>电话号码</th>
				<th>机构</th>
				<th>更新时间</th>
				<shiro:hasPermission name="archive:visitorInfo:view"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="visitorInfo">
			<tr>
				<td><a href="${ctx}/archive/visitorInfo/form?id=${visitorInfo.id}">
					${visitorInfo.visitorNo}
				</a></td>
				<td>
					${visitorInfo.realName}
				</td>
				<td>
					${fns:getDictLabel(visitorInfo.sex, 'sex', '')}
				</td>
				<td>
					${visitorInfo.phoneNum}
				</td>
				<td>
					${visitorInfo.orgId}
				</td>
				<td>
					<fmt:formatDate value="${visitorInfo.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="archive:visitorInfo:view"><td>
    				<a href="${ctx}/archive/visitorInfo/form?id=${visitorInfo.id}">查看</a>					
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
                        <h4 class="example-title">学生档案列表</h4>
                        <div class="example">
                    		<div class="btn-group " id="exampleTableEventsToolbar" role="group">
                                
								<button type="button" class="btn btn-outline btn-default">
                                    <shiro:hasPermission name="archive:visitorInfo:edit">
										<a class="J_menuItem" href="${ctx}/archive/visitorInfo/form">
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
										<th data-field="visitorNo">来访者编号</th>
										<th data-field="realName">姓名</th>
										<th data-field="sex">性别</th>
										<th data-field="phoneNum">电话号码</th>										
										<th data-field="updateDate">更新时间</th>										
                                        <th>操作</th>
                                    </tr>
                                </thead>
                                <c:forEach items="${page.list}" var="visitorInfo">									
									<tr>
										<td data-field="id" data-checkbox="true" aria-hidden="true"></td>
										<td><a class="text-navy" href="${ctx}/archive/visitorInfo/form?id=${visitorInfo.id}">${visitorInfo.visitorNo}</a></td>
										<td>
											${visitorInfo.realName}
										</td>
										<td>
											${fns:getDictLabel(visitorInfo.sex, 'sex', '')}
										</td>
										<td>
											${visitorInfo.phoneNum}
										</td>
										<td>
											<fmt:formatDate value="${visitorInfo.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
										</td>
										<td>
										<!--
											<a href="#" class="text-info">
												<i class="fa fa-pie-chart" aria-hidden="true"></i>
											</a>&nbsp;
										-->
											<a class="text-navy" href="${ctx}/archive/visitorInfo/form?id=${visitorInfo.id}">
												<i class="glyphicon glyphicon-edit" aria-hidden="true"></i>
											</a>&nbsp;											
											<a  class="text-danger" href="${ctx}/archive/visitorInfo/delete?id=${visitorInfo.id}" onclick="return confirmx('确认要删除吗？', this.href)">
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