<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<!-- Mirrored from www.zi-han.net/theme/hplus/form_validate.html by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 20 Jan 2016 14:19:15 GMT -->
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>机构管理</title>
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
                        <h5><a class="text-navy" href="${ctx}/sys/dict/form?id=${dict.id}&parent.id=${office.parent.id}">问题类型管理<shiro:hasPermission name="sys:dict:edit">${not empty dict.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="sys:dict:edit">查看</shiro:lacksPermission></a></h5>
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
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/sys/dict/">字典列表</a></li>
		<li class="active"><a href="${ctx}/sys/dict/form?id=${dict.id}">字典<shiro:hasPermission name="sys:dict:edit">${not empty dict.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="sys:dict:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/> modelAttribute="dict" 
	-->
	<div class="ibox-content">
                        <form class="form-horizontal m-t" id="inputForm" action="${ctx}/sys/dict/saveQuestionType" method="post">
                            <input type="hidden" name="id" value="${dict.id }">
							 <input type="hidden" id="type" name="type" value="${dict.type }" >
                            <div class="form-group">
                                <label class="col-sm-3 control-label">键值：</label>
                                <div class="col-sm-8">
                                    <input id="value" name="value" value="${dict.value }" class="form-control" type="text" required="required">
                                    <span class="help-block m-b-none"><i class="fa fa-info-circle"></i> 键值必须是唯一的（必填）</span>
                                </div>
                            </div>
							<div class="form-group">
                                <label class="col-sm-3 control-label">问题类型名称：</label>
                                <div class="col-sm-8">
                                    <input id="label" name="label" value="${dict.label }" class="form-control" type="text" required="required">
                                    <span class="help-block m-b-none"><i class="fa fa-info-circle"></i> 键值必须是唯一的（必填）</span>
                                </div>
                            </div>                           
							<div class="form-group">
                                <label class="col-sm-3 control-label">描述：</label>
                                <div class="col-sm-8">
                                    <input id="description" name="description" value="${dict.description }" class="form-control" type="text" >
                                    <span class="help-block m-b-none"><i class="fa fa-info-circle"></i></span>
                                </div>
                            </div>
							
								<div class="form-group">
                                <label class="col-sm-3 control-label">排序：</label>
                                <div class="col-sm-8">
                                    <input id="sort" name="sort" value="${dict.sort}" class="form-control" type="text" required="required">
                                    <span class="help-block m-b-none"><i class="fa fa-info-circle"></i> </span>
                                </div>
                            </div>
							
				
		<div class="form-group">
		 <div class="col-sm-8 col-sm-offset-3">
			<shiro:hasPermission name="sys:dict:edit">
				<input id="btnSubmit" class="btn btn-primary" type="submit" value="提交"/>&nbsp;&nbsp;
			</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
		</div>
	</form>
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
			$("#value").focus();
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