<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${sysConfig.schoolName } 公益活动--我报名的公益活动</title>
    <meta name="keywords" content="${fns:getConfig('keywords')} ${sysConfig.schoolName } ">
    <meta name="description" content="${fns:getConfig('description')} ${sysConfig.schoolName }">
	<%@ include file="/WEB-INF/views/include/commoncss.jsp" %>
</head>
<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeInRight">
	       <div class="ibox float-e-margins">
	           <div class="ibox-content">
	               <div class="row row-lg">
	                   <div class="col-sm-12">
	                       <h4 class="example-title">我报名的公益活动列表</h4>
						<div class="example">
							<sys:message content="${message}"/>
							<form id="searchForm" action="${ctx}/ante/publicActivity/myEnrollList" method="post" class="form-inline">
								<div class="form-group">
									<label>开始时间：</label>
									<input name="beginUpdateDate" type="text"
										readonly="readonly" maxlength="20" class="form-control Wdate "
										style="border: 1px solid #e5e6e7;padding: 6px 12px;height: 34px;line-height:1.42857143"
										value="<fmt:formatDate value="${publicActivity.beginUpdateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
										onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});" />
								</div>
								<div class="form-group">	
									<label>结束时间：</label>
									<input name="endUpdateDate" type="text"
										readonly="readonly" maxlength="20" class="form-control Wdate "
										style="border: 1px solid #e5e6e7;padding: 6px 12px;height: 34px;line-height:1.42857143"
										value="<fmt:formatDate value="${publicActivity.endUpdateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
										onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});" />
								</div>
								<div class="form-group">		
									<label>标题：</label><input id="title" name="title" class="form-control" type="text" maxlength="30"/>
								</div>
								<div class="form-group">
									<button id="btnQuery" class="btn btn-primary">查询</button>
								</div>
							</form>
							<!-- <table id="contentTable" class="table table-striped table-bordered table-condensed"> -->
							<table id="publicActivityMyTable" data-toggle="table" data-query-params="queryParams" data-mobile-responsive="true" data-height="400" data-pagination="true">
								<thead>
									<tr>
										<th data-checkbox="true" aria-hidden="true"></th>
                                    	<th data-field="id" data-visible="false"></th>
										<th data-field="title">标题</th>
										<th data-field="createName">发布人</th>
										<th data-field="updateDate">发布时间</th>
										<th data-field="orgName">所属机构</th>
										<th data-field="checkStatus">审核状态</th>
										<th data-field="activityStatus">活动状态</th>
										<shiro:hasPermission name="ante:publicActivity:edit"><th>操作</th></shiro:hasPermission>
									</tr>
								</thead>
								<c:forEach items="${page.list}" var="publicActivity">
									<tr>
										<td>${publicActivity.id}</td>
										<td>${publicActivity.id}</td>
										<td><a class="text-navy" href="${ctx}/ante/publicActivity/view?id=${publicActivity.id}">${publicActivity.title}</a></td>
										<td>
											${publicActivity.createName}
										</td>
										<td>
											<fmt:formatDate value="${publicActivity.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
										</td>
										<td>
											${publicActivity.orgName}
										</td>
										<td>
											${fns:getDictLabel(publicActivity.checkStatus, 'public_activity_check_status', '')}
										</td>
										<td>
											${fns:getDictLabel(publicActivity.activityStatus, 'public_activity_status', '')}
										</td>
										<shiro:hasPermission name="ante:publicActivity:edit"><td>
											<a class="text-navy" href='${ctx}/ante/publicActivity/view?id=${publicActivity.id}'>查看</a>&nbsp;&nbsp;
											<a class="text-navy" href='${ctx}/ante/publicActivity/form?id=${publicActivity.id}'>修改</a>&nbsp;&nbsp;
											<a class="text-danger" href="#" onclick="delOne('${publicActivity.id}')">删除</a>
										</td></shiro:hasPermission>
									</tr>
								</c:forEach>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="/WEB-INF/views/include/commonlistjs.jsp" %>
	<script type="text/javascript">
		/**
		 * 单个删除
		 */
		function delOne(id){
			layer.confirm('是否删除记录？', {
			    btn: ['确认','取消'], //按钮
			    title:'提示',
			    shade: false //不显示遮罩
			}, function(){
				document.location.href="${ctx}/ante/publicActivity/delete?id="+id+"&flag=2";
			    layer.msg('删除成功！', {icon: 6});
			}, function(){
			    layer.msg('已取消', {icon: 1});
			});
		}
	</script>
	
</body>
</html>