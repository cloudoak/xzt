<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>咨询问题类型管理</title>
    <meta name="keywords" content="${fns:getConfig('keywords')} ${sysConfig.schoolName } ">
    <meta name="description" content="${fns:getConfig('description')} ${sysConfig.schoolName }">
    <link href="${hplusStatic }/favicon.ico" rel="shortcut icon">
	<link href="${hplusStatic }/css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
    <link href="${hplusStatic }/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
    <link href="${hplusStatic }/css/animate.min.css" rel="stylesheet">
    <link href="${hplusStatic }/css/style.min862f.css?v=4.1.0" rel="stylesheet">
</head>
<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>咨询问题类型</h5>
                        <div class="ibox-tools"></div>
                    </div>
					<div class="ibox-content">
                        <form class="form-horizontal m-t" id="inputForm" action="${ctx}/counsel/counselQuestionType/save" method="post">
                            <input type="hidden" name="id" value="${counselQuestionType.id }">
                            <div class="form-group">
                                <label class="col-sm-3 control-label">问题名称：</label>
                                <div class="col-sm-8">
									<input id="name" name="name" value="${counselQuestionType.name }" class="form-control" type="text" required="required">
                                    <span class="help-block m-b-none"><i class="fa fa-info-circle"></i> 问题名称（必填）</span>
                                </div>
                            </div>
							<div class="form-group">
                                <label class="col-sm-3 control-label">说明：</label>
                                <div class="col-sm-8">
									<textarea name="remark" rows="4" maxlength="128" class="form-control" >
									${counselQuestionType.remark }
									</textarea>
                                </div>
                            </div>
							<div class="form-group">
                                <div class="col-sm-8 col-sm-offset-3">
									<shiro:hasPermission name="counsel:counselQuestionType:edit">
									<input id="btnSubmit" class="btn btn-primary" type="submit" value="保存"/>&nbsp;</shiro:hasPermission>
									<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
	<script src="${hplusStatic }/js/jquery.min.js?v=2.1.4"></script>
    <script src="${hplusStatic }/js/bootstrap.min.js?v=3.3.6"></script>
    <script src="${hplusStatic }/js/content.min.js?v=1.0.0"></script>
    <script src="${hplusStatic }/js/plugins/validate/jquery.validate.min.js"></script>
    <script src="${hplusStatic }/js/plugins/validate/messages_zh.min.js"></script>
	<script type="text/javascript">
		$(function() {
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
</html>