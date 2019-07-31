<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${sysConfig.schoolName } 来访者信息管理--来访者信息维护</title>
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
						<h4 class="example-title">来访者信息维护</h4>
						<div class="example">
							<div class="btn-group " id="exampleTableEventsToolbar" role="group">
								<a class="J_menuItem" href="${ctx}/ante/visitorInfo/form">
									<button type="button" class="btn btn-outline btn-default">
										<shiro:hasPermission name="ante:visitorInfo:view">
												<i class="glyphicon glyphicon-plus text-navy" aria-hidden="false"></i>
										</shiro:hasPermission>
									</button>
								</a>
								<!-- <a>
									<button type="button" class="btn btn-outline btn-default">
										<i class="glyphicon glyphicon-edit text-navy" aria-hidden="false"></i>
									</button>
								</a> -->
								<a>
									<button type="button" onclick="delAll()" class="btn btn-outline btn-default">
										<i class="glyphicon glyphicon-trash text-navy" aria-hidden="false"></i>
									</button>
								</a>
							</div>
							<form id="searchForm" action="${ctx}/ante/visitorInfo/list" method="post" class="form-inline">
								<div class="form-group">
									<label>来访者编号：</label>
									<input id="visitorNo" name="visitorNo" value="${visitorInfo.visitorNo}" class="form-control" type="text" maxlength="30"/>
								</div>
								<div class="form-group">
									<label>来访者姓名：</label>
									<input id="name" name="user.name" value="${visitorInfo.user.name}" class="form-control" type="text" maxlength="30"/>
								</div>
								<div class="form-group">
									<button id="btnQuery" class="btn btn-primary">查询</button>
									<shiro:hasPermission name="ante:visitorInfo:edit">
									<button id="btnExportAll" type="button" class="btn btn-primary">导出全部</button>
									</shiro:hasPermission>
								</div>
							</form>
							<table id="exampleTableToolbar" data-toggle="table" data-query-params="queryParams" data-mobile-responsive="true" data-height="400" data-pagination="true" data-search="false">
								<thead>
									<tr>
										<th data-checkbox="true" aria-hidden="true"></th>
                                    	<th data-field="id" data-visible="false"></th>
										<th data-field="visitorNo">来访者编号</th>
										<th data-field="user.name">姓名</th>
										<th data-field="sex">性别</th>
										<th data-field="orgId">机构</th>
										<th data-field="age">年龄</th>
										<th data-field="sj">最近登录时间</th>
										<th>操作</th>
									</tr>
								</thead>
								<c:forEach items="${page.list}" var="visitorInfo">
										<tr>
											<td>${visitorInfo.id}</td>
											<td>${visitorInfo.id}</td>
											<td><a class="text-navy" href="${ctx}/ante/visitorInfo/view?id=${visitorInfo.id}">${visitorInfo.visitorNo}</a></td>
											<td>${visitorInfo.user.name}</td>
											<td>${fns:getDictLabel(visitorInfo.sex, 'sex', '')}</td>
											<td>
												${visitorInfo.org.name}
											</td>
											<td>${visitorInfo.age}</td>
											<td>
												<c:if test="${visitorInfo.user.loginDate ne null}">
													<fmt:formatDate value="${visitorInfo.user.loginDate}" pattern="yyyy-MM-dd HH:mm:ss" />
												</c:if>
												<c:if test="${visitorInfo.user.loginDate eq null}"></c:if>
											</td>
											<td>
												<a class="text-navy" href="${ctx}/ante/visitorInfo/view?id=${visitorInfo.id}" title="查看">
													<i class="glyphicon glyphicon-eye-open" aria-hidden="true"></i>
												</a>&nbsp;&nbsp;
												<a class="text-info" href="${ctx}/sys/user/modifyPwd?visitorNo=${visitorInfo.visitorNo}&oldPassword=${visitorInfo.password}" title="密码管理">
													<i class="glyphicon glyphicon-lock" aria-hidden="true"></i>
												</a>&nbsp;&nbsp;
												<a class="text-navy" href="${ctx}/ante/visitorInfo/form?id=${visitorInfo.id}" title="修改">
													<i class="glyphicon glyphicon-edit" aria-hidden="true"></i>
												</a>&nbsp;&nbsp;
												<a href="#" class="text-danger" onclick="delOffice('${visitorInfo.id}')" title="删除">
													<i class="glyphicon glyphicon-trash" aria-hidden="true"></i>
												</a>&nbsp;&nbsp;
												<a class="text-info" href="${ctx}/ante/commentInfo/viewList?commentId=${visitorInfo.user.id}" title="评语记录">
													<i class="glyphicon glyphicon-envelope" aria-hidden="true"></i>
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
	<%@ include file="/WEB-INF/views/include/commonlistjs.jsp" %>
	<script type="text/javascript">
		$(function(){
			$("#btnExportAll").on("click", function(){
				location.href = "${ctx}/ante/visitorInfo/exportFile";
			});
		})
		/**
		 * 删除一条
		 */    
		function delOffice(id){
	        layer.confirm('确认要删除该来访者吗？', {
	            btn: ['确认','取消'], //按钮
	            title:'删除确认',
	            shade: false //不显示遮罩
	        }, function(){
	            document.location.href="${ctx}/ante/visitorInfo/delete?id="+id;
	            layer.msg('删除成功！', {icon: 6});
	        }, function(){
	            layer.msg('已取消', {icon: 1});
	        });
	    }
	    
	    /**
		 * 删除多条
		 */
		function delAll(){
			var sumChecked=$("input:checked").length;
			if(sumChecked<1){
				layer.msg('请选择要删除的记录！', {icon: 6});
				return false;
			}
			var a = $.map($("#exampleTableToolbar").bootstrapTable('getSelections'), function (row) {
		        return row.id;
		    });
			var ids=JSON.stringify(a);
			layer.confirm('是否删除选中的记录？', {
			    btn: ['确认','取消'], //按钮
			    title:'提示',
			    shade: false //不显示遮罩
			}, function(){
				window.location.href="${ctx}/ante/visitorInfo/deleteAll?ids="+ids;
			    //layer.msg('删除成功！', {icon: 6});
			}, function(){
			    layer.msg('已取消', {icon: 1});
			});
		}
	</script>
</body>
</html>
