<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<meta charset="utf-8">
	<meta name="decorator" content="default"/>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>消息管理</title>
	
	<%@ include file="/WEB-INF/views/include/commoncss.jsp" %>
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
                        <h5><a href="${ctx}/ante/messageInfo/form?id=${messageInfo.id}">消息管理<shiro:hasPermission name="ante:messageInfo:edit">${not empty messageInfo.id?'修改':'新增'}</shiro:hasPermission><shiro:lacksPermission name="ante:messageInfo:edit">查看</shiro:lacksPermission></a></h5>
                    </div>
                    <div class="ibox-content">
						<form:form id="inputForm" modelAttribute="messageInfo" action="${ctx}/ante/messageInfo/save?send=true" method="post" class="form-horizontal">
							<form:hidden path="id"/>
							<sys:message content="${message}"/>	
							<%-- <div class="control-group">
								<label class="control-label">发送者：</label>
								<div class="controls">
									<form:input path="fromUser" htmlEscape="false" maxlength="30" class="input-xlarge "/>
								</div>
							</div> --%>
							
							<div class="control-group">
								<label class="control-label">收件人(用户名)：</label>
								<div class="controls">
									<input id="toUser" name="toUser" htmlEscape="false" maxlength="30" class="input-xlarge " required="true"/>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">主题：</label>
								<div class="controls">
									<input id="title" name="title" htmlEscape="false" maxlength="50" class="input-xlarge " required="true"/>
								</div>
							</div>
							<div class="form-group">
                                <label class="col-sm-3 control-label">内容：</label>
                                <div class="col-sm-8">
									<form:textarea path="content" htmlEscape="false" rows="4" maxlength="200" class="input-xlarge" required="required"/>
									<sys:ckeditor replace="content" uploadPath="/ante/messageInfo" />
								</div>
                            </div>
						<%-- 	<div class="control-group">
								<label class="control-label">备注字段：</label>
								<div class="controls">
									<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
								</div>
							</div> --%>
							<div class="form-group">
								<div class="col-sm-8 col-sm-offset-3">
									<shiro:hasPermission name="ante:messageInfo:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="发 送"/>&nbsp;</shiro:hasPermission>
									<input type="button" class="btn btn-primary" onclick="history.go(-1)" value="返回"/>
								</div>
							</div>
						</form:form>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<%@ include file="/WEB-INF/views/include/commonlistjs.jsp" %>
	<script src="${hplusStatic }/js/plugins/validate/jquery.validate.min.js"></script>
    <script src="${hplusStatic }/js/plugins/validate/messages_zh.min.js"></script>
</body>
</html>