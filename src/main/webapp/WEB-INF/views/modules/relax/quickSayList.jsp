<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<!-- Mirrored from www.zi-han.net/theme/hplus/table_bootstrap.html by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 20 Jan 2016 14:20:03 GMT -->
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${sysConfig.schoolName } 机构--一吐为快管理</title>
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
                        <h4 class="example-title">一吐为快列表</h4>
                        <div class="example">
                    		<div class="btn-group " id="exampleTableEventsToolbar" role="group">
                                <shiro:hasPermission name="relax:quickSay:edit">
								  <a class="J_menuItem" href="${ctx}/relax/quickSay/form">
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
							
							
			<table id="exampleTableToolbar" data-toggle="table" data-query-params="queryParams" data-mobile-responsive="true" data-height="400" data-pagination="true" data-search="true">
                                <thead>
                                    <tr>
                                        <th data-field="id" data-checkbox="true" aria-hidden="true"></th>
                                        <th data-field="name">标题</th>
										<th data-field = "creatorName" >发布人</th>
										<th data-field= "orgName" >所属机构</th>                                       
										<th data-field= "createDate">创建时间</th>
																				
                                        <th>操作</th>
                                    </tr>
                                </thead>
                                <c:forEach items="${page.list}" var="quickSay">									
									<tr>
										<td data-field="id" data-checkbox="true" aria-hidden="true"></td>
										<td><a class="text-navy" href="${ctx}/relax/heartCafe/form?id=${heartCafe.id}">${quickSay.name}</a></td>
										<td> ${quickSay.creatorName}</td>
										<td>${quickSay.orgName} </td>										
										<td><fmt:formatDate value="${quickSay.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
										<!--<td>${fns:getDictLabel(quickSay.isPublic, 'yes_no', '')}</td>-->
										<td>
										<!--
											<a href="#" class="text-info">
												<i class="fa fa-pie-chart" aria-hidden="true"></i>
											</a>&nbsp;
										-->
											<a class="text-navy" href="${ctx}/relax/quickSay/form?id=${quickSay.id}">
												<i class="glyphicon glyphicon-edit" aria-hidden="true"></i>
											</a>&nbsp;
											
											<a  class="text-danger" href="${ctx}/relax/quickSay/delete?id=${quickSay.id}" onclick="return confirmx('确认要删除一吐为快吗？', this.href)">
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