<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>我的评语记录</title>
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
                        <h4 class="example-title">给我的评语记录列表</h4>
						<div class="example">
                   			<div class="btn-group " id="exampleTableEventsToolbar" role="group">
                               	<a>
	                                <button type="button" onclick="delAll()" class="btn btn-outline btn-default">
	                                    <i class="glyphicon glyphicon-trash text-navy" aria-hidden="false"></i>
	                                </button>
                               	</a>
                            </div>
							<form id="searchForm" modelAttribute="commentInfo" action="${ctx}/ante/commentInfo/toMeList" method="post" class="breadcrumb form-search">
								<div>
									<label>评论标题：</label><input id="title" name="title" value="${commentInfo.title}" type="text" maxlength="30"/>
									<button id="btnQuery" class="btn btn-primary" onclick="">查询</button>
								</div>
							</form>
							<sys:message content="${message}"/>
							<table id="contentTable" data-toggle="table" data-query-params="queryParams" data-mobile-responsive="true" data-height="400" data-pagination="true">
								<thead>
									<tr>
										<th data-checkbox="true" aria-hidden="true"></th>
                                    	<th data-field="id" data-visible="false"></th>
										<th data-field="title">评论标题</th>
										<th data-field="userName1">评价人姓名</th>
										<th data-field="userName2">被评价人姓名</th>
										<th data-field="createDate">评价时间</th>
										<shiro:hasPermission name="ante:commentInfo:edit"><th>操作</th></shiro:hasPermission>
									</tr>
								</thead>
								<c:forEach items="${page.list}" var="commentInfo">
									<tr>
										<td>${commentInfo.id}</td>
										<td>${commentInfo.id}</td>
										<td><a class="text-navy" href="${ctx}/ante/commentInfo/view?id=${commentInfo.id}">
											${commentInfo.title}
										</a></td>
										<td>${commentInfo.userName1}</td>
										<td>${commentInfo.userName2}</td>
										<td>
											<fmt:formatDate value="${commentInfo.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
										</td>
										<shiro:hasPermission name="ante:commentInfo:edit"><td>
										<a class="text-navy" href="${ctx}/ante/commentInfo/view?id=${commentInfo.id}">
											<i class="glyphicon glyphicon-eye-open" aria-hidden="true" title="查看"></i>
										</a>&nbsp;&nbsp;
										<a href="#" class="text-danger" onclick="delOne('${commentInfo.id}')" title="删除">
											<i class="glyphicon glyphicon-trash" aria-hidden="true"></i>
										</a>
										</td></shiro:hasPermission>
									</tr>
								</c:forEach>
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
			layer.confirm('是否删除记录？', {
			    btn: ['确认','取消'], //按钮
			    title:'提示',
			    shade: false //不显示遮罩
			}, function(){
				document.location.href="${ctx}/ante/commentInfo/delete?id="+id+"&flag=0";
			    layer.msg('删除成功！', {icon: 6});
			}, function(){
			    layer.msg('已取消', {icon: 1});
			});
		}
		
		/**
		 * 批量删除
		 */
		function delAll(){
			var sumChecked=$("input:checked").length;
			if(sumChecked<1){
				layer.msg('请选择要删除的记录！', {icon: 6});
				return false;
			}
			var a = $.map($("#contentTable").bootstrapTable('getSelections'), function (row) {
		        return row.id;
		    });
			var ids=JSON.stringify(a);
			layer.confirm('是否删除选中的记录？', {
			    btn: ['确认','取消'], //按钮
			    title:'提示',
			    shade: false //不显示遮罩
			}, function(){
				window.location.href="${ctx}/ante/commentInfo/deleteAll?ids="+ids;
			    layer.msg('删除成功！', {icon: 6});
			}, function(){
			    layer.msg('已取消', {icon: 1});
			});
		}
	</script>
	
</body>
</html>