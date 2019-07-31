<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<!-- Mirrored from www.zi-han.net/theme/hplus/table_bootstrap.html by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 20 Jan 2016 14:20:03 GMT -->
<head>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>${sysConfig.schoolName }量表测评任务</title>
<!-- <meta name="decorator" content="default" /> -->
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
</head>
<body>
	<div class="wrapper wrapper-content animated fadeInRight">
		<div class="ibox float-e-margins">
			<div class="ibox-content">
				<div class="row row-lg">
					<div class="col-sm-12">
						<h4 class="text-navy">测评任务列表</h4>
						<div class="form-horizontal">
						<!-- <form class="form-horizontal m-t" id="checkTaskForm"> -->
								<%-- <input type="hidden" name="id" value="${scaleCheckTask.id }"> --%>
								<div class="form-group">
									<label class="col-sm-3 control-label"><input type="checkbox" id="cbBatchNumber" class="control-checkbox"/>&nbsp;&nbsp;&nbsp;测评批号：</label>
										<div class="col-sm-8">
											<input id="batchNumber" name="batchNumber" type="text"/>
										</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label"><input type="checkbox" id="cbStartTime" class="control-checkbox"/>&nbsp;&nbsp;&nbsp;开始时间：</label> 
									<div class="col-sm-8">
									<input id="startTime" name="startTime" type="text" readonly="readonly" maxlength="40" class="input-medium Wdate"
										value="<fmt:formatDate value="${scaleCheckTask.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
										onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label"><input type="checkbox" id="cbEndTime" class="control-checkbox"/>&nbsp;&nbsp;&nbsp;结束时间：</label> 
									<div class="col-sm-8">
									<input id="endTime" name="endTime" type="text" readonly="readonly" maxlength="40" class="input-medium Wdate"
										value="<fmt:formatDate value="${scaleCheckTask.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
										onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label"><input type="checkbox" id="cbPrincipalName" class="control-checkbox"/>负责人姓名：</label>
									<div class="col-sm-8">
										<input id="principalName" name="principalName" type="text"/>
									</div>
								</div>
								<div class="form-group">
									<div class="col-sm-8 col-sm-offset-3">
									<button id="btnQuery" class="btn btn-primary" onclick="queryResult();">查询</button>
									</div>
								</div>
								<!-- </form> -->
						</div>
						<div class="example">	
							<table id="scaleCheckTaskTable" data-toggle="table"
								data-query-params="queryParams" data-mobile-responsive="true"
								data-pagination="true">
								<thead>
									<tr>
										<th data-field="batchNumber">测评批号</th>
										<th data-field="startTime">开始时间</th>
										<th data-field="endTime">结束时间</th>
										<th data-field="principalName">负责人</th>
										<th data-field="joinScale">任务量表</th>
										<th data-field="joinUser">任务人员</th>
										<shiro:hasPermission name="jzmk:scaleCheckTask:edit">
											<th>操作</th>
										</shiro:hasPermission>
									</tr>
								</thead>
								<tbody>
								<c:forEach items="${page.list}" var="scaleCheckTask">
									<tr>
										<td>${scaleCheckTask.batchNumber}</td>
										<td><fmt:formatDate value="${scaleCheckTask.startTime}"
												pattern="yyyy-MM-dd HH:mm:ss" /></td>
										<td><fmt:formatDate value="${scaleCheckTask.endTime}"
												pattern="yyyy-MM-dd HH:mm:ss" /></td>
										<td>${scaleCheckTask.principalName}</td>
										<td><a class="text-navy"
											href="${ctx}/jzmk/scaleTaskList/list?taskId=${scaleCheckTask.id}">[量表]</a></td>
										<td><a class="text-navy"
											href="${ctx}/jzmk/scaleTaskUser/list?taskId=${scaleCheckTask.id}">[人员]</a></td>
										<td><shiro:hasPermission name="jzmk:scaleCheckTask:edit">
												<c:set var="nowTime">
													<fmt:formatDate value="<%=new java.util.Date()%>"
														pattern="yyyy-MM-dd HH:mm:ss" type="date" />
												</c:set>
												<c:set var="startTime">
													<fmt:formatDate value="${scaleCheckTask.startTime}"
														pattern="yyyy-MM-dd HH:mm:ss" type="date" />
												</c:set>
												<c:set var="endTime">
													<fmt:formatDate value="${scaleCheckTask.endTime}"
														pattern="yyyy-MM-dd HH:mm:ss" type="date" />
												</c:set>
												<c:choose>
													<c:when test="${nowTime<startTime}">
													[停止]
													&nbsp;&nbsp;
											        <a class="text-navy" href="#" onclick="delCheckTask(${scaleCheckTask.id})"> [删除] 
													</a>
													</c:when>
													<c:when test="${nowTime>endTime}">
													[停止]
													&nbsp;&nbsp;
													[删除]
    												</c:when>
													<c:otherwise>
														<c:if test="${scaleCheckTask.state==2}">
															[停止]
														</c:if>
														<c:if test="${scaleCheckTask.state!=2}">
														<a class="text-navy" href="#"
			      											onclick="stopCheckTask(${scaleCheckTask.id});"> [停止]
														</a>
														</c:if>
													&nbsp;&nbsp;
													[删除]
    												</c:otherwise>
												</c:choose>
											</shiro:hasPermission></td>
									</tr>
								</c:forEach>
								</tbody>
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
		$(document).ready(function() {
		if('${epBatchNumber}'!="")
		{
			$('#cbBatchNumber').prop('checked',true);
			$('#batchNumber').val('${epBatchNumber}');
		}
		if('${epStartTime}'!="")
		{
			$('#cbStartTime').prop('checked',true);
			$('#startTime').val('${epStartTime}');
		}
		if('${epEndTime}'!="")
		{
			$('#cbEndTime').prop('checked',true);
			$('#endTime').val('${epEndTime}');
		}
		if('${epPrincipalName}'!="")
		{
			$('#cbPrincipalName').prop('checked',true);
			$('#principalName').val('${epPrincipalName}');
		}
		});
		function queryResult()
		{
			var queryBatchNumber="";
			var queryStartTime="";
			var queryEndTime="";
			var queryPrincipalName="";
			if($("#cbBatchNumber").prop("checked"))
			{
				queryBatchNumber = $("#batchNumber").val();
			}
			if($("#cbStartTime").prop("checked"))
			{
				queryStartTime = $("#startTime").val();
			}
			if($("#cbEndTime").prop("checked"))
			{
				queryEndTime = $("#endTime").val();
			}
			if($("#cbPrincipalName").prop("checked"))
			{
				queryPrincipalName = $("#principalName").val();
			}
			location.href = "${ctx}/jzmk/scaleCheckTask/list?batchNumber="+queryBatchNumber+"&startTime="+queryStartTime+"&endTime="+queryEndTime+"&principalName="+queryPrincipalName;
		}
	    function stopCheckTask(id)
	    {
	    	layer.confirm('是否停止这条测评任务？', {
			    btn: ['确认','取消'], //按钮
			    title:'停止确认',
			    shade: false //不显示遮罩
			}, function(){
				document.location.href="${ctx}/jzmk/scaleCheckTask/stop?taskId="+id;
			    layer.msg('停止成功！', {icon: 6});
			}, function(){
			    layer.msg('已取消', {icon: 1});
			});
	    }
	    function delCheckTask(id)
	    {
	    	layer.confirm('是否删除 这条测评任务？', {
			    btn: ['确认','取消'], //按钮
			    title:'删除确认',
			    shade: false //不显示遮罩
			}, function(){
				document.location.href="${ctx}/jzmk/scaleCheckTask/delete?taskId="+id;
			    layer.msg('删除成功！', {icon: 6});
			}, function(){
			    layer.msg('已取消', {icon: 1});
			});
	    }
		function delOrgAffiche(id,name){
			layer.confirm('是否删除 ('+name+') 这条测评任务？', {
			    btn: ['确认','取消'], //按钮
			    title:'删除确认',
			    shade: false //不显示遮罩
			}, function(){
				document.location.href="${ctx}/jzmk/scaleCheckTask/delete?id="+id;
			    layer.msg('删除成功！', {icon: 6});
			}, function(){
			    layer.msg('已取消', {icon: 1});
			});
		}
	</script>
</body>
<!-- Mirrored from www.zi-han.net/theme/hplus/table_bootstrap.html by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 20 Jan 2016 14:20:06 GMT -->
</html>
