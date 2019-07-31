<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>咨询师类型管理</title>
	<meta charset="utf-8">
	<meta name="decorator" content="default"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
    <meta name="keywords" content="${fns:getConfig('keywords')} ${sysConfig.schoolName } ">
    <meta name="description" content="${fns:getConfig('description')} ${sysConfig.schoolName }">
  	
  	<link href="${hplusStatic }/favicon.ico" rel="shortcut icon">
	<link href="${hplusStatic }/css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
    <link href="${hplusStatic }/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
    <link href="${hplusStatic }/css/animate.min.css" rel="stylesheet">
    <link href="${hplusStatic }/css/style.min862f.css?v=4.1.0" rel="stylesheet">
    <link href="${hplusStatic }/css/plugins/awesome-bootstrap-checkbox/awesome-bootstrap-checkbox.css" rel="stylesheet">
    <link href="${hplusStatic }/css/plugins/iCheck/custom.css" rel="stylesheet">
	<link href="${serverUrl }css/measure.css" type="text/css" rel="stylesheet" />
	<script type="text/javascript">
		$(document).ready(function() {
			$("#inputForm").validate({
				rules:{
            		"comment":{required: true},
            		"typeName": {required: true}
            	},
            	messages:{
            		comment: {required: "请输入类型描述"},
            		typeName: {required: "请输入教师类型"}
            	},
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
			
			var i = 0, 
	   		modeid = "${counselorType.id}",	
	   		permission = '${counselorType.permission}', 
	   		permissionEl = document.getElementsByName("permission");
	   		if(modeid !== ''){
	   			$("#modeid").val(modeid);
	   		}
	   		
	    	if($("#modeid").val() == 0){
	    		$('#ruleDisplay').css("display", "none");
	    	}
	       	 $("#modeid").on('change',function(){
	           	var val = $(this).val();
	           	if(val !== "0"){
	           		$('#ruleDisplay').css("display", "block");
	           	}else{
	           		$('#ruleDisplay').css("display", "none");
	           	}
	       	});
	       	
	       	for(; i < permissionEl.length; i++){
	       		if(permission.toString().indexOf(permissionEl[i].value) != -1){
	       			permissionEl[i].checked = true;
	       		}
	       	}
	       	$(".i-checks").iCheck({checkboxClass:"icheckbox_square-green",radioClass:"iradio_square-green"})
		});
	</script>
</head>
<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeInRight">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5><a href="${ctx}/ante/counselorType/form?id=${counselorType.id}">教师类型信息<shiro:hasPermission name="ante:counselorType:edit">${not empty counselorType.id?'修改':'新增'}</shiro:hasPermission><shiro:lacksPermission name="ante:counselorType:edit">查看</shiro:lacksPermission></a></h5>
                    </div>
                    <div class="ibox-content">
						<form:form id="inputForm" modelAttribute="counselorType" action="${ctx}/ante/counselorType/save" method="post" class="form-horizontal">
							<form:hidden path="id"/>
							<sys:message content="${message}"/>		
							<div class="form-group">
								<label class="col-sm-3 control-label">咨询师类型：</label>
								<div class="col-sm-8">
									<form:input path="typeName" htmlEscape="false" maxlength="20" required="true" class="input-xlarge " />
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">类型描述：</label>
								<div class="col-sm-8">
									<form:textarea path="comment" htmlEscape="false" rows="4" maxlength="255" required="true" class="input-xxlarge "/>
								</div>
							</div>
							<div class="form-group">
								 <label class="col-sm-3 control-label">类型权限：</label>
					             <div class="col-sm-8">
					             	<div class="form-group">
					                 	<label class="col-sm-3 control-label">接待室</label>
					                 	<div class="col-sm-8">
					                 		 <div class="i-checks">
					                 		 	<label><input type="checkbox" name="permission" value="J-0"> <i></i> 公益活动</label>
					                 		 	<label><input type="checkbox" name="permission" value="J-1"> <i></i> 系统常规设置</label>
					                 		 	<label><input type="checkbox" name="permission" value="J-2"> <i></i> 来访者信息管理</label>
					                 		 	<label><input type="checkbox" name="permission" value="J-3"> <i></i> 个人信息管理</label>
					                 		 	<label><input type="checkbox" name="permission" value="J-4"> <i></i> 家属管理</label>
					                 		 	<label><input type="checkbox" name="permission" value="J-5"> <i></i> 教师管理</label>
					                 		 	<label><input type="checkbox" name="permission" value="J-6"> <i></i> 即时信息管理</label>
					                         </div>
					                 	</div>
					                 </div>
					                 <div class="form-group">
					                 	<label class="col-sm-3 control-label">测量室</label>
					                 	<div class="col-sm-8">
					                 		 <div class="i-checks">
					                 		 	<label><input type="checkbox" name="permission" value="C-0"> <i></i> 量表管理</label>
					                 		 	<label><input type="checkbox" name="permission" value="C-1"> <i></i> 测评管理</label>
					                 		 	<label><input type="checkbox" name="permission" value="C-2"> <i></i> 自我测评管理</label>
					                         </div>
					                 	</div>
					                 </div>
					                 <div class="form-group">
					                 	<label class="col-sm-3 control-label">咨询室</label>
					                 	<div class="col-sm-8">
					                 		 <div class="i-checks">
					                 		 	<label><input type="checkbox" name="permission" value="Z-0"> <i></i> 咨询师管理</label>
					                 		 	<label><input type="checkbox" name="permission" value="Z-1"> <i></i> 咨询师审核</label>
					                 		 	<label><input type="checkbox" name="permission" value="Z-2"> <i></i> 问题类型管理</label>
					                         </div>
					                 	</div>
					                 </div>
					                 <div class="form-group">
					                 	<label class="col-sm-3 control-label">档案室</label>
					                 	<div class="col-sm-8">
					                 		 <div class="i-checks">
					                 		 	<label><input type="checkbox" name="permission" value="D-0"> <i></i> 档案查询</label>
					                 		 	<label><input type="checkbox" name="permission" value="D-1"> <i></i> 档案生成</label>
					                         </div>
					                 	</div>
					                 </div>
					                  <div class="form-group">
					                 	<label class="col-sm-3 control-label">心理课堂</label>
					                 	<div class="col-sm-8">
					                 		 <div class="i-checks">
					                 		 	<label><input type="checkbox" name="permission" value="X-0"> <i></i> 课件分类</label>
					                 		 	<label><input type="checkbox" name="permission" value="X-1"> <i></i> 课件管理</label>
					                         </div>
					                 	</div>
					                 </div>
					                  <div class="form-group">
					                 	<label class="col-sm-3 control-label">放松室</label>
					                 	<div class="col-sm-8">
					                 		 <div class="i-checks">
					                 		 	<label><input type="checkbox" name="permission" value="F-0"> <i></i> 成功案例</label>
					                 		 	<label><input type="checkbox" name="permission" value="F-1"> <i></i> 心灵咖啡屋</label>
					                         	<label><input type="checkbox" name="permission" value="F-2"> <i></i> 一吐为快</label>
					                         	<label><input type="checkbox" name="permission" value="F-3"> <i></i> 放松音乐</label>
					                         </div>
					                 	</div>
					                 </div>
					               </div>
							</div>
							<div class="form-actions">
								<shiro:hasPermission name="ante:counselorType:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
								<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
							</div>
						</form:form>
					</div>
				</div>
			</div>
		</div>
	</div>
	
</body>
</html>