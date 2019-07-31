<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<!-- Mirrored from www.zi-han.net/theme/hplus/table_bootstrap.html by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 20 Jan 2016 14:20:03 GMT -->
<head>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>测评人员管理</title>
<!-- <meta name="decorator" content="default" /> -->
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
						<h4 class="text-navy">测评人员</h4>
							<div class="form-horizontal">
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
										value="<fmt:formatDate value="${scaleTaskUser.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
										onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label"><input type="checkbox" id="cbEndTime" class="control-checkbox"/>&nbsp;&nbsp;&nbsp;结束时间：</label> 
									<div class="col-sm-8">
									<input id="endTime" name="endTime" type="text" readonly="readonly" maxlength="40" class="input-medium Wdate"
										value="<fmt:formatDate value="${scaleTaskUser.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
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
							</div>
							<div class="example">
							<table id="scaleCheckUserTable" data-toggle="table"
			data-query-params="queryParams" data-mobile-responsive="true"
			data-pagination="true">
								<thead>
									<tr>
										<th>ID</th>
										<th>量表</th>
										<th>用户名</th>
										<th>姓名</th>
										<th>性别</th>
										<th>身份</th>
										<th>开始时间</th>
										<th>结束时间</th>
										<th>状态</th>
									</tr>
								</thead>
								<c:forEach items="${page.list}" var="scaleTaskUser" varStatus="status">
									<tr>
										<td>${status.index+1}</td>
										<td>${scaleTaskUser.scaleName}</td>
										<td>${scaleTaskUser.userLoginName}</td>
										<td>${scaleTaskUser.userName}</td>
										<td>${scaleTaskUser.userSex==1?"男":"女"}</td>
										<td>
										<c:choose>
    										<c:when test="${scaleTaskUser.role==0}">
        										来访者
    										</c:when>
    										<c:when test="${scaleTaskUser.role==1}">
        										家长
    										</c:when>
    										<c:when test="${scaleTaskUser.role==2}">
        										教师
    										</c:when>
										</c:choose>
										</td>
										<td><fmt:formatDate value="${scaleTaskUser.startTime}"
							pattern="yyyy-MM-dd HH:mm:ss" /></td>
										<td><fmt:formatDate value="${scaleTaskUser.endTime}"
							pattern="yyyy-MM-dd HH:mm:ss" /></td>
										<td>${scaleTaskUser.state==0?"未完成":"完成"}</td>
									</tr>
								</c:forEach>
							</table>
							</div>
							<button class="btn btn-primary" type="button" onclick="history.go(-1)">返回</button>&nbsp;
	
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

