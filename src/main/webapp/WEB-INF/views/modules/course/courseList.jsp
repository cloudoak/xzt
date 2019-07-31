<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${sysConfig.schoolName } 机构--课件管理</title>
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
                        <h4 class="example-title">课件列表</h4>
                        <div class="example">
                    		<div class="btn-group " id="exampleTableEventsToolbar" role="group">
                                <shiro:hasPermission name="course:course:edit">
								<a class="J_menuItem" href="${ctx}/course/course/form">
								<button type="button" class="btn btn-outline btn-default">
                                    <i class="glyphicon glyphicon-plus text-navy" aria-hidden="false"></i>
                                </button>
								</a></shiro:hasPermission>
								<a>
                                <button type="button" class="btn btn-outline btn-default">
                                    <i class="glyphicon glyphicon-edit text-navy" aria-hidden="false"></i>
                                </button>
                                </a>
                                <a>
                                <button type="button" class="btn btn-outline btn-default">
                                    <i class="glyphicon glyphicon-trash text-navy" aria-hidden="false"></i>
                                </button>
                                </a>
                            </div>
						<sys:message content="${message}"/>
						<table id="exampleTableToolbar" data-toggle="table" data-query-params="queryParams" data-mobile-responsive="true"  data-pagination="true">
                                <thead>
                                    <tr>
                                        <th data-field="id" data-checkbox="true" aria-hidden="true"></th>
                                        <th data-field="name">课件名称</th>
                                        <th data-field="catalog">课件分类</th>
                                        <th data-field="isPublic">是否完全公开</th>
										<th data-field="createDate">创建时间</th>										
                                        <th>操作</th>
                                    </tr>
                                </thead>
                                <c:forEach items="${page.list}" var="course">									
									<tr>
										<td data-field="id" data-checkbox="true" aria-hidden="true"></td>
										<td><a class="text-navy" href="${ctx}/course/course/view?id=${course.id}">${course.name}</a></td>
										<td>${course.courseCatalogName}</td>
										<td>${fns:getDictLabel(course.isPublic, 'yes_no', '')}</td>
										<td><fmt:formatDate value="${course.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
										<td>
											<a class="text-navy" href="${ctx}/course/course/form?id=${course.id}">
												<i class="glyphicon glyphicon-edit" aria-hidden="true"></i>
											</a>&nbsp;
											<a class="text-danger" href="${ctx}/course/course/delete?id=${course.id}" onclick="return confirm('确认要删除该课件吗？', this.href)">
												<i class="glyphicon glyphicon-trash" aria-hidden="true"></i>
											</a>&nbsp;
											<c:if test="${course.isPublic==0}">
											<a class="text-info" href="${ctx}/course/course/setBrowsePermissions?id=${course.id}" >
												<i class="fa fa-pie-chart" aria-hidden="true"></i>
											</a>&nbsp;
											</c:if>
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
			var val = $("#selecttype").val();
			$("#select_type option[value='"+val+"']").attr("selected",true);
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
		function changetype(){
			var value = $("#select_type option:selected").val();
			window.location.href="${ctx}/course/course/?"+value+"&type=1"; 
		}
	</script>
</html>