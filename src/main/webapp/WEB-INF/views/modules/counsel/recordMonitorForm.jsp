<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<!-- Mirrored from www.zi-han.net/theme/hplus/form_validate.html by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 20 Jan 2016 14:19:15 GMT -->
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>${sysConfig.schoolName } 机构--咨询督导列表管理</title>
    <meta name="keywords" content="${fns:getConfig('keywords')} ${sysConfig.schoolName } ">
    <meta name="description" content="${fns:getConfig('description')} ${sysConfig.schoolName }">
    
	<%@ include file="/WEB-INF/views/include/commoncss.jsp" %>
	
</head>

<!--
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/counsel/recordMonitor/">咨询督导列表</a></li>
		<li class="active"><a href="${ctx}/counsel/recordMonitor/form?id=${recordMonitor.id}">咨询督导<shiro:hasPermission name="counsel:recordMonitor:edit">${not empty recordMonitor.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="counsel:recordMonitor:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	-->
	
	
<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>咨询督导</h5>
                        <div class="ibox-tools">
                            <!--
							<a class="text-navy" href="${ctx}/sys/org/form?id=${office.id}&parent.id=${office.parent.id}">咨询室查看</a>
							<a class="collapse-link">
                                <i class="fa fa-chevron-up"></i>
                            </a>
                            <a class="dropdown-toggle" data-toggle="dropdown" href="form_basic.html#">
                                <i class="fa fa-wrench"></i>
                            </a>
                            <a class="close-link">
                                <i class="fa fa-times"></i>
                            </a>
							<shiro:hasPermission name="sys:office:edit">${not empty office.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="sys:office:edit">查看</shiro:lacksPermission>
							-->
                        </div>
                    </div>
					
					<div class="ibox-content">
                        <form:form class="form-horizontal m-t" id="inputForm" 
						modelAttribute="recordMonitor"
						action="${ctx}/counsel/recordMonitor/save" method="post">
                            <input type="hidden" name="id" value="${recordMonitor.id }">
							
							<div class="form-group">
								<label class="col-sm-3 control-label">督导咨询师名称：</label>
								<div class="col-sm-4">
									<form:input path="counselorName" htmlEscape="false" maxlength="32" class="input-xlarge "/>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">督导状态：</label>
								<div class="col-sm-2">							
									<form:radiobuttons path="monitorStatus" items="${fns:getDictList('monitor_status')}" itemLabel="label" itemValue="value" htmlEscape="false" class=""/>
								</div>
							</div>
							
						
							<div class="form-group">
                                <label class="col-sm-3 control-label">督导内容：</label>
                                <div class="col-sm-8">								
									<form:textarea path="content" htmlEscape="false" maxlength="32" class="form-control"  rows="4"  type="text"/>
                                </div>
                            </div>
							
							<div class="form-group">
                                <div class="col-sm-8 col-sm-offset-3">
									<shiro:hasPermission name="counsel:counselReport:edit">
									<input id="btnSubmit" class="btn btn-primary" type="submit" value="保存"/>&nbsp;</shiro:hasPermission>
									<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
                                </div>
                            </div>
                        </form:form>
                    </div>
                </div>
            </div>
        </div>
    </div>
	
	<!--
	
	<form:form id="inputForm" modelAttribute="recordMonitor" action="${ctx}/counsel/recordMonitor/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">咨询记录标识：</label>
			<div class="controls">
				<form:input path="recordId" htmlEscape="false" maxlength="11" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">督导咨询师标识：</label>
			<div class="controls">
				<form:input path="counselorId" htmlEscape="false" maxlength="11" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">督导咨询师名称：</label>
			<div class="controls">
				<form:input path="counselorName" htmlEscape="false" maxlength="32" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">督导状态：</label>
			<div class="controls">
				<form:select path="monitorStatus" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('monitor_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">督导内容：</label>
			<div class="controls">
				<form:textarea path="content" htmlEscape="false" rows="4" maxlength="512" class="input-xxlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">分校id：</label>
			<div class="controls">
				<form:input path="orgId" htmlEscape="false" maxlength="11" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="counsel:recordMonitor:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
	-->	
	
</body>

<%@ include file="/WEB-INF/views/include/commonformjs.jsp" %>

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

</html>