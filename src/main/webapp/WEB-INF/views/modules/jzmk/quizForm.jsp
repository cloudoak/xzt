<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scaleTotalExplain=1.0">

    <title>量表测试管理</title>
    <meta name="keywords" content="${fns:getConfig('keywords')} ${sysConfig.schoolName } ">
    <meta name="description" content="${fns:getConfig('description')} ${sysConfig.schoolName }">
    
    <link href="${hplusStatic }/favicon.ico" rel="shortcut icon">
	<link href="${hplusStatic }/css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
    <link href="${hplusStatic }/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
    <link href="${hplusStatic }/css/animate.min.css" rel="stylesheet">
    <link href="${hplusStatic }/css/style.min862f.css?v=4.1.0" rel="stylesheet">
    <link href="${hplusStatic }/css/plugins/bootstrap-table/bootstrap-table.min.css" rel="stylesheet">
    <link href="${hplusStatic }/css/plugins/awesome-bootstrap-checkbox/awesome-bootstrap-checkbox.css" rel="stylesheet">
    <link href="${hplusStatic }/css/plugins/summernote/summernote.css" rel="stylesheet">
    <link href="${hplusStatic }/css/plugins/summernote/summernote-bs3.css" rel="stylesheet">
    <link href="${hplusStatic }/css/plugins/iCheck/custom.css" rel="stylesheet">
    
	<link href="${serverUrl }css/measure.css" type="text/css" rel="stylesheet" />
</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5><a class="text-navy" href="javascript:void();">量表测试</a></h5>
                        <div class="ibox-tools">
                        </div>
                    </div>
                    <div class="ibox-content">
                        <form class="form-horizontal m-t" id="quizForm" action="${ctx}/jzmk/scale/question?sid=${scale.id}&questionNo=1" method="post">
                        <input type="hidden" name="maxAnswerTime" value="${scale.maxAnswerTime}" />
                        <input type="hidden" name="name" value="${scale.name}" />
                        <input type="hidden" name="inside" value="${scale.inside}" />
						<div class="form-group">
                            <div class="col-sm-8">
                            	<span class="caption">${scale.name }</span>
                            </div>
                        </div>
						<div class="form-group top-dashed">
                            <label class="maintitle"><strong>介绍</strong></label>
                        </div>
                        <div class="form-group">
                            <label class="subtitle">${scale.introduce}</label>
                        </div>
						<div class="form-group bottom-dashed">
                            <label class="col-sm-4 control-label">答题时间：</label>
                            <label class="col-sm-4 control-label">${scale.maxAnswerTime}分钟</label>
                        </div>
						<div class="form-group">
                             <div class="col-sm-8 col-sm-offset-3">
                                <button class="btn btn-primary" id="btnStart" name="btnStart" type="submit">开始测试</button>&nbsp;
                     			<button class="btn btn-primary" id="btnUnderstand" name="btnUnderstand" type="button" onclick="understand();">不明白</button>
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
    <script src="${hplusStatic }/js/plugins/validate/jquery.validate.min.js"></script>
    <script src="${hplusStatic }/js/plugins/validate/messages_zh.min.js"></script>
    <script src="${hplusStatic }/js/demo/form-validate-demo.min.js"></script>
    <script src="${hplusStatic }/js/plugins/iCheck/icheck.min.js"></script>
    <script src="${hplusStatic }/js/plugins/bootstrap-table/bootstrap-table.min.js"></script>
    <script src="${hplusStatic }/js/plugins/bootstrap-table/bootstrap-table-mobile.min.js"></script>
    <script src="${hplusStatic }/js/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>
    <script src="${hplusStatic }/js/demo/bootstrap-table-demo.min.js"></script>
    <script src="${hplusStatic }/js/plugins/layer/layer.min.js" type="text/javascript"></script>
    <script src="${hplusStatic }/js/plugins/summernote/summernote.min.js"></script>
    <script src="${hplusStatic }/js/plugins/summernote/summernote-zh-CN.js"></script>
    <style type="text/css">
     .maintitle{
     	font-size: 16px;
     	font-family: '黑体';
     	padding-left: 10px;
     }
     .subtitle{
     	font-size: 14px;
     	font-family: '黑体';
     	padding-left: 10px;
     }
     .caption{
     	font-size: 20px;
	    font-weight: bold;
	    color: navy;
     }
     .top-dashed{
      	border-top: 1px dashed #cccccc; 
      	margin-top: 20px;
      	padding-top: 20px;
     }
     .bottom-dashed{
      	border-bottom: 1px dashed #cccccc;
      	margin-bottom: 20px;
      	padding-bottom: 20px;
     }
    </style>
  	<script>
		function understand(){
			
		}
        $(function(){
        	var t, maxtime = parseInt('${scale.minShowTime}');
        	$("#btnStart").attr("disabled", true);
        	$("#btnStart").text("剩余（" + maxtime + " 秒）");
			window.setMinute = function()
			{
			    if (maxtime == 0) return;
			    else maxtime--;
			    if (maxtime == 0) {
			    	$("#btnStart").attr("disabled", false);
			        window.clearInterval(t);
			        $("#btnStart").text("开始测试");
			    }
			    else {
			    	$("#btnStart").text("剩余（" + maxtime + " 秒）");
			    }
			};
			t = window.setInterval("window.setMinute()", 1000);
        });
    </script>
</body>
</html>