<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${sysConfig.schoolName } 即时信息管理--消息查看</title>
    <meta name="keywords" content="${fns:getConfig('keywords')} ${sysConfig.schoolName } ">
    <meta name="description" content="${fns:getConfig('description')} ${sysConfig.schoolName }">
    <%@ include file="/WEB-INF/views/include/commoncss.jsp" %>
</head>
<body class="gray-bg">
	<%-- <ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/ante/messageInfo/read?id=${messageInfo.id}">消息详情</a></li>
	</ul><br/> --%>
	<div class="wrapper wrapper-content animated fadeInRight">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>消息查看</h5>
                    </div>
                    <div class="ibox-content">
                		<form:form id="inputForm" modelAttribute="messageInfo" action="${ctx}/ante/messageInfo/read?id=${messageInfo.id}" method="post" class="form-horizontal">
							<sys:message content="${message}"/>		
							<div class="form-group">
								<label class="col-sm-3 control-label">收件人(用户名)：</label>
								<div class="col-sm-8">
									<input id="toUser" name="toUser" value="${messageInfo.toUser}" htmlEscape="false" class="input-xlarge" readonly="readonly"/>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">主题：</label>
								<div class="col-sm-8">
									<input id="title" name="title" value="${messageInfo.title}" htmlEscape="false" class="input-xlarge" readonly="readonly"/>
								</div>
							</div>
							<div class="form-group">
                                <label class="col-sm-3 control-label">内容：</label>
                                <div class="col-sm-8">
									<%-- <form:textarea path="content" htmlEscape="false" rows="4" maxlength="200" class="input-xlarge" disabled="disabled"/>
									<sys:ckeditor replace="content" uploadPath="/ante/messageInfo" /> --%>
									<label id="content" name="content">
									${messageInfo.content}
									</label>
								</div>
                            </div>
                           	<div class="form-group">
                             	<div class="col-sm-8 col-sm-offset-3">
                                 	<input type="button" class="btn btn-primary" onclick="location.href='${ctx}/ante/messageInfo/list'" value="返回"/>
                                 	<!-- <input type="button" class="btn btn-primary" onclick="history.go(-1)" value="返回"/> -->
                             	</div>
                            </div>
						</form:form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>

</html>