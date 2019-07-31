<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<!-- Mirrored from www.zi-han.net/theme/hplus/form_validate.html by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 20 Jan 2016 14:19:15 GMT -->
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>量表类型管理</title>
    <meta name="keywords" content="${fns:getConfig('keywords')} ${sysConfig.schoolName } ">
    <meta name="description" content="${fns:getConfig('description')} ${sysConfig.schoolName }">
    
    <link href="${hplusStatic }/favicon.ico" rel="shortcut icon">
	<link href="${hplusStatic }/css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
    <link href="${hplusStatic }/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
    <link href="${hplusStatic }/css/animate.min.css" rel="stylesheet">
    <link href="${hplusStatic }/css/style.min862f.css?v=4.1.0" rel="stylesheet">
    <link href="${hplusStatic }/css/plugins/awesome-bootstrap-checkbox/awesome-bootstrap-checkbox.css" rel="stylesheet">
    <link href="${hplusStatic }/css/plugins/summernote/summernote.css" rel="stylesheet">
    <link href="${hplusStatic }/css/plugins/summernote/summernote-bs3.css" rel="stylesheet">

</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5><a class="text-navy" href="javascript:">量表类型管理</a></h5>
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
                    <div class="ibox-content">
                        <%-- <form class="form-horizontal m-t" id="signupForm" action="${ctx}/sys/org/save" method="post"> --%>
                        <form class="form-horizontal m-t" id="signupForm" action="${ctx}/jzmk/scaleType/save" method="post">
                            <input type="hidden" name="id" value="${scaleType.id }">
                            <div class="form-group">
                                <label class="col-sm-3 control-label">类型：</label>
                                <div class="col-sm-8">
                                   	<input id="name" name="name" value="${scaleType.name }" class="form-control" type="text" required="required">
                                    <span class="help-block m-b-none"><i class="fa fa-info-circle"></i> （必填）</span>
                                </div>
                            </div>
                            <%-- <div class="form-group">
                                <label class="col-sm-3 control-label">内容：</label>
                                <div class="col-sm-8">
                                 	<input id="introduce" name="introduce" type="hidden" value="">
                                    <input id="introduce" value="${scaleType.introduce }" name="introduce" class="form-control" type="text" aria-required="true" aria-invalid="true" class="error">
			                        <div class="summernote"></div>
                                </div>
                            </div> --%>
                            <%-- <div class="form-group">
                                <label class="col-sm-3 control-label">缩略图：</label>
                                <div class="col-sm-8">
                                    <form:hidden id="nameImage" path="scaleType.headImage" htmlEscape="false" maxlength="255" class="input-xlarge"/>
									<sys:ckfinder input="nameImage" type="images" uploadPath="/jzmk/scaleType" selectMultiple="false" maxWidth="100" maxHeight="100"/>
                                </div>
                            </div> --%>
                            <%-- <div class="form-group">
                                <label class="col-sm-3 control-label">是否首页显示：</label>
                                <div class="col-md-4">
                                <fieldset>
                                    <div class="radio radio-info radio-inline">
                                        <input type="radio" id="inlineRadio1" value="1" name="firstDisplay" ${scaleType.firstDisplay==1?"checked":"" } required="required">
                                        <label for="inlineRadio1">是</label>
                                    </div>
                                    <div class="radio radio-inline">
                                        <input type="radio" id="inlineRadio2" value="0" name="firstDisplay" ${scaleType.firstDisplay==0?"checked":"" } required="required">
                                        <label for="inlineRadio2">否</label>
                                    </div>
                                </fieldset>
                                </div>
                            </div> --%>
                            <!-- <div class="form-group">
                                <div class="col-sm-8 col-sm-offset-3">
                                    <div class="checkbox">
                                        <label>
                                            <input type="checkbox" class="checkbox" id="agree" name="agree"> 我已经认真阅读并同意《H+使用协议》
                                        </label>
                                    </div>
                                </div>
                            </div> -->
                            <div class="form-group">
                                <div class="col-sm-8 col-sm-offset-3">
                                    <button class="btn btn-primary" type="submit">保存</button>&nbsp;
                                    <button class="btn btn-primary" onclick="history.go(-1)">返回</button>
                                </div>
                            </div>
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
    <script src="${hplusStatic }/js/plugins/summernote/summernote.min.js"></script>
    <script src="${hplusStatic }/js/plugins/summernote/summernote-zh-CN.js"></script>
    <script>
        $(document).ready(function(){
        	$(".summernote").summernote({
        		lang:"zh-CN",
        		height: 300,
        		focus:true
        		/* callbacks: {  
                    onImageUpload: function(files, editor, $editable) {  
                        sendFile(files);  
                    }  
                }   */
        	});
        	$('.summernote').code('${scaleType.introduce }');
        });
       	/* var edit=function(){
       		$("#eg").addClass("no-padding");
       		$(".click2edit").summernote({lang:"zh-CN",focus:true})
       	};
       	var save=function(){
       		$("#eg").removeClass("no-padding");
       		var aHTML=$(".click2edit").code();
       		$(".click2edit").destroy()
       	}; */
       	$("button[type=submit]").click(function(){
       		var sHTML = $('.summernote').code();
       		$("#introduce").val(sHTML);
       	});
       	
       	/* function sendFile(files, editor, $editable) {  
            var data = new FormData();  
            data.append("ajaxTaskFile", files[0]);  
            $.ajax({  
                data : data,  
                type : "POST",  
                url : "${ctx}/admin/common/uploadPic.html", //图片上传出来的url，返回的是图片上传后的路径，http格式  
                cache : false,  
                contentType : false,  
                processData : false,  
                dataType : "json",  
                success: function(data) {//data是返回的hash,key之类的值，key是定义的文件名  
                    $('.summernote').summernote('insertImage', data.data);  
                },  
                error:function(){  
                    alert("上传失败");  
                }  
             });  
          }   */
    </script>
    <!-- <script src="http://tajs.qq.com/stats?sId=9051096" type="text/javascript" charset="UTF-8"></script> -->
</body>
<!-- Mirrored from www.zi-han.net/theme/hplus/form_validate.html by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 20 Jan 2016 14:19:16 GMT -->
</html>