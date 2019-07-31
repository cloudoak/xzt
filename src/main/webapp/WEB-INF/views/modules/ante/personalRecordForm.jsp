<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>个人成长记录管理</title>
	<meta charset="utf-8">
	<meta name="decorator" content="default"/>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="keywords" content="${fns:getConfig('keywords')} ${sysConfig.schoolName } ">
    <meta name="description" content="${fns:getConfig('description')} ${sysConfig.schoolName }">
    <%@ include file="/WEB-INF/views/include/commoncss.jsp" %>
	
	<script type="text/javascript">
		$(document).ready(function() {
			$("#inputForm").validate({
				submitHandler: function(form){
					//loading('正在提交，请稍等...');
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
</head>
<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5><a class="text-navy" href="${ctx}/ante/personalRecord/form?id=${personalRecord.id}">个人成长记录<shiro:hasPermission name="ante:personalRecord:edit">${not empty personalRecord.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="ante:personalRecord:edit">查看</shiro:lacksPermission></a></h5>
                    </div>
                    <div class="ibox-content">
                        <form:form class="form-horizontal m-t" id="inputForm" modelAttribute="personalRecord" action="${ctx}/ante/personalRecord/save" method="post">
                            <input type="hidden" name="id" value="${personalRecord.id }">
                            <div class="form-group">
                                <label class="col-sm-3 control-label">主题：</label>
                                <div class="col-sm-8">
                                    <input id="subject" name="subject" value="${personalRecord.subject}" htmlEscape="false" class="input-xlarge" required="true" maxlength="255"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">内容：</label>
                                <div class="col-sm-8">
									<form:textarea path="content" htmlEscape="false" rows="4" maxlength="200" class="input-xlarge" required="required"/>
									<sys:ckeditor replace="content" uploadPath="/ante/personalRecord" />
								</div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-8 col-sm-offset-3">
                                    <shiro:hasPermission name="ante:personalRecord:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
                                    <button class="btn btn-primary" onclick="history.go(-1)">返回</button>
                                </div>
                            </div>
                        </form:form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    
    <%@ include file="/WEB-INF/views/include/commonlistjs.jsp" %>
    <script src="${hplusStatic }/js/plugins/validate/jquery.validate.min.js"></script>
    <script src="${hplusStatic }/js/plugins/validate/messages_zh.min.js"></script>
  	<script type="text/javascript">
   		 function windowOpen(url, name, width, height){
	    	var top=parseInt((window.screen.height-height)/2,10),left=parseInt((window.screen.width-width)/2,10)),
	    		options="location=no,menubar=no,toolbar=no,dependent=yes,minimizable=no,modal=yes,alwaysRaised=yes,"+
	    		"resizable=yes,scrollbars=yes,"+"width="+width+",height="+height+",top="+top+",left="+left;
	    	window.open(url ,name , options);
    }
    </script>
</body>
</html>