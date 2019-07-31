<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<!-- Mirrored from www.zi-han.net/theme/hplus/form_validate.html by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 20 Jan 2016 14:19:15 GMT -->
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>${sysConfig.schoolName } 机构--一吐为快管理</title>
    <meta name="keywords" content="${fns:getConfig('keywords')} ${sysConfig.schoolName } ">
    <meta name="description" content="${fns:getConfig('description')} ${sysConfig.schoolName }">
    
	<%@ include file="/WEB-INF/views/include/commoncss.jsp" %>
	<%@ include file="/WEB-INF/views/include/commonformjs.jsp" %>
	<script src="${ctxStatic}/ckeditor/ckeditor.js" ></script>
	
	
<!--
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/relax/quickSay/">一吐为快列表</a></li>
		<li class="active"><a href="${ctx}/relax/quickSay/form?id=${quickSay.id}">一吐为快<shiro:hasPermission name="relax:quickSay:edit">${not empty quickSay.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="relax:quickSay:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	-->
	
<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>一吐为快信息</h5>
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
        </div>
    </div>	
	
	<sys:message content="${message}"/>		
	<div class="ibox-content">		
                        <form:form class="form-horizontal m-t" id="inputForm" 
						modelAttribute="quickSay"
						action="${ctx}/relax/quickSay/save" method="post">
                            <input type="hidden" name="id" value="${quickSay.id }">
							
							<div class="form-group">
                                <label class="col-sm-3 control-label">文章标题：</label>
                                <div class="col-sm-8">								
									<form:input path="name" htmlEscape="false" maxlength="16" class="form-control"   type="text"/>
                                </div>
                            </div>
							
							<div class="form-group">
                                <label class="col-sm-3 control-label">时间：</label>
                                <div class="col-sm-4">
								<input name="createDate" type="text" readonly="readonly" maxlength="20" 
								class="form-control"
						value="<fmt:formatDate value="${quickSay.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>" />
                                </div>
                            </div>		
							<div class="form-group">
								<label class="col-sm-3 control-label">文章内容:</label>
								<div class="col-sm-8">
									<form:textarea path="content" htmlEscape="false" rows="4" maxlength="128" class="form-control"  type="text"/>
									<sys:ckeditor replace="content" uploadPath="/relax/quickSay" />
								</div>
							</div>
																		
						
				
				    <div class="form-group">
                                <div class="col-sm-8 col-sm-offset-3">
									<shiro:hasPermission name="relax:quickSay:edit">
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
	<form:form id="inputForm" modelAttribute="quickSay" action="${ctx}/relax/quickSay/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">名称：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="64" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">内容:</label>
			<div class="controls">
				<form:textarea id="content" htmlEscape="true" path="content" rows="4" maxlength="200" class="input-xxlarge"/>
				<sys:ckeditor replace="content" uploadPath="/relax/quickSay" />
			</div>
		</div>
	
		<div class="form-actions">
			<shiro:hasPermission name="relax:quickSay:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
	-->
</body>

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