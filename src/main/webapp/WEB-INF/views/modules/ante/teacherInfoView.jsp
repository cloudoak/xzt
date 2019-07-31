<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>教师基本信息查看</title>
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
                        <h5>教师信息查看</h5>
                    </div>
                    <div class="ibox-content">
						<form:form id="inputForm" modelAttribute="counselor" action="${ctx}/ante/teacherInfo/save" method="post" class="form-horizontal">
							<form:hidden path="id"/>
							<sys:message content="${message}"/>	
							<div class="form-group">
								<label class="col-sm-3 control-label">用户名：</label>
								<div class="col-sm-8">
									<input id="title" name="title" value="${counselor.user.loginName}" htmlEscape="false" class="input-xlarge" readonly="readonly"/>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">姓名：</label>
								<div class="col-sm-8">
									<input id="name" name="user.name" value="${counselor.user.name}" htmlEscape="false" class="input-xlarge" readonly="readonly"/>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">性别：</label>
								<div class="col-sm-8">
									<fieldset>
										<div class="radio radio-info radio-inline">
											<input type="radio" id="sex1" value="1" name="sex" ${counselor.sex==1?"checked":"" } disabled="disabled">
											<label for="gender1">男</label>
										</div>
										<div class="radio radio-inline">
											<input type="radio" id="sex2" value="0" name="sex" ${counselor.sex==0?"checked":"" } disabled="disabled">
											<label for="gender2">女</label>
										</div>
									</fieldset>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">邮箱：</label>
								<div class="col-sm-8">
									<input id="email" name="user.email" value="${counselor.user.email }" class="input-xlarge" readonly="readonly">
								</div>
							</div>
							<div class="form-group">
	                            <label class="col-sm-3 control-label">类型：</label>
	                            <div class="col-sm-8">
	                                <select name="counselorType" id="counselorType" disabled="disabled">
	                                    <option value="" label=""/>
	                                	<c:forEach items="${counselorTypes.list}" var="counselorType">
											${counselor.counselorType==counselorType.id?counselorType.typeName:''}
										</c:forEach>
	                                </select>
	                            </div>
	                        </div>
	                        <div class="form-group">
	                            <label class="col-sm-3 control-label">部门：</label>
	                            <div class="col-sm-8">
	                                <select name="department" id="department" disabled="disabled">
	                                    <option value="" label=""/>
	                                	<c:forEach items="${departments.list}" var="department">
	                                		${counselor.deptId==department.id?department.deptName:''}
										</c:forEach>
	                                </select>
	                            </div>
	                        </div>
							<div class="form-group">
								<label class="col-sm-3 control-label">职务：</label>
								<div class="col-sm-8">
									<input id="job" name="job" value="${counselor.job}" htmlEscape="false" class="input-xlarge"  readonly="readonly">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">职称：</label>
								<div class="col-sm-8">
									<input id="jobName" name="jobName" value="${counselor.jobName}" htmlEscape="false" class="input-xlarge" readonly="readonly">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">出生年月：</label>
								<div class="col-sm-2">
									<input name="birthday" type="text" disabled="disabled"
										maxlength="20" class="form-control"
										value="<fmt:formatDate value="${counselor.birthday}" pattern="yyyy-MM-dd"/>"
										onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">上传图片：</label>
								<div class="col-sm-8">
									<input id="photo" name="user.photo" value="${counselor.user.photo}" htmlEscape="false" class="input-xlarge" readonly="readonly"/>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">简介：</label>
								<div class="col-sm-8">
									<form:textarea path="instro" value="${counselor.instro}" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge " disabled="true"/>
								</div>
							</div>
							
							<div class="form-group">
								<div class="col-sm-8 col-sm-offset-3">
									<input type="button" class="btn btn-primary" onclick="history.go(-1)" value="返回" />
								</div>
							</div>
						</form:form>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<%@ include file="/WEB-INF/views/include/commonlistjs.jsp" %>
</body>
</html>