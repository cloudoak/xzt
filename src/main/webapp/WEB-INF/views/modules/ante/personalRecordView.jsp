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
</head>
<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>个人成长记录查看</h5>
                    </div>
                    <div class="ibox-content">
                        <form:form class="form-horizontal m-t" id="inputForm" modelAttribute="personalRecord" action="${ctx}/ante/personalRecord/save" method="post">
                            <input type="hidden" name="id" value="${personalRecord.id }">
                            <div class="form-group">
                                <label class="col-sm-3 control-label">主题：</label>
                                <div class="col-sm-8">
                                    <input id="subject" name="subject" value="${personalRecord.subject}" htmlEscape="false" class="input-xlarge" readonly="readonly"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">内容：</label>
                                <div class="col-sm-8">
									<form:textarea path="content" htmlEscape="false" rows="4" maxlength="200" class="input-xlarge" disabled="true"/>
									<sys:ckeditor replace="content" uploadPath="/ante/personalRecord" />
								</div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-8 col-sm-offset-3">
                                    <input type="button" class="btn btn-primary" onclick="history.go(-1)" value="返回"/>
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