<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<!-- Mirrored from www.zi-han.net/theme/hplus/table_bootstrap.html by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 20 Jan 2016 14:20:03 GMT -->
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${sysConfig.schoolName } 机构</title>
    <meta name="keywords" content="${fns:getConfig('keywords')} ${sysConfig.schoolName } ">
    <meta name="description" content="${fns:getConfig('description')} ${sysConfig.schoolName }">

    <link rel="shortcut icon" href="${hplusStatic }favicon.ico"> 
    <link href="${hplusStatic }/css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
    <link href="${hplusStatic }/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
    <link href="${hplusStatic }/css/plugins/bootstrap-table/bootstrap-table.min.css" rel="stylesheet">
    <link href="${hplusStatic }/css/animate.min.css" rel="stylesheet">
    <link href="${hplusStatic }/css/style.min862f.css?v=4.1.0" rel="stylesheet">

</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="ibox float-e-margins">
            <div class="ibox-content">
                <div class="row row-lg">
                    <div class="col-sm-12">
                        <h4 class="example-title">机构列表</h4>
                        <div class="example">
                    		<div class="btn-group " id="exampleTableEventsToolbar" role="group">
                            	<shiro:hasPermission name="sys:office:edit">
                                    <a class="J_menuItem" href="${ctx}/sys/org/form">
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
                                        <th data-field="name">机构名称</th>
                                        <!-- <th data-field="master">负责人</th>
                                        <th data-field="phone">联系电话</th> -->
                                        <th>操作</th>
                                    </tr>
                                </thead>
                                <c:forEach items="${list }" var="office">
									<c:if test="${office.type==1 }">
									<tr>
										<td></td>
										<td>${office.id }</td>
										<td><a class="text-navy" href="${ctx}/sys/org/form?id=${office.id}&viewonly=1">${office.name}</a></td>
										<td>
											<a href="${ctx}/sys/org/allot?id=${office.id}" class="text-info">
												<i class="fa fa-pie-chart" aria-hidden="true"></i>
											</a>&nbsp;
											<shiro:hasPermission name="sys:office:edit">
											<a class="text-navy" href="${ctx}/sys/org/form?id=${office.id}">
												<i class="glyphicon glyphicon-edit" aria-hidden="true"></i>
											</a>&nbsp;
											</shiro:hasPermission>
											<a href="#" class="text-danger" onclick="delOffice('${office.id}','${office.name}')">
												<i class="glyphicon glyphicon-trash" aria-hidden="true"></i>
											</a>
										</td>
									</tr>
									</c:if>
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
    <!-- <script src="http://tajs.qq.com/stats?sId=9051096" charset="UTF-8" type="text/javascript"></script> -->
	<script type="text/javascript">
	//${ctx}/sys/org/delete?id=${office.id}
		//删除选中的机构
		function delOffice(id,name){
			layer.confirm('是否删除 ('+name+') 机构？', {
			    btn: ['确认','取消'], //按钮
			    title:'提示',
			    shade: false //不显示遮罩
			}, function(){
				document.location.href="${ctx}/sys/org/deleteOrg?id="+id;
			    layer.msg('删除成功！', {icon: 6});
			}, function(){
			    layer.msg('已取消', {icon: 1});
			});
		}
		//删除所有的机构
		function delAll(){
			var sumChecked=$("input:checked").length;
			if(sumChecked<3){
				layer.msg('请选择你要删除的机构', {icon: 6});
				return false;
			}
			var a = $.map($("#exampleTableToolbar").bootstrapTable('getSelections'), function (row) {
		        return row.id;
		    });
			var ids=JSON.stringify(a);
			layer.confirm('是否删除选中的机构？', {
			    btn: ['确认','取消'], //按钮
			    title:'提示',
			    shade: false //不显示遮罩
			}, function(){
				window.location.href="${ctx}/sys/org/deleteAll?orgIds="+ids;
			    layer.msg('删除成功！', {icon: 6});
			}, function(){
			    layer.msg('已取消', {icon: 1});
			});
		}
		
	</script>
</body>
<!-- Mirrored from www.zi-han.net/theme/hplus/table_bootstrap.html by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 20 Jan 2016 14:20:06 GMT -->
</html>
