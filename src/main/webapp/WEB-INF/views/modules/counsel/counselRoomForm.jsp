<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<!-- Mirrored from www.zi-han.net/theme/hplus/form_validate.html by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 20 Jan 2016 14:19:15 GMT -->
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>咨询室管理</title>
    <meta name="keywords" content="${fns:getConfig('keywords')} ${sysConfig.schoolName } ">
    <meta name="description" content="${fns:getConfig('description')} ${sysConfig.schoolName }">
    
	<%@ include file="/WEB-INF/views/include/commoncss.jsp" %>
	<!--
    <link href="${hplusStatic }/favicon.ico" rel="shortcut icon">
	<link href="${hplusStatic }/css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
    <link href="${hplusStatic }/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
    <link href="${hplusStatic }/css/animate.min.css" rel="stylesheet">
    <link href="${hplusStatic }/css/style.min862f.css?v=4.1.0" rel="stylesheet">
	-->
</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5><a class="text-navy" href="${ctx}/counsel/counselRoom/list">
						咨询室<shiro:hasPermission name="counsel:counselRoom:edit">${not empty counselRoom.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="sys:office:edit">查看</shiro:lacksPermission></a></h5>
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
<!--
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/counsel/counselRoom/">咨询室列表</a></li>
		<li class="active"><a href="${ctx}/counsel/counselRoom/form?id=${counselRoom.id}">咨询室<shiro:hasPermission name="counsel:counselRoom:edit">${not empty counselRoom.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="counsel:counselRoom:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	
	<form:form id="inputForm" modelAttribute="counselRoom" action="${ctx}/counsel/counselRoom/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">咨询室名称：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="32" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">咨询师：</label>
			<div class="controls">
				<form:input path="counselorId" htmlEscape="false" maxlength="11" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">咨询室状态：</label>
			<div class="controls">
				<form:select path="status" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('counsel_room_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">图片地址：</label>
			<div class="controls">
				<form:hidden id="photoPath" path="photoPath" htmlEscape="false" maxlength="32" class="input-xlarge"/>
				<sys:ckfinder input="photoPath" type="files" uploadPath="/counsel/counselRoom" selectMultiple="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">说明：</label>
			<div class="controls">
				<form:textarea path="intro" htmlEscape="false" rows="4" maxlength="128" class="input-xxlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">咨询中心标识：</label>
			<div class="controls">
				<form:input path="counselCenterId" htmlEscape="false" maxlength="11" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">分校id：</label>
			<div class="controls">
				<form:input path="orgId" htmlEscape="false" maxlength="11" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="counsel:counselRoom:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
	-->
	 <div class="ibox-content">
          <form:form class="form-horizontal m-t" id="inputForm" action="${ctx}/counsel/counselRoom/save" 
		  modelAttribute="counselRoom"
		  method="post">
		  <form:hidden path="id"/>
		  <input type="hidden" id="counselorId" name="counselorId" value="${counselRoom.counselorId }">
		 <input type="hidden" id="counselorName" name="counselorName" value="${counselRoom.counselorName }">
		<sys:message content="${message}"/>	
            <div class="form-group">
                      <label class="col-sm-3 control-label">咨询室名称：</label>
                      <div class="col-sm-8">
					   <form:input path="name" htmlEscape="false" maxlength="32" class="form-control"  required="required"/>
                                    <span class="help-block m-b-none"><i class="fa fa-info-circle"></i> 咨询室名称必须是唯一的（必填）</span>
                      </div>
            </div>
			
			 <div class="form-group">
                        <label class="col-sm-3 control-label">咨询师：</label>
                        <div class="col-sm-8">
                        <select id="counselorSelect" >
						</select>						
                        </div>
            </div>
			
			<div class="form-group">
                                <label class="col-sm-3 control-label">咨询室状态：</label>
                                <div class="col-sm-8">
                                    <form:select path="status" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('counsel_room_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
                                </div>
              </div>
	
			 <div class="form-group">
                        <label class="col-sm-3 control-label">介绍：</label>
                        <div class="col-sm-8">
                       <form:textarea path="intro" htmlEscape="false" rows="4" maxlength="128" class="form-control"/>						
                        </div>
            </div>
			 <div class="form-group">
                        <label class="col-sm-3 control-label">图片：</label>
                        <div class="col-sm-8">
                       <form:hidden id="photoPath" path="photoPath" htmlEscape="false" maxlength="32" class="input-xlarge"/>
				<sys:ckfinder input="photoPath" type="files" uploadPath="/counsel/counselRoom" selectMultiple="true"/>					
                        </div>
            </div>
			
			  <div class="form-group">
                                <div class="col-sm-8 col-sm-offset-3">
								<shiro:hasPermission name="counsel:counselRoom:edit">
								<button class="btn btn-primary" type="submit">保存</button>&nbsp;</shiro:hasPermission>                                    
                                    <button class="btn btn-primary" onclick="history.go(-1)">返回</button>
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
					//咨询师 选择
					var counselorId = $("#counselorSelect").find("option:selected").attr("id");
					var counselorName = $("#counselorSelect").find("option:selected").attr("title"); 
					if(null == counselorId || counselorId ==''){
						return ;
					}
					//alert(counselorName);
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