<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>公益活动管理</title>
	<meta charset="utf-8">
	<meta name="decorator" content="default"/>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<%@ include file="/WEB-INF/views/include/commoncss.jsp" %>
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
                        <h4 class="example-title">公益活动审核列表</h4>
						<div class="example">
							<form:form id="searchForm" modelAttribute="publicActivity" action="${ctx}/ante/publicActivity/checkList" method="post" class="breadcrumb form-search">
								<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
								<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
								
								<div>	
									<label>发布时间：</label>
									<input name="beginUpdateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
										value="<fmt:formatDate value="${publicActivity.beginUpdateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
										onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> - 
									<input name="endUpdateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
										value="<fmt:formatDate value="${publicActivity.endUpdateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
										onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
									<label>审核状态：</label>
									<select name="checkStatus" class="input-medium">
										<option value="">--请选择--</option>
										<c:forEach items="${fns:getDictList('public_activity_check_status')}" var="dict">
											<option value="${dict.value}">${dict.label}</option>
										</c:forEach>
									</select>
								</div>
								<div>
									<label>所属机构：</label>
									<form:input path="orgName" htmlEscape="false" maxlength="100" class="input-medium"/>
									<label>标题：</label>
									<form:input path="title" htmlEscape="false" maxlength="30" class="input-medium"/>
									<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
								</div>
							</form:form>
							<sys:message content="${message}"/>
							<table id="publicActivityCheckTable" data-toggle="table" data-query-params="queryParams" data-mobile-responsive="true" data-height="400" data-pagination="true">
								<thead>
									<tr>
										<th data-checkbox="true" aria-hidden="true"></th>
						                <th data-field="id" data-visible="false"></th>
										<th data-field="title">标题</th>
										<th data-field="createName">发布人</th>
										<th data-field="updateDate">发布时间</th>
										<th data-field="orgName">所属机构</th>
										<th data-field="checkStatus">审核状态</th>
										<th data-field="remarks">审核意见</th>
										<shiro:hasPermission name="ante:publicActivity:edit"><th>操作</th></shiro:hasPermission>
									</tr>
								</thead>
								<c:forEach items="${page.list}" var="publicActivity">
									<tr>
										<td>${publicActivity.id}</td>
										<td>${publicActivity.id}</td>
										<td><a class="text-navy" href="${ctx}/ante/publicActivity/view?id=${publicActivity.id}">${publicActivity.title}</a></td>
										<td>${publicActivity.createName}</td>
										<td>
											<fmt:formatDate value="${publicActivity.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
										</td>
										<td>${publicActivity.orgName}</td>
										<td>${fns:getDictLabel(publicActivity.checkStatus, 'public_activity_check_status', '')}</td>
										<td>${publicActivity.remarks}</td>
										<td>
											<shiro:hasPermission name="ante:publicActivity:edit">
												<a class="text-navy" href='${ctx}/ante/publicActivity/view?id=${publicActivity.id}'>查看</a>&nbsp;&nbsp;
												<a class="text-navy" href='${ctx}/ante/publicActivity/form?id=${publicActivity.id}'>修改</a>&nbsp;&nbsp;
							    				<c:choose>
								    				<c:when test="${publicActivity.checkStatus!=1}">
								    					<a class="text-danger" href="javascript:checkOne('${publicActivity.id}');">审核</a>
								    				</c:when>
								    				<c:otherwise>审核</c:otherwise>
							    				</c:choose>
											</shiro:hasPermission>
										</td>
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
		 * 单个审核
		 */
		function checkOne(id){
			layui.use(['layer', 'form'], function(){
				  var layer = layui.layer ,form = layui.form;
				  layer.confirm('是否审核记录？', {
					    btn: ['确认','取消'], //按钮
					    title:'提示',
					    shade: false //不显示遮罩
					}, function(){
						document.location.href="${ctx}/ante/publicActivity/checkForm?id="+id;
					    //layer.msg('审核成功！', {icon: 6});
					}, function(){
					    layer.msg('已取消', {icon: 1});
					});
			});
		}
	</script>
	
</body>
</html>