<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${sysConfig.schoolName }-- 排班管理</title>
    <meta name="keywords" content="${fns:getConfig('keywords')} ${sysConfig.schoolName } ">
    <meta name="description" content="${fns:getConfig('description')} ${sysConfig.schoolName }">
    <link rel="shortcut icon" href="${hplusStatic }favicon.ico"> 
    <link href="${hplusStatic }/css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
    <link href="${hplusStatic }/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
    <link href="${hplusStatic }/css/plugins/bootstrap-table/bootstrap-table.min.css" rel="stylesheet">
    <link href="${hplusStatic }/css/animate.min.css" rel="stylesheet">
    <link href="${hplusStatic }/css/style.min862f.css?v=4.1.0" rel="stylesheet">
</head>
<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="ibox float-e-margins">
            <div class="ibox-content">
                <div class="row row-lg">
                    <div class="col-sm-12">
                        <h4 class="example-title">排班管理列表</h4>
                        <div class="example">
                    		<div class="btn-group " id="exampleTableEventsToolbar" role="group">
                                <button type="button" class="btn btn-outline btn-default">
                                    <shiro:hasPermission name="counsel:counselRoom:edit"><a class="J_menuItem" href="${ctx}/counsel/counselRoom/form">
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
<th>&nbsp;</th>										
<c:forEach items="${dayWeekList}" var ="dayweek" >
<th>${dayweek}</th>										
</c:forEach>
</tr>
</thead>
<c:forEach items="${conselorScheduleMap}" var="counselorSchedule">
<tr>
	<td>${counselorSchedule.key}</td>
<c:forEach items="${counselorSchedule.value}" var = "cs">
<td>
<c:if test="${null != cs.id}">
<fmt:formatDate value="${cs.beginTime}" pattern="HH:mm:ss"/>
-<fmt:formatDate value="${cs.endTime}" pattern="HH:mm:ss"/>
</c:if>
</td>
</c:forEach>
	
	<td data-field="id" data-checkbox="true" aria-hidden="true"></td>									
	
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