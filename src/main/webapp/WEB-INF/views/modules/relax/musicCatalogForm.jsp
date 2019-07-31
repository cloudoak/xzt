<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<!-- Mirrored from www.zi-han.net/theme/hplus/form_validate.html by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 20 Jan 2016 14:19:15 GMT -->
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>${sysConfig.schoolName } 机构--音乐分类列表管理</title>
    <meta name="keywords" content="${fns:getConfig('keywords')} ${sysConfig.schoolName } ">
    <meta name="description" content="${fns:getConfig('description')} ${sysConfig.schoolName }">
	<%@ include file="/WEB-INF/views/include/commoncss.jsp" %>
	<%@ include file="/WEB-INF/views/include/commonformjs.jsp" %>
</head>
<!--
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/relax/musicCatalog/">音乐分类列表</a></li>
		<li class="active"><a href="${ctx}/relax/musicCatalog/form?id=${musicCatalog.id}&parent.id=${musicCatalogparent.id}">音乐分类<shiro:hasPermission name="relax:musicCatalog:edit">${not empty musicCatalog.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="relax:musicCatalog:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	
	<form:form id="inputForm" modelAttribute="musicCatalog" action="${ctx}/relax/musicCatalog/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">上级父级编号:</label>
			<div class="controls">
				<sys:treeselect id="parent" name="parent.id" value="${musicCatalog.parent.id}" labelName="parent.name" labelValue="${musicCatalog.parent.name}"
					title="父级编号" url="/relax/musicCatalog/treeData" extId="${musicCatalog.id}" cssClass="" allowClear="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">名称：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="32" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">排序：</label>
			<div class="controls">
				<form:input path="sort" htmlEscape="false" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="32" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="relax:musicCatalog:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
	-->
	
<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>课件分类</h5>
                        <div class="ibox-tools">
                            <!--
							<a class="text-navy" href="${ctx}/relax/musicCatalog/form">咨询室查看</a>
							<a class="collapse-link">
                                <i class="fa fa-chevron-up"></i>
                            </a>
                            <a class="dropdown-toggle" data-toggle="dropdown" href="form_basic.html#">
                                <i class="fa fa-wrench"></i>
                            </a>
                            <a class="close-link">
                                <i class="fa fa-times"></i>
                            </a>
							-->
                        </div>
                    </div>
						
			<div class="ibox-content">
                        <form:form class="form-horizontal m-t" id="inputForm" 
						modelAttribute="musicCatalog"
						action="${ctx}/relax/musicCatalog/save" method="post">
                            <input type="hidden" name="id" value="${musicCatalog.id }">
						<!-- 	
						<div class="form-group">
							<label class="col-sm-3 control-label">父级编号:</label>
							<div class="col-sm-4">
								<sys:treeselect id="parent" name="parent.id" value="${musicCatalog.parent.id}" labelName="parent.name" labelValue="${musicCatalog.parent.name}"
									title="父级编号" url="/relax/musicCatalog/treeData" extId="${musicCatalog.id}" cssClass="col-sm-4" allowClear="true"/>
							</div>
						</div>	
						 -->
						<div class="form-group">
                                <label class="col-sm-3 control-label">名称：</label>
                                <div class="col-sm-4	">								
									<form:input path="name" htmlEscape="false" maxlength="16" class="form-control"   type="text"/>
                                </div>
                        </div>
						
						<div class="form-group">
							<label class="col-sm-3  control-label">排序：</label>
							<div class="col-sm-8">
								<form:input path="sort" htmlEscape="false" maxlength="2" class="form-control" />
							</div>
						</div>
						
						<div class="form-group">
                                <label class="col-sm-3 control-label">备注：</label>
                                <div class="col-sm-8">
									<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="128" class="form-control" type="text"/>
                                </div>
                        </div>	
				
	
						<div class="form-group">
                                <div class="col-sm-8 col-sm-offset-3">
									<shiro:hasPermission name="relax:musicCatalog:edit">
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

<script type="text/javascript">
		$(document).ready(function() {
			$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox") || element.is(":radio") || element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
	</script>


</html>