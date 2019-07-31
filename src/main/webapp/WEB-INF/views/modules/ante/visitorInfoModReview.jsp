<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>来访者自助审核</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
			$("#audited").click(function(){
				 if(confirm("确定审核通过吗？"))
				 {
					 $("#stautsId").append("<input name='status' type='hidden' value='SUCCESS'/>");
					 $("#inputForm").submit();
				 }
				
			});
			
			$("#reject").click(function(){
				 if(confirm("确定驳回吗？"))
				 {
					 $("#stautsId").append("<input name='status' type='hidden' value='REFUSE'/>");
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
		<%-- <li><a href="${ctx}/ante/visitorInfo/">来访者列表</a></li> --%>
		<li class="active"><a href="${ctx}/ante/visitorInfo/form?id=${visitorInfo.id}">添加来访者信息<shiro:hasPermission name="ante:visitorInfo:edit">${not empty visitorInfo.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="ante:visitorInfo:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="visitorInfo" action="${ctx}/ante/visitorInfo/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">来访者编号：</label>
			<div class="controls">
				${visitorInfo.visitorNo}		
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">姓名：</label>
			<div class="controls">
				${visitorInfo.realName}
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">性别：</label>
			<div class="controls">
				${visitorInfo.sex}
			</div>
		</div>
		<div class="control-group">
			<div id="stautsId"  class="controls">
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">民族：</label>
			<div class="controls">
				${visitorInfo.nation}
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">出生年月：</label>
			<div class="controls">
				${visitorInfo.birthday}
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">组织：</label>
			<div class="controls">
				${visitorInfo.orgId}
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">联系电话：</label>
			<div class="controls">
				${visitorInfo.phoneNum}
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">审核理由：</label>
			<div class="controls">
				<textarea name="remarks" rows="4" ></textarea>  
			</div>
		</div>
		<!-- id="btnSubmit" -->
		<div class="form-actions">
			<shiro:hasPermission name="ante:visitorInfo:edit"><input id="audited"  class="btn btn-primary" type="button" value="审核通过"/>&nbsp;</shiro:hasPermission>
			<shiro:hasPermission name="ante:visitorInfo:edit"><input id="reject"   class="btn btn-primary" type="button" value="驳回"/>&nbsp;</shiro:hasPermission>
		</div>
		
	</form:form>
</body>
</html>