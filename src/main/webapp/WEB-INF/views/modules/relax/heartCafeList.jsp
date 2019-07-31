<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<!-- Mirrored from www.zi-han.net/theme/hplus/table_bootstrap.html by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 20 Jan 2016 14:20:03 GMT -->
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${sysConfig.schoolName } 机构--心灵咖啡屋管理</title>
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
                        <h4 class="example-title">心灵咖啡屋列表</h4>
                        <div class="example">
                    		<div class="btn-group " id="exampleTableEventsToolbar" role="group">
                                <shiro:hasPermission name="relax:heartCafe:edit">
								<a class="J_menuItem" href="${ctx}/relax/heartCafe/form">
								<button type="button" class="btn btn-outline btn-default">
                                    <i class="glyphicon glyphicon-plus text-navy" aria-hidden="false"></i>
                                </button>
								</a>
								</shiro:hasPermission>
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
	
	<!--
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>名称</th>
				<th>是否公开</th>
				<th>创建时间</th>
				<shiro:hasPermission name="relax:heartCafe:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="heartCafe">
			<tr>
				<td><a href="${ctx}/relax/heartCafe/form?id=${heartCafe.id}">
					${heartCafe.name}
				</a></td>
				<td>
					${fns:getDictLabel(heartCafe.isPublic, 'yes_no', '')}
				</td>
				<td>
					<fmt:formatDate value="${heartCafe.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="relax:heartCafe:edit"><td>
    				<a href="${ctx}/relax/heartCafe/form?id=${heartCafe.id}">修改</a>
					<a href="${ctx}/relax/heartCafe/delete?id=${heartCafe.id}" onclick="return confirmx('确认要删除该心灵咖啡屋吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	-->
	
			<table id="exampleTableToolbar" data-toggle="table" data-query-params="queryParams" data-mobile-responsive="true" data-height="400" data-pagination="true" data-search="true">
                                <thead>
                                    <tr>
                                        <th data-field="id" data-checkbox="true" aria-hidden="true"></th>
                                        <th data-field="name">标题</th>
										<th data-field = "creatorName" >发布人</th>
										<th data-field= "orgName" >所属机构</th>                                       
										<th data-field= "createDate">创建时间</th>
										<th data-field= "isPublic">是否共享</th>										
                                        <th>操作</th>
                                    </tr>
                                </thead>
                                <c:forEach items="${page.list}" var="heartCafe">									
									<tr>
										<td data-field="id" data-checkbox="true" aria-hidden="true"></td>
										<td><a class="text-navy" href="${ctx}/relax/heartCafe/form?id=${heartCafe.id}">${heartCafe.name}</a></td>
										<td> ${heartCafe.creatorName}</td>
										<td>${heartCafe.orgName} </td>										
										<td><fmt:formatDate value="${heartCafe.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
										<td>${fns:getDictLabel(heartCafe.isPublic, 'yes_no', '')}</td>
										<td>
										<!--
											<a href="#" class="text-info">
												<i class="fa fa-pie-chart" aria-hidden="true"></i>
											</a>&nbsp;
										-->
											<a class="text-navy" href="${ctx}/relax/heartCafe/form?id=${heartCafe.id}">
												<i class="glyphicon glyphicon-edit" aria-hidden="true"></i>
											</a>&nbsp;
											
											<a  class="text-danger" href="${ctx}/relax/heartCafe/delete?id=${heartCafe.id}" onclick="return confirmx('确认要删除该心灵咖啡屋吗？', this.href)">
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