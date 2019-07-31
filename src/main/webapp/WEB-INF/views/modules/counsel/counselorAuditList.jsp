<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${sysConfig.schoolName } 咨询师自主注册审核管理</title>
    <meta name="keywords" content="${fns:getConfig('keywords')} ${sysConfig.schoolName } ">
    <meta name="description" content="${fns:getConfig('description')} ${sysConfig.schoolName }">
    <link rel="shortcut icon" href="${hplusStatic }favicon.ico"> 
   <%@ include file="/WEB-INF/views/include/commoncss.jsp" %>
</head>
<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="ibox float-e-margins">
            <div class="ibox-content">
                <div class="row row-lg">
                    <div class="col-sm-12">
                        <h4 class="example-title">咨询师自主注册审核列表</h4>
					<div class="example">
                  		<div class="btn-group " id="exampleTableEventsToolbar" role="group">
							<form:form id="searchForm" modelAttribute="counselor" action="${ctx}/counsel/counselor/" method="post" class="breadcrumb form-search">
								<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
								<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
								<!--
                                <button type="button" class="btn btn-outline btn-default">
                                    <shiro:hasPermission name="sys:office:edit">
									<a class="J_menuItem" href="${ctx}/counsel/counselor/form">
                                    <i class="glyphicon glyphicon-plus text-navy" aria-hidden="false"></i>
                                   	</a></shiro:hasPermission>									
                                </button>
								-->
                                <button type="button" class="btn btn-outline btn-default">
                                    <i class="glyphicon glyphicon-edit text-navy" aria-hidden="false"></i>
                                </button>
                                <button type="button" class="btn btn-outline btn-default">
                                    <i class="glyphicon glyphicon-trash text-navy" aria-hidden="false"></i>
                                </button>
                            </div>
						</form:form>
			<sys:message content="${message}"/>
	  <table id="exampleTableToolbar" data-toggle="table" data-query-params="queryParams" data-mobile-responsive="true" data-height="400" data-pagination="true" data-search="true">
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
							<td><a class="text-navy" href="${ctx}/counsel/counselor/form?id=${counselor.id}">${counselor.user.name}</a></td>	
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
								<c:choose>
				    				<c:when test="${counselor.status == 0}">
				    					<shiro:hasPermission name="counsel:counselor:edit">
										<a href="javascript:approvalSubmit('注册咨询师审核', ${counselor.id}, '${counselor.applyStatus}', '${counselor.status}');">注册咨询师审核</a>
				    					</shiro:hasPermission>
				    				</c:when>
				    				<c:when test="${counselor.status == 1}">
				    				注册咨询师审核通过
				    				</c:when>
				    				<c:otherwise>注册咨询师审核不通过</c:otherwise>
			    				</c:choose>
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
var approvalSubmit;
$(function() {
	approvalSubmit = function (title, counselorId, applyStatus, status){
		layui.use(['layer', 'form'], function(){
			var layer = layui.layer ,form = layui.form;
			var passable = (status === '1') ? 'selected = "selected"' : '', 
				reject = (status === '2') ? 'selected = "selected"' : '', 
				html = '<div class="ibox-content">'+
				'<div class="form-horizontal m-t">'+
	            '<div class="form-group">'+
	                '<label class="col-sm-3 control-label">审批状态：</label>'+
	                '<div class="col-sm-8">'+
	                    '<select id="status" required="required" class="form-control" name="status"><option value="1" '+passable+'>通过</option><option value="2" '+reject+'>驳回</option></select>'+
	                '</div>'+
	            '</div>'+
	            '</div>'+
	        '</div>';
			layer.open({
			    type: 1,
			    title: title,
			    shadeClose:true,
			    shade: 0.3,
			    area: ['450px', '215px'],
			    offset: 'c',
			    shift: 2,
			    content: html,
			    btn:['保存','取消'],
			    yes: function(index, layero){
			    	var status = $(layero.context).find("#status").val();
			    	var hrefLocation = "${ctx}/counsel/counselor/audit?id="+counselorId + "&applyStatus="+applyStatus+"&status="+status;
			    	window.location.href = hrefLocation;
			    },btn2: function(index, layero){
			       
			    }
			});
		});
	};
});
function page(n,s){
	$("#pageNo").val(n);
	$("#pageSize").val(s);
	$("#searchForm").submit();
  	return false;
}
</script>
</html>