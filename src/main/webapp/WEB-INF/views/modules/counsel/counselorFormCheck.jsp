<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<!-- Mirrored from www.zi-han.net/theme/hplus/form_validate.html by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 20 Jan 2016 14:19:15 GMT -->
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

   <title>咨询师管理</title>
    <meta name="keywords" content="${fns:getConfig('keywords')} ${sysConfig.schoolName } ">
    <meta name="description" content="${fns:getConfig('description')} ${sysConfig.schoolName }">
    
    <link href="${hplusStatic }/favicon.ico" rel="shortcut icon">
	<link href="${hplusStatic }/css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
    <link href="${hplusStatic }/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
    <link href="${hplusStatic }/css/animate.min.css" rel="stylesheet">
    <link href="${hplusStatic }/css/style.min862f.css?v=4.1.0" rel="stylesheet">
	<script src="${hplusStatic }/js/jquery.min.js?v=2.1.4"></script>
</head>
<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5><a class="text-navy" href="${ctx}/counsel/counselor/form?id=${counselor.id}">咨询师申请</a></h5>
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
	
	<form:form id="inputForm" class="form-horizontal m-t"  modelAttribute="counselor" action="${ctx}/counsel/counselor/check" method="post" >
		<form:hidden path="id" value="${counselor.id}"/>
		<form:hidden path="applyStatus" value="${applyStatus}"/>
		<form:hidden path="isCheck" value="1"/>
		<sys:message content="${message}"/>		
		<div class="form-group">
			<label class="col-sm-3 control-label">老师姓名：</label>
			<div class="col-sm-4">
				 ${counselor.userName}
				<!--
			<sys:treeselect id="user" name="user.id" value="${counselor.user.id}" labelName="user.name" labelValue="${counselor.user.name}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="" allowClear="true" notAllowSelectParent="true"/>
				onChange="provinceChange();" <option>请选择</option>--
				-->
					
			</div>
			  <span class="help-block m-b-none"><i class="fa fa-info-circle"></i> 老师必选（必填）</span>
		</div>
		<!-- 
		<div class="form-group">
			<label class="col-sm-3 control-label">老师姓名：</label>
			<div class="col-sm-8">
				<form:input path="userName" htmlEscape="false" maxlength="32" class="input-xlarge "/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label">性别：</label>
			<div class="col-sm-8">
				<form:select path="sex" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('sex')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label">年龄：</label>
			<div class="col-sm-8">
				<form:input path="age" htmlEscape="false" maxlength="6" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label">认证类型：</label>
			<div class="col-sm-8">
				<form:select path="authType" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('counselor_auth_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		
		<input id="username" value="${office.user.loginName }" name="user.loginName"  type="text" aria-required="true" aria-invalid="true" class="error">
		 -->
		 <div class="form-group">
               <label class="col-sm-3 control-label">描述：</label>
                  <div class="col-sm-8">                     
					 <form:textarea path="instro" htmlEscape="false" rows="4" maxlength="128" class="form-control" />
                  </div>
          </div>
					
		<div class="form-group">
			<label class="col-sm-3 control-label">资格证书：</label>
			<div class="col-sm-8">
				<form:hidden id="certificatePath" path="certificatePath" htmlEscape="false" maxlength="32" />
				<sys:ckfinder input="certificatePath" type="files" uploadPath="/counsel/counselor" selectMultiple="true"/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label">照片：</label>
			<div class="col-sm-8">
				<form:hidden id="photopPath" path="photopPath" htmlEscape="false" maxlength="32" />
				<sys:ckfinder input="photopPath" type="files" uploadPath="/counsel/counselor" selectMultiple="true"/>
			</div>
		</div>
		<!-- 认证状态 默认为提交
		<div class="form-group">
			<label class="col-sm-3 control-label">认证状态：</label>
			<div class="col-sm-8">
				<form:select path="applyStatus" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('counselor_apply_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		-->
		
		<div class="form-group">
			<label class="col-sm-3 control-label">是否推荐到首页：</label>
			<!--
			<div class="col-sm-8">
			-->
			<div class="col-sm-8">
				<form:radiobuttons path="indexFlag" items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false" class="required"/>
				<!-- 
				<form:select path="indexFlag" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				-->
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label">在职状态：</label>
			<div class="col-sm-8">
				<form:radiobuttons path="onjobFlag" items="${fns:getDictList('counselor_onjob_flag')}" itemLabel="label" itemValue="value" htmlEscape="false" class="required"/>
				<!-- 
				<form:select path="onjobFlag" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('counselor_onjob_flag')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				-->
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label">是否内部：</label>
			<div class="col-sm-8">
			<form:radiobuttons path="insideFlag" items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false" class="required"/>
				<!-- 
				<form:select path="insideFlag" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				 -->
			</div>
		</div>
		
		<div class="form-group">
		 <div class="col-sm-8 col-sm-offset-3">
			<shiro:hasPermission name="counsel:counselor:edit">
				<input id="btnSubmit" class="btn btn-primary" type="submit" value="审核通过"/>&nbsp;&nbsp;
			</shiro:hasPermission>
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
					/*
					var id = $("#counselorSelect").find("option:selected").attr("id");
					if(null == id || id ==''){
						return ;
					}
					*/
					$("#id").val(id);
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
			
			/*
			$(function(){ 
			$.ajax({
				url:"${ctx}/counsel/counselor/getCounselorNotApply",
				type:"POST",
				dataType:"json",
				data:{"parent":0},
				success:function(data){
					//alert(data);
					for (i = 0; i < data.length; i++){
						var counselor = data[i];
						$('#counselorSelect').append("<option id='"+counselor["id"]+"' title='"+counselor["userName"]+"' value='"+counselor["id"]+"'>"+counselor["userName"]+"</option>");
					}
				}
			});
			*/
			})
			
		});
</script>

</html>