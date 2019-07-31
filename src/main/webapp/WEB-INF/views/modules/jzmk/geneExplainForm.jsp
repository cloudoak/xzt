<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-geneExplain=1.0">

    <title>因子解释管理</title>
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
                        <h5><a class="text-navy" href="javascript:">因子解释添加</a></h5>
                        <div class="ibox-tools">
                        </div>
                    </div>
                    <div class="ibox-content">
                        <form class="form-horizontal m-t" id="geneExplainForm" action="${ctx}/jzmk/geneExplain/save" method="post">
                        	<input type="hidden" name="tid" value="${tid }">
                            <input type="hidden" name="id" value="${geneExplain.id }">
                            <div class="form-group">
                                <label class="col-sm-3 control-label"></label>
                                <div class="col-sm-12">
			                    	<div class="liangbao">
							         	<a href="#" class="hover"><em class="em1 hover"></em>01基本信息</a>
							            <a href="#" class="hover"><em class="em2 hover"></em>02条目设置</a>
							            <a href="#" class="hover"><em class="em3 hover"></em>03因子设置</a>
							            <a href="#" class="hover"><em class="em4 hover"></em>04因子解释</a>
							            <a href="#"><em class="em5"></em>05总解释</a>
							         </div>
						        </div>
                            </div>
                            
                            <c:if test="${not empty sections}">
                            <c:forEach items="${sections }" var="section">
                            	<c:if test="${not empty section}">
                            	<c:set var="gsec" value="${fn:split(section.groupSection, '-')}" />
                            	<c:set var="gper" value="${(gsec[1] - gsec[0]) == 0 ? 100 : (gsec[1] - gsec[0])}" />
                            	<div class="sections">${ section.name }(${section.groupSection})</div>
                            	<div class="progress-bar">
                            	<c:if test="${not empty section.section}">
                            	<c:forEach items="${fn:split(section.section, ',')}" var="s_section">
                            	<c:set var="sec" value="${fn:split(s_section, '-')}" />
                            	<fmt:formatNumber var="percentage" value="${ ((sec[1] - sec[0]) < 0 ? -(sec[1] - sec[0]) : (sec[1] - sec[0]))/gper* 100 }" pattern="0.00" maxFractionDigits="2"/>
                          		<fmt:formatNumber var="r" value="${ sec[0]/gper * 100 }" pattern="0.00" maxFractionDigits="0"/>
								<fmt:formatNumber var="g" value="${ sec[1]/gper * 100 }" pattern="0.00" maxFractionDigits="0"/>
                            	<fmt:formatNumber var="b" value="${ percentage }" pattern="0.00" maxFractionDigits="0"/>
	                            <div class="progress-text" style="width:${percentage}%;background-color:rgba(${ r },${ g }, ${ b }, 0.5);">${ s_section }</div>
	                            </c:forEach>
	                            </c:if>
	                            </div>
	                            </c:if>
                            </c:forEach>
                            </c:if>
                            
                            <div class="form-group">
                                <label class="col-sm-3 control-label">选择因子：</label>
                                <div class="col-sm-8">
                                	<select class="form-control m-b" id="gid" name="gid">
	                                    <c:forEach items="${geneList }" var="gene">
	                                    	<option value="${gene.id}" hassubinfo="true">${gene.name}</option>
	                                    </c:forEach>
	                                </select>
                                </div>
                                <label class="col-sm-3 control-label">因子分数段：</label>
                                <div class="col-sm-8">
                                	<span class="help-block m-b-none">(根据值校验)</span>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">起始分：</label>
                                <div class="col-sm-8">
                                    <input id="minValue" name="minValue" value="${geneExplain.minValue }" class="form-control" type="number" required="required"> 
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">结束分：</label>
                                <div class="col-sm-8">
                                    <input id="maxValue" name="maxValue" value="${geneExplain.maxValue }" class="form-control" type="number" required="required"> 
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">因子解释：</label>
                                <div class="col-sm-8">
                                    <input id="geneExplain" name="geneExplain" value="${geneExplain.geneExplain }" class="form-control" type="hidden" required="required"> 
                               		<div class="summernote"></div>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-8 col-sm-offset-3">
                                    <button class="btn btn-primary" type="submit">保存</button>&nbsp;
                                    <button class="btn btn-primary" type="button" onclick="toBack(${tid });">返回</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
     <style type="text/css">
     	.sections{
	    	line-height:20px;height:20px;width:100%;text-align: center;
	    }
	    .progress-bar{
	    	line-height:30px;height:30px;width:100%;background-color:gray;border:solid 1px;border-radius: 2px;margin: 5px auto;
	    }
	    .progress-text{
	    	line-height:28px;height:28px;color:#FFFFFF;float:left;
	    }
	</style>
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
	    	window.open(url ,name , options);
	    }
	    function toBack(tid){
	    	location.href = "${ctx}/jzmk/geneExplain/list?tid=" + tid;
	    }
	    $(function(){
	    	var gid = "${geneExplain.gid}";
	    	if(gid && gid != ''){
	    		$("#gid").val(gid);
	    	}else{
	    		$("#gid").get(0).selectedIndex = 0;
	    	}

	    	$(".summernote").summernote({
	    		lang:"zh-CN",
	    		height: 300,
	    		focus:true
	    	});
	    	$('.summernote').code('${geneExplain.geneExplain }');
	    	
	    	$("#gid").on("change",function(){
	    		var v = $(this).val();
	    		location.href = "${ctx}/jzmk/geneExplain/form?"+ $("#geneExplainForm").serialize();
	    	});
	    	
	    	jQuery.validator.addMethod("compareMaxValue", function(value, element, param) {
           	  var minValue = $("#" + param).val();
           	  return this.optional(element) || (parseInt(value) > parseInt(minValue) );   
           	});
	    	
	    	$("#geneExplainForm").validate({
	    		errorElement: 'div',
	    		errorClass: 'help-block',
	    		focusInvalid: false,
	    		rules: {
	    		    gid: {
	    		    	required: true
	    		    },
	    			minValue: {
	    				remote: {
	    					url: "${ctx}/jzmk/geneExplain/checkMinValue",
	                        type: "post",
	                        dataType: "json",
	                        data: {
	                        	minValue: function () {
	                        		return $("#minValue").val();
	                        	},
	                        	gid: function() {
	                        		return $("#gid").val();
	                        	}
	                        },
	                        dataFilter: function (data) {
	                        	return (data == 1);
	                        }
	    				}
	    			},
	    			maxValue: {
	    				compareMaxValue: 'minValue',
						remote: {
							url: "${ctx}/jzmk/geneExplain/checkMaxValue",
	                        type: "post",
	                        dataType: "json",
	                        data: {
	                        	maxValue: function () {
	                        		return $("#maxValue").val();
	                        	},
	                        	gid: function() {
	                        		return $("#gid").val();
	                        	}
	                        },
	                        dataFilter: function (data) {
	                        	return (data == 1);
	                        }
	    				}
		    		}
		    	},messages: {
		    		minValue: {
		    			remote: '起始分小于因子最小值/因子解释前段的结束分！'
		    		},
		    		maxValue: {
		    			compareMaxValue: '起始分比结束分数值要大！',
		    			remote: '结束分超过因子最大值！'
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
			   		$("#geneExplain").val(sHTML); 
					form.submit();
				}
	    	});
	    	
	    	$(".i-checks").iCheck({checkboxClass:"icheckbox_square-green",radioClass:"iradio_square-green"});
	    });
    </script>
</body>
</html>