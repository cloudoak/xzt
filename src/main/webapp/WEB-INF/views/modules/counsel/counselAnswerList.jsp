<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<!-- Mirrored from www.zi-han.net/theme/hplus/table_bootstrap.html by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 20 Jan 2016 14:20:03 GMT -->
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${sysConfig.schoolName } 机构</title>
    <meta name="keywords" content="${fns:getConfig('keywords')} ${sysConfig.schoolName } ">
    <meta name="description" content="${fns:getConfig('description')} ${sysConfig.schoolName }">

    <link rel="shortcut icon" href="${hplusStatic }favicon.ico"> 
    <link href="${hplusStatic }/css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
    <link href="${hplusStatic }/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
    <link href="${hplusStatic }/css/plugins/bootstrap-table/bootstrap-table.min.css" rel="stylesheet">
    <link href="${hplusStatic }/css/animate.min.css" rel="stylesheet">
    <link href="${hplusStatic }/css/style.min862f.css?v=4.1.0" rel="stylesheet">
</head>


<!--
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/counsel/counselAnswer/">答疑室${counselAnswer.questionId} 回复列表</a></li>
		<shiro:hasPermission name="counsel:counselAnswer:edit"><li><a href="${ctx}/counsel/counselAnswer/form">答疑室回复添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="counselAnswer" action="${ctx}/counsel/counselAnswer/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<input id="counselAnswer.questionId" name="counselAnswer.questionId" type="hidden" value="${counselAnswer.questionId}" />
		<ul class="ul-form">
			<li><label>标题：</label>
				<form:input path="title" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			<li><label>是否匿名：</label>
				<form:radiobuttons path="isAnony" items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>	
	-->	
<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="ibox float-e-margins">
            <div class="ibox-content">
                <div class="row row-lg">
                    <div class="col-sm-12">
                        <h4 class="example-title"><a href="${ctx}/counsel/counselQuestion/">返回答疑室<a></h4>
                        <div class="example">
							<!--
                    		<div class="btn-group " id="exampleTableEventsToolbar" role="group">
                                <button type="button" class="btn btn-outline btn-default">
                                    <shiro:hasPermission name="sys:office:edit"><a class="J_menuItem" href="${ctx}/sys/org/form?parent.id=${office.id}">
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
							-->
	
	<sys:message content="${message}"/>
	<!--
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>标题</th>
				<th>内容</th>
				<th>是否匿名</th>
				<th>创建时间</th>
				<shiro:hasPermission name="counsel:counselAnswer:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="counselAnswer">
			<tr>
				<td><a href="${ctx}/counsel/counselAnswer/form?id=${counselAnswer.id}">
					${counselAnswer.title}
				</a></td>
				<td>
					${fns:getDictLabel(counselAnswer.isAnony, 'yes_no', '')}
				</td>
				<td>
					<fmt:formatDate value="${counselAnswer.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="counsel:counselAnswer:edit"><td>
    				<a href="${ctx}/counsel/counselAnswer/form?id=${counselAnswer.id}">修改</a>
					<a href="${ctx}/counsel/counselAnswer/delete?id=${counselAnswer.id}" onclick="return confirmx('确认要删除该答疑室回复吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	-->
	
	 <table id="exampleTableToolbar" data-toggle="table" data-query-params="queryParams" data-mobile-responsive="true" data-height="400" data-pagination="true" >
                                <thead>
                                    <tr>
                                        <th data-field="id" data-checkbox="true" aria-hidden="true"></th>
                                        <th data-field="title">标题</th>
                                        <th data-field="content">内容</th>
                                        <th data-field="create_name">提交人</th>
										<th data-field="createDate">创建时间</th>                                        
                                    </tr>
                                </thead>
                                <c:forEach items="${page.list}" var="counselAnswer">
									
									<tr>
										<td data-field="id" data-checkbox="true" aria-hidden="true"></td>
										<td>
										<!--
										<a class="text-navy" href="${ctx}/counsel/counselAnswer/form?id=${counselAnswer.id}"></a>
										-->
										${counselAnswer.title}</td>
										<td title="${counselAnswer.content}">
											${fns:abbr(counselAnswer.content,30)}
										</td>
										<td>
										<c:if test="${counselAnswer.isAnony==0 }">
											${counselAnswer.createName}
										</c:if>
										<c:if test="${counselAnswer.isAnony==1 }">
											匿名
										</c:if>
										</td>
										<td><fmt:formatDate value="${counselAnswer.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
										<!--
										<td>
											<a href="#" class="text-info">
												<i class="fa fa-pie-chart" aria-hidden="true"></i>
											</a>&nbsp;
											<a class="text-navy" href="${ctx}/sys/org/form?id=${office.id}">
												<i class="glyphicon glyphicon-edit" aria-hidden="true"></i>
											</a>&nbsp;
											<a href="#" class="text-danger" onclick="delOffice('${office.id}','${office.name}')">
												<i class="glyphicon glyphicon-trash" aria-hidden="true"></i>
											</a>
										</td>
										-->
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
	
	<!--
	<div class="pagination">${page}</div>
	-->
</body>
  <script src="${hplusStatic }/js/jquery.min.js?v=2.1.4"></script>
    <script src="${hplusStatic }/js/bootstrap.min.js?v=3.3.6"></script>
    <script src="${hplusStatic }/js/content.min.js?v=1.0.0"></script>
    <script src="${hplusStatic }/js/plugins/bootstrap-table/bootstrap-table.min.js"></script>
    <script src="${hplusStatic }/js/plugins/bootstrap-table/bootstrap-table-mobile.min.js"></script>
    <script src="${hplusStatic }/js/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>
    <script src="${hplusStatic }/js/demo/bootstrap-table-demo.min.js"></script>
    <script src="${hplusStatic }/js/plugins/layer/layer.min.js" type="text/javascript"></script>


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