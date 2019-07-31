<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scaleTotalExplain=1.0">

    <title>总解释管理</title>
    <meta name="keywords" content="${fns:getConfig('keywords')} ${sysConfig.schoolName } ">
    <meta name="description" content="${fns:getConfig('description')} ${sysConfig.schoolName }">
    
    <link href="${hplusStatic }/favicon.ico" rel="shortcut icon">
	<link href="${hplusStatic }/css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
    <link href="${hplusStatic }/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
    <link href="${hplusStatic }/css/animate.min.css" rel="stylesheet">
    <link href="${hplusStatic }/css/style.min862f.css?v=4.1.0" rel="stylesheet">
    <link href="${hplusStatic }/css/plugins/bootstrap-table/bootstrap-table.min.css" rel="stylesheet">
    <link href="${hplusStatic }/css/plugins/awesome-bootstrap-checkbox/awesome-bootstrap-checkbox.css" rel="stylesheet">
    <link href="${hplusStatic }/css/plugins/summernote/summernote.css" rel="stylesheet">
    <link href="${hplusStatic }/css/plugins/summernote/summernote-bs3.css" rel="stylesheet">
    <link href="${hplusStatic }/css/plugins/iCheck/custom.css" rel="stylesheet">
    
	<link href="${serverUrl }css/measure.css" type="text/css" rel="stylesheet" />

