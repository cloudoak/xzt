<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>漏测量表</title>
<meta name="decorator" content="default" />
<link rel="shortcut icon" href="${hplusStatic }favicon.ico">
<link href="${hplusStatic }/css/bootstrap.min14ed.css?v=3.3.6"
	rel="stylesheet">
<link href="${hplusStatic }/css/font-awesome.min93e3.css?v=4.4.0"
	rel="stylesheet">
<link
	href="${hplusStatic }/css/plugins/bootstrap-table/bootstrap-table.min.css"
	rel="stylesheet">
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
						<h4 class="text-navy">测评记录</h4>
						<div class="example">
							<table id="scaleCheckTaskTable" data-toggle="table"
								data-query-params="queryParams" data-mobile-responsive="true"
								data-pagination="true">
								<thead>
									<tr>
										<th data-field="scaleName">量表名称</th>
										<th data-field="startTime">开始时间</th>
										<th data-field="endTime">结束时间</th>
										<th data-field="operate"></th>
									</tr>
								</thead>
								<tbody>
								<c:forEach items="${page.list}" var="scaleTaskUser">
									<tr>
										<td>${scaleTaskUser.scaleName}</td>
										<td><fmt:formatDate value="${scaleTaskUser.startTime}"
												pattern="yyyy-MM-dd HH:mm:ss" /></td>
										<td><fmt:formatDate value="${scaleTaskUser.endTime}"
												pattern="yyyy-MM-dd HH:mm:ss" /></td>
										<!--
										<td><a class="text-navy"
											href="${ctx}/jzmk/scaleCheckResult/list?taskId=${scaleTaskUser.taskId}">[测评结果]</a></td> 
										 -->		
										<td><a class="text-navy"
											href="${ctx}/jzmk/scale/evaluation?sid=${scaleTaskUser.tid}&taskId=${scaleTaskUser.taskId}&taskUserId=${scaleTaskUser.userId}&taskNumber=${scaleTaskUser.taskNumber}">[测评结果]</a></td>
									</tr>
								</c:forEach>
								</tbody>
							</table>
							<%-- <div class="pagination">${page}</div> --%>
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
		//${ctx}/sys/delete?id=${office.id}
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