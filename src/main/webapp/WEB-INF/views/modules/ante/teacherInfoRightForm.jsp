<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>教师信息管理</title>
	<meta charset="utf-8">
	<meta name="decorator" content="default"/>
	<meta name="keywords" content="${fns:getConfig('keywords')} ${sysConfig.schoolName } ">
    <meta name="description" content="${fns:getConfig('description')} ${sysConfig.schoolName }">
	
	<%@ include file="/WEB-INF/views/include/commoncss.jsp" %>
	<script type="text/javascript">
		 $(document).ready(function() {
			//$("#name").focus();
			$("#confirmPassword").focus();
			$("#inputForm").validate({
				rules: {
					
				},
				messages: {
					confirmPassword: {equalTo: "输入密码不正确！"}
				},
				submitHandler: function(form){
					if($("#officeIdInput").val()==""){
						alert("请选择机构");
						return false;
					}
					
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
	</script> 
</head>
<body class="gray-bg">
	<%-- <ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/ante/teacherInfo/form?id=${counselor.id}">分配组织管理<shiro:hasPermission name="ante:teacherInfo:edit">${not empty counselor.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="ante:teacherInfo:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/> --%>
	<div class="wrapper wrapper-content animated fadeInRight">
		<div class="row">
			<div class="col-sm-12">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<h5>
							<a class="text-navy" href="#">咨询师信息<shiro:hasPermission
									name="ante:teacherInfo:edit">${not empty counselor.id?'修改':'新增'}</shiro:hasPermission>
								<shiro:lacksPermission name="ante:teacherInfo:edit">查看</shiro:lacksPermission></a>
						</h5>
					</div>
					<div class="ibox-content">
						<form:form id="inputForm" modelAttribute="counselor" action="${ctx}/ante/teacherInfo/save" method="post" enctype="multipart/form-data" class="form-horizontal">
							<input type="hidden" name="id" value="${counselor.id }">
							<sys:message content="${message}"/>		
							<input id="officeIdInput" name="orgId" value="${counselor.orgId}" type="hidden"/>
							
							<div class="form-group">
								<label class="col-sm-3 control-label">用户名：<span style="color:red;font-size:18px">*</span></label>
								<div class="col-sm-8">
									<input id="user.loginName" name="user.loginName" value="${counselor.user.loginName}" htmlEscape="false" maxlength="64" class="input-xlarge " required="true"/>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">密码:<span style="color:red;font-size:18px">*</span></label>
								<div class="col-sm-8">
									<input id="password" name="user.password" type="password" value="" maxlength="50" minlength="6" class="input-xlarge" required="false"/>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">确认密码:<span style="color:red;font-size:18px">*</span></label>
								<div class="col-sm-8">
									<input id="confirmPassword" name="confirmPassword" type="password" value="" maxlength="50" minlength="6" class="input-xlarge" equalTo="#password" required="false"/>
								</div>
							</div>
							
							<div class="form-group">
								<label class="col-sm-3 control-label">教师姓名：<span style="color:red;font-size:18px">*</span></label>
								<div class="col-sm-8">
									<input id="name" name="user.name" value="${counselor.user.name}" htmlEscape="false" maxlength="50" class="input-xlarge " required="true"/>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">性别：<span style="color:red;font-size:18px">*</span></label>
								<div class="col-sm-8">
									<fieldset>
										<div class="radio radio-info radio-inline">
											<input type="radio" id="sex1" value="1" name="sex" ${counselor.sex==1?"checked":"" } required="required">
											<label for="sex1">男</label>
										</div>
										<div class="radio radio-inline">
											<input type="radio" id="sex2" value="0" name="sex" ${counselor.sex==0?"checked":"" } required="required">
											<label for="sex2">女</label>
										</div>
									</fieldset>
								</div>
							</div>
					 		<div class="form-group">
								<label class="col-sm-3 control-label">邮箱：</label>
								<div class="col-sm-8">
									<input id="user.email" name="user.email" value="${counselor.user.email}" htmlEscape="false" maxlength="64" class="input-xlarge "/>
								</div>
							</div> 
						 	<div class="form-group">
								<label class="col-sm-3 control-label">类型：</label>
								<div class="col-sm-8">
									<select name="counselorType" id="counselorType" value="${counselor.counselorType}" class="required input-mini" style="width:180px;">
										<c:forEach items="${counselorTypes.list}" var="counselorType">
											<option value="${counselorType.id}" ${counselorType.id==column.id?'selected':''} title="${counselorType.typeName}">${counselorType.typeName}</option>
										</c:forEach>
									</select>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">部门：</label>
								<div class="col-sm-8">
									<select name="deptId" id="deptId" class="required input-mini" style="width:180px;*width:75px">
										<c:forEach items="${departments.list}" var="department">
											<option value="${department.id}" ${department.id==column.id?'selected':''} title="${department.id}">${department.deptName}</option>
										</c:forEach>
									</select>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">职务：</label>
								<div class="col-sm-8">
									<input id="job" name="job" value="${counselor.job}" htmlEscape="false" maxlength="32" class="input-xlarge "/>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">职称：</label>
								<div class="col-sm-8">
									<input id="jobName" name="jobName" value="${counselor.jobName}" htmlEscape="false" maxlength="32" class="input-xlarge "/>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">出生年月：</label>
								<div class="col-sm-8">
									<input id="birthday" name="birthday" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
										value="<fmt:formatDate value="${counselor.birthday}" pattern="yyyy-MM-dd"/>"
										onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false})" onblur="getAge()"/>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">年龄：</label>
								<div class="col-sm-8">
									<input id="age" name="age" value="${counselor.age}" readonly="readonly" htmlEscape="false" maxlength="2" class="input-xlarge " style="width:165px"/>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">上传图片路径：</label>
								<div class="col-sm-8">
									<input id="file" name="image" type="file" class="input-xlarge"/>
								</div>
							</div>
							
							<div class="form-group">
								<label class="col-sm-3 control-label">简介：</label>
								<div class="col-sm-8">
									<input id="instro" name="instro" value="${counselor.instro}" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
								</div>
							</div>
							
							<div class="form-actions">
								<shiro:hasPermission name="ante:teacherInfo:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
								<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
							</div>
						</form:form>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<%@ include file="/WEB-INF/views/include/commonlistjs.jsp" %>
	<script type="text/javascript">
	    //根据出生日期算出年龄
	    function getAge(){         
	        var returnAge;
	        var strBirthday = document.getElementById("birthday").value;
	        var strBirthdayArr=strBirthday.split("-"); 
	        var birthYear = strBirthdayArr[0];  
	        var birthMonth = strBirthdayArr[1];  
	        var birthDay = strBirthdayArr[2];  
	          
	        d = new Date();  
	        var nowYear = d.getFullYear();  
	        var nowMonth = d.getMonth() + 1;  
	        var nowDay = d.getDate();  
	          
	        if(nowYear == birthYear){  
	            returnAge = 0;//同年 则为0岁  
	        } else{  
	            var ageDiff = nowYear - birthYear ; //年之差  
	            if(ageDiff > 0){  
	                if(nowMonth == birthMonth) {  
	                    var dayDiff = nowDay - birthDay;//日之差  
	                    if(dayDiff < 0)  
	                    {  
	                        returnAge = ageDiff - 1;  
	                    }  
	                    else  
	                    {  
	                        returnAge = ageDiff ;  
	                    }  
	                }  
	                else  
	                {  
	                    var monthDiff = nowMonth - birthMonth;//月之差  
	                    if(monthDiff < 0)  
	                    {  
	                        returnAge = ageDiff - 1;  
	                    }  
	                    else  
	                    {  
	                        returnAge = ageDiff ;  
	                    }  
	                }  
	            }  
	            else  
	            {  
	                returnAge = -1;//返回-1 表示出生日期输入错误 晚于今天  
	            }  
	        }  
	        document.getElementById("age").value = returnAge;
	    }
	</script>
</body>
</html>