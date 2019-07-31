<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${sysConfig.schoolName } 咨询师管理--咨询师类型管理</title>
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
                        <h4 class="example-title">咨询师类型列表</h4>
						<div class="example">
                   			<div class="btn-group " id="exampleTableEventsToolbar" role="group">
                                <shiro:hasPermission name="ante:counselorType:edit">
									<a class="J_menuItem" href="${ctx}/ante/counselorType/form">
										<button type="button" class="btn btn-outline btn-default">
	                                    	<i class="glyphicon glyphicon-plus text-navy" aria-hidden="false"></i>
		                                </button>
	                               	</a>
                               	</shiro:hasPermission>
                               <!-- 	<a>
	                                <button type="button" class="btn btn-outline btn-default">
	                                    <i class="glyphicon glyphicon-edit text-navy" aria-hidden="false"></i>
	                                </button>
                               	</a> -->
                               	<a>
	                                <button type="button" class="btn btn-outline btn-default">
	                                    <i class="glyphicon glyphicon-trash text-navy" aria-hidden="false"></i>
	                                </button>
                               	</a>
                            </div>
							<sys:message content="${message}"/>
							<form id="searchForm" action="${ctx}/ante/counselorType/list" method="post" class="form-inline">
								<div class="form-group">
								<label>部门：</label>
								<select name="id" id="id" class="form-control">
									<option value="">--请选择--</option>
									<c:forEach items="${departments.list}" var="department">
										<option value="${department.id}" ${department.id==column.id?'selected':''} title="${department.id}">${department.deptName}</option>
									</c:forEach>
								</select>
								</div>
								<div class="form-group">
								<label>咨询师类型：</label>
								<select name="id" id="id" class="form-control">
									<option value="">--请选择--</option>
									<c:forEach items="${page.list}" var="counselorType">
										<option value="${counselorType.id}" ${counselorType.id==column.id?'selected':''} title="${counselorType.typeName}">${counselorType.typeName}</option>
									</c:forEach>
								</select>
								</div>
								<div class="form-group">
								<button id="btnQuery" class="btn btn-primary" onclick="">查询</button>
								</div>
								<%-- <label>机构：</label>
								<select name="orgId" id="orgId" class="required input-mini" style="width:180px;">
									<option value="">--请选择--</option>
									<c:forEach items="${offices}" var="office">
										<option value="${office.id}">${office.name}</option>
									</c:forEach>
								</select> --%>
							</form>
							<table id="exampleTableToolbar" data-toggle="table" data-query-params="queryParams" data-mobile-responsive="true" data-height="400" data-pagination="true" data-search="false">
								<thead>
									<tr>
										<th>咨询师类型</th>
										<th>类型描述</th>
										<!-- <th>机构</th> -->
										<shiro:hasPermission name="ante:counselorType:edit"><th>操作</th></shiro:hasPermission>
									</tr>
								</thead>
								<c:forEach items="${page.list}" var="counselorType">
									<tr>
										<td><a class="text-navy" href="${ctx}/ante/counselorType/view?id=${counselorType.id}">${counselorType.typeName}</a></td>
										<td>${counselorType.comment}</td>
										<%-- <td>
											<c:forEach items="${offices}" var="office">
												<c:if test="${counselorType.orgId==office.id}">${office.name}</c:if>
											</c:forEach>
										</td> --%>
										<shiro:hasPermission name="ante:counselorType:edit"><td>
						    				<a class="text-navy" href="${ctx}/ante/counselorType/form?id=${counselorType.id}">
												<i class="glyphicon glyphicon-edit" aria-hidden="true" title="修改"></i>
											</a>&nbsp;&nbsp;
						    				<a href="#" class="text-danger" onclick="delOne('${counselorType.id}')" title="删除">
												<i class="glyphicon glyphicon-trash" aria-hidden="true"></i>
											</a>&nbsp;&nbsp;
						    				<a class="text-navy" href="${ctx}/ante/counselorType/form?id=${counselorType.id}">设置权限</a>
										</td></shiro:hasPermission>
									</tr>
								</c:forEach>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="/WEB-INF/views/include/commonlistjs.jsp" %>
	<script type="text/javascript">
		/**
		 * 删除一条
		 */    
		function delOne(id){
	        layer.confirm('确认要删除吗？', {
	            btn: ['确认','取消'], //按钮
	            title:'删除确认',
	            shade: false //不显示遮罩
	        }, function(){
	            document.location.href="${ctx}/ante/counselorType/delete?id="+id;
	            layer.msg('删除成功！', {icon: 6});
	        }, function(){
	            layer.msg('已取消', {icon: 1});
	        });
	    }
	</script>
</body>
</html>