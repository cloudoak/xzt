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
	<script type="text/javascript">
		$(document).ready(function() {
			$("#btnSubmitBack").click(function(form){
				$("#inputForm").attr("action","${ctx}/ante/publicActivity/backCheck");
			}); 
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
<body>
	<div class="wrapper wrapper-content animated fadeInRight">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5><a href="${ctx}/ante/publicActivity/form?id=${publicActivity.id}">公益活动审核</a></h5>
                    </div>
                    <div class="ibox-content">
						<form:form id="inputForm" modelAttribute="publicActivity" action="${ctx}/ante/publicActivity/pass" method="post" class="form-horizontal">
							<form:hidden path="id"/>
							<sys:message content="${message}"/>		
							<div class="control-group">
								<label class="control-label">标题：</label>
								<div class="controls">
									<input id="title" name="title" value="${publicActivity.title}" htmlEscape="false" class="input-xlarge" readonly="readonly"/>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">活动内容：</label>
								<div class="controls">
									<form:textarea id="content" disabled="true" htmlEscape="false" path="content" rows="4" maxlength="200" class="input-xlarge"/>
									<sys:ckeditor replace="content" uploadPath="/ante/publicActivity" />
								</div>
							</div>
							<form:hidden path="accessCount"/>
							<form:hidden path="parterNum"/>
							<div class="control-group">
								<label class="control-label">开始时间：</label>
								<div class="controls">
									<input name="startTime" type="text" disabled="true" maxlength="20" class="input-medium Wdate "
										value="<fmt:formatDate value="${publicActivity.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
										onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">结束时间：</label>
								<div class="controls">
									<input name="endTime" type="text" disabled="true" maxlength="20" class="input-medium Wdate "
										value="<fmt:formatDate value="${publicActivity.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
										onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">审核意见：</label>
								<div class="controls">
									<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xlarge "/>
								</div>
							</div>
							<div class="form-actions">
								<shiro:hasPermission name="ante:publicActivity:edit">
									<c:choose>
					    				<c:when test="${publicActivity.remarks=='' || publicActivity.checkStatus!=1}">
											<input id="btnSubmit" class="btn btn-primary" type="submit" value="通 过"/>&nbsp;
											<input id="btnSubmitBack" class="btn btn-primary" type="submit" value="驳 回"/>&nbsp;
					    				</c:when>
					    				<c:otherwise>
					    					<input id="btnSubmit2" class="btn btn-primary" type="submit" disabled="true" value="通 过"/>&nbsp;
											<input id="btnSubmitBack2" class="btn btn-primary" type="submit" disabled="true" value="驳 回"/>&nbsp;
					    				</c:otherwise>
				    				</c:choose>
								</shiro:hasPermission>
								<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
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