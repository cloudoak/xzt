<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>系统积分设置管理(平台)</title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link href="${hplusStatic }/favicon.ico" rel="shortcut icon">
	<link href="${hplusStatic }/css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
    <link href="${hplusStatic }/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
    <link href="${hplusStatic }/css/animate.min.css" rel="stylesheet">
    <link href="${hplusStatic }/css/style.min862f.css?v=4.1.0" rel="stylesheet">
    <link href="${serverUrl }css/measure.css" type="text/css" rel="stylesheet" />
</head>
<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeInRight">
        <div class="row">
            <div class="col-sm-12">
	    		<div class="ibox float-e-margins">
			        <div class="ibox-title">
			            <h5>平台系统积分设置</h5>
			        </div>
					<div class="ibox-content">
						<form class="form-horizontal m-t" id="inputForm"  action="${ctx}/jzmk/sysIntegral/sys/save" method="post">
							<input type="hidden" name="id"/>
							<sys:message content="${message}"/>		
							<div class="form-group">
								<label class="col-sm-3 control-label">专业测评积分：</label>
								<div class="col-sm-6">
									<input type="digits" name="zycpIntegral" value="${empty sysIntegral.zycpIntegral ? 0 : sysIntegral.zycpIntegral}" class="form-control"/>
								</div>
								<label class="control-label">分</label>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">职业测评积分：</label>
								<div class="col-sm-6">
									<input type="digits" name="zhycpIntegral" value="${empty sysIntegral.zhycpIntegral ? 0 : sysIntegral.zhycpIntegral}" class="form-control"/>
								</div>
								<label class="control-label">分</label>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">咨询预约积分：</label>
								<div class="col-sm-6">
									<input type="digits" name="zxyyIntegral" value="${empty sysIntegral.zxyyIntegral ? 0 : sysIntegral.zxyyIntegral}" class="form-control"/>
								</div>
								<label class="control-label">分</label>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">课堂留言积分：</label>
								<div class="col-sm-6">
									<input type="digits" name="ktlyIntegral" value="${empty sysIntegral.ktlyIntegral ? 0 : sysIntegral.ktlyIntegral}" class="form-control"/>
								</div>
								<label class="control-label">分</label>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">职业指导新闻评论：</label>
								<div class="col-sm-6">
									<input type="digits" name="zyzdxwplIntegral" type="digits" value="${empty sysIntegral.zyzdxwplIntegral ? 0 : sysIntegral.zyzdxwplIntegral}" class="form-control"/>
								</div>
								<label class="control-label">分</label>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">资格文件：</label>
								<div class="col-sm-6">
									<input type="digits" name="zgwjIntegral" value="${empty sysIntegral.zgwjIntegral ? 0 : sysIntegral.zgwjIntegral}" class="form-control"/>
								</div>
								<label class="control-label">分</label>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">课件上传：</label>
								<div class="col-sm-6">
									<input type="digits" name="kjscIntegral" type="digits" value="${empty sysIntegral.kjscIntegral ? 0 : sysIntegral.kjscIntegral}" class="form-control"/>
								</div>
								<label class="control-label">分</label>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">资源：</label>
								<div class="col-sm-6">
									<input type="digits" name="zyIntegral" type="digits" value="${empty sysIntegral.zyIntegral ? 0 : sysIntegral.zyIntegral}" class="form-control"/>
								</div>
								<label class="control-label">分</label>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">提供咨询：</label>
								<div class="col-sm-6">
									<input type="digits" name="tgzxIntegral" type="digits" value="${empty sysIntegral.tgzxIntegral ? 0 : sysIntegral.tgzxIntegral}" class="form-control"/>
								</div>
								<label class="control-label">分</label>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">接收督导：</label>
								<div class="col-sm-6">
									<input type="digits" name="jsddIntegral" type="digits" value="${empty sysIntegral.jsddIntegral ? 0 : sysIntegral.jsddIntegral}" class="form-control"/>
								</div>
								<label class="control-label">分</label>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">提供督导：</label>
								<div class="col-sm-6">
									<input type="digits" name="tgddIntegral" type="digits" value="${empty sysIntegral.tgddIntegral ? 0 : sysIntegral.tgddIntegral}" class="form-control"/>
								</div>
								<label class="control-label">分</label>
							</div>
							<div class="form-group">
							<div class="col-sm-8 col-sm-offset-3">
								<shiro:hasPermission name="jzmk:sysIntegral:edit">
								<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
								<!-- <input id="btnCancel" class="btn btn-primary" type="button" value="返 回" onclick="window.close(this);"/> -->
							</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<script src="${hplusStatic }/js/jquery.min.js?v=2.1.4"></script>
    <script src="${hplusStatic }/js/bootstrap.min.js?v=3.3.6"></script>
    <script src="${hplusStatic }/js/content.min.js?v=1.0.0"></script>
    <script src="${hplusStatic }/js/plugins/validate/jquery.validate.min.js"></script>
    <script src="${hplusStatic }/js/plugins/validate/messages_zh.min.js"></script>
    <script type="text/javascript">
		$(function() {
			//$("#name").focus();
			jQuery.validator.addMethod("integer", function (value, element) {

    			var number = /^-?\d+$/g;  //正、负 整数 + 0

    			return this.optional(element) || (number.test(value));

			}, "请输入整数"); 
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				rules:{
					zycpIntegral:{
						required:true,
						integer:zycpIntegral
					},
					zhycpIntegral:{
						required:true,
						integer:zhycpIntegral
					},
					zxyyIntegral:{
						required:true,
						integer:zxyyIntegral
					},
					ktlyIntegral:{
						required:true,
						integer:ktlyIntegral
					},
					zyzdxwplIntegral:{
						required:true,
						integer:zyzdxwplIntegral
					},
					zgwjIntegral:{
						required:true,
						integer:zgwjIntegral
					},
					kjscIntegral:{
						required:true,
						integer:kjscIntegral
					},
					zyIntegral:{
						required:true,
						integer:zyIntegral
					},
					tgzxIntegral:{
						required:true,
						integer:tgzxIntegral
					},
					jsddIntegral:{
						required:true,
						integer:jsddIntegral
					},
					tgddIntegral:{
						required:true,
						integer:tgddIntegral
					}
				},
				messages:{
					zycpIntegral:{
						requierd:'字段必填',
						integer:'必须输入整数'
					},
					zhycpIntegral:{
						requierd:'字段必填',
						integer:'必须输入整数'
					},
					zxyyIntegral:{
						requierd:'字段必填',
						integer:'必须输入整数'
					},
					ktlyIntegral:{
						requierd:'字段必填',
						integer:'必须输入整数'
					},
					zyzdxwplIntegral:{
						requierd:'字段必填',
						integer:'必须输入整数'
					},
					zgwjIntegral:{
						requierd:'字段必填',
						integer:'必须输入整数'
					},
					kjscIntegral:{
						requierd:'字段必填',
						integer:'必须输入整数'
					},
					zyIntegral:{
						requierd:'字段必填',
						integer:'必须输入整数'
					},
					tgzxIntegral:{
						requierd:'字段必填',
						integer:'必须输入整数'
					},
					jsddIntegral:{
						requierd:'字段必填',
						integer:'必须输入整数'
					},
					tgddIntegral:{
						requierd:'字段必填',
						integer:'必须输入整数'
					}
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