</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5><a class="text-navy" href="javascript:">总解释管理</a></h5>
                        <div class="ibox-tools">
                        </div>
                    </div>
                    <div class="ibox-content">
                        <form class="form-horizontal m-t" id="signupForm" action="${ctx}/jzmk/scale/totalExplain" method="post">
                        <input type="hidden" name="explain" value="0"/>
                        <input type="hidden" name="id" value="${tid}"/>
                           <div class="form-group">
                                <label class="col-sm-3 control-label"></label>
                                <div class="col-sm-12">
			                    	<div class="liangbao">
							         	<a href="#" class="hover"><em class="em1 hover"></em>01基本信息</a>
							            <a href="#" class="hover"><em class="em2 hover"></em>02条目设置</a>
							            <a href="#" class="hover"><em class="em3 hover"></em>03因子设置</a>
							            <a href="#" class="hover"><em class="em4 hover"></em>04因子解释</a>
							            <a href="#" class="hover"><em class="em5 hover"></em>05总解释</a>
							         </div>
						        </div>
                            </div>
                            <div class="example">
                    		<div class="btn-group " id="exampleTableEventsToolbar" role="group">
                                <button type="button" class="btn btn-outline btn-default" onclick="addScaleTotalExplain(${tid});">
                                    <a class="J_menuItem" href="javascript:void();">
                                    <i class="glyphicon glyphicon-plus text-navy" aria-hidden="false">总解释</i>
                                   	</a>
                                </button>
                            </div>
                            <table id="exampleTableToolbar" data-toggle="table" data-query-params="queryParams" data-mobile-responsive="true" data-height="400" data-pagination="true" data-search="true">
                                <thead>
                                    <tr>
                                        <th data-field="" data-checkbox="true" aria-hidden="true"></th>
                                        <th data-field="id" data-visible="false">ID</th>
										<th>总解释</th>
										<th>起始分</th>
										<th>结束分</th>
										<shiro:hasPermission name="jzmk:scaleTotalExplain:edit"><th>操作</th></shiro:hasPermission>
                                    </tr>
                                </thead>
                                <c:forEach items="${page.list }" var="scaleTotalExplain" varStatus="status">
									<tr>
										<td data-field="id" data-checkbox="true" aria-hidden="true"></td>
										<td>${scaleTotalExplain.id }</td>
										<td>${scaleTotalExplain.explainContent}</a></td>
										<td>${scaleTotalExplain.minValue}</td>
										<td>${scaleTotalExplain.maxValue}</td>
										<td>${scaleTotalExplain.tid}
											<a class="text-navy" href="${ctx}/jzmk/scaleTotalExplain/form?id=${scaleTotalExplain.id}&sid=${scaleTotalExplain.tid}">
												<i class="glyphicon glyphicon-edit" aria-hidden="true"></i>
											</a>&nbsp;
											<a href="#" class="text-danger" onclick="delscaleTotalExplain('${scaleTotalExplain.id}','${scaleTotalExplain.tid}')">
												<i class="glyphicon glyphicon-trash" aria-hidden="true"></i>
											</a>
										</td>
									</tr>
								</c:forEach>
                            </table>
							<div class="form-group">
                                <div class="col-sm-8 col-sm-offset-3">
                                    <button class="btn btn-primary" type="button" onclick="gotoGeneExplainPage(${tid})">上一步</button>&nbsp;
                                    <button class="btn btn-primary" type="button" onclick="finish(${tid})">完成</button>
                                </div>
                            </div>
                        </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script src="${hplusStatic }/js/jquery.min.js?v=2.1.4"></script>
    <script src="${hplusStatic }/js/bootstrap.min.js?v=3.3.6"></script>
    <script src="${hplusStatic }/js/content.min.js?v=1.0.0"></script>
    <script src="${hplusStatic }/js/plugins/validate/jquery.validate.min.js"></script>
    <script src="${hplusStatic }/js/plugins/validate/messages_zh.min.js"></script>
    <script src="${hplusStatic }/js/demo/form-validate-demo.min.js"></script>
    <script src="${hplusStatic }/js/plugins/summernote/summernote.min.js"></script>
    <script src="${hplusStatic }/js/plugins/summernote/summernote-zh-CN.js"></script>
    <script src="${hplusStatic }/js/plugins/iCheck/icheck.min.js"></script>
    <script src="${hplusStatic }/js/plugins/bootstrap-table/bootstrap-table.min.js"></script>
    <script src="${hplusStatic }/js/plugins/bootstrap-table/bootstrap-table-mobile.min.js"></script>
    <script src="${hplusStatic }/js/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>
    <script src="${hplusStatic }/js/demo/bootstrap-table-demo.min.js"></script>
    <script src="${hplusStatic }/js/plugins/layer/layer.min.js" type="text/javascript"></script>
    <script>
	    function gotoGeneExplainPage(tid){
	    	document.location.href = "${ctx}/jzmk/gene/geneList?tid=" + tid;
	    }
	    function windowOpen(url, name, width, height){
	    	var top=parseInt((window.screen.height-height)/2,10),left=parseInt((window.screen.width-width)/2,10),
	    		options="location=no,menubar=no,toolbar=no,dependent=yes,minimizable=no,modal=yes,alwaysRaised=yes,"+
	    		"resizable=yes,scrollbars=yes,"+"width="+width+",height="+height+",top="+top+",left="+left;
	    	window.open(url ,name , options);
	    }
    	//删除总解释
	    function delscaleTotalExplain(id,sid){
			layer.confirm('是否删除该总解释？', {
			    btn: ['确认','取消'], //按钮
			    title:'删除确认',
			    shade: false //不显示遮罩
			}, function(){
				document.location.href="${ctx}/jzmk/scaleTotalExplain/delete?id="+id+"&sid="+sid;
			    layer.msg('删除成功！', {icon: 6});
			}, function(){
			    layer.msg('已取消', {icon: 1});
			});
		}
    	
	    function finish(tid){
	    	layer.open({
			    type: 1,
			    title: "成功信息",
			    shadeClose:true,
			    shade: 0.3,
			    area: ['340px', '215px'],
			    offset: 'c',
			    shift: 2,
			    content: "<br/> 进行量表维护 / 继续添加量表？",
			    btn:['添加量表','量表维护'],
			    yes: function(index, layero){
			    	//继续添加量表
			    	document.location.href = "${ctx}/jzmk/scale/totalExplain?explain=0&id=" + tid;   
			    }, 
			    btn2: function(index, layero){
			       //进入量表维护
			    	document.location.href = "${ctx}/jzmk/scale/totalExplain?explain=1&id=" + tid;
			    }
			});
	    	return false;
	   	};
	   	
	   	function addScaleTotalExplain(tid){
	   		location.href="${ctx}/jzmk/scaleTotalExplain/form?tid=" + tid;
	   	}
        
    	$(function(){
    		$(".i-checks").iCheck({checkboxClass:"icheckbox_square-green",radioClass:"iradio_square-green"});
    	});
    </script>
</body>
</html>