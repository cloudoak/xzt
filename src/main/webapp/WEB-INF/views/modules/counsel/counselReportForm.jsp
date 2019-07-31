<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<!-- Mirrored from www.zi-han.net/theme/hplus/form_validate.html by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 20 Jan 2016 14:19:15 GMT -->
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>${sysConfig.schoolName } 机构--咨询报表列表管理</title>
    <meta name="keywords" content="${fns:getConfig('keywords')} ${sysConfig.schoolName } ">
    <meta name="description" content="${fns:getConfig('description')} ${sysConfig.schoolName }">
    
	<%@ include file="/WEB-INF/views/include/commoncss.jsp" %>
	
</head>

<!--
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/counsel/counselReport/">咨询报表列表</a></li>
		<li class="active"><a href="${ctx}/counsel/counselReport/form?id=${counselReport.id}">咨询报表<shiro:hasPermission name="counsel:counselReport:edit">${not empty counselReport.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="counsel:counselReport:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	-->
	
	
<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>咨询预约记录</h5>
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
						modelAttribute="counselReport"
						action="${ctx}/counsel/counselReport/save" method="post">
                            <input type="hidden" name="id" value="${counselReport.id }">
							
							<div class="form-group">
                                <label class="col-sm-3 control-label">标题：</label>
                                <div class="col-sm-8">								
									<form:input path="title" htmlEscape="false" maxlength="16" class="form-control"   type="text"/>
                                </div>
                            </div>
							
							<div class="form-group">
                                <label class="col-sm-3 control-label">时间：</label>
                                <div class="col-sm-2">
								<input name="beginTime" type="text" readonly="readonly" maxlength="20" 
								class="form-control"
						value="<fmt:formatDate value="${counselReport.beginTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>&nbsp;&nbsp;
						<input name="endTime" type="text" readonly="readonly" maxlength="20" 
								class="form-control"
						value="<fmt:formatDate value="${counselReport.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
                                </div>
                            </div>		
													
							<div class="form-group">
                                <label class="col-sm-3 control-label">编号：</label>
                                <div class="col-sm-8">								
									<form:input path="sn" htmlEscape="false" maxlength="16" class="form-control"   type="text"/>
                                </div>
                            </div>			
							
							<div class="form-group">
                                <label class="col-sm-3 control-label">制作人：</label>
                                <div class="col-sm-8">								
									<form:input path="createName" htmlEscape="false" maxlength="16" class="form-control"   type="text"/>
                                </div>
                            </div>                        
						
							<div class="form-group">
                                <label class="col-sm-3 control-label">结论：</label>
                                <div class="col-sm-8">								
									<form:textarea path="conclusion" htmlEscape="false" maxlength="32" class="form-control"   type="text"/>
                                </div>
                            </div>
							
							<div class="form-group">
                                <label class="col-sm-3 control-label">备注：</label>
                                <div class="col-sm-8">
									<form:textarea path="remark" htmlEscape="false" rows="4" maxlength="128" class="form-control" type="text"/>
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