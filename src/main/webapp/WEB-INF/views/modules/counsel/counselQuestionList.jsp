<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>答疑室管理</title>
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
                        <h4 class="example-title">答疑室问题列表</h4>
                        <div class="example">
                    		<div class="btn-group " id="exampleTableEventsToolbar" role="group">
                                <button type="button" class="btn btn-outline btn-default">
                                    <shiro:hasPermission name="counsel:counselQuestion:edit"><a class="J_menuItem" href="${ctx}/counsel/counselQuestion/form">
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
							
		<!--					
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/counsel/counselQuestion/">答疑室问题列表</a></li>
		<shiro:hasPermission name="counsel:counselQuestion:edit"><li><a href="${ctx}/counsel/counselQuestion/form">答疑室问题添加</a></li></shiro:hasPermission>
	</ul>	
	-->
	<!--
	<form:form id="searchForm" modelAttribute="counselQuestion" action="${ctx}/counsel/counselQuestion/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>咨询者：</label>
				<form:input path="askName" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			<li><label>咨询师：</label>
				<form:input path="counselorName" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			<li><label>咨询类型：</label>
				<form:select path="questionType" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('question_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>创建时间：</label>
				<input name="beginCreateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${counselQuestion.beginCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> - 
				<input name="endCreateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${counselQuestion.endCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	-->
	<sys:message content="${message}"/>
	
	
	 <table id="exampleTableToolbar" data-toggle="table" data-query-params="queryParams" data-mobile-responsive="true" data-height="400" data-pagination="true" data-search="true">
                                <thead>
                                    <tr>
                                        <th data-field="id" data-checkbox="true" aria-hidden="true"></th>
                                        <th data-field="user.name">咨询者</th>
                                        <th data-field="counselor.name">咨询师</th>
                                        <th data-field="question">咨询问题</th>
										<th data-field="questonType">咨询类型</th>
										<th data-field="createDate">提问时间</th>
										<th data-field="count">回复次数</th>
                                        <th>操作</th>
                                    </tr>
                                </thead>
                                <c:forEach items="${page.list}" var="counselQuestion">
									<tr>
										<td data-field="id" data-checkbox="true" aria-hidden="true"></td>
										<td><a class="text-navy" href="${ctx}/sys/org/form?id=${office.id}">${counselQuestion.user.name}</a></td>
										<td>${counselQuestion.counselor.user.name}</td>
										<td title="${counselQuestion.title} ">${fns:abbr(counselor.title,30)}</td>
										<td>${fns:getDictLabel(counselQuestion.questionType, 'question_type', '')}</td>
										<td><fmt:formatDate value="${counselQuestion.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
										<td>${counselQuestion.count}</td>
										<td>
											
											<a class="text-navy" href="${ctx}/counsel/counselQuestion/form?id=${counselQuestion.id}">
												<i class="glyphicon glyphicon-edit" aria-hidden="true"></i>
											</a>&nbsp;
											<a class="text-danger" href="${ctx}/counsel/counselQuestion/delete?id=${counselQuestion.id}" onclick="return confirmx('确认要删除该答疑室吗？', this.href)">
												<i class="glyphicon glyphicon-trash" aria-hidden="true"></i>
											</a>&nbsp;
											<a href="${ctx}/counsel/counselAnswer/?counselAnswer.questionId=${counselQuestion.id}" class="text-info">
												<i class="fa fa-pie-chart" aria-hidden="true"></i>
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
	<!--
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>咨询者</th>
				<th>咨询师</th>
				<th>咨询类型</th>
				<th>提问时间</th>
				<shiro:hasPermission name="counsel:counselQuestion:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="counselQuestion">
			<tr>
				<td><a href="${ctx}/counsel/counselQuestion/form?id=${counselQuestion.id}">
					${counselQuestion.askName}
				</a></td>
				<td>
					${counselQuestion.counselorName}
				</td>
				<td>
					${fns:getDictLabel(counselQuestion.questionType, 'question_type', '')}
				</td>
				<td>
					<fmt:formatDate value="${counselQuestion.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="counsel:counselQuestion:edit"><td>
    				<a href="${ctx}/counsel/counselQuestion/form?id=${counselQuestion.id}">修改</a>
					<a href="${ctx}/counsel/counselQuestion/delete?id=${counselQuestion.id}" onclick="return confirmx('确认要删除该答疑室问题吗？', this.href)">删除</a>
					
					<a href="${ctx}/counsel/counselAnswer/?counselAnswer.questionId=${counselQuestion.id}">查看回复</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	-->
	
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