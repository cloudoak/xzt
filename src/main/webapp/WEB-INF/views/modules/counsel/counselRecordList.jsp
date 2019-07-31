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
	
	<%@ include file="/WEB-INF/views/include/commoncss.jsp" %>
	
</head>
<!--
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/counsel/counselRecord/">咨询记录列表</a></li>
		<shiro:hasPermission name="counsel:counselRecord:edit"><li><a href="${ctx}/counsel/counselRecord/form">咨询记录添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="counselRecord" action="${ctx}/counsel/counselRecord/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>学员标识：</label>
				<form:input path="visitorId" htmlEscape="false" maxlength="11" class="input-medium"/>
			</li>
			<li><label>学员名称：</label>
				<form:input path="visitorName" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			<li><label>咨询师标识：</label>
				<form:input path="counselorId" htmlEscape="false" maxlength="11" class="input-medium"/>
			</li>
			<li><label>问题类型：</label>
				<form:select path="questionType" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('question_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>是否重点个案：</label>
				<form:radiobuttons path="isCase" items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
			</li>
			<li><label>是否家长查看：</label>
				<form:radiobuttons path="isParentwatch" items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
			</li>
			<li><label>是否学生查看：</label>
				<form:radiobuttons path="isStudentwatch" items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
			</li>
			<li><label>更新时间：</label>
				<input name="updateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${counselRecord.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>分校id：</label>
				<form:input path="orgId" htmlEscape="false" maxlength="11" class="input-medium"/>
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
                        <h4 class="example-title">咨询记录列表</h4>
                        <div class="example">
                    		<div class="btn-group " id="exampleTableEventsToolbar" role="group">
                                <button type="button" class="btn btn-outline btn-default">
                                    <shiro:hasPermission name="counsel:counselRecord:edit">
										<a class="J_menuItem" href="${ctx}/counsel/counselRecord/form">
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
							
							
	<sys:message content="${message}"/>
	
	<!--
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>是否重点个案</th>
				<th>是否家长查看</th>
				<th>是否学生查看</th>
				<shiro:hasPermission name="counsel:counselRecord:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="counselRecord">
			<tr>
				<td><a href="${ctx}/counsel/counselRecord/form?id=${counselRecord.id}">
					${fns:getDictLabel(counselRecord.isCase, 'yes_no', '')}
				</a></td>
				<td>
					${fns:getDictLabel(counselRecord.isParentwatch, 'yes_no', '')}
				</td>
				<td>
					${fns:getDictLabel(counselRecord.isStudentwatch, 'yes_no', '')}
				</td>
				<shiro:hasPermission name="counsel:counselRecord:edit"><td>
    				<a href="${ctx}/counsel/counselRecord/form?id=${counselRecord.id}">修改</a>
					<a href="${ctx}/counsel/counselRecord/delete?id=${counselRecord.id}" onclick="return confirmx('确认要删除该咨询记录吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
		-->
	<table id="exampleTableToolbar" data-toggle="table" data-query-params="queryParams" data-mobile-responsive="true" data-height="400" data-pagination="true" data-search="true">
                                <thead>
                                    <tr>
                                        <th data-field="id" data-checkbox="true" aria-hidden="true"></th>
                                        <th data-field="visitorName">咨询者</th>
                                        <th data-field="counselorName">咨询师</th>
                                        <th data-field="questionType">咨询类型</th>
										<th data-field="counselDate">咨询时间</th>
                                        <th>操作</th>
                                    </tr>
                                </thead>
                                <c:forEach items="${page.list}" var="counselRecord">									
									<tr>
										<td data-field="id" data-checkbox="true" aria-hidden="true"></td>
										<td><a class="text-navy" href="${ctx}/counsel/counselRecord/form?id=${counselRecord.id}">${counselRecord.visitorName}</a></td>
										<td>${counselRecord.counselorName}</td>
										<td>${fns:getDictLabel(counselRecord.questionType, 'question_type', '')}</td>
										<td><fmt:formatDate value="${counselRecord.counselDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
										<td>
											<a href="#" class="text-info">
												<i class="fa fa-pie-chart" aria-hidden="true"></i>
											</a>&nbsp;
											<a class="text-navy" href="${ctx}/counsel/counselRecord/form?id=${counselRecord.id}">
												<i class="glyphicon glyphicon-edit" aria-hidden="true"></i>
											</a>&nbsp;
											<!--
											<a href="#" class="text-danger" onclick="delOffice('${office.id}','${office.name}')">
												<i class="glyphicon glyphicon-trash" aria-hidden="true"></i>
											</a>
											-->
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
	<div class="pagination">${page}</div>
	-->
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