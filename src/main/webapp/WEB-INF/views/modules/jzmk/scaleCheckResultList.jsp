<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<!-- <meta name="decorator" content="default" /> -->
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>评测结果管理</title>
<meta name="keywords" content="${fns:getConfig('keywords')} ${sysConfig.schoolName } ">
<meta name="description" content="${fns:getConfig('description')} ${sysConfig.schoolName }">
<link href="${hplusStatic }/css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
<link href="${hplusStatic }/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
<link href="${hplusStatic }/css/plugins/bootstrap-table/bootstrap-table.min.css" rel="stylesheet">
<link href="${hplusStatic }/css/animate.min.css" rel="stylesheet">
<link href="${hplusStatic }/css/style.min862f.css?v=4.1.0"	rel="stylesheet">
<link href="${ctxStatic}/jquery-ztree/3.5.12/css/zTreeStyle/zTreeStyle.min.css"	rel="stylesheet" type="text/css" />
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
		//var epResultStateStr = '${epResultState}';
		dropdownTree = $("#orgInput").dropdownTree({
		    	root: "${rootParentId}",
	            async: {
	            	url: "${ctx}/sys/org/asynTree",
	            	type: "json",
	            	params: ["id"]
	            },
	            selectValue: orgId
		});
		if('${epResultState}'!="")
		{
			$('#cbResultState').prop('checked',true);
			$('#resultState').val('${epResultState}');
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
						<h4 class="text-navy">测评结果查看</h4>
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
										<c:forEach items="${scaleCheckResults }"
											var="scaleCheckResult">
											<option value="${scaleCheckResult.taskId }" hassubinfo="true">${scaleCheckResult.taskNumber }</option>
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
							<div class="form-group">
								<label class="col-sm-3 control-label"><input type="checkbox" id="cbUserType" class="control-checkbox"/>用户类型：</label>
								<div class="col-sm-8">
									<select class="form-control m-b" name="userTypeId" id="userTypeId"
										required="required">
										<option value="2" hassubinfo="true">咨询师</option>
										<option value="4" hassubinfo="true">来访者</option>
										<option value="3" hassubinfo="true">家属</option>
									</select>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label"><input type="checkbox" id="cbUserName" class="control-checkbox"/>用户名：</label>
								<div class="col-sm-8">
									<input id="userName" name="userName" type="text" class="form-control"/>
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
									<input id="male" type="radio" name="gender" class="col-sm-3" value="1"/> <label
										class="col-sm-1 control-label">男 </label> <input id="female"
										type="radio" name="gender" class="col-sm-3" value="0"/> <label
										class="col-sm-1 control-label">女</label>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label"><input type="checkbox" id="cbCheckTime" class="control-checkbox"/>测评时间：</label>
								<div class="col-sm-8">
									<input name="beginTime" id="beginTime" type="text" readonly="readonly"
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
								<label class="col-sm-3 control-label"><input type="checkbox" id="cbResultState" class="control-checkbox"/>状态：</label>
								<div class="col-sm-8">
									<select class="form-control m-b" name="resultState" id="resultState" 
										required="required">
											<option value="0" hassubinfo="true">未完成</option>
											<option value="2" hassubinfo="true">完成</option>
											<option value="1" hassubinfo="true">超时</option>
											<option value="3" hassubinfo="true">重测申请中</option>
											<option value="4" hassubinfo="true">已同意申请</option>
											<option value="5" hassubinfo="true">测谎未通过</option>
									</select>
								</div>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-8 col-sm-offset-3">
								<button id="btnQuery" class="btn btn-primary"
									onclick="javascript:queryResult();">确定</button>
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
										<th>性别</th>
										<th>年龄</th>
										<th>量表名称</th>
										<th>开始时间</th>
										<th>结束时间</th>
										<th>状态</th>
										<th>操作</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${page.list}" var="scaleCheckResult">
										<tr>
											<td>${scaleCheckResult.userLoginName}</td>
											<td>${scaleCheckResult.userName}</td>
											<td>${scaleCheckResult.userSex==1?"男":"女"}</td>
											<td>${scaleCheckResult.userAge}</td>
											<td>${scaleCheckResult.scaleName}</td>
											<td><fmt:formatDate
													value="${scaleCheckResult.startTime}"
													pattern="yyyy-MM-dd HH:mm:ss" /></td>
											<td><fmt:formatDate
													value="${scaleCheckResult.endTime}"
													pattern="yyyy-MM-dd HH:mm:ss" /></td>
											<td><c:choose>
													<c:when test="${scaleCheckResult.state==0}">
        											未完成
    												</c:when>
    												<c:when test="${scaleCheckResult.state==1}">
    												超时
													</c:when>
													<c:when test="${scaleCheckResult.state==2}">
        											完成
    												</c:when>
    												<c:when test="${scaleCheckResult.state==3}">
        											重测申请中
    												</c:when>
    												<c:when test="${scaleCheckResult.state==4}">
        											已同意申请
    												</c:when>
    												<c:when test="${scaleCheckResult.state==5}">
        											测谎未通过
    												</c:when>
												</c:choose>
											</td>
											<td><c:choose>
													<c:when test="${scaleCheckResult.state==0}">
													</c:when>
													<c:when test="${scaleCheckResult.state==1}">
													</c:when>
													<c:when test="${scaleCheckResult.state==2}">
														<a class="text-navy"
															href="${ctx}/jzmk/scale/evaluation?sid=${scaleCheckResult.tid}&taskId=${scaleCheckResult.taskId}&taskUserId=${scaleCheckResult.userId}&taskNumber=${scaleCheckResult.taskNumber}">[查看结果]</a>
													</c:when>
													<c:when test="${scaleCheckResult.state==3}">
														<a class="text-navy" href="#" onclick="agreeRecheck(${scaleCheckResult.id});">[同意]</a>
													</c:when>
													<c:when test="${scaleCheckResult.state==4}">
													</c:when>
													<c:when test="${scaleCheckResult.state==5}">
														<a class="text-navy"
															href="${ctx}/jzmk/scale/evaluation?sid=${scaleCheckResult.tid}&taskId=${scaleCheckResult.taskId}&taskUserId=${scaleCheckResult.userId}&taskNumber=${scaleCheckResult.taskNumber}">[查看结果]</a>
													</c:when>
												</c:choose></td>
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
	<script
		src="${ctxStatic}/jquery-ztree/3.5.12/js/jquery.ztree.all-3.5.min.js"
		type="text/javascript"></script>
	<script src="${ctxStatic}/jquery-ztree/3.5.12/js/jquery-dropdowntree.min.js" type="text/javascript"></script>
	<script type="text/javascript">
		//${ctx}/sys/delete?id=${office.id}
		function queryResult()
		{
			//$("#printPage").css("display","block");
			var queryParameters="";			
			var queryResultState="";
			if($("#cbTaskId").prop("checked"))
			{
				queryTaskId = $("#taskId option:selected").val();
				queryParameters +="taskId=" + queryTaskId;
			}
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
			if($("#cbUserType").prop("checked"))
			{
				queryUserType = $("#userTypeId option:selected").val();
				if(queryParameters.length>0)
				{
					queryParameters +="&userTypeId=" + queryUserType;
				}
				else
				{
					queryParameters +="userTypeId=" + queryUserType;
				}
			}
			if($("#cbUserName").prop("checked"))
			{
				queryUserName = $("#userName").val();
				if(queryParameters.length>0)
				{
					queryParameters +="&userName=" + queryUserName;
				}
				else
				{
					queryParameters +="userName=" + queryUserName;
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
			if($("#cbAge").prop("checked"))
			{
				queryBeginAge = $("#beginAge").val();
				queryEndAge = $("#endAge").val();
				if(queryBeginAge==''&&queryEndAge=='')
				{
					layer.alert('请至少填写一个年龄字段');
					return;
				}
				if(queryBeginAge!=''&&queryEndAge!=''&&queryBeginAge>queryEndAge)
				{
					layer.alert('开始年龄不能大于截止年龄');
					return;
				}
				if(queryParameters.length>0)
				{
					if(queryBeginAge!='')
					{
						queryParameters +="&beginAge=" + queryBeginAge;
					}
					if(queryEndAge!='')
					{
						queryParameters +="&endAge=" + queryEndAge;
					}
				}
				else
				{
					if(queryBeginAge!='')
					{
						queryParameters +="beginAge=" + queryBeginAge;
					}
					if(queryParameters.length>0)
					{
						if(queryEndAge!='')
						{
							queryParameters +="&endAge=" + queryEndAge;
						}
					}
					else
					{
						if(queryEndAge!='')
						{
							queryParameters +="endAge=" + queryEndAge;
						}
					}
				}
			}
			if($("#cbGender").prop("checked"))
			{
				queryGender = $("input:radio[name='gender']:checked").val();
				if(queryGender==null)
				{
					layer.alert('请勾选性别');
					return;
				}
				if(queryParameters.length>0)
				{
					queryParameters +="&gender=" + queryGender;
				}
				else
				{
					queryParameters +="gender=" + queryGender;
				}
			}
			if($("#cbCheckTime").prop("checked"))
			{
				queryBeginTime = $("#beginTime").val();
				queryEndTime = $("#endTime").val();
				if(queryBeginTime>queryEndTime)
				{
					layer.alert('开始时间不能大于结束时间');
					return;
				}
				if(queryParameters.length>0)
				{
					if(queryBeginTime!=null)
					{
						queryParameters +="&beginTime=" + queryBeginTime;
					}
					if(queryEndTime!=null)
					{
						queryParameters +="&endTime=" + queryEndTime;
					}
				}
				else
				{
					if(queryBeginTime!=null)
					{
						queryParameters +="beginTime=" + queryBeginTime;
					}
					if(queryParameters.length>0)
					{
						if(queryEndTime!=null)
						{
							queryParameters +="&endTime=" + queryEndTime;
						}
					}
					else
					{
						if(queryEndTime!=null)
						{
							queryParameters +="endTime=" + queryEndTime;
						}
					}
				}
			}
			if($("#cbResultState").prop("checked"))
			{
				queryResultState = $("#resultState option:selected").val();
				if(queryParameters.length>0)
				{
					queryParameters +="&resultState=" + queryResultState;
				}
				else
				{
					queryParameters +="resultState=" + queryResultState;
				}
			}
			$("#epResultState").val(queryResultState);
			/* location.href = "${ctx}/jzmk/scaleCheckResult/listQuery?resultState="+queryResultState; */
			console.log("${ctx}/jzmk/scaleCheckResult/listQuery?"+queryParameters);
			//alert("${ctx}/jzmk/scaleCheckResult/listQuery?"+queryParameters);
			location.href = "${ctx}/jzmk/scaleCheckResult/listQuery?"+queryParameters;
		}
		function agreeRecheck(id)
		{
			//$("#printPage").css("display","block");
			var queryResultState="";
			if($("#cbResultState").prop("checked"))
			{
				queryResultState = $("#resultState option:selected").val();
			}
			$("#epResultState").val(queryResultState);
			layer.confirm('是否同意申请重测？', {
			    btn: ['确认','取消'], //按钮
			    title:'同意重测',
			    shade: false //不显示遮罩
			}, function(){
				document.location.href="${ctx}/jzmk/scaleCheckResult/agreeRecheck?id="+id+"&resultState="+queryResultState;
			    layer.msg('同意成功！', {icon: 6});
			}, function(){
			    layer.msg('已取消', {icon: 1});
			});
		}
		function printContent(printpage) {
			var headstr = "<html><head><title></title></head><body>";
			var footstr = "</body>";
			var newstr = document.all.item(printpage).innerHTML;
			var oldstr = document.body.innerHTML;
			document.body.innerHTML = headstr + newstr + footstr;
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
