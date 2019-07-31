<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>评测结果管理</title>
	<!-- <meta name="decorator" content="default"/> -->
	<link href="${hplusStatic }/css/bootstrap.min14ed.css?v=3.3.6"
	rel="stylesheet">
	<link href="${hplusStatic }/css/font-awesome.min93e3.css?v=4.4.0"
	rel="stylesheet">
	<link href="${hplusStatic }/css/plugins/bootstrap-table/bootstrap-table.min.css"
	rel="stylesheet">
	<link href="${hplusStatic }/css/animate.min.css" rel="stylesheet">
	<link href="${hplusStatic }/css/style.min862f.css?v=4.1.0"
	rel="stylesheet">
	<script type="text/javascript">
		$(document).ready(function() {
			if($("#epTaskId").val()!="")
			{
				$('#cbTaskId').prop('checked',true);
				$('#taskId').val($("#epTaskId").val());
			}
			if($("#epScaleId").val()!="")
			{
				$('#cbScaleId').prop('checked',true);
				$('#scaleId').val($("#epScaleId").val());
			}
		});
		function page(n,s){
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
						<h4 class="example-title">测评数据导出</h4>
						<!-- 
						<div>
							<button id="btnPrint" class="btn btn-primary" onclick="printContent('printPage');">打印</button>
						</div>
						 -->
						<div class="form-horizontal">
							 <div class="form-group">
								<label class="col-sm-3 control-label"><input type="checkbox" id="cbTaskId" class="control-checkbox"/>测评批次：</label>
								<div class="col-sm-8">
									<select class="form-control m-b" name="taskId" id="taskId" 
										required="required">
										<c:forEach items="${scaleCheckResults }" var="scaleCheckResult">
											<option value="${scaleCheckResult.taskId }" hassubinfo="true">${scaleCheckResult.batchNumber }</option>
										</c:forEach>
									</select>
							 	</div>
							 </div>
							 <div class="form-group">
								<label class="col-sm-3 control-label"><input type="checkbox" id="cbScaleId" class="control-checkbox"/>量表名称：</label>
								<div class="col-sm-8">
									<select class="form-control m-b" name="scaleId" id="scaleId" 
										required="required">
										<c:forEach items="${scales }" var="scale">
											<option value="${scale.id }" hassubinfo="true">${scale.name }</option>
										</c:forEach>
									</select>
								</div>
							 </div>
						</div>
						<div class="form-group">
                                <div class="col-sm-8 col-sm-offset-3">
                                    <button id="btnQuery" class="btn btn-primary"
								onclick="queryResult();">查看</button>
								<button id="btnExport" class="btn btn-primary"
								onclick="javascript:exportUser();">导出</button>
                                </div>
                        </div>
						<div class="example" id="printPage" style="${displayStyle}">
							<table id="scaleCheckTaskTable" data-toggle="table"
								data-query-params="queryParams" data-mobile-responsive="true"
								data-pagination="true">
								<thead>
									<tr>
										<th>用户名</th>
										<th>姓名</th>
										<th>测评批次</th>
										<th>量表名称</th>
										<th>因子名称</th>
										<th>原始分</th>
										<th>因子分</th>
									</tr>
								</thead>
								<tbody>
								<c:forEach items="${page.list}" var="scaleCheckResult">
								<tr>
									<td>${scaleCheckResult.userLoginName}</td>
									<td>${scaleCheckResult.userName}</td>
									<td>${scaleCheckResult.batchNumber}</td>
									<td>${scaleCheckResult.scaleName}</td>
									<td>${scaleCheckResult.geneName}</td>
									<td>${scaleCheckResult.originalScore}</td>
									<td>${scaleCheckResult.geneScore}</td>
								</tr>
								</c:forEach>
								</tbody>
							</table>
							<div>
							<form id="exportForm" action="${ctx}/jzmk/scaleCheckResult/exportFile" method="post">
							<input type="hidden" id="epTaskId" name="epTaskId" value="${epTaskId}"/>
							<input type="hidden" id="epScaleId" name="epScaleId" value="${epScaleId}"/>
							</form>
							</div>
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
		function queryResult()
		{
			var queryTaskId="";
			var queryScaleId="";
			if($("#cbTaskId").prop("checked"))
			{
				queryTaskId = $("#taskId option:selected").val();
			}
			if($("#cbScaleId").prop("checked"))
			{
				queryScaleId = $("#scaleId option:selected").val();
			}
			$("#epTaskId").val(queryTaskId);
			$("#epScaleId").val(queryScaleId);
			location.href = "${ctx}/jzmk/scaleCheckResult/exportQuery?scaleId="+queryScaleId+"&taskId="+queryTaskId;
		}
		function exportUser()
		{
			//layer.alert("导出用户数据");
			$("#exportForm").submit();
			//location.href="${ctx}/jzmk/scaleCheckResult/exportFile";
		}
		function printContent(printpage)
		{
			var headstr = "<html><head><title></title></head><body>"; 
			var footstr = "</body>"; 
			var newstr = document.all.item(printpage).innerHTML; 
			var oldstr = document.body.innerHTML; 
			document.body.innerHTML = headstr+newstr+footstr; 
			window.print(); 
			document.body.innerHTML = oldstr; 
			return false; 
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
