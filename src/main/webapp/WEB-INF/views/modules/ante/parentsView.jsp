<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<!-- Mirrored from www.zi-han.net/theme/hplus/form_validate.html by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 20 Jan 2016 14:19:15 GMT -->
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
						<h5>家属查看</h5>
					</div>
					<div class="ibox-content">
						<form class="form-horizontal m-t" id="signupForm" action="${ctx}/ante/parents/save" method="post">
							<input type="hidden" name="id" value="${parentsVo.id }">
							<input type="hidden" name="parentNo" value="${parentsVo.parentNo }">
							
							<div class="form-group">
								<label class="col-sm-3 control-label">来访者编号：</label>
								<div class="col-sm-8">
									<input id="id" name="id"
										value="${parentsVo.studentCode }" class="form-control" type="text" readonly="readonly" >
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">家属编号：</label>
								<div class="col-sm-8">
									<input id="parentNo" name="parentNo" value="${parentsVo.parentNo }" class="form-control" type="text" readonly="readonly">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">家属姓名：</label>
								<div class="col-sm-8">
									<input id="name" name="name" value="${parentsVo.name }" class="form-control" type="text" readonly="readonly">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">性别：</label>
								<div class="col-sm-8">
									<fieldset>
										<div class="radio radio-info radio-inline">
											<input type="radio" id="gender1" value="1" name="gender" ${parentsVo.gender==1?"checked":"" } disabled="disabled">
											<label for="gender1">男</label>
										</div>
										<div class="radio radio-inline">
											<input type="radio" id="gender2" value="0" name="gender" ${parentsVo.gender==0?"checked":"" } disabled="disabled">
											<label for="gender2">女</label>
										</div>
									</fieldset>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">出生年月：</label>
								<div class="col-sm-2">
									<input name="birthday" type="text" disabled="disabled"
										maxlength="20" class="form-control"
										value="<fmt:formatDate value="${parentsVo.birthday}" pattern="yyyy-MM-dd"/>"
										onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">登陆用户名：</label>
								<div class="col-sm-8">
									<input id="account" name="account" value="${parentsVo.account }" class="form-control" type="text" readonly="readonly">
								</div>
							</div>
								
							<div class="form-group">
								<div class="col-sm-8 col-sm-offset-3">
									<input type="button" class="btn btn-primary" onclick="history.go(-1)" value="返回" />
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
</body>


<!-- Mirrored from www.zi-han.net/theme/hplus/form_validate.html by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 20 Jan 2016 14:19:16 GMT -->
</html>