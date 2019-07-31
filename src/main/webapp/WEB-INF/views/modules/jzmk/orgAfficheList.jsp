<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<!-- Mirrored from www.zi-han.net/theme/hplus/table_bootstrap.html by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 20 Jan 2016 14:20:03 GMT -->
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${sysConfig.schoolName }机构公告</title>
    <meta name="keywords" content="${fns:getConfig('keywords')} ${sysConfig.schoolName } ">
    <meta name="description" content="${fns:getConfig('description')} ${sysConfig.schoolName }">

    <link rel="shortcut icon" href="${hplusStatic }favicon.ico"> 
    <link href="${hplusStatic }/css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
    <link href="${hplusStatic }/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
    <link href="${hplusStatic }/css/plugins/bootstrap-table/bootstrap-table.min.css" rel="stylesheet">
    <link href="${hplusStatic }/css/animate.min.css" rel="stylesheet">
    <link href="${hplusStatic }/css/style.min862f.css?v=4.1.0" rel="stylesheet">
    
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
                        <h4 class="example-title">系统公告列表</h4>
                        <div class="example">
                    		<div class="btn-group " id="exampleTableEventsToolbar" role="group">
                                <shiro:hasPermission name="jzmk:sysAffiche:edit">
                                 	<a class="J_menuItem" href="${ctx}/jzmk/sysAffiche/org/form">
                                 		<button type="button" class="btn btn-outline btn-default">
	                                 		<i class="glyphicon glyphicon-plus text-navy" aria-hidden="false"></i>
	                                 	</button>
                                	</a>
                               	</shiro:hasPermission>
                               	<a>
	                                <button type="button" onclick="update()" class="btn btn-outline btn-default">
	                                    <i class="glyphicon glyphicon-edit text-navy" aria-hidden="false"></i>
	                                </button>
                               	</a>
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
                                        <th data-field="afficheTitle" align="center">公告标题</th>
                                        <th data-field="firstDisplay" align="center">首页显示</th>
                                        <shiro:hasPermission name="jzmk:sysAffiche:edit"><th align="center">操作</th></shiro:hasPermission>
                                    </tr>
                                </thead>
                                <c:forEach items="${page.list}" var="sysAffiche">
									<tr>
										<td>${sysAffiche.id}</td>
										<td>${sysAffiche.id}</td>
										<td><a class="text-navy" href="${ctx}/jzmk/sysAffiche/org/view?id=${sysAffiche.id}">${sysAffiche.afficheTitle}</a></td>
										<td>${sysAffiche.firstDisplay==0?"否":"是"}</td>
										<td>
											<shiro:hasPermission name="jzmk:sysAffiche:edit">
											<a class="text-navy" href="${ctx}/jzmk/sysAffiche/org/view?id=${sysAffiche.id}" title="查看">
												<i class="fa fa-pie-chart" aria-hidden="true"></i>
											</a>&nbsp;&nbsp;
											<a class="text-navy" href="${ctx}/jzmk/sysAffiche/org/form?id=${sysAffiche.id}" title="修改">
												<i class="glyphicon glyphicon-edit" aria-hidden="true"></i>
											</a>&nbsp;&nbsp;
											<a href="#" class="text-danger" onclick="delOrgAffiche('${sysAffiche.id}','${sysAffiche.afficheTitle}')" title="删除">
												<i class="glyphicon glyphicon-trash" aria-hidden="true"></i>
											</a>
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
	<script type="text/javascript">
		/**
		 * 修改
		 */
		function update(){
			var a = $.map($("#exampleTableToolbar").bootstrapTable('getSelections'), function (row) {
		        return row.id;
		    });
			alert(a);
			if(a==null){
				layer.msg('请选择要修改的一条记录！', {icon: 6});
				return false;
			}
			var id=JSON.stringify(a);
			layer.confirm('是否修改选中的记录？', {
			    btn: ['确认','取消'], //按钮
			    title:'提示',
			    shade: false //不显示遮罩
			}, function(){
				window.location.href="${ctx}/jzmk/sysAffiche/org/form?id="+id;
			}, function(){
			    layer.msg('已取消', {icon: 1});
			});
		}	
	
		/**
		 * 单个删除
		 */
		function delOrgAffiche(id,name){
			layer.confirm('是否删除 ('+name+') 这条公告？', {
			    btn: ['确认','取消'], //按钮
			    title:'删除确认',
			    shade: false //不显示遮罩
			}, function(){
				document.location.href="${ctx}/jzmk/sysAffiche/org/delete?id="+id;
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
				window.location.href="${ctx}/jzmk/sysAffiche/deleteAll?ids="+ids;
			    //layer.msg('删除成功！', {icon: 6});
			}, function(){
			    layer.msg('已取消', {icon: 1});
			});
		}
	</script>
</body>
<!-- Mirrored from www.zi-han.net/theme/hplus/table_bootstrap.html by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 20 Jan 2016 14:20:06 GMT -->
</html>
