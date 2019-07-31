<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${sysConfig.schoolName } 公益活动--查看公益活动</title>
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
                        <h4 class="example-title">公益活动列表</h4>
						<div class="example">
                   			<div class="btn-group " id="exampleTableEventsToolbar" role="group">
                                <shiro:hasPermission name="ante:publicActivity:edit">
									<a class="J_menuItem" href="${ctx}/ante/publicActivity/form?myActivity=false">
										<button type="button" class="btn btn-outline btn-default">
	                                    	<i class="glyphicon glyphicon-plus text-navy" aria-hidden="false"></i>
		                                </button>
	                               	</a>
                               	</shiro:hasPermission>
                               	<!-- <a>
	                                <button type="button" class="btn btn-outline btn-default">
	                                    <i class="glyphicon glyphicon-edit text-navy" aria-hidden="false"></i>
	                                </button>
                               	</a> -->
                               	<a>
	                                <button type="button" onclick="delAll()" class="btn btn-outline btn-default">
	                                    <i class="glyphicon glyphicon-trash text-navy" aria-hidden="false"></i>
	                                </button>
                               	</a>
                            </div>
						<sys:message content="${message}"/>
						<form id="searchForm" action="${ctx}/ante/publicActivity/list" method="post" class="form-inline">
							<div class="form-group">
								<label>开始时间：</label>
								<input name="beginUpdateDate" type="text"
									readonly="readonly" maxlength="20" class="form-control Wdate "
									style="border: 1px solid #e5e6e7;padding: 6px 12px;height: 34px;line-height:1.42857143"
									value="<fmt:formatDate value="${publicActivity.beginUpdateDate}" pattern="yyyy-MM-dd"/>"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" />
							</div>
							<div class="form-group">
								<label>结束时间：</label>
								<input name="endTime" type="text"
									readonly="readonly" maxlength="20" class="form-control Wdate "
									style="border: 1px solid #e5e6e7;padding: 6px 12px;height: 34px;line-height:1.42857143"
									value="<fmt:formatDate value="${publicActivity.endTime}" pattern="yyyy-MM-dd"/>"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" />
							</div>
							<div class="form-group">
								<label>状态：</label>
								<select name="checkStatus" class="form-control">
									<option value="">--请选择--</option>
									<c:forEach items="${fns:getDictList('public_activity_check_status')}" var="dict">
										<option value="${dict.value}">${dict.label}</option>
									</c:forEach>
								</select>
							</div>
							<div class="form-group">
								<label>所属机构：</label>
								<input id="orgName" type="text" class="form-control" maxlength="11"/>
							</div>
							<div class="form-group">
								<label>标题：</label>
								<input id="title" type="text" class="form-control" maxlength="30"/>
							</div>
							<div class="form-group">
								<button id="btnQuery" class="btn btn-primary">查询</button>
							</div>
						</form>
					    <table id="publicActivityTable" data-toggle="table" data-query-params="queryParams" data-mobile-responsive="true" data-height="400" data-pagination="true">
							<thead>
								<tr>
									<th data-checkbox="true" aria-hidden="true"></th>
                                    <th data-field="id" data-visible="false"></th>
									<th data-field="title">标题</th>
									<th data-field="createName">发布人</th>
									<th data-field="updateDate">发布时间</th>
									<th data-field="orgName">所属机构</th>
									<th data-field="activityStatus">活动状态</th>
									<th data-field="checkStatus">审核状态</th>
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
										${fns:getDictLabel(publicActivity.activityStatus, 'public_activity_status', '')}
									</td>
									<td>
										${fns:getDictLabel(publicActivity.checkStatus, 'public_activity_check_status', '')}
									</td>
									<shiro:hasPermission name="ante:publicActivity:edit"><td>
										<a class="text-navy" href='${ctx}/ante/publicActivity/view?id=${publicActivity.id}'>查看</a>&nbsp;&nbsp;
										<a class="text-navy" href='${ctx}/ante/publicActivity/form?id=${publicActivity.id}'>修改</a>&nbsp;&nbsp;
					    				<a class="text-danger" href="#" onclick="delOne('${publicActivity.id}')">删除</a>&nbsp;&nbsp;
					    				<c:choose>
						    				<c:when test="${publicActivity.checkStatus==1 && (publicActivity.activityStatus==1 || publicActivity.activityStatus==4)}">
						    					<a class="text-navy" href="#" onclick="enroll('${publicActivity.id}')">报名</a>
						    				</c:when>
						    				<c:when test="${publicActivity.activityStatus==2}">已结束</c:when>
						    				<c:when test="${publicActivity.activityStatus==3}">已报名</c:when>
						    				<c:otherwise>未开始</c:otherwise>
					    				</c:choose>
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
		 * 报名
		 */
		function enroll(id){
			layer.confirm('是否报名活动？', {
			    btn: ['确认','取消'], //按钮
			    title:'提示',
			    shade: false //不显示遮罩
			}, function(){
				document.location.href="${ctx}/ante/publicActivity/enroll?id="+id;
			    layer.msg('报名成功！', {icon: 6});
			}, function(){
			    layer.msg('已取消', {icon: 1});
			});
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
				document.location.href="${ctx}/ante/publicActivity/delete?id="+id+"&flag=0";
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
			var a = $.map($("#publicActivityTable").bootstrapTable('getSelections'), function (row) {
		        return row.id;
		    });
			var ids=JSON.stringify(a);
			layer.confirm('是否删除选中的记录？', {
			    btn: ['确认','取消'], //按钮
			    title:'提示',
			    shade: false //不显示遮罩
			}, function(){
				window.location.href="${ctx}/ante/publicActivity/deleteAll?ids="+ids;
			    //layer.msg('删除成功！', {icon: 6});
			}, function(){
			    layer.msg('已取消', {icon: 1});
			});
		}
	</script>
</body>

</html>