<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>条目管理</title>
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
    
    <link href="${serverUrl }css/measure.css" type="text/css" rel="stylesheet" />

</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5><a class="text-navy" href="${ctx}/jzmk/question/form?id=${content.id}">条目
                        <shiro:hasPermission name="sys:content:edit">${not empty content.id?'修改':'添加'}</shiro:hasPermission>
                        <shiro:lacksPermission name="sys:content:edit">查看</shiro:lacksPermission></a></h5>
                        <div class="ibox-tools">
                        </div>
                    </div>
                    <div class="ibox-content">
                        <form class="form-horizontal m-t" id="signupForm" action="${ctx}/jzmk/question/save" method="post">
                            <input type="hidden" name="id" value="${question.id }">
                            <input type="hidden" name="sid" value="${param.sid }">
                            <div class="form-group">
                                <label class="col-sm-3 control-label"></label>
                                <div class="col-sm-12">
			                    	<div class="liangbao">
							         	<a href="#" class="hover"><em class="em1 hover"></em>01基本信息</a>
							            <a href="#" class="hover"><em class="em2 hover"></em>02条目设置</a>
							            <a href="#"><em class="em3"></em>03因子设置</a>
							            <a href="#"><em class="em4"></em>04因子解释</a>
							            <a href="#"><em class="em5"></em>05总解释</a>
							         </div>
						        </div>
                            </div>
                            <c:if test="${isAdmin == 2 }">
                            <div class="form-group">
                                <label class="col-sm-3 control-label">父条目：</label>
                                <div class="col-sm-8">
                                	<input id="parentId" name="parentId" value="${question.parentId }" type="hidden">
                                	
                                	<table data-toggle="table" data-query-params="queryParams" data-page-size="100" data-click-to-select="true" data-checkbox-header="true"
                                	data-mobile-responsive="true" data-height="400" data-pagination="true" data-search="true" >
		                                <thead>
		                                    <tr>
		                                        <th data-field="id"><input type="checkbox" name="btSelectAll" value="0" /></th>
		                                        <th data-field="number">序号</th>
		                                        <th data-field="content">条目</th>
		                                    </tr>
		                                </thead>
		                                <c:forEach items="${parents }" var="parent" varStatus="status" >
											<tr>
												<td data-field="id"><input type="checkbox" name="btSelectItem" value="${parent.id}" /></td>
												<td data-field="number">${parent.number}</td>
												<td data-field="content">${parent.content}</td>
											</tr>
										</c:forEach>
		                            </table>
		                            
                                </div>
                            </div>
                            </c:if>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">条目内容：</label>
                                <div class="col-sm-8">
                                 	<input id="content" name="content" type="hidden" value="">
                                    <div class="summernote"></div>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-8 col-sm-offset-3">
                                    <button class="btn btn-primary" type="submit">保存</button>&nbsp;
                                    <button class="btn btn-primary" type="button" onclick="gotoQuestionPage(${param.sid });">返回</button>
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
    <script src="${hplusStatic }/js/plugins/bootstrap-table/bootstrap-table.min.js"></script>
    <script src="${hplusStatic }/js/plugins/bootstrap-table/bootstrap-table-mobile.min.js"></script>
    <script src="${hplusStatic }/js/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>
    <script src="${hplusStatic }/js/plugins/summernote/summernote.min.js"></script>
    <script src="${hplusStatic }/js/plugins/summernote/summernote-zh-CN.js"></script>
    <script>
    	var parentId = '${question.parentId}';
    
	    function gotoQuestionPage(sid){
	    	document.location.href = "${ctx}/jzmk/question/qa?sid=" + sid;
	    }
    
	    function windowOpen(url, name, width, height){
	    	var top=parseInt((window.screen.height-height)/2,10),left=parseInt((window.screen.width-width)/2,10),
	    		options="location=no,menubar=no,toolbar=no,dependent=yes,minimizable=no,modal=yes,alwaysRaised=yes,"+
	    		"resizable=yes,scrollbars=yes,"+"width="+width+",height="+height+",top="+top+",left="+left;
	    	window.open(url ,name , options);
	    }
        $(function(){
        	
        	//ifClicked
        	$("input[type=checkbox][name=btSelectAll]").on('click', function(event){
	       		 var checked = $(this).is(':checked');
	           	$("input[type=checkbox][name=btSelectItem]").each(function () {  
	           		if (checked) {  
	                    $(this).attr("checked", "checked"); //.iCheck('check');
                    }else{
                   	 	$(this).removeAttr("checked"); //.iCheck('uncheck');  
                    }
	   			 });
	       	}); 
        	/* <div class="i-checks"></div> */
         	if(parentId && parentId.length > 0){
        		$("input[type=checkbox][name=btSelectAll]").attr("checked", "checked"); //.iCheck('check'); 
	       	}else{
	       		$("input[type=checkbox][name=btSelectAll]").removeAttr("checked"); //.iCheck('uncheck'); 
	       	} 
        	
        	  $("input[type=checkbox][name=btSelectItem]").each(function ( ) { 
        		var selectItem = $(this).val();
				 if (parentId.toString().indexOf($(this).val()) != -1) {  
                     $(this).attr("checked", "checked"); //.iCheck('check');
                 }else{
                	 $(this).removeAttr("checked"); //.iCheck('uncheck');  
                 }
			 });
        	
        	$(".summernote").summernote({
        		lang:"zh-CN",
        		height: 300,
        		focus:true
        	});
        	$('.summernote').code('${question.content }');
        });
       	$("button[type=submit]").click(function(){
       			var parentIds = [];
				 $("input[type=checkbox][name=btSelectItem]").each(function (i, e) {  
					 if ($(this).is(':checked')) {  
						 parentIds.push($(this).val());  
	                  }
				 });
				 if(parentIds.length > 0){
					$("#parentId").val(parentIds.join(','));
				 }
       		var sHTML = $('.summernote').code();
       		$("#content").val(sHTML);
       	});
    </script>
</body>
</html>