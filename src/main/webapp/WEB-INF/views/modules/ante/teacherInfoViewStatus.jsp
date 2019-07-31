<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>教师基本信息审核页面</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
			$("#audited").click(function(){
				 if(confirm("确定审核通过吗？"))
				 {
					 $("#stautsId").append("<input name='teaStatus' type='hidden' value='1'/>");
					 $("#inputForm").submit();
				 }
				
			});
			
			$("#reject").click(function(){
				 if(confirm("确定驳回吗？"))
				 {
					 $("#stautsId").append("<input name='teaStatus' type='hidden' value='2'/>");
					 $("#inputForm").submit();
				 }
				
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
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/ante/teacherInfo/reviewStatus?id=${teacherInfo.id}">教师信息审核</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="teacherInfo" action="${ctx}/ante/teacherInfo/modiyStatus" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">用户名：</label>
			<div class="controls">
				${teacherInfo.teaAcct}
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">姓名：</label>
			<div class="controls">
				${teacherInfo.teaName}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">性别 ：</label>
			<div class="controls">
			${teacherInfo.teaSex=='1'?'男':'女'}			
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">邮箱：</label>
			<div class="controls">
			${teacherInfo.teaMail}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">类型：</label>
			<div class="controls">
			${teacherInfo.teaTypeid}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">部门：</label>
			<div class="controls">
			${teacherInfo.teaDepno}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">职务：</label>
			<div class="controls">
			${teacherInfo.teaJob}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">职称：</label>
			<div class="controls">
			${teacherInfo.teaJobName}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">出生年月：</label>
			<div class="controls">
			<fmt:formatDate value="${teacherInfo.teaBirth}" pattern="yyyy-MM-dd"/>
				
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">上传图片：</label>
			<div class="controls">
				<form:input path="teaPicpath" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">简介：</label>
			<div class="controls">
			${teacherInfo.teaBrief}
			</div>
		</div>
		<div id="stautsId"  class="controls">
		
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="ante:teacherInfo:edit"><input id="audited"  class="btn btn-primary" type="button" value="审核通过"/>&nbsp;</shiro:hasPermission>
			<shiro:hasPermission name="ante:teacherInfo:edit"><input id="reject"   class="btn btn-primary" type="button" value="驳回"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>