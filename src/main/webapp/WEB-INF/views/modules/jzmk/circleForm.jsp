<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>


<!-- Mirrored from www.zi-han.net/theme/hplus/form_editors.html by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 20 Jan 2016 14:19:35 GMT -->
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>圈子发言</title>
    <meta name="keywords" content="圈子">
    <meta name="description" content="圈子">
    <link rel="shortcut icon" href="${hplusStatic }/favicon.ico">
    <link href="${hplusStatic }/css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
    <link href="${hplusStatic }/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
    <link href="${hplusStatic }/css/animate.min.css" rel="stylesheet">
    <link href="${hplusStatic }/css/plugins/summernote/summernote.css" rel="stylesheet">
    <link href="${hplusStatic }/css/plugins/summernote/summernote-bs3.css" rel="stylesheet">
    <link href="${hplusStatic }/css/style.min862f.css?v=4.1.0" rel="stylesheet">

</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h4 class="text-navy">圈子发言（${currentUserArea}）</h4>
                    </div>
                    <div class="ibox-content no-padding">
                    <form class="form-horizontal m-t" modelAttribute="circle" id="circleForm" action="${ctx}/jzmk/circle/save" method="post">
                    <input type="hidden" name="id" value="${circle.id }">
                    <div class="form-group">
                    	<label class="col-sm-3 control-label">我的发言</label>
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
        	$('.summernote').code('${circle.content }');
        });
        function checkValue()
		{
			var sHTML = $('.summernote').code();
	   		$("#content").val(sHTML); 
		}
    </script>
</body>
<!-- Mirrored from www.zi-han.net/theme/hplus/form_editors.html by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 20 Jan 2016 14:19:35 GMT -->
</html>
