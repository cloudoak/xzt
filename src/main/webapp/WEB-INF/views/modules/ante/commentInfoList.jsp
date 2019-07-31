<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${sysConfig.schoolName } 评价管理</title>
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
                        <h4 class="example-title">评价管理</h4>
						<div class="example">
							<div class="tabs-container">
							<ul class="nav nav-tabs">
								<!-- <li class=""><a data-toggle="tab" href="#tab-1" aria-expanded="true" onclick="tab('tab-1')">咨询师评价记录</a></li> -->
								<li class="active"><a data-toggle="tab" href="#tab-2" aria-expanded="false" onclick="">来访者评价记录</a></li>
								<li class=""><a data-toggle="tab" href="#tab-3" aria-expanded="false" onclick="">家属评价记录</a></li>
							</ul>
							<div class="tab-content">
								<%-- <div id="tab-1" class="tab-pane active">
									<div class="panel-body">
										<form id="searchForm1" modelAttribute="commentInfo" method="post" class="form-inline">
											<div class="form-group">
												<label>咨询师姓名：</label>
												<input id="userName11" name="userName1" value="${commentInfo.userName1}" class="form-control" type="text" maxlength="30"/>
											</div>
											<div class="form-group">
												<label>评论标题：</label>
												<input id="title1" name="title" value="${commentInfo.title}" class="form-control" type="text" maxlength="30"/>
											</div>
											<div class="form-group">
												<shiro:hasPermission name="ante:commentInfo:view">
												<input type="button" class="btn btn-primary" onclick="query()" value="查询"/>
												</shiro:hasPermission>
											</div>
										</form>
										<sys:message content="${message}"/>
										<table id="contentTable1" data-toggle="table" data-query-params="queryParams" data-mobile-responsive="true" data-height="400" data-pagination="true">
											<thead>
												<tr>
													<th data-checkbox="true" aria-hidden="true"></th>
			                                    	<th data-field="id" data-visible="false"></th>
													<th data-field="title">评论标题</th>
													<th data-field="userName1">评价人姓名(咨询师)</th>
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
													<shiro:hasPermission name="ante:commentInfo:edit">
														<td>
															<a class="text-navy" href="${ctx}/ante/commentInfo/view?id=${commentInfo.id}">
																<i class="glyphicon glyphicon-eye-open" aria-hidden="true" title="查看"></i>
															</a>&nbsp;&nbsp;
															<a href="#" class="text-danger" onclick="delOne('${commentInfo.id}')" title="删除">
																<i class="glyphicon glyphicon-trash" aria-hidden="true"></i>
															</a>
														</td>
													</shiro:hasPermission>
												</tr>
											</c:forEach>
										</table>
									</div>
								</div> --%>
								<div id="tab-2" class="tab-pane active">
									<div class="panel-body">
										<form id="searchForm2" modelAttribute="commentInfo" method="post" class="form-inline">
											<div class="form-group">
												<label>来访者姓名：</label>
												<input id="userName12" name="userName1" value="${commentInfo.userName1}" class="form-control" type="text" maxlength="30"/>
											</div>
											<div class="form-group">
												<label>评论标题：</label>
												<input id="title2" name="title" value="${commentInfo.title}" type="text" class="form-control" maxlength="30"/>
											</div>
											<div class="form-group">
												<shiro:hasPermission name="ante:commentInfo:view">
												<input type="button" class="btn btn-primary" onclick="query()" value="查询"/>
												</shiro:hasPermission>
											</div>
										</form>
										<sys:message content="${message}"/>
										<table id="contentTable2" data-toggle="table" data-query-params="queryParams" data-mobile-responsive="true" data-height="400" data-pagination="true">
											<thead>
												<tr>
													<th data-checkbox="true" aria-hidden="true"></th>
			                                    	<th data-field="id" data-visible="false"></th>
													<th data-field="title">评论标题</th>
													<th data-field="userName1">评价人姓名(来访者)</th>
													<th data-field="userName2">被评价人姓名(咨询师)</th>
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
													<shiro:hasPermission name="ante:commentInfo:edit">
														<td>
															<a class="text-navy" href="${ctx}/ante/commentInfo/view?id=${commentInfo.id}">
																<i class="glyphicon glyphicon-eye-open" aria-hidden="true" title="查看"></i>
															</a>&nbsp;&nbsp;
															<a href="#" class="text-danger" onclick="delOne('${commentInfo.id}')" title="删除">
																<i class="glyphicon glyphicon-trash" aria-hidden="true"></i>
															</a>
														</td>
													</shiro:hasPermission>
												</tr>
											</c:forEach>
										</table>
									</div>
								</div>
								<div id="tab-3" class="tab-pane">
									<div class="panel-body">
										<form id="searchForm3" modelAttribute="commentInfo" method="post" class="form-inline">
											<div class="form-group">
												<label>家属姓名：</label>
												<input id="userName13" name="userName1" value="${commentInfo.userName1}" class="form-control" type="text" maxlength="30"/>
											</div>
											<div class="form-group">
												<label>评论标题：</label>
												<input id="title3" name="title" value="${commentInfo.title}" class="form-control" type="text" maxlength="30"/>
											</div>
											<div class="form-group">
												<input type="button" class="btn btn-primary" onclick="query()" value="查询"/>
											</div>
										</form>
										<sys:message content="${message}"/>
										<table id="contentTable3" data-toggle="table" data-query-params="queryParams" data-mobile-responsive="true" data-height="400" data-pagination="true">
											<thead>
												<tr>
													<th data-checkbox="true" aria-hidden="true"></th>
			                                    	<th data-field="id" data-visible="false"></th>
													<th data-field="title">评论标题</th>
													<th data-field="userName1">评价人姓名(家属)</th>
													<th data-field="userName2">被评价人姓名(咨询师)</th>
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
													<shiro:hasPermission name="ante:commentInfo:edit">
														<td>
															<a class="text-navy" href="${ctx}/ante/commentInfo/view?id=${commentInfo.id}">
																<i class="glyphicon glyphicon-eye-open" aria-hidden="true" title="查看"></i>
															</a>&nbsp;&nbsp;
															<a href="#" class="text-danger" onclick="delOne('${commentInfo.id}')" title="删除">
																<i class="glyphicon glyphicon-trash" aria-hidden="true"></i>
															</a>
														</td>
													</shiro:hasPermission>
												</tr>
											</c:forEach>
										</table>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
	<%@ include file="/WEB-INF/views/include/commonlistjs.jsp" %>
	<script type="text/javascript">
		/**
		 * 查询
		 */
		function tab(tab){
			if(tab=="tab-1"){
				document.location.href="${ctx}/ante/commentInfo/list?commentType=24";
			}else if(tab=="tab-2"){
				document.location.href="${ctx}/ante/commentInfo/list?commentType=42";
			}else{
				document.location.href="${ctx}/ante/commentInfo/list?commentType=32";
			}
		}
		/**
		 * 查询
		 */
		function query(){
			var name1 = document.getElementById("userName11").value;
			var name2 = document.getElementById("userName12").value;
			var name3 = document.getElementById("userName13").value;
			var title1 = document.getElementById("title1").value;
			var title2 = document.getElementById("title2").value;
			var title3 = document.getElementById("title3").value;
			if(name1!=null || title1!=null){
				document.location.href="${ctx}/ante/commentInfo/list?userName1="+name1+"&title="+title1;
				document.getElementById("userName12").value = "";
				document.getElementById("userName13").value = "";
				document.getElementById("title2").value = "";
				document.getElementById("title3").value = "";
			}else if(name2!=null || title2!=null){
				document.location.href="${ctx}/ante/commentInfo/list?userName2="+name2+"&title="+title2;
				document.getElementById("userName11").value = "";
				document.getElementById("userName13").value = "";
				document.getElementById("title1").value = "";
				document.getElementById("title3").value = "";
			}else if(name3!=null || title3!=null){
				document.location.href="${ctx}/ante/commentInfo/list?userName3="+name3+"&title="+title3;
				document.getElementById("userName11").value = "";
				document.getElementById("userName12").value = "";
				document.getElementById("title1").value = "";
				document.getElementById("title2").value = "";
			}else{
				/*	
				document.location.href="${ctx}/ante/commentInfo/list?userName1="+name1+"&title="+title1;
				document.getElementById("userName12").value = "";
				document.getElementById("userName13").value = "";
				document.getElementById("title2").value = "";
				document.getElementById("title3").value = ""; 
				*/
			}
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