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
                        <h5><a href="${ctx}/sys/share/form?id=${share.id}">共享积分审核</a></h5>
                    </div>
                    <div class="ibox-content">
						<form id="inputForm" modelAttribute="share" action="${ctx}/sys/share/save" method="post" class="form-horizontal">
							<sys:message content="${message}"/>		
							<input type="hidden" name="id" value="${share.id }">
							<div class="control-group">
								<label class="control-label">共享人：<span style="color:red;font-size:18px">*</span></label>
								<div class="controls">
									<sys:treeselect id="user" name="user.id" value="${share.user.id}" labelName="user.name" labelValue="${share.user.name}"
										title="用户" url="/sys/office/treeData?type=3" cssClass="required" allowClear="true" notAllowSelectParent="true" />
										<%-- <label>${share.user.name}</label> --%>
								</div>
							</div>
							<div class="control-group">
	                            <label class="control-label">数据来源：<span style="color:red;font-size:18px">*</span></label>
	                            <div class="controls">
	                                <select id="dataType" name="dataType" required="required">
	                                    <option value="">--请选择--</option>
										<option value="0">系统</option>
										<option value="1">量表</option>
										<option value="2">课件</option>
										<option value="3">心灵咖啡屋</option>
	                                </select>
	                                <%-- <label>${share.dataType}</label> --%>
	                            </div>
	                        </div>
							<div class="control-group">
								<label class="control-label">资料链接：<span style="color:red;font-size:18px">*</span></label>
								<div class="controls">
									<input id="dataLink" name="dataLink" htmlEscape="false" maxlength="250" class="input-xlarge required" required="required"/>
									<%-- <label>${share.dataLink}</label> --%>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">奖励积分：</label>
								<div class="controls">
									<input id="score" name="score" htmlEscape="false" maxlength="11" class="input-xlarge "/>
									<%-- <label>${share.score}</label> --%>
								</div>
							</div>
							<shiro:hasPermission name="sys:share:edit">
							<div class="control-group">
								<label class="control-label">审核：</label>
								<div class="controls" algin="center">
									<label>通过</label>
									<input id="status1" name="status" required="true" type="radio" value="1"/>
									<label >不通过</label>
									<input id="status2" name="status" required="true" type="radio" value="2"/>
								</div>
							</div>
							</shiro:hasPermission>
							<div class="control-group">
								<label class="control-label">备注：</label>
								<div class="controls">
									<textarea id="remark" name="remark" rows="4" cols="20" class="input-xlarge "></textarea>
								</div>
							</div>
							<%-- <div class="control-group">
								<label class="control-label">机构：</label>
								<div class="controls">
									<form:input path="orgId" htmlEscape="false" maxlength="11" class="input-xlarge required"/>
									<span class="help-inline"><font color="red">*</font> </span>
								</div>
							</div> --%>
							<div class="form-actions">
								<shiro:hasPermission name="sys:share:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
								<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<%@ include file="/WEB-INF/views/include/commonlistjs.jsp" %>
</body>
</html>