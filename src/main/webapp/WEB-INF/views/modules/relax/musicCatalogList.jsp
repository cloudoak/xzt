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

	<script type="text/javascript">
		$(document).ready(function() {
			var tpl = $("#treeTableTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
			var data = ${fns:toJson(list)}, ids = [], rootIds = [];
			for (var i=0; i<data.length; i++){
				ids.push(data[i].id);
			}
			ids = ',' + ids.join(',') + ',';
			for (var i=0; i<data.length; i++){
				if (ids.indexOf(','+data[i].parentId+',') == -1){
					if ((','+rootIds.join(',')+',').indexOf(','+data[i].parentId+',') == -1){
						rootIds.push(data[i].parentId);
					}
				}
			}
			for (var i=0; i<rootIds.length; i++){
				addRow("#treeTableList", tpl, data, rootIds[i], true);
			}
			$("#treeTable").treeTable({expandLevel : 5});
		});
		function addRow(list, tpl, data, pid, root){
			for (var i=0; i<data.length; i++){
				var row = data[i];
				if ((${fns:jsGetVal('row.parentId')}) == pid){
					$(list).append(Mustache.render(tpl, {
						dict: {
						blank123:0}, pid: (root?0:pid), row: row
					}));
					addRow(list, tpl, data, row.id);
				}
			}
		}
	</script>
</head>
<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="ibox float-e-margins">
            <div class="ibox-content">
                <div class="row row-lg">
                    <div class="col-sm-12">
                        <h4 class="example-title">音乐分类列表</h4>
                        <div class="example">
                    		<div class="btn-group " id="exampleTableEventsToolbar" role="group">
                                <shiro:hasPermission name="relax:musicCatalog:edit">
								<a class="J_menuItem" href="${ctx}/relax/musicCatalog/form">
								<button type="button" class="btn btn-outline btn-default">
                                    <i class="glyphicon glyphicon-plus text-navy" aria-hidden="false"></i>
                                </button>
								</a></shiro:hasPermission>
								<a>
                                <button type="button" class="btn btn-outline btn-default">
                                    <i class="glyphicon glyphicon-edit text-navy" aria-hidden="false"></i>
                                </button>
                                </a>
                                <a>
                                <button type="button" class="btn btn-outline btn-default">
                                    <i class="glyphicon glyphicon-trash text-navy" aria-hidden="false"></i>
                                </button>
                                </a>
                            </div>
							<sys:message content="${message}"/>	
							<table id="treeTable" class="table table-striped table-bordered table-condensed">
							<thead>
							<tr>
								<th>名称</th>
								<th>创建时间</th>
								<th>备注</th>
								<shiro:hasPermission name="relax:musicCatalog:edit"><th>操作</th></shiro:hasPermission>
							</tr>
							</thead>
							<tbody id="treeTableList"></tbody>
							</table>
	   					 </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- End Panel Basic -->
    </div>
	
	<script type="text/template" id="treeTableTpl">
		<tr id="{{row.id}}" pId="{{pid}}">
			<td><a href="${ctx}/relax/musicCatalog/form?id={{row.id}}">
				{{row.name}}
			</a></td>			
			<td>
				{{row.createDate}}
			</td>
			<td>
				{{row.remarks}}
			</td>
			<shiro:hasPermission name="relax:musicCatalog:edit"><td>
   				<a href="${ctx}/relax/musicCatalog/form?id={{row.id}}">修改</a>
				<a href="${ctx}/relax/musicCatalog/delete?id={{row.id}}" onclick="return confirmx('确认要删除该音乐分类及所有子音乐分类吗？', this.href)">删除</a>
				<a href="${ctx}/relax/musicCatalog/form?parent.id={{row.id}}">添加下级音乐分类</a>  
			</td></shiro:hasPermission>
		</tr>
	</script>
	
</body>
</html>