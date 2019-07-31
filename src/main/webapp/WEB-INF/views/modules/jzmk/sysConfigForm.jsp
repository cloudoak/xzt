<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>系统参数设置(平台)</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="${hplusStatic }/favicon.ico" rel="shortcut icon">
	<%@ include file="/WEB-INF/views/include/commoncss.jsp" %>
    <link href="${hplusStatic }/css/plugins/iCheck/custom.css" rel="stylesheet">
</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>平台系统参数设置</h5>
                    </div>
                    <div class="ibox-content">
                        <form:form class="form-horizontal m-t" id="signupForm" modelAttribute="sysConfig" action="${ctx}/jzmk/sysConfig/sys/save" method="post">
                            <form:hidden path="id"/>
                            <%-- <input type="hidden" name="id" value="${sysConfig.id }"> --%>
                            <sys:message content="${message}"/>	
                            <div class="form-group">
                                <label class="col-sm-3 control-label">系统名称：</label>
                                <div class="col-sm-8">
                                   	<input id="schoolName" name="schoolName" value="${sysConfig.schoolName }" class="form-control" type="text" required="required">
                                    <span class="help-block m-b-none"><i class="fa fa-info-circle"></i> 默认为登录机构的名称（必填）</span>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">页面标题：</label>
                                <div class="col-sm-8">
                                    <input id="systemTitle" name="systemTitle" value="${sysConfig.systemTitle }" class="form-control" type="text" aria-required="true" aria-invalid="true" class="error" required="required">
                                	<!-- <input path="systemTitle" htmlEscape="false" maxlength="10" class="form-control" type="text" required="required"/> -->
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">上传图片大小：</label>
                                <div class="col-sm-8">
                                    <input id="pictureSize" name="pictureSize" value="${sysConfig.pictureSize }" class="form-control" type="digits" aria-required="true" aria-invalid="true" size="4" >
                                	<span class="help-block m-b-none"><i class="fa fa-info-circle"></i> （kb）</span>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">上传文章大小：</label>
                                <div class="col-sm-8">
                                    <input id="article" name="article" value="${sysConfig.article }" class="form-control" type="digits" aria-required="true" aria-invalid="true" maxlength="4" >
                                	<span class="help-block m-b-none"><i class="fa fa-info-circle"></i> （kb）</span>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">文章是否需要审核：</label>
                                <div class="col-md-4">
                                <fieldset>
                                    <div class="radio radio-info radio-inline">
                                        <input type="radio" id="checkArticle1" value="1" name="checkArticle" ${sysConfig.checkArticle==1?"checked":"" } required="required">
                                        <label for="checkArticle1">是</label>
                                    </div>
                                    <div class="radio radio-inline">
                                        <input type="radio" id="checkArticle2" value="0" name="checkArticle" ${sysConfig.checkArticle==0?"checked":"" } required="required">
                                        <label for="checkArticle2">否</label>
                                    </div>
                                </fieldset>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">是否开通积分：</label>
                                <div class="col-md-4">
                                <fieldset>
                                    <div class="radio radio-info radio-inline">
                                        <input type="radio" id="enableIntegral1" value="1" name="enableIntegral" ${sysConfig.enableIntegral==1?"checked":"" } required="required">
                                        <label for="enableIntegral1">是</label>
                                    </div>
                                    <div class="radio radio-inline">
                                        <input type="radio" id="enableIntegral2" value="0" name="enableIntegral" ${sysConfig.enableIntegral==0?"checked":"" } required="required">
                                        <label for="enableIntegral2">否</label>
                                    </div>
                                </fieldset>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">是否开通匿名评价：</label>
                                <div class="col-md-4">
                                <fieldset>
                                    <div class="radio radio-info radio-inline">
                                        <input type="radio" id="enableAnonymityAppraise1" value="1" name="enableAnonymityAppraise" ${sysConfig.enableAnonymityAppraise==1?"checked":"" } required="required">
                                        <label for="enableAnonymityAppraise1">是</label>
                                    </div>
                                    <div class="radio radio-inline">
                                        <input type="radio" id="enableAnonymityAppraise2" value="0" name="enableAnonymityAppraise" ${sysConfig.enableAnonymityAppraise==0?"checked":"" } required="required">
                                        <label for="enableAnonymityAppraise2">否</label>
                                    </div>
                                </fieldset>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">开通咨询收费：</label>
                                <div class="col-md-4">
                                <fieldset>
                                    <div class="radio radio-info radio-inline">
                                        <input type="radio" id="enableCounselFree1" value="1" name="enableCounselFree" ${sysConfig.enableCounselFree==1?"checked":"" } required="required">
                                        <label for="enableCounselFree1">开通</label>
                                    </div>
                                    <div class="radio radio-inline">
                                        <input type="radio" id="enableCounselFree2" value="0" name="enableCounselFree" ${sysConfig.enableCounselFree==0?"checked":"" } required="required">
                                        <label for="enableCounselFree2">关闭</label>
                                    </div>
                                </fieldset>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">开通短信提醒：</label>
                                <div class="col-md-4">
                                <fieldset>
                                    <div class="radio radio-info radio-inline">
                                        <input type="radio" id="enableSmsRemind1" value="1" name="enableSmsRemind" ${sysConfig.enableSmsRemind==1?"checked":"" } required="required">
                                        <label for="enableSmsRemind1">开通</label>
                                    </div>
                                    <div class="radio radio-inline">
                                        <input type="radio" id="enableSmsRemind2" value="0" name="enableSmsRemind" ${sysConfig.enableSmsRemind==0?"checked":"" } required="required">
                                        <label for="enableSmsRemind2">关闭</label>
                                    </div>
                                </fieldset>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">开通音频咨询：</label>
                                <div class="col-md-4">
                                <fieldset>
                                    <div class="radio radio-info radio-inline">
                                        <input type="radio" id="enableCounselVoice1" value="1" name="enableCounselVoice" ${sysConfig.enableCounselVoice==1?"checked":"" } required="required">
                                        <label for="enableCounselVoice1">开通</label>
                                    </div>
                                    <div class="radio radio-inline">
                                        <input type="radio" id="enableCounselVoice2" value="0" name="enableCounselVoice" ${sysConfig.enableCounselVoice==0?"checked":"" } required="required">
                                        <label for="enableCounselVoice2">关闭</label>
                                    </div>
                                </fieldset>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">是否关闭系统：</label>
                                <div class="col-md-4">
                                <fieldset>
                                    <div class="radio radio-info radio-inline">
                                        <input type="radio" id="systemClosed1" value="1" name="systemClosed" ${sysConfig.systemClosed==1?"checked":"" } required="required">
                                        <label for="systemClosed1">开通</label>
                                    </div>
                                    <div class="radio radio-inline">
                                        <input type="radio" id="systemClosed2" value="0" name="systemClosed" ${sysConfig.systemClosed==0?"checked":"" } required="required">
                                        <label for="systemClosed2">关闭</label>
                                    </div>
                                </fieldset>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">预约时限：</label>
                                <div class="col-sm-8">
                                    <input id="bookingDays" name="bookingDays" value="${sysConfig.bookingDays }" class="form-control" type="digits" aria-required="true" aria-invalid="true" class="error" maxlength="3" >
                                	（分钟）
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">职业测评次数：</label>
                                <div class="col-sm-8">
                                    <input id="surveyTotal" name="surveyTotal" value="${sysConfig.surveyTotal }" class="form-control" type="digits" aria-required="true" aria-invalid="true" class="error" maxlength="3">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">关闭系统提示：</label>
                                <div class="col-sm-8">
                                    <input id="systemPrompt" name="systemPrompt" value="${sysConfig.systemPrompt }" class="form-control" type="text" aria-required="true" aria-invalid="true" class="error">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">更新日期：</label>
                                <div class="col-sm-8">
									<input id="timeMark" name="timeMark" type="text" readonly="readonly" maxlength="20" class="form-control Wdate "
										style="border: 1px solid #e5e6e7;padding: 6px 12px;height: 34px;line-height:1.42857143"
										value="<fmt:formatDate value="${sysConfig.timeMark}" pattern="yyyy-MM-dd HH:mm:ss"/>"
										onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});" required="true"/>
								</div>
                            </div>
                            <%-- <div class="form-group">
                                <label class="col-sm-3 control-label">序列号：</label>
                                <div class="col-sm-8">
                                    <input id="authKey" name="authKey" value="${sysConfig.authKey }" class="form-control" type="text" aria-required="true" aria-invalid="true" class="error">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">smtp地址：</label>
                                <div class="col-sm-8">
                                    <input id="smtpHost" name="smtpHost" value="${sysConfig.smtpHost }" class="form-control" type="text" aria-required="true" aria-invalid="true" class="error">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">smtp用户名：</label>
                                <div class="col-sm-8">
                                    <input id="smtpUser" name="smtpUser" value="${sysConfig.smtpUser }" class="form-control" type="text" aria-required="true" aria-invalid="true" class="error">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">smtp密码：</label>
                                <div class="col-sm-8">
                                    <input id="smtpPwd" name="smtpPwd" value="${sysConfig.smtpPwd }" class="form-control" type="text" aria-required="true" aria-invalid="true" class="error">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">smtp端口：</label>
                                <div class="col-sm-8">
                                    <input id="smtpPort" name="smtpPort" value="${sysConfig.smtpPort }" class="form-control" type="number" aria-required="true" aria-invalid="true" class="error" maxlength="5">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">邮件主题：</label>
                                <div class="col-sm-8">
                                    <input id="mailSubject" name="mailSubject" value="${sysConfig.mailSubject }" class="form-control" type="text" aria-required="true" aria-invalid="true" class="error">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">邮件内容：</label>
                                <div class="col-sm-8">
                                    <input id="mailContent" name="mailContent" value="${sysConfig.mailContent }" class="form-control" type="text" aria-required="true" aria-invalid="true" class="error">
                                </div>
                            </div> --%>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">系统logo：</label>
                                <%-- <div class="col-sm-8">
                                    <form id="nameImage" path="sysConfig.logo" htmlEscape="false" maxlength="255" class="input-xlarge"/>
									<sys:ckfinder input="nameImage" type="images" uploadPath="/jzmk/sysConfig" selectMultiple="false" maxWidth="100" maxHeight="100"/>
                                </div> --%>
                                <div class="col-sm-8">
									<input type="text" name="logo" id="logo" value="${sysConfig.logo}"  readonly="readonly" class="input-mini" />
									<input id="btnShowDialog" class="btn btn-primary" type="button" value="上传"/>
                                </div>
                            </div>
                            <div class="form-group">
								<label class="col-sm-3 control-label"></label>
								<div class="col-sm-8">
									<img alt="logo" id="imgLogo" width="40px" height="60px" border="1px" src="${fileAbsolutePath }" />
								</div>
						    </div>
                            <div class="form-group">
                                <div class="col-sm-8 col-sm-offset-3">
                                  <%--   <button class="btn btn-primary" type="submit">保存</button>&nbsp; --%>
                                    <shiro:hasPermission name="jzmk:sysConfig:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
                                    <!-- <button class="btn btn-primary" onclick="history.go(-1)">返回</button> -->
                                </div>
                            </div>
                        </form:form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <%@ include file="/WEB-INF/views/include/commonlistjs.jsp" %>
	<script src="${hplusStatic }/js/plugins/validate/jquery.validate.min.js"></script>
    <script src="${hplusStatic }/js/plugins/validate/messages_zh.min.js"></script>
    <script src="${hplusStatic }/js/plugins/iCheck/icheck.min.js"></script>
    <script type="text/javascript">
	$(function() {
    	var ajaxFileUpload = function(){
    		layui.use(['layer', 'form'], function(){
    			var layer = layui.layer ,form = layui.form;
	    		var ctx = '${ctx}', 
	    			html = '<form class="layui-form layui-form-pane" action="'+ctx+'/jzmk/sysConfig/upload" method="post">' +
	    			'<div class="layui-form-item">'+
	    				'<label class="layui-form-label">LOGO文件附件：</label>'+
	    	            '<div class="layui-input-inline">' +
	    	            	'<input type="text" class="layui-input file" readonly="readonly" name="certified" value="" />'+
	    	            '</div>'+
	    	            '<input type="file" name="file" style="display:none;" />'+
	    	            '<div class="layui-input-inline">' +
	    	            '<input name="btnImageDialog" class="layui-btn" type="button" value="选择Logo文件图片..."/>'+
	    	            '</div>'+
	    	        '</div>'+
	    	        '</form>';
	    	    layer.open({
	    		    type: 1,
	    		    title: "Logo图片上传",
	    		    shadeClose:true,
	    		    shade: 0.3,
	    		    area: ['580px', '175px'],
	    		    offset: 'c',
	    		    shift: 2,
	    		    scrollbar : false,
	    		    content: html,
	    		    btn:['保存','关闭'],
	    		    success: function(layero, index){
	    		    	layero.find("input[type=button][name=btnImageDialog]").on("click", function(){
	    		    		layero.find("input[type=file][name=file]").click();
	    		      	});
	    		    	layero.find("input[type=file][name=file]").change(function(){
	    					var f = $(this)[0].files[0];
	    					if(f.type !== 'image/jpeg' && f.type !== 'image/png'){
	    						layer.alert('文件类型不匹配请上传image/jpeg、image/png格式文件', {icon: 1});
	    						return;
	    					}else if(f.size > (50*1024*1024)){
	    						layer.alert('文件大小超出' + (50*1024*1024) + 'KB范围', {icon: 1});
	    						return;
	    					}
	    					layero.find("input[type=text][name=certified]").val($(this).val());
	    				});
	    		    },
	    		    yes: function(index, layero){
	    		    	
	    		    	var formData = new FormData();
	    		    	formData.append('file', layero.find("input[type=file][name=file]")[0].files[0]);
	    		    	$.ajax({
	    					url : '${ctx}/jzmk/sysConfig/upload',
	    					type : 'POST',
	    					cache: false,
	    					data: formData,
	    					processData: false,
	    					contentType: false
	    				}).done(function(res) {
	    					//res = JSON.stringify(res);
	    					res = res.replace("\\", "\\\\");
	    					var data = JSON.parse(res); //$.parseJSON(res);
	    					if(data.success){
	    						$("#logo").val(data.message);
	    						console.log('${fileAbsPath}' + data.message);
	    						$("#imgLogo").attr("src", '${fileAbsPath}' + data.message);
	    					}
	    					layer.close(index);
	    				}).fail(function(res) {
	    					
	    				});
	    		    }
	    	    });
    		});
    	};
    	$("#btnShowDialog").on("click", ajaxFileUpload);
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
    	$(".radio").iCheck({checkboxClass:"icheckbox_square-green",radioClass:"iradio_square-green"})
    });
    </script>
</body>
</html>