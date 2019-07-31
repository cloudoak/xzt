<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scaleTotalExplain=1.0">

    <title>总解释管理</title>
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
                        <h5><a class="text-navy" href="javascript:">总解释添加</a></h5>
                        <div class="ibox-tools">
                        </div>
                    </div>
                    <div class="ibox-content">
                        <form class="form-horizontal m-t" id="scaleTotalExplainForm" action="${ctx}/jzmk/scaleTotalExplain/save" method="post">
                        	<input type="hidden" id="tid" name="tid" value="${tid }"> 
                            <input type="hidden" name="id" value="${scaleTotalExplain.id }">
                            <div class="form-group">
                                <label class="col-sm-3 control-label"></label>
                                <div class="col-sm-12">
			                    	<div class="liangbao">
							         	<a href="#" class="hover"><em class="em1 hover"></em>01基本信息</a>
							            <a href="#" class="hover"><em class="em2 hover"></em>02条目设置</a>
							            <a href="#" class="hover"><em class="em3 hover"></em>03因子设置</a>
							            <a href="#" class="hover"><em class="em4 hover"></em>04因子解释</a>
							            <a href="#" class="hover"><em class="em5 hover"></em>05总解释</a>
							         </div>
						        </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">分数类型：</label>
                                <div class="col-sm-8">
                                	<select class="form-control m-b" id="scoreType" name="scoreType">
	                                	<option value="1" hassubinfo="true" ${scaleTotalExplain.scoreType==1?"selected":""}>原始分</option>
	                                	<option value="2" hassubinfo="true" ${scaleTotalExplain.scoreType==2?"selected":""}>平均分</option>
	                                </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">起始分：</label>
                                <div class="col-sm-8">
                                    <input id="minValue" name="minValue" value="${scaleTotalExplain.minValue }" class="form-control" type="number" required="required"> 
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">结束分：</label>
                                <div class="col-sm-8">
                                    <input id="maxValue" name="maxValue" value="${scaleTotalExplain.maxValue }" class="form-control" type="number" required="required"> 
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">总解释：</label>
                                <div class="col-sm-8">
                                    <input id="explainContent" name="explainContent" value="${scaleTotalExplain.explainContent }" class="form-control" type="hidden" required="required"> 
                               		<div class="summernote"></div>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-8 col-sm-offset-3">
                                    <button class="btn btn-primary" type="submit" id="btnQuestion">保存</button>&nbsp;
                                    <button class="btn btn-primary" type="button" onclick="toBack(${tid})">返回</button>
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
    <script>
	    function windowOpen(url, name, width, height){
	    	var top=parseInt((window.screen.height-height)/2,10),left=parseInt((window.screen.width-width)/2,10),
	    		options="location=no,menubar=no,toolbar=no,dependent=yes,minimizable=no,modal=yes,alwaysRaised=yes,"+
	    		"resizable=yes,scrollbars=yes,"+"width="+width+",height="+height+",top="+top+",left="+left;
	    	window.open(url, name, options);
	    }
	    
	    function toBack(tid){
	    	location.href = '${ctx}/jzmk/scaleTotalExplain/list?tid='+ tid;
	    }
	   	
        $(function(){
        	$(".summernote").summernote({
	    		lang:"zh-CN",
	    		height: 300,
	    		focus:true
	    	});
	    	$('.summernote').code('${scaleTotalExplain.explainContent }');
	    	
	    	jQuery.validator.addMethod("compareMaxValue", function(value, element, param) {
           	  var minValue = $("#" + param).val();
           	  return this.optional(element) || (parseInt(value) > parseInt(minValue) );   
           	});
	    	
	    	$("#scaleTotalExplainForm").validate({
	    		errorElement: 'div',
	    		errorClass: 'help-block',
	    		focusInvalid: false,
	    		rules: {
	    			minValue: {
	    				required: true
	    			},
	    			maxValue: {
	    				compareMaxValue: 'minValue',
	    				remote: {
							url: "${ctx}/jzmk/scaleTotalExplain/checkMaxValue",
	                        type: "post",
	                        dataType: "json",
	                        data: {
	                        	maxValue: function () {
	                        		return $("#maxValue").val();
	                        	},
	                        	tid: function() {
	                        		return $("#tid").val();
	                        	}
	                        },
	                        dataFilter: function (data) {
	                        	return (data == 1);
	                        }
	    				}
	    			}
		    	},messages: {
		    		maxValue: {
		    			compareMaxValue: '起始分比结束分数值要大！',
		    			remote: '结束分数值超出了该量表下所有条目设置的答案总分数值！'
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
					var sHTML = $('.summernote').code();
			   		$("#explainContent").val(sHTML); 
					form.submit();
				}
	    	});
	    	
        	$(".i-checks").iCheck({checkboxClass:"icheckbox_square-green", radioClass:"iradio_square-green"});
        });
    </script>
</body>
</html>