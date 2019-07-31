<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>圈子动态回复</title>
	<meta name="decorator" content="default"/>
	<link rel="shortcut icon" href="${hplusStatic }/favicon.ico">
    <link href="${hplusStatic }/css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
    <link href="${hplusStatic }/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
    <link href="${hplusStatic }/css/animate.min.css" rel="stylesheet">
    <link href="${hplusStatic }/css/plugins/summernote/summernote.css" rel="stylesheet">
    <link href="${hplusStatic }/css/plugins/summernote/summernote-bs3.css" rel="stylesheet">
    <link href="${hplusStatic }/css/style.min862f.css?v=4.1.0" rel="stylesheet">
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
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
</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h4 class="text-navy">圈子动态回复</h4>
                    </div>
                    <div class="ibox-content no-padding">
                    <form class="form-horizontal m-t" modelAttribute="circle" id="circleForm" action="${ctx}/jzmk/circle/save" method="post">
                    <div class="form-group">
                    	<label class="col-sm-3 control-label">原发布人</label>
                    	<div class="col-sm-8">
                    		<input id="userName" name="userName" value="${circle.userName}" readonly="true" required="required">
                        </div>
                    </div>
                    <div class="form-group">
                    	<label class="col-sm-3 control-label">回复发言</label>
                    	<div class="col-sm-8">
                    		<input id="content" name="content" value="" type="hidden" required="required">
                        	<div class="summernote"></div>
                        </div>
                    </div>
                    <div class="form-group">
           				<div class="col-sm-8 col-sm-offset-3">
						<input id="btnSubmit" class="btn btn-primary" type="submit" onClick="checkValue();" value="保 存"/>&nbsp;
						<input id="btnCancel" class="btn btn-primary" type="button" value="返 回" onclick="history.go(-1)"/>
						</div>
					</div>
                    </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script src="${hplusStatic }/js/jquery.min.js?v=2.1.4"></script>
    <script src="${hplusStatic }/js/bootstrap.min.js?v=3.3.6"></script>
    <script src="${hplusStatic }/js/content.min.js?v=1.0.0"></script>
    <script src="${hplusStatic }/js/plugins/summernote/summernote.min.js"></script>
    <script src="${hplusStatic }/js/plugins/summernote/summernote-zh-CN.js"></script>
    <script>
        $(document).ready(function(){
        	$(".summernote").summernote({
        		lang:"zh-CN",
        		height:300,
        		focus:true
        	})
        	//$('.summernote').code('${circle.content}');
        });
        function checkValue()
		{
			var sHTML = $('.summernote').code();
	   		$("#content").val(sHTML); 
		}
    </script>
</body>
</html>