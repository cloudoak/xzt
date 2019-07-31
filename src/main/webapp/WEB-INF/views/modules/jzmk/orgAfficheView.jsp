<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<!-- Mirrored from www.zi-han.net/theme/hplus/form_validate.html by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 20 Jan 2016 14:19:15 GMT -->
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>公告管理(机构)</title>
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
                        <h5>系统公告查看</h5>
                    </div>
                    <div class="ibox-content">
                        <form class="form-horizontal m-t" id="signupForm" method="post">
                            <input type="hidden" name="id" value="${sysAffiche.id }">
                            <div class="form-group">
                                <label class="col-sm-3 control-label">公告标题：</label>
                                <div class="col-sm-8">
                                   	<input id="afficheTitle" name="afficheTitle" value="${sysAffiche.afficheTitle }" class="form-control" type="text" readonly="readonly">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">内容：</label>
                                <div class="col-sm-8">
                                 	<input id="afficheContent" name="afficheContent" type="hidden" value="${sysAffiche.afficheContent}" >
			                        <div class="summernote"></div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">是否首页显示：</label>
                                <div class="col-md-4">
                                <fieldset>
                                    <div class="radio radio-info radio-inline">
                                        <input type="radio" id="inlineRadio1" value="1" name="firstDisplay" ${sysAffiche.firstDisplay==1?"checked":"" } disabled="disabled">
                                        <label for="inlineRadio1">是</label>
                                    </div>
                                    <div class="radio radio-inline">
                                        <input type="radio" id="inlineRadio2" value="0" name="firstDisplay" ${sysAffiche.firstDisplay==0?"checked":"" } disabled="disabled">
                                        <label for="inlineRadio2">否</label>
                                    </div>
                                </fieldset>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-8 col-sm-offset-3">
                                    <input class="btn btn-primary" type="button" onclick="history.go(-1)" value="返回" />
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
        	$('.summernote').code('${sysAffiche.afficheContent }');
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
       		$("#afficheContent").val(sHTML);
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