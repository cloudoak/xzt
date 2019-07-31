<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<!-- Mirrored from www.zi-han.net/theme/hplus/form_validate.html by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 20 Jan 2016 14:19:15 GMT -->
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>用户管理</title>
    <meta name="keywords" content="${fns:getConfig('keywords')} ${sysConfig.schoolName } ">
    <meta name="description" content="${fns:getConfig('description')} ${sysConfig.schoolName }">
    
    <link href="${hplusStatic }/favicon.ico" rel="shortcut icon">
	<link href="${hplusStatic }/css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
    <link href="${hplusStatic }/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
    <link href="${hplusStatic }/css/animate.min.css" rel="stylesheet">
    <link href="${hplusStatic }/css/style.min862f.css?v=4.1.0" rel="stylesheet">

</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5><a class="text-navy" href="${ctx}/sys/user/form?id=${user.id}">用户<shiro:hasPermission name="sys:user:edit">${not empty user.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="sys:user:edit">查看</shiro:lacksPermission></a></h5>
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
                        <form class="form-horizontal m-t" id="signupForm" action="${ctx}/sys/user/save" method="post">
                            <input type="hidden" name="id" value="${user.id }">
                            <input type="hidden" name="status" value="1">
                            <div class="form-group">
                                <label class="col-sm-3 control-label">用户名：</label>
                                <div class="col-sm-8">
                                    <input id="username" value="${user.loginName }" name="loginName" class="form-control" type="text" required="true">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">密码：</label>
                                <div class="col-sm-8">
                                    <input id="password" value="${user.password }" name="password" class="form-control" type="password">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">确认密码：</label>
                                <div class="col-sm-8">
                                    <input id="confirm_password" value="${user.password }" name="confirm_password" class="form-control" type="password">
                                    <span class="help-block m-b-none"><i class="fa fa-info-circle"></i> 请再次输入您的密码</span>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">联系人：</label>
                                <div class="col-sm-8">
                                    <input id="name" value="${user.name }" name="name" class="form-control" type="text" required="true">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">联系电话：</label>
                                <div class="col-sm-8">
                                    <input id="phone" value="${user.phone }" name="phone" class="form-control" type="tel" maxlength="12">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">角色分配：</label>
                                <div class="col-sm-8">
                                    <select class="form-control m-b" name="userType" id="userType">
	                                    <option value="0" hassubinfo="true" selected>平台管理员</option>
	                                </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label"></label>
                                <div class="col-sm-8">
                                    <font color="red">${message}</font>
                                </div>
                            </div>
                            <!-- <div class="form-group">
                                <div class="col-sm-8 col-sm-offset-3">
                                    <div class="checkbox">
                                        <label>
                                            <input type="checkbox" class="checkbox" id="agree" name="agree"> 我已经认真阅读并同意《H+使用协议》
                                        </label>
                                    </div>
                                </div>
                            </div> -->
                            <div class="form-group">
                                <div class="col-sm-8 col-sm-offset-3">
                                    <button class="btn btn-primary" type="submit">保存</button>&nbsp;
                                    <!-- <button class="btn btn-primary" onclick="history.go(-1)">返回</button> -->
                                    <input type="button" class="btn btn-primary" onclick="history.go(-1)" value="返回">
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
    $(document).ready(function(){
    	jQuery.validator.addMethod("isPhone", function(value,element) {
			  var length = value.length;
			  var mobile = /^(((13[0-9]{1})|(15[0-9]{1}))+\d{8})$/;
			  var tel = /^\d{3,4}-?\d{7,9}$/;
			  return this.optional(element) || (tel.test(value) || mobile.test(value));

			}, "请正确填写您的联系电话"); 
			$("#signupForm").validate({
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
    <script src="${hplusStatic }/js/bootstrap.min.js?v=3.3.6"></script>
	<script src="${hplusStatic }/js/content.min.js?v=1.0.0"></script>
	<script src="${hplusStatic }/js/plugins/validate/jquery.validate.min.js"></script>
	<script src="${hplusStatic }/js/plugins/validate/messages_zh.min.js"></script>
	<script src="${hplusStatic }/js/demo/form-validate-demo.min.js"></script>
	<script src="${hplusStatic }/js/content.min.js?v=1.0.0"></script>
	<script src="${hplusStatic }/js/plugins/iCheck/icheck.min.js"></script>
    <!-- <script src="http://tajs.qq.com/stats?sId=9051096" type="text/javascript" charset="UTF-8"></script> -->
    
</body>


<!-- Mirrored from www.zi-han.net/theme/hplus/form_validate.html by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 20 Jan 2016 14:19:16 GMT -->
</html>