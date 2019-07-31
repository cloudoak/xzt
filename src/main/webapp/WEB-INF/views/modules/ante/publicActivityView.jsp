<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>公益活动管理</title>
	<meta charset="utf-8">
	<meta name="decorator" content="default"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="keywords" content="${fns:getConfig('keywords')} ${sysConfig.schoolName } ">
    <meta name="description" content="${fns:getConfig('description')} ${sysConfig.schoolName }">
    <%@ include file="/WEB-INF/views/include/commoncss.jsp" %>
</head>
<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeInRight">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>公益活动查看</h5>
                    </div>
                    <div class="ibox-content">
						<form:form id="inputForm" modelAttribute="publicActivity" method="post" class="form-horizontal m-t">
							<sys:message content="${message}"/>		
							<div class="form-group">
								<label class="col-sm-3 control-label">标题：</label>
								<div class="col-sm-8">
									<input id="title" name="title" value="${publicActivity.title}" htmlEscape="false" class="input-xlarge" readonly="readonly"/>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">活动内容：</label>
								<div class="col-sm-8">
									<form:textarea path="content" htmlEscape="false" rows="4" maxlength="200" class="input-xlarge" disabled="true"/>
									<sys:ckeditor replace="content" uploadPath="/ante/publicActivity" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">开始时间：</label>
								<div class="col-sm-8">
									<input id="startTime" name="startTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
										value="<fmt:formatDate value="${publicActivity.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">结束时间：</label>
								<div class="col-sm-8">
									<input id="endTime" name="endTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
										value="<fmt:formatDate value="${publicActivity.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">审核意见：</label>
								<div class="controls">
									<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge " disabled="true"/>
								</div>
							</div>
                           	<div class="form-group">
                             	<div class="col-sm-8 col-sm-offset-3">
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
    <script src="${hplusStatic }/js/plugins/validate/messages_zh.min.js"></script>
</body>
</html>