<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>${sysConfig.schoolName }测评结果管理</title>
<meta name="keywords"
	content="${fns:getConfig('keywords')} ${sysConfig.schoolName } ">
<meta name="description"
	content="${fns:getConfig('description')} ${sysConfig.schoolName }">

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

</head>

<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeInRight">
		<div class="ibox float-e-margins">
			<div class="ibox-content">
				<div class="row row-lg">
					<div class="col-sm-12">
						<h4 class="text-navy">
							“${scaleName}”测评结果<img src="${ctxStatic}/images/print.png"
								onmouseover="this.src='${ctxStatic}/images/print_f2.png'"
								onmouseout="this.src='${ctxStatic}/images/print.png'"
								onclick="printResult();" title="打印" align="right">
						</h4>
						<div id="myPrintArea">
							<div class="example">
								<h4 class="col-sm-12 col-sm-offset-5">基本信息</h4>
								<table id="exampleTable" data-toggle="table"
									data-query-params="queryParams" data-mobile-responsive="true"
									data-pagination="false" data-search="false">
									<thead>
										<tr>
											<th data-field="number">测量批号</th>
											<th data-field="name">姓名</th>
											<th data-field="enname">性别</th>
											<th data-field="score">年龄</th>
											<th data-field="genescore">测试日期</th>
											<th data-field="demarcationcriteria">测试耗时</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td>${scaleCheckResultList[0].batchNumber}</td>
											<td>${user.name}</td>
											<td>${userSex==1?"男":"女"}</td>
											<td>${userAge}</td>
											<c:set var="startTime">
												<fmt:formatDate
													value="${scaleCheckResultList[0].answerStartTime}"
													pattern="yyyy-MM-dd HH:mm:ss" type="date" />
											</c:set>
											<c:set var="endTime">
												<fmt:formatDate
													value="${scaleCheckResultList[0].answerEndTime}"
													pattern="yyyy-MM-dd HH:mm:ss" type="date" />
											</c:set>
											<td>${startTime}至${endTime}</td>
											<c:set var="interval">
												<fmt:formatNumber
													value="${(scaleCheckResultList[0].answerEndTime.time-scaleCheckResultList[0].answerStartTime.time)/1000}"
													pattern="#" type="number" />
											</c:set>
											<c:set var="hour">
												<fmt:formatNumber value="${interval/3600}" pattern="#"
													type="number" />
											</c:set>
											<c:set var="minute">
												<fmt:formatNumber value="${interval/60%60}" pattern="#"
													type="number" />
											</c:set>
											<c:set var="second">
												<fmt:formatNumber value="${interval%60}" pattern="#"
													type="number" />
											</c:set>
											<c:choose>
												<c:when test="${interval>=3600}">
													<td>${hour}小时${minute}分${second}秒</td>
												</c:when>
												<c:when test="${interval<60}">
													<td>${second}秒</td>
												</c:when>
												<c:otherwise>
													<td>${minute}分${second}秒</td>
												</c:otherwise>
											</c:choose>
										</tr>
									</tbody>
								</table>
							</div>
							<div class="example" <c:if test="${scoreTable==''}">style="display: none"</c:if>>
								<h4 class="col-sm-12 col-sm-offset-5">因子得分</h4>
								${scoreTable}
							</div>
							<div class="example"
								<c:if test="${!scoreGraph.graphShow}">style="display: none"</c:if>>
								<h4 class="col-sm-12 col-sm-offset-5">
									</br>
								</h4>
								<div id="chart1" class="col-sm-12 col-sm-offset-2"
									style="width: 600px; height: 400px;"></div>
							</div>
							<div class="example"
								<c:if test="${!scoreGraph.graphShow}">style="display: none"</c:if>>
								<h4 class="col-sm-12 col-sm-offset-5">
									</br>
								</h4>
								<div id="chart2" class="col-sm-12 col-sm-offset-2"
									style="width: 600px; height: 400px;"></div>
							</div>
							<div class="example">
								<h4 class="col-sm-12">结果解释</h4>
								<div class="col-sm-8">
									<table id="exampleTableExplain" data-toggle="table"
										data-query-params="queryParams" data-mobile-responsive="true"
										data-pagination="false" data-search="false">
										<tbody>
											<tr>${scaleCheckResultList[0].resultExplain }
											</tr>
										</tbody>
									</table>
								</div>
							</div>
							<div class="example">
								<h4 class="col-sm-12">
									教师指导意见<a onclick="showEditArea();"><i
										class="glyphicon glyphicon-edit" aria-hidden="true"></i></a>
								</h4>
								<div class="col-sm-8" style="display: block" id="guidanceShow">
									<label><c:choose>
											<c:when test="${empty scaleCheckResultList[0].guidance }">暂无</c:when>
											<c:otherwise>	${scaleCheckResultList[0].guidance}</c:otherwise>
										</c:choose></label>
								</div>
								<div class="col-sm-8" style="display: none"
									id="editGuidanceArea">
									<c:choose>
										<c:when test="${empty scaleCheckResultList[0].guidance }">
											<textarea rows="3" cols="100" id="guidance"
												style="text-align: left;">暂无</textarea>
										</c:when>
										<c:otherwise>
											<textarea rows="3" cols="100" id="guidance"
												style="text-align: left;">${scaleCheckResultList[0].guidance}</textarea>
										</c:otherwise>
									</c:choose>
									<button class="btn btn-primary" type="button"
										onclick="editGuidance();">提交</button>
									&nbsp;
									<button class="btn btn-primary" type="button"
										onclick="cancelEdit();">取消</button>
								</div>
							</div>
							<div class="example">
								<h4 class="col-sm-12">详细反应</h4>
								<div class="col-sm-8">
									<c:choose>
										<c:when test="${taskScaleId==1}">
											<c:forEach items="${scaleTaskAnswerList}"
												var="scaleTaskAnswerItem" varStatus="status">
												<tr>
													<td>${scaleTaskAnswerItem.parentTid}.</td>
													<c:choose>
														<c:when test="${not empty scaleTaskAnswerItem.answer}">
															<c:if test="${scaleTaskAnswerItem.answer.matches('[0-9]+')}">
															<td><input type="text" readonly="readonly" size="1"
																value="${scaleTaskAnswerItem.answer + 1}" /></td>
															</c:if>
														</c:when>
														<c:otherwise>
														<td><input type="text" readonly="readonly" size="1"
															value="${scaleTaskAnswerItem.answer}" /></td>
														</c:otherwise>
													</c:choose>
												</tr>
											</c:forEach>
										</c:when>
										<c:otherwise>
											<c:forEach items="${scaleTaskAnswerList}"
												var="scaleTaskAnswerItem" varStatus="status">
												<tr>
													<td>${status.index+1}.</td>
													<c:choose>
														<c:when test="${not empty scaleTaskAnswerItem.answer}">
															<c:if test="${scaleTaskAnswerItem.answer.matches('[0-9]+')}">
															<td><input type="text" readonly="readonly" size="1"
																value="${scaleTaskAnswerItem.answer + 1}" /></td>
															</c:if>
														</c:when>
														<c:otherwise>
														<td><input type="text" readonly="readonly" size="1"
															value="${scaleTaskAnswerItem.answer}" /></td>
														</c:otherwise>
													</c:choose>
												</tr>
											</c:forEach>
										</c:otherwise>
									</c:choose>
									<%-- <c:forEach items="${scaleTaskAnswerList}"
										var="scaleTaskAnswerItem" varStatus="status">
										<tr>
											<td>${status.index+1}.</td>
											<c:choose>
												<c:when test="${not empty scaleTaskAnswerItem.answer}">
													<c:if
														test="${scaleTaskAnswerItem.answer.matches('[0-9]+')}">
														<td><input type="text" readonly="readonly" size="1"
															value="${scaleTaskAnswerItem.answer + 1}" /></td>
													</c:if>
												</c:when>
												<c:otherwise>
													<td><input type="text" readonly="readonly" size="1"
														value="${scaleTaskAnswerItem.answer}" /></td>
												</c:otherwise>
											</c:choose>
										</tr>
									</c:forEach> --%>
								</div>
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
	<script src="${ctxStatic}/echarts/echarts.js"></script>
	<script src="${ctxStatic}/jquery-printarea/jquery.PrintArea.js"></script>
	<script type="text/javascript">
		function goScaleForm() {
			location.href = "${ctx}/jzmk/scale/form";
		}
		function showEditArea() {
			$("#guidanceShow").css("display", "none");
			$("#editGuidanceArea").css("display", "block");
		}
		function editGuidance()
		{
			location.href = "${ctx}/jzmk/scale/editGuidance?taskId=${taskId}&taskUserId=${taskUserId}&taskScaleId=${taskScaleId}&taskNumber=${taskNumber}&guidance="+$("#guidance").val();
		}
		function cancelEdit() {
			$("#guidanceShow").css("display", "block");
			$("#editGuidanceArea").css("display", "none");
		}
		function printResult()
		{
		     var printPreview = window.open('${ctx}/jzmk/scale/evaluation?sid=${taskScaleId}&taskId=${taskId}&taskUserId=${taskUserId}&taskScaleId=${taskScaleId}&taskNumber=${taskNumber}&print_mode=1', 'newwindow', 'width=840, top=0, left=0, toolbar=no, menubar=no, scrollbars=yes, resizable=yes,location=no, status=no');
		     debugger;
		     printPreview.document.body.innerHTML = $("#myPrintArea").html();
		     printPreview.print();
		    //$("#myPrintArea").printArea({mode:"popup"});
		}
		function delscale(id, name) {
			layer.confirm('是否删除 (' + name + ') 量表？', {
				btn : [ '确认', '取消' ], //按钮
				title : '删除确认',
				shade : false
			//不显示遮罩
			}, function() {
				document.location.href = "${ctx}/jzmk/scale/delete?id=" + id;
				layer.msg('删除成功！', {
					icon : 6
				});
			}, function() {
				layer.msg('已取消', {
					icon : 1
				});
			});
		}
		var option2 = {
			title : {
				text : '结果剖析图'
			},
			tooltip : {
				trigger : 'axis'
			},
			legend : {
				data : [ '因子分', '正常区域' ]
			},
			toolbox : {
				show : true,
				feature : {
					dataZoom : {
						yAxisIndex : 'none'
					},
					dataView : {
						readOnly : false
					},
					magicType : {
						type : [ 'line', 'bar' ]
					},
					restore : {},
					saveAsImage : {}
				}
			},
			xAxis : {
				type : 'category',
				boundaryGap : false,
				data : [${scoreGraph.xAxisData!=null?scoreGraph.xAxisData:''}]
			},
			yAxis : {
				type : 'value',
				max : ${scoreGraph.yMaxValue!=null?scoreGraph.yMaxValue:0},
				min : ${scoreGraph.yMinValue!=null?scoreGraph.yMinValue:0}
			},
			series : [ 
				{
				    name : '因子分',
				    type : 'line',
				    data : [${scoreGraph.gScoreData!=null?scoreGraph.gScoreData:''}]
				}, 
				{
					name : '正常区域',
					type : 'line',
					data : [${scoreGraph.cValueData!=null?scoreGraph.cValueData:''}]
				}
				]
		};
		var option1 = {
			title : {
				text : '结果剖析图'
			},
			tooltip : {
				trigger : 'axis'
			},
			legend : {
				data : [ '因子分', '标准分' ]
			},
			toolbox : {
				show : true,
				feature : {
					dataView : {
						show : true,
						readOnly : false
					},
					magicType : {
						show : true,
						type : [ 'line', 'bar' ]
					},
					restore : {
						show : true
					},
					saveAsImage : {
						show : true
					}
				}
			},
			calculable : true,
			xAxis : [ {
				type : 'category',
				data : [${scoreGraph.xAxisData!=null?scoreGraph.xAxisData:''}]
			} ],
			yAxis : [ {
				type : 'value',
				max : ${scoreGraph.yMaxValue!=null?scoreGraph.yMaxValue:0},
				min : ${scoreGraph.yMinValue!=null?scoreGraph.yMinValue:0}
			} ],
			series : [
					{
						name : '因子分',
						type : 'bar',
						data : [${scoreGraph.gScoreData!=null?scoreGraph.gScoreData:''}]
					},
					{
						name : '标准分',
						type : 'bar',
						data : [${scoreGraph.sValueData!=null?scoreGraph.sValueData:''}]
					} ]
		};

		var option = {
			title : {
				text : 'ECharts 入门示例'
			},
			tooltip : {},
			legend : {
				data : [ '销量' ]
			},
			xAxis : {
				data : [ "衬衫", "羊毛衫", "雪纺衫", "裤子", "高跟鞋", "袜子" ]
			},
			yAxis : {},
			series : [ {
				name : '销量',
				type : 'bar',
				data : [ 5, 20, 36, 10, 10, 20 ]
			} ]
		};
		var myChart1 = echarts.init(document.getElementById('chart1'));
		var myChart2 = echarts.init(document.getElementById('chart2'));
		myChart1.setOption(option1);
		myChart2.setOption(option2);
	</script>
</body>
</html>


