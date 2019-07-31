<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>评价管理</title>
	<meta name="decorator" content="default"/>
	
	<%@ include file="/WEB-INF/views/include/commoncss.jsp" %>
	<script type="text/javascript">
		$(document).ready(function() {
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
                    	<h5><a href="${ctx}/ante/commentInfo/form?id=${commentInfo.id}">评语管理<shiro:hasPermission name="ante:commentInfo:edit">${not empty commentInfo.id?'修改':'新增'}</shiro:hasPermission><shiro:lacksPermission name="ante:commentInfo:edit">查看</shiro:lacksPermission></a></h5>
                    </div>
                    <div class="ibox-content">
						<form:form id="inputForm" modelAttribute="commentInfo" action="${ctx}/ante/commentInfo/save" method="post" class="form-horizontal">
							<form:hidden path="id"/>
							<sys:message content="${message}"/>		
							<div class="form-group">
								<label class="col-sm-3 control-label">编号：<span style="color:red;font-size:18px">*</span></label>
								<div class="col-sm-8">
									<input id="userId2" name="userId2" htmlEscape="false" maxlength="100" class="input-xlarge " required="required"/>
									<span class="help-block m-b-none"><i class="fa fa-info-circle"></i> 该用户登录名</span>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">评语类型：</label>
								<div class="col-sm-8">
										<div class="radio radio-inline">
											<input type="radio" id="rangeType" value="0" name="rangeType" ${commentInfo.rangeType==0?"checked":"" } title="总评1" required="required">
											<label for="gender2">总评</label>
										</div>
										<div class="radio radio-info radio-inline">
											<input type="radio" id="rangeType" value="1" name="rangeType" ${commentInfo.rangeType==1?"checked":"" } required="required">
											<label for="gender1">阶段性评语</label>
										</div>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">权限：</label>
								<div class="col-sm-8">
										<div class="radio radio-inline">
											<input type="checkbox" id="powerType1" value="P" name="powerType" ${commentInfo.powerType==P?"checked":"" } required="required">
											<label for="powerType1">家属</label>
										</div>
										<div class="radio radio-info radio-inline">
											<input type="checkbox" id="powerType2" value="V" name="powerType" ${commentInfo.powerType==V?"checked":"" } required="required">
											<label for="powerType2">来访者</label>
										</div>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">评论标题：</label>
								<div class="col-sm-8">
									<input id="title" name="title" htmlEscape="false" maxlength="100" class="input-xlarge "/>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">评论内容：</label>
								<div class="col-sm-8">
									<form:textarea path="content" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
								</div>
							</div>
							<div class="form-actions">
								<shiro:hasPermission name="ante:commentInfo:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
								<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
							</div>
						</form:form>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<script src="${hplusStatic }/js/bootstrap.min.js?v=3.3.6"></script>
	<script src="${hplusStatic }/js/content.min.js?v=1.0.0"></script>
	<script src="${hplusStatic }/js/plugins/validate/jquery.validate.min.js"></script>
	<script src="${hplusStatic }/js/plugins/validate/messages_zh.min.js"></script>
	<script src="${hplusStatic }/js/demo/form-validate-demo.min.js"></script>
	<script src="${hplusStatic }/js/content.min.js?v=1.0.0"></script>
	<script src="${hplusStatic }/js/plugins/iCheck/icheck.min.js"></script>
	<script src="${hplusStatic }/js/plugins/treeview/bootstrap-treeview.js"></script>
	<script src="${ctxStatic}/My97DatePicker/WdatePicker.js" ></script>
   	<script>
		$(document).ready(function() {
			$(".i-checks").iCheck({
				checkboxClass : "icheckbox_square-green",
				radioClass : "iradio_square-green",
			})
			
			$("#userId2").change(function(){
				var userId2 = $(this).val().trim();
				if(userId2 != ""){
					$.post(
						"${ctx}/sys/user/checkLoginName",
						{"userId2":userId2},
						function(result){
							if(result == "false"){
								parent.layer.alert('该用户名不存在，请重新填写！');
								$("#userId2").val("");
							}
						}
					)
				 }
			})
		});
	</script>
</body>
</html>