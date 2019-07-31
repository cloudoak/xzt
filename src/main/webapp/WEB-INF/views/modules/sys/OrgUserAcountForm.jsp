<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<!-- Mirrored from www.zi-han.net/theme/hplus/form_validate.html by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 20 Jan 2016 14:19:15 GMT -->
<head>
    <title>机构管理员账号</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="keywords" content="${fns:getConfig('keywords')} ${sysConfig.schoolName } ">
    <meta name="description" content="${fns:getConfig('description')} ${sysConfig.schoolName }">
    
    <link href="${hplusStatic }/favicon.ico" rel="shortcut icon">
	<link href="${hplusStatic }/css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
    <link href="${hplusStatic }/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
    <link href="${hplusStatic }/css/animate.min.css" rel="stylesheet">
    <link href="${hplusStatic }/css/style.min862f.css?v=4.1.0" rel="stylesheet">
   	<script>
		$(document).ready(function() {
			$(".i-checks").iCheck({
				checkboxClass : "icheckbox_square-green",
				radioClass : "iradio_square-green",
			})
			
			$("#loginName").change(function(){
				var account = $(this).val().trim();
				if(account != ""){
					$.post(
						"${ctx}/sys/user/checkLoginName",
						{"loginName":loginName},
						function(result){
							if(result == "false"){
								parent.layer.alert('该用户名已经存在，请重新填写！');
								$("#loginName").val("");
							}
						}
					)
				 }
			})
		});
	</script>
</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5><a class="text-navy" href="${ctx}/sys/user/form?id=${user.id}">管理员账号<shiro:hasPermission name="sys:user:edit">${not empty user.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="sys:user:edit">查看</shiro:lacksPermission></a></h5>
                        <div class="ibox-tools">
                            <!-- <a class="collapse-link">
                                <i class="fa fa-chevron-up"></i>
                            </a>
                            <a class="dropdown-toggle" data-toggle="dropdown" href="form_basic.html#">
                                <i class="fa fa-wrench"></i>
                            </a>
                            <a class="close-link">
                                <i class="fa fa-times"></i>
                            </a> -->
                        </div>
                    </div>
                    <div class="ibox-content">
                        <form class="form-horizontal m-t" id="inputForm" action="${ctx}/sys/org/addAcountForOrg" method="post">
                            <input type="hidden" name="id" value="${user.id }">
                            <input type="hidden" name="orgId" value="${orgId }">
                            <input type="hidden" name="status" value="1">
                            <div class="form-group">
                                <label class="col-sm-3 control-label">账号：<span style="color:red;font-size:18px">*</span></label>
                                <div class="col-sm-8">
                                    <input id="username" value="${user.loginName }" name="loginName" class="form-control" type="text" required="true" aria-invalid="true" class="error" maxlength="30">
                                </div>
                            </div>
                    	    <div class="form-group">
								<label class="col-sm-3 control-label">密码:<span style="color:red;font-size:18px">*</span></label>
								<div class="col-sm-8">
									<input id="password" name="password" type="password" value="" maxlength="50" minlength="6" class="form-control" required="true"/>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">确认密码:<span style="color:red;font-size:18px">*</span></label>
								<div class="col-sm-8">
									<input id="confirmNewPassword" name="confirmNewPassword" type="password" value="" maxlength="50" minlength="6" class="form-control" equalTo="#password" required="true"/>
								</div>
							</div>
                            
                            <div class="form-group">
                                <label class="col-sm-3 control-label">联系人：<span style="color:red;font-size:18px">*</span></label>
                                <div class="col-sm-8">
                                    <input id="name" value="${user.name }" name="name" class="form-control" type="text" maxlength="20" required="true">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">联系电话：<span style="color:red;font-size:18px">*</span></label>
                                <div class="col-sm-8">
                                    <input id="phone" value="${user.phone }" name="phone" class="form-control" type="tel" maxlength="12" required="true">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label"></label>
                                <div class="col-sm-8">
                                    <font color="red">${message}</font>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-8 col-sm-offset-3">
                                    <button class="btn btn-primary" type="submit">保存</button>&nbsp;
                                    <button class="btn btn-primary" onclick="gotoOrgAllot()">返回</button>
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
		$(document).ready(function() {
			$("#username").focus();
			// 联系电话(手机/电话皆可)验证
			jQuery.validator.addMethod("isPhone", function(value,element) {
			  var length = value.length;
			  var mobile = /^(((13[0-9]{1})|(15[0-9]{1}))+\d{8})$/;
			  var tel = /^\d{3,4}-?\d{7,9}$/;
			  return this.optional(element) || (tel.test(value) || mobile.test(value));

			}, "请正确填写您的联系电话"); 
			$("#inputForm").validate({
				rules: {
					 phone:{
						  required:true,
						  isPhone:true
						  }
				},
				messages: {
					confirmNewPassword: {equalTo: "请输入与上面相同的密码!"},
					phone:{
						  required: "请输入您的联系电话",
						  isPhone: "请输入一个有效的联系电话"
						  }
				},
				submitHandler: function(form){
					//loading('正在提交，请稍等...');
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
    <script type="text/javascript">
	    function windowOpen(url, name, width, height){
	    	var top=parseInt((window.screen.height-height)/2,10),left=parseInt((window.screen.width-width)/2,10),
	    		options="location=no,menubar=no,toolbar=no,dependent=yes,minimizable=no,modal=yes,alwaysRaised=yes,"+
	    		"resizable=yes,scrollbars=yes,"+"width="+width+",height="+height+",top="+top+",left="+left;
	    	window.open(url ,name , options);
	    }
	    
	    function gotoOrgAllot(id){
	    	window.location.href="${ctx}/sys/org/allot?id=${orgId}";
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