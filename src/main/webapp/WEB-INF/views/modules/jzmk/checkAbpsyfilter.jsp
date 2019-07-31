<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>评测结果管理</title>
<meta name="keywords" content="${fns:getConfig('keywords')} ${sysConfig.schoolName } ">
<meta name="description" content="${fns:getConfig('description')} ${sysConfig.schoolName }">
<link href="${hplusStatic }/css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
<link href="${hplusStatic }/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
<link href="${hplusStatic }/css/plugins/bootstrap-table/bootstrap-table.min.css" rel="stylesheet">
<link href="${hplusStatic }/css/animate.min.css" rel="stylesheet">
<link href="${hplusStatic }/css/style.min862f.css?v=4.1.0" rel="stylesheet">
<link href="${ctxStatic}/jquery-ztree/3.5.12/css/zTreeStyle/zTreeStyle.min.css" rel="stylesheet" type="text/css" />
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
		
	</script>
</head>
<body>
	<div class="wrapper wrapper-content animated fadeInRight">
		<div class="ibox float-e-margins">
			<div class="ibox-content">
				<div class="row row-lg">
					<div class="col-sm-12">
						<h4 class="example-title">心理异常筛选</h4>
						<!-- 
						<div>
							<button id="btnPrint" class="btn btn-primary" onclick="printContent('printPage');">打印</button>
						</div>
						 -->
						<div class="form-horizontal">
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
								<label class="col-sm-3 control-label"><input type="checkbox" id="cbGeneId" class="control-checkbox" onclick="checkVerify();"/>因子名称：</label>
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
								<label class="col-sm-3 control-label"><input type="checkbox" id="cbTaskId" class="control-checkbox"/>测评批次：</label>
								<div class="col-sm-8">
									<select class="form-control m-b" name="taskId"
										required="required">
										<c:forEach items="${scaleCheckScores }"
											var="scaleCheckScore">
											<option value="${scaleCheckScore.taskNumber }" hassubinfo="true">${scaleCheckScore.taskNumber }</option>
										</c:forEach>
									</select>
								</div>
							</div>
							<!-- <div class="form-group">
								<label class="col-sm-3 control-label"><input type="checkbox" id="cbOrgId" class="control-checkbox"/>组织：</label>
								<div class="col-sm-8 ztree" id="ztree"></div>
							</div> -->
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
									<input id="userName" name="userName" type="text" class="form-control" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label"><input type="checkbox" id="cbDepartId" class="control-checkbox"/>部门：</label>
								<div class="col-sm-8">
									<select class="form-control" name="departId" id="departId" 
										required="required">
										<c:forEach items="${departmentList }" var="department">
											<option value="${department.id }" hassubinfo="true">${department.deptName }</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</div>
						<div class="form-group">
                                <div class="col-sm-8 col-sm-offset-3">
                                    <button id="btnQuery" class="btn btn-primary"
								onclick="queryResult();">确定</button>
                        </div>
						<div class="example" id="printPage" style="${displayStyle}">
						<div class="example">
                        	<h4 class="col-sm-12"></h4>
                            <div id="chart" class="col-sm-12 col-sm-offset-2" style="width: 600px;height:400px;">
                            </div>
                        </div>
						<div class="form-group">
                                <div class="col-sm-8 col-sm-offset-0">
                                    <button id="btnExport" class="btn btn-primary"
								onclick="exportData();">导出</button>
                        </div>
							<table id="scaleCheckTaskTable" data-toggle="table"
								data-query-params="queryParams" data-mobile-responsive="true"
								data-pagination="true">
								<thead>
									<tr>
										<th>用户名</th>
										<th>姓名</th>
										<th>身份</th>
										<th>量表名称</th>
										<th>因子名称</th>
										<th>因子分</th>
										<th></th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${page.list}" var="scaleCheckScore">
										<tr>
											<td>${scaleCheckScore.loginName }</td>
											<td>${scaleCheckScore.userName }</td>
											<td><c:choose>
													<c:when test="${scaleCheckScore.userType==0}">
														平台管理员
													</c:when>
													<c:when test="${scaleCheckScore.userType==1}">
														机构管理员
													</c:when>
													<c:when test="${scaleCheckScore.userType==2}">
														咨询师
													</c:when>
													<c:when test="${scaleCheckScore.userType==3}">
														家属
													</c:when>
													<c:when test="${scaleCheckScore.userType==4}">
														来访者
													</c:when>
												</c:choose>
											</td>
											<td>${scaleCheckScore.scaleName }</td>
											<td>${scaleCheckScore.geneName }</td>
											<td>${scaleCheckScore.score2 }</td>
											<td>
											<a class="text-navy" href="${ctx}/jzmk/scale/evaluation?sid=${scaleCheckScore.rid}&taskNumber=${scaleCheckScore.taskNumber}">[详细]</a>
											</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
							<form id="exportForm" action="${ctx}/jzmk/scaleCheckResult/exportAbpsyFile" method="post">
							<input type="hidden" id="epTaskId" name="epTaskId" value="${epTaskId}"/>
							<input type="hidden" id="epScaleId" name="epScaleId" value="${epScaleId}"/>
							<input type="hidden" id="epGeneId" name="epGeneId" value="${epGeneId}"/>
							<input type="hidden" id="epOrgId" name="epOrgId" value="${epOrgId}"/>
							<input type="hidden" id="epUserName" name="epUserName" value="${epUserName}"/>
							</form>
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
	<script src="${ctxStatic}/jquery-ztree/3.5.12/js/jquery.ztree.all-3.5.min.js" type="text/javascript"></script>
	<script src="${ctxStatic}/jquery-ztree/3.5.12/js/jquery-dropdowntree.min.js" type="text/javascript"></script>
	<script src="${ctxStatic}/echarts/echarts.js"></script>
	<script type="text/javascript">
		//${ctx}/sys/delete?id=${office.id}
		var queryResult;
		$(document).ready(function() {
			/* var orgId = $("#orgId").val(); */
			$("#scaleId").val("${nowScaleId}");
			var dropdownTree = $("#orgInput").dropdownTree({
		    	root: "${rootParentId}",
	            async: {
	            	url: "${ctx}/sys/org/asynTree",
	            	type: "json",
	            	params: ["id"]
	            },
	            selectValue: orgId
		    });
			
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
			if($("#epGeneId").val()!="")
			{
				$('#cbGeneId').prop('checked',true);
				$('#geneId').val($("#epGeneId").val());
			}
			/* if($("#epOrgId").val()!="")
			{
				$('#cbOrgId').prop('checked',true);
				$("#orgInput").dropdownTree({
			    	root: "${rootParentId}",
		            async: {
		            	url: "${ctx}/sys/org/asynTree",
		            	type: "json",
		            	params: ["id"]
		            },
		            selectValue: $("#epOrgId").val()
			    });
			} */
			
			queryResult = function () {
				//$("#printPage").css("display","block");
				$("#orgId").val(dropdownTree.getValue());
				console.log(dropdownTree.getValue());
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
				if($("#cbGeneId").prop("checked"))
				{
					queryGeneId = $("#geneId option:selected").val();
					if(queryParameters.length>0)
					{
						queryParameters +="&geneId=" + queryGeneId;
					}
					else
					{
						queryParameters +="geneId=" + queryGeneId;
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
				location.href = "${ctx}/jzmk/scaleCheckResult/abpsyfilterQuery?"+queryParameters;
			};
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
		
		function exportData()
		{
			//layer.alert("数据导出");
			$("#exportForm").submit();
		}
		function changeScale()
		{
			//$("#printPage").css("display","block");
			location.href = "${ctx}/jzmk/scaleCheckResult/abpsyfilter?scaleId="+$("#scaleId").val();
		}
		function checkVerify(){
			if($("#cbGeneId").prop("checked"))
			{
				$('#cbScaleId').prop('checked',true);
			}
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
		var option = {
			    title : {
			        text: '结果正常异常状态',
			        subtext: '统计饼图',
			        x:'center'
			    },
			    tooltip : {
			        trigger: 'item',
			        formatter: "{a} <br/>{b} : {c} ({d}%)"
			    },
			    legend: {
			        orient: 'vertical',
			        left: 'left',
			        data: ['正常','异常']
			    },
			    series : [
			        {
			            name: '测评结果',
			            type: 'pie',
			            radius : '55%',
			            center: ['50%', '60%'],
			            data:[
			            	<c:if test="${displayStyle=='display:block'}">
			                {value:${normalCount}, name:'正常'},
			                {value:${abnormalCount}, name:'异常'}
			                </c:if>
			            ],
			            itemStyle: {
			                emphasis: {
			                    shadowBlur: 10,
			                    shadowOffsetX: 0,
			                    shadowColor: 'rgba(0, 0, 0, 0.5)'
			                }
			            }
			        }
			    ]
			};
		var myChart = echarts.init(document.getElementById('chart'));
		myChart.setOption(option);
	</script>
</body>
</html>
