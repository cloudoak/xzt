<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>测评人员管理</title>
<!-- <meta name="decorator" content="default" /> -->
<meta name="renderer" content="ie-comp" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="shortcut icon" href="${hplusStatic }favicon.ico">
<link href="${hplusStatic }/css/bootstrap.min14ed.css?v=3.3.6"
	rel="stylesheet">
<link href="${hplusStatic }/css/font-awesome.min93e3.css?v=4.4.0"
	rel="stylesheet">
<link
	href="${hplusStatic }/css/plugins/bootstrap-table/bootstrap-table.min.css"
	rel="stylesheet">
<link href="${hplusStatic }/css/plugins/awesome-bootstrap-checkbox/awesome-bootstrap-checkbox.css" rel="stylesheet">
<link href="${hplusStatic }/css/animate.min.css" rel="stylesheet">
<link href="${hplusStatic }/css/style.min862f.css?v=4.1.0"
	rel="stylesheet">
<script type="text/javascript">
	$(document).ready(function() {

	});
	function page(n, s) {
		$("#pageNo").val(n);
		$("#pageSize").val(s);
		$("#searchForm").submit();
		return false;
	}
</script>
</head>
<body>
	<div class="wrapper wrapper-content animated fadeInRight">
		<div class="ibox float-e-margins">
			<div class="ibox-content">
				<div class="row row-lg">
					<div class="col-sm-12">
						<h4 class="text-navy">待测量表</h4>
						<div class="example" style="${displayStyle}">
							<table id="scaleCheckingTable" data-toggle="table"
								data-query-params="queryParams" data-mobile-responsive="true"
								>
								<thead>
									<tr>
										<th data-field="batchNumber">量表名称</th>
										<th data-field="startTime">开始时间</th>
										<th data-field="endTime">结束时间</th>
										<th data-field="integral">所需积分</th>
										<th data-field=""></th>
									</tr>
								</thead>
								<tbody>
								<c:forEach items="${pageDc.list}" var="scaleTaskUserDc">
									<tr>
										<td>${scaleTaskUserDc.scaleName}</td>
										<td><fmt:formatDate value="${scaleTaskUserDc.startDate}"
												pattern="yyyy-MM-dd HH:mm:ss" /></td>
										<td><fmt:formatDate value="${scaleTaskUserDc.endDate}"
												pattern="yyyy-MM-dd HH:mm:ss" /></td>
										<td>${scaleTaskUserDc.integral}</td>
										<td>   
												<c:set var="nowDate">
										 			<fmt:formatDate value="<%=new java.util.Date()%>" pattern="yyyy-MM-dd HH:mm:ss" type="date"/>  
    											</c:set>
    											<c:set var="startDate">  
        										<fmt:formatDate value="${scaleTaskUserDc.startDate}" pattern="yyyy-MM-dd HH:mm:ss" type="date"/>  
    											</c:set>   
    											<c:set var="endDate">  
        										<fmt:formatDate value="${scaleTaskUserDc.endDate}" pattern="yyyy-MM-dd HH:mm:ss" type="date"/>  
    											</c:set>  
    											<c:choose>
    												<c:when test="${nowDate<startDate}">
        												测评时间未到
    												</c:when>
    												<c:when test="${nowDate>endDate}">
        												${nowDate}
    												</c:when>
    												<c:otherwise>
    													<shiro:hasPermission name="jzmk:scale:edit">
        												<a href="${ctx}/jzmk/scale/quiz?id=${scaleTaskUserDc.tid}&taskId=${scaleTaskUserDc.taskId}">进入测评</a>
        												</shiro:hasPermission>
    												</c:otherwise>
												</c:choose>
        								</td>
									</tr>
								</c:forEach>
								</tbody>
							</table>
						</div>
						<div class="example" style="${displayStyleCc}">
							<table id="scaleCheckingTableCc" data-toggle="table"
								data-query-params="queryParams" data-mobile-responsive="true"
								>
								<thead>
									<tr>
										<th data-field="batchNumber">量表名称</th>
										<th data-field="startTime">原开始时间</th>
										<th data-field="endTime">原结束时间</th>
										<th data-field="integral">所需积分</th>
										<th data-field=""></th>
									</tr>
								</thead>
								<tbody>
								<c:forEach items="${pageCc.list}" var="scaleTaskUserCc">
									<tr>
										<td>${scaleTaskUserCc.scaleName}</td>
										<td><fmt:formatDate value="${scaleTaskUserCc.startDate}"
												pattern="yyyy-MM-dd HH:mm:ss" /></td>
										<td><fmt:formatDate value="${scaleTaskUserCc.endDate}"
												pattern="yyyy-MM-dd HH:mm:ss" /></td>
										<td>${scaleTaskUserCc.integral}</td>
										<td>   
        									<a href="${ctx}/jzmk/scale/quiz?id=${scaleTaskUserCc.tid}&taskId=${scaleTaskUserCc.taskId}&taskNumber=${scaleTaskUserCc.taskNumber}">进入重测</a>
        								</td>
									</tr>
								</c:forEach>
								</tbody>
							</table>
						</div>
						<div class="example" style="">
							<div>
								<h4 class="example-title">您有以下量表未做完，请申请重测</h4>
							</div>
							<table id="scaleCheckTaskTable" data-toggle="table"
								data-query-params="queryParams" data-mobile-responsive="true"
								data-pagination="true">
								<thead>
									<tr>
										<th data-field="" data-checkbox="true" aria-hidden="true">选择</th>
										<th data-field="id" data-visible="false">ID</th>
										<!--  
										<th data-field="choose">选择</th>
										-->
										<th data-field="scaleName">量表名称</th>
										<th data-field="startTime">开始时间</th>
										<th data-field="endTime">结束时间</th>
										<th data-field="integral">所需积分</th>
									</tr>
								</thead>
								<tbody>
								<c:forEach items="${page.list}" var="scaleTaskUser">
									<tr>
										<td data-field="id" data-checkbox="true" aria-hidden="true">${scaleTaskUser.id}</td>
										<td>${scaleTaskUser.id}</td>
										<td>${scaleTaskUser.scaleName}</td>
										<td><fmt:formatDate value="${scaleTaskUser.startTime}"
												pattern="yyyy-MM-dd HH:mm:ss" /></td>
										<td><fmt:formatDate value="${scaleTaskUser.endTime}"
												pattern="yyyy-MM-dd HH:mm:ss" /></td>
										<td>${scaleTaskUser.integral}</td>
									</tr>
								</c:forEach>
								</tbody>
							</table>
							<%-- <div class="pagination">${page}</div> --%>
							<div>
									<button id="btnCheckAgain" class="btn btn-primary" onclick="checkAgain();">申请重测</button>
							</div>
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
	<script
		src="${hplusStatic }/js/plugins/bootstrap-table/bootstrap-table.min.js"></script>
	<script
		src="${hplusStatic }/js/plugins/bootstrap-table/bootstrap-table-mobile.min.js"></script>
	<script
		src="${hplusStatic }/js/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>
	<script src="${hplusStatic }/js/demo/bootstrap-table-demo.min.js"></script>
	<script src="${hplusStatic }/js/plugins/layer/layer.min.js"
		type="text/javascript"></script>
	<script src="${ctxStatic}/My97DatePicker/WdatePicker.js"></script>
	<!-- <script src="http://tajs.qq.com/stats?sId=9051096" charset="UTF-8" type="text/javascript"></script> -->
	<script type="text/javascript">
		function checkAgain()
		{
				var a = $.map($("#scaleCheckTaskTable").bootstrapTable('getSelections'), function (row) {
	                 return row.id;
	             });
				if(a.length<=0)
	    		{
	    			layer.alert('请选择量表');
			    	return false;
	    		}
	    		else
	    		{
	    			var ids=JSON.stringify(a);
	    			//alert('${ctx}/jzmk/scaleTaskUser/dc/scale/list?ids='+ids);
	   				document.location.href='${ctx}/jzmk/scaleTaskUser/dc/scale/list?ids='+ids;
	    		}
		}
		function delOrgAffiche(id, name) {
			layer.confirm(
							'是否删除 (' + name + ') 这条测评任务？',
							{
								btn : [ '确认', '取消' ], //按钮
								title : '删除确认',
								shade : false
							//不显示遮罩
							},
							function() {
								document.location.href = "${ctx}/jzmk/scaleCheckTask/delete?id="
										+ id;
								layer.msg('删除成功！', {
									icon : 6
								});
							}, function() {
								layer.msg('已取消', {
									icon : 1
								});
							});
		}
	</script>
</body>
</html>