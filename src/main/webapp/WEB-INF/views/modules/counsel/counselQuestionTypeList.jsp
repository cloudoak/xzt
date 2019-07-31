<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>咨询问题类型</title>
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
                     <h4 class="example-title">咨询问题类型列表</h4>
                     <div class="example">
              		<div class="btn-group " id="exampleTableEventsToolbar" role="group">
                          <button type="button" class="btn btn-outline btn-default">
                              <shiro:hasPermission name="counsel:counselQuestionType:edit">
                              <a class="J_menuItem" href="${ctx}/counsel/counselQuestionType/form">
                              <i class="glyphicon glyphicon-plus text-navy" aria-hidden="false"></i>
                             	</a></shiro:hasPermission>
                          </button>
                          <button type="button" class="btn btn-outline btn-default">
                              <i class="glyphicon glyphicon-edit text-navy" aria-hidden="false"></i>
                          </button>
                          <button type="button" class="btn btn-outline btn-default">
                              <i class="glyphicon glyphicon-trash text-navy" aria-hidden="false"></i>
                          </button>
                     </div>
	 				<table id="exampleTableToolbar" data-toggle="table" data-query-params="queryParams" 
	 					data-mobile-responsive="true" data-height="400" data-pagination="true" data-search="true">
                                <thead>
                                    <tr>
                                        <th data-field="id" data-checkbox="true" aria-hidden="true"></th>
                                        <th data-field="counselQuestionType.name">问题名称</th>
                                        <th data-field="counselQuestionType.remark">说明</th>
                                        <th>操作</th>
                                    </tr>
                                </thead>
                                <c:forEach items="${page.list}" var="counselQuestionType">
									<tr>
										<td data-field="id" data-checkbox="true" aria-hidden="true"></td>
										<td>${counselQuestionType.name}</td>
										<td title="${counselQuestionType.remark}">${fns:abbr(counselQuestionType.remark,30)}</td>
										<td>
											<a class="text-navy" href="${ctx}/counsel/counselQuestionType/form?id=${counselQuestionType.id}">
												<i class="glyphicon glyphicon-edit" aria-hidden="true"></i>
											</a>&nbsp;
											<a class="text-danger" href="javascript:delCounselQuestionType(${counselQuestionType.id});" />
												<i class="glyphicon glyphicon-trash" aria-hidden="true"></i>
											</a>
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
</body>
<script src="${hplusStatic }/js/jquery.min.js?v=2.1.4"></script>
<script src="${hplusStatic }/js/bootstrap.min.js?v=3.3.6"></script>
<script src="${hplusStatic }/js/content.min.js?v=1.0.0"></script>
<script src="${hplusStatic }/js/plugins/bootstrap-table/bootstrap-table.min.js"></script>
<script src="${hplusStatic }/js/plugins/bootstrap-table/bootstrap-table-mobile.min.js"></script>
<script src="${hplusStatic }/js/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>
<script src="${hplusStatic }/js/plugins/layer/layer.min.js" type="text/javascript"></script>
<script type="text/javascript">
function page(n,s){
	$("#pageNo").val(n);
	$("#pageSize").val(s);
	$("#searchForm").submit();
	return false;
}
function delCounselQuestionType(id){
	layer.confirm('是否删除该问题类型？', {
	    btn: ['确认','取消'], //按钮
	    title:'删除确认',
	    shade: false //不显示遮罩
	}, function(){
		document.location.href="${ctx}/counsel/counselQuestionType/delete?id="+id;
	    layer.msg('删除成功！', {icon: 6});
	}, function(){
	    layer.msg('已取消', {icon: 1});
	});
}
$(function() {
	
});
</script>
</html>