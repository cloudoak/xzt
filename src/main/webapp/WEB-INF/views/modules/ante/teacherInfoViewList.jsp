<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>教师审核</title>
	<meta charset="utf-8">
	<meta name="decorator" content="default"/>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<%@ include file="/WEB-INF/views/include/commoncss.jsp" %>
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
</head>
<body class="gray-bg">
	<%-- <ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/ante/teacherInfo/">教师审核列表</a></li>
	</ul> --%>
	<div class="wrapper wrapper-content animated fadeInRight">
		<div class="ibox float-e-margins">
			<div class="ibox-content">
				<div class="row row-lg">
					<div class="col-sm-12">
						<h4 class="example-title">教师审核列表</h4>
						<div class="example">
							<form id="searchForm" modelAttribute="teacherInfo" action="${ctx}/ante/teacherInfo/reviewList" method="post" class="breadcrumb form-search">
								<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
								<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
								<div>
									<label>教师姓名：</label>
									<input id="name" name="name" type="text" maxlength="50" />
									<label>教师类型：</label>
									<select name="counselorType" id="counselorType" class="required input-mini" style="width:180px;">
										<option value="">--请选择--</option>
										<c:forEach items="${counselorTypes.list}" var="counselorType">
											<option value="${counselorType.id}" ${counselorType.id==column.id?'selected':''} title="${counselorType.typeName}">${counselorType.typeName}</option>
										</c:forEach>
									</select>
									<label>审核状态 ：</label>
									<select name="applyStatus" id="applyStatus">
										<option value="">请选择</option>
										<option value="0">待审核</option>
										<option value="1">审核通过</option>
										<option value="2">驳回</option>
									</select>
									
									<button id="btnQuery" class="btn btn-primary" onclick="">查询</button>
								</ul>
							</form>
							<sys:message content="${message}"/>
							<table id="contentTable" class="table table-striped table-bordered table-condensed">
								<thead>
									<tr>
										<!-- <th data-checkbox="true" aria-hidden="true"></th>
										<th data-field="id" data-visible="false"></th> -->
										<th>教师姓名</th>
										<th>教师类型</th>
										<th>部门</th>
										<th>性别 </th>
										<th>年龄</th>
										<th>审核状态 </th>
										<shiro:hasPermission name="ante:teacherInfo:edit"><th>操作</th></shiro:hasPermission>
									</tr>
								</thead>
								<c:forEach items="${page.list}" var="counselor">
									<tr>
										<%-- <td>${counselor.id}</td>
										<td>${counselor.id}</td> --%>
										<td><a href="${ctx}/ante/teacherInfo/view?id=${counselor.id}">${counselor.name}</a></td>
										<td>
											<c:forEach items="${counselorTypes.list}" var="counselorType">
												${counselor.counselorType==counselorType.id?counselorType.typeName:''}
											</c:forEach>
										</td>
										<td>
											<c:forEach items="${departments.list}" var="department">
												${counselor.deptId==department.id?department.deptName:''}
											</c:forEach>
										</td>
										<td>${counselor.sex=='1'?'男':'女'}</td>
										<%-- <td><fmt:formatDate value="${counselor.teaBirth}" pattern="yyyy-MM-dd"/></td> --%>
										<td>${counselor.age}</td>
										<td>
											<c:if test="${counselor.applyStatus=='0'}">待审核</c:if>
											<c:if test="${counselor.applyStatus=='1'}">审核通过</c:if>
											<c:if test="${counselor.applyStatus=='2'}">驳回</c:if>
										</td>
										
										<td>
											<shiro:hasPermission name="ante:teacherInfo:edit">
												<a href="${ctx}/ante/teacherInfo/delete?id=${counselor.id}&reviewSign=true" onclick="return confirmx('确认删除吗？', this.href)">删除</a>
												<c:choose>
								    				<c:when test="${counselor.applyStatus==0 || counselor.applyStatus==2}">
														<a href="${ctx}/ante/teacherInfo/toModStatus?id=${counselor.id}" onclick="return confirmx('确认审核吗？', this.href)">审核</a>
								    				</c:when>
								    				<c:otherwise>审核</c:otherwise>
							    				</c:choose>
											</shiro:hasPermission>
										</td>
									</tr>
								</c:forEach>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="pagination">${page}</div>
	
	<%@ include file="/WEB-INF/views/include/commonlistjs.jsp" %>
</body>
</html>