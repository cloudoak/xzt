<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<!-- Mirrored from www.zi-han.net/theme/hplus/table_bootstrap.html by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 20 Jan 2016 14:20:03 GMT -->
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${sysConfig.schoolName } 机构--咨询预约管理</title>
    <meta name="keywords" content="${fns:getConfig('keywords')} ${sysConfig.schoolName } ">
    <meta name="description" content="${fns:getConfig('description')} ${sysConfig.schoolName }">
	<%@ include file="/WEB-INF/views/include/commoncss.jsp" %>
	<%@ include file="/WEB-INF/views/include/commonlistjs.jsp" %>
	<%@include file="/WEB-INF/views/include/treetable.jsp" %>
	<script src="${ctxStatic}/common/mustache.min.js" type="text/javascript"></script>
</head>
<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="ibox float-e-margins">
            <div class="ibox-content">
                <div class="row row-lg">
                    <div class="col-sm-12">
                        <h4 class="example-title">课件分类列表</h4>
                        <div class="example">
                    		<div class="btn-group " id="exampleTableEventsToolbar" role="group">
                                <shiro:hasPermission name="course:courseCatalog:edit">
								<a class="J_menuItem" href="${ctx}/course/courseCatalog/form">
								<button type="button" class="btn btn-outline btn-default">
                                    <i class="glyphicon glyphicon-plus text-navy" aria-hidden="false"></i>
                                </button>
                                </a></shiro:hasPermission>
                            </div>
	<table id="exampleTableToolbar" data-toggle="table" data-query-params="queryParams" data-mobile-responsive="true">
		<thead>
			<tr>
				<th>名称</th>
				<th>创建时间</th>
				<shiro:hasPermission name="course:courseCatalog:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${list}" var="catalog">
		<tr>
			<td>
				<a href="${ctx}/course/courseCatalog/form?id=${catalog.id}" class="text-navy">
				${catalog.name}
				</a>
			</td>
			<td>
				<fmt:formatDate value="${catalog.createDate}" pattern="yyyy-MM-dd HH:mm:ss" type="date"/>
			</td>
			<td>
				<shiro:hasPermission name="course:courseCatalog:edit">
   				<a href="${ctx}/course/courseCatalog/form?id=${catalog.id}" class="text-navy">
   				<i class="glyphicon glyphicon-edit" aria-hidden="true"></i>
   				</a>&nbsp;
				<a href="#" onclick="delCatalog('${catalog.id}','${catalog.name}');" class="text-danger">
				<i class="glyphicon glyphicon-trash" aria-hidden="true"></i>
				</a>
				</shiro:hasPermission>
			</td>
		</tr>
		</c:forEach>
		</tbody>
	</table>
	    </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- End Panel Basic -->
    </div>
                    
</body>
	<script type="text/javascript">
		
		function delCatalog(id,name){
			layer.confirm('是否删除 ('+name+') 课件分类？', {
			    btn: ['确认','取消'], //按钮
			    title:'提示',
			    shade: false //不显示遮罩
			}, function(){
				document.location.href="${ctx}/course/courseCatalog/delete?id="+id;
			    layer.msg('删除成功！', {icon: 6});
			}, function(){
			    layer.msg('已取消', {icon: 1});
			});
		}
	</script>
	
</html>