<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${sysConfig.schoolName } 机构--课件管理</title>
    <meta name="keywords" content="${fns:getConfig('keywords')} ${sysConfig.schoolName } ">
    <meta name="description" content="${fns:getConfig('description')} ${sysConfig.schoolName }">
	<%@ include file="/WEB-INF/views/include/commoncss.jsp" %>
	<%@ include file="/WEB-INF/views/include/commonformjs.jsp" %>
	<link href="${hplusStatic }/css/plugins/iCheck/custom.css" rel="stylesheet">
</head>
<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>课件信息</h5>
                        <div class="ibox-tools">
                        </div>
					</div>
					<div class="ibox-content">
                    <form class="form-horizontal m-t" id="inputForm" action="${ctx}/course/course/save" method="post">
                        <input type="hidden" name="id" value="${course.id }">
						<div class="form-group">
                            <label class="col-sm-3 control-label">课件名称：</label>
                            <div class="col-sm-8">								
							<input name="name" maxlength="16" class="form-control" value="${course.name }" type="text" />
                            </div>
                        </div>
						<div class="form-group">
							<label class="col-sm-3 control-label">课件类别：</label>
							<div class="col-sm-8">
								<select id="courseCatalogId" name="courseCatalogId" class="form-control">
									<c:forEach items="${courseCatelogList}" var="tpl">
									<option value="${tpl.id}" ${tpl.id==course.courseCatalogId?"selected":""}>${tpl.name}</option>	
      								</c:forEach>
								</select>
							</div>
						</div>				
						<div class="form-group">
						<label class="col-sm-3 control-label">是否公开：</label>
						<div class="col-sm-8">
							<div class="i-checks">
							<fieldset>
							<c:forEach items="${fns:getDictList('yes_no')}" var="item" varStatus="status">
								<div class="radio radio-info radio-inline">
                                     <input type="radio" id="isPublic${status.index}" value="${item.value }" name="isPublic" ${empty course.isPublic ? (item.value == 1 ? "checked" :"") : (course.isPublic==item.value?"checked":"") } >
                                     <label for="isPublic${status.index}">${item.label }</label>
                                 </div>
							</c:forEach>
							</fieldset>
	                        </div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label">课件文件：</label>
						<div class="col-sm-8">
							<input type="text" name="path" id="path" value="${course.path}"  readonly="readonly" class="input-mini" />
							<input id="btnShowDialog" class="btn btn-primary" type="button" value="上传"/>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label"></label>
						<div class="col-sm-8">
							<a id="courseDetails" class="text-navy" title="${course.name }" target="_blank" href="${fileAbsolutePath }">${course.path}</a>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label">描述：</label>
						<div class="col-sm-8">
							<textarea name="intro" rows="4" cols="128" class="form-control">${course.intro }</textarea>
						</div>
					</div>               
					<div class="form-group">
                        <div class="col-sm-8 col-sm-offset-3">
						<shiro:hasPermission name="course:course:edit">
						<input id="btnSubmit" class="btn btn-primary" type="submit" value="保存"/>&nbsp;
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
<script src="${ctxStatic}/My97DatePicker/WdatePicker.js" ></script>
<script src="${hplusStatic }/js/jquery.min.js?v=2.1.4"></script>
<script src="${hplusStatic }/js/bootstrap.min.js?v=3.3.6"></script>
<script src="${hplusStatic }/js/content.min.js?v=1.0.0"></script>
<script src="${hplusStatic }/js/plugins/validate/jquery.validate.min.js"></script>
<script src="${hplusStatic }/js/plugins/validate/messages_zh.min.js"></script>
<script src="${hplusStatic }/js/plugins/iCheck/icheck.min.js"></script>
<script type="text/javascript">
		var courseCatalogId = "${course.courseCatalogId}";
		$(function() {
			$("#btnCancel").on("click", function(){
				location.href = "${ctx}/course/course/list";
			});
			if(courseCatalogId){
				$("#courseCatalogId").val(courseCatalogId);
			}
			var ajaxFileUpload = function(){
				var ctx = '${ctx}', 
					html = '<form  class="layui-form" action="'+ctx+'/counse/counse/upload" method="post">' +
					'<div class="layui-form-item">'+
						'<label class="layui-form-label">课件文件附件：</label>'+
			            '<div class="layui-input-block">'+
			            	'<input type="text" class="layui-input file" readonly="readonly" name="coursePath" value="" />'+
			                '<input type="file" name="file" style="display:none;" />'+
			                '<input name="btnImageDialog" class="btn btn-primary" type="button" value="选择课件文件..."/>'+
			            '</div>'+
			        '</div>'+
			        '</form>';
			    layer.open({
				    type: 1,
				    title: "课件文件附件上传",
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
							if(f.type !== 'application/msword'
									&& f.type !== 'application/pdf' 
									&& f.type !== 'application/rtf'
									&& f.type !== 'application/vnd.ms-excel'
									&& f.type !== 'application/vnd.ms-powerpoint'
									&& f.type !== 'application/vnd.ms-works'
									&& f.type !== 'text/plain'
									&& f.type !== 'video/x-msvideo'
									&& f.type !== 'image/jpeg'
									&& f.type !== 'image/bmp'){
								layer.alert('文件类型不匹配请上传application/msword、application/pdf、application/rtf、application/vnd.ms-excel、application/vnd.ms-powerpoint、application/vnd.ms-works、text/plain、video/x-msvideo、image/jpeg、image/bmp格式文件', {icon: 1});
								return;
							}else if(f.size > (50*1024*1024)){
								layer.alert('文件大小超出' + (50*1024*1024) + 'KB范围', {icon: 1});
								return;
							}
							layero.find("input[type=text][name=coursePath]").val($(this).val());
						});
				    },
				    yes: function(index, layero){
				    	
				    	var formData = new FormData();
				    	formData.append('file', layero.find("input[type=file][name=file]")[0].files[0]);
				    	$.ajax({
							url : '${ctx}/course/course/upload',
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
								$("#path").val(data.message);
								$("#courseDetails").attr("href", '${fileAbsPath}' + data.message);
							}
							layer.close(index);
						}).fail(function(res) {
							
						});
				    }
			    });
			};
			
			$("#btnShowDialog").on("click", ajaxFileUpload);
			
			$("#inputForm").validate({
				errorElement: 'div',
	    		errorClass: 'help-block',
	    		focusInvalid: false,
	    		rules: {
		    		name: {
		    			required: true
		    		},
		    		path: {
		    			required: true
		    		}
		    	},messages: {
		    		name: {
		    			required: '课件名称必填！'
		    		},
		    		path: {
		    			required: '课件文件必填！'
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
			
			$(".i-checks").iCheck({checkboxClass:"icheckbox_square-green",radioClass:"iradio_square-green"})
		});
	</script>
</html>