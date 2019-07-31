<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<!-- Mirrored from www.zi-han.net/theme/hplus/form_validate.html by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 20 Jan 2016 14:19:15 GMT -->
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>条目答案管理</title>
    <meta name="keywords" content="${fns:getConfig('keywords')} ${sysConfig.schoolName } ">
    <meta name="description" content="${fns:getConfig('description')} ${sysConfig.schoolName }">
    
    <link href="${hplusStatic }/favicon.ico" rel="shortcut icon">
	<link href="${hplusStatic }/css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
    <link href="${hplusStatic }/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
    <link href="${hplusStatic }/css/animate.min.css" rel="stylesheet">
    <link href="${hplusStatic }/css/style.min862f.css?v=4.1.0" rel="stylesheet">

</head>

<body class="gray-bg" id="answerFromBody">
    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <%-- <div class="ibox-title">
                        <h5><a class="text-navy" href="${ctx}/jzmk/answer/form?id=${answer.id}">条目答案<shiro:hasPermission name="sys:answer:edit">${not empty answer.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="sys:answer:edit">查看</shiro:lacksPermission></a></h5>
                        <div class="ibox-tools">
                        </div>
                    </div> --%>
                    <div class="ibox-content">
                    	<%-- <span class="help-block m-b-none"><i class="fa fa-info-circle"></i> 正在为条目 ${qids } 设置答案</span> --%>
                        <form class="form-horizontal m-t" id="signupForm" action="${ctx}/jzmk/answer/save" method="post">
                            <input type="hidden" name="id" value="${answer.id }">
                            <input type="hidden" name="qids" value="${qids }">
                            <div class="form-group">
                                <label class="col-sm-3 control-label">答案：</label>
                                <div class="col-sm-8">
                                    <input id="answerContent" name="answerContent" value="${answer.answerContent }" class="form-control" type="text" required="required">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">分数：</label>
                                <div class="col-sm-8">
                                    <input id="score" value="${answer.score }" name="score" class="form-control" type="text" aria-required="true" aria-invalid="true" class="error">
                                </div>
                            </div>
                            <!-- <div class="form-group">
                                <div class="col-sm-8 col-sm-offset-3">
                                    <button class="btn btn-primary" type="submit" id="btnCloseIfream">保存</button>&nbsp;
                                </div>
                            </div> -->
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script src="${hplusStatic }/js/jquery.min.js?v=2.1.4"></script>
    <script type="text/javascript">
    function windowOpen(url, name, width, height){
    	var top=parseInt((window.screen.height-height)/2,10),left=parseInt((window.screen.width-width)/2,10),
    		options="location=no,menubar=no,toolbar=no,dependent=yes,minimizable=no,modal=yes,alwaysRaised=yes,"+
    		"resizable=yes,scrollbars=yes,"+"width="+width+",height="+height+",top="+top+",left="+left;
    	window.open(url ,name , options);
    }
    </script>
    <script src="${hplusStatic }/js/bootstrap.min.js?v=3.3.6"></script>
    <script src="${hplusStatic }/js/content.min.js?v=1.0.0"></script>
    <script src="${hplusStatic }/js/plugins/validate/jquery.validate.min.js"></script>
    <script src="${hplusStatic }/js/plugins/validate/messages_zh.min.js"></script>
    <script src="${hplusStatic }/js/demo/form-validate-demo.min.js"></script>
    <script src="${hplusStatic }/js/plugins/layer/layer.min.js" type="text/javascript"></script>
</body>
</html>