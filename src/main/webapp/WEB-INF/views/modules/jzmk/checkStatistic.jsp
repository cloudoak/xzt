<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>评测结果管理</title>
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
<link
	href="${ctxStatic}/jquery-ztree/3.5.12/css/zTreeStyle/zTreeStyle.min.css"
	rel="stylesheet" type="text/css" />
<link href="${ctxStatic}/jquery-ztree/3.5.12/css/zTreeStyle/dropdownTreeStyle.css" rel="stylesheet" type="text/css" />
<style type="text/css">
.ztree {
	overflow: auto;
	margin: 0;
	_margin-top: 10px;
	padding: 10px 0 0 10px;
}
</style>
<script type="text/javascript">
        var dropdownTree;
		$(document).ready(function() {
			dropdownTree = $("#orgInput").dropdownTree({
		    	root: "${rootParentId}",
	            async: {
	            	url: "${ctx}/sys/org/asynTree",
	            	type: "json",
	            	params: ["id"]
	            },
	            selectValue: orgId
		    });
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
						<h4 class="example-title">测评结果统计</h4>
						<!-- 
						<div>
							<button id="btnPrint" class="btn btn-primary" onclick="printContent('printPage');">打印</button>
						</div>
						 -->
						<div class="form-horizontal">
							<div class="form-group">
								<label class="col-sm-3 control-label">量表名称：</label>
								<div class="col-sm-8">
									<select class="form-control m-b" name="scaleId" id="scaleId" 
										required="required">
										<c:forEach items="${scales }" var="scale">
											<option value="${scale.id }" hassubinfo="true">${scale.name }</option>
										</c:forEach>
									</select>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label"><input type="checkbox" id="cbTaskId" class="control-checkbox"/>测评批次：</label>
								<div class="col-sm-8">
									<select class="form-control m-b" name="userTypeId"
										required="required">
										<c:forEach items="${scaleCheckResults }"
											var="scaleCheckResult">
											<option value="${scaleCheckResult.taskId }" hassubinfo="true">${scaleCheckResult.batchNumber }</option>
										</c:forEach>
									</select>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label"><input type="checkbox" id="cbCheckTime" class="control-checkbox"/>测评时间：</label>
								<div class="col-sm-8">
									<input name="startTime" id="startTime" type="text" readonly="readonly"
										maxlength="20" class="input-medium Wdate col-sm-3"
										value="<fmt:formatDate value="<%=new java.util.Date()%>" pattern="yyyy-MM-dd"/>"
										onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" />
									<label class="col-sm-1 control-label">至</label> <input
										name="endTime" id="endTime" type="text" readonly="readonly" maxlength="20"
										class="input-medium Wdate col-sm-3"
										value="<fmt:formatDate value="<%=new java.util.Date()%>" pattern="yyyy-MM-dd"/>"
										onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label"><input type="checkbox" id="cbUserType" class="control-checkbox"/>用户类型：</label>
								<div class="col-sm-8">
									<select class="form-control m-b" name="userTypeId"
										required="required">
										<option value="0" hassubinfo="true">咨询师</option>
										<option value="1" hassubinfo="true">来访者</option>
										<option value="2" hassubinfo="true">家属</option>
									</select>
								</div>
							</div>
							<div class="form-group">
								<input type="hidden" id="orgId" name="orgId" />
								<label class="col-sm-3 control-label">
									<input type="checkbox" id="cbOrgId" class="control-checkbox"/>组织：</label>
									<div class="col-sm-8">
			                        <input id="orgInput" name="orgInput" type="text" class="form-control" readonly="readonly" />
			                        </div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label"><input type="checkbox" id="cbDepartId" class="control-checkbox"/>所在部门：</label>
								<div class="col-sm-8">
									<select class="form-control m-b" name="typeId"
										required="required">
										<c:forEach items="${departmentList }" var="department">
											<option value="${department.id }" hassubinfo="true">${department.deptName }</option>
										</c:forEach>
									</select>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label"><input type="checkbox" id="cbCounselorType" class="control-checkbox"/>咨询师类型：</label>
								<div class="col-sm-8">
									<select class="form-control m-b" name="counselorTypeId"
										required="required">
										<c:forEach items="${counselorTypes }" var="counselorType">
											<option value="${counselorType.id }" hassubinfo="true">${counselorType.typeName }</option>
										</c:forEach>
									</select>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label"><input type="checkbox" id="cbAge" class="control-checkbox"/>年龄：</label>
								<div class="col-sm-8">
									<label class="col-sm-1 control-label">从</label> <input
										name="beginAge" id="beginAge" class="col-sm-4" type="number" />
									<label class="col-sm-1 control-label">到</label> <input
										name="endAge" id="endAge" class="col-sm-4" type="number" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label"><input type="checkbox" id="cbGender" class="control-checkbox"/>性别：</label>
								<div class="col-sm-8">
									<input id="male" type="radio" name="sex" class="col-sm-3" /> <label
										class="col-sm-1 control-label">男 </label> <input id="female"
										type="radio" name="sex" class="col-sm-3" /> <label
										class="col-sm-1 control-label">女</label>
								</div>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-8 col-sm-offset-3">
								<button id="btnQuery" class="btn btn-primary"
									onclick="javascript:queryResult();">确定</button>
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
	<script
		src="${ctxStatic}/jquery-ztree/3.5.12/js/jquery.ztree.all-3.5.min.js"
		type="text/javascript"></script>
	<script src="${ctxStatic}/jquery-ztree/3.5.12/js/jquery-dropdowntree.min.js" type="text/javascript"></script>
	<script type="text/javascript">
		//${ctx}/sys/delete?id=${office.id}
		function queryResult()
		{
			var queryParameters="";	
			if($("#cbCheckTime").prop("checked"))
			{
				queryStartTime = $("#startTime").val();
				queryEndTime = $("#endTime").val();
				if(queryStartTime>queryEndTime)
				{
					layer.alert('开始时间不能大于结束时间');
					return;
				}
				if(queryStartTime!=null)
				{
					queryParameters +="&startTime=" + queryStartTime;
				}
				if(queryEndTime!=null)
				{
					queryParameters +="&endTime=" + queryEndTime;
				}
			}
			if($("#cbOrgId").prop("checked"))
			{
				queryOrgId = dropdownTree.getValue();
				if(queryOrgId!=null)
				{
					queryParameters +="&orgId=" + queryOrgId;
				}
			}
			console.log(queryParameters);
			location.href="${ctx}/jzmk/scaleCheckResult/statistic/result?tid="+$("#scaleId").val()+queryParameters;
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
