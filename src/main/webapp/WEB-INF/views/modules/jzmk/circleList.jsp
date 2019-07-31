<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>圈子动态</title>
<meta name="decorator" content="default"/>
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
	function page(n,s){
	   $("#pageNo").val(n);
	   $("#pageSize").val(s);
	   $("#searchForm").submit();
       return false;
 }
</script>
</head>
<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeInRight">
		<div class="ibox float-e-margins">
			<div class="ibox-content">
				<div class="row row-lg">
					<div class="col-sm-12">
						<h4 class="text-navy">圈子动态（${currentUserArea}）</h4>
						<div class="example">
							<button id="btnSpeech" class="btn btn-primary" onclick="speech();">我要发言</button>
							<button id="btnMyGraph" class="btn btn-primary" onclick="graph();" align="right">我的对比图</button>
							<c:if test="${checkDeleleTag>0}">
							<button id="btnCheckDelete" class="btn btn-primary" onclick="checkDelete();" align="right">选贴删除</button>
							</c:if>
						</div>
						<div class="example">
							<table id="circleTable" data-toggle="table"
								data-query-params="queryParams" data-mobile-responsive="true"
								>
								<thead>
									<tr>
										<c:if test="${checkDeleleTag>0}">
										<th data-field="" data-checkbox="true" aria-hidden="true">选择</th>
										<th data-field="id" data-visible="false">ID</th>
										</c:if>
										<th data-field="startTime">发布内容</th>
										<th data-field="integral">发布人</th>
										<th data-field="operation">操作</th>
									</tr>
								</thead>
								<tbody>
								<c:forEach items="${page.list}" var="circle">
									<tr>
										<c:if test="${checkDeleleTag>0}">
										<td data-field="id" data-checkbox="true" aria-hidden="true">${circle.id}</td>
										<td>${circle.id}</td>
										</c:if>
										<td>${circle.content}</td>
										<td>${circle.userName}</td>
										<shiro:hasPermission name="jzmk:circle:edit">
										<td>
											<c:if test="${currentUserId==circle.userId}">
											<a href="${ctx}/jzmk/circle/form?id=${circle.id}" class="text-navy">修改</a>
											<a href="#" class="text-navy" onclick="confirmDo('确认要删除该圈子动态吗？', '${ctx}/jzmk/circle/delete?id=${circle.id}');">删除</a>
											</c:if>
											<a href="#" class="text-navy" onclick="confirmDo('确认要回复圈子动态吗？', '${ctx}/jzmk/circle/reply?id=${circle.id}');">回复</a>
										</td></shiro:hasPermission>
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
	<script type="text/javascript">
		function speech()
		{
			location.href = "${ctx}/jzmk/circle/form";
		}
		function checkDelete()
		{
			var a = $.map($("#circleTable").bootstrapTable('getSelections'), function (row) {
                return row.id;
            });
			if(a.length<=0)
   			{
   				layer.alert('请选择发言');
		    	return false;
   			}
   			else
   			{
   				var ids=JSON.stringify(a);
  				var href='${ctx}/jzmk/circle/delete?ids='+ids;
  				confirmDo('确认删除选中的帖子？',href);
   			}
		}
		function confirmDo(titleNow,hrefNow)
		{
			var prompt=layer.confirm(titleNow, {
			    btn: ['确认','取消'], //按钮
			    title:'操作确认',
			    shade: false //不显示遮罩
			}, function(){
				document.location.href=hrefNow;
			    /* layer.msg('成功！', {icon: 6}); */
			    layer.close(prompt);
			}, function(){
			    layer.msg('已取消', {icon: 1});
			});
		}
		function graph()
		{
			location.href='${ctx}/jzmk/circle/graph?id=${circle.id}';
		}
	</script>
</body>
</html>