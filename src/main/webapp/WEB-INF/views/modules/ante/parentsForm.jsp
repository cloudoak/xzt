<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>家属管理</title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="keywords" content="${fns:getConfig('keywords')} ${sysConfig.schoolName } ">
	<meta name="description" content="${fns:getConfig('description')} ${sysConfig.schoolName }">
	<link href="${hplusStatic }/favicon.ico" rel="shortcut icon">
	<link href="${hplusStatic }/css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
	<link href="${hplusStatic }/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
	<link href="${hplusStatic }/css/animate.min.css" rel="stylesheet">
	<link href="${hplusStatic }/css/style.min862f.css?v=4.1.0" rel="stylesheet">
	<link href="${hplusStatic }/css/plugins/iCheck/custom.css" rel="stylesheet">
	<link href="${hplusStatic }/css/plugins/treeview/bootstrap-treeview.css" rel="stylesheet">
	<link href="${hplusStatic }/css/plugins/awesome-bootstrap-checkbox/awesome-bootstrap-checkbox.css" rel="stylesheet">
</head>

<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeInRight">
		<div class="row">
			<div class="col-sm-12">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<h5>
							<a class="text-navy" href="#">家属<shiro:hasPermission
									name="ante:parents:edit">${not empty parentsVo.id?'修改':'添加'}</shiro:hasPermission>
								<shiro:lacksPermission name="ante:parents:edit">查看</shiro:lacksPermission></a>
						</h5>
					</div>
					<div class="ibox-content">
						<form class="form-horizontal m-t" id="signupForm" action="${ctx}/ante/parents/save" method="post">
							<input type="hidden" name="id" value="${parentsVo.id }">
							<input type="hidden" name="parentNo" value="${parentsVo.parentNo }">
							
							<div class="form-group">
								<label class="col-sm-3 control-label">来访者编号：<span style="color:red;font-size:18px">*</span></label>
								<div class="col-sm-8">
									<input id="studentCode" name="studentCode"
										value="${parentsVo.studentCode }" class="form-control" type="text" required="required" 
										${parentsVo.id!=null?"readonly":"" }>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">家属姓名：<span style="color:red;font-size:18px">*</span></label>
								<div class="col-sm-8">
									<input id="name" name="name" value="${parentsVo.name }" class="form-control" type="text" required="required">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">性别：<span style="color:red;font-size:18px">*</span></label>
								<div class="col-sm-8">
									<fieldset>
										<div class="radio radio-info radio-inline">
											<input type="radio" id="gender1" value="1" name="gender" ${parentsVo.gender==1?"checked":"" } required="required">
											<label for="gender1">男</label>
										</div>
										<div class="radio radio-inline">
											<input type="radio" id="gender2" value="0" name="gender" ${parentsVo.gender==0?"checked":"" } required="required">
											<label for="gender2">女</label>
										</div>
									</fieldset>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">出生年月：<span style="color:red;font-size:18px">*</span></label>
								<div class="col-sm-2">
									<input name="birthday" type="text" readonly="readonly"
										maxlength="20" class="form-control"
										value="<fmt:formatDate value="${parentsVo.birthday}" pattern="yyyy-MM-dd"/>"
										onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">用户名：<span style="color:red;font-size:18px">*</span></label>
								<div class="col-sm-8">
									<input id="account" name="account" value="${parentsVo.account }" class="form-control" type="text" required="required">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">密码:<span style="color:red;font-size:18px">*</span></label>
								<div class="col-sm-8">
									<input id="password" name="password" type="password" value="" maxlength="50" minlength="3" class="form-control" required="false"/>
									<c:if test="${parentsVo.id != null}">
										<span class="help-block m-b-none" style="color:red"><i class="fa fa-info-circle"></i> 说明：密码空，则不修改密码</span>
									</c:if>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">验证密码:<span style="color:red;font-size:18px">*</span></label>
								<div class="col-sm-8">
									<input id="confirmNewPassword" name="confirmNewPassword" type="password" value="" maxlength="50" minlength="3" class="form-control" equalTo="#password" required="false"/>
								</div>
							</div>
								
							<div class="form-group">
								<div class="col-sm-8 col-sm-offset-3">
									<button class="btn btn-primary" type="submit">保存</button>&nbsp;
									<button class="btn btn-primary" onclick="history.go(-1)">返回</button>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script src="${hplusStatic }/js/jquery.min.js?v=2.1.4"></script>
	<script type="text/javascript">
		function windowOpen(url, name, width, height) {
			var top = parseInt((window.screen.height - height) / 2, 10), left = parseInt(
					(window.screen.width - width) / 2, 10), options = "location=no,menubar=no,toolbar=no,dependent=yes,minimizable=no,modal=yes,alwaysRaised=yes,"
					+ "resizable=yes,scrollbars=yes,"
					+ "width="
					+ width
					+ ",height=" + height + ",top=" + top + ",left=" + left;
			window.open(url, name, options);
		}
	</script>
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
		$(function() {
			$(".i-checks").iCheck({
				checkboxClass : "icheckbox_square-green",
				radioClass : "iradio_square-green",
			})
			
			$("#studentCode").change(function(){
				var studentCode = $(this).val().trim();
				if(studentCode != ""){
					$.post(
						"${ctx}/ante/parents/checkStuCode",
						{"studentCode":studentCode},
						function(result){
							if(result == "false"){
								parent.layer.alert('该编号不存在，请重新填写！');
								$("#studentCode").val("");
							}
						}
					)
				}
			})
			
			$("#account").change(function(){
				var account = $(this).val().trim();
				if(account != ""){
					$.post(
						"${ctx}/ante/parents/checkAccount",
						{"account":account},
						function(result){
							if(result == "true"){
								parent.layer.alert('该用户名已经存在，请重新填写！');
								$("#account").val("");
							}
						}
					)
				}
			});
			
			$("#treeview6").treeview({
				color : "#428bca",
				expandIcon : "glyphicon glyphicon-stop",
				collapseIcon : "glyphicon glyphicon-unchecked",
				nodeIcon : "glyphicon glyphicon-user",
				showTags : !0,
				data : '${fns:getMenuList()}'
			});
			
			$("#password").focus();
			$("#inputForm").validate({
				rules: {
					/* oldPassword: {require: true},
					newPassword: {require: true} */
				},
				messages: {
					/* oldPassword: "旧密码必须填写",
					newPassword: "新密码必须填写", */
					confirmNewPassword: {equalTo: "新密码与确认密码不一致"}
				},
				submitHandler: function(form){
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
</body>
</html>