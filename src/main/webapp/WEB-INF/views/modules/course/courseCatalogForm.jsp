<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<!-- Mirrored from www.zi-han.net/theme/hplus/form_validate.html by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 20 Jan 2016 14:19:15 GMT -->
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>${sysConfig.schoolName } 机构--课件分类列表管理</title>
    <meta name="keywords" content="${fns:getConfig('keywords')} ${sysConfig.schoolName } ">
    <meta name="description" content="${fns:getConfig('description')} ${sysConfig.schoolName }">
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
                        <h5>课件分类${courseCatalog.id!=null?"修改":"添加"}</h5>
                        <div class="ibox-tools">
                            <!--
							<a class="text-navy" href="${ctx}/course/courseCatalog/form">咨询室查看</a>
							<a class="collapse-link">
                                <i class="fa fa-chevron-up"></i>
                            </a>
                            <a class="dropdown-toggle" data-toggle="dropdown" href="form_basic.html#">
                                <i class="fa fa-wrench"></i>
                            </a>
                            <a class="close-link">
                                <i class="fa fa-times"></i>
                            </a>
							-->
                        </div>
                    </div>
					
			<div class="ibox-content">
                  <form class="form-horizontal m-t" id="inputForm" 
						modelAttribute="courseCatalog"
						action="${ctx}/course/courseCatalog/save" method="post">
                        <input type="hidden" name="id" id="id" value="${courseCatalog.id }" required="required">
						<!-- 	
						<div class="form-group">
							<label class="col-sm-3 control-label">父级编号:</label>
							<div class="col-sm-4">
								<sys:treeselect id="parent" name="parent.id" value="${courseCatalog.parent.id}" labelName="parent.name" labelValue="${courseCatalog.parent.name}"
									title="父级编号" url="/course/courseCatalog/treeData" extId="${courseCatalog.id}" cssClass="col-sm-4" allowClear="true"/>
							</div>
						</div>	
						 -->
						<div class="form-group">
                                <label class="col-sm-3 control-label">名称：</label>
                                <div class="col-sm-4">								
									<input name="name" id="name" htmlEscape="false" maxlength="16" class="form-control" type="text" value="${courseCatalog.name }"/>
                                </div>
                        </div>
						
						<%-- <div class="form-group">
							<label class="col-sm-3  control-label">排序：</label>
							<div class="col-sm-8">
								<form:input path="sort" htmlEscape="false" maxlength="2" class="form-control" />
							</div>
						</div> --%>
						
						<div class="form-group">
                                <label class="col-sm-3 control-label">备注：</label>
                                <div class="col-sm-8">
									<input name="remarks" id ="remarks" class="form-control" type="text" value="${courseCatalog.remarks }"/>
                                </div>
                        </div>	
				
	
						<div class="form-group">
                                <div class="col-sm-8 col-sm-offset-3">
									<%-- <shiro:hasPermission name="course:courseCatalog:edit"> --%>
									<input id="btnSubmit" class="btn btn-primary" type="submit" value="保存"/>&nbsp;
									<%-- </shiro:hasPermission> --%>
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

<%@ include file="/WEB-INF/views/include/commonformjs.jsp" %>
<script type="text/javascript">
	$(document).ready(function() {
			$("#name").focus();
		});
	$("#inputForm").validate({
	errorElement: 'div',
	errorClass: 'help-block',
	focusInvalid: false,
	rules: {
	    		name: {
	    			remote: {
		    		    url: "${ctx}/course/courseCatalog/checkCatalog",     //后台处理程序
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
		    		required:true
	    		}
	    	},messages: {
	    		name: {
	    			remote: "分类已存在！",
	    			required:"分类名称必填"
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
</script>

</html>