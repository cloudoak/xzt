<!-- 

注册信息

@author OAK 
@since 2017/12/19

-->
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">
	<title>注册页</title>
	<meta name="keywords" content="${fns:getConfig('keywords')} ${sysConfig.schoolName } ">
    <meta name="description" content="${fns:getConfig('description')} ${sysConfig.schoolName }">
	<link rel="shortcut icon" href="${hplusStatic }favicon.ico"> 
    <link href="${hplusStatic }/css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
    <link href="${hplusStatic }/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
    <link href="${hplusStatic }/css/animate.min.css" rel="stylesheet">
    <link href="${hplusStatic }/css/style.min862f.css?v=4.1.0" rel="stylesheet">
    <link href="${hplusStatic }/css/plugins/iCheck/custom.css" rel="stylesheet">
</head>
<body class="gray-bg">
    <%@ include file="/WEB-INF/views/web/common/header.jsp"%>
   	<div class="wrapper wrapper-content animated fadeInRight">
        <div class="row">
            <div class="col-sm-12">
	            <div class="ibox float-e-margins">
	            <form class="form-horizontal m-t" id="registorForm" action="${ctx}/register/save" method="post">
	            <input type="hidden" id="datamodel" name="datamodel" value="consultant" />
	            <input type="hidden" id="enableSMS" name="enableSMS" value="${enableSMS }" />
	            <input type="hidden" id="address" name="address" value="" />
	            <div class="ibox-title">
	                 <h5><a class="text-navy" href="javascript:void();"></a></h5>
	                 <div class="ibox-tools"></div>
	             </div>
	            <div class="ibox-content">
	             <div class="tabs-container">
                    <ul class="nav nav-tabs">
                        <li class="active"><a data-toggle="tab" data-model="consultant" href="#tab-1" aria-expanded="true"> 咨询师</a>
                        </li>
                        <li class=""><a data-toggle="tab" data-model="visitor" href="#tab-1" aria-expanded="false">来访者</a>
                        </li>
                        <li class=""><a data-toggle="tab" data-model="familyMembers" href="#tab-1" aria-expanded="false">家属</a>
                        </li>
                    </ul>
                    <div class="tab-content">
                        <div id="tab-1" class="tab-pane active">
                            <div class="panel-body">
                            	<div class="form-group">
									<label class="col-sm-3 control-label">用户名：</label>
									<div class="col-sm-8">
									<input type="text" id="account" class="form-control" name="account" placeholder="请输入用户名">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">姓名：</label>
									<div class="col-sm-8">
									<input type="text" id="name" class="form-control" name="name" placeholder="请输入姓名">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">密码：</label>
									<div class="col-sm-8">
									<input type="password" id="password" class="form-control" name="password" placeholder="请输入密码">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">确认密码：</label>
									<div class="col-sm-8">
									<input type="password" id="confirmpassword" name="confirmpassword" class="form-control" placeholder="请输入确认密码">
									</div>
								</div>
								<div id="consultant">
								<div class="form-group">
									<label class="col-sm-3 control-label">昵称：</label>
									<div class="col-sm-8">
									<input type="text" id="nickName" class="form-control" name="nickName" placeholder="请输入昵称">
									</div>
								</div>
								</div>
								<div id="familyMembers">
								<div class="form-group">
									<label class="col-sm-3 control-label">来访者用户名：</label>
									<div class="col-sm-8">
									<input type="text" id="visitorAccount" class="form-control" name="visitorAccount" placeholder="请输入来访者用户名">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">来访者手机号：</label>
									<div class="col-sm-8">
									<input type="text" id="visitorPhone" class="form-control" name="visitorPhone" placeholder="请输入来访者手机号">
									</div>
								</div>
								</div>
								<div class="form-group">
								 	<label class="col-sm-3 control-label">性别：</label>
	                                <div class="col-md-4">
	                                <fieldset>
	                                    <div class="radio radio-info radio-inline">
	                                        <input type="radio" id="optionsRadios1" value="1" checked="checked" name="sex" />
	                                        <label for="optionsRadios1">男</label>
	                                    </div>
	                                    <div class="radio radio-inline">
	                                        <input type="radio" id="optionsRadios2" value="0" name="sex"  />
	                                        <label for="optionsRadios2">女</label>
	                                    </div>
	                                </fieldset>
	                                </div>
								</div>
		                        <div class="form-group">
		                            <label class="col-sm-3 control-label">民族：</label>
		                            <div class="col-sm-8">
			                        <select id="nation" class="form-control" name="nation">
										<option value="">请选择你的民族</option>
										<c:forEach items="${nationList}" var="dict">
											<option value="${dict.value}">${dict.description}</option>
										</c:forEach>
									</select>
									</div>
		                        </div>
								<div class="form-group">
									<label class="col-sm-3 control-label">出生日期：</label>
									<div class="col-sm-8">
									<input id="birthday" style="border: 1px solid #e5e6e7;padding: 6px 12px;height: 34px;line-height:1.42857143" class="form-control Wdate" name="birthday" type="text"  maxlength="20" placeholder="年-月-日"
											onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false,maxDate:'%y-%M-%d'})"});" />
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">联系电话：</label>
									<div class="col-sm-8">
									<input type="text" id="phone" class="form-control" name="phone" placeholder="请输入你的手机号码">
									</div>
								</div>
								<div id="sVerificationCode">
								<div class="form-group">
									<label class="col-sm-3 control-label">短信验证码：</label>
									<div class="col-sm-4">
									<input type="text" id="sMSVerificationCode" class="form-control" name="sMSVerificationCode" placeholder="请输入你的验证码">
									</div>
									<div class="col-sm-4">
									<button class="btn btn-primary" id="btnGetVerificationCode" name="btnGetVerificationCode" type="button" >获取验证码</button>
									</div>
								</div>
								</div>
								<div class="form-group">
		                            <label class="col-sm-3 control-label">选择地区：</label>
		                            <div class="col-sm-8">
			                        <select id="province" class="form-control" name="province">
										<option value="">请选择你所在的城市/省</option>
										<c:forEach items="${provinces}" var="province">
											<option value="${province.code}">${province.name}</option>
										</c:forEach>
									</select>
									</div>
		                        </div>
		                        <div class="form-group">
		                            <label class="col-sm-3 control-label"></label>
		                            <div class="col-sm-8">
			                        <select id="city" class="form-control" name="city">
									</select>
									</div>
		                        </div>
		                        <div class="form-group">
		                            <label class="col-sm-3 control-label"></label>
		                            <div class="col-sm-8">
			                        <select id="area" class="form-control" name="area">
									</select>
									</div>
		                        </div>
							    <div class="form-group">
		                            <label class="col-sm-3 control-label">选择机构：</label>
		                            <div class="col-sm-8">
			                        <select id="organization" class="form-control" name="organization">
										<option value="">请选择你所在的机构</option>
										<c:forEach items="${offices}" var="office">
											<option value="${office.id}">${office.name}</option>
										</c:forEach>
									</select>
									</div>
		                        </div>
		                        <div id="visitor">
								<div class="form-group">
									<label class="col-sm-3 control-label">Email：</label>
									<div class="col-sm-8">
									<input type="text" id="email" class="form-control" name="email" placeholder="请输入你的邮箱">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">职务：</label>
									<div class="col-sm-8">
									<input type="text" id="position" class="form-control" name="position" placeholder="请输入你的职务">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">简介：</label>
									<div class="col-sm-8">
									<textarea name="introduction" class="form-control" id="introduction" rows="10" cols="20" placeholder="请输入自我简介"></textarea>
									</div>
								</div>
								</div>
                            </div>
                        </div> 
                    </div>
                </div>
	            	<br />
	            	<div class="form-group">
                        <div class="col-sm-8 col-sm-offset-3">
                        <fieldset>
                            <div class="radio radio-info radio-inline i-checks">
                                <input type="checkbox" value="0" id="chkProtocol" name="chkProtocol" />
                                <label for="chkProtocol">已阅读，并同意<a style="color:#27b03d;" href="#">《心知堂用户注册协议》</a></label>
                            </div>
                            <input type="text" id="protocol" style="opacity:0;border:none;outline:medium;" name="protocol" />
                        </fieldset>
                        </div>
                    </div>
	            	<div class="form-group">
                        <div class="col-sm-8 col-sm-offset-3">
                        <button class="btn btn-primary" id="btnRegistor" name="btnRegistor" type="submit">注 &nbsp;&nbsp;册</button>
                        </div>
                    </div>
	            </div>
            	</form>
            	</div>
			</div>
		</div>
	</div>
	<!-- 底部 -->
   <%@ include file="/WEB-INF/views/web/common/footer.jsp" %>
   <script src="${hplusStatic }/js/jquery.min.js?v=2.1.4"></script>
   <script src="${hplusStatic }/js/bootstrap.min.js?v=3.3.6"></script>
   <script src="${hplusStatic }/js/plugins/validate/jquery.validate.min.js"></script>
   <script src="${hplusStatic }/js/plugins/validate/messages_zh.min.js"></script>
   <script src="${hplusStatic }/js/plugins/iCheck/icheck.min.js"></script>
   <script src="${hplusStatic }/js/plugins/layer/layer.min.js" type="text/javascript"></script>
   <script src="${ctxStatic}/My97DatePicker/WdatePicker.js" ></script>
   <script type="text/javascript">
   		$(function(){
   			var orginalMaxTime = 5, maxtime = orginalMaxTime, enableSMS = $("#enableSMS").val();
   			
   			$(window).on("load", function(){
   				$("#familyMembers").hide();
   				
   				if(enableSMS == 0){
   					$("#sVerificationCode").hide();
   				}else{
   					$("#btnGetVerificationCode").on("click", function(){
   		   				$(this).attr("disabled", true);
   		   	        	$(this).text("剩余（" + maxtime + " 秒）");
   		   	        	$.ajax({
   		    				url: "${ctx}/register/sendSMS",
   		    	            type: "POST",
   		    	            data: $("#registorForm").serialize(),
   		    	            success:function(responseText, textStatus, jqXHR){
   		    	            	if(responseText != null){
   		    	            		layer.alert("验证码已发送到您的手机");
   		    	            	}
   		    	            }
   		                });
   		   	        	window.setMinute = function()
   		   				{
   		   				    if (maxtime == 0) {
   		   				    	window.clearInterval(t);
   		   				  		maxtime = orginalMaxTime;
   		   				    	$("#btnGetVerificationCode").attr("disabled", false);
   		   				        $("#btnGetVerificationCode").text("获取验证码");
   		   				    }
   		   				    else {
   		   				  		maxtime--;
   		   				    	$("#btnGetVerificationCode").text("剩余（" + maxtime + " 秒）");
   		   				    }
   		   				};
   		   				t = window.setInterval("window.setMinute()", 1000);
   		   			});//15510111946
   				}
   				
   				$("#province").on("change", function(){
   					var provinceId = $(this).val();
   					$.ajax({
   						url: '${ctx}/register/findByProvinceId',
   						type: 'get',
   						dataType: 'json',
   						data: {'provinceId': provinceId},
   					})
   					.done(function(data) {
   						$("#city").html("");
   						$("#city").append(new Option('请选择你所在的管辖区/街道', ''));
   						$("#area").html("");
						$("#area").append(new Option('请选择你所在的镇/乡/村', ''));
   						for(var i = 0 ; i < data.length ;i++){
   							var option = new Option(data[i].name, data[i].code);
   							$("#city").append(option);
   						}
   					})
   					.fail(function() {
   						console.log("error");
   					})
   					.always(function() {
   						console.log("complete");
   					});
   				});
   				
   				$("#city").on("change", function(){
   					var cityId = $(this).val();
   					$.ajax({
   						url: '${ctx}/register/findByCityId',
   						type: 'get',
   						dataType: 'json',
   						data: {'cityId': cityId},
   					})
   					.done(function(data) {
   						$("#area").html("");
						$("#area").append(new Option('请选择你所在的镇/乡/村', ''));
   						for(var i = 0 ; i < data.length ;i++){
   							var option = new Option(data[i].name, data[i].code);
   							$("#area").append(option);
   						}
   					})
   					.fail(function() {
   						console.log("error");
   					})
   					.always(function() {
   						console.log("complete");
   					});
   				});
   				
   				$("#chkProtocol").on('ifClicked', function(event){
   					var checked = $(this).is(':checked');
   					if(!checked){
   						$("#protocol").val("1");
   					}else{
   						$("#protocol").val("");
   					}
   				});
   				
   				$(".tabs-container .nav-tabs").children("li").each(function( any ){
   	   				$(this).on("click", function(){
   	   					var model = $(this).children("a").data("model");
   	   					switch(model){
   	   						case "visitor":
   	   							$("#datamodel").val(model);
   	   							$("#" + model).hide();
   	   							$("#familyMembers").hide();
   	   							$("#consultant").hide();
   	   							break;
   	   						case "familyMembers":
   	   							$("#datamodel").val(model);
   	   							$("#visitor").hide();
   	   							$("#" + model).show();
   	   							$("#consultant").hide();
	   							break;
   							default:
   								$("#datamodel").val(model);
   								$("#visitor").show();
   								$("#familyMembers").hide();
   								$("#" + model).show();
   								break;
   	   					}
   	   				});
   	   			});
   			});
   			
   			/* jQuery.validator.addMethod("readStory", function(value, element, param) {
           	  	var checked = $("#" + param).is(':checked');
           	  	return this.optional(element) || checked ;   
           	}); */
   			
   			var regRules = {
   	    			account: {
   		    			required: true,
   		    			remote: {
   	    					url: "${ctx}/register/checkAccount",
   	                        type: "post",
   	                        dataType: "json",
   	                        data: {
   	                        	account: function () {
   	                               return $("#account").val();
   	                          	},
   	                          	org: function() {
   	                          		return $("#organization").val();
   	                          	}
   	                        },
   	                        dataFilter: function (data) {
   	                        	return (data == 1);
   	                        }
   	    				}
   		    		},
   		    		name: {
   		    			required: true
   		    		},
   		    		password: {
   		    			required: true
   		    		},
   		    		confirmpassword: {
   		    			equalTo: "#password"
   		    		},
   		    		visitorAccount: {
   		    			required: true,
   		    			remote: {
   	    					url: "${ctx}/register/checkExistAccount",
   	                        type: "post",
   	                        dataType: "json",
   	                        data: {
   	                        	account: function () {
   	                               return $("#visitorAccount").val();
   	                          	},
   	                          	org: function() {
   	                          		return $("#organization").val();
   	                          	}
   	                        },
   	                        dataFilter: function (data) {
   	                        	return (data == 1);
   	                        }
   	    				}
   		    		},
   		    		visitorPhone: {
   		    			required: true
   		    		},
   	    			email: {
   		    			required: true
   		    		},
   		    		birthday: {
   		    			required: true
   		    		},
   		    		area: {
   		    			required: true
   		    		},
   		    		organization: {
   		    			required: true
   		    		},
   		    		position: {
   		    			required: true
   		    		},
   		    		protocol: {
   		    			required: true
   		    			//readStory: "protocol"
   		    		}
   		    	},
   		    	regMessage = {
   	   		    		account: {
   	   		    			required:'用户名必填！',
   	   		    			remote: "用户名已经存在，请重新选择用户名!"
   	   		    		},
   	   		    		name: {
   	   		    			required:'姓名必填！'
   	   		    		},
   	   		    		password: {
   	   		    			required:'密码必填！'
   	   		    		},
   	   		    		confirmpassword: {
   	   		    			equalTo: "输入的密码与确认密码不一致!"
   	   		    		},
   	   		    		visitorAccount:{
   	   		    			required:'来访者用户名必填！',
   	   		    			remote: "来访者用户名不存在，请重新选择已存在的来访者用户名!"
   	   		    		},
   						visitorPhone:{
   							required: "来访者电话号码必填！"
   	   		    		},
   	   		    		email: {
   	   		    			required: "Email必填！"
   	   		    		},
   	   		    		birthday: {
   	   		    			required: "生日必填！"
   	   		    		},
   	   		    		area: {
   	   		    			required: "所在区必填！"
   	   		    		},
   	   		    		organization: {
   		    				required: "所在机构必填！"
   		    			},
   	   		    		position: {
   	   		    			required: "职位必填！"
   	   		    		},
   	   		    		protocol:{
   	   		    			required: "请阅读本协议后勾选同意！"
   	   		    		}
   	   		    	};
   			
   			if($("#enableSMS").val() == 1){
   				regRules['phone'] = {required: true};
   				regRules['sMSVerificationCode'] = {
	    			required: true,
    				remote: {
    					url: "${ctx}/register/checkVerificationCode",
                        type: "post",
                        dataType: "json",
                        data: {
                        	verificationCode: function () {
                               return $("#sMSVerificationCode").val();
                          	},
    						enableSMS: function () {
    							return $("#enableSMS").val();
    						}
                        },
                        dataFilter: function (data) {
                        	return (data == 1);
                        }
    				}
    			};
   				regMessage['phone'] = {required: "电话号码必填！"};
   				regMessage['sMSVerificationCode'] = {required: "验证码必输！", remote: "验证码不正确请重新获取！"};
   			}
   			
   			$("#registorForm").validate({
   	    		errorElement: 'div',
   	    		errorClass: 'help-block',
   	    		focusInvalid: false,
   	    		rules: regRules, 
   	    		messages: regMessage,
   		    	invalidHandler: function (event, validator) { //display error alert on form submit   
   					$('.alert-danger', $('.login-form')).show();
   				},
   				highlight: function (e) {
   					$(e).closest('.form-group').removeClass('has-info').addClass('has-error');
   				},
   				success: function (e) {
   					$(e).closest('.form-group').removeClass('has-error').addClass('has-info');
   					$(e).remove();
   				},
   				errorPlacement: function (error, element) {
   					 error.appendTo(element.parent());  
   				},
   				submitHandler: function (form) {
   					form.submit();
   				}
   	    	});
   			$(".i-checks").iCheck({checkboxClass:"icheckbox_square-green", radioClass:"iradio_square-green"});
   		});
   </script>
</body>
</html>