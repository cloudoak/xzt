<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<!-- Mirrored from www.zi-han.net/theme/hplus/form_validate.html by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 20 Jan 2016 14:19:15 GMT -->
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>答疑室问题管理</title>
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
                        <h5>答疑室</h5>
                        <div class="ibox-tools">
                            <!--
<a class="text-navy" href="${ctx}/sys/org/form?id=${office.id}&parent.id=${office.parent.id}">咨询室查看</a>
							<a class="collapse-link">
                                <i class="fa fa-chevron-up"></i>
                            </a>
                            <a class="dropdown-toggle" data-toggle="dropdown" href="form_basic.html#">
                                <i class="fa fa-wrench"></i>
                            </a>
                            <a class="close-link">
                                <i class="fa fa-times"></i>
                            </a>
							<shiro:hasPermission name="sys:office:edit">${not empty office.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="sys:office:edit">查看</shiro:lacksPermission>
							-->
                        </div>
                    </div>
<!--
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/counsel/counselQuestion/">答疑室问题列表</a></li>
		<li class="active"><a href="${ctx}/counsel/counselQuestion/form?id=${counselQuestion.id}">答疑室问题<shiro:hasPermission name="counsel:counselQuestion:edit">${not empty counselQuestion.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="counsel:counselQuestion:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	-->
	
	
	<div class="ibox-content">
                        <form:form class="form-horizontal m-t" id="inputForm" 
						modelAttribute="counselQuestion"
						action="${ctx}/counsel/counselQuestion/save" method="post">
                            <input type="hidden" name="id" value="${counselQuestion.id }">
							
							<input type="hidden" id="askId" name="askId" value="${counselQuestion.askId }">
							<input type="hidden" id="askName" name="askName" value="${counselQuestion.askName }">
							
							<input type="hidden" id="counselorId" name="counselorId" value="${counselQuestion.counselorId }">
							<input type="hidden" id="counselorName" name="counselorName" value="${counselQuestion.counselorName }">
							
                            <div class="form-group">
                                <label class="col-sm-3 control-label">学员：</label>
                                <div class="col-sm-8">
                                    <!--
									<input id="name" name="name" value="${office.name }" class="form-control" type="text" required="required">
									-->
									<select id="visitorInfoSelect"  class="required">
									</select>
                                    <span class="help-block m-b-none"><i class="fa fa-info-circle"></i> 学员名称（必填）</span>
                                </div>
                            </div>
							
							  <div class="form-group">
                                <label class="col-sm-3 control-label">咨询师：</label>
                                <div class="col-sm-8">
									<select id="counselorSelect"  class="required">
									</select>
									
                                    <!--
									<input id="username" value="${office.user.loginName }" name="user.loginName" class="form-control" type="text" aria-required="true" aria-invalid="true" class="error">
									-->
                                </div>
                            </div>
							
							
							<div class="form-group">
								<label class="col-sm-3 control-label">咨询问题类型：</label>
								<div class="col-sm-4">
									<form:select path="questionType" class="input-xlarge ">
										<!-- <form:option value="" label=""/>  -->
										<form:options items="${fns:getDictList('question_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
									</form:select>
								</div>
							</div>
							
							<div class="form-group">
                                <label class="col-sm-3 control-label">问题标题：</label>
                                <div class="col-sm-8">								
									<form:input path="title" htmlEscape="false" maxlength="32" class="form-control"  />
                                </div>
                            </div>
							
							<div class="form-group">
                                <label class="col-sm-3 control-label">问题内容：</label>
                                <div class="col-sm-8">
									<form:textarea path="content" htmlEscape="false" rows="4" maxlength="128" class="form-control" type="text"/>
                                </div>
                            </div>
							<div class="form-group">
                                <div class="col-sm-8 col-sm-offset-3">
									<shiro:hasPermission name="counsel:counselQuestion:edit">
									<input id="btnSubmit" class="btn btn-primary" type="submit" value="保存"/>&nbsp;</shiro:hasPermission>
									<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
                                </div>
                            </div>
                        </form:form>
                    </div>
                </div>
            </div>
        </div>
    </div>
	
	
</body>
<script src="${hplusStatic }/js/jquery.min.js?v=2.1.4"></script>
  
    </script>
    <script src="${hplusStatic }/js/bootstrap.min.js?v=3.3.6"></script>
    <script src="${hplusStatic }/js/content.min.js?v=1.0.0"></script>
    <script src="${hplusStatic }/js/plugins/validate/jquery.validate.min.js"></script>
    <script src="${hplusStatic }/js/plugins/validate/messages_zh.min.js"></script>
    <script src="${hplusStatic }/js/demo/form-validate-demo.min.js"></script>
    <script src="http://tajs.qq.com/stats?sId=9051096" type="text/javascript" charset="UTF-8"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					//学员 选择
					var askId = $("#visitorInfoSelect").find("option:selected").attr("id");
					var askName = $("#visitorInfoSelect").find("option:selected").attr("title");
					if(null == askId || askId ==''){
						return ;
					}
					$("#askId").val(askId);
					$("#askName").val(askName);
					//咨询师 选择
					var counselorId = $("#counselorSelect").find("option:selected").attr("id");
					var counselorName = $("#counselorSelect").find("option:selected").attr("title"); 
					if(null == counselorId || counselorId ==''){
						return ;
					}
					$("#counselorId").val(counselorId);
					$("#counselorName").val(counselorName);
					
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
			
			//获取 学员 ajax						
			
			$.ajax({
				url:"${ctx}/counsel/counselQuestion/getVisitorInfoList",
				type:"POST",
				dataType:"json",
				data:{"parent":0},
				success:function(data){
					//alert(data);
					for (i = 0; i < data.length; i++){
						var visitorInfo = data[i];
						var visitorId = $('#askId').val();
						var option = "";
						if(visitorId != null && visitorId==visitorInfo["id"] ){
							//alert("");
							option = 						
							"<option id='"+visitorInfo["id"]+"' title='"+visitorInfo["realName"]+"' value='"+visitorInfo["id"]+"'  selected='true' >"+visitorInfo["realName"]+"</option>";
						}else{
							option =  
							"<option id='"+visitorInfo["id"]+"' title='"+visitorInfo["realName"]+"' value='"+visitorInfo["id"]+"'>"+visitorInfo["realName"]+"</option>";
						}
						
						$('#visitorInfoSelect').append(option);
						
					}
				}
			});
			//获取 咨询师 ajax 
			
			$.ajax({
				url:"${ctx}/counsel/counselor/getCounselorList",
				type:"POST",
				dataType:"json",
				data:{"parent":0},
				success:function(data){
					//alert(data);
					for (i = 0; i < data.length; i++){
						var counselor = data[i];
						var counselorId = $('#counselorId').val();
						var option = "";
						if(counselorId != null && counselorId==counselor["id"] ){
							//alert("");
							option =  
							"<option id='"+counselor["id"]+"' title='"+counselor["userName"]+"' value='"+counselor["id"]+"' selected='true' >"+counselor["userName"]+"</option>";
						}else{
							option =  
							"<option id='"+counselor["id"]+"' title='"+counselor["userName"]+"' value='"+counselor["id"]+"'>"+counselor["userName"]+"</option>";
						}
						//);
						$('#counselorSelect').append(option);
					}
				}
			});
			
			
			
			
		});
	</script>
	
</html>