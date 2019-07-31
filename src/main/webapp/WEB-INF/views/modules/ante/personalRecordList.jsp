<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>个人成长记录管理</title>
	<meta name="decorator" content="default"/>
	
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
		
		/**
		 * 单个删除
		 */
		function delOne(id){
			layer.confirm('是否删除记录？', {
			    btn: ['确认','取消'], //按钮
			    title:'提示',
			    shade: false //不显示遮罩
			}, function(){
				document.location.href="${ctx}/ante/personalRecord/delete?id="+id;
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
			var a = $.map($("#exampleTableToolbar").bootstrapTable('getSelections'), function (row) {
		        return row.id;
		    });
			var ids=JSON.stringify(a);
			layer.confirm('是否删除选中的记录？', {
			    btn: ['确认','取消'], //按钮
			    title:'提示',
			    shade: false //不显示遮罩
			}, function(){
				window.location.href="${ctx}/ante/personalRecord/deleteAll?ids="+ids;
			    layer.msg('删除成功！', {icon: 6});
			}, function(){
			    layer.msg('已取消', {icon: 1});
			});
		}
	</script>
</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="ibox float-e-margins">
            <div class="ibox-content">
                <div class="row row-lg">
                    <div class="col-sm-12">
                        <h4 class="example-title">个人成长记录管理</h4>
                        <div class="example">
                    		<div class="btn-group " id="exampleTableEventsToolbar" role="group">
                            	<shiro:hasPermission name="ante:personalRecord:edit">
                                    <a class="J_menuItem" href="${ctx}/ante/personalRecord/form">
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
                        	<table id="exampleTableToolbar" data-toggle="table" data-query-params="queryParams" data-mobile-responsive="true" data-height="400" data-pagination="true" data-search="true">
                                <thead>
                                    <tr>
                                       	<th data-checkbox="true" aria-hidden="true"></th>
                                        <th data-field="id" data-visible="false"></th>
										<th data-field="subject">主题</th>
										<th data-field="updateDate">时间</th>
										<shiro:hasPermission name="ante:personalRecord:edit"><th>操作</th></shiro:hasPermission>
                                    </tr>
                                </thead>
                                <c:forEach items="${page.list}" var="personalRecord">
									<tr>	
										<td>${personalRecord.id}</td>
										<td>${personalRecord.id}</td>
										<td>
											<a class="text-navy" href="${ctx}/ante/personalRecord/view?id=${personalRecord.id}">
												${personalRecord.subject}
											</a>
										</td>
										<td>
											<fmt:formatDate value="${personalRecord.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
										</td>
										<shiro:hasPermission name="ante:personalRecord:edit">
											<td>
												<a href="${ctx}/ante/personalRecord/view?id=${personalRecord.id}" class="text-info" title="查看">
													<i class="fa fa-pie-chart" aria-hidden="true"></i>
												</a>&nbsp;
												<a class="text-navy" href="${ctx}/ante/personalRecord/view?id=${personalRecord.id}" title="修改">
													<i class="glyphicon glyphicon-edit" aria-hidden="true"></i>
												</a>&nbsp;
												<a href="#" class="text-danger" onclick="delOne('${personalRecord.id}')" title="删除">
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
        <!-- End Panel Basic -->
    </div>

    <script src="${hplusStatic }/js/jquery.min.js?v=2.1.4"></script>
    <script src="${hplusStatic }/js/bootstrap.min.js?v=3.3.6"></script>
    <script src="${hplusStatic }/js/content.min.js?v=1.0.0"></script>
    <script src="${hplusStatic }/js/plugins/bootstrap-table/bootstrap-table.min.js"></script>
    <script src="${hplusStatic }/js/plugins/bootstrap-table/bootstrap-table-mobile.min.js"></script>
    <script src="${hplusStatic }/js/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>
    <script src="${hplusStatic }/js/demo/bootstrap-table-demo.min.js"></script>
    <script src="${hplusStatic }/js/plugins/layer/layer.min.js" type="text/javascript"></script>
</body>
</html>