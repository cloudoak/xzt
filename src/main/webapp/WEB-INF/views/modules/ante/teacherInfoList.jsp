<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${sysConfig.schoolName } 咨询师管理--机构设置</title>
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
						<h4 class="example-title">咨询师信息列表</h4>
						<div class="example">
							<div class="btn-group " id="exampleTableEventsToolbar" role="group">
								<a class="J_menuItem" href="${ctx}/ante/teacherInfo/form">
									<button type="button" class="btn btn-outline btn-default">
										<shiro:hasPermission name="ante:teacherInfo:view">
												<i class="glyphicon glyphicon-plus text-navy" aria-hidden="false"></i>
										</shiro:hasPermission>
									</button>
								</a>
								<a>
									<button type="button" class="btn btn-outline btn-default">
										<i class="glyphicon glyphicon-edit text-navy" aria-hidden="false"></i>
									</button>
								</a>
								<a>
									<button type="button" onclick="delAll()" class="btn btn-outline btn-default">
										<i class="glyphicon glyphicon-trash text-navy" aria-hidden="false"></i>
									</button>
								</a>
							</div>
							<form id="searchForm" class="form-inline"  action="${ctx}/ante/teacherInfo/list" method="post">
								<div class="form-group">
		                            <label for="name">姓名：</label>
									<input id="name" name="user.name" maxlength="16" class="form-control" value="${counselor.user.name }" type="text" />
		                        </div>
		                        <div class="form-group">
		                            <label for="counselorType">咨询师类型：</label>
		                            <select name="counselorType" id="counselorType" class="form-control">
										<option value="">--请选择--</option>
										<c:forEach items="${counselorTypes.list}" var="counselorType">
											<option value="${counselorType.id}" ${counselorType.id==column.id?'selected':''} title="${counselorType.typeName}">${counselorType.typeName}</option>
										</c:forEach>
									</select>								
		                        </div>
		                        <div class="form-group">
		                            <label for="deptId">组织：</label>
		                            <select id="deptId" name="deptId" class="form-control">
										<option value="">--请选择--</option>
										<c:forEach items="${departments.list}" var="department">
											<option value="${department.id}" ${department.id==column.id?'selected':''} title="${department.id}">${department.deptName}</option>
										</c:forEach>
									</select>							
		                        </div>	
		                        <div class="form-group">
									<shiro:hasPermission name="ante:teacherInfo:edit">
									<input id="btnQuery" class="btn btn-primary" type="submit" value="查询"/>&nbsp;
									</shiro:hasPermission>
			                    </div>
							</form>
							<table id="exampleTableToolbar" data-toggle="table" data-query-params="queryParams" data-mobile-responsive="true" data-height="400" data-pagination="true" data-search="false">
								<thead>
									<tr>
										<th data-checkbox="true" aria-hidden="true"></th>
                                    	<th data-field="id" data-visible="false"></th>
										<th data-field="user.name">姓名</th>
										<th data-field="counselType.typeName">类型</th>
										<th data-field="department.name">部门</th>
										<th data-field="sex">性别 </th>
										<th data-field="age">年龄</th>
										<th data-field="applyStatus">审核状态 </th>
										<th data-field="instro">备注</th>
										<shiro:hasPermission name="ante:teacherInfo:edit"><th>操作</th></shiro:hasPermission>
									</tr>
								</thead>
								<c:forEach items="${page.list}" var="counselor">
									<tr>
										<td>${counselor.id}</td>
										<td>${counselor.id}</td>
										<td><a class="text-navy" href="${ctx}/ante/teacherInfo/view?id=${counselor.id}">
											${counselor.user.name}
										</a></td>
										<td>
											${counselor.counselType.typeName}
										</td>
										<td>
											${counselor.department.deptName}
										</td>
										<td>
											${counselor.sex=='1'?'男':'女'}
										</td>
										<td>
											${counselor.age}
										</td>
										<td>
											<c:if test="${counselor.applyStatus=='0'}">待审核</c:if>
											<c:if test="${counselor.applyStatus=='1'}">审核通过</c:if>
											<c:if test="${counselor.applyStatus=='2'}">驳回</c:if>
										</td>
										<td>
											${counselor.instro}
										</td>
										<shiro:hasPermission name="ante:teacherInfo:edit"><td>
											<a class="text-navy" href="${ctx}/ante/teacherInfo/form?id=${counselor.id}">
												<i class="glyphicon glyphicon-edit" aria-hidden="true" title="修改"></i>
											</a>&nbsp;&nbsp;
											<a href="#" class="text-danger" onclick="delOne('${counselor.id}')" title="删除">
												<i class="glyphicon glyphicon-trash" aria-hidden="true"></i>
											</a>&nbsp;&nbsp;
											<a class="text-navy" href="${ctx}/ante/commentInfo/viewList?id=${parentsVo.account}" title="评语记录">
												<i class="glyphicon glyphicon-envelope" aria-hidden="true"></i>
											</a>&nbsp;&nbsp;
											<a class="text-navy" href="${ctx}/ante/teacherInfo/setBrowsePermissions?id=${counselor.id}">分配管理组织</a>&nbsp;&nbsp;&nbsp;
											<a class="text-navy" href="${ctx}/ante/teacherInfo/res?id=${counselor.id}">权限设置</a>
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
		$(function() {
	
		});
		/**
		 * 删除一条
		 */    
		function delOne(id){
	        layer.confirm('确认要删除吗？', {
	            btn: ['确认','取消'], //按钮
	            title:'删除确认',
	            shade: false //不显示遮罩
	        }, function(){
	            document.location.href="${ctx}/ante/teacherInfo/delete?id="+id;
	            layer.msg('删除成功！', {icon: 6});
	        }, function(){
	            layer.msg('已取消', {icon: 1});
	        });
	    }
	</script>
</body>
</html>