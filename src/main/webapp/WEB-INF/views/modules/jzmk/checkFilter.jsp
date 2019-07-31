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
		$("#scaleId").val("${nowScaleId}");
		if($("#epScaleId").val()!="")
		{
			$('#cbScaleId').prop('checked',true);
			$('#scaleId').val($("#epScaleId").val());
		}
		if($("#epGeneId").val()!="")
		{
			$('#cbGeneId').prop('checked',true);
			$('#geneId').val($("#epGeneId").val());
		}
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
						<input type="hidden" id="epScaleId" name="epScaleId" value="${epScaleId}"/>
						<input type="hidden" id="epGeneId" name="epGeneId" value="${epGeneId}"/>
						<input type="hidden" id="epOrgId" name="epOrgId" value="${epOrgId}"/>
						<h4 class="example-title">测评结果筛选</h4>
						<div class="form-horizontal">
							<div class="form-group">
								<label class="col-sm-3 control-label"><input type="checkbox" id="cbTaskId" class="control-checkbox"/>测评批次：</label>
								<div class="col-sm-8">
									<select class="form-control m-b" name="checkTaskId"
										required="required">
										<c:forEach items="${scaleCheckResults }"
											var="scaleCheckResult">
											<option value="${scaleCheckResult.taskId }" hassubinfo="true">${scaleCheckResult.batchNumber }</option>
										</c:forEach>
									</select>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label"><input type="checkbox" id="cbScaleId" class="control-checkbox" onclick="checkVerify();"/>量表名称：</label>
								<div class="col-sm-8">
									<select class="form-control m-b" name="scaleId" id="scaleId" 
										required="required" onchange="changeScale();">
										<c:forEach items="${scales }" var="scale">
											<option value="${scale.id }" hassubinfo="true">${scale.name }</option>
										</c:forEach>
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
								<label class="col-sm-3 control-label"><input type="checkbox" id="cbUserName" class="control-checkbox"/>用户名：</label>
								<div class="col-sm-8">
									<input id="userName" type="text" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label"><input type="checkbox" id="cbDepartId" class="control-checkbox"/>部门：</label>
								<div class="col-sm-8">
									<select class="form-control m-b" name="departId" id="departId"
										required="required">
										<c:forEach items="${departmentList }" var="department">
											<option value="${department.id }" hassubinfo="true">${department.deptName }</option>
										</c:forEach>
									</select>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label"><input type="checkbox" id="cbGeneId" class="control-checkbox" onclick="checkVerify();"/>因子：</label>
								<div class="col-sm-8">
									<select class="form-control m-b" name="geneId" id="geneId" 
										required="required">
										<c:forEach items="${genes }" var="gene">
											<option value="${gene.id }" hassubinfo="true">${gene.name }</option>
										</c:forEach>
									</select>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label"><input type="checkbox" id="cbGeneScoreRange" class="control-checkbox"/>因子得分范围：</label>
								<div class="col-sm-8">
									<input name="minScore" id="minScore" class="col-sm-4"
										type="number" /> <label class="col-sm-1 control-label">到</label>
									<input name="maxScore" id="maxScore" class="col-sm-4"
										type="number" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label"><input type="checkbox" id="cbItemScore" class="control-checkbox"/>题目得分：</label>
								<div class="col-sm-8">
									<label class="col-sm-1 control-label">第</label> <input
										name="questionNumber" id="questionNumber" class="col-sm-2"
										type="number" /> <label class="col-sm-3 control-label">题的选择序号为</label>
									<input name="answerNumber" id="answerNumber" class="col-sm-4"
										type="number" /> <label class="col-sm-2 control-label">(数字)</label>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label"><input type="checkbox" id="cbMaxMinScore" class="control-checkbox"/>最高分因子与最低分因子差值：</label>
								<div class="col-sm-8">
									<input id="diffValue1" type="text" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label"><input type="checkbox" id="cbMaxSecMaxScore" class="control-checkbox"/>最高分因子与次高分因子差值：</label>
								<div class="col-sm-8">
									<input id="diffValue2" type="text" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label"><input type="checkbox" id="cbMinSecMinScore" class="control-checkbox"/>最低分因子与次低分因子差值：</label>
								<div class="col-sm-8">
									<input id="diffValue3" type="text" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label"><input type="checkbox" id="cbGeneScore" class="control-checkbox"/>因子分等于：</label>
								<div class="col-sm-8">
									<input id="maxGeneScore" type="radio" name="geneScoreEqual"
										class="col-sm-2" /> <label class="col-sm-2 control-label">最高分
									</label> <input id="minGeneScore" type="radio" name="geneScoreEqual"
										class="col-sm-2" /> <label class="col-sm-2 control-label">最低分</label><input
										id="averageGeneScore" type="radio" name="geneScoreEqual"
										class="col-sm-2" /> <label class="col-sm-2 control-label">平均
									</label>
								</div>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-8 col-sm-offset-3">
								<button id="btnQuery" class="btn btn-primary"
									onclick="queryResult();">确定</button>
							</div>
							<div class="example" id="printPage" style="${displayStyle}">
								<table id="scaleCheckTaskTable" data-toggle="table"
									data-query-params="queryParams" data-mobile-responsive="true"
									data-pagination="true">
									<thead>
										<tr>
											<th>用户名</th>
											<th>姓名</th>
											<th>性别</th>
											<th>年龄</th>
											<th>身份</th>
											<th></th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${page.list}" var="scaleCheckResult">
											<tr>
												<td>${scaleCheckResult.userLoginName}</td>
												<td>${scaleCheckResult.userName}</td>
												<td>${scaleCheckResult.userSex==1?"男":"女"}</td>
												<td>${scaleCheckResult.userAge}</td>
												<td><c:choose>
													<c:when test="${scaleCheckResult.userType==0}">
														平台管理员
													</c:when>
													<c:when test="${scaleCheckResult.userType==1}">
														机构管理员
													</c:when>
													<c:when test="${scaleCheckResult.userType==2}">
														咨询师
													</c:when>
													<c:when test="${scaleCheckResult.userType==3}">
														家属
													</c:when>
													<c:when test="${scaleCheckResult.userType==4}">
														来访者
													</c:when>
												</c:choose>
												</td>
												<td>
													<a class="text-navy" href="${ctx}/jzmk/scale/evaluation?sid=${scaleCheckResult.taskScaleId}&taskId=${scaleCheckResult.taskId}&taskUserId=${scaleCheckResult.taskUserId}&taskNumber=${scaleCheckResult.taskNumber}">[测评结果]</a>
												</td>
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
<script src="${hplusStatic }/js/plugins/bootstrap-table/bootstrap-table.min.js"></script>
<script src="${hplusStatic }/js/plugins/bootstrap-table/bootstrap-table-mobile.min.js"></script>
<script src="${hplusStatic }/js/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>
<script src="${hplusStatic }/js/demo/bootstrap-table-demo.min.js"></script>
<script src="${hplusStatic }/js/plugins/layer/layer.min.js"></script>
<script src="${ctxStatic}/My97DatePicker/WdatePicker.js"></script>
<script	src="${ctxStatic}/jquery-ztree/3.5.12/js/jquery.ztree.all-3.5.min.js"></script>
<script src="${ctxStatic}/jquery-ztree/3.5.12/js/jquery-dropdowntree.min.js" type="text/javascript"></script>
<script type="text/javascript">
function queryResult(){
	//$("#printPage").css("display", "block");
	var queryParameters="";
	if($("#cbScaleId").prop("checked"))
	{
		queryScaleId = $("#scaleId option:selected").val();
		if(queryParameters.length>0)
		{
			queryParameters +="&scaleId=" + queryScaleId;
		}
		else
		{
			queryParameters +="scaleId=" + queryScaleId;
		}
	}
	if($("#cbGeneId").prop("checked"))
	{
		queryScaleId = $("#geneId option:selected").val();
		if(queryParameters.length>0)
		{
			queryParameters +="&geneId=" + queryScaleId;
		}
		else
		{
			queryParameters +="geneId=" + queryScaleId;
		}
	}
	if($("#cbOrgId").prop("checked"))
	{
		queryOrgId = dropdownTree.getValue();
		if(queryParameters.length>0)
		{
			queryParameters +="&orgId=" + queryOrgId;
		}
		else
		{
			queryParameters +="orgId=" + queryOrgId;
		}
	}
	location.href = "${ctx}/jzmk/scaleCheckResult/filterQuery?"+queryParameters;
}
function changeScale()
{
	//$("#printPage").css("display","block");
	location.href = "${ctx}/jzmk/scaleCheckResult/filter?scaleId="+$("#scaleId").val();
}
function checkVerify(){
	if($("#cbGeneId").prop("checked"))
	{
		$('#cbScaleId').prop('checked',true);
	}
}
function printContent(printpage){
	var headstr = "<html><head><title></title></head><body>";
	var footstr = "</body>";
	var newstr = document.all.item(printpage).innerHTML;
	var oldstr = document.body.innerHTML;
	document.body.innerHTML = headstr + newstr + footstr;
	window.print();
	document.body.innerHTML = oldstr;
	return false;
}
</script>
</body>
</html>
