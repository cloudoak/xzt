<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>咨询师设置</title>
    <meta name="keywords" content="${fns:getConfig('keywords')} ${sysConfig.schoolName } ">
    <meta name="description" content="${fns:getConfig('description')} ${sysConfig.schoolName }">
    <link href="${hplusStatic }/favicon.ico" rel="shortcut icon">
	<link href="${hplusStatic }/css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
    <link href="${hplusStatic }/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
    <link href="${hplusStatic }/css/animate.min.css" rel="stylesheet">
    <link href="${hplusStatic }/css/style.min862f.css?v=4.1.0" rel="stylesheet">
    <link href="${hplusStatic }/css/plugins/iCheck/custom.css" rel="stylesheet">
	<script src="${hplusStatic }/js/jquery.min.js?v=2.1.4"></script>
	<script src="${hplusStatic }/js/plugins/layer/layer.min.js" type="text/javascript"></script>
	<script src="${hplusStatic }/js/plugins/iCheck/icheck.min.js"></script>
</head>
<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5><a class="text-navy" href="${ctx}/counsel/counselor/form?id=${counselor.id}">咨询师设置</a></h5>
                        <div class="ibox-tools">
                        </div>
                    </div>
				<div class="ibox-content"> 
				<form id="counselorForm" class="form-horizontal m-t" action="${ctx}/counsel/counselor/save" method="post" >		
					<sys:message content="${message}"/>	
					<input type="hidden" name="id" value="${ counselor.id }" />
					<input type="hidden" name="applyStatus" value="${ counselor.applyStatus }" />
					<div class="form-group">
						<label class="col-sm-3 control-label">咨询师：</label>
						<div class="col-sm-8">
							${ counselor.user.name }
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label">资格证书：</label>
						<div class="col-sm-8">
							<input type="text" name="certificatePath" id="certificatePath" value="${counselor.certificatePath}"  readonly="readonly" class="input-mini" />
							<input id="btnShowDialog" class="btn btn-primary" type="button" value="上传"/>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label"></label>
						<div class="col-sm-8">
							<img alt="资格证书" id="imgCertificatePath" width="40px" height="60px" border="1px" src="${fileAbsolutePath }" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label">是否推荐到首页：</label>
                         <div class="col-md-4">
                         <fieldset>
                         <div class="i-checks">
                             <div class="radio radio-info radio-inline">
                                 <input type="radio" id="indexFlag1" value="1" name="indexFlag" ${empty counselor.indexFlag ? "checked" : (counselor.indexFlag==1?"checked":"") } >
                                 <label for="indexFlag1">是</label>
                             </div>
                             <div class="radio radio-inline">
                                 <input type="radio" id="indexFlag2" value="0" name="indexFlag" ${counselor.indexFlag==0?"checked":"" } >
                                 <label for="indexFlag2">否</label>
                             </div>
                         </div>
                         </fieldset>
                         </div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label">是否内部咨询师：</label>
                        <div class="col-md-4">
						<fieldset>
						<div class="i-checks">
                             <div class="radio radio-info radio-inline">
                                 <input type="radio" id="insideFlag1" value="1" name="insideFlag" ${empty counselor.insideFlag ? "checked" : (counselor.insideFlag==1?"checked":"") } >
                                 <label for="insideFlag1">否</label>
                             </div>
                             <div class="radio radio-inline">
                                 <input type="radio" id="insideFlag2" value="0" name="insideFlag" ${counselor.insideFlag==0?"checked":"" } >
                                 <label for="insideFlag2">是</label>
                             </div>
                         </div>
                         </fieldset>
                         </div>
					</div>
					<div class="form-group">
			            <label class="col-sm-3 control-label">描述：</label>
			            <div class="col-sm-8">     
			            <textarea name="instro" rows="4" cols="10" maxlength="128" class="form-control">${ counselor.instro }</textarea>
			            </div>
			        </div>	
					<div class="form-group">
					 <div class="col-sm-8 col-sm-offset-3">
						<shiro:hasPermission name="counsel:counselor:edit">
							<input id="btnSubmit" class="btn btn-primary" type="submit" value="保存"/>&nbsp;&nbsp;
						</shiro:hasPermission>
						<input id="btnCancel" class="btn" type="button" value="返 回" />
					</div>
					</div>
				</form>
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
<script type="text/javascript">
	var ajaxFileUpload = function(){
		var ctx = '${ctx}', 
			html = '<form  class="layui-form" action="'+ctx+'/counsel/counselor/upload" method="post">' +
			'<div class="layui-form-item">'+
				'<label class="layui-form-label">资质文件附件：</label>'+
	            '<div class="layui-input-block">'+
	            	'<input type="text" class="layui-input file" readonly="readonly" name="certified" value="" />'+
	                '<input type="file" name="file" style="display:none;" />'+
	                '<input name="btnImageDialog" class="btn btn-primary" type="button" value="选择资质文件图片..."/>'+
	            '</div>'+
	        '</div>'+
	        '</form>';
	    layer.open({
		    type: 1,
		    title: "资质文件附件上传",
		    shadeClose:true,
		    shade: 0.3,
		    area: ['540px', '175px'],
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
					url : '${ctx}/counsel/counselor/upload',
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
						$("#certificatePath").val(data.message);
						$("#imgCertificatePath").attr("src", '${fileAbsPath}' + data.message);
					}
					layer.close(index);
				}).fail(function(res) {
					
				});
		    }
	    });
	};
	$(function() {
		$("#btnCancel").on("click", function(){
      		location.href = "${ctx}/counsel/counselor/list";
      	});
		$("#btnShowDialog").on("click", ajaxFileUpload);
		$("#counselorForm").validate({
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
		
		$(".i-checks").iCheck({checkboxClass:"icheckbox_square-green",radioClass:"iradio_square-green"})
	});
</script>
</html>