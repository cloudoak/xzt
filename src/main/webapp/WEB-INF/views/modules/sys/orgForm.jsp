<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <title>机构管理</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<%@ include file="/WEB-INF/views/include/commoncss.jsp" %>
	<link href="${hplusStatic }/js/plugins/layui/css/layui.css" rel="stylesheet">
	<link href="${hplusStatic }/js/plugins/layui/css/layui.mobile.css" rel="stylesheet">
</head>
<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5><a class="text-navy" href="${ctx}/sys/org/form?id=${office.id}&parent.id=${office.parent.id}">机构<shiro:hasPermission name="sys:office:edit">${not empty office.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="sys:office:edit">查看</shiro:lacksPermission></a></h5>
                        <div class="ibox-tools">
                        </div>
                    </div>
                    <div class="ibox-content">
                        <form id="inputForm" modelAttribute="office" class="form-horizontal m-t" action="${ctx}/sys/org/saveOrg" method="post">
                            <input type="hidden" id="id" name="id" value="${office.id }">
                            <input type="hidden" id="parentId" name="parentId" value="${empty office.parentId ? 0 : office.parentId}">
                            <sys:message content="${message}"/>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">机构名称：</label>
                                <div class="col-sm-8">
                                    <input id="name" name="name" value="${office.name }" class="form-control" type="text" required="required" maxlength="30">
                                    <span class="help-block m-b-none"><i class="fa fa-info-circle"></i> 机构名称必须是唯一的（必填）</span>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">页面标题：</label>
                                <div class="col-sm-8">
                                    <input id="title" value="${office.title }" name="title" class="form-control" type="text" maxlength="30">
                                </div>
                            </div>
                            <div class="form-group form-inline">
                                <label class="col-sm-3 control-label">Logo图标：</label>
                                <div class="col-sm-8">
                                <input type="text" name="logo" id="logo" value="${office.logo}"  readonly="readonly" class="form-control" />
                                <input id="btnShowDialog" class="btn btn-primary" type="button" value="上传"/>
                                </div>
                            </div>
                            <div class="form-group">
								<label class="col-sm-3 control-label"></label>
								<div class="col-sm-8">
									<img alt="logo" id="imgLogo" class="img-thumbnail" src="${fileAbsolutePath }" />
								</div>
						    </div>
                            <div class="form-group">
                                <div class="col-sm-8 col-sm-offset-3">
                                	<shiro:hasPermission name="sys:office:edit">
                                    <input class="btn btn-primary" type="submit" value="保存"/>&nbsp;
                                    </shiro:hasPermission>
                                    <input class="btn" type="button" onclick="gotoPage();" value="返回"/>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
	<%@ include file="/WEB-INF/views/include/commonlistjs.jsp" %>
	<script src="${hplusStatic }/js/plugins/layui/layui.all.js" type="text/javascript"></script>
	<script>
		$(function() {
			$("#btnShowDialog").on("click", ajaxFileUpload);  
		});
		var ajaxFileUpload = function(){
			var ctx = '${ctx}', 
			content = '<form class="form-horizontal m-t" action="'+ctx+'/sys/org/upload" method="POST">'+
			'<input type="file" name="file" style="display:none;" />'+
			'<div class="form-group">'+
			'<label for="annex" class="col-sm-4 control-label">组织Logo图片附件</label>'+  
			'<div class="col-sm-6">'+
			'<div class="input-append">组织Logo图片附件'+
			  '<input id="annex" name="certified" type="text" class="span2" style="border: 1px solid #ccc;padding: 6px 0px;" readonly="readonly" placeholder="请选择图片...">'+
			  '<button name="btnImageDialog" class="btn btn-default" type="button">选择</button>'+
			'</div>'+
			'</div>'+
			'</div>'+
			'</form>';
		    layer.open({
			    type: 1,
			    title: "组织Logo图片上传",
			    shadeClose:true,
			    resize: false,
			    move: false,
			    /* shade: 0.3, */
			    shade: [0.8, '#393D49'],
			    offset: 'auto',
			    scrollbar: false,
			    resize: false,
			    area: ['500px', '235px'],
			   /*  offset: 'c',
			    shift: 2, */
			    content: content,
			    btn:['保存','关闭'],
			    success: function(layero, index){
			    	layero.find("button[type=button][name=btnImageDialog]").on("click", function(){
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
						url : '${ctx}/sys/org/upload',
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
							$("#imgLogo").attr("src", '${fileAbsPath}' + data.message);
						}
						layer.close(index);
					}).fail(function(res) {
						
					});
			    }
		    });
		};
		$("#inputForm").validate({
    		errorElement: 'div',
    		errorClass: 'help-block',
    		focusInvalid: false,
    		rules: {
  		    		name: {
  		    			remote: {
  			    		    url: "${ctx}/sys/org/checkOrg",     //后台处理程序
  			    		    type: "post",               //数据发送方式
  			    		    dataType: "json",           //接受数据格式   
  			    		    data: {                     //要传递的数据
  			    		            name: function() {
  			    		            	return $("#name").val();
  			    		        	},
  			    		        	id:function(){
  			    		        		return $("#id").val();
  			    		        	}
  			    		    }
  			    		},
  			    		required:{
  			    			required:true
  			    		}
  		    		}
  		    	},messages: {
  		    		name: {
  		    			remote: "机构已存在！",
  		    			required:"机构名称必填"
  		    		}
  		    	},
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
		function gotoPage()
		{
			location.href="${ctx}/sys/org/list";
		}
	</script>
</body>
</html>