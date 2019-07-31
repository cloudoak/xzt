<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<!-- Mirrored from www.zi-han.net/theme/hplus/table_bootstrap.html by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 20 Jan 2016 14:20:03 GMT -->
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${sysConfig.schoolName } </title>
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
                        <h4 class="example-title">权限资源列表</h4>
                        <div class="example">
                    		<div class="btn-group " id="exampleTableEventsToolbar" sysRoleGrant="group">
                                <%-- <button type="button" class="btn btn-outline btn-default">
                                    <shiro:hasPermission name="sys:sysRoleGrant:edit">
                                    <a class="J_menuItem" href="${ctx}/sys/sysRoleGrant/form?parentId=${sysRoleGrant.id}">
                                    <i class="glyphicon glyphicon-plus text-navy" aria-hidden="false"></i>
                                   	</a>
                                   	</shiro:hasPermission>
                                </button>
                                <button type="button" class="btn btn-outline btn-default">
                                    <i class="glyphicon glyphicon-edit text-navy" aria-hidden="false"></i>
                                </button>
                                <button type="button" class="btn btn-outline btn-default">
                                    <i class="glyphicon glyphicon-trash text-navy" aria-hidden="false"></i>
                                </button> --%>
                            </div>
                            <table id="exampleTableToolbar" data-toggle="table" data-query-params="queryParams" data-mobile-responsive="true" data-height="500" data-pagination="true" data-search="true">
                                <thead>
                                    <tr>
                                        <!-- <th data-field="id" data-checkbox="true" aria-hidden="true"></th> -->
                                        <th data-field="name">权限资源名称</th>
                                        <%-- <shiro:hasPermission name="sys:sysRoleGrant:edit"> --%>
                                        <th></th>
										<!-- <th>操作</th> -->
										<%-- </shiro:hasPermission> --%> 
                                    </tr>
                                </thead>
                                <c:forEach items="${sourcelist }" var="sysRoleGrant">
                                	<c:if test="${sysRoleGrant.parentId==1 }">
									<tr>
										<!-- <td data-field="id" data-checkbox="true" aria-hidden="true"></td> -->
										<td>
											<%-- <a class="text-navy" href="${ctx}/sys/sysRoleGrant/form?id=${sysRoleGrant.id}">${sysRoleGrant.name}</a> --%>
											<a class="text-navy" href="javascript:">${sysRoleGrant.name}</a>
										</td>
										<td>
										<ul>
										<c:forEach items="${sourcelist }" var="tGrant">
		                                	<c:if test="${sysRoleGrant.id==tGrant.parentId }">
											<li class="list-group-item">
												<a class="text-navy" href="${ctx}/sys/sysRoleGrant/form?id=${tGrant.id}">${tGrant.name}</a>&nbsp;
												<%-- <a class="text-navy" href="${ctx}/sys/sysRoleGrant/form?id=${tGrant.id}">
													<i class="glyphicon glyphicon-edit" aria-hidden="true"></i>
												</a>&nbsp;
												<a href="#" class="text-danger" onclick="delsysRoleGrant('${tGrant.id}','${tGrant.name}')">
													<i class="glyphicon glyphicon-trash" aria-hidden="true"></i>
												</a> --%>
											</li>
											<li class="list-group-item">
												<ul>
													<c:forEach items="${sourcelist }" var="grant">
					                                	<c:if test="${tGrant.id==grant.parentId }">
					                                		<li class="list-group-item">
															<%-- <a class="text-navy" href="${ctx}/sys/sysRoleGrant/form?id=${grant.id}">${grant.name}</a>&nbsp; --%>
															<a class="text-navy" href="javascript:">${grant.name}</a>&nbsp;
															<%-- <a class="text-navy" href="${ctx}/sys/sysRoleGrant/form?id=${grant.id}">
																<i class="glyphicon glyphicon-edit" aria-hidden="true"></i>
															</a>&nbsp;
															<a href="#" class="text-danger" onclick="delsysRoleGrant('${grant.id}','${grant.name}')">
																<i class="glyphicon glyphicon-trash" aria-hidden="true"></i>
															</a> --%>
															
															<c:forEach items="${sourcelist }" var="g">
							                                	<c:if test="${grant.id==g.parentId }">
							                                		&nbsp;
																	<%-- <a class="text-navy" href="${ctx}/sys/sysRoleGrant/form?id=${g.id}">${g.name}</a>&nbsp; --%>
																	<a class="text-navy" href="javascript:">${g.name}</a>&nbsp;
																</c:if>
															</c:forEach>
															
															</li>
														</c:if>
													</c:forEach>
												</ul>
											</li>
											</c:if>
										</c:forEach>
										</ul>
										</td>
										<%-- <td>
											<a class="text-navy" href="${ctx}/sys/sysRoleGrant/form?id=${sysRoleGrant.id}">
												<i class="glyphicon glyphicon-edit" aria-hidden="true"></i>
											</a>&nbsp;
											<a href="#" class="text-danger" onclick="delsysRoleGrant('${sysRoleGrant.id}','${sysRoleGrant.name}')">
												<i class="glyphicon glyphicon-trash" aria-hidden="true"></i>
											</a>
										</td> --%>
									</tr>
									</c:if>
								</c:forEach>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- End Panel Basic -->
    </div>
    <script src="${hplusStatic }/js/jquery.min.js?v=2.1.4"></script>
    <script src="${hplusStatic }/js/bootstrap.min.js?v=3.3.6"></script>
    <script src="${hplusStatic }/js/content.min.js?v=1.0.0"></script>
    <script src="${hplusStatic }/js/plugins/bootstrap-table/bootstrap-table.min.js"></script>
    <script src="${hplusStatic }/js/plugins/bootstrap-table/bootstrap-table-mobile.min.js"></script>
    <script src="${hplusStatic }/js/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>
    <script src="${hplusStatic }/js/demo/bootstrap-table-demo.min.js"></script>
    <script src="${hplusStatic }/js/plugins/layer/layer.min.js" type="text/javascript"></script>
    <!-- <script src="http://tajs.qq.com/stats?sId=9051096" charset="UTF-8" type="text/javascript"></script> -->
	<script type="text/javascript">
	//${ctx}/sys/org/delete?id=${sysRoleGrant.id}
		function delsysRoleGrant(id,name){
			layer.confirm('是否删除 ('+name+') 权限资源？', {
			    btn: ['确认','取消'], //按钮
			    title:'删除确认',
			    shade: false //不显示遮罩
			}, function(){
				document.location.href="${ctx}/sys/sysRoleGrant/delete?id="+id;
			    layer.msg('删除成功！', {icon: 6});
			}, function(){
			    layer.msg('已取消', {icon: 1});
			});
		}
	</script>
</body>
<!-- Mirrored from www.zi-han.net/theme/hplus/table_bootstrap.html by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 20 Jan 2016 14:20:06 GMT -->
</html>
