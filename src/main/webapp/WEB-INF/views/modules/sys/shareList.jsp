<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>共享积分管理</title>
	<meta charset="utf-8">
	<meta name="decorator" content="default"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="keywords" content="${fns:getConfig('keywords')} ${sysConfig.schoolName } ">
    <meta name="description" content="${fns:getConfig('description')} ${sysConfig.schoolName }">
    
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
                        <h4 class="example-title">资料共享积分审核</h4>
						<div class="example">
						<sys:message content="${message}"/>
						<form id="searchForm" action="${ctx}/sys/share/list" method="post" class="breadcrumb form-search">
							<div>
								<div>
									<label>共享人：</label>
									<input id="user.name" name="user.name" value="${share.user.name}" type="text" maxlength="30"/>
									<label>状态：</label>
									<select name="status" class="input-medium">
										<option value="${share.status}">--请选择--</option>
										<c:forEach items="${fns:getDictList('public_activity_check_status')}" var="dict">
											<option value="${dict.value}">${dict.label}</option>
										</c:forEach>
									</select>
									<button id="btnQuery" class="btn btn-primary" onclick="">查询</button>
								</div>
							</div>
						</form>
						
						<sys:message content="${message}"/>
						<table id="contentTable" class="table table-striped table-bordered table-condensed">
							<thead>
								<tr>
									<th data-field="user.name">共享人姓名</th>
									<th data-field="orgId">所属机构</th>
									<th data-field="dataType">资料来源</th>
									<th data-field="status">审核状态</th>
									<th data-field="updateDate">修改日期</th>
									<shiro:hasPermission name="sys:share:edit"><th>操作</th></shiro:hasPermission>
								</tr>
							</thead>
							<tbody>
							<c:forEach items="${page.list}" var="share">
								<tr>
									<td><a class="text-navy" href="${ctx}/sys/share/view?id=${share.id}">
										${share.user.name}
									</a></td>
									<td>
										<c:forEach items="${offices.list}" var="office">
											<c:if test="${share.orgId==office.id}">
												${office.name}
											</c:if>
										</c:forEach>
									</td>
									<td>
										<c:if test="${share.dataType == '0'}">系统</c:if>
										<c:if test="${share.dataType == '1'}">量表</c:if>
										<c:if test="${share.dataType == '2'}">课件</c:if>
										<c:if test="${share.dataType == '3'}">心灵咖啡屋</c:if>
										<%-- ${fns:getDictLabel(visitorInfo.status, 'visitor_review_status', '')} --%>
									</td>
									<td>
										<c:if test="${share.status == '0'}">待审核</c:if>
										<c:if test="${share.status == '1'}">审核通过</c:if>
										<c:if test="${share.status == '2'}">审核未通过</c:if>
										<%-- ${fns:getDictLabel(visitorInfo.status, 'visitor_review_status', '')} --%>
									</td>
									<td><fmt:formatDate value="${share.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
									<shiro:hasPermission name="sys:share:edit"><td>
										<a class="text-navy" href="${ctx}/sys/share/view?id=${share.id}" title="查看">
											<i class="glyphicon glyphicon-eye-open" aria-hidden="true"></i>
										</a>&nbsp;&nbsp;
					    				<a class="text-navy" href="${ctx}/sys/share/form?id=${share.id}" title="修改">
											<i class="glyphicon glyphicon-edit" aria-hidden="true"></i>
										</a>&nbsp;&nbsp;
										<a class="text-danger" href="#" onclick="delOne('${share.id}')" title="删除">
											<i class="glyphicon glyphicon-trash" aria-hidden="true"></i>
										</a>&nbsp;&nbsp;
										<c:choose>
						    				<c:when test="${share.status!=1}">
						    					<a href='#' class="text-danger" onclick="checkOne('${share.id}')">审核</a>
						    				</c:when>
						    				<c:otherwise>审核</c:otherwise>
					    				</c:choose>
									</td></shiro:hasPermission>
								</tr>
							</c:forEach>
							</tbody>
						</table>
						<div class="pagination">${page}</div>
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
			layui.use(['layer', 'form'], function(){
				  var layer = layui.layer ,form = layui.form;
				layer.confirm('是否删除记录？', {
				    btn: ['确认','取消'], //按钮
				    title:'提示',
				    shade: false //不显示遮罩
				}, function(){
					document.location.href="${ctx}/sys/share/delete?id="+id;
				    layer.msg('删除成功！', {icon: 6});
				}, function(){
				    layer.msg('已取消', {icon: 1});
				});
			});
		}
		
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
					document.location.href="${ctx}/sys/share/audit?id="+id;
				    //layer.msg('审核成功！', {icon: 6});
				}, function(){
				    layer.msg('已取消', {icon: 1});
				});
			});
		}
	</script>
</body>
</html>