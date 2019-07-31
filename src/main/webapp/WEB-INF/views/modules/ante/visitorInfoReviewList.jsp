<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${sysConfig.schoolName } 来访者信息管理--来访者自助审核</title>
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
                        <h4 class="example-title">来访者自助审核</h4>
						<div class="example">
							<div class="btn-group" id="exampleTableEventsToolbar" role="group">
								<a>
									<button type="button" onclick="delAll()" class="btn btn-outline btn-default">
										<i class="glyphicon glyphicon-trash text-navy" aria-hidden="false"></i>
									</button>
								</a>
							</div>
						<sys:message content="${message}"/>
						<table id="exampleTableToolbar" data-toggle="table" data-query-params="queryParams" data-mobile-responsive="true" data-height="400" data-pagination="true" data-search="true">
							<thead>
								<tr>
									<th data-checkbox="true" aria-hidden="true"></th>
                                   	<th data-field="id" data-visible="false"></th>
									<th>来访者编号</th>
									<th>姓名</th>
									<th>性别</th>
									<th>电话号码</th>
									<th>机构名称</th>
									<th>状态</th>
									<th>更新时间</th>
									<th>操作</th>
								</tr>
							</thead>
							<c:forEach items="${page.list}" var="visitorInfo">
								<tr>
									<td>${visitorInfo.id}</td>
									<td>${visitorInfo.id}</td>
									<td>
										<a class="text-navy" href="${ctx}/ante/visitorInfo/form?id=${visitorInfo.id}">
											${visitorInfo.visitorNo}
										</a>
									</td>
									<td>
										${visitorInfo.user.name}
									</td>
									<td>
										${fns:getDictLabel(visitorInfo.sex, 'sex', '')}
									</td>
									<td>
										${visitorInfo.phoneNum}
									</td>
									<td>
										${visitorInfo.org.name}
									</td>
									<td>
										<c:if test="${visitorInfo.status == '0'}">待审核</c:if>
										<c:if test="${visitorInfo.status == '1'}">审核通过</c:if>
										<c:if test="${visitorInfo.status == '2'}">审核未通过</c:if>
										<%-- ${fns:getDictLabel(visitorInfo.status, 'visitor_review_status', '')} --%>
									</td>
									<td>
										<fmt:formatDate value="${visitorInfo.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
									</td>
									<td>
										<shiro:hasPermission name="ante:visitorInfo:edit">
											<a href="#" class="text-danger" onclick="delOne('${visitorInfo.id}')" title="删除">
												<i class="glyphicon glyphicon-trash" aria-hidden="true"></i>
											</a>&nbsp;&nbsp;
											<c:choose>
							    				<c:when test="${visitorInfo.status==0}">
							    					<a href="#" class="text-navy" onclick="reviewOne('${visitorInfo.id}')" >审核</a>
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
		 * 审核一条
		 */    
		function reviewOne(id){
			layui.use(['layer', 'form'], function(){
    			var layer = layui.layer ,form = layui.form;
		        layer.confirm('确认要审核该来访者吗？', {
		            btn: ['确认','取消'], //按钮
		            title:'审核确认',
		            shade: false //不显示遮罩
		        }, function(){
		            document.location.href="${ctx}/ante/visitorInfo/reviewListSave?id="+id;
		            layer.msg('审核成功！', {icon: 6});
		        }, function(){
		            layer.msg('已取消', {icon: 1});
		        });
			});
		}
	    /**
		 * 审核多条
		 */
		function reviewAll(){
			layui.use(['layer', 'form'], function(){
    			var layer = layui.layer ,form = layui.form;
				var sumChecked=$("input:checked").length;
				if(sumChecked<1){
					layer.msg('请选择要审核的记录！', {icon: 6});
					return false;
				}
				var a = $.map($("#exampleTableToolbar").bootstrapTable('getSelections'), function (row) {
			        return row.id;
			    });
				var ids=JSON.stringify(a);
				layer.confirm('是否审核选中的记录？', {
				    btn: ['确认','取消'], //按钮
				    title:'提示',
				    shade: false //不显示遮罩
				}, function(){
					window.location.href="${ctx}/ante/visitorInfo/reviewAll?ids="+ids;
				    //layer.msg('审核成功！', {icon: 6});
				}, function(){
				    layer.msg('已取消', {icon: 1});
				});
			});
		}
	    
		/**
		 * 删除一条
		 */    
		function delOne(id){
			layui.use(['layer', 'form'], function(){
    			var layer = layui.layer ,form = layui.form;
		        layer.confirm('确认要删除该来访者吗？', {
		            btn: ['确认','取消'], //按钮
		            title:'删除确认',
		            shade: false //不显示遮罩
		        }, function(){
		            document.location.href="${ctx}/ante/visitorInfo/delete?id="+id+"&reviewSign=true";
		            layer.msg('删除成功！', {icon: 6});
		        }, function(){
		            layer.msg('已取消', {icon: 1});
		        });
			});
	    }
	    
	    /**
		 * 删除多条
		 */
		function delAll(){
			layui.use(['layer', 'form'], function(){
    			var layer = layui.layer ,form = layui.form;
				var sumChecked=$("input:checked").length;
				if(sumChecked<1){
					layer.msg('请选择要删除的记录！', {icon: 6});
					return false;
				}
				var a = $.map($("#exampleTableToolbar").bootstrapTable('getSelections'), function (row) {
			        return row.id;
			    });
				var ids=JSON.stringify(a);
				layer.confirm('是否删除选中的记录？', {
				    btn: ['确认','取消'], //按钮
				    title:'提示',
				    shade: false //不显示遮罩
				}, function(){
					window.location.href="${ctx}/ante/visitorInfo/deleteAll?ids="+ids+"&reviewSign="+true;
				}, function(){
				    layer.msg('已取消', {icon: 1});
				});
			});
		}
	</script>
</body>
</html>