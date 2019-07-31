<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>评测结果管理</title>
<meta name="decorator" content="default" />
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
<style type="text/css">
.ztree {
	overflow: auto;
	margin: 0;
	_margin-top: 10px;
	padding: 10px 0 0 10px;
}
</style>
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
<body>
	<div class="wrapper wrapper-content animated fadeInRight">
		<div class="ibox float-e-margins">
			<div class="ibox-content">
				<div class="row row-lg">
					<div class="col-sm-12">
						<h4 class="example-title">"${scale.name }"统计结果</h4>
						<div class="example">
							<h4 class="col-sm-12">测评情况</h4>
							<table id="exampleTable" data-toggle="table"
								data-query-params="queryParams" data-mobile-responsive="true"
								data-pagination="false" data-search="false">
								<thead>
									<tr>
										<th data-field="number">测评人数</th>
										<th data-field="name">已测人数</th>
										<th data-field="enname">未测人数</th>
										<th data-field="score">异常人数</th>
										<th data-field="genescore">无效人数</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td>${checkTotalCount}</td>
										<td>${checkFinishedCount}</td>
										<td>${checkUnfinishedCount}</td>
										<td>${checkAbnormalCount}</td>
										<td>${checkInvalidCount}</td>
									</tr>
								</tbody>
							</table>
						</div>
						<div class="example">
                        	<h4 class="col-sm-12">施测率</h4>
                            <div id="chart1" class="col-sm-12 col-sm-offset-2" style="width: 600px;height:400px;">
                            </div>
                            <div class="col-sm-12 col-sm-offset-2">
                            <span>该图表只针对您当前统计的范围。从上图可知，总人数为：${checkTotalCount}人，已经测试过的人数为：${checkFinishedCount}人，则施测率为：<fmt:formatNumber type="percent" value="${checkTotalCount==0?0:checkFinishedCount/checkTotalCount}" maxFractionDigits="2"/>。</span>
                            </div>
                        </div>
                        <div class="example">
                        	<h4 class="col-sm-12">异常率</h4>
                            <div id="chart2" class="col-sm-12 col-sm-offset-2" style="width: 600px;height:400px;">
                            </div>
                            <div class="col-sm-12 col-sm-offset-2">
                            <span>该图表只针对您当前统计的范围。从上图可知，有效测评总人数为：${checkFinishedCount}人，异常人数为：${checkAbnormalCount}人，则异常率为：<fmt:formatNumber type="percent" value="${checkFinishedCount==0?0:checkAbnormalCount/checkFinishedCount}" maxFractionDigits="2"/>。</span>
                            </div>
                        </div>
						<div class="form-group">
							<div class="col-sm-8">
								<button id="btnQuery" class="btn btn-primary"
									onclick="javascript:gotoPage();">返回</button>
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
	<script src="${ctxStatic}/echarts/echarts.js"></script>
	<script type="text/javascript">
		 $(document).ready(function(){
        });
		function gotoPage()
		{
			location.href = "${ctx}/jzmk/scaleCheckResult/statistic"
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
		
		var option1 = {
			    title : {
			        text: '施测率',
			        subtext: '测评结果数据',
			        x:'center'
			    },
			    tooltip : {
			        trigger: 'item',
			        formatter: "{a} <br/>{b} : {c} ({d}%)"
			    },
			    legend: {
			        orient: 'vertical',
			        left: 'left',
			        data: ['已测','未测']
			    },
			    series : [
			        {
			            name: '访问来源',
			            type: 'pie',
			            radius : '55%',
			            center: ['50%', '60%'],
			            data:[
			                {value:${checkFinishedCount}, name:'已测'},
			                {value:${checkInvalidCount}, name:'未测'}
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
		var option2 = {
			    title : {
			        text: '结果正常异常状态',
			        subtext: '数据',
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
			                {value:(${checkFinishedCount}-${checkAbnormalCount}), name:'正常'},
			                {value:${checkAbnormalCount}, name:'异常'}
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
		var myChart1 = echarts.init(document.getElementById('chart1'));
		var myChart2 = echarts.init(document.getElementById('chart2'));
		myChart1.setOption(option1);
		myChart2.setOption(option2);
	</script>
</body>
</html>
