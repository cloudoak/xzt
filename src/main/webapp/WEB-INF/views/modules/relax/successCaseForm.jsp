<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
   <meta charset="utf-8">
   <meta name="viewport" content="width=device-width, initial-scale=1.0">
   <title>${sysConfig.schoolName } 机构--成功案例管理</title>
   <meta name="keywords" content="${fns:getConfig('keywords')} ${sysConfig.schoolName } ">
   <meta name="description" content="${fns:getConfig('description')} ${sysConfig.schoolName }">
<%@ include file="/WEB-INF/views/include/commoncss.jsp" %>
<%@ include file="/WEB-INF/views/include/commonformjs.jsp" %>	
</head>
<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>成功案例信息</h5>
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
					
					
        </div>
    </div>
	
	
		<div class="ibox-content">
		<sys:message content="${message}"/>		
                        <form:form class="form-horizontal m-t" id="inputForm" 
						modelAttribute="successCase"
						action="${ctx}/relax/successCase/save" method="post">
                            <input type="hidden" name="id" value="${successCase.id }">
							
							<div class="form-group">
                                <label class="col-sm-3 control-label">案例名称：</label>
                                <div class="col-sm-8">								
									<form:input path="name" htmlEscape="false" maxlength="16" class="form-control"   type="text"/>
                                </div>
                            </div>
						<div class="form-group">
                                <label class="col-sm-3 control-label">时间：</label>
                                <div class="col-sm-4">
								<input name="createDate" type="text" readonly="readonly" maxlength="20" 
								class="form-control"
						value="<fmt:formatDate value="${successCase.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>" />
                                </div>
                            </div>			
						
					<div class="form-group">
						<label class="col-sm-3 control-label">上传案例：</label>
						<div class="col-sm-4">
						<%-- form:hidden id="path" path="path" htmlEscape="false" maxlength="255" class="input-xlarge"/>
						<sys:ckfinder input="path" type="files" uploadPath="/relax/successCase" selectMultiple="false" maxWidth="100" maxHeight="100"/> --%>
							<input type="text" name="path" id="path" value="${successCase.path}"  readonly="readonly" class="form-control" />
							<input id="btnShowDialog" class="btn btn-primary" type="button" value="上传"/>
						</div>
					</div>
					<div class="form-group">
                                <label class="col-sm-3 control-label">发布人：</label>
                                <div class="col-sm-8">	
									${successCase.creatorName}
                                </div>
                     </div>
					<div class="form-group">
                                <label class="col-sm-3 control-label">所属机构：</label>
                                <div class="col-sm-8">								
									${successCase.orgName}
                                </div>
                      </div>
					
					<div class="form-group">
						<label class="col-sm-3 control-label">描述：</label>
						<div class="col-sm-8">
							<form:textarea path="intro" htmlEscape="false" rows="4" maxlength="128" class="form-control"  type="text"/>
						</div>
					</div>               
							<div class="form-group">
						<label class="col-sm-3 control-label">是否共享：</label>
						<div class="col-sm-2">
							<form:radiobuttons path="isPublic" items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false" class=""/>
						</div>
					</div>
							<div class="form-group">
                                <div class="col-sm-8 col-sm-offset-3">
									<shiro:hasPermission name="relax:successCase:edit">
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
<style>
.file {
    position: relative;
    display: inline-block;
    background: #D0EEFF;
    border: 1px solid #99D3F5;
    border-radius: 4px;
    padding: 4px 12px;
    overflow: hidden;
    color: #1E88C7;
    text-decoration: none;
    text-indent: 0;
    line-height: 20px;
}
.file input {
    position: absolute;
    font-size: 100px;
    right: 0;
    top: 0;
    opacity: 0;
}
.file:hover {
    background: #AADFFD;
    border-color: #78C3F3;
    color: #004974;
    text-decoration: none;
}
</style>
<script src="${hplusStatic }/js/plugins/layer/layer.min.js" type="text/javascript"></script>
<script type="text/javascript">

	var ajaxFileUpload = function(){
		var ctx = '${ctx}', 
			html = '<form  class="layui-form" action="'+ctx+'/relax/successCase/upload" method="post">' +
			'<div class="layui-form-item">'+
				'<label class="layui-form-label">案例文件附件：</label>'+
	            '<div class="layui-input-block">'+
	                '<input type="file" name="file" id="file" class="layui-input file" />'+
	            '</div>'+
	        '</div>'+
	        '</form>';
	    layer.open({
		    type: 1,
		    title: "案例附件上传",
		    shadeClose:true,
		    shade: 0.3,
		    area: ['340px', '175px'],
		    offset: 'c',
		    shift: 2,
		    scrollbar : false,
		    content: html,
		    btn:['保存','关闭'],
		    yes: function(index, layero){
		    	var formData = new FormData();
		    	formData.append('file', $('#file')[0].files[0]);
		    	$.ajax({
					url : '${ctx}/relax/successCase/upload',
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
					}
					layer.close(index);
				}).fail(function(res) {
					
				});
		    }
	    });
	};
	
	$(function() {
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
	});
</script>
	
</html>