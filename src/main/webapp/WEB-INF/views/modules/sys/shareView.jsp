<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>共享积分管理</title>
	<meta charset="utf-8">
	<meta name="decorator" content="default"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="keywords" content="${fns:getConfig('keywords')} ${sysConfig.schoolName } ">
    <meta name="description" content="${fns:getConfig('description')} ${sysConfig.schoolName }">
    
    <link href="${hplusStatic }/favicon.ico" rel="shortcut icon">
	<link href="${hplusStatic }/css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
    <link href="${hplusStatic }/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
    <link href="${hplusStatic }/css/animate.min.css" rel="stylesheet">
    <link href="${hplusStatic }/css/style.min862f.css?v=4.1.0" rel="stylesheet">
    <link href="${hplusStatic }/css/plugins/awesome-bootstrap-checkbox/awesome-bootstrap-checkbox.css" rel="stylesheet">
    <link href="${hplusStatic }/css/plugins/summernote/summernote.css" rel="stylesheet">
    <link href="${hplusStatic }/css/plugins/summernote/summernote-bs3.css" rel="stylesheet">
	<%-- <%@ include file="/WEB-INF/views/include/commoncss.jsp" %> --%>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
	</script>
</head>
<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeInRight">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>共享积分查看</h5>
                    </div>
                    <div class="ibox-content">
						<form id="inputForm" method="post" class="form-horizontal m-t">
							<input type="hidden" name="id" value="${share.id }">
							<div class="control-group">
								<label class="control-label">共享人：</label>
								<div class="controls">
									<input id="user" name="user.id" value="${share.user.name}" htmlEscape="false" maxlength="11" class="input-xlarge required" style="width:800px;" readonly="readonly"/>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">所属机构：</label>
								<div class="controls">
									<input id="orgId" name="orgId" value="${share.orgId}" htmlEscape="false" maxlength="11" class="input-xlarge required" style="width:800px;" readonly="readonly"/>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">数据来源：</label>
								<div class="controls">
									<input id="dataType" name="dataType" value="${share.dataType}" htmlEscape="false" maxlength="11" class="input-xlarge required" style="width:800px;" readonly="readonly"/>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">资料链接：</label>
								<div class="controls">
									<input id="dataLink" name="dataLink" value="${share.dataLink}" htmlEscape="false" maxlength="250" class="input-xlarge required" style="width:800px;" readonly="readonly"/>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">奖励积分：</label>
								<div class="controls">
									<input id="score" name="score" value="${share.score}" htmlEscape="false" maxlength="11" class="input-xlarge " style="width:800px;" readonly="readonly"/>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">备注：</label>
								<div class="controls">
									<input id="remark" name="remark" value="${share.remark}" htmlEscape="false" maxlength="500" class="input-xlarge " style="width:800px;" readonly="readonly"/>
								</div>
							</div>
							
							<div class="form-group">
                             	<div class="col-sm-8 col-sm-offset-3">
                                 	<input type="button" class="btn btn-primary" onclick="history.go(-1)" value="返回"/>
                             	</div>
                            </div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<%-- <%@ include file="/WEB-INF/views/include/commonlistjs.jsp" %> --%>
	 <script type="text/javascript">
	    function windowOpen(url, name, width, height){
	    	var top=parseInt((window.screen.height-height)/2,10),left=parseInt((window.screen.width-width)/2,10),
	    		options="location=no,menubar=no,toolbar=no,dependent=yes,minimizable=no,modal=yes,alwaysRaised=yes,"+
	    		"resizable=yes,scrollbars=yes,"+"width="+width+",height="+height+",top="+top+",left="+left;
	    	window.open(url ,name , options);
	    }
    </script>
	<script src="${hplusStatic }/js/jquery.min.js?v=2.1.4"></script>
	<script src="${hplusStatic }/js/bootstrap.min.js?v=3.3.6"></script>
    <script src="${hplusStatic }/js/content.min.js?v=1.0.0"></script>
    <script src="${hplusStatic }/js/plugins/validate/jquery.validate.min.js"></script>
    <script src="${hplusStatic }/js/plugins/validate/messages_zh.min.js"></script>
    <script src="${hplusStatic }/js/demo/form-validate-demo.min.js"></script>
    <script src="${hplusStatic }/js/plugins/summernote/summernote.min.js"></script>
    <script src="${hplusStatic }/js/plugins/summernote/summernote-zh-CN.js"></script>
</body>
</html>