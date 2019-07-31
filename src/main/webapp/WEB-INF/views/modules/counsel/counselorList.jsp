<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${sysConfig.schoolName } 咨询师管理</title>
    <meta name="keywords" content="${fns:getConfig('keywords')} ${sysConfig.schoolName } ">
    <meta name="description" content="${fns:getConfig('description')} ${sysConfig.schoolName }">
	<%@ include file="/WEB-INF/views/include/commoncss.jsp" %>
</head>
<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="ibox float-e-margins">
            <div class="ibox-content">
                <div class="row row-lg">
                    <div class="col-sm-12">
                        <h4 class="example-title">咨询师列表</h4>
						<div class="example">
                   			<div class="btn-group " id="exampleTableEventsToolbar" role="group">
								<form:form id="searchForm" modelAttribute="counselor" action="${ctx}/counsel/counselor/" method="post" class="breadcrumb form-search">
									<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
									<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
	                                <button type="button" class="btn btn-outline btn-default">
	                                    <shiro:hasPermission name="sys:office:edit">
										<a class="J_menuItem" href="${ctx}/counsel/counselor/form">
	                                    <i class="glyphicon glyphicon-plus text-navy" aria-hidden="false"></i>
	                                   	</a></shiro:hasPermission>
	                                </button>
	                                <button type="button" class="btn btn-outline btn-default">
	                                    <i class="glyphicon glyphicon-edit text-navy" aria-hidden="false"></i>
	                                </button>
	                                <button type="button" class="btn btn-outline btn-default">
	                                    <i class="glyphicon glyphicon-trash text-navy" aria-hidden="false"></i>
	                                </button>
	                            </form:form>
                            </div>
						<sys:message content="${message}"/>
	  					<table id="exampleTableToolbar" data-toggle="table" data-query-params="queryParams" 
	  						data-mobile-responsive="true" data-height="400" data-pagination="true" data-search="true">
                                <thead>
                                    <tr>
                                        <th data-field="id" data-checkbox="true" aria-hidden="true"></th>
                                        <th data-field="org.name">所属机构</th>											
                                        <th data-field="user.name">姓名</th>
										<th data-field="instro">描述</th>									
                                        <th data-field="applyStatus">认证状态</th>
										<th data-field="indexFlag">是否推荐首页</th>
										<th data-field="insideFlag">是否内部</th>
                                        <shiro:hasPermission name="counsel:counselor:edit"><th>操作</th></shiro:hasPermission>
                                    </tr>
                                </thead>
							<c:forEach items="${page.list}" var="counselor">	
							<tr>
							<td data-field="id" data-checkbox="true" aria-hidden="true"></td>
							<td>${counselor.org.name}</td>
							<td><a class="text-navy" href="${ctx}/counsel/counselor/detail?id=${counselor.id}">${counselor.user.name}</a></td>	
							<td title="${counselor.instro}">${fns:abbr(counselor.instro,30)}</td>
							<td>
							<c:choose>
							<c:when test="${ counselor.applyStatus == 2 }">认证</c:when>
							<c:otherwise>未认证</c:otherwise>
							</c:choose>
							</td>
							<td>
							<c:choose>
							<c:when test="${ counselor.indexFlag == 1 }">是</c:when>
							<c:otherwise>否</c:otherwise>
							</c:choose>
							</td>
							<td>
							<c:choose>
							<c:when test="${ counselor.insideFlag == 1 }">否</c:when>
							<c:otherwise>是</c:otherwise>
							</c:choose>
							</td>
							<td>
								<shiro:hasPermission name="counsel:counselor:edit">
								<a class="text-navy" href="${ctx}/counsel/counselor/form?id=${counselor.id}">
									<i class="glyphicon glyphicon-edit" aria-hidden="true"></i>
								</a>&nbsp;
								<a href="${ctx}/counsel/counselor/delete?id=${counselor.id}" class="text-danger" onclick="return confirmx('确认要删除该咨询师吗？', this.href)">
									<i class="glyphicon glyphicon-trash" aria-hidden="true"></i>
								</a>
								</shiro:hasPermission>
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
	$(function() {
		
	});
	function page(n,s){
		$("#pageNo").val(n);
		$("#pageSize").val(s);
		$("#searchForm").submit();
       	return false;
       }
</script>
</html>